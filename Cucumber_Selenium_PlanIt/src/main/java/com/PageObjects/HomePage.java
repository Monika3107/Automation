package com.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	//Web elements

	@FindBy(xpath = "//a[text()=\"Home\"]")
	WebElement HomePageTab;
	
	//Initializing the page factory
	
	private WebDriver driver;
		
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		
	//Actions
	public void goToHomePage() {
		HomePageTab.click();	
	}
}
