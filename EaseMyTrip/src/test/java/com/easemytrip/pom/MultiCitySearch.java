package com.easemytrip.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.ExtentTest;
import com.easemytrip.utils.BasePage;

public class MultiCitySearch extends BasePage {
	JavascriptExecutor js=(JavascriptExecutor)driver;
	Actions actions=new Actions(driver);
	public MultiCitySearch(WebDriver driver) {
		super(driver);
	}

	public void scroll(ExtentTest log) {
		log.info("scroll to see all flights");
		js.executeScript("window.scrollBy(0,1200);");// //scroll specified number of pixels down

		System.out.println("first scroll success");
	}
	
	public void modifymsearch(ExtentTest log) throws Exception {
		Thread.sleep(2000);
		js.executeScript("window.scrollBy(0,-1200);");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@class='srchFlbtn'][contains(text(),'Modify')]")).click();
	//	driver.findElement(By.id("crs5")).click();
		Thread.sleep(2000);
	//	driver.findElement(By.id("crs4")).click();
		driver.findElement(By.id("btnSrchMul")).click();
	}
	
	
	public void scrolldown(ExtentTest log) {
		log.info("scroll to see all flights");
		js.executeScript("window.scrollBy(0,1200);");// //scroll specified number of pixels down
		log.info("second scroll success");
	}
	
	public void viewdetails (ExtentTest log) {
		log.info("view details of a particular option");
		driver.findElement(By.id(prop.getProperty("flightidm"))).click();
		log.info("view fare details");
		driver.findElement(By.xpath(prop.getProperty("faredetails"))).click();
		log.info("view cancellation policy");
		driver.findElement(By.xpath(prop.getProperty("cancellationrules"))).click();
		log.info("view terms and conditions");
		WebElement tnc= driver.findElement(By.xpath("//div[contains(@class,'col-sm-12 pad-0 pad-btmm')]//div[3]"));
		actions.moveToElement(tnc);
		js.executeScript("window.scrollBy(0,100);");
		log.info("close details");
		driver.findElement(By.xpath(prop.getProperty("closebuttonm"))).click();
			
	}
	
	public MultiFlightTraveller bookmflights(ExtentTest log) {
		log.info("book flight");
		js.executeScript("window.scrollBy(0,-1200);");
		driver.findElement(By.xpath("//div[contains(@class,'container')]//div[5]//div[2]//div[1]//div[2]//div[1]//div[2]//button[1]")).click();
		
		return new MultiFlightTraveller(driver);
		
		
	}
	
	
	
	
	
	
}
