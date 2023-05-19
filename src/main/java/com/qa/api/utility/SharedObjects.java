package com.qa.api.utility;

import com.qa.api.config.ConfigFileFactory;
import com.qa.api.config.ConfigReader;
import com.qa.api.config.TestDataReader;
import io.cucumber.java.Scenario;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class SharedObjects {
    private Client client;
    private Scenario scenario;
    private ConfigReader configReader;
    private TestDataReader testDataReader;
    private Map<String, Object> runTimeData = new HashMap<>();

    public void setConfigReader(){
        this.configReader = ConfigFileFactory.configReader;
    }

}
