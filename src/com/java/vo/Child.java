package com.java.vo;

public class Child extends Parents {


	public Child() {
		super.phones=this.phones;
	}

	String address;
	String phn;
	String name="aaa";
	String[] phones= {"01098468940","01083118940","01090488940","01026313590"};
	
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhn() {
		return phn;
	}
	public void setPhn(String phn) {
		this.phn = phn;
	}
	
	public static void main(String[] args) {
		Child c=new Child();
		c.printPhones();
		c.printName();
	}

}
