/**
 * 
 */
package com.java.casting;

import java.text.DecimalFormat;

/**

  * @FileName : StringCasting.java

  * @Project : UnitTest

  * @Date : 2021. 4. 13. 

  * @작성자 : kibumkim

  * @변경이력 :

  * @프로그램 설명 :

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
