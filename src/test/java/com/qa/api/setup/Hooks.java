package com.qa.api.setup;

import com.qa.api.config.TestDataReader;
import com.qa.api.utility.Client;
import com.qa.api.utility.SharedObjects;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
    SharedObjects sharedObjects;
    TestDataReader testDataReader;

    public Hooks(SharedObjects sharedObjects) {
        this.sharedObjects = sharedObjects;
    }

    @Before
    public void setUp(Scenario scenario){
        sharedObjects.setConfigReader();
        sharedObjects.setClient(new Client());
        sharedObjects.setScenario(scenario);
    }

    @After
    public void tearDown(Scenario scenario){

    }
}
