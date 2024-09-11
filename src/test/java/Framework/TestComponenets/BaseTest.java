
package Framework.TestComponenets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import Framework.PageObjects.LandingPage_LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	
	public static WebDriver driver;
	public LandingPage_LoginPage LandLogin;
	public WebDriver initializeDriver() throws IOException {
		
		Properties prop = new Properties();
		FileInputStream files= new FileInputStream(System.getProperty("user.dir")+"//src//main//java//Framework/resources//GlobalData.properties");
		prop.load(files);
		String browserName = prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("chrome")) 
		{
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		
	    }
		else if(browserName.equalsIgnoreCase("Firefox")) {
			
		}
            else if(browserName.equalsIgnoreCase("Edge")) {
			
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize(); 
		return driver;
}
	@BeforeMethod
	public LandingPage_LoginPage luanchApplication() throws IOException 
	{
		
		 driver=initializeDriver();
		 LandLogin = new LandingPage_LoginPage(driver);
		 LandLogin.GoToLoginPage();
		return LandLogin;

	}
	@AfterMethod
	public void teardown() 
	{
		driver.close();
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
		
		
	}

	

}
