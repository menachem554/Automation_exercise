package Pages;

import Listener.ExtentManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class LoginPage extends BasePage {
    // Locators
    @FindBy(css = "[class='button']:first-child")
    protected WebElement login;
    @FindBy(name = "username")
    protected WebElement username;
    @FindBy(name = "password")
    protected WebElement password;
    @FindBy(css = "[class='smallText']")
    protected WebElement welcomeMessage;
    @FindBy(css = "[href^='activity.htm']")
    protected WebElement accountId;

    // Functions
    public void Login(String sName, String sPassword) {
            if (sName != null) username.sendKeys(sName);
            password.sendKeys(sPassword);
            login.click();
    }

    public void validateLogin() {
        try{
            Assert.assertTrue(welcomeMessage.getText().contains("Welcome"));
            ExtentManager.pass("Login action completed. The account number is: " + accountId.getText());
            sAccountId = accountId.getText();
        } catch (Exception e) {
            Assert.fail("Validation failed" + e);
        }

    }
}
