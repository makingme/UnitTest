/**
 * 
 */
package com.java.extend;

/**

  * @FileName : Child1.java

  * @Project : UnitTest

  * @Date : 2021. 2. 5. 

  * @�ۼ��� : kibumkim

  * @�����̷� :

  * @���α׷� ���� :

  */
public class Child1 extends Parents1{
	static String m1vela = "B";
	/* (non-Javadoc)
	 * @see com.java.extend.Parents1#displayC()
	 */
	public void Child1(){
		super.m1vela="adfadb";
	}
	@Override
	public void displayC() {
		// TODO Auto-generated method stub
		System.out.println("override");
		
	}

	public void displayC(String a) {
		System.out.println(a);
	}
	
	public void displayD() {
		System.out.println(getVela());
	}
	/* (non-Javadoc)
	 * @see com.java.extend.Parents1#getVela()
	 */
	@Override
	public String getVela() {
		return super.getVela();
	}
	
}
