package yuvanu1.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import yuvanu1.TestComponents.BaseTest;
import yuvanu1.pageobjects.CartPage;
import yuvanu1.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest{

	@Test(groups= {"ErrorHandling"})
	public void userLogin() throws IOException
	{
		
		landingpage.loginAppliaction("adc@yahoo.com","Shelb@1234");
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMsg());
	}
	
	@Test
	public void ProdcutErrorValidation() throws InterruptedException {
		
String Product="IPHONE 13 PRO";
		
		ProductCatalogue productcatalogue=landingpage.loginAppliaction("adc@yahoo.com","Shelby@1234");
		//Products
		
		List<WebElement> products=productcatalogue.getProductList();
		productcatalogue.addProductToCart(Product);
		CartPage cartpage=productcatalogue.goToCartPage();
		
		
		Boolean match=cartpage.ValidateCartProducts("lion");
		Assert.assertFalse(match);
	}
	

}
