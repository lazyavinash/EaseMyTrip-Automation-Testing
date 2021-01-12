package com.easemytrip.runner;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.easemytrip.pom.HotelTravellerDetails;
import com.easemytrip.utils.Base;
import com.easemytrip.utils.ExcelReader;

public class HotelTravellerDetailsTestRunner extends Base {
	HotelTravellerDetails fp ;
	
	@Test(priority = 1, description= "Click on book now test")
	public void clickbook() throws Exception {
		driver.get(prop.getProperty("URL"));
		 fp = new HotelTravellerDetails(driver);
		fp.clickonbook(testLog);

		Thread.sleep(5000);
	}

	@Test(priority = 2,description= "Title selecting test")
	public void selectTitle() {
		
		fp.selectTitle(testLog);
		try {
			new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.name("ddlGender")));
		} catch (Exception E) {
			throw new AssertionError("Select Title Failed");
		}
	}

	/*@Test(priority = 3)
	public void enterdetails() {

		HotelTravellerDetails fp = new HotelTravellerDetails(driver);
		ExcelReader ex = new ExcelReader(prop.getProperty("excelpath"));
		int j = 0;

		for (int i = 1; i < ex.getRowNum("Sheet1"); i++) {
		
			j = 0;
			try {
			while (j == 0) {

				fp.enterdetails(ex.getCellData("Sheet1", i, j), ex.getCellData("Sheet1", i, j + 1),
						ex.getCellData("Sheet1", i, j + 2), ex.getCellData("Sheet1", i, j + 3),ex.getCellData("Sheet1", i, j + 4),ex.getCellData("Sheet1", i, j + 5)
						,testLog);
				j++;
				
				
			}
			fp.clickoncheckbox(testLog);
			fp.clickonPay(testLog);
				
		}catch(Exception e) {
			testLog.info("Invalid data");
			continue;
		}
				
	}
	}*/

	
	
	@DataProvider
	public Object[][] dp() {
		ExcelReader ex = new ExcelReader((prop.getProperty("excelpathHTD"))); //excel file location from property
		int row = ex.getRowNum(prop.getProperty("sheetNameHTD"));  //get excel sheet name from property
		
		Object[][] data = new Object[row-1][6]; 
		for(int i = 0; i <row-1;i++) {
			data[i][0]=ex.getCellData(prop.getProperty("sheetNameHTD"), i+1, 0);
			data[i][1]=ex.getCellData(prop.getProperty("sheetNameHTD"), i+1, 1);
			data[i][2]=ex.getCellData(prop.getProperty("sheetNameHTD"), i+1, 2);
			data[i][3]=ex.getCellData(prop.getProperty("sheetNameHTD"), i+1, 3);
			data[i][4]=ex.getCellData(prop.getProperty("sheetNameHTD"), i+1, 4);
			data[i][5]=ex.getCellData(prop.getProperty("sheetNameHTD"), i+1, 5);

		}
		return data;
	}
	
	
	@Test(priority = 3,dataProvider = "dp",description= "Traveller details test")
	public void enterdetails(String fname, String lname,String fname2,String lname2,String email,String contact) //same sequence as taken values from data provider
	{
	
		
		fp.enterdetails(fname, lname, fname2, lname2, email, contact);
		/*boolean a=fp.isChecked();
		if(!a) {
			fp.clickoncheckbox(testLog);
		}*/
		fp.clickoncheckbox(testLog);
		fp.clickonPay(testLog);
			
	
		
	}
		
}
	

	
	/*
	@Test(priority = 4)
	public void checkbox()  {
		HotelTravellerDetails fp = new HotelTravellerDetails(driver);
		fp.clickoncheckbox(testLog);		
	}
	
	@Test(priority = 5)
	public void clickonpay() {
		HotelTravellerDetails fp = new HotelTravellerDetails(driver);
		fp.clickonPay(testLog);	
	      
	}
	*/

	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


