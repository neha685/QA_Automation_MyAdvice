package PageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ReviewPower {

	@FindBy(xpath="//span[normalize-space()='Review Power']")
	WebElement reviewPower;
	
	@FindBy(xpath="//ul[@class='nav nav-second-level collapse in']//li")
	List<WebElement> list_reviewPower;
	
	
	public void reviewPowerDropDwn() {
		
	}
}
