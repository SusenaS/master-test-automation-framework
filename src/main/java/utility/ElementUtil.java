package utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ElementUtil {

    WebDriver driver;

    public ElementUtil(WebDriver driver) {
        this.driver = driver;
    }

    public void doClick(By locator) {
        Log.info("Clicking on element: " + locator.toString());
        driver.findElement(locator).click();
    }

    public void doSendKeys(By locator, String value) {
        Log.info("Typing in element: " + locator.toString() + " with value: " + value);
        driver.findElement(locator).sendKeys(value);
    }

    public String doGetText(By locator) {
        String text = driver.findElement(locator).getText();
        Log.info("Getting text from element: " + locator.toString() + " - Text: " + text);
        return text;
    }
}

