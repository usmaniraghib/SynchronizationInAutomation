package com.raghib.selenium.implicitly;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import com.base.BaseClass;
import com.raghib.selenium.explicitly.WaitClass;

public class ImplicitlyWaitExample extends BaseClass {

	public static WebDriver driver;
	public static String browserName = "chrome";
	public static String browserVersion = "116";
	
	public static String url = "https://www.spicejet.com/";
	
	public static void main(String[] args) {
		WaitClass wc = new WaitClass();
		
		// Chrome Browser
		driver = BaseClass.getDriver(browserName, browserVersion);

		/* Implicitly Wait */
		wc.pageLoadTimeout(driver, Duration.ofSeconds(100));
		wc.implicitWait(driver, Duration.ofSeconds(30));

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		driver.get(url);
		
		BaseClass.quitDriver();
	}
}
