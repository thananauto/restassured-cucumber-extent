package com.qa.api.stepdefinition;

import com.qa.api.utility.Client;
import com.qa.api.utility.Helper;
import com.qa.api.utility.SharedObjects;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class RequestDefinition extends Helper {
    SharedObjects sharedObjects;
    public RequestDefinition(SharedObjects sharedObjects){
        this.sharedObjects = sharedObjects;
    }

    @Given("I hit the url {string}")
    public void setUrl(String url) {
        if(url.contains("{")){
            String subUrl = url.split("\\{")[1].split("\\}")[0];
            Object runTimedata = sharedObjects.getRunTimeData().get(subUrl);
            url = url.replaceAll("\\{", "").replaceAll("\\}", "").replace(subUrl, (String)runTimedata);
        }
       String baseUrl = sharedObjects.getConfigReader().baseUrl();
       sharedObjects.getClient().setApiUrl(baseUrl + url);
    }
    @Given("I set the header as")
    public void setHeader(DataTable dataTable) {
       Map<String, String> mapHeader = new HashMap<>(dataTable.asMap(String.class, String.class));
       sharedObjects.getClient().setHeader(mapHeader);
    }

    @Given("I set the parameter")
    public void setParameter(DataTable dataTable) {
        Map<String, String> mapParams = new HashMap<>(dataTable.asMap(String.class, String.class));
        sharedObjects.getClient().setParams(mapParams);
    }
    @When("^I send the (.*) request$")
    public void sendRequest(String method) {
        Client client = sharedObjects.getClient();
        if(method.equalsIgnoreCase("get")){
            sharedObjects.getClient().setResponse(client.sendGETRequest());
        }else if(method.equalsIgnoreCase("post")){
            sharedObjects.getClient().setResponse(client.sendPOSTRequest());
        }else if(method.equalsIgnoreCase("put")){
            sharedObjects.getClient().setResponse(client.sendPUTRequest());
        }else if(method.equalsIgnoreCase("delete")){
            sharedObjects.getClient().setResponse(client.sendDELETERequest());
        }
    }

    @Given("^I get the body from (.*) json and set$")
    public void getReqPostBody(String fileName, DataTable dataTable) throws IOException {
       Map<String, String> mapDts = dataTable.asMap(String.class, String.class);
        String schema = getSchemaFromJSON("testData",fileName);
        JSONObject object = new JSONObject(schema);
        //Java 8 stream doesn't allow to change a local variable, a small workaround
        List<JSONObject> obj = Arrays.asList(object);
        mapDts.keySet().forEach( k -> {
            try {
                obj.set(0,updateJson(obj.get(0),"set", k,mapDts.get(k)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        System.out.println(obj.get(0));
        sharedObjects.getClient().setReq_body(obj.get(0).toString());

    }

}
