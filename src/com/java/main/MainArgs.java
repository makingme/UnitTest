package com.java.main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainArgs {

	public MainArgs() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
/*		String name=System.getenv("name");//OneDrive
		String etc=System.getenv("OneDrive");
		String oper=System.getProperty("kibum");
		System.out.println(name);
		System.out.println("##############");
		System.out.println(etc);
		System.out.println("##############");
		System.out.println(oper);
		System.out.println("##############");
		//System.out.println(name+"\n"+oper+"\n"+etc);
		String first=args[0];
		System.out.println(first);
		String sec=args[1];
		System.out.println(sec);*/
		
		
		  LocalDateTime myDateObj = LocalDateTime.now();
		    System.out.println("Before formatting: " + myDateObj);
		    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		    String formattedDate = myDateObj.format(myFormatObj);
		    System.out.println("After formatting: " + formattedDate);
		
	
	}

}
