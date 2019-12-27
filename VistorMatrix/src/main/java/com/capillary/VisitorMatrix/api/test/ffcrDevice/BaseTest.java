package com.capillary.VisitorMatrix.api.test.ffcrDevice;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
 
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.capillary.VisitorMatrix.qa.framework.Requests.RequestResponseLogger;
 
public class BaseTest
{
    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest Logger;
     
    @BeforeSuite
    public void setUp()
    {
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/EmailableOverviewReoprt.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
         
        extent.setSystemInfo("OS", "Mac Sierra");
        extent.setSystemInfo("Host Name", "Arun");
        extent.setSystemInfo("Environment", "Nightly");
        extent.setSystemInfo("User Name", "Arun Vijay");
         
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("AutomationTesting.in Demo Report");
        htmlReporter.config().setReportName("My Own Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.DARK);
    }
    
    @BeforeMethod
    public void beforeMethod(ITestResult result)
    {
    	Logger = extent.createTest(result.getMethod().getDescription().toString());
    	Logger.log(Status.INFO, "The parameters are" + result.getParameters().toString());
    }
     
    @AfterMethod(alwaysRun = true)
    public void getResult(ITestResult result)
    {
        if(result.getStatus() == ITestResult.FAILURE)
        {
        	Logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" Test case FAILED due to below issues:", ExtentColor.RED));
        	Logger.fail(result.getThrowable());
        }
        else if(result.getStatus() == ITestResult.SUCCESS)
        {
        	Logger.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
        }
        else
        {
        	Logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" Test Case SKIPPED", ExtentColor.ORANGE));
        	Logger.skip(result.getThrowable());
        }
        extent.flush();
    }
    
    @AfterTest
    public void afterTest()
    {
    	extent.flush();
    }
    @AfterSuite
    public void tearDown()
    {
        extent.flush();
    }
    
    public void logInfo(String message)
    {
    	Logger.info(MarkupHelper.createCodeBlock(message));
    }
    public void Log(String message)
    {
    	Logger.log(Status.INFO, message);
    }
}
