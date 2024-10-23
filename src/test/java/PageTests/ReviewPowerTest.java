package PageTests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import BasePackage.BaseClass;
import PageObjects.LoginPage;
import PageObjects.ReviewPower;

public class ReviewPowerTest extends BaseClass{
	ReviewPower review;
	LoginPage ln;
	
	public ReviewPowerTest() {
		super();
	}

	@BeforeMethod
	public void setup() {
		browserInitialization();
		ln=new LoginPage();
		ln.loginToPC(prop.getProperty("emailid"),prop.getProperty("password"));
		review=new ReviewPower();
	}
	
	@Test
	public void verifyReviewSubmenus() {
		Assert.assertTrue(review.reviewPowerDropDwn());
	}
}
