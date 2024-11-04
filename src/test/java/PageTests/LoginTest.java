package PageTests;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import BasePackage.BaseClass;
import PageObjects.LoginPage;

public class LoginTest extends BaseClass {
	LoginPage ln;

	public LoginTest() {
		super();
	}

	@BeforeMethod
	public void setup() {
		browserInitialization();
		ln = new LoginPage();
	}

	@Test(priority = 1, enabled = true, description = "Verification of login page title", groups = { "smoke" })
	public void validateLoginPageTitle() {
		String actTitle = ln.loginPageTitle();
		Assert.assertEquals(actTitle, prop.getProperty("loginPageTitle"));
	}

//This test case will capture the message after sending the verification link to user email
	@Test(priority = 2, enabled = true, description = "verification of forgot pw link", groups = { "smoke" })
	public void verifyForgotPwLink() {
		String actText = ln.forgotPassword(prop.getProperty("emailid"));
		Assert.assertEquals(prop.getProperty("PW_reset_text"), actText);
	}

	@Test(priority = 3, enabled = true, description = "Verification of successful login", groups = { "smoke" })
	public void validateLogin() {
		ln.loginToPC(prop.getProperty("emailid"), prop.getProperty("password"));
		Assert.assertEquals(driver.getTitle(), prop.getProperty("homepage_title"));
	}

	@Test(priority = 4, enabled = true, description = "Verification of terms of service")
	public void validateTermsOfService() {
		String actText = ln.termsOfService();
		Assert.assertEquals(prop.getProperty("terms_of_service"), actText);
	}

	@Test(priority = 5, enabled = true, description = "Verification of privacy policy")
	public void validatePrivacyPolicy() {
		String actText = ln.privacyPolicy();
		Assert.assertEquals(prop.getProperty("privacy_policy"), actText);
	}

}
