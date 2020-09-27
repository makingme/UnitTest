/**
 * 
 */
package com.api.enc;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

/**

  * @FileName : SHA256Ex1.java

  * @Project : UnitTest

  * @Date : 2020. 9. 22. 

  * @작성자 : kibumkim

  * @변경이력 :

  * @프로그램 설명 :

  */
public class SHA256Ex1 {
	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		BASE64Encoder encoder =new BASE64Encoder();
		
		//System.out.println(encoder.encode(auth.getBytes()));
		String pw="aleldjfhrm11!";
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] encodedhash = digest.digest(pw.getBytes("UTF-8"));
		
		String e_pw=encoder.encode(encodedhash);
		System.out.println(e_pw);
		String data=e_pw+"."+"1600784913376";
		
		byte[] nHash = digest.digest(data.getBytes("UTF-8"));
		String n_pw=encoder.encode(nHash);
		System.out.println(n_pw);
	}
}
