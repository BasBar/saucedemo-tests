package com.test.tests;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@SelectClasspathResource("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.test.tests")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = 
        "pretty, summary, html:target/cucumber-report.html, json:target/cucumber.json, io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm")
public class CucumberTest {
}