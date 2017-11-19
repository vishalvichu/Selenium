package com.htc.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RegisterAutomation {

	WebDriver driver;

	@DataProvider(name = "registeruser")
	public Object[][] setValidRegisterData() {

		//

		Object[][] validRegisterData = {
				{ "jithu", "123Welcome", "jithu", "raj", "jithu666@gmail.com", "ddsa", 989509316 },
				{ "sachin", "password", "Sachin", "HH", "sachin77@gmail.com", "dsadd", 898877697 } };
		return validRegisterData;
	}

	@BeforeClass
	public void initializeDriver() {
		// Automation testing without browser or UI
		driver = new HtmlUnitDriver(true);

		// Below code is to test your application with chrome browser
		/*
		 * System.setProperty("webdriver.chrome.driver",
		 * "D://IMPC0989_D//MavenTest//MavenSrpingMVC_Selenium//chromedriver.exe");
		 * driver = new ChromeDriver();
		 */
		// driver.get("http://localhost:8080/LoginProject/login.html");

	}

	@AfterClass
	public void closedriver() {
		driver.close();
	}

	@Test(dataProvider = "registeruser")
	public void testSuccessLoginLogout(String username, String password, String fname, String lname, String email,
			String address, int phone) throws InterruptedException {
		driver.get("http://172.16.51.12:8080/MavenSpringMVC/");
		// Synchronize test execution with application execution.
		WebElement register = (new WebDriverWait(driver, 15))
				.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("Register")));
		register.click();
		WebElement uname = (new WebDriverWait(driver, 15))
				.until(ExpectedConditions.presenceOfElementLocated(By.name("username")));
		// WebElement unameElement = driver.findElement(By.name("user_name"));
		System.out.println(uname.isEnabled());
		uname.sendKeys(username);
		WebElement pass = (new WebDriverWait(driver, 15))
				.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
		pass.sendKeys(password);
		WebElement firstname = (new WebDriverWait(driver, 15))
				.until(ExpectedConditions.presenceOfElementLocated(By.name("firstname")));
		firstname.sendKeys(fname);
		WebElement lastname = (new WebDriverWait(driver, 15))
				.until(ExpectedConditions.presenceOfElementLocated(By.name("lastname")));
		lastname.sendKeys(lname);
		WebElement emailid = (new WebDriverWait(driver, 15))
				.until(ExpectedConditions.presenceOfElementLocated(By.name("email")));
		emailid.sendKeys(email);
		WebElement addresselem = (new WebDriverWait(driver, 15))
				.until(ExpectedConditions.presenceOfElementLocated(By.name("address")));
		addresselem.sendKeys(address);
		WebElement phoneno = (new WebDriverWait(driver, 15))
				.until(ExpectedConditions.presenceOfElementLocated(By.id("phone")));
		phoneno.sendKeys(String.valueOf(phone));

		WebElement subElement = (new WebDriverWait(driver, 15))
				.until(ExpectedConditions.presenceOfElementLocated(By.id("registerbtn")));
		subElement.click();
		Thread.sleep(1000);

		String successTitle = driver.getTitle();
		System.out.println(successTitle);
		Assert.assertEquals(successTitle, "Welcome");
		// driver.close();}
	}
}
