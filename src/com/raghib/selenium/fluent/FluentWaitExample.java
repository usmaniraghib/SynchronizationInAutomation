package com.raghib.selenium.fluent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.base.Function;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FluentWaitExample {

	public static WebDriver driver;
	
	public static void main(String[] args) {
		
		//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Driver\\chromedriver.exe");
		//driver = new ChromeDriver();
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
		
		driver.get("http://seleniumpractise.blogspot.com/2016/08/how-to-use-explicit-wait-in-selenium.html");
		
		driver.findElement(By.xpath("//button[@onclick='timedText()']")).click();
		
		// Waiting 30 seconds for an element to be present on the page, checking
		// for its presence once every 5 seconds.
		/*
		 * Wait<WebDriver> wait = new FluentWait<WebDriver>(driver) // .withTimeout(30,
		 * TimeUnit.SECONDS) This is Deprecated // .pollingEvery(5, TimeUnit.SECONDS)
		 * This is Deprecated .withTimeout(Duration.ofSeconds(30))
		 * .pollingEvery(Duration.ofMillis(5)) .ignoring(NoSuchElementException.class);
		 * 
		 * WebElement returnElement = wait.until(new Function<WebDriver, WebElement>() {
		 * //com.google.common.base.Function public WebElement apply(WebDriver driver) {
		 * return driver.findElement(By.xpath("//p[text()='WebDriver']")); } });
		 */
		
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
					 System.out.println("Text whih is coming on screen is "+value);
					 return null;
				 }				 
			 }
		});
		
		System.out.println("Return Element : "+returnElement.isDisplayed());
		driver.quit(); 
    
	}

}
