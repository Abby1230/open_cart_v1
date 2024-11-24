package test_cases;

import org.testng.Assert;
import org.testng.annotations.Test;
import page_objects.Home_Page;
import page_objects.LoginPage;
import page_objects.MyAccountPage;
import test_base.BaseClass;
import utilities.DataProviders;

public class TC_003_LoginDDT extends BaseClass {

    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class,groups={"datadriven","Master"})
    public void verify_loginDDT(String email, String pwd, String exp) {
        try {
            logger.info("***** Starting TC_003_LoginDDT *****");

            Home_Page hp = new Home_Page(driver);
            hp.clickMyAccount();
            hp.clickLogin();

            LoginPage lp = new LoginPage(driver);
            lp.setEmail(email);
            lp.setPassword(pwd);
            lp.clickLogin();

            MyAccountPage macc = new MyAccountPage(driver);
            boolean targetPage = macc.isMyAccountExists();

            logger.info("Email: " + email + ", Password: " + pwd + ", Expected: " + exp);
            logger.info("Actual targetPage: " + targetPage);

            if (exp.equalsIgnoreCase("valid")) {
                if (targetPage) {
                    macc.clickLogOut();
                    Assert.assertTrue(targetPage, "Login succeeded as expected");
                } else {
                    Assert.fail("Login failed for valid credentials");
                }
            } else if (exp.equalsIgnoreCase("invalid")) {
                if (targetPage) {
                    macc.clickLogOut();
                    Assert.fail("Login succeeded for invalid credentials");
                } else {
                    Assert.assertTrue(true, "Login failed as expected");
                }
            }

        } catch (Exception e) {
            logger.error("Test failed due to exception: " + e.getMessage());
            Assert.fail(e.getMessage());
        }

        logger.info("***** Finished TC_003_LoginDDT *****");
    }
}
