package Framework.TestComponenets;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Framework.resources.ExtentReportNG;

public class Listeners extends BaseTest implements ITestListener{
	ExtentTest test;

	ExtentReports extent =ExtentReportNG.getReportObject();

	@Override

	public void onTestStart(ITestResult result) {
		test= extent.createTest(result.getMethod().getMethodName())	;
		}
	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		//extentTest.get().log(Status.PASS, "Test Passed");
		test.log(Status.PASS, "Test Passed");
		
	}
	@Override
	public void onTestFailure(ITestResult result) {
		//test.log(Status.FAIL, "Test Passed");
		test.fail(result.getThrowable());
		String FilePath = null;
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
					.get(result.getInstance());
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		try {
			FilePath = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(FilePath, result.getMethod().getMethodName());

		

	}	
	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
		
	}
	


}
