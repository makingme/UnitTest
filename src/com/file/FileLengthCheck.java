package com.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FileLengthCheck {

	public FileLengthCheck() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) throws Exception {
		File f = new File("C:\\Users\\kibumkim\\Downloads\\20191223_ERRLOG\\bin\\msg.MTSMT00001_1.x.20191223182852.dat");
	    FileInputStream fis = new FileInputStream(f);
	    byte[] buffer = new byte[6000];
	    int reads=0;
	    int total=0;
	    StringBuilder sb=new StringBuilder();
	    String s=null;
	    while((reads = fis.read(buffer))!=-1) {
	    	total+=reads;
	    	sb.append(new String(buffer,0,reads,"euc-kr"));
	    	System.out.println("읽은길이 =="+reads);
	    }
	  //  System.out.println("["+s+"]");
	    byte[] a=sb.toString().getBytes("euc-kr");
	    
	    //System.out.println(total);
	   // System.out.println(a.length);
	    
	    String header =new String(buffer,0,100,"euc-kr");
	    String sms =new String(buffer,100,330,"euc-kr");
	    String lms =new String(buffer,430,4300,"euc-kr");
	    String kko =new String(buffer,4730,700,"euc-kr");
	    System.out.println("###############################################");
	    System.out.println("["+header+"]");
	    System.out.println("###############################################");
	    System.out.println("["+sms+"]");
	    System.out.println("###############################################");
	    System.out.println("["+lms+"]"); 
	    System.out.println("###############################################");
	    System.out.println("["+kko+"]");
	    System.out.println("###############################################");
	}

}
