package com.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CLOBHandler {
	final static long maxMem=Runtime.getRuntime().maxMemory();
	final static long totalMem=Runtime.getRuntime().totalMemory();
	public CLOBHandler() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) throws Exception {
		//Connection conn=DBConnection.getConnection("112.216.241.98:21521:XE","SMSADMIN" ,"m1m2m3" );
		//PreparedStatement pstmt = null;
		
	      //path=Class.forName("java.sql.Connection").getProtectionDomain().;
		System.out.println("Max Memory:\t"+maxMem/1000000);
		System.out.println("total Memory:\t"+totalMem/1000000);
		System.out.println("##########################");
		System.out.println("##########################");
	     // System.out.println("path:"+path);
		CLOBHandler cbh= new CLOBHandler();
		String sql = "begin insert into TBL_LOB (NO,TXT_FILE) values(?,?); end;";
		
		int i=1;
		while(true) {
			Connection conn=null;
			conn=DBConnection.getConnection("112.216.241.98:21521:XE","SMSADMIN" ,"m1m2m3" );
			PreparedStatement pstmt = null;
			pstmt = conn.prepareStatement(sql);
			System.out.println("index:"+i);
			cbh.add100RowToCoffeeDescriptions(1, "A:\\workspace\\NEW_NV\\WebContent\\campaign\\smsCampaign2.jsp", conn,pstmt);
		
			if(i%2==0) {
				int c=cbh.deleteTable(conn);
				System.out.println("Data Delete Count:\t"+c);
			}
			conn.close();
			pstmt.close();
	
			Thread.sleep(10);
			i++;
		}
		
		//cbh.retrieveExcerpt(conn);

	}
	
	
	public void addRowToCoffeeDescriptions(int num, String fileName,Connection con,PreparedStatement pstmt )   throws SQLException {

		   // PreparedStatement pstmt = null;
		    try {
		        Clob myClob = con.createClob();
		        Writer clobWriter = myClob.setCharacterStream(1);
		        String str = readFile(fileName, clobWriter);
		     
		       
		      // System.out.println("Length of STR: " + str.length());
		     //   System.out.println("Length of Clob: " + myClob.length());
		       
		      // System.out.println("readFile str: "+str);
		      //  System.out.println("myClob toString: " + myClob.getSubString(1,(int)myClob.length()));
		        
		       // String sql = "begin insert into TBL_LOB (NO,TXT_FILE) values(?,?); end;";

		        //pstmt = con.prepareStatement(sql);
		        pstmt.setInt(1, num);
		        pstmt.setClob(2, myClob);
		        
		        //pstmt.executeUpdate();
		        pstmt.addBatch();
		        //myClob.free();
		        
		       
		    }  catch (Exception ex) {
		      System.out.println("Unexpected exception: " + ex.toString());
		    }/* finally {
		        if (pstmt != null)pstmt.close();
		    }*/
	}
	public void add100RowToCoffeeDescriptions(int num, String fileName,Connection con,PreparedStatement pstmt )   throws SQLException {

		    try {
		    	for(int i=1;i<=100;i++) {
		    		Clob myClob = con.createClob();
			        Writer clobWriter = myClob.setCharacterStream(1);
			        String str = readFile(fileName, clobWriter);
			        pstmt.setInt(1, num);
			        pstmt.setClob(2, myClob);		    
			        pstmt.addBatch();
		    	}
		    	pstmt.executeBatch();
				long usedMem=totalMem-Runtime.getRuntime().freeMemory();
				long freeMem=maxMem-usedMem;
				System.out.println("used Memory:\t"+usedMem/1000000);
				System.out.println("free Memory:\t"+freeMem/1000000);

		        
		       
		    }  catch (Exception ex) {
		      System.out.println("Unexpected exception: " + ex.toString());
		    }/* finally {
		        if (pstmt != null)pstmt.close();
		    }*/
	}
	
	private String readFile(String fileName, Writer writerArg)  throws FileNotFoundException, IOException {

	    BufferedReader br = new BufferedReader(new FileReader(fileName));
	    String nextLine = "";
	    StringBuffer sb = new StringBuffer();
	   String newline=System.getProperty("line.separator");
	   if(newline ==null){
	    	System.out.println("line.separator is null!! ");
	    	newline="\r\n";
	    }
	   // System.out.println("newline:"+line);
	    while ((nextLine = br.readLine()) != null) {
	        //System.out.println("Writing: " + nextLine);
	    	//nextLine+=System.getProperty("line.separator");
	    	nextLine+=newline;
	        writerArg.write(nextLine);
	       // writerArg.
	        sb.append(nextLine);
	    }
	    writerArg.flush();
	    String clobData = sb.toString();
	    br.close();
	    writerArg.close();
	    return clobData;
	}
	
	public int deleteTable(Connection con) {
		
		 String sql = "DELETE  FROM TBL_LOB";
		 Statement st=null;
		 int c=0;
		 try {
			st=con.createStatement();
			
			c=st.executeUpdate(sql);
		} catch (Exception e) {
			
		}finally {
			try {
				st.close();
				st=null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	     
		
		return c;
	}
	public String retrieveExcerpt(Connection con)
		    throws Exception {

		    String description = null;
		    Clob myClob = null;
		    NClob ss=null;
		    StringBuffer sb = new StringBuffer();
		    PreparedStatement pstmt = null;
		    Reader rd =null;
		    try {
		        String sql =
		            "select TXT_FILE " +
		            "from TBL_LOB " +
		            "where NO = ?";

		        pstmt = con.prepareStatement(sql);
		        pstmt.setInt(1, 1);
		        ResultSet rs = pstmt.executeQuery();

		        if (rs.next()) {
		            myClob = rs.getClob(1);
		            System.out.println("Length of retrieved Clob: " +  myClob.length());
		       // 	 rd = rs.getCharacterStream("TXT_FILE");
		     /*   	 char[] buf = new char[1024];
		        	 int readcnt;
		        	 while ((readcnt = rd.read(buf, 0, 1024)) != -1) {
		        	 sb.append(buf, 0, readcnt);
		        	 }*/
		        }
		        description = myClob.getSubString(1, (int)myClob.length());
		        //System.out.println(sb.toString());
		        System.out.println(description);
		    }catch (Exception ex) {
		        System.out.println("Unexpected exception: " + ex.toString());
		    } finally {
		        if (pstmt != null) pstmt.close();
		        if(rd !=null)rd.close();
		    }
		    return description;
		}
	
}
