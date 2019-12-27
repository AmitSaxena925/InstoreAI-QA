package com.capillary.VisitorMatrix.qa.framework.listeners;

import java.io.File;
import java.lang.reflect.Method;

import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class BaseClassLogger {
	public ExtentReports reports;
	public ExtentTest logger;

	@BeforeSuite
	public void setUpSuite()
	{
		ExtentHtmlReporter reporter=new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/Reports/DeviceAPIAutomation.html"));
		reports = new ExtentReports();
		reports.attachReporter(reporter);
		logger = reports.createTest("Started automation");
	}
	@AfterSuite
	public void tearDown() {
		reports.flush();
	}
	
	
}
