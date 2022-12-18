package Listener;

import Pages.BasePage;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    @Override
    public synchronized void onStart(ITestContext context) {
        ExtentManager.getReport();
        ExtentManager.createTest(context.getName(), context.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // empty
    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {
        ExtentManager.getTest().fail(result.getThrowable());

        try {
            System.out.println("Test Fail: " + result.getName());
            BasePage.takeSnapshot(result.getMethod().getMethodName());
            ExtentManager.attachImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // empty
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // empty
    }

    @Override
    public void onTestStart(ITestResult result) {
        // empty
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.flushReport();
    }
}
