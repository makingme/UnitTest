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

  * @�ۼ��� : kibumkim

  * @�����̷� :

  * @���α׷� ���� :

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
			System.out.printf("���� ����: %s - %s \n",engName, engVersion);
			
			List<String> engNames=factory.getNames();
			for (String name : engNames) {
				System.out.printf("������Ī: %s \n",name);
			}
			System.out.printf("�����������: %s - %s \n",engLang, engLangVersion);
			
			List<String> engMimeTypes=factory.getMimeTypes();
			for (String mime : engMimeTypes) {
				System.out.printf("�������� MIME: %s \n",mime);
			}
			
			List<String> engExtensions=factory.getExtensions();
			for (String ext : engExtensions) {
				System.out.printf("���� ���� Ȯ����: %s \n",ext);
			}
		}
	}
}
