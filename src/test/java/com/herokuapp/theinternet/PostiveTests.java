package com.herokuapp.theinternet;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class PostiveTests {

	@Test
	public void loginTest() {
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

	private void sleep(long m) {
		try {
			Thread.sleep(m);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
