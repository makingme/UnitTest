/**
 * 
 */
package com.json;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.json.simple.JSONObject;

/**

  * @FileName : SimpeJsonFileEx.java

  * @Project : UnitTest

  * @Date : 2020. 6. 30. 

  * @작성자 : kibumkim

  * @변경이력 :

  * @프로그램 설명 :

  */
public class SimpeJsonFileEx {
	public static void main(String[] args) throws Exception {
		BufferedImage bImage = ImageIO.read(new File("A:\\temp\\yes.png"));
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		ImageIO.write(bImage, "png", bos);
		byte[] data=bos.toByteArray();
		String s_en=Base64.getEncoder().encodeToString(data);
		System.out.println(s_en);
		System.out.println(URLEncoder.encode(new String(data)));
		//String s_de=new String(Base64.getDecoder().decode(data));
		
		String s_file=new String(data);
		//System.out.println(s_file);
		
		JSONObject file=new JSONObject();
		//file.put("file", s_en);
		file.put("file2", data);
		//System.out.println(file.get("file"));
		//System.out.println(file.get("file"));
		
		ByteArrayInputStream bis = new ByteArrayInputStream((byte[])file.get("file2"));
		BufferedImage bImage2 = ImageIO.read(bis);
	    ImageIO.write(bImage2, "png", new File("A:\\temp\\output.jpg") );
	    System.out.println(JSONObject.toJSONString(file));
	    
		
		
	}
}
