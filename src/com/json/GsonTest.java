package com.json;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class GsonTest {

	public GsonTest() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
/*		StringBuilder sb=new StringBuilder();
		String jsonString="{a:a,b:b}";
		JsonParser jsonParser = new JsonParser();
		JsonObject jo = (JsonObject)jsonParser.parse(jsonString);
		Set<String> keySet=jo.keySet();
		
		Iterator<String> iter=keySet.iterator();
		while(iter.hasNext()) {
			//sb.append("|"+iter.next());
			System.out.println(iter.next());
		}*/
		String jsonArray="[{name=kibum, grpSeq=1111, emplId=1111},{name=youyo, grpSeq=2222, emplId=2222}]";
		Gson gson = new Gson();

		ArrayList<GsonTestBean> aaaaa=gson.fromJson( jsonArray , new TypeToken<ArrayList<GsonTestBean>>(){}.getType() );
		System.out.println(aaaaa.size());

	}
}
