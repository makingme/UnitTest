/**
 * 
 */
package com.java.web;

import java.awt.Desktop;
import java.io.*;
/**

  * @FileName : ExecuteHtml.java

  * @Project : UnitTest

  * @Date : 2021. 5. 12. 

  * @작성자 : kibumkim

  * @변경이력 :

  * @프로그램 설명 :

  */
public class ExecuteHtml {
	public static void main(String[] args) throws Exception {
        File f = new File("source.htm");
        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        bw.write("<html><body><h1>Blah, Blah!</h1>");
        bw.write("<textarea cols=75 rows=10>");
        for (int ii=0; ii<20; ii++) {
            bw.write("Blah blah..");
        }
        bw.write("</textarea>");
        bw.write("</body></html>");
        bw.close();

        Desktop.getDesktop().browse(f.toURI());
    }
}
