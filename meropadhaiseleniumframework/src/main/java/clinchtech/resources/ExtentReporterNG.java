package clinchtech.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG{

    public static ExtentReports getReportObject(){
        String path = System.getProperty("user.dir") + "//reports//index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Meropadhai Web Automation Result");
        reporter.config().setDocumentTitle("Automation Report");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Automation Engineer:", "Suraj Nepali");
        return extent;
    }

}