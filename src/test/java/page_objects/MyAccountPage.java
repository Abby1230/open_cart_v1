package page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends Base_Page {
	
	public MyAccountPage (WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//*[@id=\"top-links\"]/ul/li[2]/a") //MyAccount Page heading
	WebElement msgHeadline;
	
	@FindBy(xpath="//*[@id=\"column-right\"]/div/a[13]")
	WebElement btnLogout;
	
	public boolean isMyAccountExists()
	{
	try {
		return(msgHeadline.isDisplayed());
	    }
	catch (Exception e)
	{
		return false;
	}
	
}
	public void clickLogOut()
	{
		btnLogout.click();
	}
	
}

