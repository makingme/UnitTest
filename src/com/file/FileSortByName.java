package com.file;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class FileSortByName {

	public FileSortByName() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		File[] fileList =new File(args[0]).listFiles();
		File[] qrFileList=new File[fileList.length];
		int c=0;
		for(int i=0;i<fileList.length;i++) {
			System.out.println("@@@@@@@@@@@@");
			System.out.println(fileList[i].getName());
			if(fileList[i].getName().toUpperCase().endsWith(".PNG")) {
				qrFileList[c]=fileList[i];
				c+=1;
			}
			System.out.println("@@@@@@@@@@@@");
		}
		Arrays.sort(qrFileList, new Comparator<Object>()
	       {
	        @Override
	        public int compare(Object object1, Object object2) {
	         
	         String s1 = "";
	         String s2 = "";
     
	          s1 = ((File)object1).getName();
	          s2 = ((File)object2).getName();
	     
	         
	         
	         return s1.compareTo(s2);

	        }
	    });

		for(int i=0;i<qrFileList.length;i++) {
			System.out.println(qrFileList[i].getName());
		}


		
	}
}
