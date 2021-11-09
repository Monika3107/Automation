package com.PageObjects;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {

	@FindBy(xpath = "//a[text()='Login']")
	private static WebElement LoginTab;
	
	@FindBy(css = "#loginUserName")
	private static WebElement userName;
	
	@FindBy(css = "#loginPassword")
	private static WebElement password;
	
	@FindBy(xpath = "//button[text()='Login']")
	private static WebElement loginButton;
	
	@FindBy(xpath = "//span[@class='user']")
	private static WebElement user;
	//Initializing the page factory
	
	private WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}
	
	public void login(String username, String pwd) {
		userName.sendKeys(username);
		password.sendKeys(pwd);
	}
	
	public HomePage clickLoginButton() {
		loginButton.click();
		return new HomePage();
	}

	public void clickLoginTab() {
		LoginTab.click();
	}
	
	public String getUserDetails() {
		return user.getText();
	}
}
