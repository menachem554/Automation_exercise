package Pages;

import Listener.ExtentManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class OpenNewAccountPage extends BasePage {
    // Locators
    @FindBy(linkText = "Open New Account")
    protected WebElement newAccountLink;
    @FindBy(css = "[value='Open New Account']")
    protected WebElement newAccountBtn;
    @FindBy(id = "type")
    protected WebElement accountType;
    @FindBy(css = "[id='newAccountId']")
    protected WebElement newAccountId;
    @FindBy(id = "accountType")
    protected WebElement validAccountType;

    // Functions
    public void openNewAccount(String sAccountType) throws InterruptedException {
        newAccountLink.click();
        Thread.sleep(1000);
        if (sAccountType != null) {
            select(accountType).selectByVisibleText(sAccountType);
        }
        newAccountBtn.click();
    }

    public void validateAccountOpening() {
        try {
            Thread.sleep(1500);
            Assert.assertEquals(titleMessage.getText(), "Account Opened!");
            ExtentManager.pass("Account Opened!💲. account number: " + newAccountId.getText());
            sNewAccountId = newAccountId.getText();
        } catch (Exception e) {
            Assert.fail("Validation failed" + e);
        }
    }

    public void validAccountType() {
        try {
            Thread.sleep(500);
            newAccountId.click();
            Thread.sleep(1500);
            System.out.println(validAccountType.getText());
            Assert.assertEquals(validAccountType.getText(),"SAVINGS");
            ExtentManager.pass("Savings Account Opened!💲");
        } catch (Exception e) {
            Assert.fail("Validation failed" + e);
        }
    }
}
