import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.LoginPage;

public class LoginSteps extends BaseTest {


    @Given("launch chrome browser")
    public void launchBrowser() {
        initializeDriver();
    }

    @Given("I am on the login page")
    public void goToLoginPage() {
        LoginPage loginpage = new LoginPage(driver);
        loginpage.navigateToLoginPage();
    }

    @When("I login with username {string} and password {string}")
    public void submitLogin(String username, String password) {
        LoginPage loginpage = new LoginPage(driver);
        loginpage.login(username, password);
    }


}