package steps;
import test.java.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.LoginPage;


public class LoginSteps extends BaseTest {


    @Given("launch chrome browser")
    public void launchBrowser() {
        initializeDriver();
    }

    @Given("User is on the login page")
    public void goToLoginPage() {
        LoginPage loginpage = new LoginPage(getDriver());
        loginpage.navigateToLoginPage();
    }

    @When("User logins with username {string} and password {string}")
    public void submitLogin(String username, String password) {
        LoginPage loginpage = new LoginPage(getDriver());
        loginpage.login(username, password);
    }

    @Then("An error message is displayed that {string}")
    public void submitLogin(String errorMsg) {
        LoginPage loginpage = new LoginPage(getDriver());
        Assert.assertTrue(loginpage.isErrorMessageDisplayed(), "Error message should be displayed");
        Assert.assertEquals(loginpage.getErrorMessageText(),
                errorMsg);
    }


}