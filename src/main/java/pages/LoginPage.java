package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.Log;

public class LoginPage {

    WebDriver driver;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // very important
        Log.info("LoginPage initialized");

    }

    @FindBy(name = "username")
    private WebElement username;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(css = ".oxd-button.oxd-button--medium.oxd-button--main.orangehrm-login-button")
    private WebElement loginBtn;
    
    @FindBy(xpath = "//h6[text()='Dashboard']")
    WebElement dashboardHeader;

    @FindBy(css = ".oxd-alert-content-text")
    WebElement errorMessage;

    public boolean isDashboardVisible() {
        try {
            return dashboardHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getLoginErrorMessage() {
        return errorMessage.getText();
    }


    public void doLogin(String user, String pass) {
        username.sendKeys(user);
        password.sendKeys(pass);
        loginBtn.click();
    }

}
