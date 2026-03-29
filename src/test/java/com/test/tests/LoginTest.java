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

    @Story("Successful login")
    @DisplayName("User can login with valid credentials")
    @Test
    void shouldLoginSuccessfully() {

        setUp();

        initPages();
        login();

        assertTrue(inventoryPage.isLoaded());
    }
}

//assertTrue(false); // to demonstrate screenshot on failure uncomment this line, comment line above and run the test