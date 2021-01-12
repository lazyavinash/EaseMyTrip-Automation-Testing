package com.easemytrip.pom;

import static org.testng.Assert.assertTrue;

import javax.swing.JOptionPane;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.easemytrip.utils.BasePage;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	/* toSearch Function is used to clear , fill and search flight on home page */

	public FlightSearchPage doSearch(String from, String to, String ddate, int adult, int infant, int children,
			String travelClass, ExtentTest log) throws InterruptedException {

		driver.manage().deleteAllCookies();
		driver.navigate().refresh();
		log.info("clicking and clearing origin");
		driver.findElement(By.id(prop.getProperty("originID"))).click();

		log.info("clicking and clearing destination");

		driver.findElement(By.id(prop.getProperty("destinationID"))).click();

		log.info("typing origin");
		driver.findElement(By.id(prop.getProperty("originID"))).sendKeys(from);

		log.info("Waiting for Options & Selecting");
		try {
			driver.findElement(By.xpath(
					prop.getProperty("originSuggestionPart1") + from + prop.getProperty("originSuggestionPart2")))
			.click();
		} catch (NoSuchElementException e) {
		}

		log.info("typing destination");
		driver.findElement(By.id(prop.getProperty("destinationID"))).sendKeys(to);

		log.info("Waiting for Options & Selecting");
		try {
			driver.findElement(By.xpath(prop.getProperty("destinationSuggestionPart1") + to
					+ prop.getProperty("destinationSuggestionPart2"))).click();
		} catch (NoSuchElementException e) {
		}
		log.info("Picking Date");
		pickDate(ddate);

		log.info("Setting Total Travellers");
		pickTraveller(adult, infant, children);

		log.info("Picking Travel Class");
		pickClass(travelClass);

		log.info("Clicking on Search");
		driver.findElement(By.xpath(prop.getProperty("searchButtonXpath"))).click();

	
		try {
			Alert a = driver.switchTo().alert();
			Thread.sleep(2000);
			a.accept();
			log.info("Handling Alert");
			System.out.println("Alert Handled");

			driver.switchTo().defaultContent();
		} catch (Exception e) {
			return new FlightSearchPage(driver);
		}
		return null;
	}

	/*
	 * pickDate function is used to convert the date from excel sheet into a format
	 * which is directly used in element finding and clicking the element in the
	 * calendar
	 * 
	 * It also contains clicking on next arrow until the desired date appears on
	 * screen
	 */
	public void pickDate(String date) {
		String day = date.split("-")[0];
		String month1 = date.split("-")[1];
		String month = null;
		switch (month1) {
		case "01":
			month = "Jan";
			break;
		case "02":
			month = "Feb";
			break;
		case "03":
			month = "Mar";
			break;
		case "04":
			month = "Apr";
			break;
		case "05":
			month = "May";
			break;
		case "06":
			month = "Jun";
			break;
		case "07":
			month = "Jul";
			break;
		case "08":
			month = "Aug";
			break;
		case "09":
			month = "Sep";
			break;
		case "10":
			month = "Oct";
			break;
		case "11":
			month = "Nov";
			break;
		case "12":
			month = "Dec";
			break;
		default:
			System.out.println("ENTER VALID DATE");
		}
		String year = date.split("-")[2];
		Boolean flag = true;
		WebElement my = null;
		String xp = null;
		driver.findElement(By.id("ddate")).click();
		while (flag == true) {
			try {
				my = driver.findElement(By.xpath(prop.getProperty("dateContainsPart1") + month + " " + year
						+ prop.getProperty("dateContainsPart2")));
				flag = my.isDisplayed();
				String z = "_" + day + "/" + month1 + "/" + year;
				xp = "//*[contains(@id," + "'" + z + "'" + ")]";
				driver.findElement(By.xpath(xp)).click();

				flag = false;
			} catch (NoSuchElementException e) {
				driver.findElement(By.xpath(prop.getProperty("dateArrowXpath"))).click();
			} catch (Exception e) {
				continue;
			}

		}

	}

	/* this function confirms the presence of alert */

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} // try
		catch (Exception e) {
			return false;
		} // catch
	}

	/*
	 * pickTraveller function is the function used to picl number of adults, infants
	 * and children as we got from the excel sheet
	 * 
	 * It clicks on "+" button as per data got by excel sheet
	 */

	public void pickTraveller(int adult, int infant, int children) throws InterruptedException {
		driver.findElement(By.cssSelector(prop.getProperty("travellerDropDownCSS"))).click();
		Thread.sleep(2000);
		int k = 0;
		int l = 0;
		int m = 0;
		while (k < adult - 1) {
			driver.findElement(By.cssSelector(prop.getProperty("plusSign1"))).click();
			k++;
		}
		while (l < children) {
			driver.findElement(By.cssSelector(prop.getProperty("plusSign2"))).click();
			l++;
		}
		while (m < infant) {
			driver.findElement(By.cssSelector(prop.getProperty("plusSign3"))).click();
			m++;
		}
		driver.findElement(By.xpath(prop.getProperty("done1Xpath"))).click();
	}

	/*
	 * this function is used to pick the class in which traveller is going to
	 * traveller this also takes data from excel
	 */

	public void pickClass(String travelClass) {
		WebElement tclass = driver.findElement(By.className(prop.getProperty("travelClassDropDownClass")));
		tclass.click();
		if (travelClass.equals("Economy"))
			driver.findElement(By.xpath(prop.getProperty("economy"))).click();
		if (travelClass.equals("Prem.Economy"))
			driver.findElement(By.xpath(prop.getProperty("premEconomy"))).click();
		if (travelClass.equals("Business"))
			driver.findElement(By.xpath(prop.getProperty("business"))).click();

		driver.findElement(By.xpath(prop.getProperty("done2Xpath"))).click();

	}

	/* this function is used to click on a link by link text */

	public void linkCheck(String link) {
		driver.findElement(By.linkText(link)).click();
	}

	/* this function is used to click on an element by attribute title */

	public void linkCheckByCssTitle(String link1) {
		driver.findElement(By.cssSelector("a[title='" + link1 + "']")).click();
	}

	/* this function is used to click on an element by xpath */

	public void xpathClickCheck(String xp1) {
		driver.findElement(By.xpath(xp1)).click();
	}

	/* this function is used to click on a check-box by xpath */

	public void checkBoxCheck(String xp) {
		WebElement ch = driver.findElement(By.xpath(xp));
		ch.click();
		assertTrue(driver.findElement(By.xpath(xp)).isSelected());
	}

	/* this function is used to scroll down to height */

	public void scroll(int a, String b) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(" + a + "," + b + ");");
	}
}
