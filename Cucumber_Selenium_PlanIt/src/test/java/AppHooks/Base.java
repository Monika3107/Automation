package AppHooks;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.qa.util.ConfigReader;

public class Base {


	private ConfigReader configReader;
	//private WebDriver driver;
	
	public void quitDriver(WebDriver driver) {
		driver.quit();
	}
	
	public Properties getProperty() {
		//test


		//test
		configReader= new ConfigReader();
		return configReader.init_prop();
	}

}
