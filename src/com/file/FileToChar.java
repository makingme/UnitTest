package com.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class FileToChar {

	public FileToChar() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) throws IOException {
		File f=new File("A:/jar_repository/aspose-barcode-18.7-java/file/"+ "all.txt");
		FileReader fReader=new FileReader(f);
		BufferedReader bReader=new BufferedReader(fReader);
		char[] cbuf=new char[3400];
		int readSize=-1;
		StringBuilder sb=new StringBuilder();
		String line=null;
		String newline=System.getProperty("line.separator");
		int size=0;
		while((line=bReader.readLine())!=null) {
			//System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			System.out.println(line);
			size+=line.length();
			sb.append(line+"\n");
			//sb.append(new String(cbuf, 0, readSize));
			line=null;
			//System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		}
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println(sb.toString());
		if(size!=sb.toString().length()) {
			System.out.println(size);
			System.out.println(sb.toString().length());
			System.out.println("!!!!!!!!!!!!!!!");
		}
		
	    File file = new File("A:/jar_repository/aspose-barcode-18.7-java/file/"+ "rewrite.txt");

	    try {
	      //파일에 문자열을 쓴다.
	      //하지만 이미 파일이 존재하면 모든 내용을 삭제하고 그위에 덮어쓴다
	      //파일이 손산될 우려가 있다.
	      FileWriter fw = new FileWriter(file);
	      fw.write(sb.toString());
	      fw.close();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
		
		
		
		
	}
}
