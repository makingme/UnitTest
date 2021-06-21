/**
 * 
 */
package com.java.basic;

/**

  * @FileName : HexStringEx.java

  * @Project : UnitTest

  * @Date : 2020. 11. 17. 

  * @작성자 : kibumkim

  * @변경이력 :

  * @프로그램 설명 :

  */
public class HexStringEx {
	public static void main(String[] args) {
		String data = Long.toHexString(System.currentTimeMillis());
		System.out.println(data);
		
	}

}
