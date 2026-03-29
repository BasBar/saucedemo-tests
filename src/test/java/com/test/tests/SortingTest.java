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

    // ====== TESTY ======

    @Test
    @Story("Sort name ASC")
    @DisplayName("Sort name ASC - Chrome")
    void sortNameAscChrome() {
        setUp("chrome");
        prepareTest();
        verifyNameAsc();
    }

    @Test
    @Story("Sort name ASC")
    @DisplayName("Sort name ASC - Edge")
    void sortNameAscEdge() {
        setUp("edge");
        prepareTest();
        verifyNameAsc();
    }

    @Test
    @Story("Sort name DESC")
    @DisplayName("Sort name DESC - Chrome")
    void sortNameDescChrome() {
        setUp("chrome");
        prepareTest();
        verifyNameDesc();
    }

    @Test
    @Story("Sort name DESC")
    @DisplayName("Sort name DESC - Edge")
    void sortNameDescEdge() {
        setUp("edge");
        prepareTest();
        verifyNameDesc();
    }

    @Test
    @Story("Sort price ASC")
    @DisplayName("Sort price ASC - Chrome")
    void sortPriceAscChrome() {
        setUp("chrome");
        prepareTest();
        verifyPriceAsc();
    }

    @Test
    @Story("Sort price ASC")
    @DisplayName("Sort price ASC - Edge")
    void sortPriceAscEdge() {
        setUp("edge");
        prepareTest();
        verifyPriceAsc();
    }

    @Test
    @Story("Sort price DESC")
    @DisplayName("Sort price DESC - Chrome")
    void sortPriceDescChrome() {
        setUp("chrome");
        prepareTest();
        verifyPriceDesc();
    }

    @Test
    @Story("Sort price DESC")
    @DisplayName("Sort price DESC - Edge")
    void sortPriceDescEdge() {
        setUp("edge");
        prepareTest();
        verifyPriceDesc();
    }

    // ====== COMMON SETUP ======

    private void prepareTest() {
        initPages();
        login();
    }

    // ====== ASSERTIONS ======

    private void verifyNameAsc() {
        inventoryPage.sortBy("Name (A to Z)");

        List<String> actual = inventoryPage.getItemNames();
        List<String> expected = new ArrayList<>(actual);
        Collections.sort(expected);

        assertEquals(expected, actual);
    }

    private void verifyNameDesc() {
        inventoryPage.sortBy("Name (Z to A)");

        List<String> actual = inventoryPage.getItemNames();
        List<String> expected = new ArrayList<>(actual);
        expected.sort(Collections.reverseOrder());

        assertEquals(expected, actual);
    }

    private void verifyPriceAsc() {
        inventoryPage.sortBy("Price (low to high)");

        List<Double> actual = inventoryPage.getItemPrices();
        List<Double> expected = new ArrayList<>(actual);
        Collections.sort(expected);

        assertEquals(expected, actual);
    }

    private void verifyPriceDesc() {
        inventoryPage.sortBy("Price (high to low)");

        List<Double> actual = inventoryPage.getItemPrices();
        List<Double> expected = new ArrayList<>(actual);
        expected.sort(Collections.reverseOrder());

        assertEquals(expected, actual);
    }
}