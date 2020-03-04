package com.json;

import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonHandler {

	public JsonHandler() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		JSONObject jObject=new JSONObject();
		jObject.put("key1", "1");
		jObject.put("key2", "2");
		jObject.put("key3", "3");
		Iterator a= jObject.keySet().iterator();
/*		while(a.hasNext()) {
			System.out.println(a.next().toString());
		}*/
		
		JSONObject tot=new JSONObject();
		JSONObject ob1=new JSONObject();
		JSONObject ob2=new JSONObject();
		ob1.put("ob11", "1");
		ob1.put("ob12", "2");
		
		ob2.put("ob21", "3");
		ob2.put("ob22", "4");
		tot.put("input1", ob1);
		tot.put("input2", ob2);
		
		
		
/*		Iterator b= tot.keySet().iterator();
		while(b.hasNext()) {
			System.out.println(b.next().toString());
		}*/
		
		JSONArray ar= new JSONArray();
		ar.add(ob1);
		ar.add(ob2);
		tot.put("ar", ar);
		Iterator c= tot.keySet().iterator();
		while(c.hasNext()) {
			//System.out.println(c.next().toString());
			String kName=c.next().toString();
			System.out.print("key name="+kName+", ");
			Object aaa=tot.get(kName) ;
				
			if(aaa !=null && aaa instanceof JSONObject) {
				System.out.println("JSONObject");
				//tot.get(c.next().toString());
			}else if(aaa !=null && aaa instanceof JSONArray ){
				System.out.println("JSONArray");
			}else {
				System.out.println("UNKOWN");
			}
		}
		
		JSONParser parser= new JSONParser();
		
		String jstring=tot.toJSONString();
		System.out.println("@@@@@@@@@@@@@@");
		System.out.println(jstring);
		System.out.println("@@@@@@@@@@@@@@");
		try {
			JSONObject nJson=(JSONObject) parser.parse(jstring);
			
			Iterator cr= tot.keySet().iterator();
			while(cr.hasNext()) {
				//System.out.println(c.next().toString());
				String kName=cr.next().toString();
				System.out.print("key name="+kName+", ");
				Object aaa=tot.get(kName) ;
					
				if(aaa !=null && aaa instanceof JSONObject) {
					System.out.println("JSONObject");
					//tot.get(c.next().toString());
				}else if(aaa !=null && aaa instanceof JSONArray ){
					System.out.println("JSONArray");
				}else {
					System.out.println("UNKOWN");
				}
			}
			
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}
}
