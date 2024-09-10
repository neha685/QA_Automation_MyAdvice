package PageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import BasePackage.BaseClass;

public class LeadManagement extends BaseClass{

	//pagefactory
	@FindBy(xpath="//ul[@class='nav nav-second-level collapse in']//li")
	List<WebElement> submenuList;	
	
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
	
	@FindBy(xpath="//div[@class='editable-text-label']//span[contains(text(),'Lead automation')]")
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
	
	 public LeadManagement() {
		 PageFactory.initElements( driver, this);
	 }
		
	
	//page methods
	public int subMenusSize() {
		return submenuList.	size();			
	}
	
	public List<String> getSubMenusList() {
		List<String> subMenus=new ArrayList<String>();
		List<WebElement> list=submenuList;
		for(WebElement ele:list) {
			String str=ele.getText();
			subMenus.add(str);
		}
		return subMenus;
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
}
