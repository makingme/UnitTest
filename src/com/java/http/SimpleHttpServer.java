/**
 * 
 */
package com.java.http;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.TrustManagerFactory;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.odinues.m1vela.ftl.ByteSequence;
import com.odinues.m1vela.ftl.DevNullOutput;
import com.odinues.m1vela.ftl.RuntimeContext;
import com.odinues.m1vela.ftl.VelaTemplates;
import com.sun.net.httpserver.*;

import freemarker.template.Template;
import freemarker.template.TemplateException;

/**

  * @FileName : SimpleHttpServer.java

  * @Project : UnitTest

  * @Date : 2020. 6. 24. 

  * @작성자 : kibumkim

  * @변경이력 :

  * @프로그램 설명 :

  */
public class SimpleHttpServer {
	public static void main(String[] args) throws Exception {
		HttpServer server=HttpServer.create(new InetSocketAddress(443), 0);
		server.createContext("/msgstatus", new SimpleHandler(args[0]));
		//server.createContext("/msgstatus", new SimpleHandler());
		server.setExecutor(null);
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
