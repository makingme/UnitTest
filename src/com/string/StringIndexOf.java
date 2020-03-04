package com.string;

public class StringIndexOf {

	public StringIndexOf() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		String fileName="abcdef/bcd";
		String fileName2="abcdef\\bcd";
		System.out.println(fileName2.lastIndexOf("\\"));
		System.out.println(fileName.indexOf("."));
		System.out.println(fileName.substring(fileName.lastIndexOf("/")+1));
		System.out.println(fileName.substring(fileName2.lastIndexOf("\\")+1));
	}
}
