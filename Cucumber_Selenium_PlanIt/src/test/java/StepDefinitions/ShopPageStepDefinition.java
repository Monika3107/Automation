package StepDefinitions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.PageObjects.CartPage;
import com.PageObjects.ShopPage;
import com.qa.factory.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ShopPageStepDefinition {

	ShopPage shopPage = new ShopPage(DriverFactory.getDriver());
	
	private World world;
	public ShopPageStepDefinition(World world) {
        this.world = world;
    }

	@Given("User navigates to Shop page from Home page")
	public void user_navigates_to_shop_page_from_home_page() {
		// Write code here that turns the phrase above into concrete actions
		shopPage.goToShopPage();
	}

	@When("User Click on items to add in cart")
	public void user_click_on_items_to_add_in_cart(DataTable table) {

		List<Map<String, String>> rows = table.asMaps(String.class, String.class);

		for (Map<String, String> columns : rows) {
			String item = columns.get("Item");
			int number = Integer.parseInt(columns.get("Number"));
			shopPage.addItemsToCart(item, number);
		}
	}
	

	@When("User Click on items to add in cart and store price")
	public void user_click_on_items_to_add_in_cart_and_store_price(DataTable dataTable) {
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
		List<String> items = new ArrayList<String>();
		for (Map<String, String> columns : rows) {
			String item = columns.get("Item");
			int number = Integer.parseInt(columns.get("Number"));
			shopPage.addItemsToCart(item, number);
			System.out.println("item price ********"+shopPage.getPrice(item, number));
			world.itemsFromShopPage.put(item, shopPage.getPrice(item, number));
				//testContext.scenarioContext.setContext(Context.ITEM_PRICE, shopPage.getPrice(item, number));
			items.add(item);
		}
	}
}