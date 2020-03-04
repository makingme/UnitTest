package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {

	public DBConnection() {
		// TODO Auto-generated constructor stub
	}
	
	
	static Connection conn=null;
	
	
	public static Connection getConnection(String address, String id, String passwd)
	{	
	//	System.out.println("드라이버 로딩 시도 중..");
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn= DriverManager.getConnection("jdbc:oracle:thin:@"+address, id,passwd);
			
			if(conn.isValid(1000)) {
		//		System.out.println("DB Connection is Ok");
			}else {
				System.out.println("DB Connection is not valid");
			}
				
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException_JDBC LIBRARY CHECK");
			e.printStackTrace();
		} catch(SQLException e){
			System.out.println("DBMS CONNECT FAILE");
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static Connection getConnection()
	{	
		System.out.println("드라이버 로딩 시도 중..");
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn= DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.120:1521:xe", "IBK_SMS","odinue2014");
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
