/**
 * 
 */
package com.java.casting;

/**

  * @FileName : ShiftCastEx.java

  * @Project : UnitTest

  * @Date : 2020. 9. 6. 

  * @�ۼ��� : kibumkim

  * @�����̷� :

  * @���α׷� ���� :

  */
public class ShiftCastEx {
	public static void main(String[] args) {
		long k = 4;
		int d =0xfe;  // 0x0f | 0xe0 >> 0xef
		int d2= (  (d >> k) | (  d << (8-k)) )  ;
		System.out.println( String.format("0x%x ( 0x%x  , 0x%x   ) --> 0x%x", d,  d >>> k ,(  d << (8-k)), (byte)d2));
		if(true) return ;
	}

}
