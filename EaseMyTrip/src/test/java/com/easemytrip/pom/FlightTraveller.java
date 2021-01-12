package com.easemytrip.pom;


import static org.testng.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.aventstack.extentreports.ExtentTest;
import com.easemytrip.utils.Base;
import com.easemytrip.utils.BasePage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlightTraveller extends BasePage {
	
	public FlightTraveller(WebDriver driver, String flightNo, LocalDate date) {
		super(driver);
		this.driver = driver;
		this.date1 = date;
		this.flightNoPrev = flightNo;
	}


	WebDriver driver;
	WebDriverWait wait;


	//Flight Details
	By flightNo = By.xpath(prop.getProperty("flightNo"));
	By flightDate = By.xpath(prop.getProperty("flightDate"));
	DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("E-ddMMMyyyy");
	LocalDate date2;

	//Coupon
	By c_clear = By.xpath(prop.getProperty("c_clear"));
	By c_check = By.id(prop.getProperty("c_check"));
	By c_text = By.xpath(prop.getProperty("c_text"));
	By c_apply = By.xpath(prop.getProperty("c_apply"));
	By c_message = By.xpath(prop.getProperty("c_message"));
	By c_list = By.xpath(prop.getProperty("c_list"));

	//Travel Insurance
	By i_insure = By.id(prop.getProperty("i_insure"));
	By i_notinsure = By.id(prop.getProperty("i_notinsure"));
	By i_radio = By.name(prop.getProperty("i_radio"));

	//Free Cancellation
	By f_yes = By.id(prop.getProperty("f_yes"));
	By f_no = By.id(prop.getProperty("f_no"));
	By f_radio = By.name(prop.getProperty("f_radio"));
	By f_tab = By.id(prop.getProperty("f_tab"));

	//Email Verification
	By e_textField = By.xpath(prop.getProperty("e_textField"));
	By e_continue = By.xpath(prop.getProperty("e_continue"));
	By e_nextSection = By.id(prop.getProperty("e_nextSection"));

	//Contact No Verification
	By co_textField = By.id(prop.getProperty("co_textField"));
	By co_continue = By.xpath(prop.getProperty("co_continue"));
	By co_pay = By.id(prop.getProperty("co_pay"));
	
	//Add Seat Button
	By a_button = By.xpath(prop.getProperty("a_button"));
	By a_panel = By.id(prop.getProperty("a_panel"));

	//Adult Details
	By t_title = By.id(prop.getProperty("t_title"));
	By t_first = By.id(prop.getProperty("t_first"));
	By t_last = By.id(prop.getProperty("t_last"));

	//Child Details
	By ch_title = By.id(prop.getProperty("ch_title"));
	By ch_first = By.id(prop.getProperty("ch_first"));
	By ch_last = By.id(prop.getProperty("ch_last"));

	//Infant Details
	By in_title = By.id(prop.getProperty("in_title"));
	By in_first = By.id(prop.getProperty("in_first"));
	By in_last = By.id(prop.getProperty("in_last"));

	//Verify Infant DOB drop downs
	By in_day = By.id(prop.getProperty("in_day"));
	By in_month = By.id(prop.getProperty("in_month"));
	By in_year = By.id(prop.getProperty("in_year"));


	//Add Baggage
	By b_add = By.id(prop.getProperty("b_add"));
	By b_disp = By.id(prop.getProperty("b_disp"));
	By b_list = By.xpath(prop.getProperty("b_list"));

	//Remove adult button
	By r_adult = By.xpath(prop.getProperty("r_adult"));
	By r_hide = By.xpath(prop.getProperty("r_hide"));

	//Remove Child button
	By r_child = By.xpath(prop.getProperty("r_child"));
	By r_chhide = By.xpath(prop.getProperty("r_chhide"));

	//Remove Infant button
	By r_infant = By.xpath(prop.getProperty("r_infant"));
	By r_inhide = By.xpath(prop.getProperty("r_inhide"));

	//Add Adult button
	By add_adult = By.xpath(prop.getProperty("add_adult"));
	By add_error = By.xpath(prop.getProperty("add_error"));

	//Add Child Button
	By add_child = By.xpath(prop.getProperty("add_child"));
	By add_cherror = By.xpath(prop.getProperty("add_cherror"));

	//Add Infant Button
	By add_infant = By.xpath(prop.getProperty("add_infant"));
	By add_inerror = By.xpath(prop.getProperty("add_inerror"));


	//data from previous page
	String flightNoPrev;
	LocalDate date1;



	public String getFlightNo(ExtentTest log) {
		log.info("Fetching flight number");
		return driver.findElement(flightNo).getText();
		
		
	}

	public LocalDate getFlightDate(ExtentTest log) {
		log.info("Fetching flight date");
		String travellerFlightDate = driver.findElement(flightDate).getText();
		date2 = LocalDate.parse(travellerFlightDate, formatter2);
		return date2;
	}

	public String getFlightNoPrev(ExtentTest log) {
		log.info("Fetching previous flight number");
		return flightNoPrev;
	}

	public LocalDate getFlightDatePrev(ExtentTest log) {
		log.info("Fetching previous flight date");
		return date1; 
	}

	public void clearCoupon(ExtentTest log) {
		log.info("Clearing the coupon field");
		driver.findElement(c_clear).click();
		log.info("Coupon field is empty");
	}

	public boolean checkCouponFieldEmpty(ExtentTest log) {
		return driver.findElement(c_check).getAttribute("style").contains("none");
	}

	public void enterCouponText(String coupon,ExtentTest log) {
		log.info("Enter coupon code ");
		driver.findElement(c_text).sendKeys(coupon);
	}

	public void applyCoupon(ExtentTest log) {
		log.info("Apply coupon code");
		driver.findElement(c_apply).click();
	}

	public boolean checkInvalidCoupon(ExtentTest log) {
		log.info("Check coupon validity");
		wait=new WebDriverWait(driver, 20);
		return wait.until(ExpectedConditions.textToBePresentInElementLocated(c_message, "Invalid"));

	}

	public List<WebElement> getAllCouponsAvailable(ExtentTest log){
		log.info("list of available coupons");
		List<WebElement> lcoupon = driver.findElements(c_list);
		return lcoupon;
	}

	public boolean checkValidCoupon(WebElement E, ExtentTest log) {
		E.click();
		wait=new WebDriverWait(driver, 20);
		return wait.until(ExpectedConditions.textToBePresentInElementLocated(c_message, "Congratulations!"));
	}

	public List<WebElement> getAllTravelInsuranceRadio(ExtentTest log){

		List<WebElement> linsure = driver.findElements(i_radio);
		return linsure;
	} 

	public boolean checkValidTravelRadio(WebElement E, ExtentTest log) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", driver.findElement(i_notinsure));
		E.click();
		return E.isSelected();
	}

	public List<WebElement> getAllFreeCancelRadio(ExtentTest log){
		log.info("Fetching all free cancellation radio buttons");
		List<WebElement> linsure = driver.findElements(f_radio);
		return linsure;
	} 

	public boolean checkValidFreeCancelRadio(WebElement E, ExtentTest log) {
		log.info("Checking cancellation radio button");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", driver.findElement(f_tab));
		E.click();
		return E.isSelected();
	}

	public boolean verifyAddSeat(ExtentTest log) {
		log.info("Choose a seat");
		driver.findElement(a_button).click();
		return driver.findElement(a_panel).isDisplayed();
	}

	public boolean verifyEmail(String email, ExtentTest log) {
		log.info("Email ID validation");
		boolean isEmail;
		driver.findElement(e_textField).clear();
		driver.findElement(e_textField).sendKeys(email);
		driver.findElement(e_continue).click();

		if(driver.findElement(e_nextSection).isDisplayed()) {
			isEmail = true;
		}else {
			isEmail = false;
		}
		return isEmail;
	}

	/*public boolean verifyContactNo(String contact) throws InterruptedException {
		boolean isContact;
		driver.findElement(co_textField).clear();
		driver.findElement(co_textField).sendKeys(contact);
		driver.findElement(co_continue).click();
		Thread.sleep(4000);
		
		if(driver.findElement(co_pay).isDisplayed()) {
			isContact = true;
		}else {
			isContact = false;
		}
		return isContact;
	}*/
	
	public void enterContactNo(String contact, ExtentTest log) {
		driver.findElement(co_textField).clear();
		driver.findElement(co_textField).sendKeys(contact);
	}
	
	public void clickPay(ExtentTest log) {
		driver.findElement(co_continue).click();
	}
	
	public boolean checkPaySectionDisplayed(ExtentTest log) {
		log.info("pay section ");
		boolean isDisplayed;
		if(driver.findElement(co_pay).isDisplayed()) {
			isDisplayed = true;
		}else {
			isDisplayed = false;
		}
		return isDisplayed;
	}
	
	public boolean verifyAdultNameText(String type,String firstName, ExtentTest log) {
		
		boolean isAlert = false;
		if(type.equalsIgnoreCase("first")) {
			log.info("adult first name");
			driver.findElement(t_first).clear();
			driver.findElement(t_first).sendKeys(firstName);
			Actions tab = new Actions(driver);
			Action pressTab = tab.sendKeys(Keys.TAB).build();
			pressTab.perform();
			if(isAlertPresent()) {
				isAlert = true;
				driver.switchTo().alert().accept();
				driver.switchTo().defaultContent();
			}else {
				isAlert = false;
			}
		}else {
			log.info("adult last name");
			driver.findElement(t_last).clear();
			driver.findElement(t_last).sendKeys(firstName);
			Actions tab = new Actions(driver);
			Action pressTab = tab.sendKeys(Keys.TAB).build();
			pressTab.perform();
			if(isAlertPresent()) {
				isAlert = true;
				driver.switchTo().alert().accept();
				driver.switchTo().defaultContent();
			}else {
				isAlert = false;
			}
		}

		return !isAlert;
	}

	public boolean verifyChildNameText(String type,String firstName, ExtentTest log) {
		
		boolean isAlert = false;
		if(type.equalsIgnoreCase("first")) {
			log.info("child first name");
			driver.findElement(ch_first).clear();
			driver.findElement(ch_first).sendKeys(firstName);
			Actions tab = new Actions(driver);
			Action pressTab = tab.sendKeys(Keys.TAB).build();
			pressTab.perform();
			if(isAlertPresent()) {
				isAlert = true;
				driver.switchTo().alert().accept();
				driver.switchTo().defaultContent();
			}else {
				isAlert = false;
			}
		}else {
			log.info("child last name");
			driver.findElement(ch_last).clear();
			driver.findElement(ch_last).sendKeys(firstName);
			Actions tab = new Actions(driver);
			Action pressTab = tab.sendKeys(Keys.TAB).build();
			pressTab.perform();
			if(isAlertPresent()) {
				isAlert = true;
				driver.switchTo().alert().accept();
				driver.switchTo().defaultContent();
			}else {
				isAlert = false;
			}
		}

		return !isAlert;
	}

	public boolean verifyInfantNameText(String type,String firstName, ExtentTest log) {

		boolean isAlert = false;
		if(type.equalsIgnoreCase("first")) {
			log.info("infant first name");
			driver.findElement(in_first).clear();
			driver.findElement(in_first).sendKeys(firstName);
			Actions tab = new Actions(driver);
			Action pressTab = tab.sendKeys(Keys.TAB).build();
			pressTab.perform();
			if(isAlertPresent()) {
				isAlert = true;
				driver.switchTo().alert().accept();
				driver.switchTo().defaultContent();
			}else {
				isAlert = false;
			}
		}else {
			log.info("infant last name");
			driver.findElement(in_last).clear();
			driver.findElement(in_last).sendKeys(firstName);
			Actions tab = new Actions(driver);
			Action pressTab = tab.sendKeys(Keys.TAB).build();
			pressTab.perform();
			if(isAlertPresent()) {
				isAlert = true;
				driver.switchTo().alert().accept();
				driver.switchTo().defaultContent();
			}else {
				isAlert = false;
			}
		}

		return !isAlert;
	}

	/*public void selectAdultTitle(String t) {
		Select title = new Select(driver.findElement(t_title));
		title.selectByVisibleText(t);

	}*/

	public boolean verifyAddBaggageButton(ExtentTest log) {
		log.info("Add baggage");
		driver.findElement(b_add).click();
		return driver.findElement(b_disp).isDisplayed();

	}

	public boolean verifyAdultTitleDropDown(ExtentTest log) {
		log.info("Testing adult title dropdown");
		boolean isSelected= false;
		WebElement titleDropDown = driver.findElement(t_title);
		titleDropDown.click();
		if(titleDropDown.isEnabled()) {
			isSelected = true;
		}
		return isSelected;
	}

	public boolean verifyChildTitleDropDown(ExtentTest log) {
		log.info("Testing child title dropdown");
		boolean isSelected= false;
		WebElement titleDropDown = driver.findElement(ch_title);
		titleDropDown.click();
		if(titleDropDown.isEnabled()) {
			isSelected = true;
		}
		return isSelected;
	}

	public boolean verifyInfantTitleDropDown(ExtentTest log) {
		log.info("Testing infant title dropdown");
		boolean isSelected= false;
		WebElement titleDropDown = driver.findElement(in_title);
		titleDropDown.click();
		if(titleDropDown.isEnabled()) {
			isSelected = true;
		}
		return isSelected;
	}

	public boolean verifyInfantDOB(ExtentTest log) {
		log.info("Testing infant's date of birth");
		boolean f= false;

		if(driver.findElement(in_day).isEnabled()) {
			driver.findElement(in_day).click();

			if(driver.findElement(in_month).isEnabled()) {
				driver.findElement(in_month).click();

				if(driver.findElement(in_year).isEnabled()) {
					driver.findElement(in_year).click();
					f = true;
				}
			}else {
				f = false;
			}
		}else {
			f = false;
		}
		return f;

	}

	/*public List<WebElement> getAllBaggageOptions(){
		List<WebElement> lAll = driver.findElements(b_list);
		return lAll;
	}
	 */

	/*public boolean verifyBaggageRadio() {
		boolean yes = false;
		driver.findElement(b_add).click();
		driver.findElement(By.xpath("/html[1]/body[1]/form[1]/div[10]/div[6]/div[1]/div[4]/div[2]/form[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[3]/div[3]/div[4]/div[2]/div[1]/div[2]/label[1]/span[1]")).click();
		boolean a1 = driver.findElement(By.id("rb_Adult2_DEL-BOM-8723_0")).isSelected();
		driver.findElement(By.xpath("/html[1]/body[1]/form[1]/div[10]/div[6]/div[1]/div[4]/div[2]/form[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[3]/div[3]/div[4]/div[2]/div[1]/div[2]/label[2]/span[1]")).click();
		boolean a2 = driver.findElement(By.id("rb_Adult2_DEL-BOM-8723_1")).isSelected();
		if(a1=a2=true) {
			yes= true;
		}
		return yes;

	}*/

	public boolean verifyRemoveAdult(ExtentTest log) {
		log.info("Removing adult traveller");
		driver.findElement(r_adult).click();
		return driver.findElement(r_hide).getAttribute("style").contains("none");
	}

	public boolean verifyRemoveChild(ExtentTest log) {
		log.info("Removing child traveller");
		driver.findElement(r_child).click();
		return driver.findElement(r_chhide).getAttribute("style").contains("none");
	}

	public boolean verifyRemoveInfant(ExtentTest log) {
		log.info("Removing infant traveller");
		driver.findElement(r_infant).click();
		return driver.findElement(r_inhide).getAttribute("style").contains("none");
	}




	public void adultCheckBoxClick(ExtentTest log) {
		log.info("testing adult checkbox");
		driver.findElement(r_adult).click();
	}

	public void childCheckBoxClick(ExtentTest log) {
		log.info("testing child checkbox");
		driver.findElement(r_child).click();
	}

	public void infantCheckBoxClick(ExtentTest log) {
		log.info("testing infant checkbox");
		driver.findElement(r_infant).click();
	}


	public boolean verifyAddExtraAdult(ExtentTest log) {
		log.info("Check if add extra adult is working");
		driver.findElement(add_adult).click();
		return (driver.findElement(add_error).isDisplayed())&&(driver.findElement(add_error).getAttribute("style").contains("block"));
	}

	public boolean verifyAddExtraChild(ExtentTest log) {
		log.info("Check if add extra child is working");
		driver.findElement(add_child).click();
		return (driver.findElement(add_cherror).isDisplayed())&&(driver.findElement(add_cherror).getAttribute("style").contains("block"));
	}

	public boolean verifyAddExtraInfant(ExtentTest log) {
		log.info("Check if add extra infant is working");
		driver.findElement(add_infant).click();
		return (driver.findElement(add_inerror).isDisplayed())&&(driver.findElement(add_inerror).getAttribute("style").contains("block"));
	}
	
	public void clickAddAdult(ExtentTest log) {
		log.info("Add adult");
		driver.findElement(add_adult).click();
	}

	public void clickAddChild(ExtentTest log) {
		log.info("Add Child");
		driver.findElement(add_child).click();
	}

	public void clickAddInfant(ExtentTest log) {
		log.info("Add Infant");
		driver.findElement(add_infant).click();
	}

	public void selectInfantDOB(String day, String month, String year, ExtentTest log) {
		log.info("Choose infant date of birth");
		Select sDay = new Select(driver.findElement(in_day));
		Select sMonth = new Select(driver.findElement(in_month));
		Select sYear = new Select(driver.findElement(in_year));
		
		sDay.selectByVisibleText(day);
		sMonth.selectByVisibleText(month);
		sYear.selectByVisibleText(year);
	}
	
	public String returnDay(String day, ExtentTest log) {
		log.info("return date");
		String ret = null;
		
			if(day.equals("01")) {
				ret="1";
			}else if(day.equals("02")) {
				ret="2";
			}else if(day.equals("03")) {
				ret="3";
			}else if(day.equals("04")) {
				ret="4";
			}else if(day.equals("05")) {
				ret="5";
			}else if(day.equals("06")) {
				ret="6";
			}else if(day.equals("07")) {
				ret="7";
			}else if(day.equals("08")) {
				ret="8";
			}else if(day.equals("09")) {
				ret="9";
			}
			else {
				ret = day;
			}
		
		return ret;
	}

	//Presence of Alert check
	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		}// try
		catch (Exception e) {
			return false;
		}// catch
	}
	
	 public boolean isValid(String no, ExtentTest log) 
	    { 
	        // The given argument to compile() method  
	        // is regular expression. With the help of  
	        // regular expression we can validate mobile 
	        // number.  
	        // 1) Begins with 0 or 91 
	        // 2) Then contains 7 or 8 or 9. 
	        // 3) Then contains 9 digits 
	        Pattern p = Pattern.compile("(0/91)?[7-9][0-9]{9}"); 
	  
	        // Pattern class contains matcher() method 
	        // to find matching between given number  
	        // and regular expression 
	        Matcher m = p.matcher(no); 
	        return (m.find() && m.group().equals(no)); 
	    } 


	  public boolean isStringOnlyAlphabet(String str) 
	{ 
	    return ((!str.equals("")) 
	            && (str != null) 
	            && (str.matches("^[a-zA-Z]*$"))); 
	}
}
