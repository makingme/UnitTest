package com.string;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class StringSplit {

	public StringSplit() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		
//		//([$])([{]{0,1})([媛�-�옡]{0,2})([0-9]){0,1}
//		String a="${蹂��닔)${蹂��닔22}";
//		int strLength = a.split("\\$\\{蹂��닔}").length - 1;
//		System.out.println(strLength);
		String[] testArray= {"a"};
		if(testArray[1] !=null) {
			System.out.println("ddd");
		}else {
			System.out.println("cc");
		}
		HashMap<String, Object> depth1 =new HashMap<String, Object>();
		HashMap<String, Object> depth2 =new HashMap<String, Object>();
		HashMap<String, Object> depth3 =new HashMap<String, Object>();
		depth3.put("c", "yes");
		depth2.put("b", depth3);
		depth1.put("a", depth2);
		
		String data="a/b/c|1/2/3";
		String data1="a/b/e";
		String[] dataArray =data1.split("\\|");
		for (String s : dataArray) {
			String[] keyArray =s.split("\\/");
			System.out.println(checkNullHash(depth1, keyArray,0));
		}

		
	}
	public static boolean returnTrue() {
		return false;
	}
	public static boolean checkNullHash(Map<String, Object> mainHash, String[] keys, int index) {
		Object valueObject=mainHash.get(keys[index]);
		if (valueObject != null) {
			if (valueObject instanceof Map && keys[index+1] != null && !keys[index+1].equals("")) {
				Map<String, Object> nextHash= (Map<String, Object>) valueObject;
				return checkNullHash(nextHash, keys, index+1);
			}
		}else {
			return false;
		}
		return true;
	}
}


/*		System.out.println(dataArray.length);
		Set<String> keys = depth1.keySet() ;
		Iterator<String> itr = keys.iterator() ;
		while ( itr.hasNext() ) {
			
			String key = itr.next() ;

			Object vright = depth1.get(key);
			if ( vright == null  ) {
				System.out.println("NULL");
			}
		}*/