package com.tutorialsninja.qa.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {

	public static ExtentReports generateExtendReport() {
		ExtentReports extentReport = new ExtentReports();

		File extentReportFile = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentreport.html");
		ExtentSparkReporter sparkReporter= new ExtentSparkReporter(extentReportFile);

		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("Tutorial Ninja Test Automation Result");
		sparkReporter.config().setDocumentTitle("Tutorial Ninja Automation Report");
		sparkReporter.config().setTimeStampFormat("dd//MM//yyyy hh:mm:ss");

		extentReport.attachReporter(sparkReporter);

		Properties configProp = new Properties();
		try {
			File file = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\Config.properties");
			FileInputStream configFis = new FileInputStream(file);
			configProp.load(configFis);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		extentReport.setSystemInfo("Application URL", configProp.getProperty("base_url"));
		extentReport.setSystemInfo("Browser Name", configProp.getProperty("browserName"));
		extentReport.setSystemInfo("Email", configProp.getProperty("validUserName"));
		extentReport.setSystemInfo("Password", configProp.getProperty("validPassword"));
		extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentReport.setSystemInfo("User Name", System.getProperty("user.name"));
		extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));

		return extentReport;
	}
}
