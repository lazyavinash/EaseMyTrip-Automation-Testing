package com.easemytrip.utils1;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;



public class KeyWords {
	WebDriver driver;
 public KeyWords(WebDriver driver) {
		
		this.driver = driver;
	}
public void getURL(String url) {
	driver = HelperFunctions.setBrowser("chrome");
	driver.get(url);
	//driver.get("https://www.facebook.com/");
	 
 }

public void click(String locator) {
	String loctype = locator.split(":=")[0];
	String locvalue = locator.split(":=")[1];
	if(loctype.equals("id"))
		driver.findElement(By.id(locvalue)).click();
	else if(loctype.equals("name"))
		driver.findElement(By.name(locvalue)).click();
	else if(loctype.equals("link"))
		driver.findElement(By.linkText(locvalue)).click();
	else if(loctype.equals("css"))
		driver.findElement(By.cssSelector(locvalue)).click();
	else if(loctype.equals("xpath"))
		driver.findElement(By.xpath(locvalue)).click();
	else
		System.out.println("Invalid selector type");
}
 public void type(String locator, String value) {
	 String loctype = locator.split(":=")[0];
		String locvalue = locator.split(":=")[1];
		if(loctype.equals("type"))
			driver.findElement(By.id(locvalue)).sendKeys(value);
		}
 public void selectFromDropDown(String locator, int val) {
	 WebElement w = null;
	 String loctype = locator.split(":=")[0];
		String locvalue = locator.split(":=")[1];
		
		if(loctype.equals("name"))
			 w = driver.findElement(By.id(locvalue));
		Select sc = new Select(w);
		sc.selectByIndex(val);
		}

	 
 
 public void getSnap(String fileNameWithLocation) {
	 TakesScreenshot tc = (TakesScreenshot)driver;
	 File scFile = tc.getScreenshotAs(OutputType.FILE);
	 try {
		 FileHandler.copy(scFile, new File(fileNameWithLocation));
		 
	 }
	 catch (IOException e) {
		 System.out.println("Error with screenshot");
	 }
	 
 }
 public void closeBrowser() {
	 driver.quit();
 }
 
}
