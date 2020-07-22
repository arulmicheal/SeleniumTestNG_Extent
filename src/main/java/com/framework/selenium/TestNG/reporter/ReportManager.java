package com.framework.selenium.TestNG.reporter;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.framework.selenium.TestNG.main.General;

@SuppressWarnings("deprecation")
public class ReportManager {
	private static ExtentReports extent;
	private static String reportFileName = "Test-Automaton-Report" + ".html";
	private static String configFileName = "extentConfig.xml";
	private static String fileSeperator = System.getProperty("file.separator");
	private static String reportFilepath = System.getProperty("user.dir") + fileSeperator + "TestReport";
	private static String reportFileLocation = reportFilepath + fileSeperator + reportFileName;
	private static String imagesDirectory = reportFilepath + fileSeperator + "Screenshots";

	public static ExtentReports getInstance() {
		if (extent == null)
			createInstance();
		return extent;
	}

	public static ExtentReports createInstance() {
		String filePath = General.createFilePath(reportFileLocation);

		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(filePath);
		htmlReporter.loadXMLConfig(configFileName);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		//Set environment details
		extent.setSystemInfo("Author", "Arul");
		extent.setSystemInfo("OS", "Windows");
		extent.setSystemInfo("Type", "FAT");

		return extent;
	}
	public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
		File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String imgPath = imagesDirectory+"\\"+screenshotName+ getCurrentDateTime("yyyyMMdd_hhmmss") + ".png";
		File path = new File(imgPath);
		FileUtils.copyFile(sourceFile, path);
		return imgPath;
		}

	private static String getCurrentDateTime(String strDateFormat) throws Exception
	{
		return DateTimeFormatter.ofPattern(strDateFormat, Locale.UK).format(LocalDateTime.now());
		
	}
}
