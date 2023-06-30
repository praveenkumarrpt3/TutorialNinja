package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialninja.qa.pages.HomePage;
import com.tutorialsninja.qa.baseclass.Base;
import com.tutorialsninja.qa.utilities.Utilities;

public class LoginTest extends Base{
	public WebDriver driver;
	public LoginTest(){
		super();
	}

	

	@BeforeMethod
	public void setup() {
		driver=intializeBrowserAndOpenApplication(prop.getProperty("browserName"));
		
		HomePage hp = new HomePage(driver);
		hp.clickOnMyAccount();
		hp.ClickOnLoginOption();
	}

	@AfterMethod
	public void tearBrowser() {
		driver.quit();
	}

	@DataProvider(name="validCredentialsForLogin")
	public Object[][] supplyTestData() {
		Object[][] data = Utilities.getTestDataFromExcel("login");
		return data;
	}

	
	@Test(priority=1,dataProvider="validCredentialsForLogin")
	public void verifyLoginWithValidCredentials(String email,String password) {

		driver.findElement(By.id("input-email")).sendKeys(email);
		driver.findElement(By.id("input-password")).sendKeys(password);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
	}
}
