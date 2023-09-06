package com.raghib.selenium.explicitly;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.base.BaseClass;

public class ExplicitlyWaitExample extends BaseClass {
	
	public static String dynamicDate = "10";
	public static String dynamicMonth = "September";
	public static String dynamicYear = "2023";
	
	public static String currency = "SGD";
	public static String sourceLocation = "DEL";
	public static String destinationLocation = "BHO";
	
	public static WebDriver driver;
	public static String browserName = "chrome";
	public static String browserVersion = "116";
	
	public static String url = "https://www.spicejet.com/";
	
	public static void main(String[] args) {
		WaitClass wc = new WaitClass();
		
		// Chrome Browser
		driver = BaseClass.getDriver(browserName, browserVersion);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(url);
		
		/*Dynamic Drop Down - Select Source and Destination*/
		WebElement location = driver.findElement(By.xpath("//div[@data-testid='to-testID-origin']//input[@type='text']"));
		/*Explicit Wait*/
		wc.explicitWaitClick(driver, Duration.ofSeconds(30), location);
		location.click();
		
		WebElement sourceLocationEle = driver.findElement(By.xpath("//div[contains(text(),'"+sourceLocation+"')]"));
		wc.explicitWaitClick(driver, Duration.ofSeconds(30), sourceLocationEle);
		sourceLocationEle.click();
		
		WebElement destinationLocationEle = driver.findElement(By.xpath("//div[contains(text(),'"+destinationLocation+"')]"));		
		wc.explicitWaitClick(driver, Duration.ofSeconds(30), destinationLocationEle);
		destinationLocationEle.click();
		
	    /*Date Selection - Departure Date Only*/
        WebElement fromDateButton = driver.findElement(By.xpath("//div[@data-testid='undefined-month-"+dynamicMonth+"-"+dynamicYear+"']//div[@data-testid='undefined-calendar-day-"+dynamicDate+"']"));
        wc.explicitWaitClick(driver, Duration.ofSeconds(30), fromDateButton);
        fromDateButton.click();
        
        /*Static Drop Down - Select No of Passenger*/
        WebElement passengerEle = driver.findElement(By.xpath("//div[@data-testid='home-page-travellers']")); 
        wc.explicitWaitClick(driver, Duration.ofSeconds(30), passengerEle);
        passengerEle.click();
        
		int adultCount = 0;
		if(adultCount  <= 2) {
			WebElement noOfPassengerEle = driver.findElement(By.xpath("//div[@data-testid='Adult-testID-plus-one-cta']"));
			wc.explicitWaitClick(driver, Duration.ofSeconds(30), noOfPassengerEle);
			noOfPassengerEle.click();
		}
		
		/*Static Drop Down - Select Currency*/
        WebElement currencyEle = driver.findElement(By.xpath("//div[@class='css-76zvg2 css-bfa6kz r-1862ga2 r-1gkfh8e' and text()='Currency']"));
        wc.explicitWaitClick(driver, Duration.ofSeconds(30), currencyEle);
        currencyEle.click();
        
        WebElement currencyEle1 = driver.findElement(By.xpath("//div[text()='"+currency+"']"));
        wc.explicitWaitClick(driver, Duration.ofSeconds(30), currencyEle1);
        currencyEle1.click();
        
        //BaseClass.quitDriver();      
	}
}