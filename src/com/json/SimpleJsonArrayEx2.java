/**
 * 
 */
package com.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**

  * @FileName : SimpleJsonArrayEx2.java

  * @Project : UnitTest

  * @Date : 2020. 6. 23. 

  * @작성자 : kibumkim

  * @변경이력 :

  * @프로그램 설명 :

  */
public class SimpleJsonArrayEx2 {
	public static void main(String[] args) throws Exception {
		JSONObject reqJson=new JSONObject();
		
		JSONArray _bntList=new JSONArray();
		JSONObject _bnt=new JSONObject();
		JSONObject _suggesJson=new JSONObject();
		JSONArray _suggesList=new JSONArray();
		JSONObject _actionJson=new JSONObject();
		JSONObject _urlActJson=new JSONObject();
		JSONObject _openUrlJson=new JSONObject();
		JSONObject _postJson=new JSONObject();
		
		
		_openUrlJson.put("url", "https://www.naver.com");
		_postJson.put("data", "set_by_chatbot_open_url");
		_urlActJson.put("openUrl", _openUrlJson);
		_actionJson.put("urlAction", _urlActJson);
		_actionJson.put("displayText", "네이버열기버튼");
		_actionJson.put("postback", _postJson);
		_suggesJson.put("action", _actionJson);
		
		_suggesList.add(_suggesJson.toString());
		
		_openUrlJson.put("url", "https://www.kt.com");
		_postJson.put("data", "set_by_chatbot_open_url");
		_urlActJson.put("openUrl", _openUrlJson);
		_actionJson.put("urlAction", _urlActJson);
		_actionJson.put("displayText", "KT열기버튼");
		_actionJson.put("postback", _postJson);
		_suggesJson.put("action", _actionJson);
		
		_suggesList.add(_suggesJson.toString());
		
		_openUrlJson.put("url", "https://www.daum.net");
		_postJson.put("data", "set_by_chatbot_open_url");
		_urlActJson.put("openUrl", _openUrlJson);
		_actionJson.put("urlAction", _urlActJson);
		_actionJson.put("displayText", "다음열기버튼");
		_actionJson.put("postback", _postJson);
		_suggesJson.put("action", _actionJson);
		_suggesList.add(_suggesJson.toString());
		
		_bnt.put("suggestions", _suggesList);
		_bntList.add(_bnt);
		
		reqJson.put("buttons", _bntList);
		System.out.println(reqJson.toString());
	}
}
