package PageTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import BasePackage.BaseClass;
import PageObjects.LeadManagement;
import PageObjects.LoginPage;

public class LeadManagementTest extends BaseClass{
	LoginPage ln;
	LeadManagement leadPage;
	
	public LeadManagementTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() {
		browserInitialization();
		ln=new LoginPage();
		leadPage=ln.loginToPC(prop.getProperty("emailid"),prop.getProperty("password"));
		}
	
	@Test(priority=1, enabled=true, description="verification of lead management dropdown", groups= {"Regression"})
	public void verifyLeadManagementSubMenus() {
		Assert.assertTrue(leadPage.verificationOfSubMenusList());
	}
	
	public void verifyLocationInOverview() {
		
	}
	@Test(priority = 2, enabled=true, description="verification of create new lead", groups= {"Regression"})
	public void verifyCreateLeadFeature() {
		boolean result=leadPage.createNewLead(prop.getProperty("first_name"), prop.getProperty("last_name"), prop.getProperty("phone"),
				prop.getProperty("email"), prop.getProperty("description"));
		//asserting the name of lead as of now, will include toast msg once its flash time is increased
		Assert.assertTrue(result);	
	}
	
	@Test(priority=3, enabled=true, description="verification of add note feature", groups= {"smoke"})
	public void verifyAddNoteFeature() {
		Assert.assertTrue(leadPage.addNoteFeature());
	}
	
		
	//to be executed at the last so that large data wont be created
	@Test(priority=4, enabled=true, description="verification of lead deletion")
	public void deleteLead() {
		Assert.assertTrue(leadPage.verifyLeadDeletion());
	}
	
}
