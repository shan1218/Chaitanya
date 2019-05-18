package com.TMG.apiTest.stepDefinitions.travelPlace;

import com.TMG.apiTest.api.VOs.HotelVO;
import com.TMG.apiTest.api.VOs.Locations;
import com.TMG.apiTest.api.service.place.TravelHotelApi;
import com.TMG.apiTest.api.service.place.TravelPlaceApi;
import com.TMG.apiTest.helper.StepDefinition;
import com.TMG.apiTest.helper.PropertyReader;
import com.TMG.apiTest.helper.TmgUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;

public class TravelPlaceDefinition extends StepDefinition {

    PropertyReader propertyFileReader = null;
    TravelPlaceApi travelPlaceApi;
    TravelHotelApi travelHotelApi;
    String createPlacePath;
    HashMap<String, String> placeIdMap  = new HashMap<String, String>();

    @Given("^I Initialize Travel Page \"([^\"]*)\"$")
    public void initializeTravelPage(String fileName) {
        propertyFileReader = TmgUtil.loadPropertyFile(fileName, "travelPlace");
        createPlacePath = propertyFileReader.readProperty("createPlacePath");
        travelPlaceApi = new TravelPlaceApi(getEnvironment().getEndpoints() + createPlacePath);
    }

    @Then("^I create a Place \"([^\"]*)\" with type \"([^\"]*)\" lat \"([^\"]*)\" and long \"([^\"]*)\"$")
    public void createPlace(String placeName, String type, String latitude, String longitude) {
        Locations location = new Locations();
        int random = TmgUtil.generateRandomNumber();
        location.setId(""+random);
        location.setLabel(placeName);
        location.setType(type);
        location.setLat(latitude);
        location.setLong(longitude);
        placeIdMap.put(location.getLabel(), location.getId());
        travelPlaceApi.createPlace("", location);
    }

    /*@Then("^I Create A First Place$")
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
    }*/

    @When("^I add relationship between First Place A (.*) and Second Place B (.*), Place A as the parent of Place B$")
    public void addRelationshipBetweenFirstPlaceAAndSecondPlaceBPlaceAAsTheParentOfPlaceB(String placeNameA, String placeNameB) throws Exception {
        String addRelationEndpoint = propertyFileReader.readProperty("addRelation");
        String placeIdA = (String) placeIdMap.get(placeNameA);
        String placeIdB = (String) placeIdMap.get(placeNameB);
        Thread.sleep(3000);
        travelPlaceApi.addRelationBetweenPlaces(addRelationEndpoint, placeIdA,placeIdB );

    }

    @Then("^I Search A Place \"([^\"]*)\"$")
    public void searchAPlace(String fileName) {
        propertyFileReader = TmgUtil.loadPropertyFile(fileName, "travelPlace");
        String flakeId = propertyFileReader.readProperty("flakeId");
        String label = propertyFileReader.readProperty("label");
        String searchPath = propertyFileReader.readProperty("searchEndPointPath");
        travelPlaceApi.searchPlace("/" + flakeId + searchPath + "?label=" + label);
    }

    @Then("^Hotel A should be available on Place B (.*) Hotel searches$")
    public void hotelAShouldBeAvailableOnPlaceBHotelSearches(String PlaceNameB) {
        //String placeToSearch = propertyFileReader.readProperty("id2");
        String placeToSearch = placeIdMap.get(PlaceNameB);
        travelHotelApi = new TravelHotelApi(getEnvironment().getEndpoints() + "/travel-products/hotels");
        travelHotelApi.searchHotelByPlace("/search?annotations=", placeToSearch);
    }

    @Then("^Hotel A should be available on Place A (.*) Hotel searches$")
    public void hotelAShouldBeAvailableOnPlaceAHotelSearches(String PlaceNameA) {
        //String placeToSearch = propertyFileReader.readProperty("id1");
        String placeToSearch = placeIdMap.get(PlaceNameA);
        travelHotelApi = new TravelHotelApi(getEnvironment().getEndpoints() + "/travel-products/hotels");
        travelHotelApi.searchHotelByPlace("/search?annotations=", placeToSearch);
    }

    @When("^I create Hotel A with locations containing Place B \"([^\"]*)\"$")
    public void iCreateHotelAWithLocationsContainingPlaceB(String placeNameB) throws Throwable {
        try {
            File file = ResourceUtils.getFile("classpath:com/TMG/apiTest/properties/hotel/createHotel.json");
            String content = new String(Files.readAllBytes(file.toPath()));
            ObjectMapper mapper = new ObjectMapper();
            HotelVO hotel = mapper.readValue(content , HotelVO.class);
            System.out.println("id of place B - " + placeIdMap.get(placeNameB));
            hotel.getLocations().get(0).setId(placeIdMap.get(placeNameB));
            hotel.getLocations().get(0).setLabel(placeNameB);
            travelHotelApi = new TravelHotelApi(getEnvironment().getEndpoints() + "/travel-products/hotels");
            travelHotelApi.createHotelWithPlace("", content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//
//    @When("^I add relationship between Place A and Place X, Place X as a parent of Place B$")
//    public void iAddRelationshipBetweenHotelsAndPlaces() {
//        TravelPlace travelPlace = new TravelPlace();
//        String placeId2 = propertyFileReader.readProperty("id2");
//        String placeId3 = propertyFileReader.readProperty("id3");
//        String addRelationEndpoint = propertyFileReader.readProperty("addRelation");
//        travelPlaceApi.addRelationBetweenMultiplePlaces(addRelationEndpoint, placeId2, placeId3);
//
//    }
//
//    @Then("^I Create A Third Place$")
//    public void iCreateAThirdPlace() {
//        TravelPlace travelPlace = new TravelPlace();
//        travelPlace.setId(propertyFileReader.readProperty("id3"));
//        travelPlace.setLabel(propertyFileReader.readProperty("label3"));
//        travelPlace.setType(propertyFileReader.readProperty("type3"));
//        travelPlace.setLat(propertyFileReader.readProperty("lat3"));
//        travelPlace.setLong(propertyFileReader.readProperty("long3"));
//        travelPlaceApi.createPlace("", travelPlace);
//
//    }
//
//    @Then("^Hotel A should be available on Place X Hotel searches$")
//    public void hotelAShouldBeAvailableOnPlaceXHotelSearches() {
//        String placeToSearch = propertyFileReader.readProperty("id3");
//        travelHotelApi = new TravelHotelApi(getEnvironment().getEndpoints() + "/travel-products/hotels");
//        travelHotelApi.searchHotelByPlace("/search?annotations=", placeToSearch);
//
//    }
//
//    @When("^I remove relationship between Place A and Place X, remove Place X as a parent of Place B$")
//    public void iRemoveRelationshipBetweenPlaceAAndPlaceXRemovePlaceXAsAParentOfPlaceB() {
//
//        TravelPlace travelPlace = new TravelPlace();
//        String placeId2 = propertyFileReader.readProperty("id2");
//        String placeId3 = propertyFileReader.readProperty("id3");
//        String removeRelationEndpoint = propertyFileReader.readProperty("removeRelation");
//        travelPlaceApi.removeRelationBetweenMultiplePlaces(removeRelationEndpoint, placeId2, placeId3);
//
//    }
//
//    @Then("^Hotel A should not be available on Place X Hotel searches$")
//    public void hotelAShouldNotBeAvailableOnPlaceXHotelSearches() {
//        String placeToSearch = propertyFileReader.readProperty("id3");
//        travelHotelApi = new TravelHotelApi(getEnvironment().getEndpoints() + "/travel-products/hotels");
//        travelHotelApi.searchHotelByPlace("/search?annotations=", placeToSearch);
//
//    }


}

