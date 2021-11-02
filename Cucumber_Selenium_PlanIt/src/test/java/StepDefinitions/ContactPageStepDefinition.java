package StepDefinitions;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.Assert;

import com.PageObjects.ContactPage;
import com.qa.factory.DriverFactory;
import com.qa.util.ConfigReader;

import AppHooks.AppHooks;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ContactPageStepDefinition {
	
	
	
	ContactPage contactPage = new ContactPage(DriverFactory.getDriver());
	
	@Given("User is already on the Home page")
	public void user_is_already_on_the_home_page() throws InterruptedException {
		
		ConfigReader configReader = new ConfigReader();
		Properties prop=configReader.init_prop();
		DriverFactory.getDriver()
				.get(prop.getProperty("url"));
		contactPage.goToHomePage();
		Thread.sleep(2000);
	}
	
	
	@Given("User navigates to Contact page from Home page")
	public void user_navigates_to_contact_page_from_home_page() throws InterruptedException {
	    // 
	    contactPage.goToContactPage();
	    Thread.sleep(2000);
	}
	
	
	@When("User clicks on submit button")
	public void user_clicks_on_submit_button() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		contactPage.clickOnSubmitButton();
		Thread.sleep(10000);
	}
	
	@Then("Validate the errors for {string} {string} {string} {string}")
	public void validate_the_errors_for(String MainError, String Forename, String Email, String Message) {
	    // Write code here that turns the phrase above into concrete actions
		Assert.assertEquals(contactPage.getValidationMsgOnContactPage(MainError),"We welcome your feedback - but we won't get it unless you complete the form correctly.");
		Assert.assertEquals(contactPage.getValidationMsgOnContactPage(Forename),"Forename is required");
		Assert.assertEquals(contactPage.getValidationMsgOnContactPage(Email),"Email is required");
		Assert.assertEquals(contactPage.getValidationMsgOnContactPage(Message),"Message is required");
	}

	
	@When("User populates mandatory fields")
	public void user_populates_mandatory_fields(DataTable table) throws InterruptedException {
	    
		// iterating through the values in the feature file
		List<Map<String, String>> rows = table.asMaps(String.class, String.class);
	    
		for (Map<String, String> columns : rows) {
	    	String forename = columns.get("Forename");
	    	String email = columns.get("Email");
	    	String message = columns.get("Message");
	    	contactPage.populateManadatoryFields(forename, email, message);
	    }
	}
	
	@Then("Check Validation errors are gone")
	public void check_validation_errors_are_gone() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		Assert.assertEquals(contactPage.getValidationMsgWhenErrorsGone(),"We welcome your feedback - tell it how it is.");
	}

	@Then("Validate successful submission message")
	public void validate_successful_submission_message() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		Assert.assertTrue(contactPage.checkForSuccesfulSubmissionMsg().contains("we appreciate your feedback."));
	}

	
	@Then("Validate errors for invalid data")
	public void validate_errors_for_invalid_data() {
		Assert.assertEquals(contactPage.getValidationErrorsforInvalidDatainMandatoryFields("Email"),"Please enter a valid email");
	}
}
