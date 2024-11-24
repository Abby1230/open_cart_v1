package test_cases;

import page_objects.Account_Registration_Page;
import page_objects.Home_Page;
import test_base.BaseClass;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_001_Account_Registration_Test extends BaseClass
{
	
	
	@Test(groups={"Regression","Master","Master"})
	public void verify_Account_Registration () throws InterruptedException 
	{
		try {
		logger.info("Strating TC_001_Account_Registration_Test ");
		Home_Page pg = new Home_Page(driver);
		
		pg.clickMyAccount();
		logger.info("Click on MyAccount Link ");
		
		pg.clickRegister();
		logger.info("Click on Register Link ");
		
		Thread.sleep(300);
		
		Account_Registration_Page arp = new Account_Registration_Page(driver);
	
		logger.info("Providing customer details");
		arp.FName(randomString().toUpperCase());
		arp.LName(randomString().toUpperCase());
		arp.EMail(randomString()+"@gmail.com");
		arp.TPhone(randomNumber());
		
		String password = randomAlphaNumeric();
		
		arp.Password(password);
		arp.CPassword(password);
		
		arp.TButton();
		arp.CButton();
		
		logger.info("Validating expected message");
		String confimsg = arp.getConfiormatioMsg();
		
		if(confimsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test failed...");
			logger.debug("Debug logs...");
			Assert.assertTrue(false);
		}
		
		//Assert.assertEquals(confimsg, "Your Account Has Been Created!!!");
		
		arp.CContinue();
		
		Thread.sleep(300);
		}
		catch (Exception e) {
			
			Assert.fail();
		}
		
		logger.info("Finished TC_001_Account_Registration_Test ");
	}
	
	   
	
}