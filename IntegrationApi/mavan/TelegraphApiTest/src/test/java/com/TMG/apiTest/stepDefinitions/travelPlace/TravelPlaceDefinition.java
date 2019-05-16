package com.TMG.apiTest.stepDefinitions.travelPlace;

import com.TMG.apiTest.api.service.place.TravelHotelApi;
import com.TMG.apiTest.api.service.place.TravelPlaceApi;
import com.TMG.apiTest.helper.StepDefinition;
import com.TMG.apiTest.helper.PropertyReader;
import com.TMG.apiTest.helper.TmgUtil;
import com.TMG.apiTest.vo.TravelPlace;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.nio.file.Files;

public class TravelPlaceDefinition extends StepDefinition {

    PropertyReader propertyFileReader = null;
    TravelPlaceApi travelPlaceApi;
    TravelHotelApi travelHotelApi;
    String createPlacePath;

    @Given("^I Initialize Travel Page \"([^\"]*)\"$")
    public void initializeTravelPage(String fileName) {
        propertyFileReader = TmgUtil.loadPropertyFile(fileName, "travelPlace");
        createPlacePath = propertyFileReader.readProperty("createPlacePath");
        travelPlaceApi = new TravelPlaceApi(getEnvironment().getEndpoints() + createPlacePath);
    }

    @Then("^I Create A First Place$")
    public void createAFirstPlace() {
        TravelPlace travelPlace = new TravelPlace();
        travelPlace.setId(propertyFileReader.readProperty("id1"));
        travelPlace.setLabel(propertyFileReader.readProperty("label1"));
        travelPlace.setType(propertyFileReader.readProperty("type1"));
        travelPlace.setLat(propertyFileReader.readProperty("lat1"));
        travelPlace.setLong(propertyFileReader.readProperty("long1"));
        travelPlaceApi.createPlace("", travelPlace);
    }

    @Then("^I Create A Second Place$")
    public void createASecondPlace() {
        TravelPlace travelPlace = new TravelPlace();
        travelPlace.setId(propertyFileReader.readProperty("id2"));
        travelPlace.setLabel(propertyFileReader.readProperty("label2"));
        travelPlace.setType(propertyFileReader.readProperty("type2"));
        travelPlace.setLat(propertyFileReader.readProperty("lat2"));
        travelPlace.setLong(propertyFileReader.readProperty("long2"));
        travelPlaceApi.createPlace("", travelPlace);
    }

    @When("^I add relationship between First Place A and Second Place B , Place A as the parent of Place B$")
    public void addRelationshipBetweenFirstPlaceAAndSecondPlaceBPlaceAAsTheParentOfPlaceB() {
        TravelPlace travelPlace = new TravelPlace();
        String placeId1 = propertyFileReader.readProperty("id1");
        String placeId2 = propertyFileReader.readProperty("id2");
        String addRelationEndpoint = propertyFileReader.readProperty("addRelation");
        travelPlaceApi.addRelationBetweenPlaces(addRelationEndpoint, placeId1, placeId2);

    }

    @Then("^I Search A Place \"([^\"]*)\"$")
    public void searchAPlace(String fileName) {
        propertyFileReader = TmgUtil.loadPropertyFile(fileName, "travelPlace");
        String flakeId = propertyFileReader.readProperty("flakeId");
        String label = propertyFileReader.readProperty("label");
        String searchPath = propertyFileReader.readProperty("searchEndPointPath");
        travelPlaceApi.searchPlace("/" + flakeId + searchPath + "?label=" + label);
    }

    @When("^I Create Hotel A with locations containing Place B$")
    public void createHotelAWithLocationsContainingPlaceB() {
        try {
            File file = ResourceUtils.getFile("classpath:com/TMG/apiTest/properties/hotel/createHotel.json");
            String content = new String(Files.readAllBytes(file.toPath()));
            travelHotelApi = new TravelHotelApi(getEnvironment().getEndpoints() + "/travel-products/hotels");
            travelHotelApi.createHotelWithPlace("", content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Then("^Hotel A should be available on Place B Hotel searches$")
    public void hotelAShouldBeAvailableOnPlaceBHotelSearches() {
        String placeToSearch = propertyFileReader.readProperty("id2");
        travelHotelApi = new TravelHotelApi(getEnvironment().getEndpoints() + "/travel-products/hotels");
        travelHotelApi.searchHotelByPlace("/search?annotations=", placeToSearch);
    }

    @Then("^Hotel A should be available on Place A Hotel searches$")
    public void hotelAShouldBeAvailableOnPlaceAHotelSearches() {
        String placeToSearch = propertyFileReader.readProperty("id1");
        travelHotelApi = new TravelHotelApi(getEnvironment().getEndpoints() + "/travel-products/hotels");
        travelHotelApi.searchHotelByPlace("/search?annotations=", placeToSearch);
    }

    @When("^I add relationship between Place A and Place X, Place X as a parent of Place B$")
    public void iAddRelationshipBetweenHotelsAndPlaces() {
        TravelPlace travelPlace = new TravelPlace();
        String placeId2 = propertyFileReader.readProperty("id2");
        String placeId3 = propertyFileReader.readProperty("id3");
        String addRelationEndpoint = propertyFileReader.readProperty("addRelation");
        travelPlaceApi.addRelationBetweenMultiplePlaces(addRelationEndpoint, placeId2, placeId3);

    }

    @Then("^I Create A Third Place$")
    public void iCreateAThirdPlace() {
        TravelPlace travelPlace = new TravelPlace();
        travelPlace.setId(propertyFileReader.readProperty("id3"));
        travelPlace.setLabel(propertyFileReader.readProperty("label3"));
        travelPlace.setType(propertyFileReader.readProperty("type3"));
        travelPlace.setLat(propertyFileReader.readProperty("lat3"));
        travelPlace.setLong(propertyFileReader.readProperty("long3"));
        travelPlaceApi.createPlace("", travelPlace);

    }

    @Then("^Hotel A should be available on Place X Hotel searches$")
    public void hotelAShouldBeAvailableOnPlaceXHotelSearches() {
        String placeToSearch = propertyFileReader.readProperty("id3");
        travelHotelApi = new TravelHotelApi(getEnvironment().getEndpoints() + "/travel-products/hotels");
        travelHotelApi.searchHotelByPlace("/search?annotations=", placeToSearch);

    }

    @When("^I remove relationship between Place A and Place X, remove Place X as a parent of Place B$")
    public void iRemoveRelationshipBetweenPlaceAAndPlaceXRemovePlaceXAsAParentOfPlaceB() {

        TravelPlace travelPlace = new TravelPlace();
        String placeId2 = propertyFileReader.readProperty("id2");
        String placeId3 = propertyFileReader.readProperty("id3");
        String removeRelationEndpoint = propertyFileReader.readProperty("removeRelation");
        travelPlaceApi.removeRelationBetweenMultiplePlaces(removeRelationEndpoint, placeId2, placeId3);

    }

    @Then("^Hotel A should not be available on Place X Hotel searches$")
    public void hotelAShouldNotBeAvailableOnPlaceXHotelSearches() {
        String placeToSearch = propertyFileReader.readProperty("id3");
        travelHotelApi = new TravelHotelApi(getEnvironment().getEndpoints() + "/travel-products/hotels");
        travelHotelApi.searchHotelByPlace("/search?annotations=", placeToSearch);

    }


}

