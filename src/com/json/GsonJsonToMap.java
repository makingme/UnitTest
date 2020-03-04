package com.json;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;

public class GsonJsonToMap {

	public GsonJsonToMap() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		//String repl="{등급:VIP, 고객명:홍길동}";
		String repl="{'등급':'VIP', '고객명':'홍길동1'}";
		//String repl="{\"등급\":\"VIP\", \"고객명\"s:\"홍길동\"}";
		Gson gson = new Gson(); 
		Map<String,String> map = new HashMap<String,String>(15);
		map = (Map<String,String>) gson.fromJson(repl, map.getClass());
		Set<String> set=map.keySet();
		Iterator<String> ito=set.iterator();
		while(ito.hasNext()) {
			String key=ito.next();
			System.out.println(key);
			System.out.println(map.get(key));
		}
	}
	
	public Map<String, String> jsonToMap(String json){
		Gson gson = new Gson(); 
		Map<String,String> map = new HashMap<String,String>(15);
		return (Map<String,String>) gson.fromJson(json, map.getClass());
	}
}
