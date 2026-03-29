package com.test.tests;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.qameta.allure.Allure;

import com.test.pages.LoginPage;
import com.test.pages.InventoryPage;

public class BaseTest {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    protected LoginPage loginPage;
    protected InventoryPage inventoryPage;

    // ✅ FIX 1: zawsze twórz NOWY driver dla każdego scenariusza
    protected void setUp(String browser) {
        try {
            URL gridUrl = new URL("http://localhost:4444/wd/hub");

            WebDriver localDriver;

            switch (browser.toLowerCase()) {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    localDriver = new RemoteWebDriver(gridUrl, chromeOptions);
                    break;

                case "edge":
                    EdgeOptions edgeOptions = new EdgeOptions();
                    localDriver = new RemoteWebDriver(gridUrl, edgeOptions);
                    break;

                default:
                    throw new RuntimeException("Unknown browser: " + browser);
            }

            // ✅ Allure info
            Allure.label("browser", browser);
            Allure.parameter("Browser", browser);

            localDriver.manage().window().maximize();

            // ✅ FIX 2: ważne dla stabilności
            localDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

            driver.set(localDriver);

        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize driver", e);
        }
    }

    protected WebDriver getDriver() {
        if (driver.get() == null) {
            throw new RuntimeException("Driver is not initialized. Did you forget to call setUp()?");
        }
        return driver.get();
    }

    protected void initPages() {
        loginPage = new LoginPage(getDriver());
        inventoryPage = new InventoryPage(getDriver());
    }

    // ✅ FIX 3: usuwamy hardcode (łatwo później podmienić na config)
    protected void login() {
        loginPage.open();
        loginPage.login(
                System.getProperty("username", "standard_user"),
                System.getProperty("password", "secret_sauce")
        );
    }

    // ✅ FIX 4: bezpieczny screenshot (nie wywali testów)
    public static void takeScreenshot() {
        try {
            if (driver.get() != null) {
                byte[] screenshot = ((TakesScreenshot) driver.get())
                        .getScreenshotAs(OutputType.BYTES);

                Allure.addAttachment(
                        "Screenshot",
                        new ByteArrayInputStream(screenshot)
                );
            }
        } catch (Exception e) {
            System.out.println("Failed to take screenshot: " + e.getMessage());
        }
    }

    @AfterEach
    void tearDown(TestInfo testInfo) {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}