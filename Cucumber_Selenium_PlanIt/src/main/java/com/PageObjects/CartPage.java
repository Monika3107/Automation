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
	
	@FindBy(tagName = "table")
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
	
	public boolean verifyItemsAddedInCart(List<String> items, List<String> numbers) throws InterruptedException {
		boolean areCartItemsValid = false;
		
		for(int listItem = 0; listItem < items.size(); listItem++) {
			
			String item = items.get(listItem).toString();
			String number = numbers.get(listItem).toString();
			WebElement itemBody = itemTable.findElement(By.tagName("tbody"));
			List<WebElement> rows_table = itemBody.findElements(By.tagName("tr"));
			int rows_count = rows_table.size();
			
			// Loop will execute till the last row of table.
			for (int row = 0; row < rows_count; row++) {					
				WebElement ProductNameElement  = driver.findElement(By.xpath("/html/body/div[2]/div/form/table/tbody/tr["+(row+1)+"]/td[1]"));
				String productName = ProductNameElement.getText();
				WebElement productQuantityElement  = driver.findElement(By.xpath("/html/body/div[2]/div/form/table/tbody/tr["+(row+1)+"]/td[3]/input"));													
				String productQuantity = productQuantityElement.getAttribute("value");					
				
				if(productName.contentEquals(item) && productQuantity.contentEquals(number)) {
					areCartItemsValid = true;					
					break;
				}
			}
		}
		return areCartItemsValid;
	} 
}
