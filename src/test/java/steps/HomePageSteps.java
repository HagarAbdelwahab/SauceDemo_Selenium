package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import test.java.BaseTest;


public class HomePageSteps extends BaseTest{


    @Then("user clicks on menu")
    public void clickOnMenu() {
        HomePage home = new HomePage(getDriver());
        home.clickOnMenu();
    }

    @Then("user clicks on logout")
    public void logout() {
        HomePage home = new HomePage(getDriver());
        home.logout();
    }

    @Then("user adds item to the cart")
    public void addItemToCart() {
        HomePage home = new HomePage(getDriver());
        home.clickAddToCart();
    }

    @Then("user checks item is added to the cart")
    public void CheckItemAdded() {
        HomePage home = new HomePage(getDriver());

    }

    @When("user clicks on cart icon")
    public void ClickOnCart() {
        HomePage home = new HomePage(getDriver());
        home.clickOnCartIcon();
    }

}
