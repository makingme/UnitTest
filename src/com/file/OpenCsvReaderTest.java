package com.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVReader;

public class OpenCsvReaderTest {

	public OpenCsvReaderTest() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		try {

			//CSVReader reader = new CSVReader(new InputStreamReader(uploadFile.getInputStream(),"EUC-KR"));
			File f=new File("A:\\sms_bulk_skip.csv");
			
			//CSVReader reader = new CSVReader(new FileReader("A:\\sms_bulk_sample.csv"));
			CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(f),"EUC-KR"));
			List<String[]> datas=reader.readAll();
			System.out.println(datas.size());
			Iterator<String[]> ite=datas.iterator(); 
			while(ite.hasNext()) {
				for(String a:ite.next()) {
					System.out.println(a);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
