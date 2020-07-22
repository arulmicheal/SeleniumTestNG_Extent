package com.framework.selenium.TestNG.reporter;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.framework.selenium.TestNG.main.DriverFactory;

public class Reporter extends ReportManager {
	ExtentReports extentReports;
	ExtentTest loggerTest;
	static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
	static ExtentReports extent = ReportManager.getInstance();
	static WebDriver driver = new DriverFactory().getDriver();

	public static synchronized ExtentTest getTest() {
		return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
	}

	public static synchronized void endTest() {
		extent.flush();
	}

	public static synchronized ExtentTest startTest(String testName) {
		ExtentTest test = extent.createTest(testName);
		extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
		return test;
	}

	public static synchronized void log(Status status, String message) {
		switch (status.toString().toLowerCase()) {
		case "fatal":
		case "error":
			log(status, message, true);
			break;
		default:
			log(status, message, false);
		}

	}

	public static synchronized void log(Status status, Exception ex) {
		log(status, ex.getMessage()+", Refer logfile for more details.");
	}

	public static synchronized void log(Status status, String message, boolean shouldCaptureScreen) {
		String strImagePath = "";
		MediaEntityModelProvider screenshot = null;
		ExtentTest test = getTest();
		try {

			if (shouldCaptureScreen) {
				strImagePath = getScreenshot(driver, "sc");
				screenshot = MediaEntityBuilder.createScreenCaptureFromPath(strImagePath).build();
			}
			test.log(status, message, screenshot);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void initializeReport()
	{
		Reporter.startTest("Initialize");
		Reporter.log(Status.INFO, "Reporter Started!");
	}
}
