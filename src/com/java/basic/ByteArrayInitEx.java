/**
 * 
 */
package com.java.basic;

import java.util.Arrays;

/**

  * @FileName : ByteArrayInitEx.java

  * @Project : UnitTest

  * @Date : 2020. 10. 22. 

  * @�ۼ��� : kibumkim

  * @�����̷� :

  * @���α׷� ���� :

  */
public class ByteArrayInitEx {
	public static void main(String[] args) {
		byte[] seq = new byte[0];
		Arrays.fill(seq, (byte)' ') ;
		System.out.println("aa");
		System.out.println(seq.length);
	}
}
