package com.test.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Step;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By usernameInput = By.cssSelector("#user-name");
    private By passwordInput = By.cssSelector("#password");
    private By loginButton = By.cssSelector("#login-button");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Open login page")
    public void open() {
        driver.get("https://www.saucedemo.com/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
    }

    @Step("Login as user {username}")
    public void login(String username, String password) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput)).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput)).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }
}