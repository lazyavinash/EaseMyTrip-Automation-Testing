package com.easemytrip.pom;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.easemytrip.utils.BasePage;

public class AvailableHotelList extends BasePage {

	public AvailableHotelList(WebDriver driver) {
		super(driver);
	}

	public void clickonEasyStay(ExtentTest log) {
		log.info("Clicking on Easy Stay");
		driver.findElement(By.linkText(prop.getProperty("easystaybtn"))).click();
	}

	public void clickonLuxEasyStay(ExtentTest log) throws InterruptedException {

		log.info("Clicking on Luxury Easy Stay");
		Thread.sleep(2000);
		driver.findElement(By.linkText(prop.getProperty("luxeasystaybtn"))).click();

	}


	public void sortby(ExtentTest log) throws Exception {
		log.info("On sorting Hotels");
		Select s = new Select(driver.findElement(By.id("drpHighList")));
		Thread.sleep(3000);
		s.selectByValue("price|ASC");
		Thread.sleep(3000);
		s.selectByValue("price|DESC");
		Thread.sleep(3000);
		s.selectByValue("Popularity");
		Thread.sleep(3000);

	}

	public void clickonbrowseall(ExtentTest log) throws InterruptedException {

		log.info("Clicking on Browse All");
		Thread.sleep(2000);
		driver.findElement(By.linkText(prop.getProperty("browseallbtn"))).click();
		Thread.sleep(2000);

	}

	public void chcktripfilter(ExtentTest log) throws Exception {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,300);"); //scroll down
		log.info("Clicking on 5 star rating");
		Thread.sleep(2000);
		WebElement box= driver.findElement(By.xpath(prop.getProperty("tripadvisorfilter")));
		box.click();
		Thread.sleep(2000);
		box.click();
		Thread.sleep(2000);
		Assert.assertEquals(false,box.isSelected());
		Thread.sleep(2000);

	}

	public void chckamenitiesfilter(ExtentTest log) throws Exception{
		log.info("Clickin on amenities");
		Thread.sleep(2000);
		WebElement box=driver.findElement(By.xpath(prop.getProperty("amenitiesfilter")));
		box.click();
		Thread.sleep(4000);
		box.click();
		Assert.assertEquals(false,box.isSelected());

	}

	public SelectedHotel listpagebooknow(ExtentTest log) {
		log.info("Clicking on book now button");
		String pid=driver.getWindowHandle();
		Actions ac=new Actions(driver);
		WebElement w=driver.findElement(By.xpath(prop.getProperty("listpagebookbtn")));
		ac.moveToElement(w).click().perform();
		Set<String> wind=driver.getWindowHandles();
		for(String s:wind) {
			if(!s.equals(pid)) {
				driver.switchTo().window(s);
			}

		}
		return new SelectedHotel(driver);
	}









}
