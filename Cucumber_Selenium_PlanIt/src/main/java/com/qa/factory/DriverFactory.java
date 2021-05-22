package com.qa.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;
	public static ThreadLocal<WebDriver> tlDriver= new ThreadLocal<WebDriver>();
	
	public WebDriver initDriver(String browser) {
		System.out.println("browser value is "+browser);
		if(browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			tlDriver.set(new ChromeDriver());
		}else if(browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver());
		}else if(browser.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			tlDriver.set(new EdgeDriver());
		}else if(browser.equals("safari")) {
//			WebDriverManager.operadriver().setup();
			tlDriver.set(new SafariDriver());
		}else
			System.out.println("Please pass the correct browser value: "+browser);
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		return getDriver();
	}
	
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	
}
