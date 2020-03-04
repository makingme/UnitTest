package com.string;

public class StringSplit {

	public StringSplit() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		
		//([$])([{]{0,1})([가-힣]{0,2})([0-9]){0,1}
		String a="${변수)${변수22}";
		int strLength = a.split("\\$\\{변수}").length - 1;
		System.out.println(strLength);
		
	}
}
