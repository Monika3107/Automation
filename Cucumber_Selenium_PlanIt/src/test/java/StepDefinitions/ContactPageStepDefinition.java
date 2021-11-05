package StepDefinitions;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.Assert;

import com.PageObjects.ContactPage;
import com.qa.factory.DriverFactory;

import io.cucumber.datatable.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ContactPageStepDefinition {
	
	
	
	ContactPage contactPage = new ContactPage(DriverFactory.getDriver());
	
	@Given("User navigates to Contact page from Home page")
	public void user_navigates_to_contact_page_from_home_page() throws InterruptedException {
	    // 
	    contactPage.goToContactPage();
	}
	
	
	@When("User clicks on submit button")
	public void user_clicks_on_submit_button() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		contactPage.clickOnSubmitButton();
	}
	

	@When("User populates field {string} as {string}")
	public void user_populates_field_as(String field, String value) {
		contactPage.populateField(field, value);
	}
	
	@When("user populates contact fields")
	public void user_populates_contact_fields(DataTable dataTable) throws InterruptedException {
	   
		/* Iteration as list
		 * List<List<String>> rows = dataTable.asLists(String.class);
		for (List<String> columns : rows) {
			contactPage.populateField(columns.get(0), columns.get(1));
		}*/
		
		Map<String, String> map = dataTable.asMap(String.class, String.class);
		for (Map.Entry<String, String> entry : map.entrySet()) {
			contactPage.populateField(entry.getKey(), entry.getValue());
		}
	}
	
	@Then("the following errors appear")
	public void the_following_errors_appear(DataTable dataTable) {
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
	    
		for (Map<String, String> columns : rows) {
	    	String field = columns.get("Field");
	    	String errorMsg = columns.get("Error Message");
	    	Assert.assertEquals(errorMsg,contactPage.getValidationMsgOnContactPage(field));
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

	
	/*@Then("Validate errors for invalid data")
	public void validate_errors_for_invalid_data() {
		Assert.assertEquals(contactPage.getValidationErrorsforInvalidDatainMandatoryFields("Email"),"Please enter a valid email");
	}*/
}
