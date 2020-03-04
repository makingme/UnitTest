package com.java.thread;

public class TestThread {

	public TestThread() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) throws InterruptedException {
		//Thread1 t1=new Thread1();
		Thread2 t2=new Thread2();
		
		//t1.start();
		
		t2.start();
	}

}

class Thread1 extends Thread{
	
	public Thread1() {

	}
	@Override
	public void run() {
		for(int i=1;i<=100;i++) {
			System.out.println("Thread One : "+i);
		}
	}
}

class Thread2 extends Thread{
	
	public Thread2() {

	}
	@Override
	public void run() {
		String i="1";
		String aa="";
		while(true) {
			aa=aa+i;
			System.out.println(i);
			System.out.println("Thread Two : "+aa);
			
		}
	}
}