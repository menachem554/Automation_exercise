package Tests;

import Listener.ExtentManager;
import Pages.BasePage;
import Pages.RequestLoanPage;
import org.testng.annotations.Test;

public class RequestLoanTest extends BaseTest {
    BasePage basePage = new BasePage();

    // Complete process of request loan
    @Test(priority = 1)
    public void requestLoan() {
        ExtentManager.log("REQUEST LOAN");
        ExtentManager.log("Test 1/3");
        RequestLoanPage requestLoan = new RequestLoanPage();
        try {
            requestLoan.requestLoan(50, 25);
            requestLoan.validateLoanRequest();
        } catch (Exception e) {
            basePage.handleInternalError();
        }
    }


    // Check if the system detects an attempt to leave blank fields in the loan request
    @Test(priority = 2)
    public void requestLoanWithEmptyField() {
        ExtentManager.log("Test 2/3");
        RequestLoanPage requestLoan = new RequestLoanPage();
        try {
            requestLoan.requestLoan(0, 25);
            Thread.sleep(1000);
            if (basePage.pageContains("We cannot grant a loan in that amount with your available funds and down payment.")) {
                ExtentManager.pass("The system responded as expected💪");
            } else if (basePage.pageContains("An internal error has occurred and has been logged.")) {
                ExtentManager.warning("The system rejected an attempt to enter invalid values," +
                        " but displayed an unrelated error message🙄");
            }
        } catch (Exception e) {
            basePage.handleInternalError();
        }
    }

    // Check if the system detects an attempt to enter in the down payment
    // a higher number than in requested amount
    @Test(priority = 3)
    public void requestIncorrectLoan() {
        ExtentManager.log("Test 3/3");
        RequestLoanPage requestLoan = new RequestLoanPage();
        try {
            requestLoan.requestLoan(25, 100);
            Thread.sleep(1000);
            if (basePage.pageContains("We cannot grant a loan in that amount with your available funds and down payment.")) {
                ExtentManager.pass("The system rejected an attempt to request a loan with invalid values💪");
            } else {
                ExtentManager.fail("The system did not respond as expected😡");
            }
        } catch (Exception e) {
            basePage.handleInternalError();
        }
    }
}
