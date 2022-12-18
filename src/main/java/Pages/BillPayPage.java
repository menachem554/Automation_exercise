package Pages;

import Listener.ExtentManager;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class BillPayPage extends BasePage {

    // Locators
    @FindBy(linkText = "Bill Pay")
    protected WebElement billPayLink;
    @FindBy(name = "payee.name")
    protected WebElement name;
    @FindBy(name = "payee.address.street")
    protected WebElement address;
    @FindBy(name = "payee.address.city")
    protected WebElement city;
    @FindBy(name = "payee.address.state")
    protected WebElement state;
    @FindBy(name = "payee.address.zipCode")
    protected WebElement zipCode;
    @FindBy(name = "payee.phoneNumber")
    protected WebElement phoneNumber;
    @FindBy(name = "payee.accountNumber")
    protected WebElement accountNumber;
    @FindBy(name = "verifyAccount")
    protected WebElement verifyAccount;
    @FindBy(name = "amount")
    protected WebElement amount;
    @FindBy(css = "value=['Send Payment']")
    protected WebElement sendPaymentBtn;
    @FindBy(css = "[ng-show='showResult'] .title")
    protected WebElement successMessage;

    // Functions
    public void billPay(String sName, String sAddress, String sCity, String sState,
                        String iZipCode, String iPhoneNumber, String iAccountNumber,
                        String iVerifyAccount, String iAmount) {

        billPayLink.click();
        if (sName != null) name.sendKeys(sName);
        address.sendKeys(sAddress);
        city.sendKeys(sCity);
        state.sendKeys(sState);
        zipCode.sendKeys( iZipCode);
        phoneNumber.sendKeys( iPhoneNumber);
        accountNumber.sendKeys( iAccountNumber);
        verifyAccount.sendKeys(iVerifyAccount);
        amount.sendKeys("" + iAmount + Keys.ENTER);
    }

    public void validateBillPay() {
        try {
            Thread.sleep(1500);
            Assert.assertEquals(successMessage.getText(), "Bill Payment Complete");
            ExtentManager.pass("Action of Bill Payment completed");
        } catch (InterruptedException e) {
            Assert.fail("Validation failed" + e);
        }
    }
}
