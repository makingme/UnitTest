package com.java.thread;

public class SyncronizeTest {

	static int val=0;
	public SyncronizeTest() {
		
	}
	
	public synchronized static int valCN() {
		val +=1;
		return val;
	}
	public static void main(String[] args) {
		ThreadWorker w1=new ThreadWorker();
		ThreadWorker w2=new ThreadWorker();
		ThreadWorker w3=new ThreadWorker();
	/*	w1.setName("W1");
		w2.setName("W2");
		w3.setName("W3");*/
		w1.start();
		w2.start();
		w3.start();
	}
}


class ThreadWorker extends Thread{
	
	@Override
	public void run() {
		for(int i=1; i<=30000; i++) {
			int a=SyncronizeTest.valCN();
			System.out.println(a);
		}
	}
	
	
}