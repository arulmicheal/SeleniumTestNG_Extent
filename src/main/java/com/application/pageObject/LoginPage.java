package com.application.pageObject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.framework.selenium.TestNG.Webdriver.DriverFactory;

public class LoginPage extends DriverFactory{
	WebDriver driver=getDriver();
	@FindBy(xpath="//input[@name='username']")
	WebElement eleUserName; 
	@FindBy(xpath="//input[@name='password']")
	WebElement elePassword; 
	@FindBy(xpath="//input[@value='Login'][@id='OKButton']")
	WebElement eleLoginButton; 
	public LoginPage()
	{
		AjaxElementLocatorFactory ajaxFactory= new AjaxElementLocatorFactory(driver, 10);
		PageFactory.initElements(ajaxFactory, this);
	}
	public void setUsername(String strUsername) throws Exception
	{
		eleUserName.sendKeys(strUsername);
	}
	public void setPassword(String strPassword) throws Exception
	{
		elePassword.sendKeys(strPassword);
	}
	public void clickLoginButton() throws Exception
	{
		eleLoginButton.click();
	}

}
