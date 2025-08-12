package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class GoogleSteps {
    WebDriver driver;

    @Given("I open the Google homepage")
    public void i_open_google_homepage() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.google.com");
    }

    @Then("I see the page title contains {string}")
    public void i_see_page_title_contains(String expected) {
        String title = driver.getTitle();
        Assert.assertTrue(title.contains(expected), "Title does not contain expected text");
        driver.quit();
    }
}
