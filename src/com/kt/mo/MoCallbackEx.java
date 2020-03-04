package com.kt.mo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;



public class MoCallbackEx {

	
	private final String configTag;

	private String agt_cd="";
	private int maxThread=1;
	private int currentThreadNo=1;

	//private Crypto c =null;
	private Connection conn = null;
	private PreparedStatement pstmt=null;
	private String insertSQL="INSERT INTO TSUMSSU80"+
											"("+
												"발송서버접수식별자,				수신일,				메시지구분,			발신처전화번호,"+
												"수신처전화번호,					거래일시,				수신일시,				이통사구분,"+
												"메시지내용,						UMS메시지내용,		발송계약식별자,			발송상태구분,			처리식별자"+
											 ")"+
											"VALUES"+
											"("+
												"SQ_UMSSU00_MSGID.nextval,		sysdate,								?,								?,"+
												"?,													sysdate,						SYSDATE, 								?,"+	
												"?,															?,									?,								'0',					?"+	
											  ")";

	public MoCallbackEx(String configTag) {
		this.configTag = configTag;
		
	
		
	}
	public static void main(String[] args) {
		MoCallbackEx ex=new MoCallbackEx("test");
		String txt = "일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십"+
				"일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십"+
				"일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십"+
				"일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십"+
				"일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십"+
				"일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십"+
				"일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십"+
				"일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십"+
				"일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십"+
				"일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십"+
				"일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십"+
				"일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십"+
				"일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십"+
				"일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십"+
				"일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십";
		ex.Func_RvMoMms("01026313590", "15881588", "15881588", (short)0, "일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일", 
				txt, "", "", "", "", "", "");
	}
	public boolean Func_RvMoMms(String caller, String callbackNo, String calee, short telecomId, String subject, 
			String text, String imageFile1, String imageFile2, String imageFile3, String audioFile, String videoFile, String acceptDate) {
		// MMS Mo Received
			

		try {
			String aaa=System.getProperty("file.encoding");
			System.out.println(aaa);
			int subLength=subject.getBytes().length;
			int textLength=text.getBytes().length;
			int subLength1=subject.getBytes("euc-kr").length;
			int textLength1=text.getBytes("euc-kr").length;
			int subLength2=subject.getBytes("utf-8").length;
			int textLength2=text.getBytes("utf-8").length;
			System.out.println("@@@@@@@@@@@");
			System.out.println("subject length="+subLength);
			System.out.println("text length="+textLength);
			System.out.println("@@@@@@@@@@@");
			System.out.println("subject KR length="+subLength1);
			System.out.println("text KR length="+textLength1);
			System.out.println("@@@@@@@@@@@");
			System.out.println("subject UTF length="+subLength2);
			System.out.println("text UTF length="+textLength2);
			System.out.println("@@@@@@@@@@@");
			
			byte[] bytes=subject.getBytes("utf-8");
			
			if(bytes.length>120) {
				byte[] nBytes=new byte[120];
				System.arraycopy(bytes, 0, nBytes, 0, 120);
				subject=new String(nBytes,"utf-8");
			}
			System.out.println(subject.getBytes().length+", "+subject);
			
			bytes=text.getBytes("utf-8");
			if(bytes.length>4000) {
				text=subStrForKo(text,4000);
			}
			
			
			conn=getConnection();
			if(conn==null){
				throw new SQLException("SMSConnectionFactory.getConnectionEx() Null Return.. DB Connetion Getting Error");
			}
			String tele=getTelco(telecomId);
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(insertSQL);
			pstmt.setString(1, "LM");
			pstmt.setString(2, "01026313590");
			pstmt.setString(3, "15881588");
			pstmt.setString(4, tele);
			pstmt.setString(5, subject);
			pstmt.setString(6, text);
			pstmt.setString(7, agt_cd);
			pstmt.setString(8, String.valueOf(maxThread));
			
		/*	String mmsBasePath = this.config.getProperty("MMS_MO_DATA_PATH", "../mms");
			int mmsBaseLength = mmsBasePath.length();
			String msg_type=(imageFile1 != null && !imageFile1.equals("") && imageFile1.length() > mmsBaseLength)?"MMS":"LMS";*/

		/*	HashMap<String, String> map= new HashMap<String, String>(20); 
			map.put("발송서버접수식별자", String.valueOf(SMSSequence.nextSequence()));
			map.put("메시지구분", msg_type);
			map.put("발신처전화번호", caller);
			map.put("수신처전화번호", calee);
			map.put("거래일시", acceptDate);
			map.put("이통사구분",getTelco(telecomId) );
			map.put("메시지내용", subject);
			map.put("UMS메시지내용", text);
			map.put("에이전트구분", agt_cd);
			map.put("처리식별자", String.valueOf(currentThreadNo));
			
			hlpr.setValueAll(pstmt, map);*/
			
			int rsCount=pstmt.executeUpdate();
			pstmt.close();
			pstmt=null;
			//map.clear();

			if(rsCount>0){
				conn.commit();
				conn.close();
				conn=null;
				
				if(currentThreadNo<maxThread){
					currentThreadNo++;
				}else{
					currentThreadNo=1;
				}
				
				return true;
			}else{
				throw new SQLException("DB INSERT 0건 성공");
			}
			
		} catch (SQLException  e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.exit(20);
			}
		
			System.exit(20);
		}catch (Exception  e) {
			e.printStackTrace();
			System.exit(30);
		}finally {
			if ( pstmt != null )
			{
				try {
					pstmt.close() ;
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				pstmt = null;
			}	
									
			if ( conn != null )
			{
				try {
					conn.close() ;
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				conn = null;
			}
		}
		
		return true;
	}
	public String subStrForKo(String txt,int maxLen) throws Exception{
		String dbEnc="utf-8";	
		int koSize=3;
		if(!dbEnc.equalsIgnoreCase("utf-8")) {
			koSize=2;
		}
	
		byte[] bytes = txt.getBytes(dbEnc);
		System.out.println(bytes.length);
		int i = 0;
		while (i < bytes.length) {
			if ((bytes[i] & 0x80) != 0) {
				if(i+koSize>maxLen)break;
				//System.out.println(i + "한글");
				i += koSize;
				
			} else {
				//System.out.println(i + "영문");
				i += 1;
			}
		}
		System.out.println(new String(bytes,0,i,dbEnc));

		
		return new String(bytes,0,i,dbEnc);
	}

	public boolean Func_RvMoSms(String caller, String callbackNo, String calee, String msg, short telecomId, String acceptDate) {
		// SMS Mo Received
	
		
		String reply_no=calee.trim().equals("")?calee:callbackNo;
	

		try {
			
			conn=getConnection();
			if(conn==null){
				throw new SQLException("SMSConnectionFactory.getConnectionEx() Null Return.. DB Connetion Getting Error");
			}
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(insertSQL);
			
/*			HashMap<String, String> map= new HashMap<String, String>(20); 
			map.put("발송서버접수식별자", String.valueOf(SMSSequence.nextSequence()));
			map.put("메시지구분", "SMS");
			map.put("발신처전화번호", caller);
			map.put("수신처전화번호", calee);
			map.put("거래일시", acceptDate);
			map.put("이통사구분",getTelco(telecomId) );
			map.put("메시지내용", msg);
			map.put("UMS메시지내용", "");
			map.put("에이전트구분", agt_cd);
			map.put("처리식별자", String.valueOf(currentThreadNo));*/
			
		
			
			int rsCount=pstmt.executeUpdate();
			pstmt.close();
			pstmt=null;
			
			if(rsCount>0){
				conn.commit();
				conn.close();
				conn=null;
				
				if(currentThreadNo<maxThread){
					currentThreadNo++;
				}else{
					currentThreadNo=1;
				}
				
				return true;
			}else{
				throw new SQLException("DB INSERT 0건 성공");
			}
			
		} catch (SQLException  e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.exit(20);
			}
			
			System.exit(20);
		}catch (Exception  e) {
			e.printStackTrace();
			System.exit(30);
		}finally {

			if ( pstmt != null )
			{
				try {
					pstmt.close() ;
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				pstmt = null;
			}	
									
			if ( conn != null )
			{
				try {
					conn.close() ;
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				conn = null;
			}
		}
		
			
		return true;

	}

	
	public static String getTelco(short telecomId) {
		switch(telecomId) {
		case 0:
			return "UNK";
		case 1:
			return "SKT";
		case 2:
			return "KTF";
		case 3:
			return "LGT";
		case 4:
			return "CJM";
		case 5:
			return "SKT";
		case 6:
			return "KTF";
		case 7:
			return "LGT";
		case 24:
			return "KCT";
		case 25:
			return "CJ7";
		case 31:
			return "SKT";
		case 32:
			return "KTF";
		case 33:
			return "LGT";
		default :
			return "UNK";
		}
	}

	public Connection getConnection()
	{	
		System.out.println("드라이버 로딩 시도 중..");
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn= DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.24:1521:xe", "m1","m1m2m3");
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException_JDBC LIBRARY CHECK");
			e.printStackTrace();
		} catch(SQLException e){
			System.out.println("DBMS CONNECT FAILE");
			e.printStackTrace();
		}
		return conn;
	}
}
