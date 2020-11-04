package TestRunner;

import org.junit.runner.RunWith;



import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src\\test\\java\\Testcases\\FeatureFiles\\createOrder.feature",glue="Testcases.StepDefinition")



public class testRunner {
	
}
