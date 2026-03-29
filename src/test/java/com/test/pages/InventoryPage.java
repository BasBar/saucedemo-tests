package com.test.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import io.qameta.allure.Step;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryPage {

    private WebDriver driver;
    private By items = By.cssSelector(".inventory_item_name");
    private By prices = By.cssSelector(".inventory_item_price");
    private By sortDropdown = By.cssSelector(".product_sort_container");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Sort products by: {value}")
    public void sortBy(String value) {
        Select select = new Select(driver.findElement(sortDropdown));
        select.selectByVisibleText(value);
    }

    @Step("Get all product names")
    public List<String> getItemNames() {
        return driver.findElements(items)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    @Step("Get all product prices")
    public List<Double> getItemPrices() {
        return driver.findElements(prices)
                .stream()
                .map(e -> Double.parseDouble(e.getText().replace("$", "")))
                .collect(Collectors.toList());
    }

    @Step("Check if inventory page is loaded")
    public boolean isLoaded() {
        return driver.getCurrentUrl().contains("inventory");
    }
}
