Feature: Login Functionality
              As a user
              I want to be able to login to the application
  So that I can access my account
        Background:
            Given launch chrome browser

        Scenario: Successful login with valid credentials
            Given User is on the login page
             When User logins with username "standard_user" and password "secret_sauce"
             Then user clicks on menu
             Then user clicks on logout


        Scenario: Failed login with invalid credentials
             When User logins with username "admin" and password "admin"
             Then An error message is displayed that "Epic sadface: Username and password do not match any user in this service"