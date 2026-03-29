package com.test.tests;

import com.test.pages.InventoryPage;

import io.cucumber.java.en.*;

import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.*;

public class SortingSteps extends BaseTest {

    private WebDriver driver;
    private InventoryPage inventoryPage;

    @Given("user logs into the application on {string}")
    public void user_logs_into_application(String browser) {
        System.out.println("THREAD: " + Thread.currentThread().getName() + " | BROWSER: " + browser);

        setUp(browser);
        driver = getDriver();

        driver.get("https://www.saucedemo.com/");

        initPages();
        login();

        inventoryPage = new InventoryPage(driver);
    }

    @When("user selects {string} sorting option")
    public void user_selects_sorting_option(String option) {
        inventoryPage.sortBy(option);
    }

    @Then("products should be sorted {string}")
    public void products_should_be_sorted(String order) {

        if (order.contains("name")) {
            verifySorting(inventoryPage.getItemNames(), order);
        } else if (order.contains("price")) {
            verifySorting(inventoryPage.getItemPrices(), order);
        } else {
            throw new RuntimeException("Unknown sorting type: " + order);
        }
    }

    private <T extends Comparable<T>> void verifySorting(List<T> actual, String order) {

        List<T> expected = new ArrayList<>(actual);

        if (order.contains("asc")) {
            Collections.sort(expected);
        } else if (order.contains("desc")) {
            expected.sort(Collections.reverseOrder());
        } else {
            throw new RuntimeException("Unknown order: " + order);
        }

        assertEquals(expected, actual, "Sorting is incorrect for order: " + order);
    }
}