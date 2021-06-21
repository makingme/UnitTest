/**
 * 
 */
package com.file;

import java.io.File;

/**

  * @FileName : FileDiskSizeCheck.java

  * @Project : UnitTest

  * @Date : 2021. 4. 27. 

  * @�ۼ��� : kibumkim

  * @�����̷� :

  * @���α׷� ���� :

  */
public class FileDiskSizeCheck {
	public static long getFileSize(File file) {
		long totalSize=0;
		if(file ==null) return 0;
		if (file.isDirectory()) {
			if(file.listFiles() == null) return file.length();
			for (File f : file.listFiles()) {
				//System.out.println(f.getName());
				totalSize+=getFileSize(f);
			}
		}else if(file.isFile()) {
			return file.length();
		}else {
			return 0;
		}
		return totalSize;
	}
	
	public static void main(String[] args) {
		File root=new File("C:\\Program Files (x86)");
		int fileCnt=0;
		int dirCnt=0;
		long totalSize=0;
		for(File f: root.listFiles()) {
			totalSize=totalSize + f.length();
			if (f.isDirectory()) {
				dirCnt++;
				System.out.println(f.getName()+"(���丮): "+ f.length() );	
			} else {
				fileCnt++;
				System.out.println(f.getName()+"(����): "+ f.length() );
			}
			
		}
		System.out.println("���丮 �� ���� : "+ dirCnt);
		System.out.println("���� �� ���� : "+ fileCnt);
		System.out.println("�� ũ�� : "+ totalSize/(1000*1000*1000));
		System.out.println("==================================");
		

		System.out.println("�׽�Ʈ : " + ((double)FileDiskSizeCheck.getFileSize(root) / (1000*1000*1000)));
		
	
	}
}
