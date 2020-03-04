package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.string.StringLeftPadding;

public class InsertBusanLossSeq {

	public InsertBusanLossSeq() {
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
	public void insertDB(Connection mainCon )
	{	

		try {

			String insertTSQL="INSERT INTO BUSANB.TB_EAI_SEQ_CHECK"+ 
			"("+
			 "RCV_DATE, 						RCV_SEQ, 				RCV_SEQ_GRP, "+ 
			 "RCV_SEQ_PRE,					RCV_SEQ_SUF, 			TRY_CNT, 		PROCESSED"+
			 ")VALUES ("+
			 " TO_CHAR(sysdate,'YYYYMMDD'),		?,						?,"+
			 " ?,														?,						1,			1"	+
			")";
			
			ps=mainCon.prepareStatement(insertTSQL);
			int count=0;
			int RCV_SEQ_GRP=1;
			int max_pre_seq=81;
			int max_suf_seq=999;
			System.out.println("Insert Start");
			
			long stime=System.currentTimeMillis();
			for(int i=0; i<=max_pre_seq; i++) {
				String rcv_seq_pre =String.valueOf((RCV_SEQ_GRP* 10000 + i))  ;
				for(int j=0; j<=max_suf_seq;j++) {
					String rcv_seq_suf=StringLeftPadding.leftPad(String.valueOf(j), "0", 3);
					ps.setString(1, rcv_seq_pre+rcv_seq_suf);
					ps.setString(2, String.valueOf(RCV_SEQ_GRP));
					ps.setString(3, rcv_seq_pre);
					ps.setString(4, rcv_seq_suf);
					ps.addBatch();
					count++;
				}
				ps.executeBatch();
				ps.clearBatch();
				System.out.println("처리 진행 중..."+count);
			}
			System.out.println("total: "+count+" insert!!");
			System.out.println("총처리TPS:"+(((count*1000)/(System.currentTimeMillis()-stime))));
			clear();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	public static void main(String[] args) {
		InsertBusanLossSeq t= new InsertBusanLossSeq();
		t.mainCon=t.getConnection();
		t.insertDB(t.mainCon);
		t.clear();
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
}
