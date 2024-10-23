package PageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import BasePackage.BaseClass;

public class ReviewPower extends BaseClass{

	@FindBy(xpath="//span[normalize-space()='Review Power']")
	WebElement reviewPower;
	
	@FindBy(xpath="//ul[@class='nav nav-second-level collapse in']//li")
	List<WebElement> list_reviewPower;
	
	
	public ReviewPower() {
		  PageFactory.initElements( driver, this);
	}
	
	public boolean reviewPowerDropDwn() {
		reviewPower.click();
		List<String> actList=list_reviewPower.stream().map(WebElement::getText).map(String::trim).toList();
		System.out.println(actList);
		List<String> expList=List.of("Overview","Latest Reviews","Request Reviews", "Quick Request","Request History","Review Portals", "Testimonials", "Settings");
		System.out.println(expList);
		if(actList.equals(expList)) {
			return true;
		} else {
			return false;
		}
	}


	
}
