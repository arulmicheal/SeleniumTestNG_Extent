package com.application.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.framework.selenium.TestNG.Webdriver.DriverFactory;

public class HomePage extends DriverFactory{
	WebDriver driver=getDriver();
	@FindBy(xpath="//*[text()='Welcome to the Automation Framework System Manager']")
	WebElement eleHomePageText; 
	
	public HomePage()
	{
		AjaxElementLocatorFactory ajaxFactory= new AjaxElementLocatorFactory(driver, 10);
		PageFactory.initElements(ajaxFactory, this);
	}
	public String getWecomeText() throws Exception
	{
		return eleHomePageText.getText();
	}
}

