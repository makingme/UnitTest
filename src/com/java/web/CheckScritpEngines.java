/**
 * 
 */
package com.java.web;

import java.util.List;

import javax.script.*;
/**

  * @FileName : CheckScritpEngines.java

  * @Project : UnitTest

  * @Date : 2021. 5. 12. 

  * @작성자 : kibumkim

  * @변경이력 :

  * @프로그램 설명 :

  */
public class CheckScritpEngines {
	public static void main(String[] args) {
		ScriptEngineManager smgr = new ScriptEngineManager();

		List<ScriptEngineFactory> factories = smgr.getEngineFactories();
		for (ScriptEngineFactory factory : factories) {
			System.out.println("===========================");
			String engName=factory.getEngineName();
			String engVersion=factory.getEngineVersion();
			String engLang=factory.getLanguageName();
			String engLangVersion=factory.getLanguageVersion();
			System.out.printf("엔진 정보: %s - %s \n",engName, engVersion);
			
			List<String> engNames=factory.getNames();
			for (String name : engNames) {
				System.out.printf("엔진별칭: %s \n",name);
			}
			System.out.printf("지원언어정보: %s - %s \n",engLang, engLangVersion);
			
			List<String> engMimeTypes=factory.getMimeTypes();
			for (String mime : engMimeTypes) {
				System.out.printf("엔진지원 MIME: %s \n",mime);
			}
			
			List<String> engExtensions=factory.getExtensions();
			for (String ext : engExtensions) {
				System.out.printf("엔진 지원 확장자: %s \n",ext);
			}
		}
	}
}
