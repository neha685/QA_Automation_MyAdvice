package Utility;

import org.openqa.selenium.WebElement;

public class UtilClass {

	public static long page_load_timeOut=20;
	public static long implicit_wait=10;
	
	public void enterText(WebElement ele, String text) {
		ele.sendKeys(text);
	}
	
	
}
