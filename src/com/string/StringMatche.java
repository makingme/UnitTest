package com.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringMatche {

	public StringMatche() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
/*		String a="A113E";
		if(!a.matches("^[A-Z]*$")) {
		//	System.out.println("AA");
		}
		String b="[$][{]{1}[가-힣]{2}[0-9]+[}]{1}";
		Pattern pattern=Pattern.compile(b);
		Matcher match = pattern.matcher("1111${변수1}1}}222${변수2}}3333${변수3)$444{변수4}");
		
		int c=0;
		while(match.find()){
			System.out.println(match.group(0));
			System.out.println(match.group(0));
			c++;
		}
		System.out.println(c);*/
		//System.out.println(match.groupCount());
		String fax="021234567";
		String regex = "0\\d{1,2}[1-9]\\d{2,3}\\d{4}";
		System.out.println(Pattern.matches(regex, fax));
		//Pattern.matches(regex, fax);
		
		
	}
}
