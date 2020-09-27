/**
 * 
 */
package com.java.object;

import java.util.LinkedHashMap;
import java.util.Map;

/**

  * @FileName : StaticClass.java

  * @Project : UnitTest

  * @Date : 2020. 9. 18. 

  * @작성자 : kibumkim

  * @변경이력 :

  * @프로그램 설명 :

  */
public class StaticClass {
	public static void main(String[] args) {
		AA a=new AA();
		AA b=new AA();
		a.fillMap();
		a.getMap();
		b.fillMap();
		a.clearMap();
		b.getMap();
		
		
	}
	static class AA{
		Map map = new LinkedHashMap() ;
		String a="1";
		int b=1;
		Integer c=new Integer(1);
		void clearMap() {
			map.clear();
		}
		void fillMap() {
			map.put("1", "1");
			map.put("2", "2");
			map.put("3", "3");
			
		}
		void getMap() {
			System.out.println(map.get("1"));
			System.out.println(map.get("2"));
			System.out.println(map.get("3"));
		}
		void printValue() {
			System.out.println(a);
			System.out.println(b);
			System.out.println(c);
		}
		void changeValue() {
			a="2";
			b=2;
			c=new Integer(2);
		}
	}
}
