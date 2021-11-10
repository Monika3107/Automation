package com.PageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
	//Web elements
	
	@FindBy(xpath = "//*[@id=\"nav-cart\"]/a")
	private static WebElement cartTab;
	
	@FindBy(xpath = "//table[@class='table table-striped cart-items']")
	private static WebElement itemTable;
	
	@FindBy(xpath = "//a[text()='Yes'][@class='btn btn-success']")
	private static WebElement confirmRemove;
	
	//Initializing the page factory
		
	private WebDriver driver;
			
	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);		
	}
			
	public void goToCart() {
		cartTab.click();
	}
	

	public String getItemQuantity(String item) {
		return driver.findElement(By.xpath("//td[contains(text(),'"+item+"')]/following-sibling::td/input")).getAttribute("value");
	}
	
	public void removeItemFromCart(String item) {
		WebElement removeItemButton = driver.findElement(By.xpath("//td[contains(text(),'"+item+"')]/following-sibling::td/ng-confirm[@title='Remove Item']"));
		removeItemButton.click();
		confirmRemove.click();
	}
	
	public boolean verifyItemIsPresentInCart(String item) {	
		return (getItemsPresentInCart().contains(item));	
	}
	
	public List<String> getItemsPresentInCart(){
		List<String> itemsInCart = new ArrayList<String>();
		/* get items present in the cart */
		WebElement tableBody = itemTable.findElement(By.tagName("tbody"));
		List<WebElement> items = tableBody.findElements(By.tagName("tr"));
			if(!items.isEmpty()) {
				for(int row = 0; row < items.size(); row++) {
					itemsInCart.add(tableBody.findElement(By.xpath("descendant::tr["+(row+1)+"]/td/img/parent::td")).getText());
				}
		}
		return itemsInCart;
	}
	
	public String getPriceOfItemInCart(String item) {
		return driver.findElement(By.xpath("//td[contains(text(),'"+item+"')]/following-sibling::td[3]")).getText();
	}
}
