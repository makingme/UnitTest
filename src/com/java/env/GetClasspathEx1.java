package com.java.env;

public class GetClasspathEx1 {

	public GetClasspathEx1() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		System.out.println(GetClasspathEx1.class.getProtectionDomain().getCodeSource().getLocation().getFile());
	}
}
