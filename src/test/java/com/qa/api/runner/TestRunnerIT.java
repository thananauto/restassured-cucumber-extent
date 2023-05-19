package com.qa.api.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = {"classpath:features"},
        plugin = {"pretty",  "json:target/cucumber-json.json",
                "rerun:target/failedrerun.txt",
                "tech.grasshopper.AllureCucumberMappingPlugin:target/cucumber-allure.json"},
        monochrome = true,
        dryRun = false,
        glue = {"com.qa.api.setup", "com.qa.api.stepdefinition", "com.qa.api.config", "com.qa.api.utility"}
)
public class TestRunnerIT extends AbstractTestNGCucumberTests {


    @DataProvider(parallel = true)
    @Override
    public java.lang.Object[][] scenarios() {
        return super.scenarios();
    }
}
