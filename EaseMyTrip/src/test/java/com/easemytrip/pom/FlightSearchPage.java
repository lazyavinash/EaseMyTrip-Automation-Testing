package com.easemytrip.pom;

import static org.testng.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.easemytrip.utils.BasePage;

public class FlightSearchPage extends BasePage {


	String flightDate,flightNo;

	LocalDate date;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");

	Actions actions=new Actions(driver);
	JavascriptExecutor js=(JavascriptExecutor)driver;
	public FlightSearchPage(WebDriver driver) {
		super(driver);

	}

	public void doSearch(ExtentTest log) throws Exception {
		Thread.sleep(5000);
		//	driver.findElement(By.className("src_btn")).click();	
		log.info("searching for flights");
	}

	public void titleTest(ExtentTest log) throws Exception{
		log.info("verifying Title");
		String title= driver.getTitle();
		log.info("fetching current page Title");
		Assert.assertEquals(title, "FlightList Lowest Airfare, Flight Tickets, Cheap Air Tickets – EaseMyTrip.com");
		log.info("title verification is passed");	
	}

	public void modifySearch(ExtentTest log) throws Exception {

		log.info("modifying destination city");
		driver.findElement(By.id(prop.getProperty("flightto"))).clear();
		WebElement e= driver.findElement(By.id(prop.getProperty("flightto")));	
		e.sendKeys(prop.getProperty("city"));
		Thread.sleep(2000);
		log.info("searching for students");
		WebElement stu=driver.findElement(By.xpath(prop.getProperty("studentxpath")));
		actions.moveToElement(stu).click().perform();
		driver.findElement(By.id(prop.getProperty("modifysearchbutton"))).click();
	
	}



	public void nextDay(ExtentTest log) throws Exception {
		Thread.sleep(2000);
		log.info("check for next day");
		WebElement next= driver.findElement(By.xpath(prop.getProperty("nextday")));
		actions.moveToElement(next).click().perform();
		for (int i=0; i<2; i++) {
			WebElement sliden=driver.findElement(By.className("jssora03r"));
			actions.moveToElement(sliden).click().perform();
		}


	}
	public void prevDay(ExtentTest log) throws Exception {
		log.info("check for previous day");
		WebElement prev= driver.findElement(By.xpath(prop.getProperty("prevday")));
		actions.moveToElement(prev).click().perform();


	}

	public void amount(ExtentTest log) throws Exception{
		log.info("check if amount is displayed");
		assertTrue(driver.findElement(By.id("amount")).isDisplayed());
	}

	public void firstScroll(ExtentTest log) {
		log.info("scroll to see all flights");
		js.executeScript("window.scrollBy(0,300);");// //scroll specified number of pixels down

		System.out.println("first scroll success");
	}


	public void viewDetails(ExtentTest log) throws InterruptedException {
		Thread.sleep(3000);
		log.info("view details of a particular flight");
		driver.findElement(By.id(prop.getProperty("flightid"))).click();
		log.info("view fare details");
		driver.findElement(By.xpath(prop.getProperty("faredetails"))).click();
		log.info("view baggage information");
		driver.findElement(By.xpath(prop.getProperty("baggageinformation"))).click();
		log.info("view cancellation policy");
		driver.findElement(By.xpath(prop.getProperty("cancellationrules"))).click();
		log.info("close details");
		driver.findElement(By.xpath(prop.getProperty("closebutton"))).click();
		log.info("view fare options");
		driver.findElement(By.xpath(prop.getProperty("checkfareoptions"))).click();
		log.info("view flight details success");	
	}

	public void filter(ExtentTest log) throws InterruptedException {


		log.info("add one stop filter");
		driver.findElement(By.xpath(prop.getProperty("onestopfilter"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(prop.getProperty("onestopfilter"))).click();
		log.info("add airline filter");
		driver.findElement(By.xpath(prop.getProperty("airlinefilter"))).click();

		log.info("filter success");
	}

	public void secondScroll(ExtentTest log) {
		log.info("scroll to choose flight");
		js.executeScript("window.scrollBy(0,-300);");
		log.info("second scroll success");
	}
	

	public FlightTraveller bookFilterFlight(ExtentTest log) {
		log.info("book now");
	//	List<WebElement> ls=driver.findElements(By.xpath(prop.getProperty("filteredflight")));
	//	System.out.println("Total available flights "+ls.size());
		
		flightDate = driver.findElement(By.xpath("//div[@class='min-ht ng-scope chk_index']//div//div//div")).getText()+" 2020";
		date = LocalDate.parse(flightDate, formatter);
		
		String flightNo1 = driver.findElement(By.xpath("//div[contains(@class,'col-md-12 col-sm-12 no-padd')]//div//div[1]//div[1]//div[1]//div[6]//button[1]//ancestor::div[2]//div//div[1]//div[2]//span[2]//span[1]")).getText();
		String flightNo2 = driver.findElement(By.xpath("//div[contains(@class,'col-md-12 col-sm-12 no-padd')]//div//div[1]//div[1]//div[1]//div[6]//button[1]//ancestor::div[2]//div//div[1]//div[2]//span[2]//span[2]")).getText();
		flightNo = flightNo1+"-"+flightNo2;
		
	//	ls.get(2).click();
		driver.findElement(By.xpath(prop.getProperty("filteredflight"))).click();
		
		return new FlightTraveller(driver, flightNo, date);
	}

}
