package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.odinues.m1.util.Crypto;

public class InsertDummyWithEnc {

	Connection mainCon=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	Crypto cr=new Crypto();
	public InsertDummyWithEnc() {
		// TODO Auto-generated constructor stub
		cr.init();
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
	public void insertDB(Connection mainCon )
	{	

		try {
			String insertSQL="INSERT INTO TEST4"+ 
			"("+
			 "msgkey,		cus_name,		cus_callno,		msg,			cus_acno"+ 

			 ")VALUES ("+
			 " ?,		?,		?,		?,		?"+
			 
			")";

			ps=mainCon.prepareStatement(insertSQL);

			int count=1;
			String num="0101237";
			for(int d=0;d<10000;d++) {
				
				
				ps.setInt(1, d);
				ps.setString(2, String.valueOf(400000-d));
				ps.setString(3, num+String.format("%04d",d));
				ps.setString(4, "T4, "+String.valueOf(d)+"번째 메시지");
				ps.setString(5, cr.encryptData(String.valueOf(40000000-d)));
				ps.addBatch();
				if(d%1000==0) {
					System.out.println(d+"insert!!");
					ps.executeBatch();
					ps.clearBatch();
				}				
			}
			ps.executeBatch();
			ps.clearBatch();
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
	
	public Connection getConnection()
	{	
		System.out.println("드라이버 로딩 시도 중..");
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			mainCon= DriverManager.getConnection("jdbc:oracle:thin:@221.146.36.121:21521:xe", "WEBMAIL","ibk2018");
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException_JDBC LIBRARY CHECK");
			e.printStackTrace();
		} catch(SQLException e){
			System.out.println("DBMS CONNECT FAILE");
			e.printStackTrace();
		}
		return mainCon;
	}
	
	public static void main(String[] args) {
		InsertDummyWithEnc in= new InsertDummyWithEnc();
		in.mainCon=in.getConnection();
		in.insertDB(in.mainCon);
		in.clear();
	}
}
