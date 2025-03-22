Feature: Purchase order Functionality
  As a user I want to be add items to the cart and validate the cart data
  Background:
    Given User is on the login page
    Given User logins with username "standard_user" and password "secret_sauce"

  Scenario: user adds item to the cart and go to cart page
    Given user adds item to the cart
    Then user checks item is added to the cart
    When user clicks on cart icon
    Then user checks product name is "Sauce Labs Onesie"
    Then user checks product price is "$7.99"