package com.easemytrip.pom;

import static org.testng.Assert.assertTrue;

import javax.swing.JOptionPane;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.easemytrip.utils.BasePage;

public class MultiCity extends BasePage {

	public MultiCity(WebDriver driver) {
		super(driver);
	}

	/* toSearch Function is used to clear , fill and search flight on home page */

	public void doSearch1(String from1, String to1,String from2, String to2,String from3, String to3,String from4, String to4,String from5, String to5,String from6, String to6, String ddate1,String ddate2,String ddate3,String ddate4,String ddate5,String ddate6, int adult, int infant, int children, String travelClass,
			ExtentTest log) throws InterruptedException {

		Actions a=new Actions(driver);
		driver.findElement(By.id("FromSector-mul1_show")).sendKeys(from1);
		driver.findElement(By.xpath("//span[contains(text(),'"+from1+"')]")).click();

		driver.findElement(By.id("ToSector-mul1_show")).sendKeys(to1);
		Thread.sleep(2000);
		Action pressTab = a.sendKeys(Keys.TAB).build();
		pressTab.perform();

		driver.findElement(By.xpath("//a[contains(text(),'13')]")).click();

		driver.findElement(By.id("ToSector-mul2_show")).sendKeys(to2);
		Thread.sleep(2000);
		pressTab.perform();

		driver.findElement(By.xpath("//a[contains(text(),'14')]")).click();
		Thread.sleep(2000);


		driver.findElement(By.id("ToSector-mul3_show")).sendKeys(to3);
		Thread.sleep(2000);
		pressTab.perform();

		driver.findElement(By.xpath("//a[contains(text(),'15')]")).click();
		Thread.sleep(2000);
/*
		driver.findElement(By.id("ToSector-mul4_show")).sendKeys(to4);
		Thread.sleep(2000);
		pressTab.perform();

		driver.findElement(By.xpath("//a[contains(text(),'16')]")).click();
		Thread.sleep(2000);

		driver.findElement(By.id("ToSector-mul5_show")).sendKeys(to5);
		Thread.sleep(2000);
		pressTab.perform();

		driver.findElement(By.xpath("//a[contains(text(),'16')]")).click();
		Thread.sleep(2000);

		driver.findElement(By.id("ToSector-mul6_show")).sendKeys(to6);
		Thread.sleep(2000);
		pressTab.perform();

		driver.findElement(By.xpath("//a[contains(text(),'16')]")).click();
		Thread.sleep(2000);

		driver.findElement(By.id("crs6")).click();

		driver.findElement(By.xpath(prop.getProperty("addCityXpath"))).click();

		driver.findElement(By.id("ToSector-mul6_show")).clear();
		driver.findElement(By.id("ToSector-mul6_show")).sendKeys(to6);
		Thread.sleep(2000);
		pressTab.perform();

		driver.findElement(By.xpath("//a[contains(text(),'17')]")).click();
		Thread.sleep(2000);
*/
		driver.findElement(By.xpath("//*[@id=\"sector-sec1\"]/div[4]/div/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"myDropdown_n2\"]/div/div[2]/div[2]/div/div[3]/input")).click();
		driver.findElement(By.xpath("//*[@id=\"myDropdown_n2\"]/div/div[3]/div[2]/div/div[3]/input")).click();
		driver.findElement(By.xpath("//*[@id=\"myDropdown_n2\"]/div/a")).click();
		driver.findElement(By.xpath("//div[@id='sector-sec1']//input[@class='src_btn']")).click();

	}

	public void linkCheck(String link) {
		driver.findElement(By.linkText(link)).click();
	}

	public void linkCheckByCssTitle(String link1) {
		driver.findElement(By.cssSelector("a[title='" + link1 + "']")).click();
	}

	public void xpathClickCheck(String xp1) {
		driver.findElement(By.xpath(xp1)).click();
	}

	public void checkBoxCheck(String xp) {
		WebElement ch = driver.findElement(By.xpath(xp));
		ch.click();
		assertTrue(driver.findElement(By.xpath(xp)).isSelected());
	}

	public void scroll(int a, String b) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(" + a + "," + b + ");");
	}
}
