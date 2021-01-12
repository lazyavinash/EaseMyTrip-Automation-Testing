package com.easemytrip.runner;


import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.easemytrip.pom.FlightSearchPage;
import com.easemytrip.utils.Base;
@Listeners()
public class FlightSearchTest extends Base {
	FlightSearchPage fp;
	 
	
		@Test(priority=1)
		public void search() throws Exception {
//		driver.get(prop.getProperty("url"));
		fp = new FlightSearchPage(driver);
		fp.doSearch(testLog);
		}
		
		@Test(priority=2, dependsOnMethods="search")
		public void titleVerify() throws Exception {
		fp.titleTest(testLog);
		}
		
		@Test(priority=3, dependsOnMethods="titleVerify")
		public void modify() throws Exception {
		fp.modifySearch(testLog);
		}
		
		@Test(priority=4, dependsOnMethods="modify")
		public void next() throws Exception {
		fp.nextDay(testLog);
		}
		@Test(priority=5, dependsOnMethods="next")
		public void prev() throws Exception {
		fp.prevDay(testLog);
		}

		@Test(priority=6, dependsOnMethods="prev")
		public void amountDisplay() throws Exception {
		fp.amount(testLog);
		}
		
		@Test(priority=7, dependsOnMethods="amountDisplay")
		public void scrollDown() {
		fp.firstScroll(testLog);
		}
		
		@Test(priority=8, dependsOnMethods="scrollDown")
		public void flightDetails() throws InterruptedException {
		fp.viewDetails(testLog);
		}
		
		@Test(priority=9, dependsOnMethods="flightDetails")
		public void addFilters() throws InterruptedException {
		fp.filter(testLog);
		}
		
		@Test(priority=10, dependsOnMethods="addFilters")
		public void scrollUp() {
		fp.secondScroll(testLog);
		}
		
		@Test(priority=11, dependsOnMethods="scrollUp")
		public void bookFlight() {
		fp.bookFilterFlight(testLog);
		}
		
	}

