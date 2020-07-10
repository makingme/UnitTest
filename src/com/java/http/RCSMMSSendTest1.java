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

  * @FileName : RCSMMSSendTest.java

  * @Project : UnitTest

  * @Date : 2020. 6. 23. 

  * @�ۼ��� : kibumkim

  * @�����̷� :

  * @���α׷� ���� :

  */
public class RCSMMSSendTest1 extends SimpleHttpRequest{

	private String reqTokenURL="https://agency-stg.hermes.kt.com/corp/v1/token";
	private String reqSndURL="https://agency-stg.hermes.kt.com/corp/v1/message";
	
	private static String[] phones= {"01098468940","01083118940","01090488940","01026313590"};
	
	private OutputStream os=null;
	private BufferedReader reader=null;
	private URL url =null;
	
	public static void main(String[] args) {
		RCSMMSSendTest1 sender=new RCSMMSSendTest1();
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
			//��ū ��û
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
			
			//�ܰ� LMS�޽��� ����
			sender.url =new URL(sender.reqSndURL);
			String payload= (rcvNum==null)?sender.generateDummyJson().toString():sender.generateDummyJson(rcvNum).toString();
			
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
	
	/* (non-Javadoc)
	 * @see com.java.http.SimpleHttpRequest#generateDummyJson()
	 */
	@Override
	public JSONObject generateDummyJson() throws ParseException {
		JSONObject _reqJsonData=new JSONObject();
		JSONObject _commonData=new JSONObject();
		JSONObject _rcsData=new JSONObject();
		JSONObject _bodyData=new JSONObject();
		JSONArray _bntList=new JSONArray();
		JSONObject _bnt=new JSONObject();
		JSONObject _suggesJson=new JSONObject();
		JSONArray _suggesList=new JSONArray();
		JSONObject _actionJson=new JSONObject();
		JSONObject _urlActJson=new JSONObject();
		JSONObject _openUrlJson=new JSONObject();
		JSONObject _postJson=new JSONObject();
		
		String dateTime=getNowDateTime();
		
		// common Data Setting
		String msgId=dateTime.replaceAll("\\D", "");
		_commonData.put("msgId", msgId);
		_commonData.put("userContact", "01083118940");//KT:01098468940 LG:01083118940 SKT:01090488940
		_commonData.put("scheduleType", "0");
		_commonData.put("msgGroupId", msgId);
		_commonData.put("msgServiceType", "rcs");
		
		//rcs Data Setting
		_rcsData.put("chatbotId", "15776825");
		_rcsData.put("agencyId", "ktrcsdev");//ktrcsdev, ktbizrcs
		_rcsData.put("messagebaseId", "SMwThT00");
		_rcsData.put("serviceType", "RCSMMS");
		_rcsData.put("expiryOption", 1);
		_rcsData.put("header", "0");
		_rcsData.put("footer", "");
		_rcsData.put("copyAllowed", false);
		
		//Body Data Setting
		_bodyData.put("title", "-RCSMMS 1th-");
		_bodyData.put("description", "["+dateTime+"], 1th SMwThT00  �׽�Ʈ");
		_bodyData.put("media", "maapfile://BR.D93Lnzo1wL.2020-07-06T16:18:07.377");
/*		_bodyData.put("title2", "-RCSMMS 2th-");
		_bodyData.put("description2", "["+dateTime+"], 2th CMwMhM0300  �׽�Ʈ");
		_bodyData.put("media2", "maapfile://BR.D93Lnzo1wL.2020-07-06T16:18:49.552");
		_bodyData.put("title3", "");
		_bodyData.put("description3", "");
		_bodyData.put("media3", "");*/
/*		_bodyData.put("title3", "-RCSMMS 3th-");
		_bodyData.put("description3", "["+dateTime+"], 3th CMwMhM0300  �׽�Ʈ");
		_bodyData.put("media3", "maapfile://BR.D93Lnzo1wL.2020-07-06T16:19:19.238");*/
		_rcsData.put("body", _bodyData);
		
		//bnt Data Setting
		_openUrlJson.put("url", "https://www.naver.com");
		_postJson.put("data", "set_by_chatbot_open_url");
		_urlActJson.put("openUrl", _openUrlJson);
		_actionJson.put("urlAction", _urlActJson);
		_actionJson.put("displayText", "���̹������ư");
		_actionJson.put("postback", _postJson);
		_suggesJson.put("action", _actionJson);
		JSONObject a1=(JSONObject)new JSONParser().parse(_suggesJson.toString());
		_suggesJson.clear();
		_suggesList.add(a1);
		
		_openUrlJson.put("url", "https://www.kt.com");
		_postJson.put("data", "set_by_chatbot_open_url");
		_urlActJson.put("openUrl", _openUrlJson);
		_actionJson.put("urlAction", _urlActJson);
		_actionJson.put("displayText", "KT�����ư");
		_actionJson.put("postback", _postJson);
		_suggesJson.put("action", _actionJson);
		JSONObject a2=(JSONObject)new JSONParser().parse(_suggesJson.toString());
		_suggesJson.clear();
		_suggesList.add(a2);
		
/*		_openUrlJson.put("url", "https://www.daum.net");
		_postJson.put("data", "set_by_chatbot_open_url");
		_urlActJson.put("openUrl", _openUrlJson);
		_actionJson.put("urlAction", _urlActJson);
		_actionJson.put("displayText", "���������ư");
		_actionJson.put("postback", _postJson);
		_suggesJson.put("action", _actionJson);
		_suggesList.add(_suggesJson);*/
		
		_bnt.put("suggestions", _suggesList);
		_bntList.add(_bnt);
/*		_bntList.add(new JSONObject());
		_bntList.add(new JSONObject());*/
		_rcsData.put("buttons", _bntList);
		
		_reqJsonData.put("common", _commonData);
		_reqJsonData.put("rcs", _rcsData);
		return _reqJsonData;
	}

	/* (non-Javadoc)
	 * @see com.java.http.SimpleHttpRequest#generateDummyJson(java.lang.String)
	 */
	@Override
	public JSONObject generateDummyJson(String v1) throws ParseException {
		JSONObject _reqJsonData=this.generateDummyJson();
		JSONObject _commonData=(JSONObject)_reqJsonData.get("common");
		_commonData.put("userContact", v1);	
		_reqJsonData.put("common", _commonData);
		
		return _reqJsonData;
	}	
}