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
		long t=1594968241+100000000;
			
		long t2=(System.currentTimeMillis()/1000)+200000000;
				System.out.println(t2);
		long t3=1598943192360l;
		Date date=new Date();
		date.setTime(t3);
		//System.out.println(t2*2);
		 SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

		 String formattedDate = sdf.format(date);
		 System.out.println(formattedDate);
	}

}
