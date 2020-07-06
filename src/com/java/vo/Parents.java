package com.java.vo;


public class Parents {
	String name;
	String age;
	String[] phones= {};
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	
	public void printName() {
		System.out.println(name);
	}
	
	public void printPhones() {

		for (String phone : phones) {
			System.out.println("p["+phone+"]");
		}
	}
}
