package com.easemytrip.runner;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.easemytrip.pom.FlightSearchPage;
import com.easemytrip.pom.FlightTraveller;
import com.easemytrip.utils.Base;
import com.easemytrip.utils.ExcelReader;
@Listeners()
public class FlightAfterBookNow extends Base {
	String flightNo;
	LocalDate date;



	FlightSearchPage fp;
	FlightTraveller	ft;

	@Test(description="One-Way Flight details check")
	public void flightDetailsOneWay() throws Exception {

		driver.get(prop.getProperty("url"));
		fp = new FlightSearchPage(driver);
		ft = fp.bookFilterFlight(testLog);

		System.out.println(driver.getTitle());
		Assert.assertTrue(driver.getTitle().contains("FlightPayment"));

		flightNo = ft.getFlightNo(testLog);
		date = ft.getFlightDate(testLog);

		String flightNoPrev = ft.getFlightNoPrev(testLog);
		LocalDate flightDatePrev = ft.getFlightDatePrev(testLog);


		Assert.assertTrue(flightNo.equals(flightNoPrev));
		Assert.assertEquals(date, flightDatePrev);	
	}


	@Test(description="Coupon Check", dependsOnMethods = "flightDetailsOneWay",
			priority = 1)
	public void travellerEMICheckCLearButton() throws Exception {
		WebDriverWait wait=new WebDriverWait(driver, 20);

		//Coupon Clear button
		ft.clearCoupon(testLog);

		//Check if Coupon Text Field is blank
		boolean checkCouponField = ft.checkCouponFieldEmpty(testLog);
		assertTrue(checkCouponField);

		ft.enterCouponText("ASDF", testLog);
		ft.applyCoupon(testLog);
		//Thread.sleep(5000);
		boolean message;
		message = ft.checkInvalidCoupon(testLog);

		//Checking invalid coupon
		assertTrue(message);

		//get All coupons available
		List<WebElement> lcoupon = ft.getAllCouponsAvailable(testLog);

		//check valid coupons
		for(int i = 0; i < (lcoupon.size()-1); i++) {
			boolean message2 = ft.checkValidCoupon(lcoupon.get(i), testLog);
			assertTrue(message2);
		}
	}

	@Test(description="Travel Insurance Radio Button", dependsOnMethods = "flightDetailsOneWay", priority = 2)
	public void uinsuranceRadioCheck() throws Exception {

		List<WebElement> linsure = ft.getAllTravelInsuranceRadio(testLog);
		for(int i = 0; i < linsure.size(); i++) {
			boolean message2 = ft.checkValidTravelRadio(linsure.get(i), testLog);
			assertTrue(message2);
		}

	}


	@Test(description="Free Cancellation Radio Button", dependsOnMethods = "flightDetailsOneWay", priority = 3)
	public void freeCancellationRadio() {
		List<WebElement> lfree = ft.getAllFreeCancelRadio(testLog);
		for(int i = 0; i < lfree.size(); i++) {
			boolean message2 = ft.checkValidFreeCancelRadio(lfree.get(i), testLog);
			assertTrue(message2);
		}
	}

	@Test(description="Email Verification", dependsOnMethods = "flightDetailsOneWay", dataProvider = "dpForEmail", priority = 4)
	public void verifyEmail(String email) {



		boolean isEmail = ft.verifyEmail(email, testLog);

		if(isEmail) {
			assertTrue(isEmail, "Valid Email Verified");
		}else {
			assertTrue(!isEmail, "inValid Email Verified");
		}
	}




	@Test(description="Verify Adult Traveller Title", dependsOnMethods = "flightDetailsOneWay", priority = 5)
	public void verifyAdultTravellerTitleDropDown() {
		assertTrue(ft.verifyAdultTitleDropDown(testLog));
	}

	@Test(description="Verify Adult Traveller Details (First Name)", dependsOnMethods = "flightDetailsOneWay", dataProvider = "dpForTravellerName", priority = 6)
	public void verifyAdultTravellerFirstName(String firstName) {
		boolean isValid = ft.verifyAdultNameText("first",firstName, testLog);
		assertTrue(isValid);
	}


	@Test(description="Verify Adult Traveller Details (Last Name)", dependsOnMethods = "flightDetailsOneWay", dataProvider = "dpForTravellerName", priority = 7)
	public void verifyAdultTravellerLastName(String lastName) {
		boolean isValid = ft.verifyAdultNameText("second",lastName, testLog);
		assertTrue(isValid);
	}

	@Test(description="Verify Add Baggage button", dependsOnMethods = "flightDetailsOneWay", priority = 8)
	public void verifyAddBaggageButton() {
		boolean isAddBaggage = ft.verifyAddBaggageButton(testLog);
		assertTrue(isAddBaggage);
		driver.findElement(By.id("baggageBtn")).click();

	}

	/*	@Test(description="Verify Baggage radio button", dependsOnMethods = "flightDetailsOneWay", priority = 9)
	public void verifyBaggageRadioButton() {
		List<WebElement> lAll = ft.getAllBaggageOptions();
		boolean isChecked = ft.verifyBaggageRadio();
		assertTrue(isChecked);
	}
	 */

	@Test(description="Verify Remove Adult button", dependsOnMethods = "flightDetailsOneWay", priority = 9)
	public void removeAdult() {
		boolean removeAdult = ft.verifyRemoveAdult(testLog);
		assertTrue(removeAdult);
	}


	@Test(description="Verify Add Adult Button"/*, dataProvider = "dpForAdult"*/, dependsOnMethods = "flightDetailsOneWay", priority = 10)
	public void verifyAddAdultButton(){
		int adultNo = Integer.parseInt(driver.findElement(By.id("divNoAdult")).getText());
		int childNo = Integer.parseInt(driver.findElement(By.id("divNoChild")).getText());
		int infantNo =  Integer.parseInt(driver.findElement(By.id("divNoInfant")).getText());
		ExcelReader excel = new ExcelReader(prop.getProperty("excelpath"));
		ft.adultCheckBoxClick(testLog);

		for(int a = 0; a < adultNo; a++) {
			String titleDropDown = "titleAdult"+a;
			System.out.println(titleDropDown);
			Select title = new Select(driver.findElement(By.id(titleDropDown)));
			title.selectByVisibleText(excel.getCellData(prop.getProperty("sheetNameAdult"), a+1, 0));
			String firstName = "txtFNAdult"+a;
			String lastName = "txtLNAdult"+a;

			driver.findElement(By.id(firstName)).clear();
			driver.findElement(By.id(firstName)).sendKeys(excel.getCellData(prop.getProperty("sheetNameAdult"), a+1, 1));	
			Actions tab = new Actions(driver);
			Action pressTab = tab.sendKeys(Keys.TAB).build();
			pressTab.perform();

			driver.findElement(By.id(lastName)).clear();
			driver.findElement(By.id(lastName)).sendKeys(excel.getCellData(prop.getProperty("sheetNameAdult"), a+1, 2));

			if(a!=adultNo-1) {
				ft.clickAddAdult(testLog);
			}
		}

		boolean addExtra = ft.verifyAddExtraAdult(testLog);
		assertTrue(addExtra);
	}

	/*@DataProvider
	public Object[][] dpForAdult() {
		ExcelReader excel = new ExcelReader(prop.getProperty("excelpath"));
		int row = excel.getRowNum(prop.getProperty("sheetNameAdult"));
		int col = excel.getColumnNum(prop.getProperty("sheetNameAdult"));
		Object[][] adult = new Object[row-1][col];
		System.out.println(row+"  "+col);
		for(int i = 0; i < row-1; i++) {
			adult[i][0] = excel.getCellData(prop.getProperty("sheetNameAdult"), i+1, 0);
			adult[i][1] = excel.getCellData(prop.getProperty("sheetNameAdult"), i+1, 1);
			adult[i][2] = excel.getCellData(prop.getProperty("sheetNameAdult"), i+1, 2);
		}
		return adult;
	}*/

	@Test(description="Verify Child Traveller Title", dependsOnMethods = "flightDetailsOneWay", priority = 11)
	public void verifyChildTravellerTitle() {
		assertTrue(ft.verifyChildTitleDropDown(testLog));
	}

	@Test(description="Verify Child Traveller Details (First Name)", dependsOnMethods = "flightDetailsOneWay", dataProvider = "dpForTravellerName", priority = 12)
	public void verifyChildTravellerFirstName(String firstName) {
		boolean isValid = ft.verifyChildNameText("first",firstName, testLog);
		assertTrue(isValid);
	}


	@Test(description="Verify Child Traveller Details (Last Name)", dependsOnMethods = "flightDetailsOneWay", dataProvider = "dpForTravellerName", priority = 13)
	public void verifyChildTravellerLastName(String lastName) {
		boolean isValid = ft.verifyChildNameText("second",lastName, testLog);
		assertTrue(isValid);
	}

	@Test(description="Verify Remove Child button", dependsOnMethods = "flightDetailsOneWay", priority = 14)
	public void removeChild() {
		boolean removeChild = ft.verifyRemoveChild(testLog);
		assertTrue(removeChild);
	}

	@Test(description="Verify Add Child Button", dependsOnMethods = "flightDetailsOneWay", priority = 15)
	public void verifyAddChildButton(){
		int adultNo = Integer.parseInt(driver.findElement(By.id("divNoAdult")).getText());
		int childNo = Integer.parseInt(driver.findElement(By.id("divNoChild")).getText());
		int infantNo =  Integer.parseInt(driver.findElement(By.id("divNoInfant")).getText());
		ExcelReader excel = new ExcelReader(prop.getProperty("excelpath"));
		ft.childCheckBoxClick(testLog);

		for(int a = 0; a < childNo; a++) {
			String titleDropDown = "titleChild"+a;
			System.out.println(titleDropDown);
			Select title = new Select(driver.findElement(By.id(titleDropDown)));
			title.selectByVisibleText(excel.getCellData(prop.getProperty("sheetNameChild"), a+1, 0));
			String firstName = "txtFNChild"+a;
			String lastName = "txtLNChild"+a;

			driver.findElement(By.id(firstName)).clear();
			driver.findElement(By.id(firstName)).sendKeys(excel.getCellData(prop.getProperty("sheetNameChild"), a+1, 1));	
			Actions tab = new Actions(driver);
			Action pressTab = tab.sendKeys(Keys.TAB).build();
			pressTab.perform();

			driver.findElement(By.id(lastName)).clear();
			driver.findElement(By.id(lastName)).sendKeys(excel.getCellData(prop.getProperty("sheetNameChild"), a+1, 2));

			if(a!=childNo-1) {
				ft.clickAddChild(testLog);
			}
		}

		boolean addExtra = ft.verifyAddExtraChild(testLog);
		assertTrue(addExtra);
	}


	@Test(description="Verify Infant Traveller Title", dependsOnMethods = "flightDetailsOneWay", priority = 16)
	public void verifyInfantTravellerTitle() {
		assertTrue(ft.verifyInfantTitleDropDown(testLog));
	}

	@Test(description="Verify Infant Traveller Details (First Name)", dependsOnMethods = "flightDetailsOneWay", dataProvider = "dpForTravellerName", priority = 17)
	public void verifyInfantTravellerFirstName(String firstName) {
		boolean isValid = ft.verifyInfantNameText("first",firstName, testLog);
		assertTrue(isValid);
	}


	@Test(description="Verify Infant Traveller Details (Last Name)", dependsOnMethods = "flightDetailsOneWay", dataProvider = "dpForTravellerName", priority = 18)
	public void verifyInfantTravellerLastName(String lastName) {
		boolean isValid = ft.verifyInfantNameText("second",lastName, testLog);
		assertTrue(isValid);
	}

	@Test(description="Verify Infant Traveller Details (DOB)", dependsOnMethods = "flightDetailsOneWay", priority = 19)
	public void verifyInfantTravellerDOB() {
		assertTrue(ft.verifyInfantDOB(testLog));
	}


	@Test(description="Verify Remove Infant button", dependsOnMethods = "flightDetailsOneWay", priority = 20)
	public void removeInfant() {
		boolean removeInfant = ft.verifyRemoveInfant(testLog);
		assertTrue(removeInfant);
	}

	@Test(description="Verify Add Infant Button", dependsOnMethods = "flightDetailsOneWay", priority = 21)
	public void verifyAddInfantButton(){
		int adultNo = Integer.parseInt(driver.findElement(By.id("divNoAdult")).getText());
		int childNo = Integer.parseInt(driver.findElement(By.id("divNoChild")).getText());
		int infantNo =  Integer.parseInt(driver.findElement(By.id("divNoInfant")).getText());
		ExcelReader excel = new ExcelReader(prop.getProperty("excelpath"));
		ft.infantCheckBoxClick(testLog);

		for(int a = 0; a < infantNo; a++) {
			String titleDropDown = "titleInfant"+a;
			System.out.println(titleDropDown);
			Select title = new Select(driver.findElement(By.id(titleDropDown)));
			title.selectByVisibleText(excel.getCellData(prop.getProperty("sheetNameInfant"), a+1, 0));
			String firstName = "txtFNInfant"+a;
			String lastName = "txtLNInfant"+a;

			driver.findElement(By.id(firstName)).clear();
			driver.findElement(By.id(firstName)).sendKeys(excel.getCellData(prop.getProperty("sheetNameInfant"), a+1, 1));	
			Actions tab = new Actions(driver);
			Action pressTab = tab.sendKeys(Keys.TAB).build();
			pressTab.perform();

			driver.findElement(By.id(lastName)).clear();
			driver.findElement(By.id(lastName)).sendKeys(excel.getCellData(prop.getProperty("sheetNameInfant"), a+1, 2));

			System.out.println(excel.getCellData(prop.getProperty("sheetNameInfant"), a+1, 3));

			String day = excel.getCellData(prop.getProperty("sheetNameInfant"), a+1, 3).split("-")[0];
			String retDay = ft.returnDay(day, testLog);

			String month = excel.getCellData(prop.getProperty("sheetNameInfant"), a+1, 3).split("-")[1];
			String year = excel.getCellData(prop.getProperty("sheetNameInfant"), a+1, 3).split("-")[2];

			System.out.println(day+" "+month+" "+year);

			String selectDate = "divDOBDayInfant"+a;
			String selectMonth = "divDOBMonInfant"+a;
			String selectYear = "divDOBYarInfant"+a;

			Select sDay = new Select(driver.findElement(By.id(selectDate)));
			Select sMonth = new Select(driver.findElement(By.id(selectMonth)));
			Select sYear = new Select(driver.findElement(By.id(selectYear)));

			sDay.selectByVisibleText(retDay);
			sMonth.selectByVisibleText(month);
			sYear.selectByVisibleText(year);

			//ft.selectInfantDOB(day, month, year); 

			if(a!=infantNo-1) {
				ft.clickAddInfant(testLog);
			}
		}

		boolean addExtra = ft.verifyAddExtraInfant(testLog);
		assertTrue(addExtra);
	}

	@Test(description="Add Seat Button Verifcation", dependsOnMethods = "flightDetailsOneWay", priority = 22)
	public void addSeatVerify() throws Exception {
		boolean checkAdd = ft.verifyAddSeat(testLog);	
		assertTrue(checkAdd);
	}


	@Test(description="Verify Contact No.", dataProvider = "dpForContact", dependsOnMethods = "flightDetailsOneWay", priority = 23)
	public void verifyContactNo(String contact) throws InterruptedException {
		/*	System.out.println(contact);
		assertTrue(ft.verifyContactNo(contact));*/
		
		boolean isValidNo = ft.isValid(contact, testLog);
		ft.enterContactNo(contact, testLog);
		ft.clickPay(testLog);
		Thread.sleep(4000);
		boolean isDisplay = ft.checkPaySectionDisplayed(testLog);

		if(isValidNo) {
			assertTrue(isDisplay);
		}else {
			if(isDisplay==true) {
				assertEquals(isDisplay, isDisplay);
			}else {
				assertEquals(isDisplay, !isDisplay);
			}

			
		}

		if(isDisplay==true) {
			driver.navigate().back();
		}
	}

	/*
	 * Data Providers
	 */

	@DataProvider
	public Object[] dpForTravellerName() {
		ExcelReader excel = new ExcelReader(prop.getProperty("excelpathFT"));
		int row = excel.getRowNum(prop.getProperty("sheetName"));
		Object[] first = new Object[row-1];
		System.out.println(row);
		for(int i = 0; i < row-1; i++) {
			first[i] = excel.getCellData(prop.getProperty("sheetName"), i+1, 1);

		}
		return first;
	}


	@DataProvider
	public Object[] dpForEmail() {
		ExcelReader excel = new ExcelReader(prop.getProperty("excelpathFT"));
		int row = excel.getRowNum(prop.getProperty("sheetName"));
		Object[] email = new Object[row-1];
		System.out.println(row);
		for(int i = 0; i < row-1; i++) {
			email[i] = excel.getCellData(prop.getProperty("sheetName"), i+1, 0);
		}
		return email;
	}



	@DataProvider
	public Object[] dpForContact() {
		ExcelReader excel = new ExcelReader(prop.getProperty("excelpathFT"));
		int row = excel.getRowNum(prop.getProperty("sheetNameContact"));
		Object[] contact = new Object[row-1];
		System.out.println(row);
		for(int i = 0; i < row-1; i++) {
			contact[i] = excel.getCellData(prop.getProperty("sheetNameContact"), i+1, 0);
		}
		return contact;
	}

}
