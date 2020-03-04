package com.java.classloader;

import java.net.URL;

public class ClassLoaderEx1 {

	public ClassLoaderEx1() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		URL url=ClassLoaderEx1.class.getClass().getClassLoader().getResource("db.properties");
		System.out.println(url.getPath());
	}
}
