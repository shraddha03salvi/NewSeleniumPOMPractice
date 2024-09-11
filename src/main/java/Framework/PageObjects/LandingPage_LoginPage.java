package Framework.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Framework.AbstractComponents.AbstractComponents;

public class LandingPage_LoginPage extends AbstractComponents{

	
	WebDriver driver;
	public LandingPage_LoginPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//PageFactory
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement login;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;


	
	
	public void GoToLoginPage() 
	{
		
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage()
	{
		WaitElementTobeVisible(errorMessage);
		return errorMessage.getText();
	}
	

	public ProductCatalouge LoginApplication(String Email, String Password) {
		
		userEmail.sendKeys(Email);
		userPassword.sendKeys(Password);
		login.click();
		ProductCatalouge productCatalogue = new ProductCatalouge(driver);
		return productCatalogue;

	}
}
