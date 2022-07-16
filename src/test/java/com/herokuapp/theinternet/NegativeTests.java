package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeTests {

	@Test
	public void negativeLoginTest() {

		System.out.println("Starting login negative test");
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver chromDriver = new ChromeDriver();
		chromDriver.manage().window().maximize();

		String url = "https://the-internet.herokuapp.com/login";
		chromDriver.get(url);
		System.out.println("Login Page is opened");

		WebElement userName = chromDriver.findElement(By.id("username"));
		userName.sendKeys("asd");
//		enter password
		WebElement password = chromDriver.findElement(By.name("password"));
		password.sendKeys("perSecretPassword!");

		WebElement loginButton = chromDriver.findElement(By.tagName("button"));
		loginButton.click();

		// Verification

		WebElement errorMsg = chromDriver.findElement(By.id("flash"));
		String expectedUsrMsg = "Your username is invalid!";
		String actualErrorMsg = errorMsg.getText();

		Assert.assertTrue(actualErrorMsg.contains(expectedUsrMsg),
				"Actual error message does not contain expected. \nActual: " + actualErrorMsg + "\nExpected:"
						+ expectedUsrMsg);
		
		//close browser

		chromDriver.quit();

	}

}
