package com.TMG.apiTest.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/featureFile"},
        glue = { "com.TMG.apiTest.cucumber.stepDefinitions" }
)
public class CucumberRunner {

}
