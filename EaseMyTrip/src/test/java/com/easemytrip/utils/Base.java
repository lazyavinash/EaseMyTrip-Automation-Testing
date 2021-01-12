package com.easemytrip.utils;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

/* This is the base class for all runners */

@Listeners({ com.easemytrip.utils.TestReport.class }) // Listener is added so that extent test report may trigger when
// tests are performed*/
public class Base {

	public static WebDriver driver;
	public Properties prop;
	protected static ExtentTest testLog;
	protected static ExtentReports extentReporter;
	

	@BeforeTest // PreConditions
	public void beforeTest() {
		prop = PropertyReader.getInstance();
/*		extentReporter = new ExtentReports();
		extentReporter.attachReporter(new ExtentHtmlReporter("EasyMyTrip.html"));
*/
		driver = HelperFunctions.setBrowser(prop.getProperty("browser"));
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(prop.getProperty("implicit.wait")),
				TimeUnit.SECONDS);
	}

	
	@BeforeSuite
	public void beforeSuiteSetup() {
		extentReporter = new ExtentReports();
		extentReporter.attachReporter(new ExtentHtmlReporter(".\\src\\test\\resources\\output\\Report.html"));
	}

	@AfterSuite(alwaysRun = true)
	public void afterSuite() {
		extentReporter.flush();
	}




@AfterTest // PreConditions
public void afterTest() {
	driver.quit();
}

}
