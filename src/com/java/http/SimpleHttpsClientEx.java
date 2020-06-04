package com.java.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

public class SimpleHttpsClientEx {
	private static HttpsURLConnection connection;
	public static void main(String[] args) {
		BufferedReader reader;
		String line;
		StringBuffer lines = new StringBuffer();
		
		try {
			URL url= new URL("https://reqres.in/api/users");
			connection = (HttpsURLConnection) url.openConnection();
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json utf-8");
			connection.setRequestProperty("Accept", "application/json");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setSSLSocketFactory((SSLSocketFactory) SSLSocketFactory.getDefault());
			String jsonInputString = "{\"name\": \"Upendra\", \"job\": \"Programmer\"}";
			
			Map<String,List<String>> httpProp=connection.getRequestProperties();
			
			Set<String> keyset=httpProp.keySet();
			Iterator<String> keyiter= keyset.iterator();
			while(keyiter.hasNext()) {
				System.out.println(keyiter.next());
			}
			System.out.println("==========================");
			
			int status =connection.getResponseCode();
			
			System.out.println(connection.getContentType());
			System.out.println(connection.getResponseMessage()); 
			
			System.out.println(status);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
