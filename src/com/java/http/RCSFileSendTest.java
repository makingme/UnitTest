/**
 * 
 */
package com.java.http;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import sun.misc.BASE64Encoder;

/**

  * @FileName : RCSFileSendTest.java

  * @Project : UnitTest

  * @Date : 2020. 6. 30. 

  * @작성자 : kibumkim

  * @변경이력 :

  * @프로그램 설명 :

  */
public class RCSFileSendTest extends SimpleHttpRequest{
	
	private final static String BRANDID="BR.D93Lnzo1wL.";
	private String reqTokenURL="https://agency-stg.hermes.kt.com/corp/v1/token";
	private String reqSndURL="https://agency-stg.hermes.kt.com/corp/v1/file";
	
	private OutputStream os=null;
	private BufferedReader reader=null;
	private URL url =null;
	
	public static void main(String[] args) {
		RCSFileSendTest sender=new RCSFileSendTest();
		List<File> fileList =new ArrayList<File>();
		try {
			if(args.length<1) {
				System.out.println("Input Send File Name in images DIR");
				System.exit(0);
			}
			//전송 파일 확인
			String m1images = System.getenv("M1_IMAGE") ;
			String imageFile =m1images + File.separator +  args[0] ;
			File uploadFile=new File(imageFile);
			if(!uploadFile.exists()) {
				System.out.println("전송파일 없음으로 인한 강제 종료");
				System.exit(0);
			}
			fileList.add(uploadFile);
			//토큰 요청
			sender.url =new URL(sender.reqTokenURL);
			
			sender.reqHeaderMap.put("Content-Type", "application/json; utf-8");
			sender.reqHeaderMap.put("Accept", "application/json, text/plain, */*");
			
			sender.reqBodyMap.put("rcsId", "KT_ODINUE");
			sender.reqBodyMap.put("rcsSecret", "$2a$10$dSh7n0SIvYebR57CgyGomupQX.V.PPGwOodIZBqxzbgQ14iiGZwrK");
			sender.reqBodyMap.put("grantType", "clientCredentials");
			
			JSONObject reqJsonData= sender.getSimpleJsonReqData(sender.reqBodyMap);
			
			String resposeData=sender.doRequest(sender.url, sender.reqHeaderMap, reqJsonData.toString(), sender.os, sender.reader, "POST");
			
			JSONObject resJsonData=(JSONObject)new JSONParser().parse(resposeData);
			resJsonData=(JSONObject)resJsonData.get("data");
			resJsonData=(JSONObject)resJsonData.get("tokenInfo");
			
			String accessToken=(String)resJsonData.get("accessToken");
			
			System.out.println("AccessToken:"+accessToken);
			
			//file 전송			
			sender.url =new URL(sender.reqSndURL);
			
			sender.reqHeaderMap.put("Content-Type", "multipart/form-data; boundary="+sender.getBoundary());
			sender.reqHeaderMap.put("Accept", "application/json");
			sender.reqHeaderMap.put("Authorization", "Bearer "+accessToken);
			
			String fileId=BRANDID+SimpleHttpRequest.getNowDateTime("yyyy-MM-dd'T'HH:mm:ss.SSS");
			System.out.println("File ID="+fileId);
			sender.reqBodyMap.put("fileId", fileId);
			sender.reqBodyMap.put("usageType", "send");
			sender.reqBodyMap.put("usageService", "RCS");
			
			String fileExt=uploadFile.getName().substring(uploadFile.getName().lastIndexOf(".")+1);
			sender.reqBodyMap.put("mimeType", "image/"+fileExt);
			sender.reqBodyMap.put("description", "test");
			
			
			resposeData=sender.doRequestMulti(sender.url, sender.reqHeaderMap, sender.reqBodyMap, fileList, sender.os, sender.reader, "POST");
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
	private String getFileToBase64String() {
		BufferedImage bImage=null;
		ByteArrayOutputStream bos=null;
		String s_en="";
		try {
			bImage = ImageIO.read(new File("yes.png"));
			bos=new ByteArrayOutputStream();
			ImageIO.write(bImage, "png", bos);
			byte[] data=bos.toByteArray();
			BASE64Encoder encoder =new BASE64Encoder();
			s_en=encoder.encode(data);
			//s_en=DatatypeConverter.printBase64Binary(data);
			
			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return s_en;
	}
	
	private byte[] getFileToByteArray() {
		BufferedImage bImage=null;
		ByteArrayOutputStream bos=null;
		byte[] data=null;
		try {
			bImage = ImageIO.read(new File("yes.png"));
			bos=new ByteArrayOutputStream();
			ImageIO.write(bImage, "png", bos);
			data=bos.toByteArray();
			//s_en=DatatypeConverter.printBase64Binary(data);
			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return data;
	}

	@Override
	public JSONObject generateDummyJson() throws ParseException {
		JSONObject _reqJsonData=new JSONObject();
		String fileId="BR.D93Lnzo1wL."+this.getNowDateTime("yyyy-MM-dd'T'HH:mm:ss.SSS");

		_reqJsonData.put("fileId", fileId);
		_reqJsonData.put("usageType", "send");
		_reqJsonData.put("usageService", "RCS");
		_reqJsonData.put("mimeType", "image/png");
		_reqJsonData.put("file", getFileToBase64String());
		_reqJsonData.put("description", "test");
		return _reqJsonData;
	}

	@Override
	public JSONObject generateDummyJson(String v1) throws ParseException {

		return null;
	}

}
