package test_base;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager; // log4j
import org.apache.logging.log4j.Logger;     // log4j
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	public WebDriver driver;
	public Logger logger;    // Log4j2
	public Properties p;

	@BeforeClass(groups={"Sanity","Regression","Master","DataDriven"})
	@Parameters({"os", "browser"})
	public void setup(@Optional("default_os") String os, @Optional("chrome") String br) throws IOException
	{
		//Loading config.properties
		FileReader file = new FileReader("D:\\WorkSpace\\open_cart_v1\\config.properties");

		p=new Properties();
		p.load(file);
		
		logger=LogManager.getLogger(this.getClass());//log4j2
		
		switch(br.toLowerCase())
		{
		case "chrome" : driver=new ChromeDriver(); break;
		case "firefox" : driver=new FirefoxDriver(); break;
		case "edge" : driver=new EdgeDriver(); break;
		default : System.out.println("Invalid browser name"); return;
		}
		
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(p.getProperty("appUrl"));
		driver.manage().window().maximize();
	}
	
	@AfterClass (groups={"Sanity","Regression","Master"})
	public void teardown ()
	{
		driver.quit();
	}
	
	 public String randomString() {
	    	String generatedstring=RandomStringUtils.randomAlphabetic(5);
	    	return generatedstring;
	    }
	    
	    public String randomNumber() {
	    	String generatedstring=RandomStringUtils.randomNumeric(10);
	    	return generatedstring;
	    }
	    
	    public String randomAlphaNumeric() {
	    	String generatedstring=RandomStringUtils.randomAlphabetic(3);
	    	String generatednumber=RandomStringUtils.randomNumeric(3);
	    	return (generatedstring+"@"+generatednumber);
	    }
	    
	    public String captureScreen(String tname) throws IOException {
	    	String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss") .format(new Date());
	    	TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
	    	File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
	    	String targetFilePath=System.getProperty("user . dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
	    	File targetFile=new File(targetFilePath);
	    	sourceFile.renameTo(targetFile);
	    	return targetFilePath;

	    }
}