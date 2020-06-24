/**
 * 
 */
package com.java.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;

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
	public static void main(String[] args) throws IOException {
		HttpServer server=HttpServer.create(new InetSocketAddress(443), 0);
		server.createContext("/msgsatus", new SimpleHandler());
		server.setExecutor(null);
		server.start();
	}
	
    static class SimpleHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = "This is the response";
            t.sendResponseHeaders(200, response.length());
            InputStream in= t.getRequestBody();
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
