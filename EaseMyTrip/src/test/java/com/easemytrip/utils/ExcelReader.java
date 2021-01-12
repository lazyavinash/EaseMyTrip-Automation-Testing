package com.easemytrip.utils;

import java.io.FileInputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	private Workbook wb;
	
	/* Excel Reader function is used to read data from excel files wthere it is xlsx or xls*/
	
	public ExcelReader(String fileNameWithLocation) {
		try {
			FileInputStream inputFile = new FileInputStream(fileNameWithLocation);
			if (fileNameWithLocation.endsWith(".xlsx")) {
				wb = new XSSFWorkbook(inputFile);
			} else if (fileNameWithLocation.endsWith(".xls")) {
				wb = new HSSFWorkbook(inputFile);
			} else
				System.out.println("Invalid File Format...Kindly use .xls/.xlsx");
		} catch (Exception E) {
			System.out.println("Error with File Reading");
			System.out.println("Make sure it is .xls/.xlsx " + E.getMessage());
		}
	}
	
	/* To get Data in String Format*/
	
	public String getCellData(String sheetName, int row, int col) {
		String data;
		try {
		data= wb.getSheet(sheetName).getRow(row).getCell(col).toString();
		}catch(NullPointerException n) {
			data=" ";
		}
		return data;
	}
	
	/* To get Data in int Format*/
	
	public int getCellDataInt(String sheetName, int row, int col) {
		Cell data = wb.getSheet(sheetName).getRow(row).getCell(col);
		int data1=(int)data.getNumericCellValue();
		return data1;
	}
	
	/* to get number of rows*/
	
	public int getRowNum(String sheetName) {
		return wb.getSheet(sheetName).getLastRowNum()+1;
	}
	
	/* to get number of Columns*/
	
	public int getColumnNum(String sheetName) {
		return wb.getSheet(sheetName).getRow(0).getLastCellNum();
	}
}
