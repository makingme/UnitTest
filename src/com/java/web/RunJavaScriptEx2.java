/**
 * 
 */
package com.java.web;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import javax.script.*;

import org.json.simple.JSONObject;

/**

  * @FileName : RunJavaScriptEx2.java

  * @Project : UnitTest

  * @Date : 2021. 5. 12. 

  * @작성자 : kibumkim

  * @변경이력 :

  * @프로그램 설명 :

  */
public class RunJavaScriptEx2 {
	public static void main(String[] args) throws FileNotFoundException, ScriptException {
		ScriptEngine se =new ScriptEngineManager().getEngineByName("js");
		//ScriptContext sc =new SimpleScriptContext();
		Bindings bind= se.getBindings(ScriptContext.ENGINE_SCOPE);
		Map<String, String> map=new HashMap<String, String>();
		map.put("name", "sara");
		bind.put("test",new RunInScriptEx1());
		bind.put("map",map);
		bind.put("jsonData","{\"key\":\"value\"}");
		JSONObject data=new JSONObject();
		data.put("1", "1");
		data.put("2", 2);
		
		bind.put("jsonObject",data );
		se.eval(new FileReader("SampleFile/ex2.js"));
		System.out.println("@@@@@@@@@@@@@@@@@@@");
		System.out.println(map.get("new"));
	}
}
