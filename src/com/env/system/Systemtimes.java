package com.env.system;

import java.util.Calendar;

public class Systemtimes {

	public Systemtimes() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		long s=System.currentTimeMillis();
		Calendar ca=Calendar.getInstance();
		ca.setTimeInMillis(s);
	   System.out.println(ca.get(Calendar.YEAR));
	   System.out.println();
	}
}
