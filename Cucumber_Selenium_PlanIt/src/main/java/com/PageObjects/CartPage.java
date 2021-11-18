package com.PageObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CartPage {
	//Web elements
	
	@FindBy(xpath = "//*[@id=\"nav-cart\"]/a")
	private static WebElement cartTab;
	
	@FindBy(xpath = "//table[@class='table table-striped cart-items']")
	private static WebElement itemTable;
	
	@FindBy(xpath = "//a[text()='Yes'][@class='btn btn-success']")
	private static WebElement confirmRemove;
	
	@FindBy(xpath = "//a[text()='Check Out']")
	private static WebElement checkOutButton;
	//Initializing the page factory
	
	@FindBy(id = "checkout-submit-btn")
	private static WebElement submitButton;
	
	@FindBy(xpath = "//div[@class=\"alert alert-success\"]")
	private static WebElement SuccessMsg;
		
	private WebDriver driver;
			
	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);	
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
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
	
	public void updateQuantityInCart(String item, String quantity) {
		driver.findElement(By.xpath("//td[contains(text(),'"+item+"')]/following-sibling::td/input[@name='quantity']")).clear();
		driver.findElement(By.xpath("//td[contains(text(),'"+item+"')]/following-sibling::td/input[@name='quantity']")).sendKeys(quantity);
	}
	
	public void clickOnCheckOut() {
		checkOutButton.click();
	}
	
	public void populateField(String field, String value) {
		if(field.equalsIgnoreCase("Card Type"))
			new Select(driver.findElement(By.xpath("//label[contains(text(),'"+field+"')]/following-sibling::div/select"))).selectByValue(value);
		else if(field.equalsIgnoreCase("Address"))
			driver.findElement(By.xpath("//label[contains(text(),'"+field+"')]/following-sibling::div/textarea")).sendKeys(value);
		else
			driver.findElement(By.xpath("//label[contains(text(),'"+field+"')]/following-sibling::div/input")).sendKeys(value);
	}
	
	public void submitDeliveryandPaymentDetails() {
		submitButton.click();
	}
	
	public String getSuccesfulCheckoutMsg() {
		String successMsg = SuccessMsg.getText();	
		return successMsg;
	}

}
