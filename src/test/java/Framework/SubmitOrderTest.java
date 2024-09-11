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
import Framework.PageObjects.OrderPage;
import Framework.PageObjects.ProductCatalouge;
import Framework.TestComponenets.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest  extends BaseTest{
	String ProductName="ZARA COAT 3";
 @Test
 public void submitOrder() throws IOException, InterruptedException
 {
	 
	 
	String CountryName ="India";
	      
	//System.out.println("driver=" +driver);
		
	   // LandingPage_LoginPage LandLogin= luanchApplication();
		ProductCatalouge productCatalogue = LandLogin.LoginApplication("reha@gmail.com","Reha@1234");
		List<WebElement> Products = productCatalogue.getProductList();
		productCatalogue.AddProductToCart(ProductName);
		Thread.sleep(4000);
		
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean Match= cartPage.VerifyProductDisplay(ProductName);
        Assert.assertTrue(Match);
        CheckoutPage checkoutPage = cartPage.goToCheckoutPage();
        checkoutPage.SelectCountry(CountryName);
        
        
        ConfirmationPage confirmationMessage = checkoutPage.PlaceOrder();
        Thread.sleep(4000);
        
       String ConfirmMsg= confirmationMessage.getConfirmMessage();
              Thread.sleep(4000);
         System.out.println(ConfirmMsg);
         Assert.assertTrue(ConfirmMsg.equalsIgnoreCase("Thankyou for the order."));
         Thread.sleep(3000);

    //Assert.assertEquals(ConfirmMessage, " Thankyou for the order.");
    }
 
    @Test(dependsOnMethods= {"submitOrder"})
 public void OrderHistoryTest() 
    {
    	ProductCatalouge productCatalogue = LandLogin.LoginApplication("reha@gmail.com","Reha@1234");
    	OrderPage orderPage =productCatalogue.goToOrderPage();
    	Assert.assertTrue(orderPage.VerifyProductDisplay(ProductName));
    }
 
 
 
}
