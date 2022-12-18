package Pages;

import Listener.ExtentManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BasePage extends AbstractPageObject {
    public BasePage() {

    }

    // Global variables
    public static String screenShotDestinationPath;
    public static String sAccountId;
    public static String sNewAccountId;
    public static String userName;

    // Global Locators
    @FindBy(linkText = "Log Out")
    protected WebElement logout;
    @FindBy(css = "[id='leftPanel'] h2")
    protected WebElement customerLogin;
    @FindBy(css = "h1[class='title']")
    protected WebElement titleMessage;

    // Snapshot function
    public static String takeSnapshot(String name) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
        File srcFile = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
        String destFile = System.getProperty("user.dir") + "\\screenshot\\" + timestamp + ".png";
        screenShotDestinationPath = destFile;

        try {
            FileUtils.copyFile(srcFile, new File(destFile));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return name;
    }

    // get destination
    public static String getScreenShotDestinationPath() {
        return screenShotDestinationPath;
    }

    // Wait for element
    public void waitForVisibility(WebElement element, int timer) throws IOException {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), timer);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    // Random char a-z 0-9
    public static String getRandomAlphabet(int length) {
        String AlphaNumericStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz0123456789";
        StringBuilder s = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int ch = (int) (AlphaNumericStr.length() * Math.random());
            s.append(AlphaNumericStr.charAt(ch));
        }

        return s.toString();
    }

    // Search for string in the page
    public static boolean pageContains(String message) {
        if (DriverManager.getDriver().getPageSource().contains(message)) {
            return true;
        }
        return false;
    }

    // Select function
    public Select select(WebElement element) {
        Select select = new Select(element);
        return select;
    }

    public void handleExistsUserError() {
        try {
            if (pageContains("This username already exists.")) {
                ExtentManager.skip("Username or password already exists." +
                        " I am trying to register again with a different name.");
                registerNewUser();
            }
        } catch (Exception e) {
            Assert.fail("Error😬" + e);
        }
    }

    public void handleInternalError() {
        if (pageContains("An internal error has occurred and has been logged.")) {
            ExtentManager.warning("The system encountered an internal error😡," +
                    " the reason for this could be that the user was disconnected from the system," +
                    " or deleted completely.");
        }
    }

    public void registerNewUser() {
        RegisterPage registerPage = new RegisterPage();
        registerPage.register("Yosef", "Cohen", "1414 President st",
                "new york", "New York", "11213", "0123456789",
                "RF245", "Yosef" + getRandomAlphabet(2),
                "test770", "test770");
        userName = registerPage.extractUserName("Yosef");
    }

}
