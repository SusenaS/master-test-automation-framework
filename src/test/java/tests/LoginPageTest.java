package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;

public class LoginPageTest extends BaseTest {

    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password) {
        driver.get("https://opensource-demo.orangehrmlive.com/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(username, password);
        if (username.equals("Admin") && password.equals("admin123")) {
            Assert.assertTrue(loginPage.isDashboardVisible(), "Login failed with valid credentials");}
        else {
                String error = loginPage.getLoginErrorMessage();
                Assert.assertTrue(error.contains("Invalid credentials"), "Expected error not displayed");
            }
			
		}
    
    
    @DataProvider(name = "loginData")
    public Object[][] loginTestData() {
        return new Object[][] {
            {"Admin", "admin123"},   // valid credentials
            {"admin", "wrongpass"},  // invalid password
            {"wronguser", "admin123"} // invalid username
        };
    }

}
