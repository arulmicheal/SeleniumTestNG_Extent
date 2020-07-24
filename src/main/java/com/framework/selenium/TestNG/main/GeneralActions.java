package com.framework.selenium.TestNG.main;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aventstack.extentreports.Status;
import com.framework.selenium.TestNG.reporter.LOGGER;
import com.framework.selenium.TestNG.reporter.Reporter;
import com.framework.selenium.TestNG.util.Data.RestWebserviceRequest;

public class GeneralActions extends TestNGMethods {
	WebDriver driver;
	RestWebserviceRequest webReq = new RestWebserviceRequest();
	@FindBy(xpath = "//a")
	private List<WebElement> eleLinks;

	public GeneralActions() {
		initPageFactory(this);
		driver = getDriver();
	}

	public void validateLinks() {
		for (String eachLink : getLinksFromWebPage()) {
			try {
				if (eachLink.startsWith("http")) {
					webReq.httpRequest(eachLink, "get", "", null, null);
					if (webReq.getResponseCode() == 200) {
						Reporter.log(Status.PASS, "\"" + eachLink + "\" is valid!");
					} else {
						Reporter.log(Status.FAIL, "\"" + eachLink + "\" is not valid!");
					}
				} else {
					System.out.println("Not valid URL : " + eachLink);
				}

			} catch (Exception ex) {
				Reporter.log(Status.FAIL, ex, true);
				LOGGER.error(ex);
			}
		}
	}

	public List<String> getLinksFromWebPage() {
		List<String> listOfLinks=new ArrayList<>();
		for (WebElement eachElement : eleLinks) {
			String strLink = eachElement.getAttribute("href");
			try {
				if (strLink.startsWith("http")) {
					listOfLinks.add(strLink);
				}
			} catch (NullPointerException e) {
				System.out.println("No href at anchor tag! " + getElementXpath(eachElement));
			} catch (Exception ex) {
				Reporter.log(Status.FAIL, ex, true);
				LOGGER.error(ex);
			}
		}
		return listOfLinks;
	}

	public String getElementXpath(WebElement element) {
		try {
			if (element.getAttribute("name") != null && !element.getAttribute("name").isEmpty()) {
				return "//" + element.getTagName() + "[@name='" + element.getAttribute("name") + "']";
			} else if (element.getAttribute("id") != null && !element.getAttribute("id").isEmpty()) {
				return "//" + element.getTagName() + "[@id='" + element.getAttribute("id") + "']";
			} else if (element.getAttribute("class") != null && !element.getAttribute("class").isEmpty()) {
				return "//" + element.getTagName() + "[@class='" + element.getAttribute("class") + "']";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public void executeJSscript(String script, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(script, element);

	}

	public void getAllattributes(WebElement element) {
		executeJSscript(
				"var items = {}; for (index = 0; index < arguments[0].attributes.length; ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; return items;",
				element);
	}

}
