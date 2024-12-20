package Utility;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import BasePackage.BaseClass;

public class UtilClass extends BaseClass {
	public static long page_load_timeOut = 20;
	public static long implicit_wait = 10;
	public WebDriverWait wait;

	public UtilClass() {
		super();
	}

	public static void scrollUsingJavaScript() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500);");
	}

	public static void switchWindow(WebDriver driver, WebElement ele, String exp) {
		String parentWindow = driver.getWindowHandle();
		String heading = null;
		Set<String> windowHandles = driver.getWindowHandles();
		for (String windowHandle : windowHandles) {
			if (!windowHandle.equals(parentWindow)) {
				driver.switchTo().window(windowHandle);
				heading = ele.getText();
			}
		}
		if (heading.equalsIgnoreCase(exp)) {
			System.out.println("Switching to child window");
		} else
			System.out.println("Child window is not present");
	}

	public static List<String> listSelectTagOptions(WebElement ele) {
		List<String> optionTexts = new ArrayList<>();
		Select drpdwn = new Select(ele);
		List<WebElement> drpdwnOptions = drpdwn.getOptions();
		for (WebElement ele1 : drpdwnOptions) {
			optionTexts.add(ele1.getText());
		}
		return optionTexts;
	}

	public static void clickUsingJavaScriptExecutor(WebElement ele) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", ele);
	}
	
	public static void waitImplicitly() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicit_wait));
	}
	
	public static void switchTabs() {
		try {
			String originalWindow=driver.getWindowHandle();
			
			Set<String> windowhandles=driver.getWindowHandles();
			while(windowhandles.size()==1) {
				windowhandles=driver.getWindowHandles();
			}
			for(String windowhandle:windowhandles) {
				if(!windowhandle.equals(originalWindow)) {
					driver.switchTo().window(windowhandle);
					break;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}