package com.file;

import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelRead {

	public ExcelRead() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		File file=new File("A:\\temp\\bone.xlsx");
		Workbook wb = null;
		Sheet sheet = null;
		Row row = null;
		try {
			wb=WorkbookFactory.create(file);
			sheet = wb.getSheetAt(0);
			int rows = sheet.getPhysicalNumberOfRows();

			for ( int r = 0; r < rows; r++) {			//  엑셀 대상자 리스트 엑셀의 두번째 행부터 Row 수 만큼 반복
				row = sheet.getRow(r);
				int firRowCnt = sheet.getRow(0).getPhysicalNumberOfCells();
				
				for(int i=0; i<firRowCnt; i++){	
					String tempValue=row.getCell(i).toString().trim();
					System.out.print(tempValue);
					if((i+1)<firRowCnt) {
						System.out.print("|");
					}else {
						System.out.println();
					}
				}
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@");
			}
			

		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
