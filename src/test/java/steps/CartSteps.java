package steps;

import test.java.BaseTest;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.CartPage;


public class CartSteps extends BaseTest {


    @Then("user checks product name is {string}")
    public void CheckProductName(String expectedProductName) {
        CartPage cartPage = new CartPage(getDriver());
        cartPage.getProductName();
        Assert.assertEquals(cartPage.getProductName(), expectedProductName);
    }

    @Then("user checks product price is {string}")
    public void CheckProductPrice(String price) {
        CartPage cartPage = new CartPage(getDriver());
        Assert.assertEquals(cartPage.checkProductPrice(), price);

    }
}
