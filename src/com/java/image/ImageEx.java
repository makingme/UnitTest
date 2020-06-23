/**
 * 
 */
package com.java.image;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**

  * @FileName : ImageEx.java

  * @Project : UnitTest

  * @Date : 2020. 6. 22. 

  * @작성자 : kibumkim

  * @변경이력 :

  * @프로그램 설명 :

  */
public class ImageEx {
	public static void main(String[] args) throws IOException {
		BufferedImage bImage = ImageIO.read(new File("A:\\temp\\yes.png"));
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		ImageIO.write(bImage, "png", bos);
		byte[] data=bos.toByteArray();
		System.out.println(new String(data));
	}
}
