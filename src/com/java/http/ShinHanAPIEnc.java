/**
 * 
 */
package com.java.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**

  * @FileName : ShinHanAPIEnc.java

  * @Project : UnitTest

  * @Date : 2020. 7. 13. 

  * @작성자 : kibumkim

  * @변경이력 :

  * @프로그램 설명 :

  */
public class ShinHanAPIEnc extends SimpleHttpRequest{
	
	private final String SKEY="smms_life_4321";
	private String reqSndURL="http://api.shinhan.rcsmaap.co.kr:8091/api/enc/enc";
	
	private OutputStream os=null;
	private BufferedReader reader=null;
	private URL url =null;
	
	public static void main(String[] args) {
		ShinHanAPIEnc sender=new ShinHanAPIEnc();		
		
		System.setProperty("http.proxyHost", "127.0.0.1");
		System.setProperty("http.proxyPort", "8888");
		try {
			//암호화
			sender.url =new URL(sender.reqSndURL);
			String reqBodyData= "dddddd";
			//JWT Token header
			sender.reqBodyMap.put("alg", "HS256");
			String header=sender.getSimpleJsonReqData(sender.reqBodyMap).toString();
			
			sender.reqBodyMap.put("sIp", "*.*.*.*");
			sender.reqBodyMap.put("iss", "smms.shinhands.com");
			sender.reqBodyMap.put("exp", 1595642443*2);
			sender.reqBodyMap.put("typ", "JWT");
			String payload=sender.getSimpleJsonReqData(sender.reqBodyMap).toString();
			String token=sender.generateJWTToken(sender.base64UrlEncode(header, "UTF-8"), sender.base64UrlEncode(payload, "UTF-8"), "HmacSHA256", sender.SKEY, "UTF-8");
			
			sender.reqHeaderMap.put("Accept", "*/*");
			sender.reqHeaderMap.put("Content-Type", "application/json");
			//sender.reqHeaderMap.put("Dstr_token", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZ2VudDEiLCJhZ2VudElkIjoiYWdlbnQxIiwiaXNzIjoic21tcy5zaGluaGFuZHMuY29tIiwidHlwIjoiSldUIiwic0lwIjoiKi4qLiouKiIsImV4cCI6MTU5NTY0MjQ0MywiaWF0IjoxNTk0MTcxMjE0fQ.ZRAhE35Jc2CE4F4zXKhSauATO5V6GXAv99nQSmvMMUE");
			sender.reqHeaderMap.put("Authorization", "Bearer "+token);
			
			
			//
			//Origin: http://api.shinhan.rcsmaap.co.kr:8091
			//Referer: http://api.shinhan.rcsmaap.co.kr:8091/swagger-ui.html
			String resposeData=sender.doRequest(sender.url, sender.reqHeaderMap, reqBodyData, sender.os, sender.reader, "POST");
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/* (non-Javadoc)
	 * @see com.java.http.SimpleHttpRequest#generateDummyJson()
	 */
	@Override
	public JSONObject generateDummyJson() throws ParseException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.java.http.SimpleHttpRequest#generateDummyJson(java.lang.String)
	 */
	@Override
	public JSONObject generateDummyJson(String v1) throws ParseException {
		// TODO Auto-generated method stub
		return null;
	}
	

}
