package com.framework.selenium.TestNG.main;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverFactory {
	private static WebDriver driver;
	private static String DriverDirPath="./Drivers/";
	private static String browserNameLocal;
	
	public DriverFactory(String browserType)
	{
		setBrowser(browserType);
	}
	
	public DriverFactory()
	{
		
	}
	public void initializeDriver() throws Exception
	{
		//Setting driver location
		System.setProperty("webdriver.chrome.driver", DriverDirPath+"chromedriver.exe");
		System.setProperty("webdriver.ie.driver", DriverDirPath+"IEDriverServer.exe");
		System.setProperty("webdriver.gecko.driver", DriverDirPath+"geckodriver.exe");
		startDriver();
		driver.manage().window().maximize();
		deleteCookies();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		setImplicitWait(10);
	}

	private void setBrowser(String browserType) 
	{
		browserNameLocal=browserType;
	}
	private void startDriver() throws Exception
	{
		switch(browserNameLocal.toLowerCase())
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
	public void loadUrl(String strUrl)
	{
		driver.get(strUrl);
	}
	public WebDriver getDriver()
	{
		if(driver==null)
		{
			try {
				initializeDriver();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return driver;
	}
	public WebDriver getDriver(String browserName)
	{
		if(driver!=null)
		{
			closeDriver();
			browserNameLocal=browserName;
			try {
				initializeDriver();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return driver;
	}
	public void setImplicitWait(int iTimeoutInSeconds)
	{
		driver.manage().timeouts().implicitlyWait(iTimeoutInSeconds, TimeUnit.SECONDS);
	}

	public void closeDriver()
	{
		if(driver!=null)
		{
			driver.quit();
			driver=null;
		}
	}
	public void closeBrowserWindow() throws Exception
	{
		if(driver!=null)
		{
			driver.close(); 
		}
	}
	public void restartBrowser() throws Exception
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
	public void deleteCookies() throws Exception
	{
		if(driver!=null)
		{
			driver.manage().deleteAllCookies();         
		}
	}
}
