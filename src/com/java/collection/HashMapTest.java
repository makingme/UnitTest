package com.java.collection;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {

	public HashMapTest() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		Map<String,Integer> vMap= new HashMap<String,Integer>(30);
		
		vMap.put("1", 1);
		int a=vMap.get("1");
		System.out.println(a);
	}
}
