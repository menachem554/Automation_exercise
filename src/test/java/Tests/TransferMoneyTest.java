package Tests;

import Listener.ExtentManager;
import Pages.BasePage;
import Pages.TransferMoneyPage;
import org.testng.annotations.Test;

public class TransferMoneyTest extends BaseTest {
    BasePage basePage = new BasePage();

    // Complete process of transferring money between accounts
    @Test(priority = 1)
    public void transferMoney() {
        try {
            ExtentManager.log("TRANSFER MONEY BETWEEN ACCOUNTS");
            ExtentManager.log("Test 1/4");
            TransferMoneyPage transfer = new TransferMoneyPage();
            transfer.transferMoney("50", true);
            transfer.validateTransferMoney();
        } catch (InterruptedException e) {
            basePage.handleInternalError();
        }

    }

    // Check that the system knows how to identify incorrect values entered in the amount field
    @Test(priority = 2)
    public void transferLetters() {
        try {
            ExtentManager.log("Test 2/4");
            TransferMoneyPage transfer = new TransferMoneyPage();
            transfer.transferMoney("test", true);
            Thread.sleep(1000);
            if (basePage.pageContains("Please enter a valid amount.")) {
                ExtentManager.pass("The system rejected an attempt to enter invalid amount💪");
            } else {
                ExtentManager.fail("The system did not respond as expected😡");
            }

        } catch (InterruptedException e) {
            basePage.handleInternalError();
        }
    }

    // Check if the system knows to reject an attempt to transfer funds from one account to the same account
    @Test(priority = 3)
    public void transferSameAccount() {
        try {
            ExtentManager.log("Test 3/4");
            TransferMoneyPage transfer = new TransferMoneyPage();
            transfer.transferMoney("50", false);
            Thread.sleep(500);
            if (basePage.pageContains("Select a different account")) {
                ExtentManager.pass("The system rejected an attempt to send money to same account 💪");
            } else {
                ExtentManager.fail("The system did not respond as expected😡");
            }

        } catch (InterruptedException e) {
            basePage.handleInternalError();
        }
    }

    // Check if the system knows to reject an attempt to transfer -50$ from one account to the another account
    @Test(priority = 4)
    public void transferInvalidAccount() {
        try {
            ExtentManager.log("Test 4/4");
            TransferMoneyPage transfer = new TransferMoneyPage();
            transfer.transferMoney("-50", true);
            Thread.sleep(500);
            if (basePage.pageContains("Choose a positive number")) {
                ExtentManager.pass("The system rejected an attempt to send -50$ 💪");
            } else {
                ExtentManager.fail("The system did not respond as expected😡");
            }

        } catch (InterruptedException e) {
            basePage.handleInternalError();
        }
    }
}
