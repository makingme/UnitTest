/**
 * 
 */
package com.java.basic;

import java.io.IOException;

/**

  * @FileName : RuntimeEx.java

  * @Project : UnitTest

  * @Date : 2020. 11. 17. 

  * @작성자 : kibumkim

  * @변경이력 :

  * @프로그램 설명 :

  */
public class RuntimeEx {
	public static void main(String[] args) throws IOException {
		Runtime.getRuntime().exec( "calc.exe" );
	}
}
