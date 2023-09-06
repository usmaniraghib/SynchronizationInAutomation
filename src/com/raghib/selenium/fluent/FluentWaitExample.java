package com.raghib.selenium.fluent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.base.BaseClass;
import com.google.common.base.Function;
import com.raghib.selenium.explicitly.WaitClass;

public class FluentWaitExample extends BaseClass {

	public static WebDriver driver;
	public static String browserName = "chrome";
	public static String browserVersion = "116";
	
	public static String url = "http://seleniumpractise.blogspot.com/2016/08/how-to-use-explicit-wait-in-selenium.html";
	
	public static void main(String[] args) {
		WaitClass wc = new WaitClass();
		
		// Chrome Browser
		driver = BaseClass.getDriver(browserName, browserVersion);
		
		/*Implicitly Wait*/
		wc.pageLoadTimeout(driver, Duration.ofSeconds(100));
		wc.implicitWait(driver, Duration.ofSeconds(30));
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.get(url);
		
		WebElement timeTextButton = driver.findElement(By.xpath("//button[@onclick='timedText()']"));
		/*Explicit Wait*/
		wc.explicitWaitClick(driver, Duration.ofSeconds(30), timeTextButton);
		timeTextButton.click();
		
		// Waiting 30 seconds for an element to be present on the page, checking
		// for its presence once every 15 seconds.
		/*Fluent Wait*/
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofMillis(15))
				.ignoring(NoSuchElementException.class);
		
		WebElement returnElement = wait.until(new Function<WebDriver, WebElement>() {	//com.google.common.base.Function 
			 public WebElement apply(WebDriver driver) {				 
				 WebElement element = driver.findElement(By.xpath("//p[@id='demo']"));				 
				 String value = element.getAttribute("innerHTML");				 
				 if(value.equalsIgnoreCase("WebDriver")) {
					 return element;
				 } else {
					 System.out.println("Text which is coming on screen is "+value);
					 return null;
				 }				 
			 }
		});
		
		System.out.println("Return Element : "+returnElement.isDisplayed());
		BaseClass.quitDriver();     
	}
}
