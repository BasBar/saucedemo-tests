package com.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;

public class LoginPage {

    private WebDriver driver;
    private By usernameInput = By.cssSelector("#user-name");
    private By passwordInput = By.cssSelector("#password");
    private By loginButton = By.xpath("//input[@id='login-button']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Open login page")
    public void open() {
        driver.get("https://www.saucedemo.com/");
    }

    @Step("Login as user {username}")
    public void login(String username, String password) {
        driver.findElement(usernameInput).sendKeys(username);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).click();
    }
}