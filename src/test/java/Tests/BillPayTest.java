package Tests;

import Listener.ExtentManager;
import Pages.BasePage;
import Pages.BillPayPage;
import org.testng.annotations.Test;

public class BillPayTest extends BaseTest {
    BasePage basePage = new BasePage();

    // Complete process of bill pay
    @Test(priority = 1)
    public void billPay() {
        BillPayPage billPay = new BillPayPage();
        ExtentManager.log("BILL PAY");
        ExtentManager.log("Test 1/3");
        try {
            billPay.billPay("Yosef Cohen", "1414 President st", "New York","NY",
                    "11213", "0123456789", BasePage.sAccountId,
                    BasePage.sAccountId, "50");
            billPay.validateBillPay();
        } catch (Exception e) {
            basePage.handleInternalError();
        }
    }

    // Check that the system displays an error message about skipping a required field
    @Test(priority = 2)
    public void billPaySkippingValues() {
        BillPayPage billPay = new BillPayPage();
        ExtentManager.log("Test 2/3");
        try {
            Thread.sleep(1000);
            billPay.billPay(null, "1414 President st", "New York","NY",
                    "11213", "0123456789", BasePage.sAccountId,
                    BasePage.sAccountId, "50");
            basePage.takeSnapshot("name");
            Thread.sleep(1500);
            if (basePage.pageContains("Payee name is required.")) {
                ExtentManager.pass("The system rejected the attempt to bill pay without a first name💪");
            } else {
                ExtentManager.fail("System failed in rejecting the attempt to register without a first name👎");
            }
        } catch (Exception e) {
            basePage.handleInternalError();
        }
    }

    // Check if the system detects an attempt to enter invalid amount
    @Test(priority = 3)
    public void billPayWithLetters() {
        BillPayPage billPay = new BillPayPage();
        ExtentManager.log("Test 3/3");
        try {
            billPay.billPay("Moses", "1414 President st", "New York","NY",
                    "11213", "0123456789", BasePage.sAccountId,
                    BasePage.sAccountId, "test");
            Thread.sleep(1500);
            if (basePage.pageContains("Please enter a valid amount.")) {
                ExtentManager.pass("The system rejected the attempt to bill pay with invalid amount💪");
            } else {
                ExtentManager.fail("System failed in rejecting the attempt to register without a first name👎");
            }
        } catch (Exception e) {
            basePage.handleInternalError();
        }
    }
}
