package com.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoadEx3 {

	public PropertiesLoadEx3() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		/*
		 * m1 방식
		 * 해당방식은 CLASSPATH에 properties 파일 위치 경로 추가해야함
		 */
		String clspath = System.getProperty("java.class.path") + System.getProperty("path.separator") + System.getenv("CLASSPATH");
		String[] pathes = clspath.split(System.getProperty("path.separator"));
		File pFile = null;
		String prop_file = "db" + ".properties";
		for ( String p : pathes) {
			System.out.println(p);
			File f = new File(new File(p),prop_file);

			if ( f.exists() ) {
				pFile = f ;
				break ;
			}

		}
		System.out.println(pFile.getAbsolutePath());
		Properties pro= new Properties();
		
		try {
			pro.load(new FileInputStream(pFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	    String avalue = pro.getProperty("name");
	    System.out.println(avalue);
	}
}
