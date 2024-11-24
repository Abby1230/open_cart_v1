package utilities;

// Importing classes for handling desktop operations, file handling, and exceptions.
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
// Importing TestNG listener interfaces and result handling classes.
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

// Importing classes for generating Extent Reports.
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

// Importing a custom base class for capturing screenshots.
import test_base.BaseClass;

// Implementing the ITestListener interface to listen to test execution events.
public class ExtentReportManager implements ITestListener {

    // Declaring variables for Extent Report components.
    public ExtentSparkReporter sparkReporter; // Responsible for report configuration.
    public ExtentReports extent;             // Manages and collects report data.
    public ExtentTest test;                  // Logs individual test details.
    String repName;                          // Stores the name of the report file.

    // This method runs at the start of the test execution.
    public void onStart(ITestContext testContext) {
        // Setting the date and time format for the report name.
        SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

        // Getting the current date and time.
        Date dt = new Date();

        // Formatting the date to create a timestamp.
        String currentdatetimestamp = df.format(dt);

        // Creating a unique report name using the timestamp.
        repName = "Test-Report-" + currentdatetimestamp + ".html";

        // Setting up the SparkReporter with the file path where the report will be saved.
        sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);

        // Setting the title of the report.
        sparkReporter.config().setDocumentTitle("opencart Automation Report");

        // Setting the name of the report displayed in the report.
        sparkReporter.config().setReportName("opencart Functional Testing");

        // Setting the theme of the report (DARK or STANDARD).
        sparkReporter.config().setTheme(Theme.DARK);

        // Initializing the ExtentReports object.
        extent = new ExtentReports();

        // Attaching the SparkReporter to the ExtentReports.
        extent.attachReporter(sparkReporter);

        // Adding system/environment details to the report.
        extent.setSystemInfo("Application", "opencart");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("Sub Module", "Customers");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");

        // Fetching and adding additional information from TestNG's XML configuration.
        String os = testContext.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("Operating System", os);

        String browser = testContext.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser", browser);

        // Adding included groups information if provided in the TestNG XML.
        List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
        if (!includedGroups.isEmpty()) {
            extent.setSystemInfo("Groups", includedGroups.toString());
        }
    }

    // This method runs when a test case passes.
    public void onTestSuccess(ITestResult result) {
        // Create a new test entry in the report.
        test = extent.createTest(result.getTestClass().getName());

        // Assign groups to the test in the report.
        test.assignCategory(result.getMethod().getGroups());

        // Log the success status in the report.
        test.log(Status.PASS, result.getName() + " got successfully executed");
    }

    // This method runs when a test case fails.
    public void onTestFailure(ITestResult result) {
        // Create a new test entry in the report.
        test = extent.createTest(result.getTestClass().getName());

        // Assign groups to the test in the report.
        test.assignCategory(result.getMethod().getGroups());

        // Log the failure status and the exception message.
        test.log(Status.FAIL, result.getName() + " got failed");
        test.log(Status.INFO, result.getThrowable().getMessage());

        try {
            // Capture a screenshot of the failed test case.
            String imgPath = new BaseClass().captureScreen(result.getName());

            // Attach the screenshot to the report.
            test.addScreenCaptureFromPath(imgPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // This method runs when a test case is skipped.
    public void onTestSkipped(ITestResult result) {
        // Create a new test entry in the report.
        test = extent.createTest(result.getTestClass().getName());

        // Assign groups to the test in the report.
        test.assignCategory(result.getMethod().getGroups());

        // Log the skipped status and the reason.
        test.log(Status.SKIP, result.getName() + " got skipped");
        test.log(Status.INFO, result.getThrowable().getMessage());
    }

    // This method runs after all tests have finished execution.
    public void onFinish(ITestContext testContext) {
        // Finalize the report and save it to the specified location.
        extent.flush();

        // Get the file path of the generated report.
        String pathofExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;

        // Open the generated report automatically in the default browser.
        File extentReport = new File(pathofExtentReport);
        try {
            Desktop.getDesktop().browse(extentReport.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
        try {
        	URL url = new URL("file:///"+System.getProperty("user.dir")+"\\reports \\"+repName) ;
        		// Create the email message
        		
        		ImageHtmlEmail email = new ImageHtmlEmail();
        		 email. setDataSourceResolver(new DataSourceUrlResolver(url));
        		email.setHostName("smtp.googlemail.com");
        		email.setSmtpPort(465);
        		 email. setAuthenticator(new DefaultAuthenticator("pavanoltraining@gmail.com", "password") );
        		
        		email.setSSLOnConnect (true);
        		email.setFrom("Abh1234@gmail.com"); //Sender
        		email.setSubject("Test Results");
        		email.setMsg("Please find Attached Report .... ");
        		email.addTo("pavankumar .busyqa@gmail.com"); //Receiver
        		email.attach(url, "extent report", "please check report ... ");
        		email.send();// send the email
        }
        		catch(Exception e)
        		{ 
        			e.printStackTrace(); 
        		}
*/
        
}
}



/*
 * Simplified Explanation

Purpose: This class creates detailed reports for your test execution using the Extent Reports library.

How It Works:

When tests start, a report is created with system and test details.
As tests run, the results (pass, fail, skip) are logged into the report.
Screenshots are captured for failed tests.
After all tests, the report is finalized and opened in the default browser.

Why It's Useful: This helps you quickly understand which tests passed/failed and why, making debugging 
                 and reporting easier.
 * 
 */
