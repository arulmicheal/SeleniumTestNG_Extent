package com.framework.selenium.TestNG.reporter;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;


public class Reporter {
	ExtentReports extentReports;
	ExtentTest loggerTest;
	static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
	static ExtentReports extent = ReportManager.getInstance();
	
	
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
	public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
		File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String imgPath = "./reports/Screenshots/screenshot_"+ System.currentTimeMillis() + ".png";
		File path = new File("./Reports/" + imgPath);
		FileUtils.copyFile(sourceFile, path);
		return imgPath;
		}
	

}
