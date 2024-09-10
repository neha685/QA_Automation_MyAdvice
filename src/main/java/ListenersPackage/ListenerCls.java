package ListenersPackage;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import BasePackage.BaseClass;
import ExtentReports.ExtentManager;

public class ListenerCls implements ITestListener{
	
	@Override
    public void onTestStart(ITestResult result) {	
        ExtentTest test = ExtentManager.getInstance().createTest(result.getMethod().getMethodName());
        ExtentManager.setTest(test);
    }
	
	@Override
    public void onTestFailure(ITestResult result) {
		ExtentTest test = ExtentManager.getTest();
        TakesScreenshot screenshot = (TakesScreenshot) BaseClass.driver;
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        String screenshotPath = "/screenshots/" + result.getMethod().getMethodName() + ".png";
        File destFile = new File(screenshotPath);
        
        try {
            FileUtils.copyFile(srcFile, destFile);
            test.log(Status.FAIL, "Test failed", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	
	 @Override
	    public void onTestSuccess(ITestResult result) {
	        ExtentTest test = ExtentManager.getTest();
	        test.log(Status.PASS, "Test passed");
	    }
	 @Override
	    public void onTestSkipped(ITestResult result) {
	        ExtentTest test = ExtentManager.getTest();
	        test.log(Status.SKIP, "Test skipped");
	    }
	 
	 @Override
	    public void onFinish(ITestContext context) {
	        ExtentManager.getInstance().flush();
	    }
    
}
