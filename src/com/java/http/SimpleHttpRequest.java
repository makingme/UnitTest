package com.java.http;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import javax.imageio.ImageIO;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;


/**

  * @FileName : SimpleHttpRequest.java

  * @Project : UnitTest

  * @Date : 2020. 6. 16. 

  * @작성자 : kibumkim

  * @변경이력 :

  * @프로그램 설명 : 단순 Http Request 추상 클래스 작성

  */
public abstract class SimpleHttpRequest {

	/**
	 * Request Header Map
	 */
	protected Map<String,String> reqHeaderMap=new HashMap<String,String>(10);
	
	/**
	 * Request body Map
	 */
	protected Map<String,Object> reqBodyMap=new HashMap<String,Object>(10);
	
	private static String[] phones= {};
	
	final static String CRLF = "\r\n";
	
	private String charset = "UTF-8";
	
	private String boundary = null;
	
	
	/**
	
	  * @Method Name : generateDummyJson
	
	  * @작성일 : 2020. 6. 19.
	
	  * @작성자 : kibumkim
	
	  * @변경이력 : 
	
	  * @Method 설명 : 테스트발송 내용 생성 함수
	
	  * @return JSONObject 발송 내용
	
	  */
	public abstract JSONObject generateDummyJson() throws ParseException;
	
	/**
	
	  * @Method Name : generateDummyJson
	
	  * @작성일 : 2020. 6. 19.
	
	  * @작성자 : kibumkim
	
	  * @변경이력 : 
	
	  * @Method 설명 : 테스트발송 내용 생성 함수
	
	  * @param v1 발송 내용 변경 값
	  * @return JSONObject 발송 내용
	
	  */
	public abstract JSONObject generateDummyJson(String v1) throws ParseException;
	
	
	/**
	
	  * @Method Name : postRequest
	
	  * @작성일 : 2020. 6. 16.
	
	  * @작성자 : kibumkim
	
	  * @변경이력 : 
	
	  * @Method 설명 : Http Post Request 실행 함수
	
	  * @param url URL 요청 주소
	 * @param header Map Request Header 지정 값
	 * @param reqData String 요청 데이타
	 * @param os OutputStream 출력스트림
	 * @param reader BufferedReader 입력리더
	 * @param method TODO
	  * @return Response String 데이타
	  * @throws IOException
	
	  */
	public String doRequest(URL url, Map<String, String> header ,String reqData, OutputStream os, BufferedReader reader, String method) throws IOException{
		HttpURLConnection con =(HttpURLConnection) url.openConnection();
		con.setConnectTimeout(5000);
		con.setReadTimeout(5000);
		con.setRequestMethod(method.toUpperCase());
		
		Iterator<String> keyItor= header.keySet().iterator();
		int i=1;
		while(keyItor.hasNext()) {
			String key=keyItor.next();
			String value=header.get(key);
			con.setRequestProperty(key, value);
			System.out.println(i+"th Request Setting["+key+": "+value+"]");
			i++;
		}
		header.clear();
		
		con.setDoInput(true);
		if( reqData!=null && reqData.length()>0) {
			con.setDoOutput(true);
			System.out.println("Request Body Data="+ reqData);
			
			os =con.getOutputStream();
			byte[] payload = reqData.getBytes("utf-8");
			os.write(payload,0,payload.length);
			os.close();
		}
		
		int code=con.getResponseCode();
		System.out.println("Response code: "+code);
		
		String line=null;
		StringBuffer lines=new StringBuffer();
		reader= new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));
		while((line=reader.readLine())!=null) {
			lines.append(line.trim());
			lines.append(System.lineSeparator());
		}
		
		
		reader.close();
		con.disconnect();
		
		System.out.println("["+SimpleHttpRequest.getNowDateTime()+"]Response Data="+lines.toString());
		return lines.toString();
	}
	
	
	public String doRequestMulti(URL url, Map<String, String> header , Map<String, Object> body, File uploadFile, OutputStream os, BufferedReader reader, String method) throws IOException{
		HttpURLConnection con =(HttpURLConnection) url.openConnection();
		con.setConnectTimeout(5000);
		con.setReadTimeout(5000);
		con.setRequestMethod(method.toUpperCase());
		
		Iterator<String> keyItor= header.keySet().iterator();
		int i=1;
		while(keyItor.hasNext()) {
			String key=keyItor.next();
			String value=header.get(key);
			con.setRequestProperty(key, value);
			System.out.println(i+"th Request Head Setting["+key+": "+value+"]");
			i++;
		}
		header.clear();
		
		con.setDoInput(true);
		if( (body != null && body.size()>0) || (uploadFile !=null) ) {
			con.setDoOutput(true);
			boundary = getBoundary();
			
			os =con.getOutputStream();
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(os, charset), true);
			
			//Send normal param
			keyItor=body.keySet().iterator();
			while(keyItor.hasNext()) {
				String key=keyItor.next();
				String value=(String)body.get(key);
				System.out.println(i+"th Request Body["+key+": "+value+"]");
				i++;
				writer.append("--" + boundary).append(CRLF);
			    writer.append("Content-Disposition: form-data; name=\""+key+"\"").append(CRLF);
			    writer.append("Content-Type: text/plain; charset=" + charset).append(CRLF);
			    writer.append(CRLF).append(value).append(CRLF).flush();
			}
			
			//Send File
			String fileName=uploadFile.getName();
			String fileExt=uploadFile.getName().substring(uploadFile.getName().lastIndexOf(".")+1);
			writer.append("--" + boundary).append(CRLF);
			writer.append("Content-Disposition: form-data; name=\"file\"; filename=\""+fileName+"\"").append(CRLF);
		    writer.append("Content-Type: image/"+fileExt).append(CRLF);
		    writer.append(CRLF).flush();
		    BufferedImage bImage = ImageIO.read(uploadFile);
		    ImageIO.write(bImage, "png", os);
		    os.flush();
		    writer.append(CRLF).flush();
			
			writer.append("--" + boundary + "--").append(CRLF).flush();
			boundary=null;
			writer.close();
			os.close();
		}
		
		int code=con.getResponseCode();
		System.out.println("Response code: "+code);
		
		String line=null;
		StringBuffer lines=new StringBuffer();
		reader= new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));
		while((line=reader.readLine())!=null) {
			lines.append(line.trim());
			lines.append(System.lineSeparator());
		}
		
		
		reader.close();
		con.disconnect();
		
		System.out.println("["+SimpleHttpRequest.getNowDateTime()+"]Response Data="+lines.toString());
		return lines.toString();
	}

	/**
	
	  * @Method Name : getSimpleJsonReqData
	
	  * @작성일 : 2020. 6. 16.
	
	  * @작성자 : kibumkim
	
	  * @변경이력 : 
	
	  * @Method 설명 : Simple Request Data를 Json으로 변환
	
	  * @param body Map
	  * @return JSONObject RequestData
	
	  */
	public JSONObject getSimpleJsonReqData(Map<String, Object> body) {
		JSONObject _reqJsonData=new JSONObject();
		
		Iterator<String> keyItor= body.keySet().iterator();
		while(keyItor.hasNext()) {
			String key=keyItor.next();
			Object ob=body.get(key);
			_reqJsonData.put(key, ob instanceof String?(String)ob:(Integer)ob);
		}
		body.clear();
		
		return _reqJsonData;
	}
	

	/**
	
	  * @Method Name : getSimpleFormReqData
	
	  * @작성일 : 2020. 6. 16.
	
	  * @작성자 : kibumkim
	
	  * @변경이력 : 
	
	  * @Method 설명 : Simple Request Data를 Formdata로 변환
	
	  * @param body Map
	  * @param urlEncode 적용여부
	  * @return String FormData
	
	  */
	public String getSimpleFormReqData(Map<String, Object> body, boolean isURLEncoding) {
		String _reqFormData="";
		Iterator<String> keyItor= body.keySet().iterator();
		int i=1;
		while(keyItor.hasNext()) {
			String key=keyItor.next();
			String value=(String)body.get(key);
			if(i==1) {
				_reqFormData += key+"="+((isURLEncoding)?URLEncoder.encode(value):value);
			}else {
				_reqFormData+="&"+key+"="+((isURLEncoding)?URLEncoder.encode(value):value);
			}
			i++;
		}
		
		return _reqFormData;
	}

	
	
	/**
	
	  * @Method Name : getNowDateTime
	
	  * @작성일 : 2020. 6. 16.
	
	  * @작성자 : kibumkim
	
	  * @변경이력 : 
	
	  * @Method 설명 : 현재 시간 값 가져오기
	
	  * @return String 날짜 값
	
	  */
	public static String getNowDateTime() {
	   	Date today= new Date();
	    SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String formattedDate = sdf.format(today);
	    return formattedDate;
	}
	

	/**
	
	  * @Method Name : getNowDateTime
	
	  * @작성일 : 2020. 6. 16.
	
	  * @작성자 : kibumkim
	
	  * @변경이력 : 
	
	  * @Method 설명 : 현재 시간 값 설정 포맷으로 가져오기
	
	  * @param format String 날짜 포맷
	  * @return String 날짜 값
	
	  */
	public static String getNowDateTime(String format) {
	   	Date today= new Date();
	    
	    SimpleDateFormat sdf= new SimpleDateFormat(format);

	    String formattedDate = sdf.format(today);
	    return formattedDate;
	}	
	
	
	public boolean checkReceivers(String number, String[] phones) {
		for (String phone : phones) {
			if(phone.equals(number))
				return true;
		}
		
		return false;
	}
	
	public String getBoundary() {
		if(boundary==null) {
			boundary=Long.toHexString(System.currentTimeMillis());
		}
		return boundary;
	}
	public static void main(String[] args) {
		SimpleHttpRequest ss= new TalkCheckRslt();
		System.out.println(ss.getNowDateTime("yyyy-MM-dd"));
	}

}
