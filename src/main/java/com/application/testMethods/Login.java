package com.application.testMethods;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.application.pageObject.HomePage;
import com.application.pageObject.LoginPage;
import com.aventstack.extentreports.Status;
import com.framework.selenium.TestNG.Webdriver.DriverFactory;
import com.framework.selenium.TestNG.reporter.Reporter;

public class Login {
	LoginPage loginPage= new LoginPage();
	HomePage homePage =new HomePage();
	@Test
	public void loginApplication() 
	{
		try
		{
			Reporter.getTest().log(Status.PASS, "Done!");
			DriverFactory.loadUrl("https://czcholstc001173.prg-dc.dhl.com/fat_afsm");
			loginPage.setUsername("arulprak");
			loginPage.setPassword("Jun@9940093354");
			loginPage.clickLoginButton();
			String strXpathTitle=homePage.getWecomeText();
			Assert.assertEquals(strXpathTitle, "Welcome to the Automation Framework System Manager","Both are equal!");
			Reporter.getTest().log(Status.PASS, "Done!");
			Thread.sleep(5000);
			Reporter.endTest();
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}

	}

}
