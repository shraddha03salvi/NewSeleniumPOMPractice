package Framework.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Framework.PageObjects.CartPage;
import Framework.PageObjects.OrderPage;

public class AbstractComponents {
	
	WebDriver driver;
	public AbstractComponents(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css="[routerlink*='cart']")
	WebElement CartHeader;
	
	@FindBy(css="[routerlink='/dashboard/myorders']")
	WebElement OrdersHeader;
	
	
	
	public void WaitElementTobeAppear(By FindBy) {

	 WebDriverWait wait = new  WebDriverWait(driver,Duration.ofSeconds(8));
     wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
}
	public void WaitElementTobeVisible(WebElement ele) {
		WebDriverWait wait = new  WebDriverWait(driver,Duration.ofSeconds(8));
		 wait.until(ExpectedConditions.visibilityOf(ele));
	}
	public void WaitElementToBeDisappear(WebElement ele) {
		WebDriverWait wait = new  WebDriverWait(driver,Duration.ofSeconds(8));
	    wait.until(ExpectedConditions.invisibilityOf(ele));

	}
	
	public void WindowPagescrollDown() {
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
	    jse.executeScript("window.scrollBy(0,2000)");
	}
	
	public CartPage goToCartPage() {
		CartHeader.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;

	}
	public OrderPage goToOrderPage() {
		OrdersHeader.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;

	}
	
}

