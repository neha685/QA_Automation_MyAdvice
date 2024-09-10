package PageTests;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import BasePackage.BaseClass;
import PageObjects.LoginPage;

public class LoginTest extends BaseClass{
	LoginPage ln;
	public LoginTest() {
		super();
	}
	
	
@BeforeMethod
public void setup() {
	browserInitialization();
	ln=new LoginPage();
}

@Test(priority=1, enabled=true)
public void validateLoginPageTitle() {
	String actTitle=ln.loginPageTitle();
	AssertJUnit.assertEquals(actTitle, prop.getProperty("loginPageTitle"));
}

//This test case will capture the message after sending the verification link to user email
@Test(priority=2, enabled=false)
public void verifyForgotPwLink() {
	String actText=ln.forgotPassword(prop.getProperty("emailid"));
	AssertJUnit.assertEquals(prop.getProperty("PW_reset_text"), actText);
}

@Test(priority=3, enabled=false)
public void validateLogin() {
	ln.loginToPC(prop.getProperty("emailid"), prop.getProperty("password"));
	AssertJUnit.assertEquals(driver.getTitle(), prop.getProperty("homepage_title"));
	System.out.println("email id is "+prop.getProperty("emailid"));
	System.out.println("pw is "+prop.getProperty("password"));
}
}
