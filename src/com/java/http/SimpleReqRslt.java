package com.java.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class SimpleReqRslt {
	private static HttpURLConnection con;
	private static String reqURL="http://222.111.214.31:40001/api/mns/rsp";
	//private static String reqURL="http://httpbin.org/post";
	private static String cid="1";
	private static String accessToken="46fa01ad-926c-4cea-a878-3dd8a93717ff";
	
	public static void main(String[] args) {
		String line=null;
		StringBuffer lines=new StringBuffer();
		OutputStream os=null;
		BufferedReader reader=null;
		System.setProperty("http.proxyHost", "127.0.0.1");
		System.setProperty("http.proxyPort", "8888");
		if(args.length<1) {
			System.out.println("Input msg key Params!!");
			System.exit(0);
		}
		String msgkey=args[0];
		try {
			URL url = new URL(reqURL);

			con =(HttpURLConnection) url.openConnection();
			con.setConnectTimeout(5000);
			con.setReadTimeout(5000);
			con.setRequestProperty("Authorization", "Bearer "+accessToken);
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json; utf-8");
			con.setRequestProperty("Accept", "*/*");
			con.setDoOutput(true);
			con.setDoInput(true);
			JSONObject reqData=generateDummyJson(msgkey);
			
			String reqInputData=reqData.toString();
			System.out.println("====Request Json Data====");
			System.out.println(reqInputData);
			System.out.println("=========================");
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
			
			System.out.println("response["+lines.toString()+"]");
			
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
	
	public static String getNowDateTime() {
	   	Date today= new Date();
	    
	    SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	    String formattedDate = sdf.format(today);
	    return formattedDate;
	}
	public static JSONObject generateDummyJson(String key) {
		JSONObject reqData=new JSONObject();

		JSONArray keyList=new JSONArray();
			
		String dateTime=getNowDateTime();
		
		String msgId=dateTime.replaceAll("\\D", "");
		System.out.println("msgId="+msgId);
		
		reqData.put("service_cd", "99999");//서비스코드 
		reqData.put("service_key", "aWVSSXwh2z");//서비스 코드 인증키
		reqData.put("api_ver", "v2");//
			
		keyList.add(key);
		
		reqData.put("src_keys", keyList);
		
		
		return reqData;
	}
}
