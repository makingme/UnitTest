/**
 * 
 */
package com.java.thread;

import java.util.concurrent.ConcurrentHashMap;

/**

  * @FileName : ThreadCtrlEx.java

  * @Project : UnitTest

  * @Date : 2020. 8. 27. 

  * @작성자 : kibumkim

  * @변경이력 :

  * @프로그램 설명 :

  */
public class ThreadCtrlEx extends Thread{
	ConcurrentHashMap<Worker, Integer> workerMap =new ConcurrentHashMap<Worker, Integer>(5);
	
	public void init() {
		
		for(int i=0; i<5; i++) {
			Worker aw=new Worker(i, this);
			aw.setName(i+"th "+aw.getClass().getSimpleName());
			aw.start();
			System.out.println(aw.getName()+", start!!");
			workerMap.put(aw, i);
		}
		
	}
	
	public void stopAll() {
		for(Worker w: workerMap.keySet()){
			w.stopThread();
			workerMap.remove(w);
			try {
				w.join(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				this.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	class Worker extends Thread{
		
		ThreadCtrlEx tc=null;
		
		boolean isStop = false;
		int tNum=0;
		
		/**
		 * @param isStop
		 */
		public Worker(int i, ThreadCtrlEx tc) {
			tNum=i;
			this.tc=tc;
		}
		
		public boolean stopThread(){
			System.out.println(this.getName()+" call stopThread()");
			isStop=true;
			return isStop;
		}
		
		@Override
		public void run() {
			int c=0;
			while(!isStop) {
				System.out.println(tNum+" th, "+c);
				if(c == 10 && tNum == 3) {
					System.out.println("Stop Sinal");
					tc.stopAll();
				}
				c++;
				
				try {
					this.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	

	public static void main(String[] args) {
		ThreadCtrlEx ex =new ThreadCtrlEx();
		ex.init();
		ex.start();
	}
}
