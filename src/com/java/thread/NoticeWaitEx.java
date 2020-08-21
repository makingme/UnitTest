/**
 * 
 */
package com.java.thread;

/**

  * @FileName : NoticeWaitEx.java

  * @Project : UnitTest

  * @Date : 2020. 8. 19. 

  * @작성자 : kibumkim

  * @변경이력 :

  * @프로그램 설명 :

  */
public class NoticeWaitEx {
	public static void main(String[] args) {
		NoticeWaitEx n=new NoticeWaitEx();
		
		Worker w=n.new Worker();
		w.start();
	}
	
	class Worker extends Thread{
		public void run() {
			while(true) {
				System.out.println(this.getName()+": check.");
				try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	}
}
