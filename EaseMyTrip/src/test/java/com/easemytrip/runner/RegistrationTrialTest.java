package com.easemytrip.runner;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.easemytrip.pom.RegistrationTrial;
import com.easemytrip.utils.Base;
import com.easemytrip.utils.ExcelReader;

public class RegistrationTrialTest extends Base {
	String username,password;

	@Test(dataProvider = "dp",priority = 1, description = "Register using valid details")
	public void registerTestValid(String username, String password) {
		
		driver.get(prop.getProperty("url"));
		RegistrationTrial rt=new RegistrationTrial(driver);
		rt.doRegister(username,password,testLog);
		try {
			new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='divLogin']//div[@class='fr_rules2'][contains(text(),'Login')]")));
			driver.findElement(By.xpath("//div[@id='divLogin']//i[@class='cr_hp_i']")).click();
		} catch (Exception E) {
			System.out.println(E);
			//throw new AssertionError("Registration Test Failed");
			testLog.info("Error Present");
		}

	}
	
	
	@Test(dataProvider = "invalidData", priority = 2, description = "Register using invalid Details")
	public void registerTestInvalid(String username, String password) {
		
		driver.get(prop.getProperty("url"));
		RegistrationTrial rt=new RegistrationTrial(driver);
		rt.doRegister(username,password,testLog);
		try {
			new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='divLogin']//div[@class='fr_rules2'][contains(text(),'Login')]")));
			driver.findElement(By.xpath("//div[@id='divLogin']//i[@class='cr_hp_i']")).click();
			throw new AssertionError("Registration Test Failed");
		} catch (Exception E) {
			System.out.println(E);
			//throw new AssertionError("Registration Test Failed");
			//testLog.info("Error Present");
		}

	}


	@DataProvider
	public Object[][] dp() { // Rows Col
		Object data[][] = new Object[1][2];
		ExcelReader ex = new ExcelReader(prop.getProperty("excelfile1"));
		for (int i = 1; i <ex.getRowNum("Sheet1"); i++) {
			data[i - 1][0] = ex.getCellData("Sheet1", i, 0);
			data[i - 1][1] = ex.getCellData("Sheet1", i, 1);
		}
		return data;
	}
	
	
	@DataProvider
	public Object[][] invalidData() throws NullPointerException{ // Rows Col
		Object data[][] = new Object[3][2];
		ExcelReader ex = new ExcelReader(prop.getProperty("excelfile2"));
		for (int i = 1; i < ex.getRowNum("Sheet1"); i++) {
			data[i - 1][0] = ex.getCellData("Sheet1", i, 0);
			data[i - 1][1] = ex.getCellData("Sheet1", i, 1);
		}

		return data;
	}
	
}
