package com.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

//using Page Object Modal for Landing Page

public class LandingPageObjects {
	
	WebDriver driver;
	
	public LandingPageObjects(WebDriver driver){
		this.driver=driver;
	}
	
	By signin = By.cssSelector("a[href*='sign_in']");
	
	By displayedText = By.xpath("//h2[contains(text(),'Featured Courses')]");
	
	By displayedNavMenu = By.xpath("//ul[@class='nav navbar-nav navbar-right']");
	
	
	
	
	public WebElement getLogin() {
		return driver.findElement(signin);
	}
	
	public WebElement displayedText() {
		return driver.findElement(displayedText);
	}
	
	public WebElement displayedNavMenu() {
		return driver.findElement(displayedNavMenu);
	}
}
