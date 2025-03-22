import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;
import test.java.BaseTest;


public class PurchaseScenarios extends BaseTest {


    @BeforeMethod()
    public void Setup() {
        LoginPage loginpage = new LoginPage(getDriver());
        loginpage.navigateToLoginPage();
        loginpage.login("standard_user","secret_sauce");
    }
    @Test(priority = 1,description = "Purchase an Item")
    @Description("")
    @Severity(SeverityLevel.CRITICAL)
    public void addItemToCart() {
        HomePage homePage = new HomePage(getDriver());
        homePage.scrollToProduct();
        homePage.clickAddToCart();
        homePage.scrollToCart();
        Assert.assertEquals(homePage.getCartItems(),"1");
        homePage.scrollToProduct();
        homePage.clickRemoveFromCart();
        homePage.scrollToCart();
        Assert.assertEquals(homePage.getCartItems(),"");
    }

    @Test(priority = 3,description = "Go to cart page")
    @Description("")
    @Severity(SeverityLevel.CRITICAL)
    public void goToCartPage() {
        HomePage homePage = new HomePage(getDriver());
        homePage.scrollToProduct();
        homePage.clickAddToCart();
        homePage.scrollToCart();
        homePage.clickOnCartIcon();
        CartPage cartPage = new CartPage(getDriver());
        Assert.assertEquals(cartPage.getProductName(),"Sauce Labs Onesie");
        Assert.assertEquals( cartPage.checkProductPrice(),"$7.99");

    }
}
