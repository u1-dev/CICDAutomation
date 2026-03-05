package yuvanu1.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import yuvanu1.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy(css=".cartSection h3")
	private List<WebElement> cartprod;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutele;
	
	public boolean ValidateCartProducts(String Product) {
		boolean match=cartprod.stream().anyMatch(cartprod->cartprod.getText().equalsIgnoreCase(Product));
		return match;
		
	}
	
	public CheckoutPage goToCheckout() {
		checkoutele.click();
		CheckoutPage checkoutpage=new CheckoutPage(driver);
		return checkoutpage;
	}
}
