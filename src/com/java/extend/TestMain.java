/**
 * 
 */
package com.java.extend;

/**

  * @FileName : TestMain.java

  * @Project : UnitTest

  * @Date : 2021. 2. 5. 

  * @작성자 : kibumkim

  * @변경이력 :

  * @프로그램 설명 :

  */
public class TestMain {
	public static void main(String[] args) {
		Child1 c=new Child1();
		Parents1 p=new Parents1();
		Parents1 p2=new Child1();
		c.displayA();
		c.displayC("aaa");
		c.displayC();
		p.displayC();
		p2.displayC();
		c.displayD();
	}
}
