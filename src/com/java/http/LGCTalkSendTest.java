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

	private String reqSndURL="https://devtalkapi.lgcns.com/friendTalk/request/kakao.json";
	
	private static String[] phones= {"01098468940","01083118940","01090488940","01026313590"};
	
	private String authorization="APITEST0000 APITEST0000";
	
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
		_reqJsonData.put("message_type", "AT");
		_reqJsonData.put("sender_key", "81ef72d77c4c6c66962b79239f02a58a1202f924");
		_reqJsonData.put("phone_number", "01026313590");
		_reqJsonData.put("user_key", "");
		_reqJsonData.put("template_code", "test_kep_template_001");
		_reqJsonData.put("message", "[카엔] 회원가입 안내\r\n어다인님, 카엔 회원이 되신 것을 환영합니다.\r\n\r\n▶신규 가입 회원 혜택\r\n1개월 무료 스트리밍 서비스 제공\r\n카카오톡 이모티콘 증정");//1000 byte
		_reqJsonData.put("sms_message", "");//2000 byte
		_reqJsonData.put("title", dateTime);
		_reqJsonData.put("ad_flag", "Y");
		_reqJsonData.put("wide", "N");
		_reqJsonData.put("sms_type", "SM");
		_reqJsonData.put("sender_no", "15776825");
//		_reqJsonData.put("etc1", "어다인");
//		_reqJsonData.put("etc2", "1개월 무료");
//		_reqJsonData.put("etc3", "이모티콘 할인권 증정");
//		for(int i=4;i<=10;i++) {
//			_reqJsonData.put("etc"+i, "");
//		}
		_reqJsonData.put("tax_cd1", "");
		_reqJsonData.put("cid", dateTime.replaceAll("\\D", ""));
		_reqJsonData.put("tax_cd2", "");
		_reqJsonData.put("reservation_date", "");
		_reqJsonData.put("button", _bntList);
		/*_reqJsonData.put("image", _image);*/
		_reqJsonData.put("content_group_id", "");
		
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
