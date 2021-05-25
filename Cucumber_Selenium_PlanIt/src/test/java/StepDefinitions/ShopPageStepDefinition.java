package StepDefinitions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
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
	
	@When("User clicks on Cart menu")
	public void user_clicks_on_cart_menu() throws InterruptedException {
		shopPage.goToCart();
		Thread.sleep(2000);
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
	    
	    Assert.assertTrue(shopPage.verifyItemsAddedInCart(itemsinCart, quantityOfItemsinCart));
		Thread.sleep(2000);
	}


}