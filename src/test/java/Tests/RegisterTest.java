package Tests;

import Listener.ExtentManager;
import Pages.BasePage;
import Pages.RegisterPage;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegisterTest extends BaseTest {
     BasePage basePage = new BasePage();

     // Complete process of user registration
    @Test(priority = 1)
    public void register() {
        RegisterPage registerPage = new RegisterPage();
        ExtentManager.log("REGISTER TEST (3 tests)");
        ExtentManager.log("Test 1/3");
        try {
            registerPage.register("Yosef", "Cohen", "1414 President st",
                    "new york", "New York", "11213", "0000000000",
                    "RF245", "Yosef" + basePage.getRandomAlphabet(2),
                    "test770", "test770");
            basePage.userName = registerPage.extractUserName("Yosef");
            registerPage.validateRegistration();
            registerPage.logout();
        } catch (Exception e) {
            basePage.handleExistsUserError();
            registerPage.logout();
        }
    }

    // Check that the system displays an error message about skipping a required field
    @Test(priority = 2)
    public void registerWithSkippingField() throws IOException, InterruptedException {
        ExtentManager.log("Test 2/3");
        RegisterPage registerPage = new RegisterPage();
        registerPage.register(null, "Cohen", "1414 President st",
                "new york", "New York", "11213", "0000000000",
                "RF245", "Yosef" + basePage.getRandomAlphabet(2),
                "test770", "test770");

        if (basePage.pageContains("First name is required.")) {
            ExtentManager.pass("The system rejected the attempt to register without a first name💪");
        } else if (basePage.pageContains("Your account was created successfully.")) {
            ExtentManager.fail("System failed in rejecting the attempt to register without a first name👎");
        }
    }

    // Check that the system displays an error message in fields where invalid values have been entered
    @Test(priority = 3)
    public void registerWithAsterisksField() throws IOException, InterruptedException {
        ExtentManager.log("Test 3/3");
        RegisterPage registerPage = new RegisterPage();
        registerPage.register("***", "Cohen", "1414 President st",
                "new york", "New York", "11213", "0000000000",
                "RF245", "Yosef" + basePage.getRandomAlphabet(2),
                "test770", "test770");

        if (basePage.pageContains("enter valid values")) {
            ExtentManager.pass("The system rejected the attempt to register with invalid values💪");
        } else if (basePage.pageContains("Your account was created successfully.")) {
            registerPage.logout();
            ExtentManager.fail("System failed in rejecting the attempt to register with invalid values👎");
        }
    }
}
