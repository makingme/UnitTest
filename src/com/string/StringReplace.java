package com.string;

public class StringReplace {

	public StringReplace() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		String num="2020-06-12 20:41:40";
		//num=num.replaceAll("-", "");
		num=num.replaceAll("\\D", "");
		System.out.println(num);
	}
}	
