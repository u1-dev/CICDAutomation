package yuvanu1.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import yuvanu1.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{
	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css=".offset-sm-1")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
			
	
	By productsBy=By.cssSelector(".mb-3");
	By toastMessage=By.cssSelector("#toast-container");
	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productname) {
		WebElement prod=getProductList().stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".w-10")).click();
		
	return prod;
	}
	
	public void addProductToCart(String prodName) throws InterruptedException {
		WebElement prod= getProductByName(prodName);
		
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear();
		}
	
	

}
