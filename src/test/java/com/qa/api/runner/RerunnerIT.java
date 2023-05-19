package com.qa.api.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = {"@target/failedrerun.txt"},
        plugin = {"pretty",
                "json:target/cucumber-json.json",
                "tech.grasshopper.AllureCucumberMappingPlugin:target/cucumber-allure.json"},
        monochrome = true,
        dryRun = false,
        glue = {"com.qa.api.setup", "com.qa.api.stepdefinition", "com.qa.api.config", "com.qa.api.utility"}
)
public class RerunnerIT extends AbstractTestNGCucumberTests {


    @DataProvider(parallel = true)
    @Override
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
