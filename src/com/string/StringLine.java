/**
 * 
 */
package com.string;

/**

  * @FileName : StringLine.java

  * @Project : UnitTest

  * @Date : 2020. 7. 29. 

  * @�ۼ��� : kibumkim

  * @�����̷� :

  * @���α׷� ���� :

  */
public class StringLine {
	public static void main(String[] args) {
		String a="hellow\nworld\r\n!!";
		System.out.println(a);
		System.out.println(a.replaceAll("\r\n", "").replaceAll("\n", ""));
	}
}
