/**
 * 
 */
package com.java.crypt;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Encoder;

/**

  * @FileName : HMACSHA256Ex.java

  * @Project : UnitTest

  * @Date : 2020. 7. 13. 

  * @작성자 : kibumkim

  * @변경이력 :

  * @프로그램 설명 :

  */
public class HMACSHA256Ex {
	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
		String secret="smms_life_4321";//smms_bank_4321 smms_life_4321
		byte[] hash =secret.getBytes("UTF-8");
		// Mac since java 1.4 support HmacMD5, HmacSHA1, HmacSHA256
		Mac sha256Hmac = Mac.getInstance("HMACSHA256");//HmacSHA256 HMACSHA256
		SecretKeySpec sKey = new SecretKeySpec(hash, "HMACSHA256");
		sha256Hmac.init(sKey);
		String data="eyJhbGciOiJIUzI1NiJ9Cg.eyJpc3MiOiJzbW1zLnNoaW5oYW5kcy5jb20iLAoidHlwIjoiSldUIiwKInNJcCI6IiouKi4qLioiLAoiZXhwIjoiMTc5NjAxMzYxNSIsCiJhZ2VudElkIjoiYWdlbnQxIiwKInN1YiI6ImFnZW50MSIsCiJpYXQiOiIxNTk1OTkwNDg0In0K";
		byte[] singedBytes=sha256Hmac.doFinal(data.getBytes("UTF-8"));
		
		BASE64Encoder encoder =new BASE64Encoder();
		String base64Data=encoder.encode(singedBytes);
		System.out.println(base64Data);
		
	}
}
