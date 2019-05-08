package com.TMG.apiTest.cucumber.restAssuredUtil;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Headers;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class RestService {

    public static Response getMethod(String accept, String path){
        System.out.println("\nGet Api Path : "+path);
        RequestSpecification request = RestAssured.given().accept(getAcceptHeader(accept)).headers(getHeaders());
        return request.get(path).andReturn();
    }

    public static Response postMethod(String accept, String path, String body){
        System.out.println("\nPost Api Path : "+path);
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
