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

  * @�ۼ��� : kibumkim

  * @�����̷� :

  * @���α׷� ���� :

  */
public class MapEx {
	public static void main(String[] args) {
		Map<Integer, String> a =new HashMap<Integer, String>();
		a.put(1, "2");
		System.out.println("����"+(a.get(1)==null?"������":a.get(1)));
	}
}
