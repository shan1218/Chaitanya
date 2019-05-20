package com.TMG.apiTest.api.service.place;

        import com.TMG.apiTest.restAssuredUtil.RestService;
        import com.fasterxml.jackson.core.JsonProcessingException;
        import com.fasterxml.jackson.databind.ObjectMapper;
        import com.jayway.restassured.RestAssured;
        import com.jayway.restassured.response.Response;
        import org.junit.Assert;

        import java.util.logging.Logger;

public class TravelHotelApi {

    private static final Logger LOGGER = Logger.getLogger(TravelHotelApi.class.getName());

    public TravelHotelApi(String endPoint) {

        RestAssured.baseURI = endPoint;
    }

    public void assertValue(io.restassured.response.Response response, String field, String value) {
        String successCode = response.jsonPath().get(field);
        junit.framework.Assert.assertEquals( "Correct Success code was returned", value, successCode);
    }

    public <T> void createHotelWithPlace(String apiPath, T body) throws JsonProcessingException {
        String jsonString = "";
        if(!(body instanceof String)) {
            ObjectMapper objectMapper = new ObjectMapper();
            jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(body);
        } else {
            jsonString = body.toString();

        }
        Response response = RestService.postMethod("json", apiPath, jsonString);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    public void searchHotelByPlace(String apiPath, String place) {
        Response response = RestService.getMethod("json", apiPath + place);
        int statusCode = response.getStatusCode();
        LOGGER.info("Search Response : " + response.asString());
        Assert.assertEquals(statusCode, 200);
    }

    public void deleteHotelByFlakeId(String apiPath, String flakeId) {
        Response response = RestService.deleteMethod("json", apiPath + "/" +flakeId);
        int statusCode = response.getStatusCode();
        LOGGER.info("Search Response : " + response.asString());
        Assert.assertEquals(statusCode, 200);
    }
}
