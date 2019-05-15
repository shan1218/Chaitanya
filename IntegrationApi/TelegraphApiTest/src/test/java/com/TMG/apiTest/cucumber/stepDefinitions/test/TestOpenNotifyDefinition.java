package com.TMG.apiTest.cucumber.stepDefinitions.test;

import com.TMG.apiTest.cucumber.api.test.TestOpenNotifyService;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TestOpenNotifyDefinition {

    TestOpenNotifyService service;

    @Given("^I access the ISS Current Location$")
    public void iAccessTheISSCurrentLocation() {
        service = new TestOpenNotifyService();
    }

    @When("^I retrieve the ISS Current Location$")
    public void iRetrieveTheISSCurrentLocation() {
        service.requestInternationalSpaceStationCurrentLocation();
    }

    @Then("^I see the ISS Current Location$")
    public void iSeeTheISSCurrentLocation() {
        service.validateInternationalSpaceStationCurrentLocationContents();
    }
}
