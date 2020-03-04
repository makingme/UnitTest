package com.string;

public class StringLeftPadding {

	public StringLeftPadding() {
		// TODO Auto-generated constructor stub
	}
	public static String leftPad(String target, String pad_char, int pad_length) {
		int padding_loop_cnt= pad_length -target.length();
		StringBuilder sb= new StringBuilder();
		for(int i=1; i<=padding_loop_cnt; i++) {
			sb.append(pad_char);
		}
		sb.append(target);
		return sb.toString();
	}
	public static void main(String[] args) {
		String n="1";
		System.out.println(leftPad(n,"0",10));
		
	}
}
