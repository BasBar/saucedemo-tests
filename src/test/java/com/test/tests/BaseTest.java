package com.test.tests;

import java.io.ByteArrayInputStream;
import java.net.URL;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.qameta.allure.Allure;

import com.test.pages.LoginPage;
import com.test.pages.InventoryPage;

public class BaseTest {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    protected LoginPage loginPage;
    protected InventoryPage inventoryPage;

    protected void setUp() {
        String browser = System.getProperty("browser", "chrome");

        try {
            URL gridUrl = new URL("http://localhost:4444/wd/hub");

            WebDriver localDriver;

            if (browser.equalsIgnoreCase("chrome")) {
                ChromeOptions options = new ChromeOptions();
                localDriver = new RemoteWebDriver(gridUrl, options);

            } else if (browser.equalsIgnoreCase("edge")) {
                EdgeOptions options = new EdgeOptions();
                localDriver = new RemoteWebDriver(gridUrl, options);

            } else {
                throw new RuntimeException("Unknown browser: " + browser);
            }

            localDriver.manage().window().maximize();
            driver.set(localDriver);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected WebDriver getDriver() {
        return driver.get();
    }

    protected void initPages() {
        loginPage = new LoginPage(getDriver());
        inventoryPage = new InventoryPage(getDriver());
    }

    protected void login() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
    }

    public static void takeScreenshot() {
        byte[] screenshot = ((TakesScreenshot) driver.get())
                .getScreenshotAs(OutputType.BYTES);

        Allure.addAttachment(
                "Screenshot on failure",
                new ByteArrayInputStream(screenshot)
        );
    }

    @AfterEach
    void tearDown(TestInfo testInfo) {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }
}