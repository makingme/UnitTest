package com.api.enc;

import com.odinues.m1.util.Crypto;

public class EncodingAPIEx {

	public EncodingAPIEx() {
	
	}
	
	public static void main(String[] args) {
		Crypto cr= new Crypto();
		cr.init();
		String enc=cr.encryptData("");
		String dec=cr.decryptData(enc);
		System.out.println(enc);
		System.out.println("@@@@@@@@@@");
		System.out.println(dec);
	}

}
