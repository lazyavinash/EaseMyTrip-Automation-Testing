package com.easemytrip.runner;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.easemytrip.pom.AvailableHotelList;
import com.easemytrip.pom.HotelTravellerDetails;
import com.easemytrip.pom.SelectedHotel;
import com.easemytrip.utils.Base;
import com.easemytrip.utils.ExcelReader;

public class EaseMyTripTestRunner extends Base{
	AvailableHotelList ah;
	SelectedHotel sh;
	HotelTravellerDetails fp;
	@Test(priority = 1,description= "Easy Stay Test")
	public void clickeasybtn() throws Exception {
		driver.get(prop.getProperty("URL1"));
		ah= new AvailableHotelList(driver);
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
		ah.clickonbrowseall(testLog);
		
		try {
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Browse All Hotels")));
		} catch (Exception E) {
			throw new AssertionError("Could not select Browse all");
		}
	}

	@Test(priority = 4,description= "Trip advisor test")
	public void chcktripadvisor() throws Exception {

		
		ah.chcktripfilter(testLog);
		try {
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Browse All Hotels")));
		} catch (Exception E) {
			throw new AssertionError("Couldn't load");
		}
	}

	@Test(priority = 5,description= "Amenities filter test")
	public void chckamenities() throws Exception {

		
		ah.chckamenitiesfilter(testLog);
		try {
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Browse All Hotels")));
		} catch (Exception E) {
			throw new AssertionError("Couldn't load");
		}

	}
	@Test(priority = 6,description= "book now button test")
	public void listpagebooknow() throws Exception {

		sh = new SelectedHotel(driver);
		sh = ah.listpagebooknow(testLog);

	}
	
	
	
	@Test(priority = 7,description= "Show Amenities test",dependsOnMethods="listpagebooknow")
	public void clickonshowallamenities() throws Exception  {
		
		
	//	sh= new SelectedHotel(driver);
		sh.showallAmenities(testLog);

	}
	
	@Test(priority=8,description= "Scrolling to review test")
	public void scrolltoreviews() throws Exception {
		
		sh.scrolltoreviews(testLog);
		
	}
	
	@Test(priority = 9,description= "Book now test")
	public void clickonbooknow() throws InterruptedException  {
		
		sh.clickonBookNow(testLog);

	}

	@Test(priority = 10,description= "Title selecting test")
	public void selectTitle() {
		fp=new HotelTravellerDetails(driver);
		fp.selectTitle(testLog);
		try {
			new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.name("ddlGender")));
		} catch (Exception E) {
			throw new AssertionError("Select Title Failed");
		}
	}
	@DataProvider
	public Object[][] dp() {
		ExcelReader ex = new ExcelReader((prop.getProperty("excelpath"))); //excel ka location from property
		int row = ex.getRowNum(prop.getProperty("sheetName"));  //get excel sheet name from property
		
		Object[][] data = new Object[row-1][6]; 
		for(int i = 0; i <row-1;i++) {
			data[i][0]=ex.getCellData(prop.getProperty("sheetName"), i+1, 0);
			data[i][1]=ex.getCellData(prop.getProperty("sheetName"), i+1, 1);
			data[i][2]=ex.getCellData(prop.getProperty("sheetName"), i+1, 2);
			data[i][3]=ex.getCellData(prop.getProperty("sheetName"), i+1, 3);
			data[i][4]=ex.getCellData(prop.getProperty("sheetName"), i+1, 4);
			data[i][5]=ex.getCellData(prop.getProperty("sheetName"), i+1, 5);

		}
		return data;
	}
	
	
	@Test(priority = 11,dataProvider = "dp",description= "Traveller details test")
	public void enterdetails(String fname, String lname,String fname2,String lname2,String email,String contact) //same sequence as taken values from data provider
	{
	
		
		fp.enterdetails(fname, lname, fname2, lname2, email, contact);
		/*boolean a=fp.isChecked();
		if(!a) {
			fp.clickoncheckbox(testLog);
		}*/
		fp.clickoncheckbox(testLog);
		fp.clickonPay(testLog);
			
	
		
	}
	
	
	
}
