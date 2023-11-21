package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"classpath:features/bestbuyapi"},
		glue = "product.bestbuyapi",
		tags = "@myTest",
		plugin = { "pretty" ,
					"json:target/cucumber.json",
					"html:target/cucumber",
					"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
			},
		dryRun=false,
		monochrome = true

		)
public class CucumberTest {




}
