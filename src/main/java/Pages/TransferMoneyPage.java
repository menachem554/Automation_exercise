package Pages;

import Listener.ExtentManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class TransferMoneyPage extends BasePage {

    // Locators
    @FindBy(linkText = "Transfer Funds")
    protected WebElement transferMoneyLink;
    @FindBy(id = "amount")
    protected WebElement amount;
    @FindBy(id = "toAccountId")
    protected WebElement selectAccount;
    @FindBy(css = "[value='Transfer']")
    protected WebElement transferBtn;

    // Functions
    public void transferMoney(String transferAmount, boolean newAccount) throws InterruptedException {
        transferMoneyLink.click();
        Thread.sleep(1000);
        amount.sendKeys("" +transferAmount);
        selectAccount.click();
        if (newAccount) select(selectAccount).selectByVisibleText(sNewAccountId);
        transferBtn.click();
    }

    public void validateTransferMoney() {
        try {
            Thread.sleep(1000);
            Assert.assertEquals(titleMessage.getText(), "Transfer Complete!");
            ExtentManager.pass("Transfer money between the origin account and the new account🤑");
        } catch (Exception e) {
            Assert.fail("Validation failed" + e);
        }
    }
}
