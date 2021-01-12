package com.easemytrip.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.easemytrip.utils.BasePage;

public class SelectedHotel extends BasePage {

	public SelectedHotel(WebDriver driver) {
		super(driver);
	} 

	
	public void showallAmenities(ExtentTest log) throws InterruptedException {
		log.info("Clicking on Show All Amenities now");
		Thread.sleep(2000);
		driver.findElement(By.linkText(prop.getProperty("showallamenities"))).click();
	}
	public void clickonBookNow(ExtentTest log) throws InterruptedException {
		log.info("Clicking on Book now");
		Thread.sleep(4000);
		driver.findElement(By.xpath(prop.getProperty("booknowbtn"))).click();
	}
	public void scrolltoreviews(ExtentTest log) throws Exception {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		
		js.executeScript("window.scrollBy(0,document.body.scrollHeight);"); //scroll down
		Thread.sleep(3000);
	
		js.executeScript("window.scrollBy(0,-document.body.scrollHeight);");
	}
}
