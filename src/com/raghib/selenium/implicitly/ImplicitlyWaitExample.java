package com.raghib.selenium.implicitly;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ImplicitlyWaitExample {

	public static WebDriver driver;
	
	public static void main(String[] args) {
		String expectedDate = "12-May-2021";
		String eDate = expectedDate.split("-")[0];
		String eMonth = expectedDate.split("-")[1];
		String eYear = expectedDate.split("-")[2];
		System.out.println(eDate+"-"+eMonth+"-"+eYear);
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		
		//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Driver\\chromedriver.exe");
		//driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		/*Implicitly Wait*/
		driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.MINUTES);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.get("https://www.spicejet.com/");
		
		driver.quit();
	}
}
