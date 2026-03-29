# SauceDemo Test Automation Framework

## Project Overview

This project is a test automation framework for the SauceDemo application:
https://www.saucedemo.com/

It is built using Java, Selenium, JUnit 5 and Maven with support for:

* parallel execution
* Selenium Grid
* Allure reporting

---

## Tech Stack

* Java 17
* Maven
* Selenium 4
* JUnit 5
* Selenium Grid (Docker)
* Allure Reports

---

## Features

* Page Object Model (POM)
* Parallel test execution (JUnit 5)
* Cross-browser testing (Chrome and Edge)
* Selenium Grid support
* Allure reporting with screenshots on failure
* ThreadLocal WebDriver

---

## Running Tests

```
mvn clean test
```

---

## Selenium Grid (Docker)

```
docker network create grid

docker run -d -p 4444:4444 --net grid --name selenium-hub selenium/hub

docker run -d --net grid --name chrome-node ^
-e SE_EVENT_BUS_HOST=selenium-hub ^
-e SE_EVENT_BUS_PUBLISH_PORT=4442 ^
-e SE_EVENT_BUS_SUBSCRIBE_PORT=4443 ^
selenium/node-chrome

docker run -d --net grid --name edge-node ^
-e SE_EVENT_BUS_HOST=selenium-hub ^
-e SE_EVENT_BUS_PUBLISH_PORT=4442 ^
-e SE_EVENT_BUS_SUBSCRIBE_PORT=4443 ^
selenium/node-edge
```

Grid UI: http://localhost:4444

---

## Allure Report

```
allure serve target/allure-results
```
