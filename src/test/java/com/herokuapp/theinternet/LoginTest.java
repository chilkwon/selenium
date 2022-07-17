package com.herokuapp.theinternet;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

	@Test(priority = 1, groups = { "positiveTests", "smokeTests" })
	public void positiveLoginTest() {
		// create driver
		System.out.println("String Login Test");
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		// maximize browser window

		driver.manage().window().maximize();

//		open test page
		String url = "https://the-internet.herokuapp.com/login";
		driver.get(url);
		System.out.println("page is opened");

		sleep(2000);

		// enter username

		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("tomsmith");

//		enter password
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("SuperSecretPassword!");

//		click login button
		WebElement loginButton = driver.findElement(By.tagName("button"));

		sleep(3000);
		loginButton.click();

		
		sleep(5000);

//		verifications

//		 new URL

		String expectedUrl = "https://the-internet.herokuapp.com/secure";
		String actualUrl = driver.getCurrentUrl();

		Assert.assertEquals(actualUrl, expectedUrl, "Actual page url is not the same as expected");
//		 logout button is visible
		WebElement logoutButton = driver.findElement(By.xpath("//a[@class='button secondary radius']"));
		Assert.assertTrue(logoutButton.isDisplayed(), "Logout buttion is not visible");

//		 successfu  login message
		WebElement successMsg = driver.findElement(By.cssSelector("#flash"));
		String expectedMsg = "You logged into a secure area!";
		String actualMsg = successMsg.getText();
		// Assert.assertEquals(actualMsg, expectedMsg," Actual message is not the same
		// as expected");
		Assert.assertTrue(actualMsg.contains(expectedMsg),
				"Actual message does not the same as expected.\n ActualMsg:" + actualMsg + "\nExpected Message:");
		// close browser
		driver.quit();

	}
	@Parameters({"username","password","expectedMessage"})
	@Test(priority = 2, groups = { "negativeTests", "smokeTests" })
	public void negativeLoginTest(String username, String password, String expectedErrorMessage) {

		System.out.println("Starting negative LoginTest with" + username+ " and " + password);
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver chromDriver = new ChromeDriver();
		chromDriver.manage().window().maximize();

		String url = "https://the-internet.herokuapp.com/login";
		chromDriver.get(url);
		System.out.println("Login Page is opened");

		WebElement usernameElement = chromDriver.findElement(By.id("username"));
		usernameElement.sendKeys(username);
//		enter password
		WebElement passwordElement = chromDriver.findElement(By.name("password"));
		passwordElement.sendKeys(password);

		WebElement loginButton = chromDriver.findElement(By.tagName("button"));
		loginButton.click();

		// Verification

		WebElement errorMsg = chromDriver.findElement(By.id("flash"));
		
		String actualErrorMsg = errorMsg.getText();

		Assert.assertTrue(actualErrorMsg.contains(expectedErrorMessage),
				"Actual error message does not contain expected. \nActual: " + actualErrorMsg + "\nExpected:"
						+ expectedErrorMessage);

		// close browser

		chromDriver.quit();

	}

	private void sleep(long m) {
		try {
			Thread.sleep(m);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
