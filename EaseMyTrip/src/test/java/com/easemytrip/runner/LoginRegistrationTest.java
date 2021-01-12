package com.easemytrip.runner;

import java.util.Base64;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.easemytrip.pom.LoginTrial;
import com.easemytrip.pom.RegistrationTrial;
import com.easemytrip.utils.Base;
import com.easemytrip.utils.ExcelReader;



public class LoginRegistrationTest extends Base{

	@Test(dataProvider = "dp",priority = 1, description = "Forgot Password Using valid Data")
	public void loginForgetPasswordTestValid(String un, String pwd) {
		driver.get(prop.getProperty("url"));
		LoginTrial lt=new LoginTrial(driver);
		lt.forgotPassword(un,pwd,testLog);//method for reseting password
		try {
			new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("xpathfgotpfield"))));
			System.out.println("Login successful");
		} catch (Exception E) {
			System.out.println("Error Shown");
			testLog.fail("Password Reset failed");
		}
	}

	@Test(dataProvider = "invalidData",priority = 2, description = "Forgot Password Using Invalid Data")
	public void loginForgetPasswordTestInvalid(String un, String pwd) {
		driver.get(prop.getProperty("url"));
		LoginTrial lt=new LoginTrial(driver);
		lt.forgotPassword(un,pwd,testLog);//method for resetting password
		try {
			new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("xpathfgotpfield"))));
			testLog.fail("Password Reset failed");
		} catch (Exception E) {
			System.out.println("Error Shown");
		}
	}

	@Test(dataProvider = "dp",priority = 3, description = "Login Test for Easemytrip using valid values")
	public void loginTest(String un, String pwd) {
		driver.get(prop.getProperty("url"));
		LoginTrial lt=new LoginTrial(driver);
		lt.doLogin(un,pwd,testLog);//method for logging in
		try {
			new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("xpathforwelcomeuser"))));
			lt.logout();
			testLog.pass("Login Successful");
		} catch (Exception E) {
			System.out.println("Error Shown");
			testLog.fail("Login Failed");
			throw new AssertionError("Login Test Failed");
		}
	}

	@Test(dataProvider = "invalidData",priority = 4, description = "Login Test for Easemytrip using Invalid values")
	public void loginInvalidTest(String un, String pwd) {
		driver.get(prop.getProperty("url"));
		LoginTrial lt=new LoginTrial(driver);
		lt.doLogin(un,pwd,testLog);//method for logging in
		try {
			new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("xpathforwelcomeuser"))));
			lt.logout();
			testLog.pass("Login Successful");
			throw new AssertionError("Login Test Failed");
		} catch (Exception E) {
			System.out.println("Error Shown");
			testLog.info("Error found");
		}
	}

	@DataProvider
	public Object[][] dp() throws NullPointerException{ // Rows Col
		Object data[][] = new Object[2][2];
		ExcelReader ex = new ExcelReader(prop.getProperty("excelfile1"));
		for (int i = 1; i < ex.getRowNum("Sheet1"); i++) {
			data[i - 1][0] = ex.getCellData("Sheet1", i, 0);
			byte[] actbyte= Base64.getDecoder().decode(ex.getCellData("Sheet1",i,1));
			String password=new String(actbyte);
			data[i-1][1]=password;
		}
		return data;
	}

	@DataProvider
	public Object[][] invalidData() throws NullPointerException{ // Rows Col
		Object data[][] = new Object[4][2];
		ExcelReader ex = new ExcelReader(prop.getProperty("excelfile2"));
		for (int i = 1; i < ex.getRowNum("Sheet1"); i++) {
			data[i - 1][0] = ex.getCellData("Sheet1", i, 0);
			byte[] actbyte= Base64.getDecoder().decode(ex.getCellData("Sheet1",i,1));
			String password=new String(actbyte);
			data[i-1][1]=password;
		}
		return data;
	}

	@Test(dataProvider = "dp",priority = 5,description = "Login test using OTP using valid Email and Phone number")
	public void OTPLoginTestValid(String un,String pwd) {
		driver.get(prop.getProperty("url"));
		LoginTrial lt=new LoginTrial(driver);
		lt.loginTestOTP(un);//method for logging in using OTP
		try {
			//new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Create New Account')]")));
			new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("xpathforwelcomeuser"))));
			lt.logout();
		} catch (Exception E) {
			System.out.println(E);
			throw new AssertionError("Login Test Failed");
		}
	}

	@Test(dataProvider = "invalidData",priority = 6,description = "Login test using OTP using incorrect values")
	public void OTPLoginTestInvalid(String un,String pwd) {
		driver.get(prop.getProperty("url"));
		LoginTrial lt=new LoginTrial(driver);
		lt.loginTestOTP(un);//method for logging in using OTP
		try {
			new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("xpathforwelcomeuser"))));
			lt.logout();
			throw new AssertionError("Login Test Failed");
		} catch (Exception E) {
			System.out.println(E);
		}
	}

	@Test(dataProvider = "dp",priority = 7, description = "Register using valid details")
	public void registerTestValid(String username, String password) {
		driver.get(prop.getProperty("url"));
		RegistrationTrial rt=new RegistrationTrial(driver);
		rt.doRegister(username,password,testLog);//method for registering oneself
		try {
			new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("xpathforloginsign"))));
			driver.findElement(By.xpath(prop.getProperty("xpathforloginclose"))).click();
		} catch (Exception E) {
			System.out.println(E);
			testLog.info("Error Present");
		}
	}

	@Test(dataProvider = "invalidData", priority = 8, description = "Register using invalid Details")
	public void registerTestInvalid(String username, String password) {
		driver.get(prop.getProperty("url"));
		RegistrationTrial rt=new RegistrationTrial(driver);
		rt.doRegister(username,password,testLog);//method for registering oneself
		try {
			new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("xpathforloginsign"))));
			driver.findElement(By.xpath(prop.getProperty("xpathforloginclose"))).click();
			throw new AssertionError("Registration Test Failed");
		} catch (Exception E) {
			System.out.println(E);
		}
	}
}
