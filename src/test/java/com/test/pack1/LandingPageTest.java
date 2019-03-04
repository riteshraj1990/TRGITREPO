package com.test.pack1;
import org.testng.Assert;
import org.testng.annotations.*;

import com.pageObjects.LandingPageObjects;
import com.resources.Base;

import org.openqa.selenium.WebDriver;

public class LandingPageTest extends Base{
	
	//WebDriver driver=initializeDriver();
	
	WebDriver driver;
	
	@BeforeClass
	public void getBrowserInstance() {
		driver=initializeBrowser();
		log.info("Driver got initialized");
	}
	
	@Test
	public void verifyTextPresent() {
		LandingPageObjects lp=new LandingPageObjects(driver);
		log.info("LandingPageObject got created");
		String actualText=lp.displayedText().getText();
		log.debug("Actual Test captured from the webpage");
		Assert.assertEquals(actualText, "FEATURE COURSES");
		log.info("Actual Test is same as given Text");
		Assert.assertTrue(lp.displayedNavMenu().isDisplayed());
		log.info("Navigation menu is displayed");
		
	}
	@AfterClass
	public void tearDown() {
		driver.close();
		log.info("Closing the browser");
		driver=null;
	}
	
	
}
