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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SendRCS {
	public static void main(String[] args) {
		String reqTokenURL="https://agency-stg.hermes.kt.com/corp/v1/token";
		//String reqTokenURL="http://httpbin.org/post";
		String reqSndURL="https://agency-stg.hermes.kt.com/corp/v1/message";
		Map<String,String> reqHeaderMap=new HashMap<String,String>(10);
		
		OutputStream os=null;
		BufferedReader reader=null;
		URL url =null;

		JSONObject resData=null;
		System.setProperty("http.proxyHost", "127.0.0.1");
		System.setProperty("http.proxyPort", "8888");
		try {
			//request Tokent info
			url = new URL(reqTokenURL);
			reqHeaderMap.put("Content-Type", "application/json; utf-8");
			reqHeaderMap.put("Accept", "application/json, text/plain, */*");
			String s_resData=postRequest(url, reqHeaderMap, getReqTokenJson().toString(), os, reader);
			
			resData=(JSONObject)new JSONParser().parse(s_resData);
			resData=(JSONObject)resData.get("data");
			resData=(JSONObject)resData.get("tokenInfo");
			
			String accessToken=(String)resData.get("accessToken");
			System.out.println("AccessToken:"+accessToken);
			
			//request Message Send
			url = new URL(reqSndURL);
			String s_payload=generateDummyJson().toString();
			reqHeaderMap.put("Content-Type", "application/json; utf-8");
			reqHeaderMap.put("Content-Length", ""+s_payload.getBytes().length);
			reqHeaderMap.put("Accept", "application/json");
			reqHeaderMap.put("Authorization", "Bearer "+accessToken);
			
			s_resData=postRequest(url, reqHeaderMap, s_payload, os, reader);
			
			System.out.println("response["+s_resData+"]");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
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
	
	public static String postRequest(URL url, Map<String, String> header ,String req, OutputStream os, BufferedReader reader) throws IOException{
		HttpURLConnection con =(HttpURLConnection) url.openConnection();
		con.setConnectTimeout(5000);
		con.setReadTimeout(5000);
		con.setRequestMethod("POST");
		setRequestProperty(con, header);
		con.setDoOutput(true);
		con.setDoInput(true);
		
		System.out.println("Request Body Data(Json)="+ req);
		
		os =con.getOutputStream();
		
		byte[] payload = req.getBytes("utf-8");
		os.write(payload,0,payload.length);
		
		int code=con.getResponseCode();
		
		System.out.println("res code: "+code);
		
		reader= new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));
		
		String line=null;
		StringBuffer lines=new StringBuffer();
		while((line=reader.readLine())!=null) {
			lines.append(line.trim());
			lines.append(System.lineSeparator());
		}
		
		os.close();
		reader.close();
		con.disconnect();
		
		return lines.toString();
	}
	
	public static void setRequestProperty(HttpURLConnection con, Map<String, String> header) {
		Set<String> keySet=header.keySet();
		Iterator<String> keyItor= keySet.iterator();
		while(keyItor.hasNext()) {
			String key=keyItor.next();
			String value=header.get(key);
			con.setRequestProperty(key, value);
			System.out.println("Request Setting, "+key+": "+value);
		}
		
		header.clear();
	}
	public static String getNowDateTime() {
	   	Date today= new Date();
	    
	    SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	    String formattedDate = sdf.format(today);
	    return formattedDate;
	}
	
	
	public static JSONObject getReqTokenJson() {
		//Essential data for request Token 
		JSONObject reqData=new JSONObject();
		String rcsId="KT_ODINUE";
		String rcsSecret="$2a$10$dSh7n0SIvYebR57CgyGomupQX.V.PPGwOodIZBqxzbgQ14iiGZwrK";
		String grantType="clientCredentials";
		
		reqData.put("rcsId", rcsId);
		reqData.put("rcsSecret", rcsSecret);
		reqData.put("grantType", grantType);
		return reqData;
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
		commonData.put("userContact", "01026313590");//KT:01098468940 LG:01083118940 SKT:01090488940
		commonData.put("scheduleType", "0");
		commonData.put("msgGroupId", msgId);
		commonData.put("msgServiceType", "rcs");
		
		//rcs Data Setting
		rcsData.put("chatbotId", "15776825");
		rcsData.put("agencyId", "ktrcsdev");//ktrcsdev, ktbizrcs
		rcsData.put("messagebaseId", "UBR.D93Lnzo1wL-GG000F");
		rcsData.put("serviceType", "RCSTMPL");
		//rcsData.put("messagebaseId", "SS000000");
		//rcsData.put("serviceType", "RCSSMS");
		rcsData.put("expiryOption", 1);
		rcsData.put("header", "0");
		rcsData.put("footer", "");
		rcsData.put("copyAllowed", false);
		
		//Body Data Setting
		bodyData.put("description", "["+dateTime+"], 프리 RCSTMPL 테스트");
		
		rcsData.put("body", bodyData);
		
		
		
		rcsData.put("buttons", bntList);
		
		reqData.put("common", commonData);
		reqData.put("rcs", rcsData);
		
		return reqData;
	}
}
