package com.TMG.apiTest.cucumber.api.service.place;

import com.TMG.apiTest.cucumber.restAssuredUtil.RestService;
import com.TMG.apiTest.helper.TmgUtil;
import com.TMG.apiTest.vo.TravelPlace;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.junit.Assert;

import static com.jayway.restassured.RestAssured.given;

public class TravelPlaceApi {

    public TravelPlaceApi(String endPoint){
        RestAssured.baseURI = endPoint;
    }

    public void createPlace(String apiPath, TravelPlace travelPlace){
        String requestBody = TmgUtil.converyObjectToJsonString(travelPlace);
        Response response = RestService.postMethod("json",apiPath, requestBody);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    public void searchPlace(String apiPath) {
        Response response = RestService.getMethod("json", apiPath);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);    }
}
