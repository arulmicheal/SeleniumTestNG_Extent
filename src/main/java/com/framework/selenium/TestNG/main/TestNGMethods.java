package com.framework.selenium.TestNG.main;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.Parameters;

public class TestNGMethods extends DriverFactory{

	public static DriverFactory driverFactory;
	@Parameters("browserType")
	public TestNGMethods(String browserType)
	{
		driverFactory=new DriverFactory(browserType);
	}
	public TestNGMethods()
	{
			
	}
	
	@AfterSuite
	public void closeDriver()
	{
		driverFactory.closeDriver();
	}
}
