package com.easemytrip.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.easemytrip.utils.BasePage;

public class HotelTravellerDetails extends BasePage {

	public HotelTravellerDetails(WebDriver driver) {
		super(driver);
	}

	public void clickonbook(ExtentTest log) {
		//clicking on book now
		log.info("Clicking on book now");
		driver.findElement(By.xpath(prop.getProperty("xpathbookbtn"))).click();
	}

	public void selectTitle(ExtentTest log) {
		log.info("Selecting title");
		WebElement s = driver.findElement(By.name(prop.getProperty("traveller_title")));
		//selecting title 
		Select fp = new Select(s);
		fp.selectByValue(prop.getProperty("title"));
		log.info("selected Title");
	}

	public void enterdetails(String fname, String lname,String fname2,String lname2, String email,String contact)
	{	
		JavascriptExecutor js=(JavascriptExecutor)driver;
		
		js.executeScript("window.scrollBy(0,document.body.scrollHeight);"); //scroll down
		/*log.info("clearing field");*/
		driver.findElement(By.id(prop.getProperty("firstname"))).clear();
		//log.info("Entering first name");
		driver.findElement(By.id(prop.getProperty("firstname"))).sendKeys(fname);
		
		//log.info("clearing field");
		driver.findElement(By.id(prop.getProperty("lastname"))).clear();
		//log.info("Entering last name");
		driver.findElement(By.id(prop.getProperty("lastname"))).sendKeys(lname);
		
		//log.info("clearing field");
		driver.findElement(By.id(prop.getProperty("firstname2"))).clear();
		
	//	log.info("Entering Traveller 2 first name");
		driver.findElement(By.id(prop.getProperty("firstname2"))).sendKeys(fname2);
		
		//log.info("clearing field");
		driver.findElement(By.id(prop.getProperty("lastname2"))).clear();
		//log.info("Entering Traveller 2 last name");
		driver.findElement(By.id(prop.getProperty("lastname2"))).sendKeys(lname2);
		
		//log.info("clearing field");
		driver.findElement(By.id(prop.getProperty("emid"))).clear();
		//log.info("Entering email");
		driver.findElement(By.id(prop.getProperty("emid"))).sendKeys(email);
		
		//log.info("clearing field");
		driver.findElement(By.id(prop.getProperty("cont"))).clear();
	//	log.info("Entering contact no");
		driver.findElement(By.id(prop.getProperty("cont"))).sendKeys(contact);
		
	}
	
	public void clickoncheckbox(ExtentTest log)  {
		
		log.info("Toggle on");
		
		WebElement e=driver.findElement(By.xpath(prop.getProperty("chckbox")));
		/*if(e.isSelected()) {
			System.out.println("reading");
			e.click();
			System.out.println("reading 2");
		}*/
		
		e.click();
		
	}
	public void clickonPay(ExtentTest log) {
		log.info("Clicking on Continue");
		// Clicking on Payment button
		driver.findElement(By.xpath(prop.getProperty("pay"))).click();
		if(isAlertPresent()) {
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
		}
	}
	
	public boolean isAlertPresent() {
		//handling alert
		try {
			driver.switchTo().alert();
			return true;
		}// try
		catch (Exception e) {
			return false;
		}// catch
	}
	
	public boolean isChecked() {
		return driver.findElement(By.id("chkAgree1")).isSelected();
	}
	
	
	

}
