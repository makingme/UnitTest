/**
 * 
 */
package com.java.collection;

/**

  * @FileName : ByteArrayEx.java

  * @Project : UnitTest

  * @Date : 2020. 9. 12. 

  * @작성자 : kibumkim

  * @변경이력 :

  * @프로그램 설명 :

  */
public class ByteArrayEx {
	public static void main(String[] args) {
		byte[] source = "abcd".getBytes();
		byte[] dataRslt = new byte[1024*10];
		byte[] zerobyte = new byte[0];
		System.out.println(dataRslt.length);
		System.out.println(new String(dataRslt));
		System.arraycopy(source, 0, dataRslt, 0, source.length);
		System.out.println(dataRslt.length);
		System.out.println(new String(dataRslt));
		
		System.out.println(zerobyte.length);
	}
}
