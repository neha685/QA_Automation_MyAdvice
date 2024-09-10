package BasePackage;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Listeners;

import Utility.UtilClass;
import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseClass {

	public static WebDriver driver;
	public static Properties prop=new Properties();
	public static int response_code=200;
	
	
	public BaseClass() {
		try {
			FileInputStream fis=new FileInputStream("./src/main/java/Config/config.properties");
			prop.load(fis);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public BaseClass(WebDriver driver) {
		BaseClass.driver=driver;
		//PageFactory.initElements(driver, this);
	}
	/*
	public WebDriver getDriver() {
		return driver;
	}
	*/
	
	public static void browserInitialization() {
		String brname=prop.getProperty("browser");
		if(brname.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		} else if(brname.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		} else if(brname.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		} else {
			System.out.println("Please enter a browser to start with");
		}
		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(UtilClass.implicit_wait));
		//driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(UtilClass.page_load_timeOut));
	}
	
	public List<String> getList(String key) {
        String value = prop.getProperty(key, "");
         return Arrays.asList(value.split(","));
    }
	
}
	

