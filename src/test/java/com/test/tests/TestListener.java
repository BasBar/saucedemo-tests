package com.test.tests;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

public class TestListener implements TestExecutionExceptionHandler {

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {

        String testName = context.getDisplayName();
        String thread = Thread.currentThread().getName();

        System.out.println(" TEST FAILED: " + testName + " | Thread: " + thread);

        try {
            BaseTest.takeScreenshot();
        } catch (Exception e) {
            System.out.println("Could not take screenshot: " + e.getMessage());
        }

        throw throwable;
    }
}