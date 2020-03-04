package com.json;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONParserEx {

	public JSONParserEx() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		String jstring="{\"등급\":\"VIP\", \"고객명\":\"홍길동\"}";
		System.out.println(jstring);
		JSONParser parser= new JSONParser();

		try {
			JSONObject json=(JSONObject) parser.parse(jstring);

			System.out.println(json.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
