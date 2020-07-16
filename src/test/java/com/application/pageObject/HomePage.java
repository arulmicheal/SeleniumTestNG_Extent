package com.application.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.framework.selenium.TestNG.main.TestNGMethods;

public class HomePage extends TestNGMethods{
	WebDriver driver;
	@FindBy(xpath="//*[text()='Welcome to the Automation Framework System Manager']")
	WebElement eleHomePageText; 
	
	public HomePage()
	{
		driver=getDriver();
		AjaxElementLocatorFactory ajaxFactory= new AjaxElementLocatorFactory(driver, 10);
		PageFactory.initElements(ajaxFactory, this);
	}
	public String getWecomeText() throws Exception
	{
		return eleHomePageText.getText();
	}
}

