package StepDefinitions;

import static org.junit.Assert.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.PageObjects.CartPage;
import com.qa.factory.DriverFactory;

import context.World;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;	

public class CartPageStepDefinition {

	CartPage cartPage = new CartPage(DriverFactory.getDriver());
	private World world;
	public CartPageStepDefinition(World world) {
        this.world = world;
    }

	@When("user clicks on Cart Tab")
	public void user_clicks_on_cart_tab() {
		cartPage.goToCart();
	}

	@Then("User verifies items are in Cart")
	public void user_verifies_items_are_in_cart(DataTable table) {
		List<Map<String, String>> rows = table.asMaps(String.class, String.class);
		
		for (Map<String, String> columns : rows) {
			assertEquals(columns.get("Quantity"), cartPage.getItemQuantity(columns.get("Item")));
		}
	}
	
	@Then("user removes item {string} from cart")
	public void user_removes_item_from_cart(String item) {
		cartPage.removeItemFromCart(item);
	}

	@Then("user verifies item {string} is removed")
	public void user_verifies_item_is_removed(String string) {
		assertTrue(!cartPage.verifyItemIsPresentInCart("Handmade Doll"));
	}
	
	@Then("user verifies other items are in cart")
	public void user_verifies_other_items_are_in_cart(DataTable table) {
		List<String> items = table.asList(String.class);		
		for (String item : items) {
			assertTrue(cartPage.verifyItemIsPresentInCart((item)));
		}
	}

	@Then("user verifies pricing of items in the cart")
	public void user_verifies_pricing_of_items_in_the_cart(DataTable table)  {
		/*List<String> items = table.asList(String.class);		
		for (String item : items) {
			assertEquals(world.itemsFromShopPage.get(item),cartPage.getPriceOfItemInCart(item));
		}*/
		List<Map<String, String>> rows = table.asMaps(String.class, String.class);
		for (Map<String, String> columns : rows) {
			String item = columns.get("Item");
			String quantity = columns.get("Quantity");
			String finalprice = "$"+BigDecimal.valueOf(Double.parseDouble(world.getItemsFromShopPage().get(item).substring(1))*Integer.parseInt(quantity)).setScale(2,BigDecimal.ROUND_HALF_UP);
			assertEquals(finalprice,cartPage.getPriceOfItemInCart(item));
		}
	}


	@When("user updates quantity of items in cart")
	public void user_updates_quantity_of_items_in_cart(DataTable dataTable) throws InterruptedException {
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> columns : rows) {
			String item = columns.get("Item");
			String quantity = columns.get("Quantity");
			cartPage.updateQuantityInCart(item, quantity);
		}
	}
	
	@When("user clicks on Checkout button")
	public void user_clicks_on_checkout_button() {
		cartPage.clickOnCheckOut();
	}

	@When("user enter delivery details")
	public void user_enter_delivery_details(io.cucumber.datatable.DataTable dataTable) {
		Map<String, String> map = dataTable.asMap(String.class, String.class);
		world.setCustomerName(map.get("Forename"));
		for (Map.Entry<String, String> entry : map.entrySet()) {
			cartPage.populateField(entry.getKey(), entry.getValue());
		}
		
	}
	
	@When("user enter payment details")
	public void user_enter_payment_details(io.cucumber.datatable.DataTable dataTable) {
		Map<String, String> map = dataTable.asMap(String.class, String.class);
		
		for (Map.Entry<String, String> entry : map.entrySet()) {
			cartPage.populateField(entry.getKey(), entry.getValue());
		}
	}
	
	@When("user clicks on Submit button")
	public void user_clicks_on_submit_button() {
		cartPage.submitDeliveryandPaymentDetails();
	}
	
	@Then("user succesfully checks out")
	public void user_succesfully_checks_out() {
		System.out.println("Message :: "+cartPage.getSuccesfulCheckoutMsg());
		assertTrue(cartPage.getSuccesfulCheckoutMsg().contains("Thanks "+world.getCustomerName()+", your order has been accepted. Your order nuumber is "), "Checkout not successfull");
	}


}
