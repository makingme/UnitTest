/**
 * 
 */
package com.json;

import org.json.simple.JSONObject;

/**

  * @FileName : SimpleJsonOverWriteEx.java

  * @Project : UnitTest

  * @Date : 2020. 6. 16. 

  * @작성자 : kibumkim

  * @변경이력 :

  * @프로그램 설명 : Simple Json put over wrte test

  */
public class SimpleJsonOverWriteEx {
	public static void main(String[] args) {
		JSONObject json=new JSONObject();
		JSONObject subjson=new JSONObject();
		
		
		
		json.put("1", "1");
		json.put("2", "2");
		json.put("3", "3");
		
		subjson.put("1", "1");
		json.put("sub", subjson);
		
		System.out.println(json.toString());
		JSONObject newjson=(JSONObject)json.get("sub");
		newjson.put("1", "2");
		json.put("sub", newjson);
		System.out.println(json.toString());
	}
}
