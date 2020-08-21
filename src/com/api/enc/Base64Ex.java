package com.api.enc;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import org.json.simple.JSONObject;

import sun.misc.*;

public class Base64Ex {
	public static void main(String[] args) throws IOException {
		String auth="APITEST0000 APITEST0000";
		String test="dddddd";
		JSONObject _reqJsonData=new JSONObject();
		_reqJsonData.put("alg", "HS256");
		
		String s_entt=Base64.getEncoder().encodeToString(_reqJsonData.toString().getBytes("UTF-8"));
		System.out.println(s_entt);
		
		String s_entt2=Base64.getEncoder().encodeToString(_reqJsonData.toString().getBytes("EUC-KR"));
		System.out.println(s_entt2);
		//java 8 Base64
		String s_en=Base64.getEncoder().encodeToString(auth.getBytes());
		//String s_en="QVBJVEVTVDAwMDA6QVBJVEVTVDAwMDA=";
		//String s_en1=new BASE64Encoder().encode(auth.getBytes());
		
		//JAVA6 xml DatatypeConverter
		String s_en2=DatatypeConverter.printBase64Binary(auth.getBytes());
		System.out.println(s_en);
		//System.out.println(s_en1);
		System.out.println(s_en2);
		String s_de=new String(Base64.getDecoder().decode(s_en));
		System.out.println(s_de);
		
		//DatatypeConverter.parseBase64Binary(lexicalXSDBase64Binary)
		String c_s=DatatypeConverter.printBase64Binary(auth.getBytes());
		System.out.println(c_s);
		
		//java6 sun.misc BASE64Encoder
		BASE64Encoder encoder =new BASE64Encoder();
		
		System.out.println(encoder.encode(auth.getBytes()));
		
		System.out.println("@@!!"+encoder.encode(_reqJsonData.toString().getBytes("UTF-8")));
		System.out.println("@@!!"+encoder.encode(_reqJsonData.toString().getBytes("EUC-KR")));
		
		
		
		BufferedImage bImage=null;
		ByteArrayOutputStream bos=null;
		String s_en3="";
		
		bImage = ImageIO.read(new File("A:\\temp\\yes.png"));
		bos=new ByteArrayOutputStream();
		ImageIO.write(bImage, "png", bos);
		byte[] data=bos.toByteArray();
		s_en3=DatatypeConverter.printBase64Binary(data);
		System.out.println(Base64.getEncoder().encodeToString(data));
		System.out.println("-----------------------------------------------------------------");
		System.out.println(DatatypeConverter.printBase64Binary(data));
		System.out.println("-----------------------------------------------------------------");
		System.out.println(encoder.encode(data));
		System.out.println("-----------------------------------------------------------------");
		bos.close();
	}
		
}