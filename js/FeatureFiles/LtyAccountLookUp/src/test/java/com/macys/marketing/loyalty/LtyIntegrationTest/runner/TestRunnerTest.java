package com.macys.marketing.loyalty.LtyIntegrationTest.runner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(glue = "classpath:com/macys/marketing/loyalty/LtyIntegrationTest/steps", strict = false, features = "classpath:features", format = {"pretty", "html:target/Destination"})
public class TestRunnerTest {
	
}