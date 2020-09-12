package com.java.server;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.TrustManagerFactory;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.odinues.m1vela.ftl.ByteSequence;
import com.odinues.m1vela.ftl.DevNullOutput;
import com.odinues.m1vela.ftl.RuntimeContext;
import com.odinues.m1vela.ftl.VelaTemplates;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsParameters;
import com.sun.net.httpserver.HttpsServer;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public class SimpleHttpsServer {
	public static void main(String[] args) throws Exception {
		HttpsServer server=HttpsServer.create(new InetSocketAddress(8443), 0);
		SSLContext sslContext = SSLContext.getInstance("TSL");
		//keytool -genkeypair -keyalg RSA -alias selfsigned -keystore testkey.jks -storepass password -validity 360 -keysize 2048
		// init keystore
		char[] pwd ="kpwd".toCharArray();
		KeyStore ks= KeyStore.getInstance("JKS");
		FileInputStream fis=new FileInputStream("testkey.jks");
		ks.load(fis, pwd);
		
		// setup key managerfactory
		KeyManagerFactory kmf= KeyManagerFactory.getInstance("SunX509");
		kmf.init(ks, pwd);
		
		// setup trust managerfactory
		TrustManagerFactory tmf= TrustManagerFactory.getInstance("SunX509");
		tmf.init(ks);
		
		sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
		server.setHttpsConfigurator(new HttpsConfigurator(sslContext) {
			@Override
			public void configure(HttpsParameters params) {
				try {
					SSLContext context = getSSLContext();
					SSLEngine engine = context.createSSLEngine();
					params.setNeedClientAuth(false);
					params.setCipherSuites(engine.getEnabledCipherSuites());
					params.setProtocols(engine.getEnabledProtocols());
					
					SSLParameters sslParams = context.getSupportedSSLParameters();
					params.setSSLParameters(sslParams);
				} catch (Exception e) {
					// fail to create https port
					e.printStackTrace();
				}
			}

		});
		server.createContext("/msgstatus", new SimpleHandler(args[0]));
		server.setExecutor(new ThreadPoolExecutor(5, 8, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100)));
		server.start();

	}
	
    static class SimpleHandler implements HttpHandler {
    	
        Template tmplAgent = null;
        static String configTag="";
        byte[] dataRslt = new byte[1024*10];
        
        Map mapRootModel = new LinkedHashMap() ;
        RuntimeContext ctxRuntime = new RuntimeContext() ;
        Writer nullOut = new OutputStreamWriter(new DevNullOutput());
        Writer sysOut = new OutputStreamWriter(System.out);
        
       
		public SimpleHandler(String tag) {
			configTag=tag;
			
			 VelaTemplates vtmpls = new VelaTemplates(configTag);
             if ( ! vtmpls.config()) {
                 System.out.println("템플릿 초기화 실패");
             }
             tmplAgent = vtmpls.getEventTemplate("doagent");
             if (tmplAgent == null)  {
                System.out.println("템플릿 없음");
             }
		}
        
        @Override
        public void handle(HttpExchange t) throws IOException {
        	System.out.println("Handler Called");
            String response = "OK";
            t.sendResponseHeaders(200, response.getBytes().length);
            
            InputStream in= t.getRequestBody();
            
            byte[] bodybytes = new byte[1024*10];
            int tot = 0 ;
            int rbytes = 0 ;
            
            while( (rbytes=in.read(bodybytes, tot, 1) ) >0 ) {
            	tot+=rbytes ;           	
            }
            ByteSequence inputdata = new ByteSequence(bodybytes,0,tot);
            ByteSequence ackdata = new ByteSequence(dataRslt,0,dataRslt.length);
            ctxRuntime.getMapStack().clear() ;
            mapRootModel.clear();
            mapRootModel.put("m1", ctxRuntime);
            mapRootModel.put("TASKNAME", configTag);
            mapRootModel.put("INPUTDATA", inputdata);
            mapRootModel.put("target", ackdata);
            
            Writer wout = Logger.getRootLogger() != null && Logger.getRootLogger().getLevel().equals(Level.DEBUG) ? sysOut : nullOut ;
            try {
				tmplAgent.process(mapRootModel,  wout);
			} catch (TemplateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            in.close();
            System.out.println("["+getNowDateTime()+"]");
            String requestBody= new String(bodybytes,0,tot);
            System.out.println("reqBody["+requestBody+"]");
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
            System.out.println("response done.");
            System.out.println("========================");
        }
    }
    
	public static String getNowDateTime() {
	   	Date today= new Date();
	    SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String formattedDate = sdf.format(today);
	    return formattedDate;
	}
}
