package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Driver;
import java.util.Properties;

public class LocatorUtils {
    private static final Logger logger = LoggerFactory.getLogger(LocatorUtils.class);
    private static Properties locators;



    /**
     * Loads locators from properties file
     * @return Properties object containing locators
     */
    public static Properties loadLocators() {
        if (locators == null) {
            try {
                locators = new Properties();
                locators.load(LocatorUtils.class.getClassLoader().getResourceAsStream("locators.properties"));
                logger.info("Locators loaded successfully");
                return locators;
            } catch (Exception e) {
                logger.error("Failed to load locators.properties", e);
                throw new RuntimeException("Failed to load locators", e);
            }
        }
        return locators;
    }

    /**
     * Gets a By locator based on the key from properties file
     * @param key The key in the properties file
     * @return By locator
     */
    public static By getLocator(String key) {
        if (locators == null) {
            loadLocators();
        }

        String locator = locators.getProperty(key);
        if (locator == null) {
            throw new IllegalArgumentException("Locator not found for key: " + key);
        }

        if (key.endsWith(".id")) {
            return By.id(locator);
        } else if (key.endsWith(".css")) {
            return By.cssSelector(locator);
        } else if (key.endsWith(".xpath")) {
            return By.xpath(locator);
        } else if (key.endsWith(".class")) {
            return By.className(locator);
        }

        throw new IllegalArgumentException("Unknown locator type for key: " + key);
    }

    public static void scrollToElement(String locator, WebDriver driver, WebDriverWait wait) {
        WebElement element= wait.until(
                ExpectedConditions.presenceOfElementLocated(LocatorUtils.getLocator(locator)));
        // Method 1: Using JavaScript Executor to scroll the element into view
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static String getText(String locator, WebDriver driver) {
        WebElement badge = driver.findElement(LocatorUtils.getLocator(locator));
        return badge.getText();
    }

    public static void clickOnLocator(String locator, WebDriverWait wait) {
        try {
            WebElement loginButton = wait.until(
                    ExpectedConditions.elementToBeClickable(LocatorUtils.getLocator(locator)));
            loginButton.click();
            logger.info("Clicked on Add to Cart");
        } catch (Exception e) {
            logger.error("Failed to click on Add to Cart button", e);
            throw e;
        }
    }





}