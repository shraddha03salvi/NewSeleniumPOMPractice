package Framework.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Framework.AbstractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents {
  WebDriver driver;
	
	public CheckoutPage(WebDriver driver)
	{   super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css="[placeholder='Select Country']")
	WebElement CountryDropDown;
	
	By results=By.cssSelector(".ta-results");
	
	@FindBy(css=".action__submit")
	WebElement PlaceOrderButton;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement SelectCountry;
	
	
	
	public void SelectCountry(String CountryName) throws InterruptedException {
		Actions a = new Actions(driver);
		   a.sendKeys(CountryDropDown,CountryName).build().perform();
		   WaitElementTobeAppear(results);
		   SelectCountry.click();
		   //Thread.sleep(4000);
	}
	
	public ConfirmationPage PlaceOrder() throws InterruptedException {
		WindowPagescrollDown();
		Thread.sleep(4000);
		WaitElementTobeVisible(PlaceOrderButton);
		PlaceOrderButton.click();
		return new ConfirmationPage(driver);

	}
}
