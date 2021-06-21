/**
 * 
 */
package com.java.basic;

import java.util.HashMap;
import java.util.Map;

/**

  * @FileName : MapEx.java

  * @Project : UnitTest

  * @Date : 2020. 12. 11. 

  * @작성자 : kibumkim

  * @변경이력 :

  * @프로그램 설명 :

  */
public class MapEx {
	public static void main(String[] args) {
		Map<Integer, String> a =new HashMap<Integer, String>();
		a.put(1, "2");
		System.out.println("상태"+(a.get(1)==null?"미지정":a.get(1)));
	}
}
