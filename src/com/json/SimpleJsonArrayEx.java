/**
 * 
 */
package com.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**

  * @FileName : SimpleJsonArrayEx.java

  * @Project : UnitTest

  * @Date : 2020. 6. 16. 

  * @작성자 : kibumkim

  * @변경이력 :

  * @프로그램 설명 : Simple JSONArray Exam

  */
public class SimpleJsonArrayEx {
	public static void main(String[] args) {
		JSONArray list=new JSONArray();
		JSONArray list2=new JSONArray();
		JSONObject json1=new JSONObject();
		JSONObject json2=new JSONObject();
		JSONObject json3=new JSONObject();
		json1.put("1", "1");
		json1.put("2", "2");
		
		json2.put("1", "2");
		json2.put("2", "3");
		
		list.add(json1);
		list.add(json2);
		System.out.println(list.toString());
		for (Object object : list) {
			JSONObject json=(JSONObject)object;
			json.put("1", "9");
			list2.add(json);
		}
		json3.put("suggestions", list2);
		list.clear();
		list.add(json3);
		System.out.println(list.toString());
	}
}
