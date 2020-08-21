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

  * @FileName : TalkSendTestWithButton2.java

  * @Project : UnitTest

  * @Date : 2020. 8. 5. 

  * @�ۼ��� : kibumkim

  * @�����̷� :

  * @���α׷� ���� :

  */
public class TalkSendTestWithButton2 extends SimpleHttpRequest {

	private String reqTokenURL="http://13.209.176.120/v1/oauth/token";
	private String reqSndURL="http://13.209.176.120/v1/message/send";
	
	private static String[] phones= {"01098468940","01083118940","01090488940","01026313590"};
	
	private String authorization="APITEST0000 APITEST0000";
	
	private OutputStream os=null;
	private BufferedReader reader=null;
	private URL url =null;
	
	public static void main(String[] args) {
		TalkSendTestWithButton2 sender=new TalkSendTestWithButton2();
		boolean isValid =true;
	
		String rcvNum=null;
		if(args.length>0) {
			rcvNum=args[0];
			isValid=sender.checkReceivers(rcvNum, phones);
			
		}
		
		if(!isValid) {
			System.out.println("�̵�� ��ȭ��ȣ�Է����� ���� ����\n �����Ȳ[01098468940, 01083118940, 01090488940, 01026313590]");
			System.exit(0);
		}
		
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
			
			//�ܰ� with buttons �޽��� ����
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
		JSONObject _image=new JSONObject();
		JSONArray _bntList=new JSONArray();
		JSONObject bnt1=new JSONObject();
		JSONObject bnt2=new JSONObject();
		JSONObject bnt3=new JSONObject();
		JSONObject bnt4=new JSONObject();
		String dateTime=getNowDateTime();
		_reqJsonData.put("message_type", "AT");
		_reqJsonData.put("sender_key", "81ef72d77c4c6c66962b79239f02a58a1202f924");
		_reqJsonData.put("phone_number", "01026313590");
		_reqJsonData.put("user_key", "");
		_reqJsonData.put("template_code", "test_kep_template_006");
		_reqJsonData.put("message", "����ΰ���, �ȳ��ϼ���.\r\n������ ��ǰ�� ���� ��ü���ù� ��������� �����Ǿ� ���� 17�� �� ��� �� �����Դϴ�.\r\n\r\n������ȣ : 12345\r\n��ޱ�翬��ó: 01026313590\r\n");//1000 byte
		_reqJsonData.put("sms_message", "");//2000 byte
		_reqJsonData.put("title", dateTime);
		_reqJsonData.put("ad_flag", "Y");
		_reqJsonData.put("wide", "N");
		_reqJsonData.put("sms_type", "SM");
		_reqJsonData.put("sender_no", "15776825");
//		for(int i=1;i<=10;i++) {
//			_reqJsonData.put("etc"+i, "");
//		}
		_reqJsonData.put("tax_cd1", "");
		_reqJsonData.put("cid", dateTime.replaceAll("\\D", ""));
		_reqJsonData.put("tax_cd2", "");
		_reqJsonData.put("reservation_date", "");
		
		/*_reqJsonData.put("image", _image);*/
		_reqJsonData.put("content_group_id", "");
		
		bnt1.put("type", "�����ȸ");
		bnt1.put("name", "�����ȸ��ư");
//		bnt1.put("scheme_android", "");
//		bnt1.put("scheme_ios", "");
//		bnt1.put("url_mobile", "http://www.kakao.com");
//		bnt1.put("url_pc", "");
//		bnt1.put("chat_extra", "");
//		bnt1.put("chat_event", "");
		
		bnt2.put("type", "��Ű����");
		bnt2.put("name", "��Ű�����ư");
//		bnt2.put("scheme_android", "");
//		bnt2.put("scheme_ios", "");
//		bnt2.put("url_mobile", "");
//		bnt2.put("url_pc", "");
//		bnt2.put("chat_extra", "");
//		bnt2.put("chat_event", "");
		
		bnt3.put("type", "�������ȯ");
		bnt3.put("name", "����ư");
//		bnt3.put("scheme_android", "daumapps://open");
//		bnt3.put("scheme_ios", "daumapps://open");
//		bnt3.put("url_mobile", "");
//		bnt3.put("url_pc", "");
//		bnt3.put("chat_extra", "");
//		bnt3.put("chat_event", "");

		bnt4.put("type", "����ȯ");
		bnt4.put("name", "������ư");
		
		_bntList.add(bnt1);
		_bntList.add(bnt2);
		_bntList.add(bnt3);
		_bntList.add(bnt4);
		
		_reqJsonData.put("button", _bntList);
		
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
