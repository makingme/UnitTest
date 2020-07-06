/**
 * 
 */
package com.java.basic;

import java.util.ArrayList;
import java.util.List;

/**

  * @FileName : LoopForeach.java

  * @Project : UnitTest

  * @Date : 2020. 6. 30. 

  * @작성자 : kibumkim

  * @변경이력 :

  * @프로그램 설명 :

  */
public class LoopForeach {
	public static void main(String[] args) {
		List<String> list=new ArrayList<String>();
		list=null;
		for (String string : list) {
			System.out.println(string);
		}
		
		System.out.println("---");
	}
}
