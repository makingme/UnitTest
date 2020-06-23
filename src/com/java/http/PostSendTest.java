/**
 * 
 */
package com.java.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**

  * @FileName : PostSendTest.java

  * @Project : UnitTest

  * @Date : 2020. 6. 16. 

  * @작성자 : kibumkim

  * @변경이력 :

  * @프로그램 설명 : 모바일 통지 단순 발송

  */
public class PostSendTest extends SimpleHttpRequest{
	
	private String reqSndURL="http://222.111.214.31:40001/api/mns/req";
	
	private static String[] phones= {"01056543970","01043206010","01026313590"};
	
	private String accessToken="aa413f01-d523-4c99-9198-5220a881947f";
	
	private OutputStream os=null;
	private BufferedReader reader=null;
	private URL url =null;
	
	public static void main(String[] args) {
		PostSendTest sender=new PostSendTest();
		boolean isValid =true;
	
		String rcvNum=null;
		if(args.length>0) {
			rcvNum=args[0];
			isValid=sender.checkReceivers(rcvNum);
			
		}
		
		if(!isValid) {
			System.out.println("미등록 전화번호입력으로 인한 종료\n 등록현황[01056543970, 01043206010, 01026313590]");
		}
		
		try {
		
			//단건  전송
			sender.url =new URL(sender.reqSndURL);
			String payload= (rcvNum==null)?sender.generateDummyJson().toString():sender.generateDummyJson(rcvNum).toString();
			
			sender.reqHeaderMap.put("Content-Type", "application/json; utf-8");
			sender.reqHeaderMap.put("Content-Length", ""+payload.getBytes().length);
			sender.reqHeaderMap.put("Accept", "*/*");
			sender.reqHeaderMap.put("Authorization", "Bearer "+sender.accessToken);
			
			sender.doRequest(sender.url, sender.reqHeaderMap, payload, sender.os, sender.reader, "POST");
			
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
		JSONObject _reqJsonData=new JSONObject();
		JSONObject _headerData=new JSONObject();
		JSONArray _postList=new JSONArray();
		JSONObject _postData=new JSONObject();
	
		String _dateTime=getNowDateTime();
		String _msgId=_dateTime.replaceAll("\\D", "");
		
		_reqJsonData.put("service_cd", "APTST");//서비스코드 
		_reqJsonData.put("service_key", "ybik2D53JR");//서비스 코드 인증키
		_reqJsonData.put("req_type", "1");//발송요청구분, 1:즉시, 2:배치비승인, 3:배승 승인
		
		// common Data Setting
		_headerData.put("biz_cd", "APTST");//기관코드
		_headerData.put("msg_cd", "00000");//문서코드
		_headerData.put("make_dt", _msgId);//생성일시 14자리
		_headerData.put("send_seq", "0000");//발송회차, 0000 즉발
		_headerData.put("data_cnt", 1);//발송회차 전체 데이터 건수
		
		//rcs Data Setting
		_postData.put("src_key", "99999"+"00000"+_msgId+"0001");//관리키
		_postData.put("d_birth", "930302");
		_postData.put("jumin_first_no", "2");
		_postData.put("c_zip", "12345");
		_postData.put("addr", "주소");
		_postData.put("rcv_tel_no", "01056543970");
		_postData.put("snd_tel_no", "15776825");
		_postData.put("rcv_pwd_use_yn", "Y");
		_postData.put("sci", "Deqmdr424oap795zJEhELZ+VHCIuxnBxLAolGOozxEmjXunyM+GiRI7tfqcBZ8C7w7GzD+qfWL8ZIiVgE0IsRg==");
		_postData.put("mms_dtl_cnts", "Mobile Post Test");
		_postData.put("mms_title", "Post Test");
		_postData.put("rcv_type", "1");
		
		_postList.add(_postData);
		
		_reqJsonData.put("hdr", _headerData);
		_reqJsonData.put("reqs", _postList);
		
		return _reqJsonData;
	}

	/* (non-Javadoc)
	 * @see com.java.http.SimpleHttpRequest#generateDummyJson(java.lang.String)
	 */
	@Override
	public JSONObject generateDummyJson(String v1) {
		JSONObject _reqJsonData=this.generateDummyJson();
		JSONArray _postList=(JSONArray)_reqJsonData.get("reqs");
		JSONArray _newPostList=new JSONArray();
		String sci="";
		if(v1.equals("01056543970")) {
			sci="Deqmdr424oap795zJEhELZ+VHCIuxnBxLAolGOozxEmjXunyM+GiRI7tfqcBZ8C7w7GzD+qfWL8ZIiVgE0IsRg==";
		}else {
			sci="MyrU2iUIYi4pNuXtAm261NJUhzaVlgNmKPnf3o/XEOY321PLA94qwQ8dDXt2x7/7AcaE2J0wv8p+oeFIWYlhJQ==";
		}
			
		for (Object object : _postList) {
			JSONObject _post=(JSONObject)object;
			_post.put("rcv_tel_no", v1);
			_post.put("sci", sci);
			_newPostList.add(_post);
		}
		_reqJsonData.put("reqs", _newPostList);
		return _reqJsonData;
	}

}
