package com.test.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.qameta.allure.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Inventory")
@Feature("Sorting")
@ExtendWith(TestListener.class)
public class SortingTest extends BaseTest {

    @Story("Sort products by name ascending")
    @DisplayName("Sort products by name ascending")
    @Test
    void shouldSortByNameAscending() {

        setUp();

        initPages();
        login();

        inventoryPage.sortBy("Name (A to Z)");

        List<String> actual = inventoryPage.getItemNames();
        List<String> expected = new ArrayList<>(actual);
        Collections.sort(expected);

        assertEquals(expected, actual);
    }

    @Story("Sort products by name descending")
    @DisplayName("Sort products by name descending")
    @Test
    void shouldSortByNameDescending() {

        setUp();

        initPages();
        login();

        inventoryPage.sortBy("Name (Z to A)");

        List<String> actual = inventoryPage.getItemNames();
        List<String> expected = new ArrayList<>(actual);
        expected.sort(Collections.reverseOrder());

        assertEquals(expected, actual);
    }

    @Story("Sort products by price ascending")
    @DisplayName("Sort products by price ascending")
    @Test
    void shouldSortByPriceAscending() {

        setUp();

        initPages();
        login();

        inventoryPage.sortBy("Price (low to high)");

        List<Double> actual = inventoryPage.getItemPrices();
        List<Double> expected = new ArrayList<>(actual);
        Collections.sort(expected);

        assertEquals(expected, actual);
    }

    @Story("Sort products by price descending")
    @DisplayName("Sort products by price descending")
    @Test
    void shouldSortByPriceDescending() {

        setUp();

        initPages();
        login();

        inventoryPage.sortBy("Price (high to low)");

        List<Double> actual = inventoryPage.getItemPrices();
        List<Double> expected = new ArrayList<>(actual);
        expected.sort(Collections.reverseOrder());

        assertEquals(expected, actual);
    }
}