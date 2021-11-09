package StepDefinitions;

import com.PageObjects.HomePage;
import com.qa.factory.DriverFactory;

import io.cucumber.java.en.Given;

public class HomePageStepDefinition {
	
	HomePage homePage = new HomePage(DriverFactory.getDriver());
	
	@Given("user is already on the Home page")
	public void user_is_already_on_the_home_page() {
		homePage.goToHomePage();
	}
}
