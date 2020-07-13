/**
 * 
 */
package com.java.date;

/**

  * @FileName : SecToDayEx.java

  * @Project : UnitTest

  * @Date : 2020. 7. 13. 

  * @작성자 : kibumkim

  * @변경이력 :

  * @프로그램 설명 :

  */
public class SecToDayEx {
	public static void main(String[] args) {
		int totalSec=1595642443;
		
		int day = totalSec/(60*60*24);
		
		int hour = (totalSec- day*60*60*24)/(60*60);
		
		System.out.println(day+", "+hour);
	}
}
