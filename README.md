# SauceDemo Test Automation Framework

## Overview

This project is a test automation framework for the SauceDemo application.
It verifies product sorting functionality and login flow using modern automation practices.

The framework is built with Selenium, Cucumber (BDD), JUnit 5, and Allure, and supports parallel execution on multiple browsers via Selenium Grid.

---

## Tech Stack

* Java 17
* Selenium WebDriver
* Cucumber (BDD)
* JUnit 5
* Allure Reports
* Docker + Selenium Grid
* Maven

---

## Features

* Cross-browser testing (Chrome, Edge)
* Parallel execution using ThreadLocal
* Selenium Grid integration (Docker)
* Page Object Model (POM)
* BDD scenarios (Cucumber)
* Allure reporting
* Automatic screenshots on failure
* Explicit waits for stability

---

## Test Scenarios

### Sorting tests:

* Sort by Name (A → Z)
* Sort by Name (Z → A)
* Sort by Price (Low → High)
* Sort by Price (High → Low)

### Login tests:

* Successful login on Chrome
* Successful login on Edge

---

## Project Structure

```id="p6r3kx"
src
 ├── main
 │   └── java
 │       └── pages
 ├── test
 │   ├── java
 │   │   └── tests
 │   └── resources
 │       └── features
```

---

## Running Tests (Selenium Grid)

### Start Selenium Grid

```bash id="3y9q2x"
docker-compose up -d
```

### Verify Grid

Open:
http://localhost:4444

---

## Run Tests

```bash id="7a2c5n"
mvn clean test
```

---

## Allure Report

```bash id="9f4d1m"
allure serve allure-results
```

---

## Design Decisions

* ThreadLocal WebDriver enables safe parallel execution
* Page Object Model improves maintainability
* Cucumber improves readability and business alignment
* Selenium Grid enables scalable cross-browser execution
* Allure provides clear reporting and debugging support
