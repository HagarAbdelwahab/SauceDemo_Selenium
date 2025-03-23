# SauceDemo_Selenium

## Web Application Testing Framework

This repository contains an automated testing framework for a web application, focusing on login functionality and shopping cart operations. The framework is built using Java, Selenium/Cypress, and Cucumber for behavior-driven development (BDD).

## Project Structure

```
src/
├── main/
│   └── java/
│       ├── pages/
│       │   ├── CartPage.java
│       │   ├── HomePage.java
│       │   └── LoginPage.java
│       └── utils/
│           ├── ConfigLoader.java
│           └── LocatorUtils.java
├── resources/
│   ├── config.properties
│   └── locators.properties
└── test/
    ├── java/
    │   ├── steps/
    │   ├── BaseTest.java
    │   ├── CucumberTestRunner.java
    │   ├── LoginScenarios.java
    │   └── PurchaseScenarios.java
    └── resources/
        └── features/
            ├── login.feature
            └── purchase.feature
```

## Page Object Model

The framework uses the Page Object Model design pattern:
- **LoginPage.java**: Handles authentication-related operations
- **HomePage.java**: Manages navigation and product browsing
- **CartPage.java**: Controls shopping cart operations

## Configuration

Two properties files are used for configuration:
- **config.properties**: Contains environment configuration like browser settings, URLs, etc.
- **locators.properties**: Stores element locators for web page elements

## Test Features

### Login Functionality

The framework tests two login scenarios:

1. **Successful login with valid credentials**
   - Navigate to login page
   - Login with username "standard_user" and password "secret_sauce"
   - Verify successful login by accessing menu options
   - Test logout functionality

2. **Failed login with invalid credentials**
   - Attempt login with incorrect credentials
   - Verify error message: "Epic sadface: Username and password do not match any user in this system"

### Purchase Functionality

The purchase feature includes:

1. **Adding items to cart**
   - Login with valid credentials
   - Browse to a product
   - Add product to cart
   - Verify item count in cart
   - Remove item from cart
   - Verify cart is empty

2. **Cart verification**
   - Add "Sauce Labs Onesie" to cart (price: $7.99)
   - Verify product name and price in cart

## How to Run Tests

### Prerequisites
- Java JDK 8 or higher
- Maven
- Chrome browser
