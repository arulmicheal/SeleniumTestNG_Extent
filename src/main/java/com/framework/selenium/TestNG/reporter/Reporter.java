package com.framework.selenium.TestNG.reporter;

import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


public class Reporter {
	@SuppressWarnings("deprecation")
	ExtentHtmlReporter htmlReporter;
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
	@SuppressWarnings("deprecation")
	public void testReporter()
	{
		extentReports = new ExtentReports();
		try
		{
			htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"\\ExtentReportResults.html");
			//Config file
			htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"\\extentConfig.xml", true);
			extentReports.attachReporter(htmlReporter);
			extentReports.setSystemInfo("Host Name", "Sample Extent");
			extentReports.setSystemInfo("Environment", "Automation Testing");
			extentReports.setSystemInfo("User Name", "Arul Prakash");
			//Test start
			loggerTest=extentReports.createTest("Pass Test");
			loggerTest.log(Status.FAIL, "Fail report");
			///Pass
			loggerTest.log(Status.PASS, "Successfully started the html reporter");
			//Another test start 
			loggerTest=extentReports.createTest("Fail Test");
			///Fail
			loggerTest.log(Status.FAIL, "Fail report");
			//Another test start 
			loggerTest=extentReports.createTest("Skip Test");
			///Skip
			loggerTest.log(Status.SKIP, "Skip report");
			loggerTest.log(Status.FAIL, MarkupHelper.createLabel("Test case FAILED due to below issues:", ExtentColor.RED));;
			loggerTest.fail("Snapshot below: "+loggerTest.addScreenCaptureFromPath(System.getProperty("user.dir")+"\\image1.jpg"));

		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally
		{
			extentReports.flush();
		}
		

		
		
	}
	//public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
		//File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		//String imgPath = "./" +reports/Screenshots/" + screenshot + "_"+ System.currentTimeMillis() + ".png";
		//File path = new File("./Reports/" + imgPath);
		//FileUtils.copyFile(sourceFile, path);
		//return imgPath;
		//}
	

}
