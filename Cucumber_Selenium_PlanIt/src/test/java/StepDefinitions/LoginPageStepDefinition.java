package StepDefinitions;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import com.PageObjects.LoginPage;
import com.qa.factory.DriverFactory;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

public class LoginPageStepDefinition {

	LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
	
	@When("user clicks on login tab")
	public void user_clicks_on_login_tab() {
		loginPage.clickLoginTab();
	}

	@When("user enters below credentials")
	public void user_enters_below_credentials(DataTable dataTable) throws InterruptedException {
		Map<String, String> map = dataTable.asMap(String.class, String.class);
		loginPage.login(map.get("username"), map.get("password"));

		Thread.sleep(15000);
	}
	
	@When("user clicks on login button")
	public void user_clicks_on_login_button() throws InterruptedException {
		loginPage.clickLoginButton();
		Thread.sleep(15000);
	}
	
	@Then("user successfully logs in as {string}")
	public void user_successfully_logs_in(String user) {
		assertEquals(user, loginPage.getUserDetails());
	}

}
