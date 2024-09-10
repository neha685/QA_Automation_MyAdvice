package PageTests;
import java.util.List;
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
	
	@Test(priority=1, enabled=false)
	public void verifyLeadManagementSubMenus() {
		List<String> actList=leadPage.getSubMenusList();
		System.out.println(actList +" is the act list");
		List<String> explist=getList(prop.getProperty("lead_submenus"));
		System.out.println("Exp list is "+explist);
		Assert.assertEquals(actList, explist);
	}
	
	@Test(priority = 2, enabled=false)
	public void verifyCreateLeadFeature() {
		boolean result=leadPage.createNewLead(prop.getProperty("first_name"), prop.getProperty("last_name"), prop.getProperty("phone"),
				prop.getProperty("email"), prop.getProperty("description"));
		Assert.assertTrue(result);	
	}
	
	@Test(priority=3, enabled=true)
	public void verifyAddNoteFeature() {
		Assert.assertTrue(leadPage.addNoteFeature());
	}
	
	
	
	@Test(priority=4, enabled=false)
	public void deleteLead() {
		Assert.assertTrue(leadPage.verifyLeadDeletion());
	}
	
}
