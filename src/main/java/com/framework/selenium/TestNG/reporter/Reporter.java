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
		log(status, message, false);

	}

	public static synchronized void log(Status status, String message, boolean shouldCaptureScreen) {
		String strImagePath="";
		MediaEntityModelProvider screenshot=null;

		try {
			
			if (shouldCaptureScreen) {
				strImagePath = getScreenshot(driver, "image");
				screenshot= MediaEntityBuilder.createScreenCaptureFromPath(strImagePath).build();
			}
			ExtentTest test = Reporter.getTest();
			switch (status.toString().toLowerCase()) {
			case "pass":
				test.log(status, message,screenshot);
				break;
			case "fail":
				test.log(status, message,screenshot);
				break;
			case "fatal":
			case "error":
				test.log(status, message);
				break;
			case "warning":
			case "info":
			case "skip":
				test.log(status, message);
				break;
			case "debug":
				test.log(status, message);
				break;
			default:
				test.log(status, message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
