package com.string;

import java.io.UnsupportedEncodingException;

public class StringLength {

	public StringLength() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		String a="";
		String b="1234567890";
		String c="";
		long al=(long) 10000000000000000000.0;
		try {
			System.out.println(a.getBytes("euc-kr").length);
			System.out.println(b.getBytes("euc-kr").length);
			System.out.println(c.getBytes("euc-kr").length);
			System.out.println(a.length());
			System.out.println(b.length());
			System.out.println(c.length());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
