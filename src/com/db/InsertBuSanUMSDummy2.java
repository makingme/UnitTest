package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertBuSanUMSDummy2 {

	public InsertBuSanUMSDummy2() {

	}
	
	Connection mainCon=null;
	PreparedStatement ps=null;
	PreparedStatement ps2=null;
	PreparedStatement ps3=null;
	ResultSet rs=null;
	public Connection getConnection()
	{	
		System.out.println("드라이버 로딩 시도 중..");
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			mainCon= DriverManager.getConnection("jdbc:oracle:thin:@112.216.241.98:21521:XE", "BUSANB","bs2019");
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException_JDBC LIBRARY CHECK");
			e.printStackTrace();
		} catch(SQLException e){
			System.out.println("DBMS CONNECT FAILE");
			e.printStackTrace();
		}
		return mainCon;
	}
	
	
	public void insertDB(Connection mainCon ) throws SQLException
	{	

		try {
			long lmsSeq=0;
			String sqlIdentifier = "select BUSANB.SEQ_LMS_PK.NEXTVAL from dual";
			
			String insertCm="INSERT INTO BUSANB.EM_TRAN_BATCH"+ 
			"("+
			 "TRAN_PR, 					TRAN_REFKEY, 				TRAN_ID, 				TRAN_PLNGKEY,	TRAN_PLNGHMS,"+ 
			 "TRAN_PHONE,				TRAN_CALLBACK, 			TRAN_STATUS, 		TRAN_DATE,			TRAN_RSLTDATE,"+ 
			 "TRAN_REPORTDATE, 	TRAN_RSLT,					TRAN_NET, 			TRAN_MSG,			TRAN_ETC1,"+ 
			 "TRAN_ETC2, 				TRAN_ETC3, 					TRAN_TYPE,			TRAN_ETC4,			SERVICE_TYPE,"+ 
			 "SERVICE_NO,				SERVICE_GUBUN, 			SEQ, 					RESULT_SEQ, 		LIST_SEQ,	"+
			 "REQ_USER_ID,			REQ_DEPT_ID, 				SLOT1,					SLOT2,					CUSTOMER_NO,"+
			 "SERIAL_NUM,				COMP_CD, 					PLACE_CD,			BIZ_ID1,				BIZ_ID2,"+
			 "COMP_KEY,				CMP_USR_ID, 				ODR_FG,				TR_MODI_ID,		BILL_ID,"+
			 "INCOMING_TYPE, 		INCOMING_FIND_KEY,	BIZ_ID3"+
			 ")VALUES ("+
			 " BUSANB.SEQ_SMS_PK.nextval,		'',						'',				null, 			null,"		+//TRAN_PR
			 " ?,													'15886200',		'1',			sysdate,	null,"		+//TRAN_PHONE
			 " null,												null,					null,			null, 			null,"		+//TRAN_REPORTDATE
			 " null,												null,					'6',			?,				null,"		+//TRAN_ETC2
			 " '',													'',						null,			null,			null,"		+//SERVICE_NO
			 " '',													'',						null,			null,			null,"		+//REQ_USER_ID
			 " '',													'',						null,			null,			null,"		+//SERIAL_NUM
			 " '',													'',						null,			null,			null,"		+//COMP_KEY
			 " null,												null,					null"										+//INCOMING_TYPE
			")";
			
			String insertLMS="INSERT INTO BUSANB.EM_TRAN_BATCH_LMS"+ 
					"("+
					 "MMS_SEQ, 					FILE_CNT, 				BUILD_YN, 			MMS_BODY,			MMS_SUBJECT,"+		//MMS_SEQ
					 "FILE_TYPE1,				FILE_TYPE2, 				FILE_TYPE3, 			FILE_TYPE4,			FILE_TYPE5,"+			//FILE_TYPE1 
					 "FILE_NAME1, 				FILE_NAME2,				FILE_NAME3, 		FILE_NAME4,			FILE_NAME5,"+			//FILE_NAME1
					 "SERVICE_DEP1, 			SERVICE_DEP2, 		SERVICE_DEP3,		SERVICE_DEP4,		SERVICE_DEP5,"+ 	//SERVICE_DEP1
					 "SKN_FILE_NAME"+																																//SKN_FILE_NAME
					 ")VALUES ("+
					 " ?,													0,						'',				?, 				?,"		+//MMS_SEQ
					 " null,												null,					null,			null,			null,"		+//FILE_TYPE1
					 " null,												null,					null,			null, 			null,"		+//FILE_NAME1
					 " null,												null,					null,			null,			null,"		+//SERVICE_DEP1
					 " null"																													+//SKN_FILE_NAME
					")";
			
			mainCon.setAutoCommit(false);
			ps=mainCon.prepareStatement(insertLMS);
			ps2=mainCon.prepareStatement(insertCm);
			ps3=mainCon.prepareStatement(sqlIdentifier);
			
			int count=0;
			long stime=System.currentTimeMillis();
			for(int i=1;i<=10000;i++) {
				String a="";
/*				rs = ps3.executeQuery();
				
			   if(rs.next()) {
				   lmsSeq = rs.getLong(1);
			   }else {
				   lmsSeq+=1;
			   }*/
			   lmsSeq+=1; 
				if(i%2==0) {
					a="01026313590";
				}else {
					a="01026313590";
				}
				count++;
				ps.setLong(1, lmsSeq);
				ps.setString(2, String.valueOf(i)+"번째 메시지");
				ps.setString(3, String.valueOf(i)+"");
				ps.executeUpdate();
				ps.clearParameters();
				//mainCon.commit();
				if(i%1000==0) {
					System.out.println(count+"번째 LMS 입력");
				}
				if(i%100==0) {
					throw new Exception();
				}
				ps2.setString(1, a);
				ps2.setLong(2, lmsSeq);
				ps2.executeUpdate();
				ps2.clearParameters();
				mainCon.commit();
				if(i%1000==0) {
					System.out.println(count+"번째 공통 입력");
				}
	
			}
			
			System.out.println("total: "+count+" insert!! 완료");
			System.out.println("소요ms:"+(System.currentTimeMillis()-stime));
			System.out.println("총처리TPS:"+(((count*1000)/(System.currentTimeMillis()-stime))));
		} catch (Exception e) {
			e.printStackTrace();
			mainCon.rollback();
			// TODO: handle exception
		}
	}
	
	public void clear() {
		
		if(rs!=null) {
			try {
				rs.close();
				rs=null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if(ps!=null) {
			try {
				ps.close();
				ps=null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if(mainCon!=null) {
			try {
				mainCon.close();
				mainCon=null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	public static void main(String[] args) throws SQLException {
		InsertBuSanUMSDummy2 t= new InsertBuSanUMSDummy2();
		t.mainCon=t.getConnection();
		t.insertDB(t.mainCon);
		t.clear();
	}
}
