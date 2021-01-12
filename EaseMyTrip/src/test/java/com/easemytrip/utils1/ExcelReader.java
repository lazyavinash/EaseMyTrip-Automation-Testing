package com.easemytrip.utils1;

import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
private Workbook wb;
	public ExcelReader(String fileNameWithLocation) {
		try {
			FileInputStream inputFile = new FileInputStream(fileNameWithLocation);
			if(fileNameWithLocation.endsWith(".xlsx")) {
				wb = new XSSFWorkbook(inputFile);
			}
			else if(fileNameWithLocation.endsWith(".xls")) {
				wb = new HSSFWorkbook(inputFile);
				
			}
			else
				System.out.println("Invalid file format");
		}
		catch(Exception e) {
			System.out.println("Error with file reading");
			System.out.println("Make sure it is .xlsx or .xls "+ e.getMessage());
		}
	}
	
	public String getCellData(String sheetName, int row, int col) {
	  String data	= wb.getSheet(sheetName).getRow(row).getCell(col).toString();
	  return data;
	}
	
	public int getRowNum(String sheetName) {
		return wb.getSheet(sheetName).getLastRowNum()+1;
	}
	public int getColumn(String sheetName) {
		return wb.getSheet(sheetName).getRow(0).getLastCellNum();
	}

}
