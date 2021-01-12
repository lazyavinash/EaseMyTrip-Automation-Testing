package com.easemytrip.pom;


import static org.testng.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


public class FlightRWTraveller {
	WebDriver driver;
	WebDriverWait wait;


	//Flight Details
	/*By flightNo = By.xpath("//div[@id='divLoadFltDetails']//div[@class='fli-d-m ng-scope']//div[2]//div//div//span[2]");
	By flightDate = By.xpath("//div[@class='ng-scope fd-des']//span[2]");*/
	DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("E-ddMMMyyyy");
	LocalDate nextDate1;
	LocalDate nextDate2;

	//Flight Data from traveller page
	By flight1No = By.xpath("//body[@id='bodyMain']/form[@id='FrmEmtMdl']/div[@id='FrmTrveller']/div[@class='tr-m']/div[@class='tr-cen']/div[@id='divTrvfltDtl']/div[@class='fd-ll']/div[@id='divReview']/div[@class='bor pdb10']/div[@id='divLoadFltDetails']/div[1]/div[1]/div[1]/div[2]//div[2]//div[1]//div[2]//span[2]");
	By flight2No = By.xpath("//body[@id='bodyMain']/form[@id='FrmEmtMdl']/div[@id='FrmTrveller']/div[@class='tr-m']/div[@class='tr-cen']/div[@id='divTrvfltDtl']/div[@class='fd-ll']/div[@id='divReview']/div[@class='bor pdb10']/div[@id='divLoadFltDetails']/div[2]/div[1]/div[1]/div[2]//div[2]//div[1]//div[2]//span[2]");
	By flight1Date = By.xpath("//div[@class='ng-scope fd-des']//span[2]");
	By flight2Date = By.xpath("//div[@class='ng-scope fd-des-round']//span[2]");


	//Coupon
	By c_clear = By.xpath("//div[@class='cancl']");
	By c_check = By.id("divCouponApplied");
	By c_text = By.xpath("//input[@id='txtCouponCode']");
	By c_apply = By.xpath("//div[@class='apl']");
	By c_message = By.xpath("//p[@id='easeFareDetails1_promodiv']");
	By c_list = By.xpath("//div[@class='coupn_scrl']//div[*]//label//span");

	//Travel Insurance
	By i_insure = By.id("chkInsurance");
	By i_notinsure = By.id("notinsure");
	By i_radio = By.name("rdoInsuNew");

	//Free Cancellation
	By f_yes = By.id("chkZeroInsurance");
	By f_no = By.id("chkNotZeroInsurance");
	By f_radio = By.name("rdoZeroInsuNew");
	By f_tab = By.id("divZeroInsTab");

	//Email Verification
	By e_textField = By.xpath("//div[@class='con-m']//input[@id='txtEmailId']");
	By e_continue = By.xpath("//span[@id='spnVerifyEmail']");
	By e_nextSection = By.id("divTravellerSHow");

	//Contact No Verification
	By co_textField = By.id("txtCPhone");
	By co_continue = By.xpath("//span[@id='spnTransaction']");
	By co_pay = By.id("divPaymentMode");

	//Add Seat Button
	By a_button = By.xpath("//div[@id='seatArea']//a[@class='adSeatbtn'][contains(text(),'+ Add Seat')]");
	By a_panel = By.id("mainSeatPanel");

	//Adult Details
	By t_title = By.id("titleAdult0");
	By t_first = By.id("txtFNAdult0");
	By t_last = By.id("txtLNAdult0");

	//Child Details
	By ch_title = By.id("titleChild0");
	By ch_first = By.id("txtFNChild0");
	By ch_last = By.id("txtLNChild0");

	//Infant Details
	By in_title = By.id("titleInfant0");
	By in_first = By.id("txtFNInfant0");
	By in_last = By.id("txtLNInfant0");

	//Verify Infant DOB drop downs
	By in_day = By.id("divDOBDayInfant0");
	By in_month = By.id("divDOBMonInfant0");
	By in_year = By.id("divDOBYarInfant0");


	//Add Baggage
	By b_add = By.id("baggageBtn");
	By b_disp = By.id("baggageBoxAdult0");
	By b_list = By.xpath("//body[@id='bodyMain']/form[@id='FrmEmtMdl']/div[@id='FrmTrveller']/div[@class='tr-m']/div[@class='tr-cen']/div[@id='divTrvfltDtl']/div[@class='fd-ll']/form[@class='ng-pristine ng-invalid ng-invalid-pattern ng-valid-maxlength ng-valid-required']/div[@id='divTravellerSHow']/div[@class='bor']/div[@id='divTravlerEntry']/div[@class='tr-cen-trv']/div[@class='fd-ll']/div[@id='divAdultPax']/div[@class='shd_pnl']/div[@class='ancillAdult1']/div[@id='baggageBoxAdult1']/div[@id='dbb_Adult1_DEL-BOM-8723']/div[@class='mealmenu']/label[*]/span[1]");

	//Remove adult button
	By r_adult = By.xpath("//div[@class='shd_pnl']//div[@class='ng-scope']//span[@class='cmark_cbox']");
	By r_hide = By.xpath("//div[@id='divTrvAdult0']");

	//Remove Child button
	By r_child = By.xpath("//span[@id='spnChild0']");
	By r_chhide = By.xpath("//div[@id='divTrvChild0']");

	//Remove Infant button
	By r_infant = By.xpath("//span[@id='spnInfant0']");
	By r_inhide = By.xpath("//div[@id='divTrvInfant0']");

	//Add Adult button
	By add_adult = By.xpath("//a[@class='add_adlt']");
	By add_error = By.xpath("//div[@id='errAdult']");

	//Add Child Button
	By add_child = By.xpath("//a[contains(text(),'+Add Child')]");
	By add_cherror = By.xpath("//div[@id='errChild']");

	//Add Infant Button
	By add_infant = By.xpath("//a[contains(text(),'+Add Infant')]");
	By add_inerror = By.xpath("//div[@id='errInfant']");


	//data from previous page
	String flight1NoPrev;
	LocalDate date1;
	String flight2NoPrev;
	LocalDate date2;

	public FlightRWTraveller(WebDriver driver, String flight1No, LocalDate fdate1, String flight2No, LocalDate fdate2) {
		super();
		this.driver = driver;
		this.date1 = fdate1;
		this.flight1NoPrev = flight1No;
		this.date2 = fdate2;
		this.flight2NoPrev = flight2No;
	}

	public String getFlight1No() {
		return driver.findElement(flight1No).getText();
	}

	public String getFlight2No() throws InterruptedException {
		Thread.sleep(2000);
		return driver.findElement(flight2No).getText();
	}


	public LocalDate getFlight1Date() {
		String travellerFlightDate = driver.findElement(flight1Date).getText();
		nextDate1 = LocalDate.parse(travellerFlightDate, formatter2);
		return nextDate1;
	}

	public LocalDate getFlight2Date() {
		String travellerFlightDate = driver.findElement(flight2Date).getText();
		nextDate2 = LocalDate.parse(travellerFlightDate, formatter2);
		return nextDate2;
	}

	public String getFlight1NoPrev() {
		return flight1NoPrev;
	}
	public String getFlight2NoPrev() {
		return flight2NoPrev;
	}

	public LocalDate getFlight1DatePrev() {
		return date1; 
	}
	public LocalDate getFlight2DatePrev() {
		return date2; 
	}

	public void clearCoupon() {
		driver.findElement(c_clear).click();
	}

	public boolean checkCouponFieldEmpty() {
		return driver.findElement(c_check).getAttribute("style").contains("none");
	}

	public void enterCouponText(String coupon) {
		driver.findElement(c_text).sendKeys(coupon);
	}

	public void applyCoupon() {
		driver.findElement(c_apply).click();
	}

	public boolean checkInvalidCoupon() {
		wait=new WebDriverWait(driver, 20);
		return wait.until(ExpectedConditions.textToBePresentInElementLocated(c_message, "Invalid"));

	}

	public List<WebElement> getAllCouponsAvailable(){
		List<WebElement> lcoupon = driver.findElements(c_list);
		return lcoupon;
	}

	public boolean checkValidCoupon(WebElement E) {
		E.click();
		wait=new WebDriverWait(driver, 20);
		return wait.until(ExpectedConditions.textToBePresentInElementLocated(c_message, "Congratulations!"));
	}

	public List<WebElement> getAllTravelInsuranceRadio(){

		List<WebElement> linsure = driver.findElements(i_radio);
		return linsure;
	} 

	public boolean checkValidTravelRadio(WebElement E) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", driver.findElement(i_notinsure));
		E.click();
		return E.isSelected();
	}

	public List<WebElement> getAllFreeCancelRadio(){

		List<WebElement> linsure = driver.findElements(f_radio);
		return linsure;
	} 

	public boolean checkValidFreeCancelRadio(WebElement E) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", driver.findElement(f_tab));
		E.click();
		return E.isSelected();
	}

	public boolean verifyAddSeat() {
		driver.findElement(a_button).click();
		return driver.findElement(a_panel).isDisplayed();
	}
	/*
		public void addSeat() {
			driver.findElement(a_button).click();
		}
	 */
	public boolean verifyEmail(String email) {
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

	public void enterContactNo(String contact) {
		driver.findElement(co_textField).clear();
		driver.findElement(co_textField).sendKeys(contact);
	}

	public void clickPay() {
		driver.findElement(co_continue).click();
	}

	public boolean checkPaySectionDisplayed() {
		boolean isDisplayed;
		if(driver.findElement(co_pay).isDisplayed()) {
			isDisplayed = true;
		}else {
			isDisplayed = false;
		}
		return isDisplayed;
	}

	public boolean verifyAdultNameText(String type,String firstName) {
		boolean isAlert = false;
		if(type.equalsIgnoreCase("first")) {
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

	public boolean verifyChildNameText(String type,String firstName) {
		boolean isAlert = false;
		if(type.equalsIgnoreCase("first")) {
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

	public boolean verifyInfantNameText(String type,String firstName) {
		boolean isAlert = false;
		if(type.equalsIgnoreCase("first")) {
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

	public boolean verifyAddBaggageButton() {
		driver.findElement(b_add).click();
		return driver.findElement(b_disp).isDisplayed();

	}

	public boolean verifyAdultTitleDropDown() throws InterruptedException {
		boolean isSelected= false;
		WebElement titleDropDown = driver.findElement(t_title);
		Thread.sleep(2000);
		titleDropDown.click();
		Thread.sleep(2000);
		if(titleDropDown.isEnabled()) {
			isSelected = true;
		}
		return isSelected;
	}

	public boolean verifyChildTitleDropDown() {
		boolean isSelected= false;
		WebElement titleDropDown = driver.findElement(ch_title);
		titleDropDown.click();
		if(titleDropDown.isEnabled()) {
			isSelected = true;
		}
		return isSelected;
	}

	public boolean verifyInfantTitleDropDown() {
		boolean isSelected= false;
		WebElement titleDropDown = driver.findElement(in_title);
		titleDropDown.click();
		if(titleDropDown.isEnabled()) {
			isSelected = true;
		}
		return isSelected;
	}

	public boolean verifyInfantDOB() {
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


	public boolean verifyRemoveAdult() {
		driver.findElement(r_adult).click();
		return driver.findElement(r_hide).getAttribute("style").contains("none");
	}

	public boolean verifyRemoveChild() {
		driver.findElement(r_child).click();
		return driver.findElement(r_chhide).getAttribute("style").contains("none");
	}

	public boolean verifyRemoveInfant() {
		driver.findElement(r_infant).click();
		return driver.findElement(r_inhide).getAttribute("style").contains("none");
	}




	public void adultCheckBoxClick() {
		driver.findElement(r_adult).click();
	}

	public void childCheckBoxClick() {
		driver.findElement(r_child).click();
	}

	public void infantCheckBoxClick() {
		driver.findElement(r_infant).click();
	}


	public boolean verifyAddExtraAdult() {
		driver.findElement(add_adult).click();
		return (driver.findElement(add_error).isDisplayed())&&(driver.findElement(add_error).getAttribute("style").contains("block"));
	}

	public boolean verifyAddExtraChild() {
		driver.findElement(add_child).click();
		return (driver.findElement(add_cherror).isDisplayed())&&(driver.findElement(add_cherror).getAttribute("style").contains("block"));
	}

	public boolean verifyAddExtraInfant() {
		driver.findElement(add_infant).click();
		return (driver.findElement(add_inerror).isDisplayed())&&(driver.findElement(add_inerror).getAttribute("style").contains("block"));
	}

	public void clickAddAdult() {
		driver.findElement(add_adult).click();
	}

	public void clickAddChild() {
		driver.findElement(add_child).click();
	}

	public void clickAddInfant() {
		driver.findElement(add_infant).click();
	}

	public void selectInfantDOB(String day, String month, String year) {
		Select sDay = new Select(driver.findElement(in_day));
		Select sMonth = new Select(driver.findElement(in_month));
		Select sYear = new Select(driver.findElement(in_year));

		sDay.selectByVisibleText(day);
		sMonth.selectByVisibleText(month);
		sYear.selectByVisibleText(year);
	}

	public String returnDay(String day) {
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

	public boolean isStringOnlyAlphabet(String str) 
	{ 
		return ((!str.equals("")) 
				&& (str != null) 
				&& (str.matches("^[a-zA-Z]*$"))); 
	} 

	public boolean isValid(String no) 
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



}
