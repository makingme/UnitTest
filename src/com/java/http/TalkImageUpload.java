/**
 * 
 */
package com.java.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**

  * @FileName : TalkImageUpload.java

  * @Project : UnitTest

  * @Date : 2020. 7. 9. 

  * @�ۼ��� : kibumkim

  * @�����̷� :

  * @���α׷� ���� :

  */
public class TalkImageUpload extends SimpleHttpRequest{
	private String reqTokenURL="http://13.209.176.120/v1/oauth/token";
	private String reqSndURL=  "http://13.209.176.120/v1/message/uploadImage";////message/uploadImage message/mms/uploadFiles /message/wide/uploadImage
	
	private String authorization="APITEST0000 APITEST0000";
	
	private OutputStream os=null;
	private BufferedReader reader=null;
	private URL url =null;
	
	public static void main(String[] args) {
		System.setProperty("http.proxyHost", "127.0.0.1");
		System.setProperty("http.proxyPort", "8888");
		
		TalkImageUpload sender=new TalkImageUpload();
		List<File> fileList =new ArrayList<File>();
		
		try {
			if(args.length<1) {
				System.out.println("Input Send File Name in images DIR");
				System.exit(0);
			}
			//���� ���� Ȯ��
			String m1images = System.getenv("M1_IMAGE") ;
			int i=0;
			for (String f : args) {
				String imageFile =m1images + File.separator +  args[i] ;
				File uploadFile=new File(imageFile);
				if(!uploadFile.exists()) {
					System.out.println(i+"��°, "+imageFile+" �������� �������� ���� ���� ����");
					System.exit(0);
				}
				fileList.add(uploadFile);
				i++;
			}
			
			
			//��ū ���
			sender.url =new URL(sender.reqTokenURL);
			
			sender.reqHeaderMap.put("Content-Type", "application/x-www-form-urlencoded; utf-8");
			sender.reqHeaderMap.put("Accept", "application/json, text/plain, */*");
			sender.reqHeaderMap.put("Authorization", "Basic "+sender.authorization);
			
			sender.reqBodyMap.put("grant_type", "password");
			sender.reqBodyMap.put("username", "api-test@kakaocorp.com");
			sender.reqBodyMap.put("password", "APITEST0000");
			
			String reqFormData= sender.getSimpleFormReqData(sender.reqBodyMap, true);
			
			String resposeData=sender.doRequest(sender.url, sender.reqHeaderMap, reqFormData, sender.os, sender.reader, "POST");
			
			JSONObject resJsonData=(JSONObject)new JSONParser().parse(resposeData);			
			String accessToken=(String)resJsonData.get("access_token");
			
			System.out.println("AccessToken:"+accessToken);
			
			//�ٰ� ���� ����
			sender.url =new URL(sender.reqSndURL);
			sender.reqHeaderMap.put("Content-Type", "multipart/form-data; boundary="+sender.getBoundary());
			sender.reqHeaderMap.put("Accept", "*/*");
			sender.reqHeaderMap.put("Authorization", "Bearer "+accessToken);
			//sender.reqHeaderMap.put("Authorization", "Bearer accessToken");
			sender.reqBodyMap.clear();
			
			resposeData=sender.doRequestMulti(sender.url, sender.reqHeaderMap, sender.reqBodyMap, fileList, sender.os, sender.reader, "POST");
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public JSONObject generateDummyJson() {
		JSONObject _reqJsonData=new JSONObject();
		
		return _reqJsonData;
	}

	@Override
	public JSONObject generateDummyJson(String v1) {
		JSONObject _reqJsonData=this.generateDummyJson();
		_reqJsonData.put("phone_number", v1);

		return _reqJsonData;
	}

}
