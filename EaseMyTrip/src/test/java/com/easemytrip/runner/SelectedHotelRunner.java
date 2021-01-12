package com.easemytrip.runner;

import org.testng.annotations.Test;

import com.easemytrip.pom.SelectedHotel;
import com.easemytrip.utils.Base;

public class SelectedHotelRunner extends Base {
	@Test(priority = 1,description= "Show Amenities test")
	public void clickonshowallamenities() throws InterruptedException  {
		
		driver.get(prop.getProperty("URL3"));
		SelectedHotel sh = new SelectedHotel(driver);
		sh.showallAmenities(testLog);

	}
	
	@Test(priority=2,description= "Scrolling to review test")
	public void scrolltoreviews() throws Exception {
		SelectedHotel sh = new SelectedHotel(driver);
		sh.scrolltoreviews(testLog);
		
	}
	
	@Test(priority = 3,description= "Book now test")
	public void clickonbooknow() throws InterruptedException  {
		SelectedHotel sh = new SelectedHotel(driver);
		sh.clickonBookNow(testLog);

	}
}
