package com.java.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class SimpleSendRCS {
	private static HttpURLConnection con;
	//private static String reqURL="https://agency-stg.hermes.kt.com/corp/v1.1/message";
	private static String reqURL="http://httpbin.org/post";
	private static String cid="1";
	
	public static String getNowDateTime() {
		   	Date today= new Date();
		    
		    SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		    String formattedDate = sdf.format(today);
		    return formattedDate;
	}
	
	public static JSONObject generateDummyJson() {
		JSONObject reqData=new JSONObject();
		JSONObject commonData=new JSONObject();
		JSONObject rcsData=new JSONObject();
		JSONObject bodyData=new JSONObject();
		JSONArray bntList=new JSONArray();
		
		String dateTime=getNowDateTime();
		
		// common Data Setting
		String msgId=dateTime.replaceAll("\\D", "");
		System.out.println("msgId="+msgId);
		commonData.put("msgId", msgId);
		commonData.put("userContact", "01026313590");
		commonData.put("scheduleType", "0");
		commonData.put("msgGroupId", msgId);
		commonData.put("msgServiceType", "rcs");
		
		//rcs Data Setting
		rcsData.put("chatbotId", "15776825");
		rcsData.put("agencyId", "ktrcsdev");//ktrcsdev, ktbizrcs
		rcsData.put("messagebaseId", "UBR.D93Lnzo1wL-GG000F");
		rcsData.put("serviceType", "RCSSMS");
		rcsData.put("expiryOption", 1);
		rcsData.put("header", "0");
		rcsData.put("footer", "");
		rcsData.put("copyAllowed", false);
		
		//Body Data Setting
		bodyData.put("description", "["+dateTime+"], 일반 RCSSMS 테스트");
		
		rcsData.put("body", bodyData);
		rcsData.put("buttons", bntList);
		
		reqData.put("common", commonData);
		reqData.put("rcs", rcsData);
		
		return reqData;
	}
	public static void main(String[] args) {
		String line=null;
		StringBuffer lines=new StringBuffer();
		OutputStream os=null;
		BufferedReader reader=null;
		System.setProperty("http.proxyHost", "127.0.0.1");
		System.setProperty("http.proxyPort", "8888");
		if(args.length<1) {
			System.out.println("Input AccessToken Params!!");
			System.exit(0);
		}
		String accessToken=args[0];
		System.out.println("Token["+accessToken+"]");
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
			JSONObject reqData=generateDummyJson();
			
			String reqInputData=reqData.toString();
			System.out.println("====Request Json Data====");
			System.out.println(reqInputData);
			System.out.println("=========================");
			os =con.getOutputStream();
			
			//byte[] payload = jsonInputData.getBytes("utf-8");
			byte[] payload = reqInputData.getBytes("utf-8");
			System.out.println("payload size:"+payload.length);
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
}
