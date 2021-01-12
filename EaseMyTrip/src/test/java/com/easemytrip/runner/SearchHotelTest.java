package com.easemytrip.runner;

import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.easemytrip.pom.SearchHotel;
import com.easemytrip.utils.Base;
import com.easemytrip.utils.ExcelReader;

public class SearchHotelTest extends Base {
	
	
	
	@DataProvider
	public Object[][] getData() {
		System.out.println(prop.getProperty("excelpath"))
;		ExcelReader ex = new ExcelReader(prop.getProperty("excelpath")); //excel file location from property
		int row = ex.getRowNum(prop.getProperty("sheetSearchName"));  //get excel sheet name from property
		System.out.println(row);
		Object[][] data = new Object[row-1][6]; 
		for(int i = 0; i <row-1;i++) {
			data[i][0]=ex.getCellData(prop.getProperty("sheetSearchName"), i+1, 0);
			data[i][1]=ex.getCellDataInt(prop.getProperty("sheetSearchName"), i+1, 1);
			data[i][2]=ex.getCellData(prop.getProperty("sheetSearchName"), i+1, 2);
			data[i][3]=ex.getCellDataInt(prop.getProperty("sheetSearchName"), i+1, 3);
			data[i][4]=ex.getCellData(prop.getProperty("sheetSearchName"), i+1, 4);
			data[i][5]=ex.getCellDataInt(prop.getProperty("sheetSearchName"), i+1, 5);

		}
		return data;
	}

	@Test(dataProvider="getData")
	public void SearchHotels(String cityname, int cindate, String cinmonth, int coutdate, String coutmonth, int guest)  throws IOException, InterruptedException{
		//driver= initializeDriver();
		driver.get("https://www.easemytrip.com/hotels/");
		System.out.println(cinmonth);
		System.out.println(cindate);
		System.out.println(coutmonth);
		System.out.println(coutdate);
		
		SearchHotel sh = new SearchHotel(driver);
		sh.ClickBox().click();
		sh.EnterCity().sendKeys(cityname);
		sh.SelectCity(cityname);
		sh.SelectCinDate(cinmonth, cindate);
		sh.SelectCoutDate(coutmonth, coutdate);
		sh.AddGuest(guest);
		sh.GoSearch().click();
		
		try {
			Alert a = driver.switchTo().alert();
			Thread.sleep(2000);
			a.accept();
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			
		}
		
		
		}
	
	/*@Test
	public void SearchHotels2()  throws IOException, InterruptedException{
		//driver= initializeDriver();
		driver.get("https://www.easemytrip.com/hotels/");
		
		SearchHotel sh = new SearchHotel(driver);
		sh.ClickBox().click();
		sh.EnterCity().sendKeys("weretew");
		sh.SelectCity();
		sh.SelectCinDate();
		sh.SelectCoutDate();
		sh.AddGuest();
		sh.GoSearch().click();
		
		
		}
*/


}



