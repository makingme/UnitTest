/**
 * 
 */
package com.java.web;

import javax.script.*;
import java.io.*;

/**

  * @FileName : RunJavaSriptEx1.java

  * @Project : UnitTest

  * @Date : 2021. 5. 12. 

  * @�ۼ��� : kibumkim

  * @�����̷� :

  * @���α׷� ���� :

  */
public class RunJavaScriptEx1 {
	public static void main(String[] args) throws FileNotFoundException, ScriptException {
		ScriptEngine se = new ScriptEngineManager().getEngineByName("Nashorn");
		
		se.eval(new FileReader("SampleFile/ex1.js"));
		
		
	}
}
