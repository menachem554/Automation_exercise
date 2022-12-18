package Pages;

import Listener.ExtentManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class RequestLoanPage extends BasePage {

    // Locators
    @FindBy(linkText = "Request Loan")
    protected WebElement requestLoanLink;
    @FindBy(id = "amount")
    protected WebElement amount;
    @FindBy(id = "downPayment")
    protected WebElement downPayment;
    @FindBy(css = "[value='Apply Now']")
    protected WebElement applyButton;


    // Functions
    public void requestLoan(int loanAmount, int loanDownPayment) {
        requestLoanLink.click();
        amount.sendKeys("" + loanAmount);
        downPayment.sendKeys("" + loanDownPayment);
        applyButton.click();
    }

    public void validateLoanRequest() {
        try {
            Thread.sleep(1000);
            Assert.assertEquals(titleMessage.getText(), "Loan Request Processed");
            ExtentManager.pass("Action of Loan Request completed");
        } catch (InterruptedException e) {
            Assert.fail("Validation failed" + e);
        }
    }
}
