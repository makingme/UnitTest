package com.string;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

public class Var2MessageExam {

	public Var2MessageExam() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		String s="${등급}입니다 ${고객명}님";
		String repl="{\"등급\":\"VIP\", \"고객명\":\"홍길동\"}";
		StringBuffer sb=Var2MessageExam.var2message(s,repl);
		
		System.out.println(sb.toString());
	}
	
	public static StringBuffer var2message(String template , String varvalue)
	{
		StringBuffer retBuf = new StringBuffer(template);
		if ( retBuf == null ) return null;
		StringTokenizer st = new StringTokenizer(varvalue , "|");
		// 템플릿에 변수가 남아 있는 조건을 추가하여, 불필요한 템플릿 검사 횟수를 줄임.
		while(st.hasMoreTokens() && retBuf.indexOf("${")!=-1 )
		{
			String Val = st.nextToken();
			int ValIdx = Val.indexOf("=");
			if (ValIdx==-1) continue;
			
			String Key = Val.substring(0,ValIdx);
//			템플릿 내 $문자와의 혼동을 방지하기 위해 새로운 변수 패턴  ${변수명}을 사용한다.
			String newKey = "${" + Key.substring(1) + "}";				
			String Value = Val.substring(ValIdx+1,Val.length());
			
			//Convert VarField to VarValue
			int sbIdx = 0;
			int sbCnt = 0;
			while((sbIdx = retBuf.indexOf(newKey))!= -1)
			{
				//retBuf.replace(sbIdx, sbIdx + NAME_VAL_SIZE(6), Value);
				retBuf.replace(sbIdx, sbIdx + newKey.length(), Value);
				sbCnt++;
			}
			//if (sbCnt==0) return null;
		}
		
		//if (retBuf.indexOf("$VAL")!=-1) return null;
		if (retBuf.indexOf("${")!=-1) return null;
		
	
		return retBuf;
	}
	
	public  StringBuffer var2message(String template , HashMap hash) 
	{
		StringBuffer retBuf = new StringBuffer(template);
		if ( retBuf == null ) return null;
		
		Set cols = hash.keySet() ;
		Iterator itr = cols.iterator() ;
		// 템플릿에 변수가 남아 있는 조건을 추가하여, 불필요한 템플릿 검사 횟수를 줄임.
		while( itr.hasNext()  && retBuf.indexOf("${")!=-1   )
		{
			
			String Key = itr.next().toString();
//			템플릿 내 $문자와의 혼동을 방지하기 위해 새로운 변수 패턴  ${변수명}을 사용한다.
			String newKey = "${" + Key + "}";				
			String Value = hash.get(Key).toString();
//			System.out.println("key " + Key + " v=" + Value);
			//Convert VarField to VarValue
			int sbIdx = 0;
			int sbCnt = 0;
//			System.out.println( "retBuf=" + retBuf.toString());
			int pos= 0; //변수값에 ${} 변수 포멧을 쓰는 경우. 무한루프가 되는 버그 패치( 2017.02.28 kbcard )
			for (int i = 0 ; (sbIdx = retBuf.indexOf(newKey,pos))!= -1 && i < 4096 ; i++)
			{
				//retBuf.replace(sbIdx, sbIdx + NAME_VAL_SIZE(6), Value);
				retBuf.replace(sbIdx, sbIdx + newKey.length(), Value);
//				System.out.println( "retBuf=" + retBuf.toString());
				pos = sbIdx + Value.length();
				sbCnt++;
			}
			//if (sbCnt==0) return null;
		}
		
		//if (retBuf.indexOf("$VAL")!=-1) return null;
		if (retBuf.indexOf("${")!=-1) return null;
		
	
		return retBuf;
	}

}
