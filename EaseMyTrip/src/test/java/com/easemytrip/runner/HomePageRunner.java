package com.easemytrip.runner;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.easemytrip.pom.FlightSearchPage;
import com.easemytrip.pom.FlightTraveller;
import com.easemytrip.pom.HomePage;
import com.easemytrip.utils.Base;
import com.easemytrip.utils.ExcelReader;

public class HomePageRunner extends Base {
	HomePage hp;
	FlightSearchPage fp;
	String hpage;
	String flightNo;
	LocalDate date;
	FlightTraveller ft; 


	@Test(priority = 1)
	public void homePageTest() {
		driver.get(prop.getProperty("url"));
		Assert.assertEquals(driver.getTitle(), prop.getProperty("homePageTitle"));
		hpage = driver.getWindowHandle();
		hp = new HomePage(driver);
	}

	@Test(priority = 2, dependsOnMethods = { "homePageTest" })
	public void WebCheckInClickTest() throws InterruptedException {
		hp.linkCheck(prop.getProperty("webCheckInLink"));
		driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString()); // Switch Airline
		Assert.assertEquals(driver.getTitle(), prop.getProperty("webCheckInTitle"));
		driver.close();
		driver.switchTo().window(hpage);
	}

	@Test(priority = 3, dependsOnMethods = { "WebCheckInClickTest" })
	public void FlightsModuleClickTest() {
		testLog.info("Clicking on FLIGHTS Module ");
		hp.linkCheck(prop.getProperty("flightsLink"));
		Assert.assertEquals(driver.getCurrentUrl(), prop.getProperty("flightsURL"));
	}

	@Test(priority = 4, dependsOnMethods = { "FlightsModuleClickTest" })
	public void logoClickTest() {
		driver.get(prop.getProperty("url"));
		testLog.info("Clicking on EaseMyTrip Logo");
		hp.xpathClickCheck(prop.getProperty("logoXpath"));
		Assert.assertEquals(driver.getCurrentUrl(), prop.getProperty("url"));
	}

	@Test(priority = 5, dependsOnMethods = { "logoClickTest" })
	public void multicityCheckBoxTest() {
		testLog.info("Clicking on MultiCity Checkbox");
		hp.checkBoxCheck(prop.getProperty("multicityCheckBoxXpath"));
	}

	@Test(priority = 6, dependsOnMethods = { "logoClickTest" })
	public void DefenceCheckBoxTest() {
		testLog.info("Clicking on Defence CheckBox ");
		hp.checkBoxCheck(prop.getProperty("defenceCheckBoxXpath"));
	}

	@Test(priority = 7, dependsOnMethods = { "logoClickTest" })
	public void studentsCheckBoxTest() {
		testLog.info("Clicking on Students CheckBox ");
		hp.checkBoxCheck(prop.getProperty("studentCheckBoxXpath"));

	}

	@Test(priority = 8, dependsOnMethods = { "logoClickTest" })
	public void seniorCitizenCheckBoxTest() {
		testLog.info("Clicking on Senior Citizen CheckBox ");
		hp.checkBoxCheck(prop.getProperty("seniorCitizenCheckBoxXpath"));
	}

	@Test(priority = 9, dependsOnMethods = { "logoClickTest" })
	public void doctorsCheckBoxTest() {
		testLog.info("Clicking on Doctors and Nurses CheckBox ");
		hp.checkBoxCheck(prop.getProperty("doctorCheckBoxXpath"));
	}

	@Test(priority = 10, dependsOnMethods = { "FlightsModuleClickTest" })
	public void HotelsModuleClickTest() {
		testLog.info("Clicking on HOTELS Module ");
		hp.linkCheck(prop.getProperty("hotelsLink"));
		Assert.assertEquals(driver.getTitle(), prop.getProperty("hotelsTitle"));
	}

	@Test(priority = 11, dependsOnMethods = { "HotelsModuleClickTest" })
	public void importantInfoClickTest() {
		driver.get(prop.getProperty("url"));
		testLog.info("Clicking on important information link ");
		hp.linkCheck(prop.getProperty("impInfoLink"));
		driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
		Assert.assertEquals(driver.getTitle(), prop.getProperty("impInfoTitle"));
		driver.close();
		driver.switchTo().window(hpage);
	}

	@Test(priority = 12, dependsOnMethods = { "importantInfoClickTest" })
	public void ourProductsClickTest() {
		hp.scroll(0, prop.getProperty("javascriptScrollDown"));
		testLog.info("Clicking on Flight Status ");
		hp.linkCheck(prop.getProperty("flightStatusLink"));
		Assert.assertEquals(driver.getTitle(), prop.getProperty("flightStatusTitle"));
	}

	@Test(priority = 13, dependsOnMethods = { "ourProductsClickTest" })
	public void emtInfoClickTest() {
		driver.get(prop.getProperty("url"));
		testLog.info("Clicking on Terms & Conditions ");
		hp.linkCheck(prop.getProperty("termsLink"));
		Assert.assertEquals(driver.getTitle(), prop.getProperty("termsTitle"));
	}

	@Test(priority = 14, dependsOnMethods = { "emtInfoClickTest" })
	public void siteDirectoryClickTest() {
		driver.get(prop.getProperty("url"));
		testLog.info("Clicking on Airports ");
		hp.linkCheck(prop.getProperty("airportsLink"));
		Assert.assertEquals(driver.getTitle(), prop.getProperty("airportsTitle"));
	}

	@Test(priority = 15, dependsOnMethods = { "siteDirectoryClickTest" })
	public void popularAirlinesClickTest() {
		driver.get(prop.getProperty("url"));
		testLog.info("Clicking on Air Asia Airlines ");
		hp.linkCheck(prop.getProperty("airAsiaLink"));
		Assert.assertEquals(driver.getTitle(), prop.getProperty("airAsiaTitle"));
	}

	@Test(priority = 16, dependsOnMethods = { "popularAirlinesClickTest" })
	public void androidStoreClickTest() {
		driver.get(prop.getProperty("url"));

		testLog.info("Clicking on Android Store App link ");
		hp.linkCheckByCssTitle(prop.getProperty("androidCSSTitle"));
		Assert.assertEquals(driver.getCurrentUrl(), prop.getProperty("androidURL"));
	}

	@Test(priority = 17, dependsOnMethods = { "androidStoreClickTest" })
	public void iosStoreClickTest() {
		driver.get(prop.getProperty("url"));

		testLog.info("Clicking on  IOS Store App link ");
		hp.linkCheckByCssTitle(prop.getProperty("iosCSSTitle"));
		Assert.assertEquals(driver.getCurrentUrl(), prop.getProperty("iosURL"));
	}

	@Test(priority = 17)
	public void switchCheck() {
		testLog.info("Clicking on switch cities button");
		driver.get(prop.getProperty("url"));
		driver.findElement(By.id(prop.getProperty("switchID"))).click();
	}

	@Test(priority = 18)
	public void myAccountMouseHoverTest() {
		testLog.info("Checking MyAccount Mouse Hover");
		Actions mouseHover = new Actions(driver);
		mouseHover.moveToElement(driver.findElement(By.xpath(prop.getProperty("myAccountArrowXpath")))).perform();
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("myAccountArrowXpath"))));
		assertTrue(driver.findElement(By.className(prop.getProperty("signInButtonClass"))).isDisplayed());
	}

	@Test(priority = 19)
	public void helplineMouseHoverTest() {
		testLog.info("Checking Helpline Mouse Hover");
		Actions mouseHover = new Actions(driver);
		mouseHover.moveToElement(driver.findElement(By.xpath(prop.getProperty("helpLineArrowLink")))).perform();
		assertTrue(driver.findElement(By.className(prop.getProperty("phoneNoClass"))).isDisplayed());
	}

	@Test(priority = 20)
	public void TravelGuideClickTest() throws InterruptedException {
		testLog.info("Clicking on TravelGuide link");
		driver.get(prop.getProperty("url"));
		driver.findElement(By.cssSelector(prop.getProperty("travelGuideCSSHref"))).click();
		driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString()); // Switch Airline
		Assert.assertEquals(driver.getTitle(), prop.getProperty("travelGuideTitle"));
		driver.close();
		driver.switchTo().window(hpage);
	}



	@Test(dataProvider = "dp", priority = 21)
	public void searchFlightOneWayTest(String from, String to, String ddate, int adult, int infant, int children,
			String travelClass) throws InterruptedException {

		driver.get(prop.getProperty("url"));
		hp.doSearch(from, to, ddate, adult, infant, children, travelClass, testLog);
		// oneWay=driver;
	}

	@Test(priority = 22)
	public void search() throws Exception {
		//	driver.get(prop.getProperty("url"));
		fp = new FlightSearchPage(driver);
		fp.doSearch(testLog);
	}

	@Test(priority = 23, dependsOnMethods = "search")
	public void titleVerify() throws Exception {
		fp.titleTest(testLog);
	}

	@Test(priority = 24, dependsOnMethods = "titleVerify")
	public void modify() throws Exception {
		fp.modifySearch(testLog);
	}

	@Test(priority = 25, dependsOnMethods = "modify")
	public void next() throws Exception {
		fp.nextDay(testLog);
	}

	@Test(priority = 26, dependsOnMethods = "next")
	public void prev() throws Exception {
		fp.prevDay(testLog);
	}

	@Test(priority = 27, dependsOnMethods = "prev")
	public void amountDisplay() throws Exception {
		fp.amount(testLog);
	}

	@Test(priority = 28, dependsOnMethods = "amountDisplay")
	public void scrollDown() {
		fp.firstScroll(testLog);
	}

	@Test(priority = 29, dependsOnMethods = "scrollDown")
	public void flightDetails() throws InterruptedException {
		fp.viewDetails(testLog);
	}

	@Test(priority = 30, dependsOnMethods = "flightDetails")
	public void addFilters() throws InterruptedException {
		fp.filter(testLog);
	}

	@Test(priority = 31, dependsOnMethods = "addFilters")
	public void scrollUp() {
		fp.secondScroll(testLog);
	}

	@Test(priority = 32, dependsOnMethods = "scrollUp")
	public void bookFlight() throws InterruptedException {
		//	ft=new FlightTraveller(driver, flightNo);
		driver.findElement(By.xpath("//label[contains(text(),'Nonstop')]")).click();
		Thread.sleep(2000);
		ft=fp.bookFilterFlight(testLog);

	}


	/*	  @DataProvider public Object[][] dp() { Object data[][] = new Object[1][7];
	  ExcelReader ex = new ExcelReader(prop.getProperty("excelpath4"));
	  System.out.println(ex.getRowNum("Sheet1")); for (int i = 1; i <
	  ex.getRowNum("Sheet1"); i++) { int j = 0; while (j == 0) { data[i - 1][0] =
	  ex.getCellData("Sheet1", i, j); data[i - 1][1] = ex.getCellData("Sheet1", i,
	  j + 1); data[i - 1][2] = ex.getCellData("Sheet1", i, j + 2); data[i - 1][3] =
	  ex.getCellDataInt("Sheet1", i, j + 3); data[i - 1][4] =
	  ex.getCellDataInt("Sheet1", i, j + 4); data[i - 1][5] =
	  ex.getCellDataInt("Sheet1", i, j + 5); data[i - 1][6] =
	  ex.getCellData("Sheet1", i, j + 6); j++; } } return data; }
	 */


	@DataProvider
	public Object[][] dp() {
		ExcelReader ex = new ExcelReader(prop.getProperty("excelpath4")); //
		int row=ex.getRowNum("Sheet1");
		Object data[][] = new Object[row-1][7];
		System.out.println(row);
		for (int i = 1; i < row; i++) {
			int j = 0;
			while (j == 0) {
				data[i - 1][0] = ex.getCellData("Sheet1", i, j);
				data[i - 1][1] = ex.getCellData("Sheet1", i, j + 1);
				data[i - 1][2] = ex.getCellData("Sheet1", i, j + 2);
				data[i - 1][3] = ex.getCellDataInt("Sheet1", i, j + 3);
				data[i - 1][4] = ex.getCellDataInt("Sheet1", i, j + 4);
				data[i - 1][5] = ex.getCellDataInt("Sheet1", i, j + 5);
				data[i - 1][6] = ex.getCellData("Sheet1", i, j + 6);
				j++;
			}
		}
		return data;
	}

	@Test(description = "One-Way Flight details check", priority = 33)
	public void flightDetailsOneWay() throws Exception {

		//	driver.get(prop.getProperty("url"));
		//	fp = new FlightSearchPage(driver);
		//	ft = fp.bookFilterFlight(testLog);

		System.out.println(driver.getTitle());
		Assert.assertTrue(driver.getTitle().contains("FlightPayment"));

		flightNo = ft.getFlightNo(testLog);
		date = ft.getFlightDate(testLog);

		String flightNoPrev = ft.getFlightNoPrev(testLog);
		LocalDate flightDatePrev = ft.getFlightDatePrev(testLog);

		Assert.assertTrue(flightNo.equals(flightNoPrev));
		Assert.assertEquals(date, flightDatePrev);
	}

	@Test(description = "Coupon Check", dependsOnMethods = "flightDetailsOneWay", priority = 34)
	public void travellerEMICheckCLearButton() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 20);

		// Coupon Clear button
		ft.clearCoupon(testLog);

		// Check if Coupon Text Field is blank
		boolean checkCouponField = ft.checkCouponFieldEmpty(testLog);
		assertTrue(checkCouponField);

		ft.enterCouponText("ASDF", testLog);
		ft.applyCoupon(testLog);
		// Thread.sleep(5000);
		boolean message;
		message = ft.checkInvalidCoupon(testLog);

		// Checking invalid coupon
		assertTrue(message);

		// get All coupons available
		List<WebElement> lcoupon = ft.getAllCouponsAvailable(testLog);

		// check valid coupons
		for (int i = 0; i < (lcoupon.size() - 1); i++) {
			boolean message2 = ft.checkValidCoupon(lcoupon.get(i), testLog);
			assertTrue(message2);
		}
	}

	@Test(description = "Travel Insurance Radio Button", dependsOnMethods = "flightDetailsOneWay", priority = 35)
	public void uinsuranceRadioCheck() throws Exception {

		List<WebElement> linsure = ft.getAllTravelInsuranceRadio(testLog);
		for (int i = 0; i < linsure.size(); i++) {
			boolean message2 = ft.checkValidTravelRadio(linsure.get(i), testLog);
			assertTrue(message2);
		}

	}

	@Test(description = "Free Cancellation Radio Button", dependsOnMethods = "flightDetailsOneWay", priority = 36)
	public void freeCancellationRadio() {
		List<WebElement> lfree = ft.getAllFreeCancelRadio(testLog);
		for (int i = 0; i < lfree.size(); i++) {
			boolean message2 = ft.checkValidFreeCancelRadio(lfree.get(i), testLog);
			assertTrue(message2);
		}
	}

	@Test(description = "Email Verification", dependsOnMethods = "flightDetailsOneWay", dataProvider = "dpForEmail", priority = 37)
	public void verifyEmail(String email) {

		boolean isEmail = ft.verifyEmail(email, testLog);

		if (isEmail) {
			assertTrue(isEmail, "Valid Email Verified");
		} else {
			assertTrue(!isEmail, "inValid Email Verified");
		}
	}

	@Test(description = "Verify Adult Traveller Title", dependsOnMethods = "flightDetailsOneWay", priority = 38)
	public void verifyAdultTravellerTitleDropDown() {
		assertTrue(ft.verifyAdultTitleDropDown(testLog));
	}

	@Test(description = "Verify Adult Traveller Details (First Name)", dependsOnMethods = "flightDetailsOneWay", dataProvider = "dpForTravellerName", priority = 39)
	public void verifyAdultTravellerFirstName(String firstName) throws InterruptedException {
		boolean isAlphabet = ft.isStringOnlyAlphabet(firstName);
		boolean isValid = ft.verifyInfantNameText("first",firstName, testLog);
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

	@Test(description = "Verify Adult Traveller Details (Last Name)", dependsOnMethods = "flightDetailsOneWay", dataProvider = "dpForTravellerName", priority = 40)
	public void verifyAdultTravellerLastName(String lastName) {
		boolean isAlphabet = ft.isStringOnlyAlphabet(lastName);
		boolean isValid = ft.verifyInfantNameText("second",lastName, testLog);
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

	@Test(description = "Verify Add Baggage button", dependsOnMethods = "flightDetailsOneWay", priority = 41)
	public void verifyAddBaggageButton() {
		boolean isAddBaggage = ft.verifyAddBaggageButton(testLog);
		assertTrue(isAddBaggage);
		driver.findElement(By.id("baggageBtn")).click();

	}

	/*
	 * @Test(description="Verify Baggage radio button", dependsOnMethods =
	 * "flightDetailsOneWay", priority = 9) public void verifyBaggageRadioButton() {
	 * List<WebElement> lAll = ft.getAllBaggageOptions(); boolean isChecked =
	 * ft.verifyBaggageRadio(); assertTrue(isChecked); }
	 */

	@Test(description = "Verify Remove Adult button", dependsOnMethods = "flightDetailsOneWay", priority = 42)
	public void removeAdult() {
		boolean removeAdult = ft.verifyRemoveAdult(testLog);
		assertTrue(removeAdult);
	}

	@Test(description = "Verify Add Adult Button"/* , dataProvider = "dpForAdult" */, dependsOnMethods = "flightDetailsOneWay", priority = 43)
	public void verifyAddAdultButton() {
		int adultNo = Integer.parseInt(driver.findElement(By.id("divNoAdult")).getText());
		int childNo = Integer.parseInt(driver.findElement(By.id("divNoChild")).getText());
		int infantNo = Integer.parseInt(driver.findElement(By.id("divNoInfant")).getText());
		ExcelReader excel = new ExcelReader(prop.getProperty("excelpathFT"));
		ft.adultCheckBoxClick(testLog);

		for (int a = 0; a < adultNo; a++) {
			String titleDropDown = "titleAdult" + a;
			System.out.println(titleDropDown);
			Select title = new Select(driver.findElement(By.id(titleDropDown)));
			title.selectByVisibleText(excel.getCellData(prop.getProperty("sheetNameAdult"), a + 1, 0));
			String firstName = "txtFNAdult" + a;
			String lastName = "txtLNAdult" + a;

			driver.findElement(By.id(firstName)).clear();
			driver.findElement(By.id(firstName))
			.sendKeys(excel.getCellData(prop.getProperty("sheetNameAdult"), a + 1, 1));
			Actions tab = new Actions(driver);
			Action pressTab = tab.sendKeys(Keys.TAB).build();
			pressTab.perform();

			driver.findElement(By.id(lastName)).clear();
			driver.findElement(By.id(lastName))
			.sendKeys(excel.getCellData(prop.getProperty("sheetNameAdult"), a + 1, 2));

			if (a != adultNo - 1) {
				ft.clickAddAdult(testLog);
			}
		}

		boolean addExtra = ft.verifyAddExtraAdult(testLog);
		assertTrue(addExtra);
	}

	/*
	 * @DataProvider public Object[][] dpForAdult() { ExcelReader excel = new
	 * ExcelReader(prop.getProperty("excelpath")); int row =
	 * excel.getRowNum(prop.getProperty("sheetNameAdult")); int col =
	 * excel.getColumnNum(prop.getProperty("sheetNameAdult")); Object[][] adult =
	 * new Object[row-1][col]; System.out.println(row+"  "+col); for(int i = 0; i <
	 * row-1; i++) { adult[i][0] =
	 * excel.getCellData(prop.getProperty("sheetNameAdult"), i+1, 0); adult[i][1] =
	 * excel.getCellData(prop.getProperty("sheetNameAdult"), i+1, 1); adult[i][2] =
	 * excel.getCellData(prop.getProperty("sheetNameAdult"), i+1, 2); } return
	 * adult; }
	 */

	@Test(description = "Verify Child Traveller Title", dependsOnMethods = "flightDetailsOneWay", priority = 44)
	public void verifyChildTravellerTitle() {
		assertTrue(ft.verifyChildTitleDropDown(testLog));
	}

	@Test(description = "Verify Child Traveller Details (First Name)", dependsOnMethods = "flightDetailsOneWay", dataProvider = "dpForTravellerName", priority = 45)
	public void verifyChildTravellerFirstName(String firstName) {
		boolean isAlphabet = ft.isStringOnlyAlphabet(firstName);
		boolean isValid = ft.verifyInfantNameText("first",firstName, testLog);
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

	@Test(description = "Verify Child Traveller Details (Last Name)", dependsOnMethods = "flightDetailsOneWay", dataProvider = "dpForTravellerName", priority = 46)
	public void verifyChildTravellerLastName(String lastName) {
		boolean isAlphabet = ft.isStringOnlyAlphabet(lastName);
		boolean isValid = ft.verifyInfantNameText("second",lastName, testLog);
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

	@Test(description = "Verify Remove Child button", dependsOnMethods = "flightDetailsOneWay", priority = 47)
	public void removeChild() {
		boolean removeChild = ft.verifyRemoveChild(testLog);
		assertTrue(removeChild);
	}

	@Test(description = "Verify Add Child Button", dependsOnMethods = "flightDetailsOneWay", priority = 48)
	public void verifyAddChildButton() {
		int adultNo = Integer.parseInt(driver.findElement(By.id("divNoAdult")).getText());
		int childNo = Integer.parseInt(driver.findElement(By.id("divNoChild")).getText());
		int infantNo = Integer.parseInt(driver.findElement(By.id("divNoInfant")).getText());
		ExcelReader excel = new ExcelReader(prop.getProperty("excelpathFT"));
		ft.childCheckBoxClick(testLog);

		for (int a = 0; a < childNo; a++) {
			String titleDropDown = "titleChild" + a;
			System.out.println(titleDropDown);
			Select title = new Select(driver.findElement(By.id(titleDropDown)));
			title.selectByVisibleText(excel.getCellData(prop.getProperty("sheetNameChild"), a + 1, 0));
			String firstName = "txtFNChild" + a;
			String lastName = "txtLNChild" + a;

			driver.findElement(By.id(firstName)).clear();
			driver.findElement(By.id(firstName))
			.sendKeys(excel.getCellData(prop.getProperty("sheetNameChild"), a + 1, 1));
			Actions tab = new Actions(driver);
			Action pressTab = tab.sendKeys(Keys.TAB).build();
			pressTab.perform();

			driver.findElement(By.id(lastName)).clear();
			driver.findElement(By.id(lastName))
			.sendKeys(excel.getCellData(prop.getProperty("sheetNameChild"), a + 1, 2));

			if (a != childNo - 1) {
				ft.clickAddChild(testLog);
			}
		}

		boolean addExtra = ft.verifyAddExtraChild(testLog);
		assertTrue(addExtra);
	}

	@Test(description = "Verify Infant Traveller Title", dependsOnMethods = "flightDetailsOneWay", priority = 49)
	public void verifyInfantTravellerTitle() {
		assertTrue(ft.verifyInfantTitleDropDown(testLog));
	}

	@Test(description = "Verify Infant Traveller Details (First Name)", dependsOnMethods = "flightDetailsOneWay", dataProvider = "dpForTravellerName", priority = 50)
	public void verifyInfantTravellerFirstName(String firstName) {
		boolean isAlphabet = ft.isStringOnlyAlphabet(firstName);
		boolean isValid = ft.verifyInfantNameText("first",firstName, testLog);
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

	@Test(description = "Verify Infant Traveller Details (Last Name)", dependsOnMethods = "flightDetailsOneWay", dataProvider = "dpForTravellerName", priority = 51)
	public void verifyInfantTravellerLastName(String lastName) {
		boolean isAlphabet = ft.isStringOnlyAlphabet(lastName);
		boolean isValid = ft.verifyInfantNameText("second",lastName, testLog);
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

	@Test(description = "Verify Infant Traveller Details (DOB)", dependsOnMethods = "flightDetailsOneWay", priority = 52)
	public void verifyInfantTravellerDOB() {
		assertTrue(ft.verifyInfantDOB(testLog));
	}

	@Test(description = "Verify Remove Infant button", dependsOnMethods = "flightDetailsOneWay", priority = 53)
	public void removeInfant() {
		boolean removeInfant = ft.verifyRemoveInfant(testLog);
		assertTrue(removeInfant);
	}

	@Test(description = "Verify Add Infant Button", dependsOnMethods = "flightDetailsOneWay", priority = 54)
	public void verifyAddInfantButton() {
		int adultNo = Integer.parseInt(driver.findElement(By.id("divNoAdult")).getText());
		int childNo = Integer.parseInt(driver.findElement(By.id("divNoChild")).getText());
		int infantNo = Integer.parseInt(driver.findElement(By.id("divNoInfant")).getText());
		ExcelReader excel = new ExcelReader(prop.getProperty("excelpathFT"));
		ft.infantCheckBoxClick(testLog);

		for (int a = 0; a < infantNo; a++) {
			String titleDropDown = "titleInfant" + a;
			System.out.println(titleDropDown);
			Select title = new Select(driver.findElement(By.id(titleDropDown)));
			title.selectByVisibleText(excel.getCellData(prop.getProperty("sheetNameInfant"), a + 1, 0));
			String firstName = "txtFNInfant" + a;
			String lastName = "txtLNInfant" + a;

			driver.findElement(By.id(firstName)).clear();
			driver.findElement(By.id(firstName))
			.sendKeys(excel.getCellData(prop.getProperty("sheetNameInfant"), a + 1, 1));
			Actions tab = new Actions(driver);
			Action pressTab = tab.sendKeys(Keys.TAB).build();
			pressTab.perform();

			driver.findElement(By.id(lastName)).clear();
			driver.findElement(By.id(lastName))
			.sendKeys(excel.getCellData(prop.getProperty("sheetNameInfant"), a + 1, 2));

			System.out.println(excel.getCellData(prop.getProperty("sheetNameInfant"), a + 1, 3));

			String day = excel.getCellData(prop.getProperty("sheetNameInfant"), a + 1, 3).split("-")[0];
			String retDay = ft.returnDay(day, testLog);

			String month = excel.getCellData(prop.getProperty("sheetNameInfant"), a + 1, 3).split("-")[1];
			String year = excel.getCellData(prop.getProperty("sheetNameInfant"), a + 1, 3).split("-")[2];

			System.out.println(day + " " + month + " " + year);

			String selectDate = "divDOBDayInfant" + a;
			String selectMonth = "divDOBMonInfant" + a;
			String selectYear = "divDOBYarInfant" + a;

			Select sDay = new Select(driver.findElement(By.id(selectDate)));
			Select sMonth = new Select(driver.findElement(By.id(selectMonth)));
			Select sYear = new Select(driver.findElement(By.id(selectYear)));

			sDay.selectByVisibleText(retDay);
			sMonth.selectByVisibleText(month);
			sYear.selectByVisibleText(year);

			// ft.selectInfantDOB(day, month, year);

			if (a != infantNo - 1) {
				ft.clickAddInfant(testLog);
			}
		}

		boolean addExtra = ft.verifyAddExtraInfant(testLog);
		assertTrue(addExtra);
	}

	@Test(description = "Add Seat Button Verifcation", dependsOnMethods = "flightDetailsOneWay", priority = 55)
	public void addSeatVerify() throws Exception {
		boolean checkAdd = ft.verifyAddSeat(testLog);
		assertTrue(checkAdd);
	}

	@Test(description = "Verify Contact No.", dataProvider = "dpForContact", dependsOnMethods = "flightDetailsOneWay", priority = 56)
	public void verifyContactNo(String contact) throws InterruptedException {
		/*
		 * System.out.println(contact); assertTrue(ft.verifyContactNo(contact));
		 */

		boolean isValidNo = ft.isValid(contact, testLog);
		ft.enterContactNo(contact, testLog);
		ft.clickPay(testLog);
		Thread.sleep(4000);
		boolean isDisplay = ft.checkPaySectionDisplayed(testLog);

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
		Object[] first = new Object[row - 1];
		System.out.println(row);
		for (int i = 0; i < row - 1; i++) {
			first[i] = excel.getCellData(prop.getProperty("sheetName"), i + 1, 1);

		}
		return first;
	}

	@DataProvider
	public Object[] dpForEmail() {
		ExcelReader excel = new ExcelReader(prop.getProperty("excelpathFT"));
		int row = excel.getRowNum(prop.getProperty("sheetName"));
		Object[] email = new Object[row - 1];
		System.out.println(row);
		for (int i = 0; i < row - 1; i++) {
			email[i] = excel.getCellData(prop.getProperty("sheetName"), i + 1, 0);
		}
		return email;
	}

	@DataProvider
	public Object[] dpForContact() {
		ExcelReader excel = new ExcelReader(prop.getProperty("excelpathFT"));
		int row = excel.getRowNum(prop.getProperty("sheetNameContact"));
		Object[] contact = new Object[row - 2];
		System.out.println(row);
		for (int i = 0; i < row - 2; i++) {
			contact[i] = excel.getCellData(prop.getProperty("sheetNameContact"), i + 1, 0);
		}
		return contact;
	}

}
