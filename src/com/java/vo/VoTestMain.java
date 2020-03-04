package com.java.vo;

public class VoTestMain {

	public VoTestMain() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		
		Child c= new Child();
	//	c.setName("kibum");
		c.setAge("35");
		c.setAddress("Korea");
		c.setPhn("01011112222");
		
		Parents p=c;
		
		System.out.println(p.getName());
		System.out.println(p.age);
		
		
	}
}
