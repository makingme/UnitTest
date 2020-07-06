/**
 * 
 */
package com.java.http;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.TrustManagerFactory;

import com.sun.net.httpserver.*;

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
		server.createContext("/msgstatus", new SimpleHandler());
		server.setExecutor(null);
		server.start();

	}
	
    static class SimpleHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
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
