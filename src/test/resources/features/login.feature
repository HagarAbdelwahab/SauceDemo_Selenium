Feature: Login Functionality
              As a user
              I want to be able to login to the application
  So that I can access my account
        Background:
            Given launch chrome browser

        Scenario: Successful login with valid credentials
            Given I am on the login page
             When I login with username "standard_user" and password "secret_sauce"
             Then click on menu
             Then click on logout


#        Scenario: Failed login with invalid credentials
#             When I login with username "admin" and password "admin"