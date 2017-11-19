package com.htc.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginAutomation {
	
	WebDriver  driver ;
	
	@DataProvider(name="validLoginData")
	public Object[][] setValidLoginData(){
		
		//
		
		Object[][] validLoginData = {{"sayooj","123Welcome"},
				{"Gopi","password"}};
		return validLoginData;
	}

	@BeforeClass
	public void initializeDriver(){
		
		// Automation testing without browser or UI
		driver=new HtmlUnitDriver(true);
		
		// Below code is to test your application with chrome browser
		/*
		System.setProperty("webdriver.chrome.driver","D://IMPC0989_D//MavenTest//MavenSrpingMVC_Selenium//chromedriver.exe");
		driver = new ChromeDriver();*/
		//driver.get("http://localhost:8080/LoginProject/login.html");
		
	}
	@AfterClass
	public void closedriver()
	{
		driver.close();
	}

	@Test(dataProvider="validLoginData")
	public void testSuccessLoginLogout(String username, String password){
		driver.get("http://172.16.51.12:8080/MavenSpringMVC/");
		//Synchronize test execution with application execution.
		WebElement login=(new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("Login")));
		login.click();
		WebElement uname = (new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.name("username")));
		//WebElement unameElement = driver.findElement(By.name("user_name"));
		System.out.println(uname.isEnabled());
		uname.sendKeys(username);
		WebElement passElement = driver.findElement(By.name("password"));
		passElement.sendKeys(password);
		WebElement subElement = (new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.id("loginbtn")));
		subElement.click();
		
		
		String successTitle = driver.getTitle();
		System.out.println(successTitle);
		Assert.assertEquals(successTitle, "Welcome");
	//driver.close();}
	}
}
