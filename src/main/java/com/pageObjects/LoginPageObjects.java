package com.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

//using Page Object Modal for Logging Page

public class LoginPageObjects {

	WebDriver driver;
	
	public LoginPageObjects(WebDriver driver) {
		this.driver=driver;
	}
	By emailid= By.id("user_email");
	By password= By.id("user_password");
	By signInButton= By.cssSelector("input[value='Log In']");
	
	
	public WebElement getEmailId() {
		return driver.findElement(emailid);
	}
	
	public WebElement getPassword() {
		return driver.findElement(password);
	}
	
	public WebElement getSignInButton() {
		return driver.findElement(signInButton);
	}
}
