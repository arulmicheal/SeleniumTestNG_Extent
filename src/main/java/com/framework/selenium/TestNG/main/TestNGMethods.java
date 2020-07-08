package com.framework.selenium.TestNG.main;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.Parameters;
import org.testng.xml.XmlSuite;

public class TestNGMethods extends DriverFactory{

	public static DriverFactory driverFactory;
	public static XmlSuite suite;
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
