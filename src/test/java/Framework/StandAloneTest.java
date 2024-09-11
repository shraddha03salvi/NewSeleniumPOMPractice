package Framework;

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

import Framework.PageObjects.LandingPage_LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {
 public static  void main(String[] args) throws InterruptedException {
	 
	 String ProductName="ZARA COAT 3";
	 WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();        
		driver.get("https://rahulshettyacademy.com/client");
		LandingPage_LoginPage LandLogin = new LandingPage_LoginPage(driver);
        driver.findElement(By.id("userEmail")).sendKeys("reha@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Reha@1234");
        driver.findElement(By.id("login")).click();
        WebDriverWait wait = new  WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
        // Finding all products
      List<WebElement> Products=  driver.findElements(By.cssSelector(".mb-3"));
      
      // Iteration using Javastreams
    WebElement Prod=  Products.stream().filter(product->
      product.findElement(By.tagName("b")).getText().equals(ProductName)).findFirst().orElse(null);
    // Clicking on Add To cart Button
    Prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
   // Wating for Success toast message
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
    // Waiting for loading to be invisible
  //  wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
    wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

    // Menu Cart Button Click
    driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
    
    //Css selector : .cartSection h3
    //Xpath = //*[@class='cartSection']/h3
    
    List<WebElement> CartProducts= driver.findElements(By.xpath("//*[@class='cartSection']/h3"));
    
   Boolean Match= CartProducts.stream().anyMatch(Cartproduct->Cartproduct.getText().equalsIgnoreCase(ProductName));
   Assert.assertTrue(Match);
   
 // Css with = .className tagName
   // Clicking Checkout button.
   driver.findElement(By.cssSelector(".totalRow button")).click();
   // AutoSuggestions Dropdown
   Actions a = new Actions(driver);
   a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"India").build().perform();
   wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ta-results"))));
   // wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results list-group ng-star-inserted")));
    // Regular expression xpath = //button[contains(@class,'ta-item')], to click on India = (//button[contains(@class,'ta-item')])[2]
    // Css - .ta-item:nth-of-type(2)
    //clicking on India
  
    driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
    // Click on placeorder
   
    JavascriptExecutor jse = (JavascriptExecutor)driver;
    jse.executeScript("window.scrollBy(0,1000)");
    wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".action__submit"))));
    driver.findElement(By.cssSelector(".action__submit")).click();
    //css for = .btnn action__submit ng-star-inserted
    
    wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".hero-primary"))));
    String ConfirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
    Thread.sleep(4000);
    System.out.println(ConfirmMessage);

    Assert.assertTrue(ConfirmMessage.equalsIgnoreCase("Thankyou for the order."));
    Thread.sleep(3000);

    //Assert.assertEquals(ConfirmMessage, " Thankyou for the order.");
    driver.close();
    

        
 }
}
