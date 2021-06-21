/**
 * 
 */
package com.java.scriptengine;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleScriptContext;

import org.json.simple.JSONObject;

import com.java.web.RunInScriptEx1;

/**

  * @FileName : ScripttEngineSample.java

  * @Project : UnitTest

  * @Date : 2021. 6. 18. 

  * @작성자 : kibumkim

  * @변경이력 :

  * @프로그램 설명 :

  */
public class ScripttEngineSample {
	public static void main(String[] args) throws FileNotFoundException, ScriptException {
		// 스크립트 엔진 객체 생성
		ScriptEngine se =new ScriptEngineManager().getEngineByName("js");
		// 바인딩 객체 생성
		Bindings bind= se.getBindings(ScriptContext.ENGINE_SCOPE);
		
		//바인드 대상 오브젝트 생성 - JAVA -SCRIPT 단 바인드
		Map<String, String> map=new HashMap<String, String>();
		map.put("name", "sara");
		JSONObject data=new JSONObject();
		data.put("1", "1");
		data.put("2", 2);
		
		//바인드 객제에 대상 오브젝트 적재
		bind.put("test",new RunInScriptEx1());
		bind.put("map",map);
		bind.put("jsonData","{\"key\":\"value\"}");
		bind.put("jsonObject",data );
		
		//스크립트 실행
		se.eval(new FileReader("SampleScriptEngine/ex2.js"));
		System.out.println("@@@@@@@@@@@@@@@@@@@");
	}
}
