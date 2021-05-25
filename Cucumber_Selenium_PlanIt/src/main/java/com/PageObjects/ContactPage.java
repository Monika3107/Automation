package com.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage {
	
	//Web elements

	@FindBy(xpath = "//*[@id=\"nav-home\"]/a")
	WebElement HomePageTab;
	
	@FindBy(xpath = "//*[@id=\"nav-contact\"]/a")
	WebElement ContactPageTab;
	
	@FindBy(xpath = "//a[@class=\"btn-contact btn btn-primary\"]") 
	WebElement SubmitButton;
	
	@FindBy(xpath = "//*[@id=\"header-message\"]/div")
	WebElement ErrorTitle;
	
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
	
	@FindBy(xpath = "//div[@class=\"alert alert-info ng-scope\"]")
	WebElement Title;

	@FindBy(xpath = "//div[@class=\"alert alert-success\"]")
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
	public void goToHomePage() {
		HomePageTab.click();
	}
	
	public void goToContactPage() {
		ContactPageTab.click();
	}
	
	public void clickOnSubmitButton() {
		SubmitButton.click();
	}
	
	public boolean validateErrors() {
		
		boolean isErrorValid = false;
		boolean ismainMsgValid = false;
		boolean isForenameEmpty = false;
		boolean isEmailEmpty = false;
		boolean isMessageBodyEmpty = false;
		
		
		String errorTitle = ErrorTitle.getText();
		String forenameErrorMsg = ForenameErrorMsg.getText();
	    String emailErrorMsg = EmailErrorMsg.getText();
	    String errorMsg = ErrorMsg.getText();
		
	    ismainMsgValid = errorTitle.contentEquals("We welcome your feedback - but we won't get it unless you complete the form correctly.");
	    isForenameEmpty = forenameErrorMsg.contentEquals("Forename is required");
	    isEmailEmpty = emailErrorMsg.contentEquals("Email is required");
		isMessageBodyEmpty = errorMsg.contentEquals("Message is required");
	
		if(ismainMsgValid && isForenameEmpty && isEmailEmpty && isMessageBodyEmpty)
			isErrorValid = true;
		
		return isErrorValid;
	}
	
	public void populateManadatoryFields(String forename, String email, String messsage) {
		
		Forename.sendKeys(forename);
		Email.sendKeys(email);
		Message.sendKeys(messsage);
	
	}
	
	public boolean checkValidationErrorsGone() {
		
		boolean isValidationMsgDisplayed = false;
		String title = Title.getText();
		isValidationMsgDisplayed = title.contentEquals("We welcome your feedback - tell it how it is.");
	    return isValidationMsgDisplayed;
	}
	
	public boolean checkForSuccesfulSubmissionMsg() {
		boolean isSuccessfulMsgDisplayed = false;
		String successMsg = SuccessMsg.getText();	
		isSuccessfulMsgDisplayed = successMsg.contains("we appreciate your feedback.");
		return isSuccessfulMsgDisplayed;
	}

	public boolean checkValidationErrorsforInvalidDatainMandatoryFields() {
		// TODO Auto-generated method stub
		boolean isDataInvalid = false;
		boolean isValidationErrorApporpriate = false;
		boolean isEmailInvalid = false;
		
		String inValidDataTitle = ErrorTitle.getText();
	    String invalidEmailMsg = EmailErrorMsg.getText();
	   
	    isValidationErrorApporpriate = inValidDataTitle.contentEquals("We welcome your feedback - but we won't get it unless you complete the form correctly.");
	    isEmailInvalid = invalidEmailMsg.contentEquals("Please enter a valid email");
		
		if(isValidationErrorApporpriate && isEmailInvalid)
			isDataInvalid = true;
		
		return isDataInvalid;
	}
}
