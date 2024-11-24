package test_cases;

import org.testng.Assert;
import org.testng.annotations.Test;

import page_objects.Home_Page;
import page_objects.LoginPage;
import page_objects.MyAccountPage;
import test_base.BaseClass;

public class TC_002_LoginTest extends BaseClass{
	
	
	@Test(groups={"sanity","Master"})
	public void verifyLogin()
	{
		logger.info("***Starting TC_002_LoginTest***");
		
		try {
		//HomePage
		Home_Page hp = new Home_Page(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//LoginPage
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		
		//MyAccount
		MyAccountPage map = new MyAccountPage(driver);
		boolean targetPage=map.isMyAccountExists();
		
		Assert.assertTrue(targetPage);//Assert.assertTrue(targetPage, true,"Login failed");
		}
		catch (Exception e)
		{
			Assert.fail();
		}
		logger.info("*****Finished TC_002_LoginTest*****");
		
		
		
		
	}

}
