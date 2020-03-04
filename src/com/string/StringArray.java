package com.string;

public class StringArray {

	public StringArray() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
/*		String[] a= {"a|","b|","c\n"};
		String[] b= {"a|","b|","c\n"};
		StringBuilder build=new StringBuilder();
		build.append(a[0]);
		build.append(a[1]);
		build.append(a[2]);
		build.append(b[0]);
		build.append(b[1]);
		build.append(b[2]);
		System.out.println(build.toString());*/
		
		String cname="DBX_AAA + DBX_BBB + DBX_CCC";
		String[] qnms = cname.split("\\+");
		for ( int i =0 ; i < qnms.length ; i++ ) {
			
			String qnm = qnms[i].trim() ;
			System.out.println(qnm);
		}
	}
}
