package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ConfigLoader;
import utils.LocatorUtils;

import java.time.Duration;

public class CartPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final ConfigLoader config;
    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.config = ConfigLoader.getInstance();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(config.getExplicitWait()));
    }

    public String getProductName() {
       return LocatorUtils.getText("product.name.css",driver);
    }
    public String checkProductPrice() {
       return LocatorUtils.getText("product.price.css",driver);
    }

    public void clickOnCheckout() {
        LocatorUtils.clickOnLocator("checkout.id",wait);
    }

    public void clickOnContinueShopping() {
        LocatorUtils.clickOnLocator("continue.shopping.id",wait);
    }

    public void clickOnRemoveProduct() {
        LocatorUtils.clickOnLocator("product.removed.id",wait);
    }


}
