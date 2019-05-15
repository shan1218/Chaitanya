package com.TMG.apiTest.api.test;

import com.TMG.apiTest.restAssuredUtil.RestService;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.junit.Assert;

import static com.jayway.restassured.RestAssured.given;

public class PetStoreApiTest {

    public PetStoreApiTest(String endPoint){
        RestAssured.baseURI = endPoint;
    }

    public void findByStatus(String status) {
        Response response = RestService.getMethod("xml","/findByStatus?status="+status);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    public String getAllDataByStatus(String status) {
        Response response = RestService.getMethod("json","/findByStatus?status="+status);
        System.out.println("Response : "+response.jsonPath().getString("name"));
        return response.getBody().asString();
    }
}
