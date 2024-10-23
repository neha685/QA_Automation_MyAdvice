package PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import BasePackage.BaseClass;
import Utility.UtilClass;


public class LoginPage extends BaseClass{
//WebDriver driver;
	//UtilClass util=new UtilClass();
	
	@FindBy(id="email")
	WebElement txt_username;
	
	@FindBy(id="password")
	WebElement txt_pw;
	
	@FindBy(xpath="//button[contains(text(), 'Login') and @type='submit']")
	WebElement btn_login;
	
	@FindBy(xpath="//a[@class='btn btn-link']")
	WebElement link_forgotPw;
	
	@FindBy(xpath="//input[@placeholder='Email Address']")
	WebElement txt_emailfield;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement btn_pwResetLink;
	
	@FindBy(xpath="//h1[normalize-space()='Verification Email Sent!']")
	WebElement info_pwReset;
	
	@FindBy(xpath="//a[normalize-space()='Terms of Service']")
	WebElement terms_of_service;
	
	@FindBy(xpath="//h1[@class='ast-advanced-headers-title']")
	WebElement headingtext;
	
	@FindBy(xpath="//a[normalize-space()='Privacy Policy']")
	WebElement privacy_policy;
	
		
	public LoginPage() 
	{
	    PageFactory.initElements( driver, this);
	}
	
	public String loginPageTitle() {
		return driver.getTitle();
	}
	
	public String forgotPassword(String emailid) {
		link_forgotPw.click();
		txt_emailfield.sendKeys(emailid);
		btn_pwResetLink.click();
		String str=info_pwReset.getText();
		return str;
	}
	
	public LeadManagement loginToPC(String uname, String pw) {
		txt_username.sendKeys(uname);
		txt_pw.sendKeys(pw);
		btn_login.click();
		System.out.println("username is "+uname+" and pw is "+pw);
		return new LeadManagement();
	}
	
	public String termsOfService() {
		terms_of_service.click();
		UtilClass.switchWindow(driver, headingtext, prop.getProperty("terms_of_service"));
		return headingtext.getText();
	}
	
	public String privacyPolicy() {
		privacy_policy.click();
		UtilClass.switchWindow(driver,headingtext,prop.getProperty("privacy_policy"));
		return headingtext.getText();
	}
	
}


