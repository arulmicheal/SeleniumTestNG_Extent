package com.application.testMethods;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.application.pageObject.HomePage;
import com.application.pageObject.LoginPage;
import com.aventstack.extentreports.Status;
import com.framework.selenium.TestNG.reporter.LOGGER;
import com.framework.selenium.TestNG.reporter.Reporter;
import com.framework.selenium.TestNG.util.Data.TestData;

public class Login {
	LoginPage loginPage;
	HomePage homePage;
	public Login()
	{
		loginPage= new LoginPage();
		homePage =new HomePage();
	}
	@Test(dataProvider="csvdata",dataProviderClass=TestData.class)
	public void loginApplication(Object[] objectData) 
	{
		try
		{
			//String appURL=objectData[0].toString(); 
			String appURL=TestData.getData(1, "URL"); 
			String strUserName=objectData[1].toString(); 
			String strPassword=objectData[2].toString(); 
			loginPage.launchApp(appURL);
			Reporter.log(Status.INFO, "Application Url "+appURL+" launched!");
			loginPage.setUsername(strUserName);
			loginPage.setPassword(strPassword);
			Reporter.log(Status.INFO, "Username and Password entered!");
			loginPage.clickLoginButton();
			String strXpathTitle=homePage.getWecomeText();
			Assert.assertEquals(strXpathTitle, "Welcome to the Automation Framework System Manager","Both are equal!");
			Reporter.log(Status.PASS, "Application Login Success!",true);
			Thread.sleep(5000);
			Reporter.endTest();
		}catch(Exception ex)
		{
			Reporter.log(Status.FAIL, ex);
			LOGGER.error(ex);
			Assert.fail();
		}

	}

}
