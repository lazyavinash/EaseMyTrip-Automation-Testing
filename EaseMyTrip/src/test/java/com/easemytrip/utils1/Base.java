package com.easemytrip.utils1;

import org.testng.annotations.Test;


import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;

public class Base {
	public WebDriver driver;
  public Properties prop;
  @BeforeTest
  public void beforeTest() throws FileNotFoundException, IOException {
	   prop = new Properties();
		//prop.load(new FileInputStream(".\\settings.property"));
		prop.load(new FileInputStream(".\\src\\test\\resources\\files\\settings.property"));
	  
	  driver = HelperFunctions.setBrowser(prop.getProperty("browser"));
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(Integer.parseInt(prop.getProperty("implicit.wait")), TimeUnit.SECONDS);
		
  }

  @AfterTest
  public void afterTest() {
	  //driver.quit();
  }

}
