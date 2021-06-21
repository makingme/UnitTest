/**
 * 
 */
package com.java.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**

  * @FileName : StringExchangeEx.java

  * @Project : UnitTest

  * @Date : 2020. 11. 26. 

  * @�ۼ��� : kibumkim

  * @�����̷� :

  * @���α׷� ���� :

  */
public class StringExchangeEx {
	static Logger ctx=null;
	public static void main(String[] args) {
		String template="${ȸ���Ԥ����Ԥ��Ƥø��������}�� �ȳ��ϼ��� ${�ݾ�}�� ����${������}�Ǿ����ϴ�.${�Ƹ�}";
		StringBuffer data=null;
		Map hash =new HashMap<String, String>();
		hash.put("ȸ����", "����");
		hash.put("�ݾ�", "1000");
		data=var2message(template, hash, "${", "}");
		if(data !=null) {
			System.out.println(data.toString());
		}
		System.out.println("����--------------");
	}
	
	public static StringBuffer var2message(String template , Map<String, String> hash,String tagStart , String tagEnd) 
	{
		StringBuffer retBuf = new StringBuffer(template);
		if ( retBuf == null ) return null;

		Set keySet=hash.keySet();
		Iterator itr=keySet.iterator();

		while( itr.hasNext()  && retBuf.indexOf(tagStart)!=-1   )
		{

			String Key = itr.next().toString();
			String newKey = tagStart + Key + tagEnd;				
			String Value = hash.get(Key).toString();

			int sbIdx = 0;
			int sbCnt = 0;
			//				System.out.println( "retBuf=" + retBuf.toString());
			int pos= 0; //�������� ${} ���� ������ ���� ���. ���ѷ����� �Ǵ� ���� ��ġ( 2017.02.28 kbcard )
			for (int i = 0 ; (sbIdx = retBuf.indexOf(newKey,pos))!= -1 && i < 4096*2 ; i++)
			{
				retBuf.replace(sbIdx, sbIdx + newKey.length(), Value);
				pos = sbIdx + Value.length();
				sbCnt++;
			}
		}
		
		
		if (retBuf.indexOf(tagStart)!=-1) {
			try {
				int pos= 0;
				int endPos=0;
				int sbIdx = 0;
				String display=null;
				while((sbIdx=retBuf.indexOf(tagStart, pos)) !=-1) {
					endPos=retBuf.indexOf(tagEnd, sbIdx);
					display="";
					if (endPos ==-1) {
						if(retBuf.length()<(sbIdx+5)) {
							endPos=retBuf.length();
						}else {
							endPos=sbIdx+5;
						}
						 display=retBuf.substring(sbIdx, endPos);
						 pos=endPos;
					}else {
						 display=retBuf.substring(sbIdx, endPos+1);
						 pos=endPos;
					}
					if ( ctx != null ) {
						ctx.error("��ġȯ ����: "+display);
					}else {
						System.out.println("��ġȯ ����: "+display);
					}
					
				}
			} catch (Exception e) {
				if ( ctx != null ) {
					ctx.error("��ġȯ ���� ������ ���� ");
				}else {
					System.out.println("��ġȯ ���� ������ ���� ");
				}
				e.printStackTrace();
			}
			System.out.println("---------------");
		}

		//if (retBuf.indexOf("$VAL")!=-1) return null;
		if (retBuf.indexOf(tagStart)!=-1) return null;


		return retBuf;
	}
}
