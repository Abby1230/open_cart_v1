package page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Account_Registration_Page  extends Base_Page{
	
public Account_Registration_Page (WebDriver driver) 
	
	{
		super (driver);
		
	}
	
	@FindBy(css ="#input-firstname")
	WebElement FirstName;
	
	@FindBy(css ="#input-lastname")
	WebElement LastName;
	
	@FindBy(css ="#input-email")
	WebElement EmailName;
	
	@FindBy(css ="#input-telephone")
	WebElement Telephone;
	
	@FindBy(css ="#input-password")
	WebElement PasswordName;
	
	@FindBy(css ="#input-confirm")
	WebElement ConfirmPasswordName;
	
	@FindBy(css ="#content > form > div > div > input[type=checkbox]:nth-child(2)")
	WebElement ToggleButton;
	
	@FindBy(css ="#content > form > div > div > input.btn.btn-primary")
	WebElement ContinueButton;
	
	////*[@id="content"]/h1
	
	@FindBy(xpath ="//*[@id='content']/h1")
	WebElement msgConfirmation;
	
	@FindBy(css ="#content > div > div > a")
	WebElement ContinueConfirmation;
	
	
	public void FName (String fname) 
	{
		FirstName.sendKeys(fname);
	}
	
	public void LName (String lname) 
	{
		LastName.sendKeys(lname);
	}
	
	public void EMail (String ename) 
	{
		EmailName.sendKeys(ename);
	}
	
	public void TPhone (String ename) 
	{
		Telephone.sendKeys(ename);
	}
	
	
	public void Password (String pname) 
	{
		PasswordName.sendKeys(pname);
	}
	
	public void CPassword (String cpname) 
	{
		ConfirmPasswordName.sendKeys(cpname);
	}
	
	public void TButton () 
	{
		ToggleButton.click();
	}
	
	public void CButton () 
	{
		ContinueButton.click();
	}
	
	public String getConfiormatioMsg () {
		try {
			return(msgConfirmation.getText());
		}
		catch (Exception e)  {
			return (e.getMessage());
		}
	}
	
	public void CContinue () 
	{
		ContinueConfirmation.click();
	}
	
	

}
