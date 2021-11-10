package StepDefinitions;

import static org.junit.Assert.*;
import java.util.List;
import java.util.Map;
import com.PageObjects.CartPage;
import com.qa.factory.DriverFactory;
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
			assertEquals(columns.get("Number"), cartPage.getItemQuantity(columns.get("Item")));
		}
	}
	
	@Then("user removes item {string} from cart")
	public void user_removes_item_from_cart(String item) {
		cartPage.removeItemFromCart(item);
		System.out.println(item + "removed.........");
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
	public void user_verifies_pricing_of_items_in_the_cart(DataTable table) {
		List<String> items = table.asList(String.class);		
		for (String item : items) {
			assertEquals(world.itemsFromShopPage.get(item),cartPage.getPriceOfItemInCart(item));
		}
	}

}
