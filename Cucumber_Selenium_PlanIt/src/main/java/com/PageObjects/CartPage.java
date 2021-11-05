package com.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
	//Web elements
	
	@FindBy(xpath = "//*[@id=\"nav-cart\"]/a")
	WebElement CartTab;
	
	@FindBy(xpath = "//table[@class='table table-striped cart-items']")
	WebElement itemTable;
	
	//Initializing the page factory
		
	private WebDriver driver;
			
	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);		
	}
			
	public void goToCart() {
		CartTab.click();
	}
	

	public String verifyCartitems(String item) {
		return driver.findElement(By.xpath("//td[contains(text(),'"+item+"')]/following-sibling::td/input")).getAttribute("value");
	}
	
	/*public boolean verifyItemsAddedInCart(List<String> items, List<String> numbers) throws InterruptedException {
		boolean areCartItemsValid = false;
		WebElement itemBody = itemTable.findElement(By.tagName("tbody"));
		List<WebElement> rows_table = itemBody.findElements(By.tagName("tr"));
		String itemName = null,value=null;	
		for(int listItem = 0; listItem < items.size(); listItem++) {
			if(!rows_table.isEmpty()) {
				for(int row = 0; row < rows_table.size(); row++) {
					//System.out.println("tr :: "+tr);
					itemName = itemBody.findElement(By.xpath("descendant::tr["+(row+1)+"]/td/img/parent::td")).getText();
					value = itemBody.findElement(By.xpath("descendant::tr["+(row+1)+"]/td/input")).getAttribute("value");
					if(itemName.contentEquals(items.get(listItem).toString()) && value.contentEquals(numbers.get(listItem).toString())) {
						areCartItemsValid = true;	
						break;
					}else {
						areCartItemsValid = false;
					}
				}
				
			}
	
		}

		return areCartItemsValid;
	}	
	*/
}
