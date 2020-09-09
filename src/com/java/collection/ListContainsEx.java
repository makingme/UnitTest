/**
 * 
 */
package com.java.collection;

import java.util.ArrayList;
import java.util.List;

/**

  * @FileName : ListContainsEx.java

  * @Project : UnitTest

  * @Date : 2020. 9. 6. 

  * @작성자 : kibumkim

  * @변경이력 :

  * @프로그램 설명 :

  */
public class ListContainsEx {
	public static void main(String[] args) {
		List<String> a= new ArrayList<String> ();
		a.add("1");
		a.add("2");
		a.add("3");
		if(a.contains(String.valueOf(1))) {
			System.out.println("존재");
		}else {
			System.out.println("미존재");
		}
	}
}
