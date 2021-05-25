package StepDefinitions;

import java.util.List;
import java.util.Map;

import org.junit.Assert;

import com.PageObjects.ContactPage;
import com.qa.factory.DriverFactory;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ContactPageStepDefinition {
	
	ContactPage contactPage = new ContactPage(DriverFactory.getDriver());
	
	@Given("User is already on the Home page")
	public void user_is_already_on_the_home_page() throws InterruptedException {

		DriverFactory.getDriver()
				.get("https://jupiter.cloud.planittesting.com/#/home");
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
	@Then("Validate errors")
	public void validate_errors() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		Assert.assertTrue(contactPage.validateErrors());
		Thread.sleep(2000);
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
	    
		Thread.sleep(2000);
	}
	
	@Then("Check Validation errors are gone")
	public void check_validation_errors_are_gone() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		Assert.assertTrue(contactPage.checkValidationErrorsGone());
		Thread.sleep(2000);
	}

	@Then("Validate successful submission message")
	public void validate_successful_submission_message() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		Assert.assertTrue(contactPage.checkForSuccesfulSubmissionMsg());
		Thread.sleep(2000);
	}

	
	@Then("Validate errors for invalid data")
	public void validate_errors_for_invalid_data() {
		Assert.assertTrue(contactPage.checkValidationErrorsforInvalidDatainMandatoryFields());
	}
}
