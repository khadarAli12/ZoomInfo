package org.Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Utils {

	WebDriver driver;

	public Utils(WebDriver driver) {
		this.driver = driver;
	}

	///////////General reusables///////////////////

	/**
	 * Hovers the mouse on the specified element
	 * @author Kadhar Ali
	 * @param driver
	 * @param targetElement
	 */
	public synchronized void mouseHoverOnSpecifiedElement(WebDriver driver,WebElement targetElement) {
		Actions action = new Actions(driver);
		action.moveToElement(targetElement).perform();
	}

	/**
	 * verifys The Specified Web Element Is Visible,
	 * if not test execution will fail
	 * @author Kadhar Ali
	 * @param driver
	 * @param targetElement
	 */
	public synchronized void verifyTheSpecifiedWebElementIsVisible(WebDriver driver,WebElement targetElement) {
		boolean displayed = targetElement.isDisplayed();
		if(displayed==true) {
			System.out.println("The banner is visible");
		}else {
			Assert.assertTrue(displayed, "The banner is not visible");
		}
	}

	/**
	 * clicks on the specified element,
	 * if exception occures, the catching will try with JSE click
	 * @author Kadhar Ali
	 * @param driver
	 * @param targetElement
	 */
	public synchronized void click(WebDriver driver,WebElement targetElement) {
		JavascriptExecutor jse =  (JavascriptExecutor)driver;
		try {
			targetElement.click();
		}catch(ElementClickInterceptedException e) {
			jse.executeScript("arguments[0].click();", targetElement);
		}catch(NoSuchElementException e) {
			System.out.println("Element is not found");	   
		}catch(ElementNotInteractableException e) {
			jse.executeScript("arguments[0].click();", targetElement);
		}
	}

	/**
	 * wait Till The Element Is Visible
	 * @author Kadhar Ali
	 * @param driver
	 * @param TargetLocator
	 */
	public synchronized void waitTillTheElementIsVisible(WebDriver driver,By TargetLocator) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(TargetLocator));
	}

	/**
	 * switch To Window
	 * @author Kadhar Ali
	 * @param driver
	 * @param id
	 */
	public synchronized void switchToFrame(WebDriver driver,String id) {
		waitTillTheElementIsVisible(driver,By.id(id));
		driver.switchTo().frame(id);
	}
	
	/**
	 * switch To Defaul tContent
	 * @author Kadhar Ali
	 * @param driver
	 */
	public synchronized void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
    /**
     * get Property file Value for specified key
     * @author Kadhar ali
     * @param sourceFileLocation
     * @param propertyKey
     * @return
     * @throws IOException
     */
    public synchronized String getPropertyValue(String sourceFileLocation,String propertyKey) throws IOException {
    	FileInputStream sourceFile = new FileInputStream(sourceFileLocation);
		Properties property = new Properties();
		property.load(sourceFile);
		Object object = property.get(propertyKey);
		String browser = (String) object;
		return browser;
	}

    /**
     * get Text From Web Element
     * @author Kadhar Ali
     * @param driver
     * @param targetElement
     * @return
     */
    public synchronized String getTextFromWebElement(WebDriver driver,WebElement targetElement) {
    	String text = targetElement.getText();
    	return text;
    }

    /**
     * verify Texts Matching
     * @author Kadhar Ali
     * @param driver
     * @param actual
     * @param expected
     */
    public synchronized  void verifyTextsMatching(WebDriver driver,String actual,String expected,String failMessage) {
      Assert.assertEquals(actual, expected,failMessage);
	}
}
