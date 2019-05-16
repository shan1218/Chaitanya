package com.TMG.apiTest.restAssuredUtil;

import com.TMG.apiTest.api.service.place.TravelHotelApi;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Headers;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class RestService {

    private static final Logger LOGGER = Logger.getLogger(RestService.class.getName());

    public static Response getMethod(String accept, String path){
        LOGGER.info("\nGet Api Path : "+path);
        RequestSpecification request = RestAssured.given().accept(getAcceptHeader(accept)).headers(getHeaders());
        return request.get(path).andReturn();
    }

    public static Response postMethod(String accept, String path, String body){
        LOGGER.info("\nPost Api Path : "+path);
        RequestSpecification request = RestAssured.given().accept(getAcceptHeader(accept)).headers(getHeaders());
        request.body(body);
        return request.post(path).andReturn();
    }

    public static String getAcceptHeader(String accept){
        String acceptHeader = "application/json";
        if(null != accept && accept.trim().equalsIgnoreCase("xml")){
            acceptHeader = "application/xml";
        }
        return  acceptHeader;
    }

    public static Map<String, String> getHeaders(){
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type","application/json");
        return headers;
    }
}
