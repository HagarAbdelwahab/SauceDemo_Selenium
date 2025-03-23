
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pages.HomePage;
import pages.LoginPage;
import test.java.BaseTest;

public class LoginScenarios extends BaseTest {

    @AfterMethod
    void tearDown(ITestResult result) {
        // Only run after the loginSuccessfully test
        if (result.getMethod().getMethodName().equals("loginSuccessfully")) {
            HomePage homePage = new HomePage(getDriver());
            homePage.clickOnMenu();
            homePage.logout();
        }
    }

    @Test(description = "Login successfully")
    @Description("Check user can be logged in successfully using valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    public void loginSuccessfully() {
        LoginPage loginpage = new LoginPage(getDriver());
        loginpage.navigateToLoginPage();
        loginpage.login(config.getProperty("valid.username"), config.getProperty("valid.password"));
        HomePage homePage = new HomePage(getDriver());
        // check user is on the home page an error msg
        Assert.assertTrue(homePage.checkProductsAreVisible());

    }

    @Test(description = "Login using Invalid Credentials")
    @Description("Check user can not be logged in using invalid credentials and an error msg is displayed")
    @Severity(SeverityLevel.CRITICAL)
    public void loginUsingInvalidCredentials() {
        LoginPage loginpage = new LoginPage(getDriver());
        loginpage.navigateToLoginPage();
        loginpage.login(config.getProperty("invalid.username"), config.getProperty("valid.password"));
        // verify an error msg
        Assert.assertEquals(loginpage.getErrorMessageText(),
                "Epic sadface: Username and password do not match any user in this service");
    }

    @Test(description = "Logout successfully")
    @Description("Check user can logout successfully")
    @Severity(SeverityLevel.CRITICAL)
    public void logoutSuccessfully() {
        LoginPage loginpage = new LoginPage(getDriver());
        loginpage.navigateToLoginPage();
        loginpage.login(config.getProperty("valid.username"), config.getProperty("valid.password"));
        HomePage homePage = new HomePage(getDriver());
        homePage.clickOnMenu();
        homePage.logout();
        // check the login form is displayed
        Assert.assertTrue(loginpage.isOnLoginPage());
    }

}
