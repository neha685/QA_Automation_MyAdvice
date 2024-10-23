package BasePackage;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utility.UtilClass;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriver driver;
	public static Properties prop=new Properties();
	public static int response_code=200;
	public WebDriverWait wait;
	
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
		driver.manage().window().maximize();
	}
	
	public static List<String> getList(String key) {
        prop=new Properties();
        List<String> itemsList = null;
        String listProperty;
        try {
        	FileInputStream fis=new FileInputStream("./src/main/java/Config/config.properties");
        	prop.load(fis);
        	 listProperty = prop.getProperty(key);
        	 if(listProperty != null) {
        		 String[] itemsArray = listProperty.split(",");
        		 itemsList = Arrays.asList(itemsArray);
        		  System.out.println("List from properties file: " + itemsList);
        	 } else {
        		 System.out.println("List property not found");
        	 }
        } catch(Exception e) {
        	e.printStackTrace();
        }
		return itemsList;
           
    }
	
	
	
}
	

