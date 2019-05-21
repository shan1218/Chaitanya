package com.TMG.apiTest.stepDefinitions.travelPlace;

import com.TMG.apiTest.api.VOs.HotelVO;
import com.TMG.apiTest.api.VOs.Locations;
import com.TMG.apiTest.api.service.place.TravelHotelApi;
import com.TMG.apiTest.api.service.place.TravelPlaceApi;
import com.TMG.apiTest.config.HotelConfig;
import com.TMG.apiTest.config.PlaceConfig;
import com.TMG.apiTest.helper.StepDefinition;
import com.TMG.apiTest.helper.PropertyReader;
import com.TMG.apiTest.helper.TmgUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    HashMap<String, String> placeIdMap = new HashMap<String, String>();

    @Given("^I Initialize Travel Page$")
    public void initializeTravelPage() {
        propertyFileReader = TmgUtil.loadPropertyFile(PlaceConfig.PLACE_PROPERTY_FILENAME, PlaceConfig.PLACE_PROPERTY_PACKAGE);
        propertyFilePath = TmgUtil.getPropertyFilePath(PlaceConfig.PLACE_PROPERTY_FILENAME, PlaceConfig.PLACE_PROPERTY_PACKAGE);
        placeIncrementer = Integer.parseInt(propertyFileReader.readProperty(PlaceConfig.PLACE_INCREMENTER));
        propertyFileReader.setProperty(PlaceConfig.PLACE_INCREMENTER, (placeIncrementer + 1) + "", propertyFilePath);
        travelPlaceApi = new TravelPlaceApi(getEnvironment().getEndpoints() + PlaceConfig.PLACE_URL);
    }

    @Then("^I create a Place \"([^\"]*)\" with type \"([^\"]*)\" lat \"([^\"]*)\" and long \"([^\"]*)\"$")
    public void createPlace(String placeName, String type, String latitude, String longitude) {
        Locations location = new Locations();
        int random = TmgUtil.generateRandomNumber();
        location.setId("" + placeIncrementer);
        location.setLabel(placeName);
        location.setType(type);
        location.setLat(latitude);
        location.setLong(longitude);
        placeIdMap.put(location.getLabel(), location.getId());
        travelPlaceApi.createPlace("", location);
    }

    @When("^I add relationship between First Place A (.*) and Second Place B (.*), Place A as the parent of Place B$")
    public void addRelationshipBetweenFirstPlaceAAndSecondPlaceBPlaceAAsTheParentOfPlaceB(String placeNameA, String placeNameB) throws Exception {
        placeNameA = placeNameA.replaceAll("\"", "");
        placeNameB = placeNameB.replaceAll("\"", "");
        String placeIdA = propertyFileReader.readProperty((PlaceConfig.ID + placeNameA).trim());
        String placeIdB = propertyFileReader.readProperty((PlaceConfig.ID + placeNameB).trim());
        System.out.println("\n" + placeIdA + " : " + placeIdB);
        Thread.sleep(3000);
        travelPlaceApi.addRelationBetweenPlaces(PlaceConfig.ADD_RELATION_URL, placeIdA, placeIdB);

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
        PlaceNameB = propertyFileReader.readProperty((PlaceConfig.ID + PlaceNameB).trim());
        System.out.println("Place Name : " + PlaceNameB);
        travelHotelApi = new TravelHotelApi(getEnvironment().getEndpoints() + HotelConfig.HOTEL_URL);
        travelHotelApi.searchHotelByPlace(HotelConfig.SEARCH_URL, PlaceNameB);
    }

    @Then("^Hotel A should be available on Place A (.*) Hotel searches$")
    public void hotelAShouldBeAvailableOnPlaceAHotelSearches(String PlaceNameA) {
        PlaceNameA = PlaceNameA.replaceAll("\"", "");
        PlaceNameA = propertyFileReader.readProperty((PlaceConfig.ID + PlaceNameA).trim());
        System.out.println("Place Name : " + PlaceNameA);
        travelHotelApi = new TravelHotelApi(getEnvironment().getEndpoints() + HotelConfig.HOTEL_URL);
        travelHotelApi.searchHotelByPlace(HotelConfig.SEARCH_URL, PlaceNameA);
    }

    @When("^I create Hotel A with locations containing Place B \"([^\"]*)\"$")
    public void iCreateHotelAWithLocationsContainingPlaceB(String placeName) throws Throwable {
        try {
            placeName = placeName.replaceAll("\"", "");

            String id = propertyFileReader.readProperty((PlaceConfig.ID + placeName).trim());
            String label = propertyFileReader.readProperty((PlaceConfig.LABEL + placeName).trim());

            File file = ResourceUtils.getFile(HotelConfig.CREATE_HOTEL_JSON_PATH);
            String content = new String(Files.readAllBytes(file.toPath()));
            ObjectMapper mapper = new ObjectMapper();
            HotelVO hotel = mapper.readValue(content, HotelVO.class);
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
        HotelVO hotel = mapper.readValue(content, HotelVO.class);
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
        location.setId(propertyFileReader.readProperty((PlaceConfig.ID + placeId)).trim());
        location.setLabel(propertyFileReader.readProperty((PlaceConfig.LABEL + placeId).trim()));
        location.setType(propertyFileReader.readProperty((PlaceConfig.TYPE + placeId).trim()));
        location.setLat(propertyFileReader.readProperty((PlaceConfig.LAT + placeId).trim()));
        location.setLong(propertyFileReader.readProperty((PlaceConfig.LONG + placeId).trim()));
        placeIdMap.put(location.getLabel(), location.getId());
        travelPlaceApi.createPlace("", location);
    }

    @Then("^I delete a Place \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void iDeleteAPlace(String place1, String place2, String place3) throws Throwable {
        place1 = place1.replace("\"", "");
        travelPlaceApi.deletePlace("", propertyFileReader.readProperty((PlaceConfig.ID + place3).trim()));
        travelPlaceApi.deletePlace("", propertyFileReader.readProperty((PlaceConfig.ID + place2).trim()));
        travelPlaceApi.deletePlace("", propertyFileReader.readProperty((PlaceConfig.ID + place1).trim()));
    }

    @When("^I add relationship between Place A \"([^\"]*)\" and Third Place C \"([^\"]*)\", Place C as a parent of Place B$")
    public void iAddRelationshipBetweenPlaceAAndPlaceCPlaceCAsAParentOfPlaceB(String placeNameC, String placeNameB) throws Throwable {
        placeNameB = placeNameB.replaceAll("\"", "");
        placeNameC = placeNameC.replaceAll("\"", "");
        String placeIdB = propertyFileReader.readProperty((PlaceConfig.ID + placeNameB).trim());
        String placeIdC = propertyFileReader.readProperty((PlaceConfig.ID + placeNameC).trim());
        System.out.println("\n" + placeIdB + " : " + placeIdC);
        Thread.sleep(3000);
        travelPlaceApi.addRelationBetweenMultiplePlaces(PlaceConfig.ADD_RELATION_URL, placeIdB, placeIdC);

    }

    @Then("^Hotel A should be available on Place C (.*)Hotel searches$")
    public void hotelAShouldBeAvailableOnPlaceCHotelSearches(String PlaceNameC) {
        PlaceNameC = PlaceNameC.replaceAll("\"", "");
        PlaceNameC = propertyFileReader.readProperty((PlaceConfig.ID + PlaceNameC).trim());
        System.out.println("Place Name : " + PlaceNameC);
        travelHotelApi = new TravelHotelApi(getEnvironment().getEndpoints() + HotelConfig.HOTEL_URL);
        travelHotelApi.searchHotelByPlace(HotelConfig.SEARCH_URL, PlaceNameC);

    }

    @When("^I remove relationship between Place A \"([^\"]*)\" and Third Place C \"([^\"]*)\", remove Place C as a parent of Place B$")
    public void iRemoveRelationshipBetweenPlaceAAndThirdPlaceCRemovePlaceCAsAParentOfPlaceB(String placeNameC, String placeNameB) throws Throwable {

        placeNameC = placeNameC.replaceAll("\"", "");
        placeNameB = placeNameB.replaceAll("\"", "");
        String placeIdB = propertyFileReader.readProperty((PlaceConfig.ID + placeNameB).trim());
        String placeIdC = propertyFileReader.readProperty((PlaceConfig.ID + placeNameC).trim());
        System.out.println("\n" + placeIdB + " : " + placeIdC);
        Thread.sleep(3000);
        travelPlaceApi.removeRelationBetweenMultiplePlaces(PlaceConfig.REMOVE_RELATION_URL, placeIdB, placeIdC);

    }

    @Then("^Hotel A should not be available on Place C (.*) Hotel searches$")
    public void hotelAShouldNotBeAvailableOnPlaceCHotelSearches(String PlaceNameC) throws Throwable {

        PlaceNameC = PlaceNameC.replaceAll("\"", "");
        PlaceNameC = propertyFileReader.readProperty((PlaceConfig.ID + PlaceNameC).trim());
        System.out.println("Place Name : " + PlaceNameC);
        travelHotelApi = new TravelHotelApi(getEnvironment().getEndpoints() + HotelConfig.HOTEL_URL);
        travelHotelApi.searchHotelByPlace(HotelConfig.SEARCH_URL, PlaceNameC);

    }


}

