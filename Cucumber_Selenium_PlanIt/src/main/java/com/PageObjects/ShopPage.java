package com.PageObjects;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShopPage {

	
	@FindBy(xpath = "//*[@id='nav-shop']/a")
	private static WebElement ShopPageTab;
	
	@FindBy(xpath = "//div[@class='products ng-scope']/child::ul") 
	private static WebElement ProductListbody;
	
	@FindBy(xpath = "//div[@class='alert alert-success']")
	private static WebElement SuccessMsg;
	
//	WebElement itemBody = driver.findElement(By.xpath("/html/body/div[3]/div/form/table/tbody/tr/td["+(row+1)+"]/img"));
	
	
	//Initializing the page factory
	
	private WebDriver driver;
	
	public ShopPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
		
	public void goToShopPage() {
		ShopPageTab.click();
	}
	
	public void addItemsToCart(String productname, int number) {
		do {
			driver.findElement(By.xpath("//h4[text()='"+productname+"']/following-sibling::p/a[text()='Buy']")).click();
			number--;
		}while(number>0);
	}
	
	public String getPrice(String productname, int number) {
		Double productPrice = 0.0;
		do {
			productPrice += Double.parseDouble(driver.findElement(By.xpath("//h4[text()='"+productname+"']/following-sibling::p/span")).getText().substring(1));
			number--;
			
		}while(number>0);
		return "$"+Double.toString(productPrice);
	}
	
}
