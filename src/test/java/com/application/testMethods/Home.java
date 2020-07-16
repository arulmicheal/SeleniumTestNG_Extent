package com.application.testMethods;

import org.testng.annotations.Test;

import com.application.pageObject.HomePage;
import com.application.pageObject.LoginPage;
import com.aventstack.extentreports.Status;
import com.framework.selenium.TestNG.reporter.Reporter;

public class Home {
	LoginPage loginPage= new LoginPage();
	HomePage homePage =new HomePage();
	@Test
	public void homePageTest() 
	{
		try
		{
			Reporter.getTest().log(Status.PASS, "Done!");
			Reporter.getTest().log(Status.FAIL, "Done!");
			Thread.sleep(5000);
			Reporter.endTest();
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}

	}

}
