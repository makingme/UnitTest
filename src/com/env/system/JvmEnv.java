package com.env.system;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class JvmEnv {

	public JvmEnv() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		Set<Entry<String,String>> set =System.getenv().entrySet();
		Iterator<Entry<String,String>> itors= set.iterator();
		while(itors.hasNext()) {
			Entry<String,String> ent=itors.next();
			System.out.println(ent.getKey()+"="+ent.getValue());
		}
		//System.out.println(System.get);
		System.out.println(System.getProperty("qqqqqqqq"));
		System.out.println(System.getProperty("pppppppp"));
		System.out.println();
		System.out.println();
		System.out.println(System.getenv("qqqqqqqq"));
		System.out.println(System.getenv("pppppppp"));
		System.out.println(System.getProperty("java.class.path"));
		System.out.println( System.getenv("CLASSPATH"));
	}

}
