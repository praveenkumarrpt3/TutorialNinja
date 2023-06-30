package com.tutorialninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utilities.ExtentReporter;

public class Mylisteners implements ITestListener{
	ExtentReports extentReport;
	ExtentTest extentTest;
	@Override
	public void onStart(ITestContext context) {
		extentReport = ExtentReporter.generateExtendReport();

	}

	@Override
	public void onTestStart(ITestResult result) {

		String testName = result.getName();
		extentTest = extentReport.createTest(testName);
		extentTest.log(Status.INFO, testName+" Started executing");

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testName = result.getName();
		extentTest.log(Status.PASS, testName +" got successfully executed");

	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getName();
		WebDriver driver = null;
		try {
			driver= (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		File srcScreenshot =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destinationFilePath= System.getProperty("user.dir")+"\\Screenshots\\"+testName+".png";
		try {
			FileHandler.copy(srcScreenshot, new File(destinationFilePath));
		}catch(Throwable e) {
			e.printStackTrace();
		}
		extentTest.addScreenCaptureFromPath(destinationFilePath);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, testName +" got Failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testName = result.getName();
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testName +" got Skipped");
		
	}

	@Override
	public void onFinish(ITestContext context) {
		extentTest.log(Status.INFO, " Complected execution");
		extentReport.flush();
		
		File file = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentreport.html");
		try {
			Desktop.getDesktop().browse(file.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
