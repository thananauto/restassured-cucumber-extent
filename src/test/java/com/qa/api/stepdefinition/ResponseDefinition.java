package com.qa.api.stepdefinition;

import com.qa.api.utility.Helper;
import com.qa.api.utility.SharedObjects;
import io.cucumber.java.en.Then;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.io.IOException;

public class ResponseDefinition extends Helper {

    SharedObjects sharedObjects;
    public ResponseDefinition(SharedObjects sharedObjects){
        this.sharedObjects = sharedObjects;
    }
    @Then("I get the status code as {string}")
    public void checkStatusCode(String code) {
        Assert.assertEquals(code,sharedObjects.getClient().getResponse().getStatusCode()+"");
    }
    @Then("I see the response having the {string} as {string}")
    public void checkResponseKeyValue(String key, String value) {
        if(value.contains("{")){
            String subUrl = value.split("\\{")[1].split("\\}")[0];
            Object runTimedata = sharedObjects.getRunTimeData().get(subUrl);
            value = value.replaceAll("\\{", "").replaceAll("\\}", "").replace(subUrl, (String)runTimedata);
        }
        JsonPath path = sharedObjects.getClient().getResponse().getBody().jsonPath();
        Assert.assertEquals(path.getString(key), value);
    }
    @Then("^I validate the (.*) schema of the response$")
    public void validateSchema(String schemaFileName) throws IOException {

       String schema = getSchemaFromJSON("schema",schemaFileName);
       sharedObjects
               .getClient()
               .getResponse()
               .then()
               .assertThat()
               .body(JsonSchemaValidator.matchesJsonSchema(schema));
    }
    @Then("Save the response {string}")
    public void getTheRunTimeValue(String variable) {
        JsonPath path = sharedObjects.getClient().getResponse().getBody().jsonPath();
        sharedObjects.getRunTimeData().put(variable,path.getString(variable));

    }
}
