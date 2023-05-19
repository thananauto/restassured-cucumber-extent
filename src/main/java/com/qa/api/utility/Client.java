package com.qa.api.utility;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import tech.grasshopper.filter.ExtentRestAssuredFilter;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
public class Client {
    public String baseURL;
    public String endPoint;
    public Map<String, String> header;
    public Map<String, String> params;
    final static Logger logger = Logger.getLogger(Client.class);
    public String req_body;
    public String apiUrl;
    public Response response;


    /**
     * Methos to send GET request
     * @return
     */
    public Response sendGETRequest(){
        //RestAssured.useRelaxedHTTPSValidation();
        logger.info("********** send GET request *************");
        logger.info("URL "+apiUrl);
        logger.info("Headers: "+header);
        logger.info("Parameters: "+params);
        logger.info("*****************************************");

        if( apiUrl.contains("?")){
             apiUrl = apiUrl.split("//?")[0];
            String parameter = apiUrl.split("//?")[1];
            List<String> lstParams = Arrays.asList(parameter.split("//&"));
             params = Objects.nonNull(getParams()) ? params : lstParams.stream().
                     collect(Collectors.toMap(p -> p.split("=")[0],
                             p -> p.split("=").length ==1 ? "" : p.split("=")[1] ));
            return RestAssured.given()
                    .headers(header)
                    .params(params)
                    .filter(new ExtentRestAssuredFilter())
                    .get(apiUrl);
        }else if(Objects.nonNull(params)){
            return RestAssured.given()
                    .headers(header)
                    .params(params)
                    .filter(new ExtentRestAssuredFilter())
                    .get(apiUrl);
        }else{
            return RestAssured.given()
                    .headers(header)
                    .filter(new ExtentRestAssuredFilter())
                    .get(apiUrl);
        }

    }
    /**
     * Methos to send POST request
     * @return
     */
    public Response sendPOSTRequest(){
        RestAssured.useRelaxedHTTPSValidation();
        logger.info("********** send POST request *************");
        logger.info("URL "+apiUrl);
        logger.info("Headers: "+header);
        logger.info("Body: "+req_body);
        logger.info("*****************************************");


            return RestAssured.given()
                    .headers(header)
                    .body(req_body)
                    .filter(new ExtentRestAssuredFilter())
                    .post(apiUrl);


    }
    /**
     * Methos to send DELETE request
     * @return
     */
    public Response sendDELETERequest(){
        RestAssured.useRelaxedHTTPSValidation();
        logger.info("********** send POST request *************");
        logger.info("URL "+apiUrl);
        logger.info("Headers: "+header);
        logger.info("Body: "+req_body);
        logger.info("*****************************************");


        return RestAssured.given()
                .headers(header)
                .body(req_body)
                .filter(new ExtentRestAssuredFilter())
                .delete(apiUrl);


    }

    /**
     * Methos to send DELETE request
     * @return
     */
    public Response sendPUTRequest(){
        RestAssured.useRelaxedHTTPSValidation();
        logger.info("********** send POST request *************");
        logger.info("URL "+apiUrl);
        logger.info("Headers: "+header);
        logger.info("Body: "+req_body);
        logger.info("*****************************************");


        return RestAssured.given()
                .headers(header)
                .body(req_body)
                .filter(new ExtentRestAssuredFilter())
                .put(apiUrl);


    }


}
