package Framework.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Framework.AbstractComponents.AbstractComponents;

public class ProductCatalouge extends AbstractComponents {
	WebDriver driver;
	
	public ProductCatalouge(WebDriver driver)
	{   super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> Products;
	
	@FindBy(css=".ng-animating")
	WebElement Spinner;
	
	
	By ProductsBy =By.cssSelector(".mb-3");
	By AddToCart =By.cssSelector(".card-body button:last-of-type");
	By toastMessage=By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList() {
		WaitElementTobeAppear(ProductsBy);
	return Products;
	}
	
	public WebElement getProductFromListByName(String ProductName) {
		//instead of Products we used getProductList() method
		WebElement Prod=  getProductList().stream().filter(product->
	      product.findElement(By.tagName("b")).getText().equals(ProductName)).findFirst().orElse(null);
		return Prod;
	}
	
	public void AddProductToCart(String ProductName) {
		
		WebElement prod=getProductFromListByName(ProductName);
		prod.findElement(AddToCart).click();
		WaitElementTobeAppear(toastMessage);
		WaitElementToBeDisappear(Spinner);
		
	}
}
