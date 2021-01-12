package com.easemytrip.pom;

import java.util.Scanner;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.easemytrip.utils.BasePage;

public class LoginTrial extends BasePage{

	public LoginTrial(WebDriver driver) {
		super(driver);
	}

	public void doLogin(String username, String password,ExtentTest log)
	{//This will do the login for the EaseMyTrip site
		log.info("opening dropdown");
		WebElement myaccount=driver.findElement(By.xpath(prop.getProperty("xpathmyaccount")));	
		Actions ac=new Actions(driver);
		ac.moveToElement(myaccount).perform();
		log.info("clicking Sign-in");
		driver.findElement(By.xpath(prop.getProperty("xpathsignin"))).click();
		log.info("clearing text fields");
		driver.findElement(By.xpath(prop.getProperty("xpathusername"))).clear();
		driver.findElement(By.xpath(prop.getProperty("xpathpassword"))).clear();
		log.info("Entering Username");
		driver.findElement(By.xpath(prop.getProperty("xpathusername"))).sendKeys(username);
		log.info("Entering Password");
		driver.findElement(By.xpath(prop.getProperty("xpathpassword"))).sendKeys(password);
		log.info("Loging in");
		driver.findElement(By.xpath(prop.getProperty("xpathloginbtn"))).click();	

	}

	public void forgotPassword(String email,String password,ExtentTest log)
	{//This Method will Reset the password
		log.info("opening dropdown");
		WebElement myaccount1=driver.findElement(By.xpath(prop.getProperty("xpathmyaccount")));	
		Actions ac=new Actions(driver);
		ac.moveToElement(myaccount1).perform();
		log.info("Clicking Sign-in");
		driver.findElement(By.xpath(prop.getProperty("xpathsignin"))).click();
		log.info("Clicking Forgot Password");
		driver.findElement(By.xpath(prop.getProperty("xpathforgotpassword"))).click();
		log.info("Entering Email");
		driver.findElement(By.xpath(prop.getProperty("xpathfemail"))).sendKeys(email);
		log.info("clicking Log-in");
		driver.findElement(By.xpath(prop.getProperty("xpathfloginbtn"))).click();
		System.out.println(email);
		try {
			log.info("Checking Email");	
			//Checking if the mail is correct.
			new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("xpathnpage"))));
			Scanner sc= new Scanner(System.in);
			log.info("Entering OTP");
			System.out.println("Enter the OTP you received");
			String otp=sc.next();
			driver.findElement(By.xpath(prop.getProperty("xpathfotp"))).sendKeys(otp);
			log.info("Entering Password");
			driver.findElement(By.xpath(prop.getProperty("xpathfpassword"))).sendKeys(password);
			driver.findElement(By.xpath(prop.getProperty("xpathfcpassword"))).sendKeys(password);
			log.info("Clicking Submit");
			driver.findElement(By.xpath(prop.getProperty("xpathfsubmit"))).click();	
		}catch(Exception E) {
			log.info("displaying Error");
			String wrong=driver.findElement(By.xpath(prop.getProperty("xpathwrongun"))).getText();
			System.out.println(wrong);
			Assert.assertEquals(wrong,"* Enter a valid Email or Phone Number.");
			driver.findElement(By.xpath(prop.getProperty("xpathfclosebtn"))).click();
		}

	}

	public void loginTestOTP(String email)
	{	//this method will login using the OTP
		WebElement myaccount=driver.findElement(By.xpath(prop.getProperty("xpathmyaccount")));	
		Actions ac=new Actions(driver);
		ac.moveToElement(myaccount).perform();
		driver.findElement(By.xpath(prop.getProperty("xpathsignin"))).click();
		driver.findElement(By.xpath(prop.getProperty("xpathotpbox"))).click();
		driver.findElement(By.xpath(prop.getProperty("xpathotpnumfield"))).clear();;
		driver.findElement(By.xpath(prop.getProperty("xpathotpnumfield"))).sendKeys(email);
		driver.findElement(By.xpath(prop.getProperty("xpathotplogin"))).click();

		try {//checking the number or email id
			new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("xpathotpfield"))));
			Scanner sc= new Scanner(System.in);
			System.out.println("Enter the OTP you received");
			String otp=sc.next();
			driver.findElement(By.xpath(prop.getProperty("xpathotpfield"))).sendKeys(otp);
			driver.findElement(By.xpath(prop.getProperty("xpathotpsubmit"))).click();
		}catch(Exception E) {
			System.out.println("Invalid username");
			driver.findElement(By.xpath(prop.getProperty("xpathotpclose"))).click();
		}
	}

	public void logout()
	{	//this method will logout 
		WebElement myaccount=driver.findElement(By.xpath(prop.getProperty("xpathwelcomeuser")));	
		Actions ac=new Actions(driver);
		ac.moveToElement(myaccount).perform();
		driver.findElement(By.xpath(prop.getProperty("xpathlogoutbtn"))).click();
		Alert al=driver.switchTo().alert();  // Focusing on Alert		
		System.out.println(al.getText());  // getting text from Alert
		System.out.println("logging out");
		al.accept();
	}
}
