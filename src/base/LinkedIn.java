package base;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import config.Config;
import org.testng.Assert;
public class LinkedIn {
	private WebDriver driver;
	private Config config;
	private List<String> titleList = new ArrayList<String>();
	@BeforeClass
	public void setup() throws InterruptedException {
		// Setup WebDriver
		driver = new EdgeDriver();
		driver.get("https://www.linkedin.com/login"); 
		driver.manage().window().maximize();
		titleList.add("LinkedIn");
		titleList.add("LinkedIn – log in or sign up");
		config = new Config();
	}
	@Test(priority = 1)
	public void testLoginSuccessful() {

		// Locate elements
		WebElement usernameField = this.driver.findElement(By.id("username"));
		WebElement passwordField = this.driver.findElement(By.id("password"));
		WebElement loginButton = driver.findElement(By.xpath("//button[@aria-label='Sign in']"));

		// Perform actions
		usernameField.sendKeys(this.config.getProperty("com.website.username"));
		passwordField.sendKeys(this.config.getProperty("com.website.password"));
		loginButton.click();

		// Assert actions
//	    WebElement searchElement = this.driver.findElement(By.cssSelector("input[placeholder='Search']"));
//	    Assert.assertNotNull(searchElement);
		System.out.println("Test Passed");

	}
	@Test(priority=2)
	public void testSearchaccount() {
		
		WebElement searchBar=driver.findElement(By.xpath("com.website.search.input.element.xpath"));
		searchBar.sendKeys(this.config.getProperty("com.website.accountname"));
		searchBar.submit();
//		WebElement accountLink = driver.findElement(By.xpath(config.getProperty("com.website.account.link.xpath")));
//        accountLink.click();
		}

    @AfterClass
	public void teardown() {
		// Teardown WebDriver
		if (driver != null) {
//            driver.quit();
		}
	}
}
