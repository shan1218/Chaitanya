package com.TMG.apiTest.api.service.place;

import com.TMG.apiTest.restAssuredUtil.RestService;
import com.TMG.apiTest.helper.TmgUtil;
import com.TMG.apiTest.vo.TravelPlace;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.junit.Assert;

import java.util.logging.Logger;

import static com.jayway.restassured.RestAssured.given;

public class TravelPlaceApi {

    private static final Logger LOGGER = Logger.getLogger(TravelPlaceApi.class.getName());

    public TravelPlaceApi(String endPoint) {

        RestAssured.baseURI = endPoint;
    }

    public void assertValue(io.restassured.response.Response response, String field, String value) {
        String successCode = response.jsonPath().get(field);
        junit.framework.Assert.assertEquals("Correct Success code was returned", value, successCode);
    }

    public void createPlace(String apiPath, TravelPlace travelPlace) {
        String requestBody = TmgUtil.converyObjectToJsonString(travelPlace);
        LOGGER.info("End Point : " + RestAssured.baseURI);
        LOGGER.info("the API path is - " + apiPath);
        LOGGER.info("the json body is - " + requestBody);
        Response response = RestService.postMethod("json", apiPath, requestBody);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 201);
    }

    public void addRelationBetweenPlaces(String apiPath, String placeId1, String placeId2) {
        LOGGER.info("End Point : " + RestAssured.baseURI);
        Response response = RestService.postMethod("json", "/" + placeId2 + "/addRelation/" + placeId1, "");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 201);
    }

    public void searchPlace(String apiPath) {
        Response response = RestService.getMethod("json", apiPath);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 201);
    }

    public void addRelationBetweenMultiplePlaces(String apiPath, String placeId2, String placeId3) {
        LOGGER.info("End Point : " + RestAssured.baseURI);
        Response response = RestService.postMethod("json", "/" + placeId2 + "/addRelation/" + placeId3, "");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 201);
    }

    public void removeRelationBetweenMultiplePlaces(String apiPath, String placeId2, String placeId3) {
        LOGGER.info("End Point : " + RestAssured.baseURI);
        Response response = RestService.postMethod("json", "/" + placeId2 + "/removeRelation/" + placeId3, "");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }
}
