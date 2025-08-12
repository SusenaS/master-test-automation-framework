package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class OpenCartHomePageTest {

    WebDriver driver;

    @BeforeClass
    public void setup() {
        // Setup ChromeDriver automatically
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void openHomePageAndVerifyTitle() {
       driver.get("https://opensource-demo.orangehrmlive.com/");
       driver.navigate().refresh();
       driver.manage().window().maximize();
       driver.navigate().back();
       driver.navigate().to("https://Google.com/");
       driver.manage().window().fullscreen();

       driver.manage().window().minimize();
       driver.navigate().forward();
       
            }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
