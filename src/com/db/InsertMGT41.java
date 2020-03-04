package com.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertMGT41 {

	public InsertMGT41() {
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
			mainCon= DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.120:1521:xe", "MGUMSADM","m1m2m3");
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

	public void insertDB(Connection mainCon )
	{	

		try {
			String insertSQL="INSERT INTO TSUMSSU41"+ 
			"("+
			 "그룹회사코드,  발송서버접수식별자, 발송이력식별자, 업무식별자,"+ 
			 "그룹고유번호, 거래구분, 발송상태구분, 예약일시, 접수일시, 발송우선순위구분,"+ 
			 "발송부점코드, 발송직원번호, 메시지구분, 휴대폰번호, 발신처전화번호, 메시지내용, UMS메시지내용"+ 
			 ")VALUES ("+
			 " 'MG0', LPAD(SQ_UMSSU00_MSGID.nextval,20,'0'),LPAD(SQ_UMSSU00_MSGID.nextval,20,'0'),  'BKOBI____' ,"+
			 " 0, 'UMSSTD0001', '0', '20180525120000', '20180525120000', '5' ,"+
			 " '부서1', '담당자1', 'SM', ?, '15776825', ?, ''"+
			")";

			ps=mainCon.prepareStatement(insertSQL);
			for (int i=1;i<=100000;i++) {
				ps.setString(1, "010111111"+ i%100);
				//ps.setString(2, "번째 메시지");
				ps.setString(2, ((i/101)+1)+"번째 메시지");
				ps.addBatch();
				if(i%1000 ==0) {
					System.out.println((i-999)+" ~"+i+" 번 입력");
					ps.executeBatch();
					ps.clearBatch();
				}
			}
			ps.executeBatch();
			ps.clearBatch();
			System.out.println("끝");
			
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
		InsertMGT41 t= new InsertMGT41();
		t.mainCon=t.getConnection();
		//t.showRS(t.mainCon);
		t.insertDB(t.mainCon);
		t.clear();
	}
}
