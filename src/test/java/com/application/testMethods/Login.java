package com.application.testMethods;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.application.pageObject.HomePage;
import com.application.pageObject.LoginPage;
import com.aventstack.extentreports.Status;
import com.framework.selenium.TestNG.reporter.Reporter;

public class Login {
	LoginPage loginPage;
	HomePage homePage;
	public Login()
	{
		loginPage= new LoginPage();
		homePage =new HomePage();
	}
	@Test
	@Parameters("appURL")
	public void loginApplication(String appURL) 
	{
		try
		{
			Reporter.getTest().log(Status.PASS, "Done!");
			loginPage.launchApp(appURL);
			/*loginPage.setUsername("arulprak");
			loginPage.setPassword("Jun@9940093354");
			loginPage.clickLoginButton();
			String strXpathTitle=homePage.getWecomeText();
			Assert.assertEquals(strXpathTitle, "Welcome to the Automation Framework System Manager","Both are equal!");*/
			Reporter.getTest().log(Status.FAIL, "Done!");
			Thread.sleep(5000);
			Reporter.endTest();
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}

	}

}
