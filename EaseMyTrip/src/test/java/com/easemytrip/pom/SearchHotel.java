package com.easemytrip.pom;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.easemytrip.utils.BasePage;


public class SearchHotel extends BasePage{
	
	public SearchHotel(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	/*WebDriver driver;
	public SearchHotel(WebDriver driver) {
		this.driver=driver;
		
	}*/
	
	//locating elements using PageFactory	
	@FindBy(css="span[class='hp_city']")      //searchbox
	WebElement searchbox;
	
	@FindBy(css="input[name='txtCity']")	  //inputcity
	WebElement inputsearch;
		
	@FindBy(css="input[value='Search']")	  //search button
	WebElement searchbutton;
	
	//----click on input city box
	public WebElement ClickBox() {
		return searchbox;
	}
	
	//---enter city name
	public WebElement EnterCity() {
		return inputsearch;
	}
	
	//--------select city from suggestive dropdown
	public void SelectCity(String cityname) throws IOException, InterruptedException {
		List<WebElement> cityOptions= driver.findElements(By.className(prop.getProperty("citynames")));
		for(WebElement option : cityOptions)
		{
			if(option.getText().equalsIgnoreCase(cityname))
			{
				option.click();
				break;
			}
			else {
				driver.findElement(By.cssSelector(prop.getProperty("firstoption"))).click();
			}
		}
	}
	
	//------------select check in date from calendar
	public void SelectCinDate(String cinmonth, int cindate) throws IOException, InterruptedException {
		System.out.println(cinmonth);
		
		
		while(!(driver.findElement(By.cssSelector(prop.getProperty("month")))).getText().contains(cinmonth))
		{
			driver.findElement(By.cssSelector(prop.getProperty("nextmonthbtn"))).click();
		}
		
		@SuppressWarnings("unused")
		List<WebElement> ci_dates = driver.findElements(By.className(prop.getProperty("date")));
		int ci_count= driver.findElements(By.className(prop.getProperty("date"))).size();
		
		for(int j=0; j<ci_count;j++)
		{
			String text=driver.findElements(By.className(prop.getProperty("date"))).get(j).getText();
			int text1=Integer.parseInt(text);
			if(text1==cindate)
			{
				driver.findElements(By.className(prop.getProperty("date"))).get(j).click();
				break;
				
			}
		}
		
		
	}
	
	//-------------select checkout date from calendar
	public void SelectCoutDate(String coutmonth, int coutdate) throws IOException, InterruptedException {
		
		while(!(driver.findElement(By.cssSelector(prop.getProperty("month")))).getText().contains(coutmonth))
		{
			driver.findElement(By.cssSelector(prop.getProperty("nextmonthbtn"))).click();
		}
		
		@SuppressWarnings("unused")
		List<WebElement> co_dates = driver.findElements(By.className(prop.getProperty("date")));
		int co_count= driver.findElements(By.className(prop.getProperty("date"))).size();
		
		for(int j=0; j<co_count;j++)
		{
			String text=driver.findElements(By.className(prop.getProperty("date"))).get(j).getText();
			int text2=Integer.parseInt(text);
			if(text2==coutdate)
			{
				driver.findElements(By.className(prop.getProperty("date"))).get(j).click();
				break;
				
			}
		}
		
		
	}
	
	//-----------add number of guests
	public void AddGuest(int guest) throws IOException, InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(prop.getProperty("guestdropdown"))).click();
		Thread.sleep(2000);
			
		int i;
				
		for(i=0;i<guest-2;i++) {
			driver.findElement(By.cssSelector(prop.getProperty("addbutton"))).click();
		}
		driver.findElement(By.cssSelector(prop.getProperty("donebutton"))).click();
		Thread.sleep(2000);

	}
	//-----------click search button
	public WebElement GoSearch() {
		
		return searchbutton;
		
	
	}
	
}
