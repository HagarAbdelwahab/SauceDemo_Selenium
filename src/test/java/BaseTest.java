import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import utils.ConfigLoader;

import java.io.ByteArrayInputStream;
import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected ConfigLoader config;
    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    private static boolean isInitialized = false;


    @BeforeClass
    protected void initializeDriver() {
        if (!isInitialized) {
            try {
                config = ConfigLoader.getInstance();
                driver = createDriver();
                setupDriver();
                isInitialized = true;
            } catch (Exception e) {
                logger.error("Failed to setup test", e);
                throw e;
            }
        }
    }


    protected void cleanupDriver() {
        if (isInitialized && driver != null) {
            try {
                driver.quit();
                isInitialized = false;
            } catch (Exception e) {
                logger.error("Failed to quit driver", e);
            }
        }
    }

    @AfterMethod(alwaysRun = true)
    public void captureFailure(ITestResult result) {
        if (!result.isSuccess()) {
            captureScreenshot(result.getName());
        }
    }

    private WebDriver createDriver() {
        try {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();

            // Basic Chrome options
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--start-maximized");
            options.addArguments("--headless");
            options.addArguments("--window-size=1920,1080");

            // Disable unwanted features
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--disable-notifications");

            // Performance options
            options.addArguments("--disable-gpu");
            options.addArguments("--dns-prefetch-disable");

            // Additional options for stability
            options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
            options.setExperimentalOption("useAutomationExtension", false);

            logger.info("Creating Chrome WebDriver with configured options");
            return new ChromeDriver(options);
        } catch (Exception e) {
            logger.error("Failed to create WebDriver", e);
            throw e;
        }
    }


    private void setupDriver() {
        try {
            int implicitWait = config.getImplicitWait();
            int pageLoadTimeout = config.getPageLoadTimeout();
            int scriptTimeout = 30;

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTimeout));
            driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(scriptTimeout));

            logger.info("Driver setup completed with timeouts - Implicit: {}s, PageLoad: {}s, Script: {}s",
                    implicitWait, pageLoadTimeout, scriptTimeout);
        } catch (Exception e) {
            logger.error("Failed to setup driver timeouts", e);
            throw e;
        }
    }

    private void captureScreenshot(String testName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(testName + "_failure_screenshot", new ByteArrayInputStream(screenshot));
        } catch (Exception e) {
            logger.error("Failed to capture screenshot", e);
        }
    }

    protected WebDriver getDriver() {
        return driver;
    }
}
