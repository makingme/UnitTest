/**
 * 
 */
package com.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**

  * @FileName : SimpleJsonArrayEx2.java

  * @Project : UnitTest

  * @Date : 2020. 6. 23. 

  * @작성자 : kibumkim

  * @변경이력 :

  * @프로그램 설명 :

  */
public class SimpleJsonArrayEx2 {
	public static void main(String[] args) {
		JSONObject reqJson=new JSONObject();
		
		JSONArray _bntList=new JSONArray();
		JSONObject _sugJson=new JSONObject();
		JSONArray _sugList=new JSONArray();
		JSONObject _actionJson=new JSONObject();
		JSONObject _urlActJson=new JSONObject();
		JSONObject _openUrlJson=new JSONObject();
		JSONObject _postJson=new JSONObject();
		
		_openUrlJson.put("url", "https://www.naver.com");
		_postJson.put("data", "set_by_chatbot_open_url");
		_urlActJson.put("openUrl", _openUrlJson);
		_actionJson.put("urlAction", _urlActJson);
		_actionJson.put("displayText", "페이지열기버튼");
		_actionJson.put("postback", _postJson);
		_sugList.add(_actionJson);
		_sugJson.put("suggestions", _sugList);
		_bntList.add(_sugJson);
		
		reqJson.put("buttons", _bntList);
		System.out.println(reqJson.toString());
	}
}
