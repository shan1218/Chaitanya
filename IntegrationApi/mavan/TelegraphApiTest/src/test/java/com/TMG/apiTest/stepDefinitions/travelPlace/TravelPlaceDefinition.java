package com.TMG.apiTest.stepDefinitions.travelPlace;

import com.TMG.apiTest.api.VOs.HotelVO;
import com.TMG.apiTest.api.VOs.Locations;
import com.TMG.apiTest.api.service.place.TravelHotelApi;
import com.TMG.apiTest.api.service.place.TravelPlaceApi;
import com.TMG.apiTest.config.AppConfig;
import com.TMG.apiTest.config.HotelConfig;
import com.TMG.apiTest.config.PlaceConfig;
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
    Integer placeIncrementer = null;
    String propertyFilePath = null;
    HashMap<String, String> placeIdMap  = new HashMap<String, String>();

    @Given("^I Initialize Travel Page$")
    public void initializeTravelPage() {
        propertyFileReader = TmgUtil.loadPropertyFile(PlaceConfig.PLACE_PROPERTY_FILENAME, PlaceConfig.PLACE_PROPERTY_PACKAGE);
        propertyFilePath = TmgUtil.getPropertyFilePath(PlaceConfig.PLACE_PROPERTY_FILENAME, PlaceConfig.PLACE_PROPERTY_PACKAGE);
        placeIncrementer = Integer.parseInt(propertyFileReader.readProperty(PlaceConfig.PLACE_INCREMENTER));
        propertyFileReader.setProperty(PlaceConfig.PLACE_INCREMENTER, (placeIncrementer + 1)+"", propertyFilePath);
        travelPlaceApi = new TravelPlaceApi(getEnvironment().getEndpoints() + PlaceConfig.PLACE_URL);
    }

    @Then("^I create a Place \"([^\"]*)\" with type \"([^\"]*)\" lat \"([^\"]*)\" and long \"([^\"]*)\"$")
    public void createPlace(String placeName, String type, String latitude, String longitude) {
        Locations location = new Locations();
        int random = TmgUtil.generateRandomNumber();
        location.setId(""+placeIncrementer);
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
        placeNameA = placeNameA.replaceAll("\"", "");
        placeNameB = placeNameB.replaceAll("\"", "");
        String placeIdA = propertyFileReader.readProperty((PlaceConfig.ID+placeNameA).trim());
        String placeIdB = propertyFileReader.readProperty((PlaceConfig.ID+placeNameB).trim());
        System.out.println("\n"+placeIdA + " : "+placeIdB);
        Thread.sleep(3000);
        travelPlaceApi.addRelationBetweenPlaces(PlaceConfig.ADD_RELATION_URL, placeIdA,placeIdB );

    }

    @Then("^I Search A Place \"([^\"]*)\"$")
    public void searchAPlace(String fileName) {
        propertyFileReader = TmgUtil.loadPropertyFile(fileName, PlaceConfig.PLACE_PROPERTY_PACKAGE);
        String flakeId = propertyFileReader.readProperty("flakeId");
        String label = propertyFileReader.readProperty("label");
        travelPlaceApi.searchPlace("/" + flakeId + PlaceConfig.SEARCH_URL + "?label=" + label);
    }

    @Then("^Hotel A should be available on Place B (.*) Hotel searches$")
    public void hotelAShouldBeAvailableOnPlaceBHotelSearches(String PlaceNameB) {
        PlaceNameB = PlaceNameB.replaceAll("\"", "");
        PlaceNameB = propertyFileReader.readProperty((PlaceConfig.ID+PlaceNameB).trim());
        System.out.println("Place Name : "+PlaceNameB);
        travelHotelApi = new TravelHotelApi(getEnvironment().getEndpoints() + HotelConfig.HOTEL_URL);
        travelHotelApi.searchHotelByPlace(HotelConfig.SEARCH_URL, PlaceNameB);
    }

    @Then("^Hotel A should be available on Place A (.*) Hotel searches$")
    public void hotelAShouldBeAvailableOnPlaceAHotelSearches(String PlaceNameA) {
        PlaceNameA = PlaceNameA.replaceAll("\"", "");
        PlaceNameA = propertyFileReader.readProperty((PlaceConfig.ID+PlaceNameA).trim());
        System.out.println("Place Name : "+PlaceNameA);
        travelHotelApi = new TravelHotelApi(getEnvironment().getEndpoints() + HotelConfig.HOTEL_URL);
        travelHotelApi.searchHotelByPlace(HotelConfig.SEARCH_URL, PlaceNameA);
    }

    @When("^I create Hotel A with locations containing Place B \"([^\"]*)\"$")
    public void iCreateHotelAWithLocationsContainingPlaceB(String placeName) throws Throwable {
        try {
            placeName = placeName.replaceAll("\"", "");

            String id = propertyFileReader.readProperty((PlaceConfig.ID+placeName).trim());
            String label = propertyFileReader.readProperty((PlaceConfig.LABEL+placeName).trim());

            File file = ResourceUtils.getFile(HotelConfig.CREATE_HOTEL_JSON_PATH);
            String content = new String(Files.readAllBytes(file.toPath()));
            ObjectMapper mapper = new ObjectMapper();
            HotelVO hotel = mapper.readValue(content , HotelVO.class);
            System.out.println("id of place B - " + id);
            System.out.println("label of place B - " + label);
            hotel.getLocations().get(0).setId(id);
            hotel.getLocations().get(0).setLabel(label);
            content = mapper.writeValueAsString(hotel);
            travelHotelApi = new TravelHotelApi(getEnvironment().getEndpoints() + HotelConfig.HOTEL_URL);
            travelHotelApi.createHotelWithPlace("", content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Then("^I Delete Hotel A$")
    public void iDeleteHotelA() throws Throwable {
        File file = ResourceUtils.getFile(HotelConfig.CREATE_HOTEL_JSON_PATH);
        String content = new String(Files.readAllBytes(file.toPath()));
        ObjectMapper mapper = new ObjectMapper();
        HotelVO hotel = mapper.readValue(content , HotelVO.class);
        travelHotelApi = new TravelHotelApi(getEnvironment().getEndpoints() + HotelConfig.HOTEL_URL);
        travelHotelApi.deleteHotelByFlakeId("", hotel.getFlakeid());
    }

    @Then("^I Delete PlaceA and PlaceB and PlaceC and Hotel \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void iDeletePlaceAAndPlaceBAndPlaceCAndHotel(String place1, String place2, String place3) throws Throwable {
        travelPlaceApi.deletePlace("", place3);
        travelPlaceApi.deletePlace("", place2);
        travelPlaceApi.deletePlace("", place1);
    }

    @Then("^I create a Place \"([^\"]*)\"$")
    public void iCreateAPlace(String placeId) {
        placeId = placeId.replaceAll("\"", "");
        Locations location = new Locations();
        int random = TmgUtil.generateRandomNumber();
        location.setId(propertyFileReader.readProperty((PlaceConfig.ID+placeId)).trim());
        location.setLabel(propertyFileReader.readProperty((PlaceConfig.LABEL+placeId).trim()));
        location.setType(propertyFileReader.readProperty((PlaceConfig.TYPE+placeId).trim()));
        location.setLat(propertyFileReader.readProperty((PlaceConfig.LAT+placeId).trim()));
        location.setLong(propertyFileReader.readProperty((PlaceConfig.LONG+placeId).trim()));
        placeIdMap.put(location.getLabel(), location.getId());
        travelPlaceApi.createPlace("", location);
    }

    @Then("^I delete a Place \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void iDeleteAPlace(String place1, String place2, String place3) throws Throwable {
        place1 = place1.replace("\"","");
//        place2 = place2.replace("\"","");
//        place3 = place3.replace("\"","");

        travelPlaceApi.deletePlace("", propertyFileReader.readProperty((PlaceConfig.ID+place3).trim()));
        travelPlaceApi.deletePlace("", propertyFileReader.readProperty((PlaceConfig.ID+place2).trim()));
        travelPlaceApi.deletePlace("", propertyFileReader.readProperty((PlaceConfig.ID+place1).trim()));
    }

//
//    @When("^I add relationship between Place A and Place X, Place X as a parent of Place B$")
//    public void iAddRelationshipBetweenHotelsAndPlaces() {
//        TravelPlace travelPlace = new TravelPlace();
//        String placeId2 = propertyFileReader.readProperty("id2");
//        String placeId3 = propertyFileReader.readProperty("id3");
//        String addRelationEndpoint = propertyFileReader.readProperty(PlaceConfig.ADD_RELATION_PATH);
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
//        travelHotelApi = new TravelHotelApi(getEnvironment().getEndpoints() + HotelConfig.HOTEL_URL);
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
//        travelHotelApi = new TravelHotelApi(getEnvironment().getEndpoints() + HotelConfig.HOTEL_URL);
//        travelHotelApi.searchHotelByPlace("/search?annotations=", placeToSearch);
//
//    }


}

