/**
 * 
 */
package com.json;

import org.json.simple.JSONObject;

/**

  * @FileName : SimpleJsonEx.java

  * @Project : UnitTest

  * @Date : 2020. 6. 22. 

  * @작성자 : kibumkim

  * @변경이력 :

  * @프로그램 설명 :

  */
public class SimpleJsonEx {
	public static void main(String[] args) {
		JSONObject data=new JSONObject();
		data.put("1", "1");
		data.put("2", 2);
		System.out.println(data.toString());
		if(data.get("2") instanceof Integer) {
			System.out.println("Integer");
		}
	}
}
