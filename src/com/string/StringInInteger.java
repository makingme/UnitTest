package com.string;

public class StringInInteger {

	public StringInInteger() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		int num=0;
		String aa=num+"번째 데이터";
		
		for(int i=1;i<=100;i++) {
			num=i;
			System.out.println(aa);
		}
	}
}
