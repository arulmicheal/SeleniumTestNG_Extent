package com.framework.selenium.TestNG.reporter;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.framework.selenium.TestNG.main.Configuration;
import com.framework.selenium.TestNG.main.General;

@SuppressWarnings("deprecation")
public class ReportManager {
	private static ExtentReports extent;
	public static ExtentReports getInstance() {
		if (extent == null)
			createInstance();
		return extent;
	}

	private static ExtentReports createInstance() {
		String filePath = General.createFilePath(Configuration.currentReportLocation + Configuration.fileSeperator + Configuration.reportFileName);
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(filePath);
		htmlReporter.loadXMLConfig(Configuration.reportConfig);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		// Set environment details
		extent.setSystemInfo("Author", System.getProperty("user.name"));
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("Type", "FAT");

		return extent;
	}

	public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
		File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String imgPath = Configuration.imagesDirectory + "\\" + screenshotName + General.getCurrentDateTime("yyyyMMdd_hhmmss") + ".png";
		File path = new File(imgPath);
		FileUtils.copyFile(sourceFile, path);
		return imgPath;
	}
}
