package com.TMG.apiTest.cucumber.api.test;

import com.TMG.apiTest.cucumber.ApiConfiguration;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.given;

public class PetStoreApiTest {

    public PetStoreApiTest(){
        RestAssured.baseURI = ApiConfiguration.PET_STORE_API_URI;
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
                given().accept("application/xml").
                        when().
                        get("/findByStatus?status="+status).andReturn();
        return response.getBody().asString();
    }

}
