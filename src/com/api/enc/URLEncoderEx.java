package com.api.enc;

import java.net.URLEncoder;

public class URLEncoderEx {
	public static void main(String[] args) {
		String reqInputData="grant_type=password&username=kepbzm@gmail.com&password=bzm7777#";
		System.out.println(URLEncoder.encode(reqInputData));
	}
}
