package com.java.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

public class SimpleSendTok {
	private static HttpURLConnection con;
	private static String reqURL="http://13.209.176.120/v1/message/send";
	//private static String reqURL="http://httpbin.org/post";
	private static String cid="1";
	
	private static String[] keys= {
			"message_type",		"sender_key",	"phone_number",	"user_key",
			"template_code",	"message",	"sms_message",	"title",
			"ad_flag",	"wide",	"sms_type",	"sender_no",
			"etc1",	"etc2",	"etc3",	"etc4",
			"etc5",	"etc6",	"etc7",	"etc8",
			"etc9",	"etc10", "tax_cd1",	"cid",
			"tax_cd2",	"reservation_date",	"button",	"image",
			"content_group_id"
	};
	public static String getNowDateTime() {
	   	Date today= new Date();
	    
	    SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	    String formattedDate = sdf.format(today);
	    return formattedDate;
	}
	public static JSONObject generateDummyJson() {
		JSONObject reqData=new JSONObject();
		String dateTime=getNowDateTime();
		reqData.put("message_type", "AT");
		reqData.put("sender_key", "81ef72d77c4c6c66962b79239f02a58a1202f924");
		reqData.put("phone_number", "01026313590");
		reqData.put("user_key", "");
		reqData.put("template_code", "test_kep_template_001");
		reqData.put("message", "["+dateTime+"], message");//1000 byte
		reqData.put("sms_message", "");//2000 byte
		reqData.put("title", dateTime);
		reqData.put("ad_flag", "Y");
		reqData.put("wide", "N");
		reqData.put("sms_type", "SM");
		reqData.put("sender_no", "01026313590");
		reqData.put("etc1", "김기범");
		reqData.put("etc2", "1개월 무료");
		reqData.put("etc3", "이모티콘 할인권 증정");
		for(int i=4;i<=10;i++) {
			reqData.put("etc"+i, "");
		}
		reqData.put("tax_cd1", "");
		reqData.put("cid", dateTime.replaceAll("\\D", ""));
		System.out.println("CID="+dateTime.replaceAll("\\D", ""));
		reqData.put("tax_cd2", "");
		reqData.put("reservation_date", "");
		reqData.put("button", "");
		reqData.put("image", "");
		reqData.put("content_group_id", "");
		
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
		try {
			URL url = new URL(reqURL);

			con =(HttpURLConnection) url.openConnection();
			con.setConnectTimeout(5000);
			con.setReadTimeout(5000);
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json; utf-8");
			con.setRequestProperty("Accept", "*/*");
			con.setRequestProperty("Authorization", accessToken);
			con.setDoOutput(true);
			con.setDoInput(true);
			JSONObject reqData=generateDummyJson();
			
			String reqInputData=reqData.toString();
			System.out.println("====Request Json Data====");
			System.out.println(reqInputData);
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
	
	public JSONObject generateDummyJson(String rcvNo, String templeCode, String message, String title, String sndNo) {
		JSONObject reqData=new JSONObject();
		reqData.put("message_type", "AT");
		reqData.put("sender_key", "4e8e1a2ebface155cd18cbda5ee6d65df9c56d8e");
		reqData.put("phone_number", "01026313590");
		reqData.put("user_key", "");
		reqData.put("template_code", "TEST");
		reqData.put("message", "["+getNowDateTime()+"], message");//1000 byte
		reqData.put("sms_message", "");//2000 byte
		reqData.put("title", getNowDateTime());
		reqData.put("ad_flag", "Y");
		reqData.put("wide", "N");
		reqData.put("sms_type", "SM");
		reqData.put("sender_no", "01026313590");
		for(int i=1;i<=10;i++) {
			reqData.put("etc"+i, "");
		}
		reqData.put("tax_cd1", "");
		reqData.put("cid", cid);
		reqData.put("tax_cd2", "");
		reqData.put("reservation_date", "");
		reqData.put("button", "");
		reqData.put("image", "");
		reqData.put("content_group_id", "");
		
		return reqData;
	}
}
