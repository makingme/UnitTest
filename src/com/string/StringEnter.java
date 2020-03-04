package com.string;

import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;

public class StringEnter {

	public StringEnter() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) throws Exception {
		String a="hello world\r\ntest1 world\n";
		String aa=new String(a.getBytes("euc-kr"),"euc-kr");
		String b="hello world\ntest1 world\n";
		String bb=new String(b.getBytes("euc-kr"),"euc-kr");
		String c="hello world\rtest1 world\n";
		String cc=new String(c.getBytes("euc-kr"),"euc-kr");
		String tt=System.getProperty("line.separator");
		byte[] t=tt.getBytes();
		System.out.println("################");
		System.out.println(aa);
		System.out.println("################");
		System.out.println(bb);
		System.out.println("################");
		System.out.println(cc);
		System.out.println("################");
		System.out.println(System.getProperty("line.separator"));
		System.out.println("################");
		for (byte d : t) {
			System.out.println(d);
		}
		FileOutputStream fos=new FileOutputStream(new File("A:\\temp\\b.txt"));
		fos.write(aa.getBytes());
		fos.write(bb.getBytes());
		fos.write(cc.getBytes());
	
		fos.close();
		
	}
}
