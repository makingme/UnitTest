package com.file;

import java.io.File;

public class FileName {

	public FileName() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		File f=new File("A:/sms_general_sample11999.xls");
		if(f.exists()) {
			System.out.println(true);
			System.out.println(f.getName());
			
		}else {
			System.out.println(f.getName());
		}
	}
}
