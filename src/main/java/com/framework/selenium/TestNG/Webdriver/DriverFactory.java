package com.framework.selenium.TestNG.Webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class DriverFactory {
	private static WebDriver driver;
	private static String DriverDirPath="./Drivers/";
	private static void initializeDriver() throws Exception
	{
		String strBrowserName="chrome";
		System.setProperty("webdriver.chrome.driver", DriverDirPath+"chromedriver.exe");
		System.setProperty("webdriver.ie.driver", DriverDirPath+"IEDriverServer.exe");
		System.setProperty("webdriver.gecko.driver", DriverDirPath+"geckodriver.exe");
		setBrowser(strBrowserName);
		driver.manage().window().maximize();
		deleteCookies();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		setImplicitWait(10);
	}
	private static void setBrowser(String strBrowserName) throws Exception
	{
		switch(strBrowserName.toLowerCase())
		{
			case "chrome":
				driver=new ChromeDriver();
				break;
			case "ie":
				driver=new InternetExplorerDriver();
				break;
			case "firefox":
				driver=new FirefoxDriver();
				break;
			default:
				driver=new ChromeDriver();
		}
	}
	public static void loadUrl(String strUrl)
	{
		driver.get(strUrl);
	}
	public static WebDriver getDriver()
	{
		if(driver==null)
		{
			try {
				initializeDriver();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return driver;
	}
	public static void setImplicitWait(int iTimeoutInSeconds)
	{
		driver.manage().timeouts().implicitlyWait(iTimeoutInSeconds, TimeUnit.SECONDS);
	}
	@AfterSuite
	public static void closeDriver()
	{
		if(driver!=null)
		{
			driver.quit();
			driver=null;
		}
	}
	public static void closeBrowserWindow() throws Exception
	{
		if(driver!=null)
		{
			driver.close(); 
		}
	}
	public static void restartBrowser() throws Exception
	{
		if(driver!=null)
		{
			String strURL=driver.getCurrentUrl();
			deleteCookies();       
			closeDriver();
			initializeDriver();
			driver.get(strURL);
		}
	}
	public static void deleteCookies() throws Exception
	{
		if(driver!=null)
		{
			driver.manage().deleteAllCookies();         
		}
	}
}
