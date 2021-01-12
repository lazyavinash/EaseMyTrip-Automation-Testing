package com.easemytrip.utils1;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;

public class HelperFunctions {
	 static WebDriver driver = null;
 public static  WebDriver setBrowser(String browserName) {
	 browserName = browserName.toLowerCase();
	
	 switch(browserName) {
	 case "chrome" :
	 {
		 System.setProperty("webdriver.chrome.driver", ".\\src\\test\\resources\\drivers\\chromedriver.exe");
	     ChromeOptions cp = new ChromeOptions(); 
	     cp.addArguments("--disable-notifications");
		 driver = new ChromeDriver(cp);
	     break;
	 }
	 case "firefox": 
	 {
		 System.setProperty("webdriver.chrome.driver", ".\\src\\test\\resources\\drivers\\chromedriver.exe");
	      driver = new ChromeDriver();
	      break;
	 }
	 default:
		 System.out.println("invalid browser");
	 }
	 return driver;
 }
 
 public static void getSnap(String fileNameWithLocation) {
	 TakesScreenshot tc = (TakesScreenshot)driver;
	 File scFile = tc.getScreenshotAs(OutputType.FILE);
	 try {
		 FileHandler.copy(scFile, new File(fileNameWithLocation));
		 
	 }
	 catch (IOException e) {
		 System.out.println("Error with screenshot");
	 }
 }
}
