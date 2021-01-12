package com.easemytrip.utils;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

/*This is the base class for all POM Pages like homePage,LoginPage,Traveller Details Page and all...*/

public class BasePage {
	public WebDriver driver;
	public Properties prop;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		prop = PropertyReader.getInstance();
	}
}
