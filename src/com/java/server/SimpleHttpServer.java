package com.java.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.odinues.m1vela.ftl.ByteSequence;
import com.odinues.m1vela.ftl.DevNullOutput;
import com.odinues.m1vela.ftl.RuntimeContext;
import com.odinues.m1vela.ftl.VelaTemplates;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public class SimpleHttpServer {
	public static void main(String[] args) throws Exception {
		HttpServer server=HttpServer.create(new InetSocketAddress(443), 0);
		server.createContext("/msgstatus", new SimpleHandler("test"));
		//server.createContext("/msgstatus", new SimpleHandler());
		server.setExecutor(new ThreadPoolExecutor(5, 8, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100)));
		server.start();

	}
	
    static class SimpleHandler implements HttpHandler {
    	
        
        static String configTag="";
        byte[] dataRslt = new byte[1024*10];
        
        
        
       
		public SimpleHandler(String tag) {
			configTag=tag;
		}
        
        @Override
        public void handle(HttpExchange t) throws IOException {
        	System.out.println("["+Thread.currentThread().getId()+"] Handler Called");
            String response = "OK";
            t.sendResponseHeaders(200, response.getBytes().length);
            
            InputStream in= t.getRequestBody();
            
            byte[] bodybytes = new byte[1024*10];
            int tot = 0 ;
            int rbytes = 0 ;
            
            while( (rbytes=in.read(bodybytes, tot, 1) ) >0 ) {
            	tot+=rbytes ;           	
            }
            
            in.close();
            //System.out.println("["+getNowDateTime()+"]");
            String requestBody= new String(bodybytes,0,tot);
            //System.out.println("reqBody["+requestBody+"]");
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
            //System.out.println("response done.");
            System.out.println(System.currentTimeMillis());
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
