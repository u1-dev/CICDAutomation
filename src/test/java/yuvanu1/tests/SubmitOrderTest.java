package yuvanu1.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import yuvanu1.TestComponents.BaseTest;
import yuvanu1.TestComponents.Retry;
import yuvanu1.pageobjects.CartPage;
import yuvanu1.pageobjects.CheckoutPage;
import yuvanu1.pageobjects.ConfirmationPage;
import yuvanu1.pageobjects.LandingPage;
import yuvanu1.pageobjects.OrderPage;
import yuvanu1.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest{
	String Product="IPHONE 13 PRO";
	
	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
	{ 
		
		
		ProductCatalogue productcatalogue=landingpage.loginAppliaction(input.get("email"),input.get("pwd"));
		//Products
		
		List<WebElement> products=productcatalogue.getProductList();
		productcatalogue.addProductToCart(input.get("product"));
		CartPage cartpage=productcatalogue.goToCartPage();
		
		
		Boolean match=cartpage.ValidateCartProducts(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutpage=cartpage.goToCheckout();
		checkoutpage.selectCountry("india");
		ConfirmationPage confirmationpage=checkoutpage.submitOrder();
		
		String confirmMessage = confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}
	
	@Test(dependsOnMethods={"submitOrder"},retryAnalyzer=Retry.class)
	public void OrderHistoryTest() {
		ProductCatalogue productcatalogue=landingpage.loginAppliaction("adc@yahoo.com","Shelby@1234");
		OrderPage orderpage= productcatalogue.goToOrdersPage();
		Assert.assertTrue(orderpage.VerifyOrderDisplay(Product));
	}
	

	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String,String>> data= getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\yuvanu1\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	
/*	@DataProvider
	public Object[][] getData() {
		HashMap<String,String> map=new HashMap<String,String>();
		map.put("email", "adc@yahoo.com");
		map.put("pwd", "Shelby@1234");
		map.put("product", "IPHONE 13 PRO");
		HashMap<String,String> map1=new HashMap<String,String>();
		map1.put("email", "shetty@gmail.com");
		map1.put("pwd", "Iamking@000");
		map1.put("product", "ADIDAS ORIGINAL");
		
		
		return new Object[][] {{map},{map1}};
		
	} */
	
/*	 @DataProvider
	  public Object[][] getData()
	  {
	    return new Object[][]  {{"anshika@gmail.com","Iamking@000","ZARA COAT 3"}, {"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL" } };
	    
	  } */
	
	
	
}
