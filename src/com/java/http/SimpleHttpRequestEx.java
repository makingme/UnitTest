/**
 * 
 */
package com.java.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONObject;

/**

  * @FileName : SimpleHttpRequestEx.java

  * @Project : UnitTest

  * @Date : 2020. 6. 25. 

  * @작성자 : kibumkim

  * @변경이력 :

  * @프로그램 설명 :

  */
public class SimpleHttpRequestEx extends SimpleHttpRequest{
	private String reqTokenURL="http://15.165.55.184:443/msgsatus";
	
	private OutputStream os=null;
	private BufferedReader reader=null;
	private URL url =null;
	
	public static void main(String[] args) {
		SimpleHttpRequestEx sender=new SimpleHttpRequestEx();
		
		try {
			sender.url =new URL(sender.reqTokenURL);
			sender.reqHeaderMap.put("Content-Type", "application/json; utf-8");
			sender.reqHeaderMap.put("Accept", "application/json, text/plain, */*");
			
			sender.reqBodyMap.put("rcsId", "12345");
			sender.reqBodyMap.put("rcsSecret", "67890");
			sender.reqBodyMap.put("grantType", "clientCredentials");
			
			JSONObject reqJsonData= sender.getSimpleJsonReqData(sender.reqBodyMap);
			String resposeData=sender.doRequest(sender.url, sender.reqHeaderMap, reqJsonData.toString(), sender.os, sender.reader, "POST");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	/* (non-Javadoc)
	 * @see com.java.http.SimpleHttpRequest#generateDummyJson()
	 */
	@Override
	public JSONObject generateDummyJson() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.java.http.SimpleHttpRequest#generateDummyJson(java.lang.String)
	 */
	@Override
	public JSONObject generateDummyJson(String v1) {
		// TODO Auto-generated method stub
		return null;
	}

}
