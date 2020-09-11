/**
 * 
 */
package com.java.client;


import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.Thread.State;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.odinues.m1.frwx.message.MSG_FWXHEADER;
import com.odinues.m1.frwx.task.AbstractTask;
import com.odinues.m1.ipc.IPCFileQueue;
import com.odinues.m1.ipc.IPCFunctions;
import com.odinues.m1.ipc.IPCReader;
import com.odinues.m1vela.ftl.ByteSequence;
import com.odinues.m1vela.ftl.DevNullOutput;
import com.odinues.m1vela.ftl.RuntimeContext;
import com.odinues.m1vela.ftl.VelaTemplates;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public class VelaOpenAgent extends AbstractTask{

    ConcurrentHashMap<AgentWorker, Integer> workerMap =new ConcurrentHashMap<AgentWorker, Integer>(5);
    String configTag=null;
    IPCReader rdr = null;
    IPCFileQueue fq = null;

    int ipcTypeNumAck = 15 ;
    int ipcTypeNumRslt = 16 ;
    int workerCount=1;
    int threadHangTime=10000;
    int threadCycleTime=1000;

    int tSendCNT=0;
    
    
    public VelaOpenAgent(String tag) {
        super(tag);
    }
    
    @Override
    public void init() {
        configTag=this.getConfigTag();
        String queueSend=getProperty("queueSend","unknownq");
        if(queueSend.equals("unknownq")){
            ERROR("ERR Unspecified queueSend");
            return ;
        }
        rdr = new IPCReader(queueSend);
        int r =  rdr.open(rdr.getTimeout());
        if( r < 0){
            ERROR("ERR q.open(" + queueSend + ") =" + r);
            System.exit(-1);
        }
        
        String fileQueue=getProperty("fileQueue", "unknownq");
        if(fileQueue.equals("unknownq")){
            ERROR("ERR Unspecified fileQueue");
            return ;
        }
        fq = new IPCFileQueue(fileQueue);
        
        ipcTypeNumAck = getProperty("ipcTypeNumAck", ipcTypeNumAck); ;
        ipcTypeNumRslt = getProperty("ipcTypeNumRslt", ipcTypeNumRslt);
        
        workerCount=getProperty("workerCount", workerCount);
        if(workerCount>5){
            INFO("WorkerCount Max is 5, Resize ["+workerCount+"] >> 5");
            workerCount=5;
        }
        
        for(int i=0; i < workerCount; i++){
            AgentWorker aw=new AgentWorker(i, this);
            aw.setName(i+"th "+aw.getClass().getSimpleName());
            aw.start();
            INFO(aw.getName()+" Started!!");
            workerMap.put(aw, i);
        }
        
        threadHangTime = getProperty("threadHangTime", threadHangTime); ;
        threadCycleTime = getProperty("threadCycleTime", threadCycleTime);
    }

    @Override
    public void close() {
        if ( rdr != null ) {
            rdr.close() ;
            rdr = null;
        }
    }

    @Override
    public int doTask() {
        tSendCNT=0;
        long currentTime=System.currentTimeMillis();
        for(AgentWorker w: workerMap.keySet()){
            if(w.getState() == State.TERMINATED){
                int ipcBufNum=workerMap.get(w);
                AgentWorker aw=new AgentWorker(ipcBufNum, this);
                aw.setName(ipcBufNum+"th "+aw.getClass().getSimpleName());
                workerMap.put(aw, ipcBufNum);
                workerMap.remove(w);    
                aw.start();
                INFO(aw.getName()+"Worker Thread Restart!!");
            }
            int workerSendCount=w.getSendCount();
            tSendCNT+=workerSendCount;
            w.addSendCount(-workerSendCount);
            checkTimeout(w, currentTime);
            //w.interrupt(); work Thread safely stop
        }
        
        //처리 건수 리턴
        return tSendCNT;
    }

    @Override
    public int idle(long t) {
        return 0;
    }
    
    @Override
    public boolean stop() {
        for(AgentWorker w: workerMap.keySet()){
            w.stopThread();
            INFO(w.getName()+"Thread Stop");
            workerMap.remove(w);
            try {
                w.join(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return super.stop();
    }

    public void checkTimeout(AgentWorker w, long currentTime){
        //쓰레드 최근 시간 체크
        if((currentTime-w.getThreadHangCheckTimeMillis()) > threadHangTime){
            FATAL(w.getName() + " Thread Hang Time 초과, HangTime["+ (currentTime-w.getThreadHangCheckTimeMillis())+"]ms 기준치["+threadHangTime+"]");
        }
        // 쓰레드 수행 소요 시간 체크
        if(w.getThreadProcTimeMillis() > threadCycleTime){
            FATAL(w.getName() + " 최소 수행 소요 시간 초과, 수행시간["+w.getThreadProcTimeMillis()+"]ms 기준치["+threadCycleTime+"]ms");
        }
    }
    
    class AgentWorker extends Thread{
        
        MSG_FWXHEADER hdr = new MSG_FWXHEADER() ;
        
        VelaOpenAgent voa=null;
        
        Template tmplSession = null;
        Template tmplAgent = null;
        
        Map mapRootModel = new LinkedHashMap() ;
        RuntimeContext ctxRuntime = new RuntimeContext() ;
        Writer nullOut = new OutputStreamWriter(new DevNullOutput());
        Writer sysOut = new OutputStreamWriter(System.out);
        
        boolean isStop = false;
        
        int ipcBufNum = 0 ;
        int sendCount =0;
        int threadState=9; //0 = 데이터없음-휴식, 1 = 처리 중, 2 = 정상 처리 완료, 3 비정상 처리 완료, 9 = 준비, -1 = 수행불가(에러)  

        long threadHangCheckTimeMillis = 0;
        long threadProcTimeMillis = 0;
        
        byte[] dataQ=null;
        byte[] dataRslt = new byte[1024*10];
        
        Logger logger = Logger.getRootLogger();
        
        public AgentWorker(int ipcBufNum, VelaOpenAgent voa){
            this.ipcBufNum = ipcBufNum;
            this.voa = voa;
        }
        
        public void init(){
            
            threadHangCheckTimeMillis=System.currentTimeMillis();
            dataQ=new byte[rdr.getMsgMax()];
            
            VelaTemplates vtmpls = new VelaTemplates(configTag);
            
            if ( ! vtmpls.config()) {
                logger.error(this.getName()+": 템플릿 초기화에 실패했습니다.");
                return ;
            }
            
            tmplSession = vtmpls.getEventTemplate("session");
            if (tmplSession == null)  {
                logger.error(this.getName()+": "+vtmpls.getAbsolutePath("session") + "가 존재하지 않습니다.");
                return ;
            }
            tmplAgent = vtmpls.getEventTemplate("doagent");
            if (tmplAgent == null)  {
                logger.error(this.getName()+": "+vtmpls.getAbsolutePath("doagent") + "가 존재하지 않습니다.");
                return ;
            }
            
            try {
                
                ctxRuntime.getMapStack().clear() ;
                
                mapRootModel.clear();
                mapRootModel.put("m1", ctxRuntime);
                mapRootModel.put("TASKNAME", configTag);

                Writer wout = Logger.getRootLogger() != null && Logger.getRootLogger().getLevel().equals(Level.DEBUG) ? sysOut : nullOut ;
                tmplSession.process(mapRootModel,  wout);

                boolean rm = RuntimeContext.getIn(ctxRuntime.getMapStack(), "return", true);

                if ( !rm ) {
                    String errmsg = RuntimeContext.getIn(ctxRuntime.getMapStack(), "errorMsg", "no-message");
                    logger.error("["+this.getName()+"] 세션초기화  처리오류:" + errmsg + " r=" +rm);
                    return ;
                }

            } catch (TemplateException e) {
                logger.error("["+this.getName()+"] 세션초기화  처리중 스크립트 오류.",e);
                return ;
            } catch (IOException e) {
                logger.error("["+this.getName()+"] 세션초기화  처리중 IO오류.",e);
                return ;
            } catch (Exception e) {
                logger.error("["+this.getName()+"] 세션초기화  처리중 오류.",e);
                return ;
            } finally {
                ctxRuntime.clearStack();
            }
        }
        
        @Override
        public void run() {
            this.init();
            while(!isStop){    //isStop    Thread.currentThread().isInterrupted()
                try {
                    this.sleep(3000);
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                markTime();

                logger.debug("["+this.getName()+"] running");
                //해당  버퍼를 먼저 읽는다.
                boolean bResend =false ;
                if ( rdr.statBuf(ipcBufNum )==IPCFunctions.MF_FILL ) {
                    logger.info("["+this.getName()+"] Buffer is filled");
                    bResend =true ;
                }
                
                Arrays.fill(dataQ,(byte)0x20);
                int readDataSize = 330;//rdr.readBuf(dataQ,0,dataQ.length, (int)rdr.getTimeout(), ipcBufNum);
                
                // Thread State 0 = 데이터없음, 1 = 큐데이터 읽기완료, 2 = 파일큐 쓰기완료, 3 비정상 처리 완료, 9 = 준비, -1 = 수행불가(에러), -2 = 초기화 중 에러, -3 = 템플릿 에러
                if ( readDataSize == IPCFunctions.ESMS_TIMEOUT ) {
                    logger.debug("["+this.getName() + "] 큐에 데이터 없음-TOUT. " );
                    this.setThreadState(0);
                } else if ( readDataSize < 0 )    {
                    logger.error("["+this.getName()+"] 소스큐 읽기 오류. " + readDataSize+", Thread 동작 중지");
                    this.setThreadState(-1);
                } else if ( readDataSize == 0 ) {
                    logger.debug("["+this.getName()+"] "+rdr.getName() + " 큐에 데이터 없음. " );
                    this.setThreadState(0);
                } else if(readDataSize > 0){
                    logger.debug("["+this.getName()+"] 큐로부터 읽은 데이터 길이 ["+ readDataSize+"]");
                }
                
                //Thread 상태에 따른 처리 분기: [데이터 없음(0) = continue], [큐 에러(-1) = 모든 Worker Stop]
                if(getThreadState() == 0){
                    long t_wait = 0 ;
                    synchronized(this)
                    {
                        while ( t_wait < waitOnNoData ){
                            idle(t_wait);
                            try {
                                
                                this.wait(waitOnNoData < 1000 ? waitOnNoData : 1000);
                            } catch (InterruptedException e) {
                                logger.error("["+this.getName()+"] 대기중 Interrupt",e);
                                break;
                            }
                            t_wait += 1000 ;
                        }
                    }
                    continue;
                }else if(getThreadState() < 0){
                    voa.stop();
                    continue;
                }
                this.setThreadState(1);
                hdr.fromMessage(dataQ, 0);
                
                if ( bResend ) {
                    logger.trace( "["+this.getName()+"] RESEND " + hdr.getSeqLocal() );
                }
                
                try {
                    ByteSequence inputdata = new ByteSequence(dataQ,0,readDataSize);
                    ByteSequence ackdata = new ByteSequence(dataRslt,0,dataRslt.length);
                    
                    ctxRuntime.getMapStack().clear() ;
                    mapRootModel.clear();
                    mapRootModel.put("m1", ctxRuntime);
                    mapRootModel.put("TASKNAME",getConfigTag());
                    
                    mapRootModel.put("INPUTDATA", inputdata);
                    mapRootModel.put("target", ackdata);
                    
                    Writer wout = Logger.getRootLogger() != null && Logger.getRootLogger().getLevel().equals(Level.DEBUG) ? sysOut : nullOut ;
                    tmplAgent.process(mapRootModel,  wout);

//                    디폴트는 1, 성공 으로 간주.
                    int rslt = RuntimeContext.getIn(ctxRuntime.getMapStack(), "return", 0);                    
                    readDataSize = RuntimeContext.getIn(ctxRuntime.getMapStack(), "mappedSize", -2);
                    
//                    재처리 정의하여 재처리 결과일 시는 재처리하도록함
                    if ( rslt != 1 ) {
                        logger.info("["+this.getName()+"] 스크립트 오류로 인한 재처리["+rslt+"]");
                        continue;
                    }
                    
                    hdr.fromMessage(dataRslt, 0);
                    rslt = fq.write(hdr.getFTrnCd().equals(MSG_FWXHEADER.FWX_ACK)?ipcTypeNumAck:ipcTypeNumRslt, dataRslt, 0, readDataSize);
                    
                    if ( rslt < 0 )
                    {
                        logger.fatal("["+this.getName()+"] "+fq.getName()+ " 파일 큐 쓰기 오류[" +rslt+"]" );
                        voa.stop();
                        continue;
                    }
                    this.setThreadState(2);
                    rdr.readBufClear(ipcBufNum);
                    addSendCount(1);
                    
                    threadProcTimeMillis = markTime();
                    this.setThreadState(9);
                    
                } catch (TemplateException e) {
                    // TODO Auto-generated catch block
                    logger.error( "["+this.getName()+ "] 처리중(tmpl) 오류(스크립트).",e);
                    this.setThreadState(-3);
                    //return 0;
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    logger.error("["+this.getName()+ "] 처리중 오류(io).",e);
                    this.setThreadState(-1);
                    //return 0;
                } catch (Exception e) {
                    logger.error("["+this.getName()+ "] 처리중 오류(unknown).",e);
                    this.setThreadState(-1);
                    //return 0;
                } finally {
                    ctxRuntime.clearStack();
                }
                
            }
        }
        
        long markTime() {
            long t = System.currentTimeMillis() ;
            long itv = t-threadHangCheckTimeMillis ;
            threadHangCheckTimeMillis = t ;
            return itv ;
        }
        
        public int getThreadState() {
            return threadState;
        }

        public synchronized void setThreadState(int tState) {
            this.threadState = tState;
        }
        
        public int getSendCount() {
            return sendCount;
        }
        
        public synchronized void addSendCount(int sendCount) {
            this.sendCount += sendCount;
        }
        
        public synchronized void setSendCount(int sendCount) {
            this.sendCount = sendCount;
        }
        
        public long getThreadHangCheckTimeMillis() {
            return threadHangCheckTimeMillis;
        }
        
        public long getThreadProcTimeMillis() {
            return threadProcTimeMillis;
        }
        
        public boolean stopThread(){
            isStop=true;
            return isStop;
        }

    }
}
