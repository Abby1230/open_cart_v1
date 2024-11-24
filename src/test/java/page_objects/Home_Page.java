package page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Home_Page extends Base_Page{

	
	// Constructor
	public Home_Page (WebDriver driver) 
	
	{
		super (driver);
		
	}
	
	@FindBy(xpath="//*[@id=\"top-links\"]/ul/li[2]/a")
	WebElement MyAccountLink;
	
	@FindBy(xpath="/html/body/nav/div/div[2]/ul/li[2]/ul/li[1]/a")
	WebElement RegistertLink;
	
	@FindBy(xpath="//*[@id=\"top-links\"]/ul/li[2]/ul/li[2]/a")
	WebElement LoginLink;
	
	public void clickMyAccount () 
	{
		MyAccountLink.click();
	}
	
	public void clickRegister () 
	{
		RegistertLink.click();
	}
	
	public void clickLogin () 
	{
		LoginLink.click();
	}
	
	
}
