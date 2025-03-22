package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Properties;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ConfigLoader;
import utils.LocatorUtils;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final ConfigLoader config;
    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.config = ConfigLoader.getInstance();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(config.getExplicitWait()));
    }


    /**
     * Navigates to the login page and waits for it to load
     */
    public void navigateToLoginPage() {
        try {
            driver.get(config.getBaseUrl() );
            logger.info("Navigated to login page");
        } catch (Exception e) {
            logger.error("Failed to navigate to login page", e);
            throw e;
        }
    }

    /**
     * Performs login with the specified credentials
     *
     * @param username The username to login with
     * @param password The password to login with
     */
    public void login(String username, String password) {
        logger.info("Performing login with username: {}", username);
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
        waitForPageLoad();
        logger.info("Login completed successfully");
    }



    /**
     * Waits for the page to complete loading
     */
    public void waitForPageLoad() {
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState")
                .equals("complete"));
    }

    public void enterUsername(String username) {
        try {
            WebElement usernameField = wait.until(
                    ExpectedConditions.presenceOfElementLocated(LocatorUtils.getLocator("login.username.id")));
            usernameField.clear();
            usernameField.sendKeys(username);
            logger.info("Entered username: {}", username);
        } catch (Exception e) {
            logger.error("Failed to enter username", e);
            throw e;
        }
    }

    public void enterPassword(String password) {
        try {
            WebElement passwordField = wait.until(
                    ExpectedConditions.presenceOfElementLocated(LocatorUtils.getLocator("login.password.id")));
            passwordField.clear();
            passwordField.sendKeys(password);
            logger.info("Entered password");
        } catch (Exception e) {
            logger.error("Failed to enter password", e);
            throw e;
        }
    }

    public void clickLoginButton() {
        try {
            WebElement loginButton = wait.until(
                    ExpectedConditions.elementToBeClickable(LocatorUtils.getLocator("login.button.id")));
            loginButton.click();
            logger.info("Clicked login button");
        } catch (Exception e) {
            logger.error("Failed to click login button", e);
            throw e;
        }
    }


    public boolean isErrorMessageDisplayed() {
        try {
            WebElement errorElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector("[data-test='error']")));
            return true;
        } catch (Exception e) {
            logger.warn("Error message was not displayed");
            return false;
        }
    }

    public String getErrorMessageText() {
        try {
            WebElement errorElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector("[data-test='error']")));
            return errorElement.getText();
        } catch (Exception e) {
            logger.error("Failed to get error message text", e);
            throw e;
        }
    }




}
