package com.application.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.framework.selenium.TestNG.main.TestNGMethods;

public class LoginPage extends TestNGMethods{
	WebDriver driver;
	@FindBy(xpath="//input[@name='username']")
	WebElement eleUserName; 
	@FindBy(xpath="//input[@name='password']")
	WebElement elePassword; 
	@FindBy(xpath="//input[@value='Login'][@id='OKButton']")
	WebElement eleLoginButton; 
	public LoginPage()
	{
		driver=getDriver();
		AjaxElementLocatorFactory ajaxFactory= new AjaxElementLocatorFactory(driver, 10);
		PageFactory.initElements(ajaxFactory, this);
	}

	public void launchApp(String strURL) 
	{
		loadUrl(strURL);
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
