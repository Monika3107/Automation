package com.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage {
	
	//Web elements
	@FindBy(xpath = "//a[text()=\"Contact\"]")
	WebElement ContactPageTab;
	
	@FindBy(xpath = "//a[text()=\"Submit\"]") 
	WebElement SubmitButton;
	
	@FindBy(xpath = "//div[@id=\"header-message\"]/div")
	WebElement HeaderMsg;
	
	@FindBy(id = "forename-err")
	WebElement ForenameErrorMsg;
	
	@FindBy(id = "email-err")
	WebElement EmailErrorMsg;
	
	@FindBy(id = "message-err")
	WebElement ErrorMsg;
	
	@FindBy(id = "forename")
	WebElement Forename;
	
	@FindBy(id = "email")
	WebElement Email;
	
	@FindBy(id = "message")
	WebElement Message;
	
	@FindBy(xpath = "//div[@ui-if=\"contactValidSubmit\"]/div")
	WebElement SuccessMsg;

	@FindBy(id = "telephone-err")
	WebElement TelephoneErrorMsg;
	
	//Initializing the page factory
	
	private WebDriver driver;
	
	public ContactPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	
	public void goToContactPage() {
		ContactPageTab.click();
	}
	
	public void clickOnSubmitButton() {
		SubmitButton.click();
	}
	
	public String getValidationMsgOnContactPage(String field) {
		
		String validationMsg= null;
		
	    switch (field) {
	    case "MainError":
			validationMsg= HeaderMsg.getText();
			System.out.println("Erroor -----------------"+ validationMsg);
			break;
			
		case "Forename":
			validationMsg= ForenameErrorMsg.getText();
			System.out.println("Erroor -----------------"+ validationMsg);
			break;

		case "Email":
			validationMsg= EmailErrorMsg.getText();
			System.out.println("Erroor -----------------"+ validationMsg);
			break;
		
		case "Message":
			validationMsg= ErrorMsg.getText();
			System.out.println("Erroor -----------------"+ validationMsg);
			break;
		default:
			break;
		}
	
		return validationMsg;
	}
	/*
	public void populateManadatoryFields(String forename, String email, String messsage) {
		
		Forename.sendKeys(forename);
		Email.sendKeys(email);
		Message.sendKeys(messsage);
	
	}*/
	
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
		System.out.println("headerMsgValue --");
		String headerMsgValue = HeaderMsg.getText();
		System.out.println("headerMsgValue -----------------"+ headerMsgValue);
	    return headerMsgValue;
	}
	
	public String checkForSuccesfulSubmissionMsg() {
		String successMsg = SuccessMsg.getText();	
		return successMsg;
	}

	public String getValidationErrorsforInvalidDatainMandatoryFields(String field) {
		 String invalidEmailMsg = null;
		 switch (field) {

			case "Email":
				invalidEmailMsg = EmailErrorMsg.getText();
				System.out.println("Erroor -----------------"+ invalidEmailMsg);
				break;
			
			default:
				break;
			}		
		return invalidEmailMsg;
	}
}
