package com.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoadEx2 {

	public PropertiesLoadEx2() {
		
	}
	public static void main(String[] args) {
		InputStream is=null;
		try {
			String path=new File("").getAbsolutePath()+File.separator;
			System.out.println(path);
			is=new FileInputStream((new File("").getAbsolutePath()+File.separator+"db.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Properties pro= new Properties();
		
		try {
			pro.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	    String avalue = pro.getProperty("name");
	    System.out.println(avalue);
	}
}
