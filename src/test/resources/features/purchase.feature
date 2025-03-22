Feature: Purchase order Functionality
  As a user
  I want to be add items to the cart and validate the cart data
  Background:
    Given I am on the login page
    Given I login with username "standard_user" and password "secret_sauce"

  Scenario: Successful login with valid credentials
    Given user adds item to the cart
    Then user checks item is added to the cart
    When user clicks on cart icon
    Then user checks product name is "Sauce Labs Onesie"
    Then user checks product price is "$7.99"