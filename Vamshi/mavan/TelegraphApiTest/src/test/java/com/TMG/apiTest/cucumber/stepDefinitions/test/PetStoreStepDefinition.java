package com.TMG.apiTest.cucumber.stepDefinitions.test;

import com.TMG.apiTest.cucumber.api.test.PetStoreApiTest;
import com.TMG.apiTest.helper.PropertyReader;
import com.TMG.apiTest.helper.StepDefinition;
import com.TMG.apiTest.helper.TmgUtil;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class PetStoreStepDefinition extends StepDefinition {

    PropertyReader propertyFileReader = null;

    PetStoreApiTest petStoreApiTest;

    @Given("^I access the pet by status \"([^\"]*)\"$")
    public void iAccessThePetByStatus(String fileName) {
        propertyFileReader = TmgUtil.loadPropertyFile(fileName, "petStore");
        String status = propertyFileReader.readProperty("status");
        petStoreApiTest = new PetStoreApiTest(getEnvironment().getEndpoints());
        petStoreApiTest.findByStatus(status);
    }

    @Then("^I get all the data by status$")
    public void iGetAllTheDataByStatus() {
        String response = petStoreApiTest.getAllDataByStatus("available");
        //System.out.println("Response : "+response);
    }
}
