package com.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoadEx1 {

	public PropertiesLoadEx1() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		Properties pro= new Properties();
		try {
			pro.load(new FileInputStream("db.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	    String avalue = pro.getProperty("name");
	    System.out.println(avalue);
	    pro.setProperty("name", "properties test");
	    
	    try {
			pro.store(new FileOutputStream("db.properties"), "db info");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
