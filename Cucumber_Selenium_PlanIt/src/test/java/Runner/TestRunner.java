package Runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src//test//resources//features//", //Path of feature file
		glue = {"StepDefinitions","AppHooks"},//Path of Step Definition file
		plugin = {"pretty","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"timeline:test-output-thread/"
				,"rerun:target/failedrerun.txt"
				},
		tags = "@SmokeTest",
		dryRun= false, //to check the mapping is proper between feature file and step definition file
		monochrome = true, //display the console output in a proper readable format
		strict = true //strictly follows all the rules between feature and step definition file
		//,tags = {"@Smoke"}//,means OR(either of them will run)----AND means {"@SmokeTest","@Regressiontest"}----{"~@SmokeTest"} to ignore Smoke Tests
		//,tags = "~@skip"
		)
public class TestRunner {

}
