package com.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoadEx4 {

	public PropertiesLoadEx4() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		InputStream is=null;
		try {
			is=new FileInputStream(new File("db.properties"));
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
