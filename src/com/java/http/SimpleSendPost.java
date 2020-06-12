package com.java.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class SimpleSendPost {
	private static HttpURLConnection con;
	private static String reqURL="http://222.111.214.31:40001/api/mns/req";
	//private static String reqURL="http://httpbin.org/post";
	private static String cid="1";
	private static String accessToken="46fa01ad-926c-4cea-a878-3dd8a93717ff";
	
	public static void main(String[] args) {
		String line=null;
		StringBuffer lines=new StringBuffer();
		OutputStream os=null;
		BufferedReader reader=null;
		System.setProperty("http.proxyHost", "127.0.0.1");
		System.setProperty("http.proxyPort", "8888");

		try {
			URL url = new URL(reqURL);

			con =(HttpURLConnection) url.openConnection();
			con.setConnectTimeout(5000);
			con.setReadTimeout(5000);
			con.setRequestProperty("Authorization", "Bearer "+accessToken);
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json; utf-8");
			con.setRequestProperty("Accept", "*/*");
			con.setDoOutput(true);
			con.setDoInput(true);
			JSONObject reqData=generateDummyJson();
			
			String reqInputData=reqData.toString();
			System.out.println("====Request Json Data====");
			System.out.println(reqInputData);
			System.out.println("=========================");
			os =con.getOutputStream();
			
			//byte[] payload = jsonInputData.getBytes("utf-8");
			byte[] payload = reqInputData.getBytes("utf-8");
			os.write(payload,0,payload.length);
			
			int code=con.getResponseCode();
			
			System.out.println("code: "+code);
			
			reader= new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));
			
			while((line=reader.readLine())!=null) {
				lines.append(line.trim());
				lines.append(System.lineSeparator());
			}
			
			System.out.println("response["+lines.toString()+"]");
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(os !=null) {
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(reader !=null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static String getNowDateTime() {
	   	Date today= new Date();
	    
	    SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	    String formattedDate = sdf.format(today);
	    return formattedDate;
	}
	public static JSONObject generateDummyJson() {
		JSONObject reqData=new JSONObject();
		JSONObject headerData=new JSONObject();

		JSONArray postList=new JSONArray();
		JSONObject postData=new JSONObject();
	
		
		String dateTime=getNowDateTime();
		
		String msgId=dateTime.replaceAll("\\D", "");
		System.out.println("msgId="+msgId);
		
		reqData.put("service_cd", "99999");//서비스코드 
		reqData.put("service_key", "aWVSSXwh2z");//서비스 코드 인증키
		reqData.put("req_type", "1");//발송요청구분, 1:즉시, 2:배치비승인, 3:배승 승인
		
		// common Data Setting
		headerData.put("biz_cd", "99999");//기관코드
		headerData.put("msg_cd", "00000");//문서코드
		headerData.put("make_dt", msgId);//생성일시 14자리
		headerData.put("send_seq", "0000");//발송회차, 0000 즉발
		headerData.put("data_cnt", 1);//발송회차 전체 데이터 건수
		
		//rcs Data Setting
		postData.put("src_key", "99999"+"00000"+msgId+"0001");//관리키
		postData.put("d_birth", "930302");
		postData.put("jumin_first_no", "2");
		postData.put("c_zip", "12345");
		postData.put("addr", "주소");
		postData.put("rcv_tel_no", "01056543970");
		postData.put("snd_tel_no", "15776825");
		postData.put("rcv_pwd_use_yn", "Y");
		postData.put("sci", "Deqmdr424oap795zJEhELZ+VHCIuxnBxLAolGOozxEmjXunyM+GiRI7tfqcBZ8C7w7GzD+qfWL8ZIiVgE0IsRg==");
		postData.put("mms_dtl_cnts", "Mobile Post Test");
		postData.put("mms_title", "Post Test");
		postData.put("rcv_type", "1");
		
		postList.add(postData);
		
		reqData.put("hdr", headerData);
		reqData.put("reqs", postList);
		
		
		return reqData;
	}
}
