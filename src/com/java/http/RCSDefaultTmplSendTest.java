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

  * @FileName : RCSDefaultTmplSendTest.java

  * @Project : UnitTest

  * @Date : 2020. 6. 23. 

  * @작성자 : kibumkim

  * @변경이력 :

  * @프로그램 설명 :

  */
public class RCSDefaultTmplSendTest extends SimpleHttpRequest {
	private String reqTokenURL="https://agency-stg.hermes.kt.com/corp/v1/token";
	private String reqSndURL="https://agency-stg.hermes.kt.com/corp/v1/message";
	
	private static String[] phones= {"01098468940","01083118940","01090488940","01026313590"};
	
	private OutputStream os=null;
	private BufferedReader reader=null;
	private URL url =null;
	
	public static void main(String[] args) {
		RCSDefaultTmplSendTest sender=new RCSDefaultTmplSendTest();
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
			
			//단건 메시지 전송
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
	public JSONObject generateDummyJson() {
		JSONObject _reqJsonData=new JSONObject();
		JSONObject _commonData=new JSONObject();
		JSONObject _rcsData=new JSONObject();
		JSONObject _bodyData=new JSONObject();
		JSONArray _bntList=new JSONArray();
		
		String dateTime=getNowDateTime();
		
		// common Data Setting
		String msgId=dateTime.replaceAll("\\D", "");
		_commonData.put("msgId", msgId);
		_commonData.put("userContact", "01098468940");//KT:01098468940 LG:01083118940 SKT:01090488940
		_commonData.put("scheduleType", "0");
		_commonData.put("msgGroupId", msgId);
		_commonData.put("msgServiceType", "rcs");
		
		//rcs Data Setting
		_rcsData.put("chatbotId", "15776825");
		_rcsData.put("agencyId", "ktrcsdev");//ktrcsdev, ktbizrcs
		_rcsData.put("messagebaseId", "UBR.D93Lnzo1wL-GG000F");
		_rcsData.put("serviceType", "RCSTMPL");
//		_rcsData.put("messagebaseId", "SS000000");
//		_rcsData.put("serviceType", "RCSSMS");
		_rcsData.put("expiryOption", 1);
		_rcsData.put("header", "0");
		_rcsData.put("footer", "");
		_rcsData.put("copyAllowed", false);
		
		//Body Data Setting
		_bodyData.put("description", "["+dateTime+"], 프리 RCSTMPL 테스트");
		
		_rcsData.put("body", _bodyData);
		_rcsData.put("buttons", _bntList);
		
		_reqJsonData.put("common", _commonData);
		_reqJsonData.put("rcs", _rcsData);
		return _reqJsonData;
	}

	/* (non-Javadoc)
	 * @see com.java.http.SimpleHttpRequest#generateDummyJson(java.lang.String)
	 */
	@Override
	public JSONObject generateDummyJson(String v1) {
		JSONObject _reqJsonData=this.generateDummyJson();
		JSONObject _commonData=(JSONObject)_reqJsonData.get("common");
		_commonData.put("userContact", v1);	
		_reqJsonData.put("common", _commonData);
		
		return _reqJsonData;
	}	
}
