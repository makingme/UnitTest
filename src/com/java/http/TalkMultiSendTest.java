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

  * @FileName : TalkMultiSendTest.java

  * @Project : UnitTest

  * @Date : 2020. 6. 22. 

  * @작성자 : kibumkim

  * @변경이력 :

  * @프로그램 설명 : 알림톡 대량 발송

  */
public class TalkMultiSendTest extends SimpleHttpRequest{

	private String reqTokenURL="http://13.209.176.120/v1/oauth/token";
	private String reqSndURL="http://13.209.176.120/v1/message/multi/send";
	
	private static String[] phones= {"01098468940","01083118940","01090488940","01026313590"};
	
	private String authorization="APITEST0000 APITEST0000";
	
	private OutputStream os=null;
	private BufferedReader reader=null;
	private URL url =null;
	
	public static void main(String[] args) {
		TalkMultiSendTest sender=new TalkMultiSendTest();
		boolean isValid =true;
	
		String rcvNum=null;
		if(args.length>0) {
			rcvNum=args[0];
			isValid=sender.checkReceivers(rcvNum);
			
		}
		
		if(!isValid) {
			System.out.println("미등록 전화번호입력으로 인한 종료\n 등록현황[01098468940, 01083118940, 01090488940, 01026313590]");
		}
		
		try {
			//토큰 얻기
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
			
			//대량 메시지 전송
			sender.url =new URL(sender.reqSndURL);
			String payload= (rcvNum==null)?sender.generateDummyJson().toString():sender.generateDummyJson(rcvNum).toString();
			
			sender.reqHeaderMap.put("Content-Type", "application/json; utf-8");
			sender.reqHeaderMap.put("Content-Length", ""+payload.getBytes().length);
			sender.reqHeaderMap.put("Accept", "*/*");
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
	
	/* (non-Javadoc)
	 * @see com.java.http.SimpleHttpRequest#generateDummyJson()
	 */
	@Override
	public JSONObject generateDummyJson() {
		JSONObject _reqJsonData=new JSONObject();
		JSONObject _msg=new JSONObject();
		JSONObject _target=new JSONObject();
		
		JSONArray _bntList=new JSONArray();
		JSONArray _msgList=new JSONArray();
		JSONArray _targetList=new JSONArray();
		
		String dateTime=getNowDateTime();
		_reqJsonData.put("message_type", "AT");
		_reqJsonData.put("sender_key", "81ef72d77c4c6c66962b79239f02a58a1202f924");
		_reqJsonData.put("template_code", "test_kep_template_001");
		_reqJsonData.put("reservation_date", "test_kep_template_001");
		
		_msg.put("phone_number", "01026313590");
		_msg.put("user_key", "");
		_msg.put("template_code", "test_kep_template_001");
		_msg.put("message", "[카엔] 회원가입 안내\r\n어다인님, 카엔 회원이 되신 것을 환영합니다.\r\n\r\n▶신규 가입 회원 혜택\r\n1개월 무료 스트리밍 서비스 제공\r\n카카오톡 이모티콘 증정");//1000 byte
		_msg.put("sms_message", "");//2000 byte
		_msg.put("title", dateTime);
		_msg.put("ad_flag", "Y");
		_msg.put("wide", "N");
		_msg.put("sms_type", "SM");
		_msg.put("sender_no", "15776825");
		_msg.put("etc1", "어다인");
		_msg.put("etc2", "1개월 무료");
		_msg.put("etc3", "이모티콘 할인권 증정");
		for(int i=4;i<=10;i++) {
			_msg.put("etc"+i, "");
			_target.put("etc"+i, "");
		}
		_msg.put("tax_cd1", "");
		_msg.put("tax_cd2", "");
		_msg.put("button", _bntList);
		_msg.put("image", "");
		_msg.put("cid", dateTime.replaceAll("\\D", ""));
		_msg.put("content_group_id", "");
		_msgList.add(_msg);
		
		_target.put("phone_number", "01026313590");
		_target.put("sender_no", "15776825");
		_target.put("etc1", "어다인");
		_target.put("etc2", "1개월 무료");
		_target.put("etc3", "이모티콘 할인권 증정");
		_target.put("tax_cd1", "");
		_target.put("tax_cd2", "");
		_target.put("cid", dateTime.replaceAll("\\D", ""));
		_targetList.add(_target);
		
		_reqJsonData.put("send_messages", _msgList);
		_reqJsonData.put("targets", _targetList);
		return _reqJsonData;
	}
	/* (non-Javadoc)
	 * @see com.java.http.SimpleHttpRequest#generateDummyJson(java.lang.String)
	 */
	@Override
	public JSONObject generateDummyJson(String v1) {
		JSONObject _reqJsonData=this.generateDummyJson();
		
		JSONArray _msgList=(JSONArray)_reqJsonData.get("send_messages");
		JSONArray _newMsgList=new JSONArray();
		for (Object object : _msgList) {
			JSONObject _msg=(JSONObject)object;
			_msg.put("phone_number", v1);
			_newMsgList.add(_msg);
		}
		
		JSONArray _targetList=(JSONArray)_reqJsonData.get("targets");
		JSONArray _newTargetList=new JSONArray();
		for (Object object : _targetList) {
			JSONObject _target=(JSONObject)object;
			_target.put("phone_number", v1);
			_newTargetList.add(_target);
		}
		
		_reqJsonData.put("send_messages", _msgList);
		_reqJsonData.put("targets", _targetList);
		return _reqJsonData;
	}

}
