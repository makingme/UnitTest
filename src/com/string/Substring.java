package com.string;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.StringTokenizer;

public class Substring {

	public Substring() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		/*String a="${a=b}";
		int ValIdx = a.indexOf("=");
		
		
		String Key = a.substring(0,ValIdx);
		System.out.println(Key);
//		템플릿 내 $문자와의 혼동을 방지하기 위해 새로운 변수 패턴  ${변수명}을 사용한다.
		if (Key.startsWith("$")) {
			String Value = a.substring(ValIdx+1,a.length());
			System.out.println(Value);
		}
		
		
		String aa="12345";
		if(aa.length()>4) {
			System.out.println(aa.substring(0, 1));
		}
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat =new SimpleDateFormat("mmssSSS");
		String fileTime= dateFormat.format(calendar.getTime());
		System.out.println(fileTime);
		<#assign tmpl=r"당신의 이름은 ${이름}이고, 나이는 ${나이} 입니다."/>
<#assign hash={ "이름":"홍길동","나이":20}/>


 <#assign msg=m1.var2message( tmpl,hash) />
		*
		*
		*/
/*		Substring aa=new Substring();
		HashMap<String,String > hash =new HashMap<String, String>();
		hash.put("변수1", "홍길동");
		hash.put("변수2", "나나");
		StringBuffer b=aa.var2message("당신의이름은 ${변수1} 입니다",hash);
		System.out.println(b.toString());*/
		Substring aa=new Substring();

		String msg="안녕하세요 ${변수1}고객님";
		String v="01083801686|7275|홍길동1";
		LinkedHashMap<String, String> hash=new LinkedHashMap<String, String>();
		hash=aa.pareBy(v);
		System.out.println(hash.toString());
		StringBuffer buffer=aa.var2message(msg, hash);
		System.out.println(buffer.toString());
	}

	
	
	public LinkedHashMap pareBy(String v2) {
		char ASCII_BELL =  (char)7;
		String STR_BELL = "" + ASCII_BELL;
				 
		LinkedHashMap hash = new LinkedHashMap();
		String v = v2;
		

		String delim  = "\\|";
		boolean bTrim = true;
		
		if ( delim.equals("\\|") ) v = v.replace("\\|", ""+(char)7);

		String[] def = {"수신전화번호","고객식별자","변수1","변수2","변수3","변수4","변수5","변수6","변수7","변수8","변수9"};
//				\|문자값을 보호 0x07 코드로 치환.
		if ( delim.equals("\\|")) v = v.replace("\\|", STR_BELL);
		
		String[] vs = v.split(delim);
		
		for ( int i = 0 ; i < vs.length && i < def.length ; i++ ) {
			String val = bTrim? vs[i].trim() : vs[i] ;
//					 0x07 코드를 |문자로 복원.					
			if ( delim.equals("\\|")) val = val.replace( ASCII_BELL,'|');
			
//					System.out.println(def.get(i).toString() + "= " + vs[i]);
			if ( delim.equals("\\|") ) val = val.replace( ""+(char)7,"|");
			
			if ( i >= def.length ) hash.put("UNDEF"+i, val);
			else hash.put(def[i].toString(), val);
		}		
		return hash;
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
	
	public  StringBuffer var2message(String template , LinkedHashMap<String,String > hash) 
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
