/**
 * 
 */
package com.java.basic;

import java.io.IOException;

/**

  * @FileName : RuntimeEx.java

  * @Project : UnitTest

  * @Date : 2020. 11. 17. 

  * @�ۼ��� : kibumkim

  * @�����̷� :

  * @���α׷� ���� :

  */
public class RuntimeEx {
	public static void main(String[] args) throws IOException {
		Runtime.getRuntime().exec( "calc.exe" );
	}
}
