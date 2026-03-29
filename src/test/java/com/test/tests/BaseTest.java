package com.test.tests;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.qameta.allure.Allure;

import com.test.pages.InventoryPage;
import com.test.pages.LoginPage;

public class BaseTest {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    protected LoginPage loginPage;
    protected InventoryPage inventoryPage;

    protected void setUp(String browser) {
        try {
            URL gridUrl = new URL("http://localhost:4444/wd/hub");

            WebDriver localDriver;

            switch (browser.toLowerCase()) {
                case "chrome":
                    localDriver = new RemoteWebDriver(gridUrl, new ChromeOptions());
                    break;
                case "edge":
                    localDriver = new RemoteWebDriver(gridUrl, new EdgeOptions());
                    break;
                default:
                    throw new RuntimeException("Unknown browser: " + browser);
            }

            localDriver.manage().timeouts().implicitlyWait(Duration.ZERO);
            localDriver.manage().window().maximize();

            driver.set(localDriver);

        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize driver", e);
        }
    }

    protected WebDriver getDriver() {
        WebDriver drv = driver.get();
        if (drv == null) {
            throw new RuntimeException("Driver not initialized!");
        }
        return drv;
    }

    protected void cleanUpDriver() {
        WebDriver drv = driver.get();

        if (drv != null) {
            try {
                drv.quit();
            } catch (Exception ignored) {}
            finally {
                driver.remove();
            }
        }
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
        try {
            WebDriver drv = driver.get();

            if (drv != null) {
                byte[] screenshot = ((TakesScreenshot) drv)
                        .getScreenshotAs(OutputType.BYTES);

                Allure.addAttachment(
                        "Screenshot",
                        new ByteArrayInputStream(screenshot)
                );
            }
        } catch (Exception ignored) {}
    }
}