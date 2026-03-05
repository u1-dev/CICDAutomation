package yuvanu1.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import yuvanu1.AbstractComponents.AbstractComponent;

public class OrderPage  extends AbstractComponent{
	
	WebDriver driver;
	@FindBy(css="tr td:nth-child(3)")
	private List<WebElement> productNames;
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean VerifyOrderDisplay(String Product) {
		boolean match=productNames.stream().anyMatch(cartprod->cartprod.getText().equalsIgnoreCase(Product));
		return match;
		
	}
}


