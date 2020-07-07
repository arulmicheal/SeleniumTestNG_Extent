package com.framework.selenium.TestNG;

import com.application.pageObject.LoginPage;
import com.framework.selenium.TestNG.Webdriver.DriverFactory;

public class MainClass {
	
	public static void main(String[] args) 
	{
		try
		{
			//new Login().loginApplication();
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally {
			DriverFactory.closeDriver();
		}

	}

}
