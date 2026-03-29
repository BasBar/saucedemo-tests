package com.test.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.qameta.allure.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Login")
@Feature("Authentication")
@ExtendWith(TestListener.class)
public class LoginTest extends BaseTest {

    @Test
    @Story("Successful login")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Login - Chrome")
    void shouldLoginChrome() {

        setUp("chrome");

        initPages();
        login();

        assertTrue(inventoryPage.isLoaded(),
                "Inventory page should be visible after login (Chrome)");
    }

    @Test
    @Story("Successful login")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Login - Edge")
    void shouldLoginEdge() {

        setUp("edge");

        initPages();
        login();

        assertTrue(inventoryPage.isLoaded(),
                "Inventory page should be visible after login (Edge)");
    }
}