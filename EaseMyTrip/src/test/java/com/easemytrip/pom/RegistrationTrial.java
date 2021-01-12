package com.easemytrip.pom;

import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.easemytrip.utils.BasePage;

public class RegistrationTrial extends BasePage {

	public RegistrationTrial(WebDriver driver) {
		super(driver);
	}

	public void doRegister(String email, String password,ExtentTest log)
	{	//This method will do the registration in EaseMyTrip site
		log.info("opening dropdown");
		WebElement myaccount=driver.findElement(By.xpath(prop.getProperty("xpathmyaccount")));	
		Actions ac=new Actions(driver);
		ac.moveToElement(myaccount).perform();
		log.info("Clicking Register");
		driver.findElement(By.xpath(prop.getProperty("xpathregister"))).click();
		log.info("Entering Email");
		driver.findElement(By.xpath(prop.getProperty("xpathremailid"))).sendKeys(email);
		log.info("clicking Register");
		driver.findElement(By.xpath(prop.getProperty("xpathregisterbtn"))).click();
		try {
			log.info("Checking Email");
			//checking whether the given E-mail is right or Wrong
			new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("xpathregisternumber"))));	
			Scanner sc= new Scanner(System.in);
			System.out.println("Enter the OTP you received");
			String otp=sc.next();
			log.info("Entering OTP");
			driver.findElement(By.xpath(prop.getProperty("xpathrotpfield"))).sendKeys(otp);
			log.info("Entering Password");
			driver.findElement(By.xpath(prop.getProperty("xpathregisterpass"))).sendKeys(password);
			driver.findElement(By.xpath(prop.getProperty("xpathregisterconfirmpass"))).sendKeys(password);
			log.info("Clicking Register");
			driver.findElement(By.xpath(prop.getProperty("xpathrsubmitbtn"))).click();	
		}catch (Exception E)
		{
			log.info("Error logged");
			System.out.println("Invalid Email-Id or Phone Number");
		}
	}
}
