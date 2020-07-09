/**
 * 
 */
package com.java.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**

  * @FileName : DateAddEx.java

  * @Project : UnitTest

  * @Date : 2020. 7. 9. 

  * @작성자 : kibumkim

  * @변경이력 :

  * @프로그램 설명 :

  */
public class DateAddEx {
	public static void main(String[] args) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		Date nowDate=new Date();
		System.out.println(dateFormat.format(nowDate));
		
		Calendar c =Calendar.getInstance();
		c.setTime(nowDate);
		
		c.add(Calendar.YEAR, -1);
		c.add(Calendar.MONTH, -1);
		c.add(Calendar.DATE, -1);
		c.add(Calendar.HOUR, -1);
		c.add(Calendar.MINUTE, -1);
		c.add(Calendar.SECOND, -1);
		
		Date newDate=c.getTime();
		System.out.println(dateFormat.format(newDate));
	}

}
