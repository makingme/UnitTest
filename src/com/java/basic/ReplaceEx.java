/**
 * 
 */
package com.java.basic;

/**

  * @FileName : ReplaceEx.java

  * @Project : UnitTest

  * @Date : 2020. 9. 7. 

  * @작성자 : kibumkim

  * @변경이력 :

  * @프로그램 설명 :

  */
public class ReplaceEx {
	public static void main(String[] args) {
		String a="\\/";
		System.out.println(a.replaceAll("\\\\/", "/"));
	}
}
