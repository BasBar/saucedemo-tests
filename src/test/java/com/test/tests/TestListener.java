package com.test.tests;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

import io.qameta.allure.Allure;

public class TestListener implements TestExecutionExceptionHandler {

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {

        String testName = context.getDisplayName();
        String thread = Thread.currentThread().getName();

        System.err.println(" TEST FAILED: " + testName + " | Thread: " + thread);
        System.err.println("Reason: " + throwable.getMessage());

        Allure.step("Test failed: " + testName);
        Allure.step("Thread: " + thread);

        try {
            BaseTest.takeScreenshot();
        } catch (Exception e) {
            System.err.println("⚠️ Could not take screenshot: " + e.getMessage());
        }

        throw throwable;
    }
}