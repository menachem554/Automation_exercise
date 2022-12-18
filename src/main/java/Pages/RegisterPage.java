package Pages;

import Listener.ExtentManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.io.IOException;

public class RegisterPage extends BasePage {
    public RegisterPage() {
    }

    // Locators
    @FindBy(linkText = "Register")
    public WebElement register;
    @FindBy(css = "h1.title")
    protected WebElement registerFormTitle;
    @FindBy(id = "customer.firstName")
    protected WebElement firstName;
    @FindBy(id = "customer.lastName")
    protected WebElement lastName;
    @FindBy(id = "customer.address.street")
    protected WebElement address;
    @FindBy(id = "customer.address.city")
    protected WebElement city;
    @FindBy(id = "customer.address.state")
    protected WebElement state;
    @FindBy(id = "customer.address.zipCode")
    protected WebElement zipCode;
    @FindBy(id = "customer.phoneNumber")
    protected WebElement phoneNumber;
    @FindBy(id = "customer.ssn")
    protected WebElement ssn;
    @FindBy(id = "customer.username")
    protected WebElement username;
    @FindBy(id = "customer.password")
    protected WebElement password;
    @FindBy(id = "repeatedPassword")
    protected WebElement repeatedPassword;
    @FindBy(css = "input[type='submit'][value='Register']")
    protected WebElement registerBtn;
    @FindBy(css = "[id='rightPanel'] > p")
    protected WebElement successMessage;

    // Functions
    public void register(String sFirstName, String sLastName, String sAddress,
                         String sCity, String sState, String sZipCode, String sPhoneNumber,
                         String sSsn, String sUsername, String sPassword,
                         String sRepeatedPassword) {
        register.click();

        try {
            Assert.assertEquals(registerFormTitle.getText(), "Signing up is easy!");
        } catch (Exception e) {
            Assert.fail("Validation failed" + e);
        }

        if (sFirstName != null) firstName.sendKeys(sFirstName);
        lastName.sendKeys(sLastName);
        address.sendKeys(sAddress);
        city.sendKeys(sCity);
        state.sendKeys(sState);
        zipCode.sendKeys(sZipCode);
        phoneNumber.sendKeys(sPhoneNumber);
        ssn.sendKeys(sSsn);
        username.sendKeys(sUsername);
        password.sendKeys(sPassword);
        repeatedPassword.sendKeys(sRepeatedPassword);
        registerBtn.click();
    }

    public void validateRegistration() {
        try {
            Assert.assertEquals(successMessage.getText(), "Your account was created successfully." +
                    " You are now logged in.");
            ExtentManager.pass("New user signup successfully: " + registerFormTitle.getText());
        } catch (Exception e) {
            Assert.fail("Validation failed" + e);
        }
    }

    public void logout() {
        try {
            Thread.sleep(1000);
            logout.click();
            Assert.assertEquals(customerLogin.getText(), "Customer Login");
        } catch (Exception e) {
            Assert.fail("Validation failed" + e);
        }
    }

    public String extractUserName(String name) {
        String extract = registerFormTitle.getText();
        name += extract.charAt(extract.length() - 2);
        name += extract.charAt(extract.length() - 1);

        return name;
    }
}
