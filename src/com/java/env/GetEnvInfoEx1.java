package com.java.env;

public class GetEnvInfoEx1 {

	public GetEnvInfoEx1() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		System.out.println(System.getenv("java.class.path"));
		System.out.println("@@@@@@@@@@@@@@@@@@@");
		System.out.println(System.getProperty("java.class.path"));

	}

}
