package com.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CLOBHandler2 {

	public CLOBHandler2() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) throws Exception {
		Connection conn=DBConnection.getConnection("112.216.241.98:21521:XE","SMSADMIN" ,"m1m2m3" );
		CLOBHandler2 cbh= new CLOBHandler2();
		cbh.addRowToCoffeeDescriptions(1, "A:\\workspace\\NEW_NV\\WebContent\\campaign\\smsCampaign.jsp", conn);
		
		//cbh.retrieveExcerpt(conn);

	}
	
	
	public void addRowToCoffeeDescriptions(int num, String fileName,Connection con)   throws SQLException {

		    PreparedStatement pstmt = null;
		    try {
/*		        Clob myClob = con.createClob();
		   
		        Writer clobWriter = myClob.setCharacterStream(1);
		        String str = readFile(fileName, clobWriter);
		     
		       
		        System.out.println("Length of STR: " + str.length());
		        System.out.println("Length of Clob: " + myClob.length());
		       
		        System.out.println("readFile str: "+str);
		        System.out.println("myClob toString: " + myClob.getSubString(1,(int)myClob.length()));
		        
		        String sql = "insert into TBL_LOB (NO,TXT_FILE) values(?,?)";*/
		    	
		    	File file = new File(fileName);
				InputStreamReader rdr = new InputStreamReader( new FileInputStream(file));
				int fileSize = (int)file.length();
				
			    String sql = "begin insert into TBL_LOB (NO,TXT_FILE) values(?,?)  delete aaaa end;";
		    	
		        pstmt = con.prepareStatement(sql);
		        pstmt.setInt(1, num);
		       // pstmt.set
		    	pstmt.setCharacterStream(2, rdr, fileSize);
		        System.out.println(fileSize);
		      //pstmt.setClob(2, rdr, file.length());
		  
		    	//pstmt.setBinaryStream(2, new FileInputStream(file), fileSize);
		//        pstmt.setC
		        pstmt.executeUpdate();
		        
		       
		    }  catch (Exception ex) {
		      System.out.println("Unexpected exception: " + ex.toString());
		    } finally {
		        if (pstmt != null)pstmt.close();
		    }
	}
	
	private String readFile(String fileName, Writer writerArg)  throws FileNotFoundException, IOException {

	    BufferedReader br = new BufferedReader(new FileReader(fileName));
	    String nextLine = "";
	    StringBuffer sb = new StringBuffer();
	    while ((nextLine = br.readLine()) != null) {
	        //System.out.println("Writing: " + nextLine);
	        writerArg.write(nextLine);
	        sb.append(nextLine);
	    }
	    writerArg.flush();
	    // Convert the content into to a string
	    String clobData = sb.toString();

	    // Return the data.
	    return clobData;
	}
	
	
	public String retrieveExcerpt(Connection con)
		    throws Exception {

		    String description = null;
		    Clob myClob = null;
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
		         /*   myClob = rs.getClob(1);
		            System.out.println("Length of retrieved Clob: " +  myClob.length());*/
		        	 rd = rs.getCharacterStream("TXT_FILE");
		        	 char[] buf = new char[1024];
		        	 int readcnt;
		        	 while ((readcnt = rd.read(buf, 0, 1024)) != -1) {
		        	 sb.append(buf, 0, readcnt);
		        	 }
		        }
		        //description = myClob.getSubString(1, 38169);
		        System.out.println(sb.toString());
		    }catch (Exception ex) {
		        System.out.println("Unexpected exception: " + ex.toString());
		    } finally {
		        if (pstmt != null) pstmt.close();
		        if(rd !=null)rd.close();
		    }
		    return description;
		}
	
}
