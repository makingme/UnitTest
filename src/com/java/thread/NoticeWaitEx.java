/**
 * 
 */
package com.java.thread;

/**

  * @FileName : NoticeWaitEx.java

  * @Project : UnitTest

  * @Date : 2020. 8. 19. 

  * @�ۼ��� : kibumkim

  * @�����̷� :

  * @���α׷� ���� :

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
