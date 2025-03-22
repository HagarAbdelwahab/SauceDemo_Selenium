import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pages.HomePage;
import pages.LoginPage;
import test.java.BaseTest;

public class LoginScenarios extends BaseTest {

    @Test(description = "Login successfully")
    @Description("")
    @Severity(SeverityLevel.CRITICAL)
    public void loginSuccessfully() {
        LoginPage loginpage = new LoginPage(getDriver());
        loginpage.navigateToLoginPage();
        loginpage.login("standard_user", "secret_sauce");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickOnMenu();
        homePage.logout();
    }

    @Test(description = "Login using Invalid Credentials")
    @Description("")
    @Severity(SeverityLevel.CRITICAL)
    public void loginUsingInvalidCredentials() {
        LoginPage loginpage = new LoginPage(getDriver());
        loginpage.login("standard_users", "secret_sauces");
        // verify an error msg
        Assert.assertTrue(loginpage.isErrorMessageDisplayed(), "Error message should be displayed");
        Assert.assertEquals(loginpage.getErrorMessageText(),
                "Epic sadface: Username and password do not match any user in this service");
    }

}
