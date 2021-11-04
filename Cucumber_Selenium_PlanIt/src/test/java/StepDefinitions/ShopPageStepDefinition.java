package StepDefinitions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import com.PageObjects.CartPage;
import com.PageObjects.ShopPage;
import com.qa.factory.DriverFactory;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ShopPageStepDefinition {

	ShopPage shopPage = new ShopPage(DriverFactory.getDriver());
	
	@Given("User navigates to Shop page from Home page")
	public void user_navigates_to_shop_page_from_home_page() throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		shopPage.goToShopPage();
		Thread.sleep(2000);
	}

	@When("User Click on items to add in cart")
	public void user_click_on_items_to_add_in_cart(DataTable table) throws InterruptedException {

		List<Map<String, String>> rows = table.asMaps(String.class, String.class);

		for (Map<String, String> columns : rows) {
			String item = columns.get("Item");
			int number = Integer.parseInt(columns.get("Number"));
			shopPage.addItemsToCart(item, number);
		}
		Thread.sleep(2000);
	}

}