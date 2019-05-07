package com.TMG.apiTest.helper;

import com.TMG.apiTest.page.PetStorePage;
import com.TMG.apiTest.page.TravelPlacePage;
import com.TMG.apiTest.vo.Environment;

public class Application {

    private Environment environment;

    private PetStorePage petStorePage;

    private TravelPlacePage travelPlacePage;

    public Application(Environment environment) {
        this.environment = environment;
    }

    public void initialize() {

        petStorePage = new PetStorePage();
        travelPlacePage = new TravelPlacePage();
    }
}
