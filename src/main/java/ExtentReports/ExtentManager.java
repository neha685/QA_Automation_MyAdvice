package ExtentReports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	public static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("./Reports/ExtentReport.html");
            extent = new ExtentReports();
            
            // Set report properties
            sparkReporter.config().setDocumentTitle("Automation Test Report for performance center");
            sparkReporter.config().setReportName("Performance center");
            sparkReporter.config().setTheme(Theme.DARK);
            sparkReporter.config().setTimeStampFormat("yyyy-MM-dd HH:mm:ss");
            sparkReporter.config().setEncoding("utf-8");
      
            // Attach the reporter to ExtentReports
            extent.attachReporter(sparkReporter);
            
            // Set system information
            extent.setSystemInfo("OS", "Windows 11");
            extent.setSystemInfo("Browser", "Chrome");
            extent.setSystemInfo("Environment", "Staging");
            extent.attachReporter(sparkReporter);
        }
        return extent;
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    public static void setTest(ExtentTest extentTest) {
        test.set(extentTest);
    }

}
