package com.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

//*******This class is used as the base class for our whole framework.*****

public class Base {

	private static WebDriver driver;//In some cases we need to get this class level driver that why we make it as static
	//because it we make it as instance variable then in later point of time this class driver will not contain any value
	//as we will be passing the object of this instance variable driver object to the other class instance driver using below
	//initializeDriver() method.
	String browserName;
	String url;
	public static Properties p;
	FileInputStream fis;
	File filePath;
	
	public Logger log=LogManager.getLogger(this);
	
	//to initialize Browser Driver call this method
	public final WebDriver initializeBrowser() {
		
		filePath=new File("./src/main/java/com/resources/GlobalConfig.properties");
		
		try {
			 fis = new FileInputStream(filePath);	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		 p=new Properties();
			try {
				p.load(fis);	
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		browserName=p.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
			driver= new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
			driver= new FirefoxDriver();
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
		url=p.getProperty("url");
		driver.get(url);
		
		return driver;
	}
	
	//call this method when you need driver referring to the initialized browser object
	public WebDriver getDriver() {
		return driver;
	}
}
