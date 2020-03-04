package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertBuSanUMSDummy {

	public InsertBuSanUMSDummy() {

	}
	
	Connection mainCon=null;
	PreparedStatement ps=null;
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
	
	public void showRS(Connection mainCon )
	{	
		PreparedStatement ps=null;
		try {
			ps=mainCon.prepareStatement("select  count(*) as tttt from DIST_TRAN");
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
			    System.out.println(rs.getInt("tttt"));
			    
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void updateDB(Connection mainCon )
	{	
		PreparedStatement ps=null;
		try {
			ps=mainCon.prepareStatement("update  TSUMSSU112 set 비밀번호='01397735F267E48D0A198162029FE2C3F4C0A0A708B01D6B43F8ADB47AD3EED1' where 직원번호='DEV' ");
			int rs= ps.executeUpdate();
			System.out.println("업데이트 Count="+rs);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void insertDB(Connection mainCon )
	{	

		try {

			String insertTSQL="INSERT INTO BUSANB.EM_TRAN_BATCH_TOTAL"+ 
			"("+
			 "TRAN_PR, 						TRAN_REFKEY, 				TRAN_ID, 				TRAN_PLNGKEY,	TRAN_PLNGHMS,"+ 
			 "TRAN_PHONE,					TRAN_CALLBACK, 			TRAN_STATUS, 		TRAN_DATE,			TRAN_RSLTDATE,"+ 
			 "TRAN_REPORTDATE, 		TRAN_RSLT,					TRAN_NET, 			TRAN_MSG,			TRAN_ETC1,"+ 
			 "TRAN_ETC2, 					TRAN_ETC3, 					TRAN_TYPE,			SERVICE_TYPE,		SERVICE_NO,"+ 
			 "SERVICE_GUBUN, 			SEQ, 							RESULT_SEQ, 		LIST_SEQ,				REQ_USER_ID,"+
			 "REQ_DEPT_ID, 				SLOT1,							SLOT2,					CUSTOMER_NO,	SERIAL_NUM,"+
			 "COMP_CD, 						PLACE_CD,					BIZ_ID1,				BIZ_ID2,				COMP_KEY,"+
			 "CMP_USR_ID, 				ODR_FG,						FILE_CNT,				BUILD_YN,			MMS_BODY,"+
			 "MMS_SUBJECT, 				FILE_TYPE1,					FILE_TYPE2,			FILE_TYPE3,			FILE_TYPE4,"+
			 "FILE_TYPE5, 					FILE_NAME1,					FILE_NAME2,			FILE_NAME3,			FILE_NAME4,"+
			 "FILE_NAME5, 					SERVICE_DEP1,				SERVICE_DEP2,		SERVICE_DEP3,		SERVICE_DEP4,"+
			 "SERVICE_DEP5, 				SKN_FILE_NAME,			TR_MODI_ID,		BILL_ID,				INCOMING_TYPE,"+
			 "INCOMING_FIND_KEY,	BIZ_ID3"+
			 ")VALUES ("+
			 " BUSANB.SEQ_SMS_PK.nextval,		'',						'',				null, 			null,"		+//TRAN_PR
			 " ?,													'15886200',		'1',			sysdate,	null,"		+//TRAN_PHONE
			 " null,												null,					null,			null, 				null,"	+//TRAN_REPORTDATE
			 " null,												null,					'6',			null,			null,"		+//TRAN_ETC2
			 " '',													'',						null,			null,			null,"		+//SERVICE_GUBUN
			 " '',													'',						null,			null,			null,"		+//REQ_DEPT_ID
			 " '',													'',						null,			null,			null,"		+//COMP_CD
			 " '',													'',						0,				null,			?,"		+//CMP_USR_ID
			 " ?,													'',						null,			null,			null,"		+//MMS_SUBJECT
			 " '',													'',						null,			null,			null,"		+//FILE_TYPE5
			 " '',													'',						null,			null,			null,"		+//FILE_NAME5
			 " '',													'',						null,			null,			null,"		+//SERVICE_DEP5
			 " null,												null"																+//INCOMING_FIND_KEY
			")";
			
			String insertSQL="";
			
			ps=mainCon.prepareStatement(insertTSQL);
			int count=0;
			System.out.println("Insert Start");
			long stime=System.currentTimeMillis();
			for(int i=1;i<=500000;i++) {
				String a="";
				
				if(i%2==0)a="01026313590";
				else 
					a="01026313590";
				ps.setString(1, a);

				ps.setString(2, String.valueOf(i)+"번째 메시지");
				ps.setString(3, String.valueOf(i)+"");
				ps.addBatch();
				if(i%1000==0) {
					ps.executeBatch();
					ps.clearBatch();
					//System.out.println(i+"insert!!");
				}
/*				if(i%10000==0) {
					//System.out.println("소요ms:"+(System.currentTimeMillis()-stime));
					System.out.println("구간처리TPS:"+((10000/(System.currentTimeMillis()-stime))*1000));
					//System.out.println(i+"insert!!");
				}*/
				count++;
			}
			ps.executeBatch();
			ps.clearBatch();
			System.out.println("total: "+count+" insert!!");
			System.out.println("총처리TPS:"+(((count*1000)/(System.currentTimeMillis()-stime))));
			
		} catch (Exception e) {
			e.printStackTrace();
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
	
	public static void main(String[] args) {
		InsertBuSanUMSDummy t= new InsertBuSanUMSDummy();
		t.mainCon=t.getConnection();
		t.insertDB(t.mainCon);
		t.clear();
	}
}
