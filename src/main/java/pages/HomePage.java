package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ConfigLoader;
import utils.LocatorUtils;

import java.time.Duration;

public class HomePage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final ConfigLoader config;
    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);


    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.config = ConfigLoader.getInstance();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(config.getExplicitWait()));
    }

    // scroll to the product
    public void scrollToProduct() {
        LocatorUtils.scrollToElement("product.sauce.xpath",driver,wait);
    }


    public void scrollToCart() {
        LocatorUtils.scrollToElement("shopping.cart.container.id",driver,wait);
    }

    public String getCartItems() {
        LocatorUtils.scrollToElement("shopping.cart.container.id",driver,wait);
        return LocatorUtils.getText("shopping.cart.container.id",driver);
    }


    public void clickAddToCart() {
        LocatorUtils.clickOnLocator("product.add.id",wait);
    }

    public void clickRemoveFromCart() {
        LocatorUtils.clickOnLocator("product.removed.id",wait);
    }


    public void clickOnCartIcon() {
        LocatorUtils.clickOnLocator("shopping.cart.container.id",wait);
    }

    public void clickOnMenu() {
        LocatorUtils.clickOnLocator("menu.id",wait);
    }

    public void logout() {
        LocatorUtils.clickOnLocator("logout.id",wait);
        logger.info("Logout completed successfully");
    }
}
