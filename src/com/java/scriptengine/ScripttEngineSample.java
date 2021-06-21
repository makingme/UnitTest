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

  * @�ۼ��� : kibumkim

  * @�����̷� :

  * @���α׷� ���� :

  */
public class ScripttEngineSample {
	public static void main(String[] args) throws FileNotFoundException, ScriptException {
		// ��ũ��Ʈ ���� ��ü ����
		ScriptEngine se =new ScriptEngineManager().getEngineByName("js");
		// ���ε� ��ü ����
		Bindings bind= se.getBindings(ScriptContext.ENGINE_SCOPE);
		
		//���ε� ��� ������Ʈ ���� - JAVA -SCRIPT �� ���ε�
		Map<String, String> map=new HashMap<String, String>();
		map.put("name", "sara");
		JSONObject data=new JSONObject();
		data.put("1", "1");
		data.put("2", 2);
		
		//���ε� ������ ��� ������Ʈ ����
		bind.put("test",new RunInScriptEx1());
		bind.put("map",map);
		bind.put("jsonData","{\"key\":\"value\"}");
		bind.put("jsonObject",data );
		
		//��ũ��Ʈ ����
		se.eval(new FileReader("SampleScriptEngine/ex2.js"));
		System.out.println("@@@@@@@@@@@@@@@@@@@");
	}
}
