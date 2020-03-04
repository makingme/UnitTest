package com.java.casting;

public class BooleanCasting {

	public BooleanCasting() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		String a="머ㅣ어";
		int c=10;
		boolean b=false;
		c+=1;
		c+=2;
		if(Boolean.parseBoolean(a)) {
			System.out.println("pass");
		}else {
			System.out.println("no");
		}
	}
}
