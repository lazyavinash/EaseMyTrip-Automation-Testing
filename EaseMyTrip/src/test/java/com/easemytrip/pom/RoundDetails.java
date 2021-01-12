package com.easemytrip.pom;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class RoundDetails {

   WebDriver driver;
   String flight1No;
	String flight2No;
	String flight1Date;
	String flight2Date;

	LocalDate date1;
	LocalDate date2;

	DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("E dd-MMM-yyyy"); //formatter for localdate
	By bookRWNow = By.xpath("//div[@id='BtnBookNow']"); //bookNow button xpath
	public RoundDetails(WebDriver driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	public RoundDetails getDetails() throws InterruptedException {
		// TODO Auto-generated method stub
		driver.findElement(By.xpath("/html[1]/body[1]/form[1]/div[9]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]")).click();
		WebElement e =driver.findElement(By.xpath("/html[1]/body[1]/form[1]/div[9]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]"));
		Actions actions = new Actions(driver);
		Thread.sleep(1000);
	    actions.moveToElement(e).click().perform();  
	    Thread.sleep(1000);
	    //testing previous day and next day button
	  // WebElement n = driver.findElement(By.id("depNext"));
	    //actions.moveToElement(n).click().perform();
	    //Thread.sleep(3000);
	    //WebElement p = driver.findElement(By.xpath("/html[1]/body[1]/form[1]/div[9]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/button[1]"));
	    
	    //actions.moveToElement(p).click().perform();
	   
		//selecting up flight
		List <WebElement> ls = driver.findElements(By.xpath("/html[1]/body[1]/form[1]/div[9]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[*]/div[1]"));	
	    System.out.println(ls.size());
	    int flight_up = (int) (Math.random()*(ls.size()-1+1)+1); 
	    System.out.println("random flight "+ flight_up);
	    WebElement element = driver.findElement(By.xpath(" /html[1]/body[1]/form[1]/div[9]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div["+flight_up+"]/div[1]/div[7]/ul[1]/li[1]/div[1]"));
	    Thread.sleep(1000);
	    Actions actions2 = new Actions(driver);
	    try {
	    Thread.sleep(1000);
	    actions2.moveToElement(element).click().perform();    
	    }
	    catch (Exception ex) {
	    	Thread.sleep(1000);
		    actions2.moveToElement(element).click().perform();
	    }
		Thread.sleep(1000);
	     //selecting down flight
	    List <WebElement> lsd = driver.findElements(By.xpath("/html[1]/body[1]/form[1]/div[9]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[1]/div[*]/div[1]"));	
	    System.out.println(lsd.size());
	    if(lsd.size()==0) {
	    	return new RoundDetails(driver);
	    }
	    else {
	    int flight_down = (int) (Math.random()*(lsd.size()-1+1)+1); 
	    System.out.println("random flight "+ flight_down);
	    WebElement ed = driver.findElement(By.xpath(" /html[1]/body[1]/form[1]/div[9]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[1]/div["+flight_down+"]/div[1]/div[7]/ul[1]/li[1]/div[1]"));
	    Thread.sleep(1000);
	    Actions actions3 = new Actions(driver);
	    try {
	    Thread.sleep(1000);
	    actions3.moveToElement(ed).click().perform();  
	    }
	    catch (Exception fl) {
	    	Thread.sleep(1000);
		    actions3.moveToElement(ed).click().perform();  
	    }
	    Thread.sleep(1000);
	    
	    return new RoundDetails(driver);}
	}
	public FlightRWTraveller verifyDetails() throws InterruptedException {
		
		
			
			//firstFlightDetails
			String flight1No1 = driver.findElement(By.xpath("//div[@id='BtnBookNow']//ancestor::div[4]//div[1]//div[1]//div[1]//span[1]//span[2]//span[1]")).getText();
			String flight1No2 = driver.findElement(By.xpath("//div[@id='BtnBookNow']//ancestor::div[4]//div[1]//div[1]//div[1]//span[1]//span[2]//span[2]")).getText();
			flight1Date = driver.findElement(By.xpath("//div[@class='col-md-3 col-sm-5 col-xs-12 no-margn no-pad']//div[1]")).getText();
			date1 = LocalDate.parse(flight1Date, formatter2);
			flight1No = flight1No1+"-"+flight1No2;
			
			//secondFlightDetails
			String flight2No1 = driver.findElement(By.xpath("//div[@id='BtnBookNow']//ancestor::div[4]//div[2]//div[1]//div[1]//span[1]//span[2]//span[1]")).getText();
			String flight2No2 = driver.findElement(By.xpath("//div[@id='BtnBookNow']//ancestor::div[4]//div[2]//div[1]//div[1]//span[1]//span[2]//span[2]")).getText();
			flight2No = flight2No1+"-"+flight2No2;
			flight2Date = driver.findElement(By.xpath("//div[@class='col-md-3 col-sm-4 col-xs-12 no-margn no-pad']//div[1]")).getText();
			date2 = LocalDate.parse(flight2Date, formatter2);
			
			System.out.println("Avaliable 1: "+flight1No+" "+date1);
			System.out.println("Avaliable 2: "+flight2No+" "+date2);
			
			//System.out.println(date1+" "+date2);
			driver.findElement(bookRWNow).click();
			return new FlightRWTraveller(driver, flight1No, date1, flight2No, date2);
		
	}

}
