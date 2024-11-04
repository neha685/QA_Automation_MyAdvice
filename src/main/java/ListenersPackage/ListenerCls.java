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
import ExtentReports.pdfReports;

public class ListenerCls implements ITestListener{
	pdfReports pdfReportGenerator = new pdfReports("test-report.pdf");
	@Override
    public void onTestStart(ITestResult result) {
		//extent report configuration
        ExtentTest test = ExtentManager.getInstance().createTest(result.getMethod().getMethodName());
        ExtentManager.setTest(test);
        test.log(Status.INFO, "Test started "+result.getMethod().getMethodName());
        
        pdfReportGenerator.logTestStep("The Test scenario "+result.getName(),"Start", "Started");
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
        test.log(Status.INFO, "Test failed!"+result.getMethod().getMethodName());
        pdfReportGenerator.logTestStep("The Test scenario "+result.getName(),"Fail" , "Test failed due to "+result.getThrowable());
    }
	
	 @Override
	    public void onTestSuccess(ITestResult result) {
	        ExtentTest test = ExtentManager.getTest();
	        test.log(Status.PASS, "Test passed"+result.getMethod().getMethodName());
	        pdfReportGenerator.logTestStep("The Test scenario "+result.getName(),"Pass" , "executed successfully");
	    }
	 @Override
	    public void onTestSkipped(ITestResult result) {
	        ExtentTest test = ExtentManager.getTest();
	        test.log(Status.SKIP, "Test skipped"+result.getMethod().getMethodName());
	    }
	 
	 @Override
	    public void onFinish(ITestContext context) {
		 String reportContent = "Test Report Content"; 
	     pdfReportGenerator.closeReport();
	        ExtentManager.getInstance().flush();
	    }
    
}
