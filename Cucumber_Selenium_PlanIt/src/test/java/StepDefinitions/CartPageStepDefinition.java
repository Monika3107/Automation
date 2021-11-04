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
	public void user_clicks_on_cart_menu() throws InterruptedException {
		cartPage.goToCart();
	}

	@Then("User verifies items are in Cart")
	public void user_verifies_items_are_in_cart(io.cucumber.datatable.DataTable table) throws InterruptedException {
		List<Map<String, String>> rows = table.asMaps(String.class, String.class);
		List<String> itemsinCart = new ArrayList<String>();
		List<String>  quantityOfItemsinCart = new ArrayList<String>() ;

		for (Map<String, String> columns : rows) {
			String item = columns.get("Item");
			String number = columns.get("Number");
			itemsinCart.add(item);
			quantityOfItemsinCart.add(number);
		}	  
		Assert.assertTrue(cartPage.verifyItemsAddedInCart(itemsinCart, quantityOfItemsinCart));
	}
}
