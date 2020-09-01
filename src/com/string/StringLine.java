/**
 * 
 */
package com.string;

/**

  * @FileName : StringLine.java

  * @Project : UnitTest

  * @Date : 2020. 7. 29. 

  * @작성자 : kibumkim

  * @변경이력 :

  * @프로그램 설명 :

  */
public class StringLine {
	public static void main(String[] args) {
		String a="hellow\nworld\r\n!!";
		System.out.println(a);
		System.out.println(a.replaceAll("\r\n", "").replaceAll("\n", ""));
	}
}
