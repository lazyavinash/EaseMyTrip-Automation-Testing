package com.easemytrip.runner;

import java.awt.Checkbox;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.easemytrip.pom.AvailableHotelList;
import com.easemytrip.utils.Base;

public class AvailableHotelListRunner extends Base {

	@Test(priority = 1,description= "Easy Stay Test")
	public void clickeasybtn() throws Exception {
		driver.get(prop.getProperty("URL1"));
		AvailableHotelList ah = new AvailableHotelList(driver);
		ah.clickonEasyStay(testLog);

		try {
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Easy Stay")));
		} catch (Exception E) {
			throw new AssertionError("Could not select Easy Stay");
		}
	}

	@Test(priority = 2,description= "Luxury Stay Test")
	public void clickluxeasybtn() throws Exception {

		AvailableHotelList ah = new AvailableHotelList(driver);
		ah.clickonLuxEasyStay(testLog);
		try {
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Luxury Easy Stay")));
		} catch (Exception E) {
			throw new AssertionError("Could not select Luxury Easy Stay");
		}

	}

	@Test(priority = 3,description= "Browse all hotels test")
	public void clickbrowseall() throws Exception {

		AvailableHotelList ah = new AvailableHotelList(driver);
		ah.clickonbrowseall(testLog);
		try {
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Browse All Hotels")));
		} catch (Exception E) {
			throw new AssertionError("Could not select Browse all");
		}
	}

	@Test(priority = 5,description= "Trip advisor test")
	public void chcktripadvisor() throws Exception {

		AvailableHotelList ah = new AvailableHotelList(driver);
		ah.chcktripfilter(testLog);
		try {
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Browse All Hotels")));
		} catch (Exception E) {
			throw new AssertionError("Couldn't load");
		}
	}

	@Test(priority = 4,description= "Sort Hotels")
	public void sorthotels() throws Exception {

		AvailableHotelList ah = new AvailableHotelList(driver);
		ah.sortby(testLog);
		try {
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Luxury Easy Stay")));
		} catch (Exception E) {
			throw new AssertionError("Could not select sort dropdown");
		}

	}
	
	@Test(priority = 6,description= "Amenities filter test")
	public void chckamenities() throws Exception {

		AvailableHotelList ah = new AvailableHotelList(driver);
		ah.chckamenitiesfilter(testLog);
		try {
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Browse All Hotels")));
		} catch (Exception E) {
			throw new AssertionError("Couldn't load");
		}

	}

}
