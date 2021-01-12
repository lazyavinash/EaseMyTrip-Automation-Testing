package com.easemytrip.runner;

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
import org.testng.annotations.Test;

import com.easemytrip.pom.FlightRWTraveller;
import com.easemytrip.pom.RoundDetails;
import com.easemytrip.pom.RoundTrip;
import com.easemytrip.utils1.Base;
import com.easemytrip.utils1.ExcelReader;

public class roundtripRunner extends Base {
  RoundTrip rt;
  RoundDetails rd; 
  String flight1No;
	String flight2No;
	LocalDate date1;
	LocalDate date2;
	boolean isValidNo;
	
	//FlightTravellerAdd fp;
	FlightRWTraveller ft;
	  @Test(priority = 1)
	  public void AvailabilityTest() throws InterruptedException {
		  driver.get("https://www.easemytrip.com/");
		  rt = new RoundTrip(driver);
		  rt.findRoundTrip();
		  
	  }
	  
	  @Test(dependsOnMethods="AvailabilityTest")
	  public void Test() throws InterruptedException {
		  rt = new RoundTrip(driver);
		  rd=rt.gotoDetails();
		  //driver.get("https://flight.easemytrip.com/FlightListRT/Index?srch=DEL-Delhi-India|BOM-Mumbai-India|10/08/2020-12/8/2020&px=1-0-0&cbn=0&ar=undefined&isow=false&isdm=true&lng=&");
		  //rd = new RoundDetails(driver);
		 rd.getDetails();
		  //Assert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/index.php/auth/requestPasswordResetCode");
		 
		  
	  }
	 /*Test(dependsOnMethods="Test")
	  public void reviewTest() throws InterruptedException {
		  
		  //driver.get("https://flight.easemytrip.com/Review/CheckOut?CSU=&SearchID=5ty00uf2xi5&ft=0&bc=&ISWL=");
		  //vd = new ReviewDetails(driver);
		
		  //Assert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/index.php/auth/requestPasswordResetCode");
		 
		  
	  }*/
	  @Test(description="One-Way Flight details check" , dependsOnMethods = "Test")
		public void flightDetailsOneWay() throws Exception {
		  rd = new RoundDetails(driver);
		  
			//driver.get(prop.getProperty("url1"));
			//fp = new FlightTravellerAdd(driver);
		  ft=rd.verifyDetails();

			System.out.println(driver.getTitle());
			Assert.assertTrue(driver.getTitle().contains("FlightPayment"));

			flight1No = ft.getFlight1No();
			date1 = ft.getFlight1Date();

			String flight1NoPrev = ft.getFlight1NoPrev();
			LocalDate flight1DatePrev = ft.getFlight1DatePrev();

			flight2No = ft.getFlight2No();
			date2 = ft.getFlight2Date();

			String flight2NoPrev = ft.getFlight2NoPrev();
			LocalDate flight2DatePrev = ft.getFlight2DatePrev();

			Assert.assertTrue(flight1No.equals(flight1NoPrev));
			Assert.assertEquals(date1, flight1DatePrev);	
			Assert.assertTrue(flight2No.equals(flight2NoPrev));
			Assert.assertEquals(date2, flight2DatePrev);	
		}


		@Test(description="Coupon Check", dependsOnMethods = "flightDetailsOneWay",
				priority = 1)
		public void travellerEMICheckCLearButton() throws Exception {
			WebDriverWait wait=new WebDriverWait(driver, 20);

			//Coupon Clear button
			ft.clearCoupon();

			//Check if Coupon Text Field is blank
			boolean checkCouponField = ft.checkCouponFieldEmpty();
			assertTrue(checkCouponField);

			ft.enterCouponText("ASDF");
			ft.applyCoupon();
			//Thread.sleep(5000);
			boolean message;
			message = ft.checkInvalidCoupon();

			//Checking invalid coupon
			assertTrue(message);

			//get All coupons available
			List<WebElement> lcoupon = ft.getAllCouponsAvailable();

			//check valid coupons
			for(int i = 0; i < (lcoupon.size()-1); i++) {
				boolean message2 = ft.checkValidCoupon(lcoupon.get(i));
				assertTrue(message2);
			}
		}

		@Test(description="Travel Insurance Radio Button", dependsOnMethods = "flightDetailsOneWay", priority = 2)
		public void uinsuranceRadioCheck() throws Exception {

			List<WebElement> linsure = ft.getAllTravelInsuranceRadio();
			for(int i = 0; i < linsure.size(); i++) {
				boolean message2 = ft.checkValidTravelRadio(linsure.get(i));
				assertTrue(message2);
			}

		}


		@Test(description="Free Cancellation Radio Button", dependsOnMethods = "flightDetailsOneWay", priority = 3)
		public void freeCancellationRadio() {
			List<WebElement> lfree = ft.getAllFreeCancelRadio();
			for(int i = 0; i < lfree.size(); i++) {
				boolean message2 = ft.checkValidFreeCancelRadio(lfree.get(i));
				assertTrue(message2);
			}
		}

		@Test(description="Email Verification", dependsOnMethods = "flightDetailsOneWay", dataProvider = "dpForEmail", priority = 4)
		public void verifyEmail(String email) {



			boolean isEmail = ft.verifyEmail(email);

			if(isEmail) {
				assertTrue(isEmail, "Valid Email Verified");
			}else {
				assertTrue(!isEmail, "inValid Email Verified");
			}
		}


		@Test(description="Verify Adult Traveller Title", dependsOnMethods = "flightDetailsOneWay", priority = 5)
		public void verifyAdultTravellerTitleDropDown() throws InterruptedException {
			assertTrue(ft.verifyAdultTitleDropDown());
		}

		@Test(description="Verify Adult Traveller Details (First Name)", dependsOnMethods = "flightDetailsOneWay", dataProvider = "dpForTravellerName", priority = 6)
		public void verifyAdultTravellerFirstName(String firstName) {
			boolean isAlphabet = ft.isStringOnlyAlphabet(firstName);
			boolean isValid = ft.verifyInfantNameText("first",firstName);
			if(isAlphabet) {
				if(isValid) {
					assertTrue(true);
				}else {
					assertTrue(false);
				}
			}else {
				if(isValid) {
					assertTrue(false);
				}else {
					assertTrue(true);
				}
			}
		}


		@Test(description="Verify Adult Traveller Details (Last Name)", dependsOnMethods = "flightDetailsOneWay", dataProvider = "dpForTravellerName", priority = 7)
		public void verifyAdultTravellerLastName(String lastName) {
			boolean isAlphabet = ft.isStringOnlyAlphabet(lastName);
			boolean isValid = ft.verifyInfantNameText("second",lastName);
			if(isAlphabet) {
				if(isValid) {
					assertTrue(true);
				}else {
					assertTrue(false);
				}
			}else {
				if(isValid) {
					assertTrue(false);
				}else {
					assertTrue(true);
				}
			}
		}

		@Test(description="Verify Add Baggage button", dependsOnMethods = "flightDetailsOneWay", priority = 8)
		public void verifyAddBaggageButton() {
			boolean isAddBaggage = ft.verifyAddBaggageButton();
			assertTrue(isAddBaggage);
			driver.findElement(By.id("baggageBtn")).click();

		}

		@Test(description="Verify Remove Adult button", dependsOnMethods = "flightDetailsOneWay", priority = 9)
		public void removeAdult() {
			boolean removeAdult = ft.verifyRemoveAdult();
			assertTrue(removeAdult);
		}


		@Test(description="Verify Add Adult Button"/*, dataProvider = "dpForAdult"*/, dependsOnMethods = "flightDetailsOneWay", priority = 10)
		public void verifyAddAdultButton(){
			int adultNo = Integer.parseInt(driver.findElement(By.id("divNoAdult")).getText());
			int childNo = Integer.parseInt(driver.findElement(By.id("divNoChild")).getText());
			int infantNo =  Integer.parseInt(driver.findElement(By.id("divNoInfant")).getText());
			ExcelReader excel = new ExcelReader(prop.getProperty("excelpathFT"));
			ft.adultCheckBoxClick();

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
					ft.clickAddAdult();
				}
			}

			boolean addExtra = ft.verifyAddExtraAdult();
			assertTrue(addExtra);
		}


		@Test(description="Verify Child Traveller Title", dependsOnMethods = "flightDetailsOneWay", priority = 11)
		public void verifyChildTravellerTitle() {
			assertTrue(ft.verifyChildTitleDropDown());
		}

		@Test(description="Verify Child Traveller Details (First Name)", dependsOnMethods = "flightDetailsOneWay", dataProvider = "dpForTravellerName", priority = 12)
		public void verifyChildTravellerFirstName(String firstName) {
			boolean isAlphabet = ft.isStringOnlyAlphabet(firstName);
			boolean isValid = ft.verifyInfantNameText("first",firstName);
			if(isAlphabet) {
				if(isValid) {
					assertTrue(true);
				}else {
					assertTrue(false);
				}
			}else {
				if(isValid) {
					assertTrue(false);
				}else {
					assertTrue(true);
				}
			}
		}


		@Test(description="Verify Child Traveller Details (Last Name)", dependsOnMethods = "flightDetailsOneWay", dataProvider = "dpForTravellerName", priority = 13)
		public void verifyChildTravellerLastName(String lastName) {
			boolean isAlphabet = ft.isStringOnlyAlphabet(lastName);
			boolean isValid = ft.verifyInfantNameText("second",lastName);
			if(isAlphabet) {
				if(isValid) {
					assertTrue(true);
				}else {
					assertTrue(false);
				}
			}else {
				if(isValid) {
					assertTrue(false);
				}else {
					assertTrue(true);
				}
			}
		}

		@Test(description="Verify Remove Child button", dependsOnMethods = "flightDetailsOneWay", priority = 14)
		public void removeChild() {
			boolean removeChild = ft.verifyRemoveChild();
			assertTrue(removeChild);
		}

		@Test(description="Verify Add Child Button", dependsOnMethods = "flightDetailsOneWay", priority = 15)
		public void verifyAddChildButton(){
			int adultNo = Integer.parseInt(driver.findElement(By.id("divNoAdult")).getText());
			int childNo = Integer.parseInt(driver.findElement(By.id("divNoChild")).getText());
			int infantNo =  Integer.parseInt(driver.findElement(By.id("divNoInfant")).getText());
			ExcelReader excel = new ExcelReader(prop.getProperty("excelpathFT"));
			ft.childCheckBoxClick();

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
					ft.clickAddChild();
				}
			}

			boolean addExtra = ft.verifyAddExtraChild();
			assertTrue(addExtra);
		}


		@Test(description="Verify Infant Traveller Title", dependsOnMethods = "flightDetailsOneWay", priority = 16)
		public void verifyInfantTravellerTitle() {
			assertTrue(ft.verifyInfantTitleDropDown());
		}

		@Test(description="Verify Infant Traveller Details (First Name)", dependsOnMethods = "flightDetailsOneWay", dataProvider = "dpForTravellerName", priority = 17)
		public void verifyInfantTravellerFirstName(String firstName) {
			boolean isAlphabet = ft.isStringOnlyAlphabet(firstName);
			boolean isValid = ft.verifyInfantNameText("first",firstName);
			if(isAlphabet) {
				if(isValid) {
					assertTrue(true);
				}else {
					assertTrue(false);
				}
			}else {
				if(isValid) {
					assertTrue(false);
				}else {
					assertTrue(true);
				}
			}
		}


		@Test(description="Verify Infant Traveller Details (Last Name)", dependsOnMethods = "flightDetailsOneWay", dataProvider = "dpForTravellerName", priority = 18)
		public void verifyInfantTravellerLastName(String lastName) {
			boolean isAlphabet = ft.isStringOnlyAlphabet(lastName);
			boolean isValid = ft.verifyInfantNameText("second",lastName);
			if(isAlphabet) {
				if(isValid) {
					assertTrue(true);
				}else {
					assertTrue(false);
				}
			}else {
				if(isValid) {
					assertTrue(false);
				}else {
					assertTrue(true);
				}
			}
		}

		@Test(description="Verify Infant Traveller Details (DOB)", dependsOnMethods = "flightDetailsOneWay", priority = 19)
		public void verifyInfantTravellerDOB() {
			assertTrue(ft.verifyInfantDOB());
		}


		@Test(description="Verify Remove Infant button", dependsOnMethods = "flightDetailsOneWay", priority = 20)
		public void removeInfant() {
			boolean removeInfant = ft.verifyRemoveInfant();
			assertTrue(removeInfant);
		}

		@Test(description="Verify Add Infant Button", dependsOnMethods = "flightDetailsOneWay", priority = 21)
		public void verifyAddInfantButton(){
			int adultNo = Integer.parseInt(driver.findElement(By.id("divNoAdult")).getText());
			int childNo = Integer.parseInt(driver.findElement(By.id("divNoChild")).getText());
			int infantNo =  Integer.parseInt(driver.findElement(By.id("divNoInfant")).getText());
			ExcelReader excel = new ExcelReader(prop.getProperty("excelpathFT"));
			ft.infantCheckBoxClick();

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
				String retDay = ft.returnDay(day);

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
					ft.clickAddInfant();
				}
			}

			boolean addExtra = ft.verifyAddExtraInfant();
			assertTrue(addExtra);
		}

		@Test(description="Add Seat Button Verifcation", dependsOnMethods = "flightDetailsOneWay", priority = 22)
		public void addSeatVerify() throws Exception {
			boolean checkAdd = ft.verifyAddSeat();	
			assertTrue(checkAdd);
		}


		@Test(description="Verify Contact No.", dataProvider = "dpForContact", dependsOnMethods = "flightDetailsOneWay", priority = 23)
		public void verifyContactNo(String contact) throws InterruptedException {
			
			boolean isValidNo = ft.isValid(contact);
			ft.enterContactNo(contact);
			ft.clickPay();
			Thread.sleep(4000);
			boolean isDisplay = ft.checkPaySectionDisplayed();

			if(isDisplay) {
				if(isValidNo) {
					assertTrue(true);
				}else {
					assertTrue(false);
				}
				Thread.sleep(3000);
				driver.navigate().back();
			}else {
				if(isValidNo) {
					assertTrue(false);
				}else {
					assertTrue(true);
				}
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
			Object[] contact = new Object[row-2];
			System.out.println(row);
			for(int i = 0; i < row-2; i++) {
				contact[i] = excel.getCellData(prop.getProperty("sheetNameContact"), i+1, 0);
			}
			return contact;
		}
  
}
