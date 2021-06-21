package com.java.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.java.http.SimpleHttpRequest;

public class SimpleHttpClient {
	
	class Worker  extends SimpleHttpRequest implements Runnable{
		private String reqURL="http://15.165.55.184:8443/msgstatus";//http://15.165.55.184:24149/xweb/test.fsp http://localhost:8443/msgstatus 15.165.55.184
		private OutputStream os=null;
		private BufferedReader reader=null;
		private URL url =null;
		private long startTime =0;
		private long cnt =0;
		private long sleepTime =0;
		private int dummyCnt =5;
		private String tName="";
		
		public Worker(String name) {
			tName=name;
		}
		
		public Worker(String name, long sleepTime) {
			tName=name;
			this.sleepTime=sleepTime;
		}
		
		public Worker(String name, long sleepTime, int dummyCnt) {
			tName=name;
			this.sleepTime=sleepTime;
			this.dummyCnt =dummyCnt;
		}
		
		
		@Override
		public void run() {
			startTime=System.currentTimeMillis();
			String payload="";
			try {
				payload = this.dummyJsonArray().toString();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			while(true) {
				if(cnt==1000) {
					break;
				}
				try {
					this.url =new URL(this.reqURL);

					this.reqHeaderMap.put("Content-Type", "application/json; utf-8");
					this.reqHeaderMap.put("Content-Length", ""+payload.getBytes().length);
					this.reqHeaderMap.put("Accept", "application/json");
					
					String resposeData=this.doRequest(this.url, this.reqHeaderMap, payload, os, reader, "POST");
					cnt++;
					Thread.currentThread().sleep(sleepTime);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			System.out.println(tName+"소요시간:["+(System.currentTimeMillis()-startTime)+"], 처리건수:["+cnt+"]");
		}

		@Override
		public JSONObject generateDummyJson() throws ParseException {
			JSONObject _reqJsonData=new JSONObject();
			JSONObject _commonData=new JSONObject();
			JSONObject _rcsData=new JSONObject();
			JSONObject _bodyData=new JSONObject();
			JSONArray _bntList=new JSONArray();
			
			String dateTime=getNowDateTime();
			
			// common Data Setting
			String msgId=dateTime.replaceAll("\\D", "");
			_commonData.put("msgId", msgId);
			_commonData.put("userContact", "01098468940");//KT:01098468940 LG:01083118940 SKT:01090488940
			_commonData.put("scheduleType", "0");
			_commonData.put("msgGroupId", msgId);
			_commonData.put("msgServiceType", "rcs");
			
			//rcs Data Setting
			_rcsData.put("chatbotId", "15776825");
			_rcsData.put("agencyId", "ktrcsdev");//ktrcsdev, ktbizrcs
			_rcsData.put("messagebaseId", "UBR.D93Lnzo1wL-GG000F");
			_rcsData.put("serviceType", "RCSTMPL");
//			_rcsData.put("messagebaseId", "SS000000");
//			_rcsData.put("serviceType", "RCSSMS");
			_rcsData.put("expiryOption", 1);
			_rcsData.put("header", "0");
			_rcsData.put("footer", "");
			_rcsData.put("copyAllowed", false);
			
			//Body Data Setting
			_bodyData.put("description", "["+msgId+"], RCSTMPL");
			
			_rcsData.put("body", _bodyData);
			//_rcsData.put("buttons", _bntList);
			
			_reqJsonData.put("common", _commonData);
			_reqJsonData.put("rcs", _rcsData);
			return _reqJsonData;
		}

		public  JSONArray dummyJsonArray() throws ParseException{
			// TODO Auto-generated method stub
			JSONArray _listJson=new JSONArray();
			JSONObject _reqJsonData=new JSONObject();
			JSONObject errorData=new JSONObject();
			

			String dateTime=getNowDateTime("yyyy-MM-dd'T'HH:mm:ss.SSS");
			
			// common Data Setting
			String msgId=dateTime.replaceAll("\\D", "");
			_reqJsonData.put("status", "fail");
			_reqJsonData.put("msgId", "DBX_TEST."+msgId);
			_reqJsonData.put("serviceType", "RCSSMS");
			_reqJsonData.put("timestamp", dateTime);
			_reqJsonData.put("rcsId", "KT_ODINUE");
			_reqJsonData.put("userContact", "01098468940");
			_reqJsonData.put("mnoInfo", "KT");
			_reqJsonData.put("sentTime", dateTime);
			
			errorData.put("code", "71010");
			errorData.put("message", "MAAP-FE API Error\"");
			
			_reqJsonData.put("error", errorData);
			for(int i=0; i<dummyCnt; i++) {
				_listJson.add(_reqJsonData);
			}
			
			return _listJson;
		}
		public JSONObject generateDummyJson(String v1) throws ParseException {
			// TODO Auto-generated method stub
			JSONArray _listJson=new JSONArray();
			JSONObject _reqJsonData=new JSONObject();
			JSONObject errorData=new JSONObject();
			

			String dateTime=getNowDateTime("yyyy-MM-dd'T'HH:mm:ss.SSS");
			
			// common Data Setting
			String msgId=dateTime.replaceAll("\\D", "");
			_reqJsonData.put("status", "fail");
			_reqJsonData.put("msgId", "DBX_TEST."+msgId);
			_reqJsonData.put("serviceType", "RCSSMS");
			_reqJsonData.put("timestamp", dateTime);
			_reqJsonData.put("rcsId", "KT_ODINUE");
			_reqJsonData.put("userContact", "01098468940");
			_reqJsonData.put("mnoInfo", "KT");
			_reqJsonData.put("sentTime", dateTime);
			
			errorData.put("code", "71010");
			errorData.put("message", "MAAP-FE API Error\"");
			
			_reqJsonData.put("error", errorData);
			_listJson.add(_reqJsonData);

			return _reqJsonData;
		}
		
	}
	
	public static void main(String[] args) {
		int count=50;
		long sleepTime=0;
		int dummyCnt=100;
		if(args.length >0)
			count=Integer.valueOf(args[0]);
		if(args.length >1)
			sleepTime=Long.valueOf(args[1]);
		if(args.length >2)
			dummyCnt=Integer.valueOf(args[2]);
		System.out.println("Thread Cnt ["+count+", DummyData Cnt ["+dummyCnt+"], Request Interval ["+sleepTime+"]");
		SimpleHttpClient sc=new SimpleHttpClient();
		for(int i=0;i<count;i++) {
			Worker a=sc.new Worker(""+i+":Thead", sleepTime, dummyCnt);
			Thread t=new Thread(a);
			t.start();
		}
		
	}
}
