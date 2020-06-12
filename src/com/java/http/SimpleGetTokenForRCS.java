package com.java.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONObject;

public class SimpleGetTokenForRCS {
	private static HttpURLConnection con;
	private static String reqURL="https://agency-stg.hermes.kt.com/corp/v1.1/token"; 
	//private static String reqURL="http://httpbin.org/post";
	private static String rcsId="KT_ODINUE";
	private static String rcsSecret="$2a$10$dSh7n0SIvYebR57CgyGomupQX.V.PPGwOodIZBqxzbgQ14iiGZwrK";
	private static String grantType="clientCredentials";
	public static void main(String[] args) {
		String line=null;
		StringBuffer lines=new StringBuffer();
		OutputStream os=null;
		BufferedReader reader=null;
		System.setProperty("http.proxyHost", "127.0.0.1");
		System.setProperty("http.proxyPort", "8888");
		try {
			URL url = new URL(reqURL);

			con =(HttpURLConnection) url.openConnection();
			con.setConnectTimeout(5000);
			con.setReadTimeout(5000);
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json; utf-8");
			con.setRequestProperty("Accept", "application/json, text/plain, */*");
			con.setRequestProperty("Authorization", "Basic ClientId ClientSecret");
			con.setDoOutput(true);
			con.setDoInput(true);
			
			JSONObject reqData=new JSONObject();
			reqData.put("rcsId", rcsId);
			reqData.put("rcsSecret", rcsSecret);
			reqData.put("grantType", grantType);
			//String reqInputData="grant_type="+URLEncoder.encode(grant_type)+"&username="+URLEncoder.encode(username)+"&password="+URLEncoder.encode(password);
			String reqInputData=reqData.toString();
			System.out.println("Body Data(Json)="+reqInputData);
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
