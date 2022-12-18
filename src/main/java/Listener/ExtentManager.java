package Listener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.text.SimpleDateFormat;
import java.util.Date;

import static Pages.BasePage.getScreenShotDestinationPath;

public class ExtentManager {
    public static ExtentReports extentReports;
    public static String extentReportPrefix;
    public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public static ExtentReports getReport() {
        setupExtentReport("para_bank");
        return extentReports;
    }

    public static ExtentReports setupExtentReport(String testName) {
        extentReports = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir") +
                "\\reports\\" + extentReportsPrefix_Name(testName) + ".html");

        extentReports.attachReporter(spark);
        spark.config().setDocumentTitle("Test result");
        spark.config().setTheme(Theme.DARK);

        return extentReports;
    }

    public static String extentReportsPrefix_Name(String testName) {
        String date = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
        extentReportPrefix = testName + "_" + date;

        return extentReportPrefix;
    }

    public static void flushReport() {
        extentReports.flush();
    }

    public synchronized static ExtentTest getTest() {
        return extentTest.get();
    }

    public synchronized static ExtentTest createTest(String name, String description) {
        ExtentTest test = extentReports.createTest(name, description);
        extentTest.set(test);
        return extentTest.get();
    }

    public static synchronized void log(String massage) {
        getTest().info(massage);
    }

    public synchronized static void pass(String massage) {
        getTest().pass(massage);
    }

    public static synchronized void skip(String massage) {
        getTest().skip(massage);
    }

    public static synchronized void warning(String massage) {
        getTest().warning(massage);
    }

    public static synchronized void fail(String massage) {
        getTest().fail(massage);
    }

    public synchronized static void attachImage() {
        getTest().addScreenCaptureFromPath(getScreenShotDestinationPath());
    }
}
