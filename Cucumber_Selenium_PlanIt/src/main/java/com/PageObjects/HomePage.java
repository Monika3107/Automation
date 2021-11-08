package com.PageObjects;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.factory.DriverFactory;
import com.qa.util.ConfigReader;

public class HomePage {
	
	//Web elements

	@FindBy(xpath = "//a[text()=\"Home\"]")
	private static WebElement HomePageTab;
	
	//Initializing the page factory
	
	private WebDriver driver;
	ConfigReader configReader = new ConfigReader();
	Properties prop=configReader.init_prop();
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		DriverFactory.getDriver()
		.get(prop.getProperty("url"));
	}
		
	//Actions
	public void goToHomePage() {
		HomePageTab.click();	
	}
}
