package Framework.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Framework.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents{
WebDriver driver;
	
	public CartPage(WebDriver driver)
	{   super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@class='cartSection']/h3")
	List<WebElement> CartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement CheckoutButton;
	
	
	public Boolean VerifyProductDisplay(String ProductName) {
		Boolean Match= CartProducts.stream().anyMatch(Cartproduct->Cartproduct.getText().equalsIgnoreCase(ProductName));
		   return Match;
	}
	public CheckoutPage goToCheckoutPage() {
		CheckoutButton.click();
		return new CheckoutPage(driver);
	}
	
}
