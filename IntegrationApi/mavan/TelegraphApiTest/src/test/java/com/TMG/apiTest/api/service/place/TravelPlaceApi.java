package com.TMG.apiTest.api.service.place;

import com.TMG.apiTest.api.VOs.Locations;
import com.TMG.apiTest.restAssuredUtil.RestService;
import com.TMG.apiTest.helper.TmgUtil;
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

    public void deletePlace(String apiPath) {

    }

    public void createPlace(String apiPath, Locations travelPlace) {
        String requestBody = TmgUtil.converyObjectToJsonString(travelPlace);
        LOGGER.info("End Point : " + RestAssured.baseURI);
        LOGGER.info("the API path is - " + apiPath);
        LOGGER.info("the json body is - " + requestBody);
        Response response = RestService.postMethod("json", apiPath, requestBody);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 201);
    }

    public void addRelationBetweenPlaces(String apiPath, String placeIdA, String placeIdB) {
        LOGGER.info("End Point : " + RestAssured.baseURI);
        Response response = RestService.postMethod("json", "/" + placeIdB + apiPath + "/" + placeIdA, "");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 201);
    }

    public void searchPlace(String apiPath) {
        Response response = RestService.getMethod("json", apiPath);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 201);
    }

    public void deletePlace(String apiPath, String placeId) {
        Response response = RestService.deleteMethod("json", apiPath + "/" + placeId);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    public void addRelationBetweenMultiplePlaces(String apiPath, String placeIdB, String placeIdC) {
        LOGGER.info("End Point : " + RestAssured.baseURI);
        Response response = RestService.postMethod("json", "/" + placeIdB + "/addRelation/" + placeIdC, "");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 201);
    }

    public void removeRelationBetweenMultiplePlaces(String apiPath, String placeIdB, String placeIdC) {
        LOGGER.info("End Point : " + RestAssured.baseURI);
        Response response = RestService.postMethod("json", "/" + placeIdB + "/removeRelation/" + placeIdC, "");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }
}
