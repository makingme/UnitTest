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

  * @FileName : LGCTalkSendTest.java

  * @Project : UnitTest

  * @Date : 2020. 8. 31. 

  * @작성자 : kibumkim

  * @변경이력 :

  * @프로그램 설명 :

  */
public class LGCTalkSendTest extends SimpleHttpRequest{

	private String reqSndURL="https://devtalkapi.lgcns.com/friendTalk/request/kakao.json";//"http://ximpqa.lgcns.com:60000/friendTalk/request/kakao.json";
	
	private static String[] phones= {"01098468940","01083118940","01090488940","01026313590"};
	
	private OutputStream os=null;
	private BufferedReader reader=null;
	private URL url =null;
	
	public static void main(String[] args) {
		LGCTalkSendTest sender=new LGCTalkSendTest();
		boolean isValid =true;
	
		String rcvNum=null;
		if(args.length>0) {
			rcvNum=args[0];
			isValid=sender.checkReceivers(rcvNum, phones);
			
		}
		
		if(!isValid) {
			System.out.println("미등록 전화번호입력으로 인한 종료\n 등록현황[01098468940, 01083118940, 01090488940, 01026313590]");
			System.exit(0);
		}
		
		try {
		
			//단건 메시지 전송
			sender.url =new URL(sender.reqSndURL);
			String payload= (rcvNum==null)?sender.generateDummyJson().toString():sender.generateDummyJson(rcvNum).toString();
			
			sender.reqHeaderMap.put("authToken", "Izt6Qm1LONIfKs9znfpIig==");
			sender.reqHeaderMap.put("serverName", "Citimobile01");
			sender.reqHeaderMap.put("Content-Type", "application/json; utf-8");
			sender.reqHeaderMap.put("Content-Length", ""+payload.getBytes().length);
			sender.reqHeaderMap.put("Accept", "*/*");
			//sender.reqHeaderMap.put("Authorization", "Bearer "+accessToken);
			
			String resposeData=sender.doRequest(sender.url, sender.reqHeaderMap, payload, sender.os, sender.reader, "POST");
			
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
		JSONObject _image=new JSONObject();
		JSONArray _bntList=new JSONArray();
		String dateTime=getNowDateTime();
		_reqJsonData.put("service", "2020048094");
		_reqJsonData.put("message", "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789");
		_reqJsonData.put("mobile", "01026313590");
		
		return _reqJsonData;
	}
	/* (non-Javadoc)
	 * @see com.java.http.SimpleHttpRequest#generateDummyJson(java.lang.String)
	 */
	@Override
	public JSONObject generateDummyJson(String v1) {
		JSONObject _reqJsonData=this.generateDummyJson();
		_reqJsonData.put("phone_number", v1);

		return _reqJsonData;
	}
}
