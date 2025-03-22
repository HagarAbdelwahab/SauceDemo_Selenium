import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;


public class PurchaseScenarios extends BaseTest {


    @BeforeClass()
    public void Setup() {
        LoginPage loginpage = new LoginPage(driver);
        loginpage.navigateToLoginPage();
        loginpage.login("standard_user","secret_sauce");
    }
    @Test(description = "Purchase an Item")
    @Description("")
    @Severity(SeverityLevel.CRITICAL)
    public void addItemToCart() {
        HomePage homePage = new HomePage(driver);
        homePage.scrollToProduct();
        homePage.clickAddToCart();
        homePage.scrollToCart();
        Assert.assertEquals(homePage.getCartItems(),"1");
    }


    @Test(description = "Remove item from Cart")
    @Description("")
    @Severity(SeverityLevel.CRITICAL)
    public void removeItemFromCart() {
        HomePage homePage = new HomePage(driver);
        homePage.scrollToProduct();
        homePage.clickAddToCart();
        homePage.scrollToCart();
        Assert.assertEquals(homePage.getCartItems(),"1");
        homePage.scrollToProduct();
        homePage.clickRemoveFromCart();
        homePage.scrollToCart();
        Assert.assertEquals(homePage.getCartItems(),"");

    }

    @Test(description = "Go to cart page")
    @Description("")
    @Severity(SeverityLevel.CRITICAL)
    public void GoToCartPage() {
        HomePage homePage = new HomePage(driver);
        homePage.scrollToProduct();
        homePage.clickAddToCart();
        homePage.scrollToCart();
        homePage.clickOnCartIcon();
        CartPage cartPage = new CartPage(driver);
        Assert.assertEquals(cartPage.getProductName(),"Sauce Labs Onesie");
        Assert.assertEquals( cartPage.checkProductPrice(),"$7.99");

    }
}
