package com.string;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringFomatter {

	public StringFomatter() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		
		int yyyymmdd=20180419;
		System.out.println(String.format("%04d",100000));
		for(int d=1;d<=2;d++) {
			
			for(int h=0;h<=23;h++) {
				String sh=String.format("%02d",h);
				for(int m=0;m<=59;m++) {
					String sm=String.format("%02d",m)+"00";
					for(int i=1;i<=1;i++) {
						//System.out.println(""+yyyymmdd+sh+sm);
					}
					
				}
				
			}
			
			yyyymmdd+=d;
		}
		
		System.out.println(String.format("H%6s", new SimpleDateFormat("yyyyMMdd").format(new Date())));
		
		
		/*for(int i=1;i<=1;i++) {
			System.out.println(""+yyyymmdd+String.format("%06d",Integer.parseInt(sh)));
		}*/
		//System.out.println(String.format("%06d", 10000));
	}
}
