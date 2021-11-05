package StepDefinitions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import com.PageObjects.CartPage;
import com.qa.factory.DriverFactory;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CartPageStepDefinition {

	CartPage cartPage = new CartPage(DriverFactory.getDriver());

	@When("User clicks on Cart menu")
	public void user_clicks_on_cart_menu() {
		cartPage.goToCart();
	}

	@Then("User verifies items are in Cart")
	public void user_verifies_items_are_in_cart(io.cucumber.datatable.DataTable table) {
		List<Map<String, String>> rows = table.asMaps(String.class, String.class);
		
		for (Map<String, String> columns : rows) {
			Assert.assertEquals(columns.get("Number"), cartPage.verifyCartitems(columns.get("Item")));
		}
	}
}
