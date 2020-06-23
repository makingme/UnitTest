package com.api.enc;

import java.util.Base64;

import javax.xml.bind.DatatypeConverter;

import sun.misc.*;

public class Base64Ex {
	public static void main(String[] args) {
		String auth="APITEST0000 APITEST0000";
		String s_en=Base64.getEncoder().encodeToString(auth.getBytes());
		//String s_en="QVBJVEVTVDAwMDA6QVBJVEVTVDAwMDA=";
		//String s_en1=new BASE64Encoder().encode(auth.getBytes());
		String s_en2=DatatypeConverter.printBase64Binary(auth.getBytes());
		System.out.println(s_en);
		//System.out.println(s_en1);
		System.out.println(s_en2);
		String s_de=new String(Base64.getDecoder().decode(s_en));
		System.out.println(s_de);
	}
}