/**
 * 
 */
package com.java.date;

import java.text.SimpleDateFormat;
import java.util.Date;

/**

  * @FileName : DateEx.java

  * @Project : UnitTest

  * @Date : 2020. 6. 23. 

  * @작성자 : kibumkim

  * @변경이력 :

  * @프로그램 설명 :

  */
public class DateEx {
	public static void main(String[] args) {
	   	Date today= new Date();
	    
	    SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

	    String formattedDate = sdf.format(today);
	    
	    System.out.println(formattedDate);
	}
}
