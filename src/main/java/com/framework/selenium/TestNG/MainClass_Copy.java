package com.framework.selenium.TestNG;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainClass_Copy {
	static WebDriver driver;
	public static void initializeDriver()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\arulprak\\workspace\\Webdrivers\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
	}
	public static void main(String[] args) 
	{
		
		try
		{
			initializeDriver();
			driver.get("https://www.onlinepianist.com/virtual-piano");

			WebDriverWait webWait= new WebDriverWait(driver, Duration.ofSeconds(120));
			String strXpathNotes="//span[@id='letterNotesText']";
			webWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(strXpathNotes)));
			webWait.until(ExpectedConditions.elementToBeClickable(By.xpath(strXpathNotes)));
			
			String strXpathPiano="//div[@id='keyboard']";
			WebElement elementPiano= driver.findElement(By.xpath(strXpathPiano));
			
			driver.findElement(By.xpath(strXpathNotes)).click();
			
			//JavascriptExecutor jsExe= (JavascriptExecutor) driver;
			//jsExe.executeScript("arguments[0]", arg1);
			Actions actionBuilder= new Actions(driver);
			actionBuilder.moveToElement(elementPiano,-90,1).click().build().perform();
			Thread.sleep(5000);
			actionBuilder.moveToElement(elementPiano,-70,1).click().build().perform();
			Thread.sleep(5000);
			actionBuilder.moveToElement(elementPiano,-60,1).click().build().perform();
			
			Thread.sleep(5000);
			//Actions actionBuilder= new Actions(driver);
			//actionBuilder.moveToElement(elementPiano,1,1).click().build().perform();
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally {
			if(driver!=null)
			driver.quit();
		}

	}

}
