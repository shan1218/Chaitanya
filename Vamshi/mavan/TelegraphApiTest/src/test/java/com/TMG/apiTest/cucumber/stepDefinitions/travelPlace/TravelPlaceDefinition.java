package com.TMG.apiTest.cucumber.stepDefinitions.travelPlace;

import com.TMG.apiTest.cucumber.api.service.place.TravelPlaceApi;
import com.TMG.apiTest.helper.StepDefinition;
import com.TMG.apiTest.helper.PropertyReader;
import com.TMG.apiTest.helper.TmgUtil;
import com.TMG.apiTest.vo.TravelPlace;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class TravelPlaceDefinition extends StepDefinition {

    PropertyReader propertyFileReader = null;
    TravelPlaceApi travelPlaceApi;
    String createPlacePath;

    @Given("^I Initialize Travel Page \"([^\"]*)\"$")
    public void iInitializeTravelPage(String fileName) {
        System.out.println("FileName : "+fileName);
        propertyFileReader = TmgUtil.loadPropertyFile(fileName, "travelPlace");
        createPlacePath = propertyFileReader.readProperty("createPlacePath");
        travelPlaceApi = new TravelPlaceApi(getEnvironment().getEndpoints()+createPlacePath);
    }

    @Then("^I Create A First Place$")
    public void iCreateAFirstPlace() throws Throwable {
        TravelPlace travelPlace = new TravelPlace();
        travelPlace.setId(propertyFileReader.readProperty("id1"));
        travelPlace.setLabel(propertyFileReader.readProperty("label1"));
        travelPlace.setType(propertyFileReader.readProperty("type1"));
        travelPlace.setLat(propertyFileReader.readProperty("lat1"));
        travelPlace.setLong(propertyFileReader.readProperty("long1"));
        travelPlaceApi.createPlace("", travelPlace);
    }

    @Then("^I Create A Second Place$")
    public void iCreateASecondPlace() throws Throwable {
        TravelPlace travelPlace = new TravelPlace();
        travelPlace.setId(propertyFileReader.readProperty("id2"));
        travelPlace.setLabel(propertyFileReader.readProperty("label2"));
        travelPlace.setType(propertyFileReader.readProperty("type2"));
        travelPlace.setLat(propertyFileReader.readProperty("lat2"));
        travelPlace.setLong(propertyFileReader.readProperty("long2"));
        travelPlaceApi.createPlace("", travelPlace);
    }

    @Then("^I Search A Place \"([^\"]*)\"$")
    public void iSearchAPlace(String fileName) throws Throwable {
        propertyFileReader = TmgUtil.loadPropertyFile(fileName, "travelPlace");
        String flakeId = propertyFileReader.readProperty("flakeId");
        String label = propertyFileReader.readProperty("label");
        String searchPath = propertyFileReader.readProperty("searchEndPointPath");
        travelPlaceApi.searchPlace("/"+flakeId+searchPath+"?label="+label);
    }
}
