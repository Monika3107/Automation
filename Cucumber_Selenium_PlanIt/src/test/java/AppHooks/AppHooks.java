package AppHooks;

import static org.junit.Assert.assertTrue;

import java.util.Properties;

import org.junit.Assume;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.qa.factory.DriverFactory;
import com.qa.util.ConfigReader;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

/**
 * Unit test for simple App.
 */
public class AppHooks {
	private DriverFactory driverFactory;
	private WebDriver driver;
	private ConfigReader configReader;
	Properties prop;
	
	@Before(value = "@skip", order = 0)
	public void skipScenario(Scenario scenario) {
		System.out.println("Skipped scenario:"+scenario.getName());
		Assume.assumeTrue(false);
		
	}
	@Before(order=1)
	public void getProperty() {
		configReader= new ConfigReader();
		prop=configReader.init_prop();
	}
	
	@Before(order=2)
	public void launchBrowser() {
		String browserName= prop.getProperty("browser");
		driverFactory=new DriverFactory();
		driver=driverFactory.initDriver(browserName);
	}
	
	@After(order=0)
	public void quitDriver() {
		driver.quit();
	}
	
	@After(order=1)
	public void tearDown(Scenario scenario) {
		if(scenario.isFailed()) {
			//take screenshot
			String screenshotName=scenario.getName().replaceAll("", "_");
			byte [] sourcePath=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(sourcePath, "image/png", screenshotName);
			
		}
	}

}
