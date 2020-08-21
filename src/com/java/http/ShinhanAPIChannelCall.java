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

  * @FileName : ShinhanAPIChannelCall.java

  * @Project : UnitTest

  * @Date : 2020. 7. 29. 

  * @작성자 : kibumkim

  * @변경이력 :

  * @프로그램 설명 :

  */
public class ShinhanAPIChannelCall extends SimpleHttpRequest{

	private final String SKEY="smms_life_4321";
	private final String BASEURL="http://dev.ectech.co.kr:38382";//http://dev.ectech.co.kr:38382 , http://api.shinhan.rcsmaap.co.kr:8091
	private String reqSndURL=BASEURL+"/api/dstr/chByMsg";
	
	private OutputStream os=null;
	private BufferedReader reader=null;
	private URL url =null;
	
	public static void main(String[] args) {
		ShinhanAPIChannelCall sender=new ShinhanAPIChannelCall();

		System.setProperty("http.proxyHost", "127.0.0.1");
		System.setProperty("http.proxyPort", "8888");
		try {
		
			sender.url =new URL(sender.reqSndURL);
			
			//JWT Token header
			sender.reqBodyMap.put("alg", "HS256");
			String header=sender.getSimpleJsonReqData(sender.reqBodyMap).toString();

			//JWT Token payload
			//sender.reqBodyMap.put("agentId", "agent1");
			//sender.reqBodyMap.put("sub", "agent1");
			sender.reqBodyMap.put("sIp", "*.*.*.*");
			sender.reqBodyMap.put("iss", "smms.shinhands.com");
			sender.reqBodyMap.put("exp", "1995642443");
			sender.reqBodyMap.put("typ", "JWT");
			//sender.reqBodyMap.put("iat", "1595990484");
			String payload=sender.getSimpleJsonReqData(sender.reqBodyMap).toString();

			String token=sender.generateJWTToken(header, payload, "HmacSHA256", sender.SKEY, "UTF-8");
			System.out.println("token="+token);
			sender.reqHeaderMap.put("Accept", "*/*");
			sender.reqHeaderMap.put("Content-Type", "application/json");
			sender.reqHeaderMap.put("Authorization", "Bearer "+token);
			
			String resposeData=sender.doRequest(sender.url, sender.reqHeaderMap, sender.generateDummyJson().toJSONString(), sender.os, sender.reader, "POST");
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
	
	/* (non-Javadoc)
	 * @see com.java.http.SimpleHttpRequest#generateDummyJson()
	 */
	@Override
	public JSONObject generateDummyJson() throws ParseException {
		JSONObject _reqJsonData=new JSONObject();
		_reqJsonData.put("chId", "SMS");
		_reqJsonData.put("dstrNm", "분배기1");
		_reqJsonData.put("errCodeCnt1", 0);
		_reqJsonData.put("errCodeCnt2", 0);
		_reqJsonData.put("errCodeCnt3", 0);
		_reqJsonData.put("errCodeCnt4", 0);
		_reqJsonData.put("errCodeCnt5", 0);
		_reqJsonData.put("failCnt", 1);
		_reqJsonData.put("rsvFld1", "string");
		_reqJsonData.put("rsvFld2", "string");
		_reqJsonData.put("rsvFld3", "string");
		_reqJsonData.put("rsvFld4", "string");
		_reqJsonData.put("rsvFld5", "string");
		_reqJsonData.put("rsvFld6", "string");
		_reqJsonData.put("sendTime", "202006221212");
		_reqJsonData.put("sucessCnt", 100);
		
		return _reqJsonData;
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
