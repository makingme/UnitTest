/**
 * 
 */
package com.java.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**

  * @FileName : TalkCheckRslt.java

  * @Project : UnitTest

  * @Date : 2020. 6. 18. 

  * @�ۼ��� : kibumkim

  * @�����̷� :

  * @���α׷� ���� : �˸��� ���� ��� ��ȸ

  */
public class TalkCheckRslt extends SimpleHttpRequest{
	
	private String reqTokenURL="http://13.209.176.120/v1/oauth/token";
	private String reqChkURL="http://13.209.176.120/v1/message/detail/";
	
	private String authorization="APITEST0000 APITEST0000";
	
	private OutputStream os=null;
	private BufferedReader reader=null;
	private URL url =null;
	
	public static void main(String[] args) {
		if(args.length<1) {
			System.out.println("Input UID!!");
			System.exit(0);
		}
		String uId=args[0];
		TalkCheckRslt sender=new TalkCheckRslt();
		
		try {
			//��ū ���
			sender.url =new URL(sender.reqTokenURL);
			
			sender.reqHeaderMap.put("Content-Type", "application/x-www-form-urlencoded; utf-8");
			sender.reqHeaderMap.put("Accept", "application/json, text/plain, */*");
			sender.reqHeaderMap.put("Authorization", "Basic "+sender.authorization);
			
			sender.reqBodyMap.put("grant_type", "password");
			sender.reqBodyMap.put("username", "api-test@kakaocorp.com");
			sender.reqBodyMap.put("password", "APITEST0000");
			
			String reqFormData= sender.getSimpleFormReqData(sender.reqBodyMap, true);
			
			String resposeData=sender.doRequest(sender.url, sender.reqHeaderMap, reqFormData, sender.os, sender.reader, "POST");
			
			JSONObject resJsonData=(JSONObject)new JSONParser().parse(resposeData);			
			String accessToken=(String)resJsonData.get("access_token");
			
			System.out.println("AccessToken:"+accessToken);
			
			//�޽��� ���� ��� ��ȸ
			sender.url =new URL(sender.reqChkURL+uId);
			String payload= "";
			
			sender.reqHeaderMap.put("Content-Type", "application/json; utf-8");
			sender.reqHeaderMap.put("Content-Length", ""+payload.getBytes().length);
			sender.reqHeaderMap.put("Accept", "*/*");
			sender.reqHeaderMap.put("Authorization", "Bearer "+accessToken);
			
			resposeData=sender.doRequest(sender.url, sender.reqHeaderMap, payload, sender.os, sender.reader, "GET");
			
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
		return null;
	}

	
	@Override
	public JSONObject generateDummyJson(String v1) {
	
		return null;
	}

}
