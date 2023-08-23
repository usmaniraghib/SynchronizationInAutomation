package com.raghib.selenium.explicitly;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExplicitlyWaitExample {
	// TODO Program Pending to implement.
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
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofMinutes(3));
		
		driver.get("https://www.spicejet.com/");		
                
        /*Dynamic Drop Down - Select Source and Destination*/
        driver.findElement(By.xpath("//div[@class='css-1dbjc4n r-1awozwy r-18u37iz r-1wtj0ep']//input[@autocapitalize='sentences' and @value='']")).click();
        driver.findElement(By.xpath("//a[@value='GAY']")).click();
        driver.findElement(By.xpath("//a[@value='BLR']")).click();		
	    
	    /*Date Selection - Departure Date Only*/
        /*Explicitly Wait*/
        Duration duration = Duration.ofSeconds(3000);
        WebDriverWait explicitlyWait = new WebDriverWait(driver, duration);
        WebElement fromDateButton = explicitlyWait.until(ExpectedConditions.elementToBeClickable(By.id("ctl00_mainContent_view_date1")));
        fromDateButton.click();
        
        String currentMonth = driver.findElement(By.xpath("(//span[@class='ui-datepicker-month'])[1]")).getText().trim();
        String currentYear = driver.findElement(By.xpath("(//span[@class='ui-datepicker-year'])[1]")).getText().trim();
        System.out.println("CurrentMonth : "+currentMonth.toString()+" / "+"CurrentYear : "+currentYear.toString());
        System.out.println("ExpectedMonth : "+eMonth+" / "+"ExpectedYear : "+eYear);
        
        WebElement next;
        while((!currentMonth.equalsIgnoreCase(eMonth)) || (!currentYear.equalsIgnoreCase(eYear))) {
        	next = driver.findElement(By.xpath("//span[text()='Next']"));
        	next.click();
        	currentMonth = driver.findElement(By.xpath("(//span[@class='ui-datepicker-month'])[1]")).getText().trim();
        	currentYear = driver.findElement(By.xpath("(//span[@class='ui-datepicker-year'])[1]")).getText().trim();
        }
        
        List<WebElement> dates = driver.findElements(By.xpath("//a[@class='ui-state-default']"));
        for(WebElement loopElement : dates) {
        	if(loopElement.getText().trim().equalsIgnoreCase(eDate)) {
        		loopElement.click();
        		break;        		
        	}
        }
        //driver.quit();        
	}

}
