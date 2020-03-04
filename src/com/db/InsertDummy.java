package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertDummy {

	public InsertDummy() {
		// TODO Auto-generated constructor stub
	}
	
	Connection mainCon=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	public Connection getConnection()
	{	
		System.out.println("드라이버 로딩 시도 중..");
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			mainCon= DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.120:1521:xe", "IBK_SMS","odinue2014");
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
			//ps=mainCon.prepareStatement("update  TSUMSSU112 set 비밀번호='01397735F267E48D0A198162029FE2C3F4C0A0A708B01D6B43F8ADB47AD3EED1' where 직원번호='DEV' ");
			String insertSQL="INSERT INTO DIST_TRAN"+ 
			"("+
			 "TRAN_PR, 					TRAN_REFKEY, 		TRAN_ID, 			TRAN_PHONE,"+ 
			 "TRAN_CALLBACK,		TRAN_STATUS, 		TRAN_DATE, 		TRAN_RSLTDATE,"+ 
			 "TRAN_REPORTDATE, 	TRAN_RSLT,			TRAN_MSG, 		TRAN_ETC1,"+ 
			 "TRAN_ETC2, 				TRAN_ETC3, 			TRAN_ETC4,		TRAN_TYPE,"+ 
			 "TRAN_NET, 				TRAN_FVIA, 				TRAN_LVIA, 		REG_DATE,"+
			 "REAL_SENDDATE, 		TRAN_ETC5"+
			 ")VALUES ("+
			 " ?,00000000001,'IBKSM00001',?, "+
			 " '15882233','1',to_date(?,'yyyymmddHH24MISS'),'',"+
			 " '','',?,'', "+
			 " '','',0,	0,"+
			 " '','','',	sysdate,"+
			 " 	'','' "+
			")";
/*			String insertSQL="INSERT INTO DIST_TRAN"+ 
			"("+
			 "TRAN_PR, 					TRAN_REFKEY, 		TRAN_ID, 			TRAN_PHONE,"+ 
			 "TRAN_CALLBACK,		TRAN_STATUS, 		TRAN_DATE, 		TRAN_RSLTDATE,"+ 
			 "TRAN_REPORTDATE, 	TRAN_RSLT,			TRAN_MSG, 		TRAN_ETC1,"+ 
			 "TRAN_ETC2, 				TRAN_ETC3, 			TRAN_ETC4,		TRAN_TYPE,"+ 
			 "TRAN_NET, 				TRAN_FVIA, 				TRAN_LVIA, 		REG_DATE,"+
			 "REAL_SENDDATE, 		TRAN_ETC5"+
			 ")VALUES ("+
			 " ?,00000000001,'IBKSM00001',?, "+
			 " '15882233','1',sysdate,'',"+
			 " '','',?,'', "+
			 " '','',0,	0,"+
			 " '','','',	sysdate,"+
			 " 	'','' "+
			")";*/
			ps=mainCon.prepareStatement(insertSQL);
			//ps.setInt(1, 1);
			//ps.setString(2, String.valueOf(1)+"번째 메시지");
			//ps.execute();
			int yyyymmdd=20180420;
			int count=1;
			for(int d=1;d<=2;d++) {
				
				for(int h=0;h<=23;h++) {
					String sh=String.format("%02d",h);
					for(int m=0;m<=59;m++) {
						String sm=String.format("%02d",m)+"00";
						for(int i=1;i<=1;i++) {
							String a="";
							if(i%2==0)a="01011112222";
							else 
								a="01033334444";
							ps.setInt(1, count+100);
							ps.setString(2, a);
							ps.setString(3, yyyymmdd+sh+sm);
							ps.setString(4, String.valueOf(i)+"번째 메시지");
							ps.addBatch();
							count++;
							System.out.println(i+"insert!!");
						}
						
					}
					ps.executeBatch();
					ps.clearBatch();
				}
				
				yyyymmdd+=d;
			}
/*			for(int i=1;i<=10000;i++) {
				String a="";
				
				if(i%2==0)a="01011112222";
				else 
					a="01033334444";
				ps.setInt(1, i+100);
				ps.setString(2, a);
				//ps.setString(3, "20180420000000");
				ps.setString(3, String.valueOf(i)+"번째 메시지");
				ps.addBatch();
				//System.out.println(ps.);
				System.out.println(i+"insert!!");
			}
			ps.executeBatch();*/
			
			
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
		InsertDummy t= new InsertDummy();
		t.mainCon=t.getConnection();
		//t.showRS(t.mainCon);
		t.insertDB(t.mainCon);
		t.clear();
	}
}
