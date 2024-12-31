package base;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import config.Config;
import org.testng.Assert;
public class Coursera {
	private WebDriver driver;
	private Config config;
	private List<String> titleList = new ArrayList<String>();
	@BeforeClass
	public void setup() throws InterruptedException {
		// Setup WebDriver
		driver = new EdgeDriver();
		driver.get("https://www.coursera.org/login"); 
		driver.manage().window().maximize();
		titleList.add("coursera");
		titleList.add("coursera – log in or sign up");
		config = new Config();
	}
	@Test(priority = 1)
	public void testLoginSuccessful() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// Locate elements
		WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(config.getProperty("com.website.login.username.element.name"))));
		WebElement passwordField =driver.findElement(By.name(config.getProperty("com.website.login.password.element.name")));
		WebElement loginButton = driver.findElement(By.xpath(config.getProperty("com.website.login.button.element.xpath")));
		// Perform actions
		usernameField.sendKeys(this.config.getProperty("com.website.username"));
		passwordField.sendKeys(this.config.getProperty("com.website.password"));
		loginButton.click();

		// Assert actions
	    WebElement searchElement = this.driver.findElement(By.cssSelector("input[placeholder='What do you want to learn?']"));
	    Assert.assertNotNull(searchElement);
		System.out.println("Test Passed");

	}
	@Test(priority=2)
	public void testSearchBar() {
		((JavascriptExecutor) driver).executeScript("window.open();");
        Set<String> handles = driver.getWindowHandles();
        String currentHandle = driver.getWindowHandle();
        for (String handle : handles) {
            if (!handle.equals(currentHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        driver.get(config.getProperty("com.website.urllink"));
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		 WebElement searchBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getProperty("com.website.search.input.element.xpath"))));
	     searchBar.sendKeys(config.getProperty("com.website.coursename"));
	     WebElement searchButton = driver.findElement(By.xpath(config.getProperty("com.website.search.button.element.xpath")));
	     searchButton.click();
	     
	}
	@Test(priority=3)
	public void testSelectCourse() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement selectCourse =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(config.getProperty("com.website.course.link.text"))));
        selectCourse.click();
        WebElement enroll_button = wait.until(ExpectedConditions.elementToBeClickable((By.xpath("com.website.course.button.element.xpath") )));
        enroll_button.click();
      	}
	@AfterClass
	public void teardown() {
		// Teardown WebDriver
		if (driver != null) {
           driver.quit();
		}
	}

	
}
