package Tests;

import Listener.ExtentManager;
import Pages.BasePage;
import Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    BasePage basePage = new BasePage();

    // Check that the system displays an error message
    // for a login attempt with a user not saved in the system
    @Test(priority = 1)
    public void UnauthorizedLogin() {
        LoginPage loginPage = new LoginPage();
        ExtentManager.log("LOGIN TEST (3 tests)");
        ExtentManager.log("Test 1/3");
        loginPage.Login("Moses", "test770");
        if (basePage.pageContains("The username and password could not be verified.")) {
            ExtentManager.pass("The system responded as expected💪");

        } else if (basePage.pageContains("An internal error has occurred and has been logged.")){
            ExtentManager.warning("The system blocked unauthorized entry," +
                    " but displayed a rejection message unrelated to the situation🙄");
        }
    }

    // Check that the system displays an error message in case of missing username or password
    @Test(priority = 2)
    public void uncompletedLogin() {
        ExtentManager.log("Test 2/3");
        LoginPage loginPage = new LoginPage();
        loginPage.Login(null, "test770");
        if (basePage.pageContains("Please enter a username and password.")) {
            ExtentManager.pass("The system asks for a username and password💪");
        } else {
            ExtentManager.fail("The system did not respond as expected👎");
        }
    }

    // Complete process of Log In
    @Test(priority = 3)
    public void login() {
        ExtentManager.log("Test 3/3");
        try {
            LoginPage loginPage = new LoginPage();
            loginPage.Login(basePage.userName, "test770");
            loginPage.validateLogin();
        } catch (Exception e) {
            basePage.handleInternalError();
            Assert.fail("Error: " + e);
        }
    }
}
