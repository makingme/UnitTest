/**
 * 
 */
package com.java.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**

  * @FileName : PostCheckRslt.java

  * @Project : UnitTest

  * @Date : 2020. 6. 18. 

  * @작성자 : kibumkim

  * @변경이력 :

  * @프로그램 설명 : 모바일 통지 전송 결과 조회

  */
public class PostCheckRslt extends SimpleHttpRequest {
	
	private String reqChkURL="http://222.111.214.31:40001/api/mns/rsp";
	
	private static String[] phones= {"01056543970","01043206010","01026313590"};
	
	private String accessToken="aa413f01-d523-4c99-9198-5220a881947f";
	
	private OutputStream os=null;
	private BufferedReader reader=null;
	private URL url =null;
	
	public static void main(String[] args) {
		
		if(args.length<1) {
			System.out.println("Input src_key Params!!");
			System.exit(0);
		}
		String msgkey=args[0];
		
		PostCheckRslt sender=new PostCheckRslt();
		
		try {
			
			//결과 조회
			sender.url =new URL(sender.reqChkURL);
			
			String payload= sender.generateDummyJson(msgkey).toString();
			sender.reqHeaderMap.put("Content-Type", "application/json; utf-8");
			sender.reqHeaderMap.put("Content-Length", ""+payload.getBytes().length);
			sender.reqHeaderMap.put("Accept", "*/*");
			sender.reqHeaderMap.put("Authorization", "Bearer "+sender.accessToken);

			sender.doRequest(sender.url, sender.reqHeaderMap, payload, sender.os, sender.reader, "POST");
			
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
	public JSONObject generateDummyJson() {
		JSONObject _reqJsonData=new JSONObject();
		JSONArray _keyList=new JSONArray();
			
		String dateTime=getNowDateTime();
		String msgId=dateTime.replaceAll("\\D", "");
		
		_reqJsonData.put("service_cd", "APTST");//서비스코드 
		_reqJsonData.put("service_key", "ybik2D53JR");//서비스 코드 인증키
		_reqJsonData.put("api_ver", "v2");//
		_keyList.add("MsgKey");
		_reqJsonData.put("src_keys", _keyList);
		
		return _reqJsonData;
	}

	/* (non-Javadoc)
	 * @see com.java.http.SimpleHttpRequest#generateDummyJson(java.lang.String)
	 */
	@Override
	public JSONObject generateDummyJson(String v1) {
		JSONObject _reqJsonData=this.generateDummyJson();
		JSONArray _keyList=(JSONArray)_reqJsonData.get("src_keys");
		_keyList.clear();
		_keyList.add(v1);
		_reqJsonData.put("src_keys", _keyList);
		return _reqJsonData;
	}

}
