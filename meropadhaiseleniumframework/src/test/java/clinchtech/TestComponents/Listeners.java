package clinchtech.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import clinchtech.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
    
    ExtentTest test;
    ExtentReports extent = ExtentReporterNG.getReportObject();

    @Override
    public void onTestStart(ITestResult result){
        test = extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result){
        test.log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result){
        test.fail(result.getThrowable());

        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Step 1: Take the screenshot of the fail situation
        String filePath = null;
        try {
            filePath = getScreenshot(result.getMethod().getMethodName(), driver);
        } catch (IOException e) {
            e.printStackTrace();
        }
        test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
        // Step 2: Attach the screenshot on the ExtentReports html file
    }

    @Override
    public void onFinish(ITestContext context){
        extent.flush();
    }

    @Override
    public void onStart(ITestContext arg0) {
        // throw new UnsupportedOperationException("Unimplemented method 'onStart'");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
        // throw new UnsupportedOperationException("Unimplemented method 'onTestFailedButWithinSuccessPercentage'");
    }

    @Override
    public void onTestSkipped(ITestResult arg0) {
        // throw new UnsupportedOperationException("Unimplemented method 'onTestSkipped'");
    }

}
