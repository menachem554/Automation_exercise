package Tests;

import Listener.ExtentManager;
import Pages.BasePage;
import Pages.OpenNewAccountPage;
import org.testng.annotations.Test;

public class OpenNewAccountTest extends BaseTest {
    BasePage basePage = new BasePage();

    // Checking that the system opens a new account
    @Test(priority = 1)
    public void openNewCheckingAccount() throws InterruptedException {
        OpenNewAccountPage newAccount = new OpenNewAccountPage();
        ExtentManager.log("OPEN NEW ACCOUNT (2 tests)");
        ExtentManager.log("Test 1/2");
        try {
            newAccount.openNewAccount(null);
            newAccount.validateAccountOpening();
        } catch (InterruptedException e) {
            basePage.handleInternalError();
        }
    }

    // Check that the system opens a new savings account
    @Test(priority = 1)
    public void openNewSavingAccount() throws InterruptedException {
        ExtentManager.log("Test 2/2");
        try {
            OpenNewAccountPage newAccount = new OpenNewAccountPage();
            newAccount.openNewAccount("SAVINGS");
            newAccount.validAccountType();
        } catch (Exception e) {
            basePage.handleInternalError();
        }


    }
}
