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

  * @�ۼ��� : kibumkim

  * @�����̷� :

  * @���α׷� ���� :

  */
public class DateEx {
	public static void main(String[] args) {
	   	Date today= new Date();
	    
	    SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

	    String formattedDate = sdf.format(today);
	    /*String fileId="BR.D93Lnzo1wL."+formattedDate;
	    System.out.println(fileId.getBytes().length);*/
	    System.out.println(formattedDate);
	    
	}
}
