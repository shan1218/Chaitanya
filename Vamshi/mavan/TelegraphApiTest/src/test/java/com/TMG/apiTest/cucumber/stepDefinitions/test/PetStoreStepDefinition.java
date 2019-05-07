package com.TMG.apiTest.cucumber.stepDefinitions.test;

import com.TMG.apiTest.cucumber.api.test.PetStoreApiTest;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class PetStoreStepDefinition {

    PetStoreApiTest petStoreApiTest;

    @Given("^I access the pet by status$")
    public void iAccessThePetByStatus() {
        petStoreApiTest = new PetStoreApiTest();
        petStoreApiTest.findByStatus("available");
    }

    @Then("^I get all the data by status$")
    public void iGetAllTheDataByStatus() {
        String response = petStoreApiTest.getAllDataByStatus("available");
        System.out.println("Response : "+response);
    }
}
