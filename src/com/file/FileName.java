package com.file;

import java.io.File;

public class FileName {

	public FileName() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		File f=new File("A:\\temp\\yes.png");
		
		String boundary = Long.toHexString(System.currentTimeMillis());
		System.out.println(boundary);
		if(f.exists()) {
			String fName=f.getName();
			System.out.println(true);
			System.out.println(fName);
			System.out.println(fName.indexOf("."));
			String fileExt=f.getName().substring(f.getName().lastIndexOf(".")+1);
			System.out.println(fileExt);
			System.out.println();
		}else {
			System.out.println(f.getName());
		}
	}
}
