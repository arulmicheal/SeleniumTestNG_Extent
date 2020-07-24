package com.framework.selenium.TestNG.main;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.Status;
import com.framework.selenium.TestNG.reporter.LOGGER;
import com.framework.selenium.TestNG.reporter.Reporter;
import com.framework.selenium.TestNG.util.Data.TestData;

public class TestNGMethods extends DriverFactory {

	public static DriverFactory driverFactory;

	@Parameters("browserType")
	public TestNGMethods(String browserType) {
		driverFactory = new DriverFactory(browserType);
	}

	public TestNGMethods() {

	}

	@BeforeSuite
	public void initialize() {
		try {
			Reporter.initializeReport();
			TestData.initializeData(Configuration.csvFilePath);
			LOGGER.initializeLog(Configuration.loggerFilePath);
		} catch (Exception ex) {
			Reporter.log(Status.FAIL, ex.getMessage(), true);
		}
	}

	@AfterSuite
	public void closeDriver() {
		try {
			driverFactory.closeDriver();
			LOGGER.closeLog();
		} catch (Exception ex) {
			ex.printStackTrace();
			Reporter.log(Status.FAIL, ex.getMessage());
		}
	}
}
