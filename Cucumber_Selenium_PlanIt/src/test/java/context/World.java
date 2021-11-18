package context;

import java.util.HashMap;
import java.util.Map;

import com.PageObjects.CartPage;
import com.PageObjects.ShopPage;

public class World {
	private Map<String, String> itemsFromShopPage = new HashMap<String,String>();
	String CustomerName;
	public String getCustomerName() {
		return CustomerName;
	}
	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}
	public Map<String, String> getItemsFromShopPage() {
		return itemsFromShopPage;
	}
	public void setItemsFromShopPage(Map<String, String> itemsFromShopPage) {
		this.itemsFromShopPage = itemsFromShopPage;
	}
	
}
