package com.test.tests;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;

public class Hooks extends BaseTest {

    @After
    public void afterScenario(Scenario scenario) {

        if (scenario.isFailed()) {
            takeScreenshot();
        }

        cleanUpDriver();
    }
}