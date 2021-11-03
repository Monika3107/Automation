package com.PageObjects;

import java.util.List;

import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShopPage {

	
	@FindBy(xpath = "//*[@id=\"nav-shop\"]/a")
	WebElement ShopPageTab;
	
	@FindBy(xpath = "/html/body/div[2]/div/ul") 
	WebElement ProductListbody;
	
//	WebElement itemBody = driver.findElement(By.xpath("/html/body/div[3]/div/form/table/tbody/tr/td["+(row+1)+"]/img"));
	
	
	//Initializing the page factory
	
	private WebDriver driver;
	
	public ShopPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);		
	}
		
	public void goToShopPage() {
		ShopPageTab.click();
	}
	
	public void addItemsToCart(String productname, int number) throws InterruptedException {
		List<WebElement> ProductList = ProductListbody.findElements(By.tagName("li"));
		int product_count = ProductList.size();
		
		// Loop will execute till the last list element
		for (int product = 0; product < product_count; product++) {
		
			WebElement ProductNameWeb = driver.findElement(By.xpath("//*[@id=\"product-"+(product+1)+"\"]/div/h4"));
			
			String productNameWeb = ProductNameWeb.getText();
			if(productNameWeb.equals(productname)) {
				do {
					WebElement buyButtonElement= driver.findElement(By.xpath("//*[@id=\"product-"+(product+1)+"\"]/div/p/a"));
					buyButtonElement.click();
					number--;
				}while(number>0);
				break;
			}
		}
	}
	
}
