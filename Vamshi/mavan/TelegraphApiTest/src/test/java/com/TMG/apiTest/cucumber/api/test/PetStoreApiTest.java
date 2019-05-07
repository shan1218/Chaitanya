package com.TMG.apiTest.cucumber.api.test;

import com.TMG.apiTest.cucumber.ApiConfiguration;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.given;

public class PetStoreApiTest {

    public PetStoreApiTest(String endPoint){
        //RestAssured.baseURI = ApiConfiguration.PET_STORE_API_URI;
        RestAssured.baseURI = endPoint;
    }

    public void findByStatus(String status) {
        Response response =
                given().accept("application/xml").
                        when().
                        get("/findByStatus?status="+status).
                        then().
                        statusCode(200).
                        extract().response();
    }

    public String getAllDataByStatus(String status) {
        Response response =
                given().contentType("application/json").
                        when().
                        get("/findByStatus?status="+status).andReturn();
        System.out.println("Response : "+response.jsonPath().getString("name"));
        return response.getBody().asString();
    }

}
