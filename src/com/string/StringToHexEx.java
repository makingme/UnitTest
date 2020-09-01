/**
 * 
 */
package com.string;

/**

  * @FileName : StringToHexEx.java

  * @Project : UnitTest

  * @Date : 2020. 8. 25. 

  * @작성자 : kibumkim

  * @변경이력 :

  * @프로그램 설명 :

  */
public class StringToHexEx {
	 public static byte[] hexToByteArray(String hex) {
	     if (hex == null || hex.length() == 0) {
	         return null;
	     }
	  
	     byte[] ba = new byte[hex.length() / 2];
	     for (int i = 0; i < ba.length; i++) {
	    	 System.out.println(i);
	    	 System.out.println(hex.substring(2 * i, 2 * i + 2));
	    	 System.out.println(Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16));
	         ba[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
	         
	     }
	     return ba;
	 }
	public static void main(String[] args) {
		try {
			byte [] aa= StringToHexEx.hexToByteArray("d230dc1df9da9d78fe96cf10R62b9d30R31393c2546062a742e69c96785ac492");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		
		//System.out.println("10진수 -> 16진수");

		//System.out.println("100 -> " +Integer.toHexString(100));


	}
}
