package yuvanu1.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import yuvanu1.TestComponents.BaseTest;
import yuvanu1.pageobjects.CartPage;
import yuvanu1.pageobjects.CheckoutPage;
import yuvanu1.pageobjects.ConfirmationPage;
import yuvanu1.pageobjects.LandingPage;
import yuvanu1.pageobjects.ProductCatalogue;

public class StepDefintionImpl extends BaseTest {
	
	public LandingPage landingPage;
	public ProductCatalogue productcatalogue;
	public ConfirmationPage confirmationpage;
	@Given("I landed on Ecommerce Page")
	public void I_Landed_on_ecommerce_Page() throws IOException {
		landingPage= launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username,String password) {
		ProductCatalogue productcatalogue=landingpage.loginAppliaction(username,password);
	}
	
	@When("^I add product (.+) to cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException {
		List<WebElement> products=productcatalogue.getProductList();
		productcatalogue.addProductToCart(productName);
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName) {

		CartPage cartpage=productcatalogue.goToCartPage();
		Boolean match=cartpage.ValidateCartProducts(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutpage=cartpage.goToCheckout();
		checkoutpage.selectCountry("india");
		confirmationpage=checkoutpage.submitOrder();
	}
	
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_confirmationPage(String string) {
		String confirmMessage = confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
	}
	
	@Then("^\"([^\"]*)\" message is displayed$")
	public void something_messgae_is_displayed(String strArg1) throws Throwable{
		Assert.assertEquals(strArg1, landingpage.getErrorMsg());
		driver.close();
	}
}
