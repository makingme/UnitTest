/**
 * 
 */
package com.string;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**

  * @FileName : StringSplitEx2.java

  * @Project : UnitTest

  * @Date : 2020. 11. 11. 

  * @작성자 : kibumkim

  * @변경이력 :

  * @프로그램 설명 :

  */
public class StringSplitEx2 {
	public static void main(String[] args) {
		String data="RCSID:odinue,RCSPW:1234,BRANDID:abcdef,TEST:";
		Map map= new HashMap(10); 
		 String[] sessions = data.split(",");
        if (sessions != null && sessions.length > 0 ) {
        	for(String session : sessions) {
        		int pos = session.indexOf(":");
        		if(pos >=0 && pos != (session.length()-1)) {
        			map.put(session.substring(0,pos), session.substring(pos+1));
        		}
        	}
		}
        System.out.println(map.size());
        Set set=map.keySet();
		Iterator itr=set.iterator();
		while (itr.hasNext()) {
			String key=(String) itr.next();
			System.out.println(key+"@"+map.get(key));
			
		}
	}
}
