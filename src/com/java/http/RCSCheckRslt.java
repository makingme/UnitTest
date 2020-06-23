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
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**

  * @FileName : RCSCheckRslt.java

  * @Project : UnitTest

  * @Date : 2020. 6. 18. 

  * @작성자 : kibumkim

  * @변경이력 :

  * @프로그램 설명 : 모바일 통지 전송 결과 조회

  */
public class RCSCheckRslt extends SimpleHttpRequest{

	private String reqTokenURL="https://agency-stg.hermes.kt.com/corp/v1/token";
	private String reqChkURL="https://agency-stg.hermes.kt.com/corp/v1/querymsgstatus";
	
	private static String[] phones= {"01098468940","01083118940","01090488940","01026313590"};
	
	private OutputStream os=null;
	private BufferedReader reader=null;
	private URL url =null;
	
	public static void main(String[] args) {

		if(args.length<1) {
			System.out.println("Input MsgId Params!!");
			System.exit(0);
		}
		String msgId=args[0];
		RCSCheckRslt sender=new RCSCheckRslt();
		try {
			//토큰 요청
			sender.url =new URL(sender.reqTokenURL);
			
			sender.reqHeaderMap.put("Content-Type", "application/json; utf-8");
			sender.reqHeaderMap.put("Accept", "application/json, text/plain, */*");
			
			sender.reqBodyMap.put("rcsId", "KT_ODINUE");
			sender.reqBodyMap.put("rcsSecret", "$2a$10$dSh7n0SIvYebR57CgyGomupQX.V.PPGwOodIZBqxzbgQ14iiGZwrK");
			sender.reqBodyMap.put("grantType", "clientCredentials");
			
			JSONObject reqJsonData= sender.getSimpleJsonReqData(sender.reqBodyMap);
			
			String resposeData=sender.doRequest(sender.url, sender.reqHeaderMap, reqJsonData.toString(), sender.os, sender.reader, "POST");
			
			JSONObject resJsonData=(JSONObject)new JSONParser().parse(resposeData);
			resJsonData=(JSONObject)resJsonData.get("data");
			resJsonData=(JSONObject)resJsonData.get("tokenInfo");
			
			String accessToken=(String)resJsonData.get("accessToken");
			
			System.out.println("AccessToken:"+accessToken);
			
			//메시지 결과 조회
			sender.url =new URL(sender.reqChkURL);
			String payload= sender.generateDummyJson(msgId).toString();
			
			sender.reqHeaderMap.put("Content-Type", "application/json; utf-8");
			sender.reqHeaderMap.put("Content-Length", ""+payload.getBytes().length);
			sender.reqHeaderMap.put("Accept", "application/json");
			sender.reqHeaderMap.put("Authorization", "Bearer "+accessToken);
			
			resposeData=sender.doRequest(sender.url, sender.reqHeaderMap, payload, sender.os, sender.reader, "POST");
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}	
	

	@Override
	public JSONObject generateDummyJson() {
		JSONObject _reqJsonData=new JSONObject();
		_reqJsonData.put("queryType", "msgId");
		_reqJsonData.put("msgId", "");
		
		return _reqJsonData;
	}


	@Override
	public JSONObject generateDummyJson(String v1) {
		JSONObject _reqJsonData=this.generateDummyJson();
		_reqJsonData.put("msgId", v1);
		return _reqJsonData;
	}

}
