package com.tutorialsninja.qa.testcases;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.baseclass.Base;

public class SearchTest extends Base{

	public SearchTest() {
		super();
	}

	public WebDriver driver;

	@BeforeMethod
	public void setupBrowser() {
		driver=intializeBrowserAndOpenApplication(prop.getProperty("browserName"));
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Login")).click();

		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validUserName"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[@type='submit']")).click();
	}

	@AfterMethod
	public void tearOfBrowser() {
		driver.quit();
	}

	@Test(priority=1)
	public void searchProductIsValid() {

		driver.findElement(By.xpath("//div[@id='search']//child::input")).sendKeys("HP");
		driver.findElement(By.xpath("//div[@id='search']//child::button")).click();
		assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed(),"Search product and result found correct");
	}

	@Test(priority=2)
	public void searchProductIsInValid() {

		driver.findElement(By.xpath("//div[@id='search']//child::input")).sendKeys("Honda");
		driver.findElement(By.xpath("//div[@id='search']//child::button")).click();

		String noSearchProductWarning ="There is no product that matches the search criteria";

		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='button-search']//following-sibling::p")).getText(),noSearchProductWarning );
	}

	@Test(priority=3,dependsOnMethods="searchProductIsValid")
	public void emptySearchField() {

		driver.findElement(By.xpath("//div[@id='search']//child::input")).sendKeys("");
		driver.findElement(By.xpath("//div[@id='search']//child::button")).click();

		String noSearchProductWarning ="There is no product that matches the search criteria.";

		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='button-search']//following-sibling::p")).getText(),noSearchProductWarning );
	}
}
