/**
 * 
 */
package com.java.casting;

import java.text.DecimalFormat;

/**

  * @FileName : StringCasting.java

  * @Project : UnitTest

  * @Date : 2021. 4. 13. 

  * @�ۼ��� : kibumkim

  * @�����̷� :

  * @���α׷� ���� :

  */
public class StringCasting {
public static void main(String[] args) {
	double data= 1000.5;
	String c=String.valueOf(data).trim();
	System.out.println(c);
	
	double data1= 1000.5;
	DecimalFormat df=new DecimalFormat("#.##");
	System.out.println(df.format(data1));

	
}
}
