/**
 * 
 */
package com.string;

/**

  * @FileName : SubStringEx3.java

  * @Project : UnitTest

  * @Date : 2020. 11. 26. 

  * @작성자 : kibumkim

  * @변경이력 :

  * @프로그램 설명 :

  */
public class SubStringEx3 {
	public static void main(String[] args) {
		String data="0123456789";
		int pos=6;
		int endPos=0;
		//System.out.println(data.substring(0,data.length()-3));
		if(data.length()<(pos+3)) {
			endPos=data.length();
		}else {
			endPos=pos+3;
		}
		System.out.println(data.substring(pos,endPos));
	}
}
