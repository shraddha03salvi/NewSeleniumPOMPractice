package Framework;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Framework.PageObjects.CartPage;
import Framework.PageObjects.CheckoutPage;
import Framework.PageObjects.ConfirmationPage;
import Framework.PageObjects.LandingPage_LoginPage;
import Framework.PageObjects.ProductCatalouge;
import Framework.TestComponenets.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidationsTest  extends BaseTest{
 @Test
 public void LoginErrorValidation() throws IOException, InterruptedException
 {
	 
	 
	String CountryName ="India";
	      
	//System.out.println("driver=" +driver);
		
	   // LandingPage_LoginPage LandLogin= luanchApplication();
		 LandLogin.LoginApplication("reha@gmail.com","Reha@12345");
		
		LandLogin.getErrorMessage();
		Assert.assertEquals("Incorrect email or password.", LandLogin.getErrorMessage());
	

    }
 
 @Test
 public void ProductErrorValidation() throws InterruptedException 
 {   String ProductName="ZARA COAT 3";
	 ProductCatalouge productCatalogue = LandLogin.LoginApplication("reha@gmail.com","Reha@1234");
		List<WebElement> Products = productCatalogue.getProductList();
		productCatalogue.AddProductToCart(ProductName);
		Thread.sleep(4000);
		
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean Match= cartPage.VerifyProductDisplay("ZARA COAT 33");
     Assert.assertFalse(Match);
 }
}
