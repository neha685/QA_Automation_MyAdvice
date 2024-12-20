package PageObjects;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import BasePackage.BaseClass;
import Utility.UtilClass;
import lombok.experimental.UtilityClass;

public class LeadManagement extends BaseClass{

	//pagefactory
	@FindBy(xpath="//ul[@class='nav nav-second-level collapse in']//li")
	List<WebElement> submenuList;	
	
	@FindBy(xpath="//a[@href='https://performance.staging.advicecentral.online/lead-management'][normalize-space()='Overview']")
	WebElement LeadOverview;
	
	@FindBy(xpath="//*[@class='dropdown-content']//a")
	List<WebElement> drpdwn_location;
	
	@FindBy(xpath="//li//a[contains(text(), 'Leads')]")
	WebElement lead_submenu;
	
	@FindBy(xpath="*//button[@class='btn btn-light btn-sm']")
	WebElement btn_add_new_lead;
	
	@FindBy(xpath="//select[@name='channel']")
	WebElement drpdwn_channel;
	
	@FindBy(xpath="//input[@placeholder='First Name']")
	WebElement txt_firstname;
	
	@FindBy(xpath="//input[@placeholder='Last Name']")
	WebElement txt_lastname;
	
	@FindBy(xpath="//input[@id='MazPhoneNumberInput-8_phone_number']")
	WebElement txt_phonenumber;
	
	@FindBy(xpath="//input[@placeholder='Email']")
	WebElement txt_emailId;
	
	@FindBy(xpath="//textarea[@placeholder='Summary/description of the request...']")
	WebElement txt_description;
	
	@FindBy(xpath="//button[@class='btn btn-primary float-right']")
	WebElement btn_submit;
	
	@FindBy(xpath="//div[@class='toast-message']") 
	WebElement alert_toastmsg;
	
	@FindBy(xpath="//table//tr[1]//td[2]") 
	WebElement lead_name;
	
	@FindBy(xpath="//tbody/tr[1]/td[2]/a[1]")
	WebElement automation_lead;
	
	@FindBy(xpath="//button[@id='dropdownMenuButton']")
	WebElement meat_balls_menu;
	
	@FindBy(xpath="//a[normalize-space()='Delete (Other)']")
	WebElement delete_link;
	
	@FindBy(xpath="//button[@class='vc-btn']")
	WebElement btn_yes;
	
	@FindBy(xpath="//button[@class='btn btn-primary btn-add-note']")
	WebElement btn_add_Note;
	
	@FindBy(xpath="//div[@class='multiselect multiselect--above']")
	WebElement drpdwn_note;
	
	@FindBy(xpath="//span[@class='multiselect__single']//strong[contains(text(),'New (Global)')]")
	WebElement drpdwn_status;
	
	@FindBy(xpath="//iframe[@title='Rich Text Area']")
	WebElement frame_froalaEditor;
	
	@FindBy(xpath="//div[@id='vue_container']//div[3]//div[1]//button[2]")
	WebElement btn_save;
	
	@FindBy(xpath="//span[contains(text(),'In Progress (Global)')]")
	WebElement status;
	
	@FindBy(xpath="//input[@placeholder='Search by name...']")
	WebElement txt_searchField;
	
	@FindBy(xpath="//button[normalize-space()='Search']")
	WebElement btn_search;
	
	@FindBy(xpath="//i[@class='fas fa-filter']")
	WebElement btn_advancedFilter;
	
	@FindBy(xpath="//h4[@class='d-inline-block text-light mb-0 float-left']")
	WebElement txt_advancedFilterTitle;
	
	@FindBy(xpath="//button[@class='btn btn-light btn-xs ml-3']")
	WebElement btn_email;
	
	@FindBy(xpath="//input[@id='add_email']")
	WebElement txt_email;
	
	@FindBy(xpath="//div[@class='w-100']//div[1]//button[2]")
	WebElement btn_ok;
	
	@FindBy(xpath="//span[@class='email-address sort-handle']")
	WebElement txt_edited_emailid;
	
	@FindBy(xpath="//a[normalize-space()='People']")
	WebElement people_submenu;
	
	@FindBy(xpath="//table//tr[1]//td[2]")
	WebElement peopleName;
	
	@FindBy(xpath="//a[@href='/lead-management/leads/317']")
	WebElement btn_viewCTA;
	
	@FindBy(xpath="//div[@class='editable-text-input']//input[@type='text']")
	WebElement leadNameInNewTab;
	
	 public LeadManagement() {
		 PageFactory.initElements( driver, this);
	 }
		
	
	//page methods
	public int subMenusSize() {
		return submenuList.	size();			
	}
	
	public boolean verificationOfSubMenusList() {
		List<String> actList=submenuList.stream().map(WebElement::getText).map(String::trim).toList();
		System.out.println(actList);
		List<String> expList=List.of("Overview", "Leads", "People", "Settings");
		if(actList.equals(expList)) {
			return true;
		} else {
			return false;
		}
	}
	

	
	public boolean createNewLead(String fname, String lname, String phone, String emailid, String description) {
		lead_submenu.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		btn_add_new_lead.click();
		Select ss=new Select(drpdwn_channel);
		ss.selectByIndex(2);
		txt_firstname.sendKeys(fname);
		txt_lastname.sendKeys(lname);
		txt_phonenumber.sendKeys(phone);
		txt_emailId.sendKeys(emailid);
		txt_description.sendKeys(description);
		btn_submit.click();
		if(lead_name.isDisplayed()) {
		return true;
		} else {
			return false;
		}
	}
	
	public boolean verifyLeadDeletion() {
		lead_submenu.click();
		if(automation_lead.isDisplayed()) {
			automation_lead.click();
		} else {
			System.out.println("Lead does not exist");
		}
		meat_balls_menu.click();
		delete_link.click();
		btn_yes.click();
		if(alert_toastmsg.isDisplayed()) {
			return true;
		} else
		return false;
	}
	
	public boolean addNoteFeature() {
		lead_submenu.click();
		if(automation_lead.isDisplayed()) {
			automation_lead.click();
		} else {
			System.out.println("Lead does not exist");
		}
		Select ss=new Select(drpdwn_note);
		ss.selectByIndex(3);
		Select ss1=new Select(drpdwn_status);
		ss1.selectByIndex(1);
		driver.switchTo().frame(frame_froalaEditor);
		frame_froalaEditor.sendKeys("Changing status to in progress");
		btn_save.click();
		if(status.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean verifySearchField() {
		lead_submenu.click();
		txt_searchField.sendKeys("lead");
		btn_search.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		automation_lead.click();
		if(lead_name.isDisplayed()) {
			return true;
		} else
			return false;
	}
	public boolean verifyAdvancedFilter() {
		lead_submenu.click();
		btn_advancedFilter.click();
		if(txt_advancedFilterTitle.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}
	public boolean verifyEditLeadFeature(String email) {
		lead_submenu.click();
		try {
				automation_lead.click();
		} catch(Exception e){
			e.printStackTrace();
		}
		btn_email.click();
		txt_email.sendKeys(email);
		btn_ok.click();
		String actText=txt_edited_emailid.getText();
		if(actText.equalsIgnoreCase(email)) {
			return true;
		} else {
			return false;
		}
	}
	public boolean verificationOfPeopleSubmenu(String actUrl) {
		people_submenu.click();
		String currentUrl=driver.getCurrentUrl();
		System.out.println(currentUrl);
		if(actUrl.equalsIgnoreCase(currentUrl)) {
			return true;
		} else
		return false;	
	}
	
	public boolean verifyViewCTAinPeople(String exp) {
		people_submenu.click();
		peopleName.click();
		btn_viewCTA.click();
		UtilClass.switchWindow(driver, leadNameInNewTab, exp);
		if(lead_name.isDisplayed()) {
			return true;
			} else {
			return false;
			}
	}
	
}