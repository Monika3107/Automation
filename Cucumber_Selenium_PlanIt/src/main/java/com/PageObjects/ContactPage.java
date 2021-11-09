package com.PageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage {
	
	//Web elements
	@FindBy(xpath = "//a[text()='Contact']")
	private static WebElement ContactPageTab;
	
	@FindBy(xpath = "//a[text()='Submit']") 
	private static WebElement SubmitButton;
	
	@FindBy(xpath = "//div[@id=\"header-message\"]/div")
	private static WebElement HeaderMsg;
	
	@FindBy(id = "forename-err")
	private static WebElement ForenameErrorMsg;
	
	@FindBy(id = "email-err")
	private static WebElement EmailErrorMsg;
	
	@FindBy(id = "message-err")
	private static WebElement ErrorMsg;
	
	@FindBy(id = "forename")
	private static WebElement Forename;
	
	@FindBy(id = "email")
	private static WebElement Email;
	
	@FindBy(id = "message")
	private static WebElement Message;
	
	@FindBy(xpath = "//div[@class=\"alert alert-success\"]")
	private static WebElement SuccessMsg;

	@FindBy(id = "telephone-err")
	private static WebElement TelephoneErrorMsg;
	
	//Initializing the page factory
	
	private WebDriver driver;
	
	public ContactPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	//Actions
	
	public void goToContactPage() {
		ContactPageTab.click();
	}
	
	public ContactPage clickOnSubmitButton() {
		SubmitButton.click();
		return new ContactPage(driver);
	}
	
	public String getValidationMsgOnContactPage(String field) {
		
		String validationMsg= null;
		
	    switch (field) {
	    case "MainError":
			validationMsg= HeaderMsg.getText();
			break;
			
		case "Forename":
			validationMsg= ForenameErrorMsg.getText();
			break;

		case "Email":
			validationMsg= EmailErrorMsg.getText();
			break;
		
		case "Message":
			validationMsg= ErrorMsg.getText();
			break;
		default:
			break;
		}
	
		return validationMsg;
	}

	public void populateField(String field, String value) {
		switch (field) {
			case "Forename":
				Forename.sendKeys(value);
				break;
				
			case "Email":
				Email.sendKeys(value);
				break;
			
			case "Message":
				Message.sendKeys(value);
				break;
		}
	}
	
	public String getValidationMsgWhenErrorsGone() {
		String headerMsgValue = HeaderMsg.getText();
		return headerMsgValue;
	}
	
	public String checkForSuccesfulSubmissionMsg() {
		String successMsg = SuccessMsg.getText();	
		return successMsg;
	}

}
