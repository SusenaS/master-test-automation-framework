package base;

import java.io.File;
import java.io.IOException;
import utility.*;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import config.ConfigReader;

public class BaseTest {
    protected WebDriver driver;
    ConfigReader config;
    public void browserSetUp(String browser) {
    	switch (browser.toLowerCase()) {
		case "chrome": {
			WebDriverManager.chromedriver().setup();
	        driver = new ChromeDriver();
	        Log.info("Chrome launched successfully");
	        driver.manage().window().maximize();
			break;
		}
		case "edge":{
			WebDriverManager.edgedriver().setup();
	        driver = new EdgeDriver();
	        Log.info("Edge launched successfully");
	        break;
		}
		case "firefox":{
			WebDriverManager.firefoxdriver().setup();
	        driver = new FirefoxDriver();
	        Log.info("Firefox launched successfully");
	        break;
		}
		
		default:
            Log.warn("Unrecognized browser. Launching Chrome by default.");
			WebDriverManager.chromedriver().setup();
	        driver = new ChromeDriver();
	        break;
				
    	}
    	driver.manage().window().maximize();
        Log.info("Browser window maximized");
    }
    
    @BeforeClass
    public void setup() {
    	 config = new ConfigReader();
         String browser = config.getBrowser();
         browserSetUp(browser); // your method from before
         driver.get(config.getUrl());
         Log.info("Navigated to URL: " + config.getUrl());

    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
            Log.info("Browser closed after test execution.");
        }
        
        
    }
    
 // Step 1: Start writing @AfterMethod and access ITestResult
    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            // We’ll call the screenshot method from here
        	takeScreenshot(result.getName());
            Log.info("Screenshot captured for failed test: " + result.getName());

        }
        driver.quit();
    }
    
    public void takeScreenshot(String testName) throws IOException {
    	TakesScreenshot ts = (TakesScreenshot) driver;
    	File directory = new File("./screenshots/");
    	if (!directory.exists()) {
    	    directory.mkdirs(); // create the folder if it doesn't exist
    	}
    	
    	File src = ts.getScreenshotAs(OutputType.FILE);
    	File dest = new File("./screenshots/" + testName + ".png");
    	try {
    		FileUtils.copyFile(src, dest);

		} catch (Exception e) {
			// TODO: handle exception
		    e.printStackTrace();

		}
    	
    }

}
