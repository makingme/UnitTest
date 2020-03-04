package com.java.loop;


public class ForLoopExam {

	public ForLoopExam() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		
		int rows=22;
		
		for(int r=1;r<rows;r++) {
			if(rows <= 20  || (  r <= 10 || (rows -r <= 10 ))) { 
				System.out.println(r);
			} 
		}
		
		
/*		for(int i=1;i<=10;i++) {
			System.out.println(i);
			if(i==5) {
				break;
			}
		}*/
	}
}
