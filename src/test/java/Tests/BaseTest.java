package Tests;

import Pages.DriverManager;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

@Listeners({Listener.TestListener.class})
public class BaseTest {
    protected WebDriver driver;
    Properties prop = new Properties();

    @BeforeTest
    public void setup() throws IOException {
        // Chrome options setting
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");

        // Get the url for the test, and the browser from config file
        FileInputStream data = new FileInputStream(
                System.getProperty("user.dir") + "\\src\\main\\resources\\config.properties");
        prop.load(data);

        if (prop.getProperty("browser").equals("chrome")) {
            ChromeDriverManager.getInstance().setup();
            driver = new ChromeDriver(options);
        }

        // Initialization
        DriverManager.setDriver(driver);
        driver.get(prop.getProperty("url"));
    }

    @AfterTest
    public void tearDown() {
//        DriverManager.quitDriver();
    }
}
