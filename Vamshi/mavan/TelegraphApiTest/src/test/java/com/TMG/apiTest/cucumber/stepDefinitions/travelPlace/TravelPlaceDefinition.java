package com.TMG.apiTest.cucumber.stepDefinitions.travelPlace;

import com.TMG.apiTest.cucumber.api.service.place.TravelPlaceApi;
import com.TMG.apiTest.cucumber.api.test.PetStoreApiTest;
import com.TMG.apiTest.helper.StepDefinition;
import com.TMG.apiTest.helper.PropertyReader;
import com.TMG.apiTest.helper.StepDefinition;
import com.TMG.apiTest.helper.TmgUtil;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class TravelPlaceDefinition extends StepDefinition {

    PropertyReader propertyFileReader = null;
    TravelPlaceApi travelPlaceApi;

    @Given("^I access the place by flakeId \"([^\"]*)\"$")
    public void iAccessThePlaceByFlakeId(String fileName) {
        propertyFileReader = TmgUtil.loadPropertyFile(fileName, "travelPlace");
        String flakeId = propertyFileReader.readProperty("flakeId");
        String getPlacePath = propertyFileReader.readProperty("getPlacePath");
        travelPlaceApi = new TravelPlaceApi(getEnvironment().getEndpoints());
        travelPlaceApi.findByFlakeId(getPlacePath,flakeId);
    }
}
