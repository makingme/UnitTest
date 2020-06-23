/**
 * 
 */
package com.java.date;

import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**

  * @FileName : TimezoneEx.java

  * @Project : UnitTest

  * @Date : 2020. 6. 23. 

  * @작성자 : kibumkim

  * @변경이력 :

  * @프로그램 설명 :

  */
public class TimezoneEx {
	public static void main(String[] args) {
		System.out.println(TimeZone.getDefault().toString());
		Date d1=new Date();
		System.out.println(d1);
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		Date d2=new Date();
		System.out.println(d2);
		TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
		Date d3=new Date();
		System.out.println(d3);
		
	}
}
