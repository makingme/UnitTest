package com.string;

public class Substring2 {

	public Substring2() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		String str="1234567890";
		System.out.println(str.substring(2,4));
		
		byte[] strBytes=str.getBytes();
		
		String str2=new String(strBytes, 5, strBytes.length-5);
		
		System.out.println(str2);
		
		String fileName = "image.jpg";
		String fileExtension=fileName.substring(fileName.lastIndexOf(".")+1);
		System.out.println(fileExtension);
		
		String data = "@s:abcd";
		String pnm = data.substring(1);
		String vnm = pnm ;
		String tnm = "defalut";//string default.
		int pos = vnm.indexOf(":");
		if ( pos >= 0 ) {
			vnm = pnm.substring(pos+1);
			if ( pos > 0) tnm = pnm.substring(0,pos);
			
		}
		System.out.println(tnm);
	}
}
