package Pages;

import org.openqa.selenium.WebDriver;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverManager() {

    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver driver) {
        DriverManager.driver.set(driver);
    }

    public static void quitDriver() {
        DriverManager.driver.get().manage().deleteAllCookies();
        DriverManager.driver.get().quit();
        driver.remove();
    }
}
