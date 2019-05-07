package com.TMG.apiTest.cucumber.api.service.place;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.given;

public class TravelPlaceApi {

    public TravelPlaceApi(String endPoint){
        RestAssured.baseURI = endPoint;
    }

    public void findByFlakeId(String restPath, String flakeId) {
        System.out.println("URL : "+(restPath+flakeId));
        Response response =
                given().accept("application/xml").
                        when().
                        get(restPath+flakeId).
                        then().
                        statusCode(200).
                        extract().response();
    }
}
