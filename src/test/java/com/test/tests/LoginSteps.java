package com.test.tests;

import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps extends BaseTest {

    @Given("user opens application on {string}")
    public void user_opens_application(String browser) {
        setUp(browser);
        initPages();
        loginPage.open();
    }

    @When("user logs in with valid credentials")
    public void user_logs_in() {
        login();
    }

    @Then("inventory page is displayed")
    public void inventory_page_is_displayed() {
        assertTrue(inventoryPage.isLoaded(),
                "Inventory page should be visible after login");
    }
}