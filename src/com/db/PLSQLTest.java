package com.db;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;

public class PLSQLTest {

	public PLSQLTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception {
		//String sql="select * from JBBANK.parts";
		Connection conn=DBConnection.getConnection("112.216.241.98:21521:XE","SMSADMIN" ,"m1m2m3" );
	//	Connection conn=DBConnetion.getConnection("112.216.241.98:21521:XE","JBH" ,"m1m2m3" );
	//	String sql = "BEGIN UPDATE parts  SET part_name = part_name || '1' RETURNING  part_name,  part_number  INTO ?, ?; END;";
		//String sql1 = "UPDATE parts  SET part_name = part_name || '1' RETURNING  part_name,  part_number  INTO ?, ?";
/*		CallableStatement callStmt = conn.prepareCall(sql);
		callStmt.registerOutParameter(1, Types.VARCHAR);
		callStmt.registerOutParameter(2, Types.INTEGER);
		 callStmt.execute();*/
		String sql = "UPDATE parts  SET part_name = part_name || '1' RETURNING  part_name,  part_number  INTO ?, ?";
		OracleConnection oraConn=null;
		conn.setAutoCommit(false);
		if(conn.isWrapperFor(oracle.jdbc.OracleConnection.class)){
			System.out.println("oracle.jdbc.OracleConnection.class");
			oraConn=conn.unwrap(oracle.jdbc.OracleConnection.class);
		}else{
			System.out.println("NOT SUPPORT");
			conn.close() ;
			conn = null;
			
		}
		oraConn.setAutoCommit(false);
		System.out.println("conn Auto Commit = "+conn.getAutoCommit());
		System.out.println("oraConn Auto Commit = "+oraConn.getAutoCommit());
		OraclePreparedStatement ps = (OraclePreparedStatement)conn.prepareStatement(sql);

		ps.registerReturnParameter(1, Types.VARCHAR);
		ps.registerReturnParameter(2, Types.VARCHAR);
		//ps.registerReturnParameter(2, Types.INTEGER);
		int upCount=ps.executeUpdate();
		System.out.println("Updated Rows Count  = "+upCount);
		ResultSet rs =ps.getReturnResultSet();

		while(rs.next()) {
			System.out.println("part_name = "+rs.getString(1));
	/*		int sz = rs.getMetaData().getColumnCount();
			//ArrayList ss = new ArrayList();
			for ( int i = 1 ; i <= sz ; i++ ) {
				String v = rs.getString(i);
				System.out.println(v);
			}
			//System.out.println("part_name = "+rs.getNString("part_name"));
			//System.out.println("part_number = "+rs.getString(2));
			//System.out.println("part_name = "+rs.getInt(2));
			System.out.println("--------------------------------------------");*/
		}
		Thread.sleep(10000);
		//oraConn.rollback();
		if(oraConn.isClosed()) {
			System.out.println("before conn Close check oraConn Closing is done");
		}
		conn.close();
		conn=null;
		if(conn == null) {
			System.out.println("conn is null");
		}
		if(oraConn == null) {
			System.out.println("After conn Close check oraConn Closing is null");
		}
		if(oraConn.isClosed()) {
			System.out.println("After conn Close check oraConn Closing is done");
		}
		
		oraConn.close();
		oraConn=null;
		
		
		
	/*	Statement st =conn.createStatement();
		ResultSet rs=st.executeQuery(sql);
		int c=1;
		while (rs.next()) {
			System.out.println(c+"th row="+rs.getInt(1));
			
		}*/
	}
}
