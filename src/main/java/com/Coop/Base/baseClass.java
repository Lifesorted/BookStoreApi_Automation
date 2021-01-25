package com.Coop.Base;

import java.lang.reflect.Method;

import javax.xml.stream.events.StartDocument;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.mongodb.diagnostics.logging.Logger;

public class baseClass {

	protected static ExtentReports extentreports;
	protected static ExtentTest Logger;
	public ExtentTest extentTest;
	
	@BeforeSuite
	public void getReport() {
		ExtentHtmlReporter reporter=new ExtentHtmlReporter("./Reports/testrunreport.html");
		extentreports=new ExtentReports();
		extentreports.attachReporter(reporter);
		extentTest=extentreports.createTest(getClass().getSimpleName());
	}
	
	@AfterMethod
	public void logsReport(ITestResult result) {
		logReport(result);
		
	}
	

	
	@AfterSuite
	public void flushReport() {
		extentreports.flush();
	}
	
	
	public void logReport(ITestResult result) {
		if (result.getStatus()==result.SUCCESS) {
			extentTest.log(Status.PASS, result.getName()+"Passed");
			//extentTest.log(Status.ERROR, result.getThrowable());
		}else if (result.getStatus()==result.SKIP) {
			extentTest.log(Status.ERROR, result.getName()+"SKIPPED");
			extentTest.log(Status.ERROR, result.getThrowable());
		}else if(result.getStatus()==result.FAILURE) {
			extentTest.log(Status.ERROR, result.getName()+"Failed");
			extentTest.log(Status.ERROR, result.getThrowable());
		}
		
	}
}
