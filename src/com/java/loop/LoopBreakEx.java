/**
 * 
 */
package com.java.loop;

/**

  * @FileName : LoopBreakEx.java

  * @Project : UnitTest

  * @Date : 2020. 8. 27. 

  * @작성자 : kibumkim

  * @변경이력 :

  * @프로그램 설명 :

  */
public class LoopBreakEx {
	public void BreakLoop() {
		
	}
	
	public static void main(String[] args) {
		
		boolean b=true;
		int i=0;
		while(b) {
			System.out.println(i);
			if(i==10) {
				b=false;
				continue;
			}
			i++;
			
		}
		
	}

}
