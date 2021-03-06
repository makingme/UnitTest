package com.java.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONObject;

public class SimplehttpClientEx {
	private static HttpURLConnection con;
	//private static String reqURL="https://agency-stg.hermes.kt.com";//STG KT RCS
	private static String reqURL="http://httpbin.org/post";//mirror 
	 
	public static void main(String[] args) {
		String line=null;
		StringBuffer lines=new StringBuffer();
		OutputStream os=null;
		BufferedReader reader=null;
		System.setProperty("http.proxyHost", "127.0.0.1");
		System.setProperty("http.proxyPort", "8888");
		try {
			//URL url = new URL("http://httpbin.org/post");
			URL url = new URL(reqURL);
			//http://validate.jsontest.com/
			//http://echo.jsontest.com/insert-key-here/insert-value-here/key/value
			//https://postman-echo.com/post
			//https://jsonplaceholder.typicode.com/posts
			//https://httpbin.org/post
			con =(HttpURLConnection) url.openConnection();
			con.setConnectTimeout(5000);
			con.setReadTimeout(5000);
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json; utf-8");
			con.setRequestProperty("Accetp", "application/json");
			con.setDoOutput(true);
			con.setDoInput(true);
			
			JSONObject reqToken=new JSONObject();
			JSONObject jOb=new JSONObject();
			
			jOb.put("name", "Upendra");
			jOb.put("job", "Programmer");
			
			reqToken.put("rcsId", "KT_ODINUE");//발급아이디
			reqToken.put("rcsSecret", "$2a$10$dSh7n0SIvYebR57CgyGomupQX.V.PPGwOodIZBqxzbgQ14iiGZwrK");//발급키
			reqToken.put("grantType", "clientCredentail");//고정값
			
			String jsonInputData = jOb.toString();
			String reqInputData=reqToken.toString();
			os =con.getOutputStream();
			
			//byte[] payload = jsonInputData.getBytes("utf-8");
			byte[] payload = reqInputData.getBytes("utf-8");
			os.write(payload,0,payload.length);
			
			int code=con.getResponseCode();
			
			System.out.println("code: "+code);
			
			reader= new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));
			
			while((line=reader.readLine())!=null) {
				lines.append(line.trim());
				lines.append(System.lineSeparator());
			}
			
			System.out.println(lines.toString());
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(os !=null) {
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(reader !=null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
}
