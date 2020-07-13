/**
 * 
 */
package com.api.enc;

import java.net.URLEncoder;
import java.util.Base64;

import org.json.simple.JSONObject;

import sun.misc.BASE64Encoder;

/**

  * @FileName : Base64WithURL.java

  * @Project : UnitTest

  * @Date : 2020. 7. 13. 

  * @작성자 : kibumkim

  * @변경이력 :

  * @프로그램 설명 :URL safety BASE64 Encoding

  */
public class Base64WithURL {
	public static void main(String[] args) {
		//eyJhbGciOiJIUzI1NiJ9.
		//eyJzdWIiOiJhZ2VudDEiLCJhZ2VudElkIjoiYWdlbnQxIiwiaXNzIjoic21tcy5zaGluaGFuZHMuY29tIiwidHlwIjoiSldUIiwic0lwIjoiKi4qLiouKiIsImV4cCI6MTU5NTY0MjQ0MywiaWF0IjoxNTk0MTcxMjE0fQ.
		//ZBQJg66xzHrLFkUMQAPrc0_eFZfJDmSyKxKmzVaat64
		BASE64Encoder encoder =new BASE64Encoder();
		
		JSONObject header=new JSONObject();
		header.put("alg", "HS256");		
		String base64Header=encoder.encode(header.toString().getBytes());
		String base64URLHeader=URLEncoder.encode(base64Header);
		String base64URLHeader8=Base64.getUrlEncoder().withoutPadding().encodeToString(header.toString().getBytes());
		System.out.println("HEADER");
		System.out.println(base64Header);
		System.out.println(base64URLHeader);
		System.out.println(base64URLHeader8);
		System.out.println("================================");
		
		JSONObject payload=new JSONObject();
		payload.put("sub", "agent1");
		payload.put("agentId", "agent1");
		payload.put("iss", "smms.shinhands.com");
		payload.put("typ", "JWT");
		payload.put("sIp", "*.*.*.*");
		payload.put("exp", 1595642443);
		payload.put("iat", 1594171214);
		String base64Payload=encoder.encode(payload.toString().getBytes());
		String base64URPayload=URLEncoder.encode(base64Payload);
		String base64URLPayload8=Base64.getUrlEncoder().withoutPadding().encodeToString(payload.toString().getBytes());
		String base64URLPayloadM=new String(encodeUrlSafe(base64Payload.getBytes()));
		System.out.println("PAYLOAD");
		System.out.println(base64Payload);
		System.out.println(base64URPayload);
		System.out.println(base64URLPayload8);
		System.out.println(base64URLPayloadM.replaceAll("=", "").replace(System.lineSeparator(), ""));
		System.out.println("================================");
		
		String test="eyJzdWIiOiJhZ2VudDEiLCJhZ2VudElkIjoiYWdlbnQxIiwiaXNzIjoic21tcy5zaGluaGFuZHMuY29tIiwidHlwIjoiSldUIiwic0lwIjoiKi4qLiouKiIsImV4cCI6MTU5NTY0MjQ0MywiaWF0IjoxNTk0MTcxMjE0fQ";
		String d_test=new String(Base64.getUrlDecoder().decode(test));
		String d_test1=new String(Base64.getUrlDecoder().decode(base64URLPayload8));
		
		//d_test2 >> java.lang.IllegalArgumentException
		//String d_test2=new String(Base64.getUrlDecoder().decode(base64URPayload));
		System.out.println(d_test);
		System.out.println(d_test1);
		//System.out.println(d_test2);
		

	}


	public static byte[] encodeUrlSafe(byte[] encode) {
	    for (int i = 0; i < encode.length; i++) {
	        if (encode[i] == '+') {
	            encode[i] = '-';
	        } else if (encode[i] == '/') {
	            encode[i] = '_';
	        } 
	    }
	    return encode;
	}

}
