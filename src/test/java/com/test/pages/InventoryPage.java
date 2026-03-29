package com.test.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Step;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // CSS selectors (best practice)
    private By items = By.cssSelector(".inventory_item_name");
    private By prices = By.cssSelector(".inventory_item_price");
    private By sortDropdown = By.cssSelector(".product_sort_container");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Sort products by: {value}")
    public void sortBy(String value) {
        WebElement dropdown = wait.until(
                ExpectedConditions.elementToBeClickable(sortDropdown)
        );
        Select select = new Select(dropdown);
        select.selectByVisibleText(value);
    }

    @Step("Get all product names")
    public List<String> getItemNames() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(items));

        return driver.findElements(items)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    @Step("Get all product prices")
    public List<Double> getItemPrices() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(prices));

        return driver.findElements(prices)
                .stream()
                .map(e -> Double.parseDouble(e.getText().replace("$", "")))
                .collect(Collectors.toList());
    }

    @Step("Check if inventory page is loaded")
    public boolean isLoaded() {
        try {
            wait.until(ExpectedConditions.urlContains("inventory"));
            wait.until(ExpectedConditions.visibilityOfElementLocated(items));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}