package com.raghib.selenium.implicitly;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ImplicitlyWaitExample {

	public static WebDriver driver;
	
	public static void main(String[] args) {
		String expectedDate = "12-May-2021";
		String eDate = expectedDate.split("-")[0];
		String eMonth = expectedDate.split("-")[1];
		String eYear = expectedDate.split("-")[2];
		System.out.println(eDate+"-"+eMonth+"-"+eYear);
		
		System.setProperty("webdriver.chrome.driver","C:\\SELENIUM\\browser-driver\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.MINUTES);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.get("https://www.spicejet.com/");
		
		driver.quit();
	}
}
