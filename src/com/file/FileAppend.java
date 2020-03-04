package com.file;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileAppend {

	public FileAppend() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		String str = "a";  
	    byte data[] = str.getBytes();       

	    try {                           
	            RandomAccessFile f = new RandomAccessFile(new File("A:\\a.txt"), "rw");
	            f.seek(0);       
	            f.write(data);
	            f.close();
	    } catch (IOException e) {       
	            e.printStackTrace();
	    }
	}
}
