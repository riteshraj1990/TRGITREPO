package com.test.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import com.pageObjects.LandingPageObjects;
import com.pageObjects.LoginPageObjects;
import com.resources.Base;

public class LoginPageTest extends Base {
	
	WebDriver driver;

	Connection con;
	Statement stat,stat1;
	ResultSet rs,rs1;
	String[][] obj;
	LoginPageObjects lpo1;
	
	@BeforeClass
	public void getBrowserInstance() {
		driver=initializeBrowser();
		log.info("Driver got initialized");
	}
	
	@Test()
	public void clickOnLogin() {
		LandingPageObjects lpo= new LandingPageObjects(driver);
		log.info("LandingPageObjects got created");
		lpo.getLogin().click();
		log.debug("clicked on Sign in link");
		lpo1= new LoginPageObjects(driver);
		log.info("LoginPageObjects got created");
	}
	
	@Test(dataProvider="getCredientials",dependsOnMethods="clickOnLogin")
	public void loginToApplication(String username,String password) {
		//1 step : Open chrome browser
		//2 step : enter eGRC Next url
		//3 step : enter valid username and password 
		//4 step : click on Login button
		
		//driver.close();
		
		lpo1.getEmailId().sendKeys(username);
		log.debug("username entered successfully");
		lpo1.getPassword().sendKeys(password);
		log.debug("password entered successfully");
		lpo1.getSignInButton().click();
		log.debug("Login button clicked successfully");
		lpo1.getEmailId().clear();
		log.debug("Cleared the username field successfully");
//		try {
//			Thread.sleep(3000L);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	
	}

	@DataProvider
	public String[][] getCredientials() {
		
		try {
			con=DriverManager.getConnection(p.getProperty("db_url"), p.getProperty("db_username"), p.getProperty("db_password"));
			stat=con.createStatement();
			rs=stat.executeQuery("select count(*) from customerInfo");
			rs.next();
			int size=rs.getInt("count(*)");
			//System.out.println(size+" is the size of resultSet .");
			log.info(size+" is the size of resultSet .");
			
			stat1=con.createStatement();
			rs1=stat1.executeQuery("select * from customerInfo");	
			obj=new String[size][2];
			
			while(rs1.next()) {
			for(int i=0;i<size;i++) {
			obj[i][0]=(rs1.getString("username")+"@gmail.com");
			obj[i][1]=rs1.getString("password");
			rs1.next();
			}
			}
			//System.out.println(obj);
			log.info("Successfully got DB task completed");
		} catch (SQLException e) {
			log.error("Issue in getting DB task completed");
			e.printStackTrace();
		}	
		return obj;
		
	}
	@AfterClass
	public void tearDow() {
		driver.close();
		log.info("closing the browser");
		driver=null;
	}
}
