/**
 * 
 */
package com.java.date;

import java.text.SimpleDateFormat;
import java.util.Date;

/**

  * @FileName : MSecToDate.java

  * @Project : UnitTest

  * @Date : 2020. 7. 13. 

  * @작성자 : kibumkim

  * @변경이력 :

  * @프로그램 설명 :

  */
public class MSecToDate {
	public static void main(String[] args) {
		long t=1595642443;
		long t2=System.currentTimeMillis();
				
		Date date=new Date();
		date.setTime(t2*2);
		System.out.println(t2*2);
		 SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

		 String formattedDate = sdf.format(date);
		 System.out.println(formattedDate);
	}

}
