package com.tutorialsninja.qa.testcases;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.baseclass.Base;
import com.tutorialsninja.qa.utilities.Utilities;

public class RegisterTest extends Base{
	public WebDriver driver;

	public RegisterTest() {
		super();
	}

	@BeforeMethod
	public void setup() {
		driver =intializeBrowserAndOpenApplication(prop.getProperty("browserName"));
		driver.findElement(By.xpath("//span[text()='My Account']/parent::a")).click();
		driver.findElement(By.linkText("Register")).click();
	}

	@AfterMethod
	public void tearBrowser() {
		driver.quit();
	}

	@Test(priority=1)
	public void fillAllFields() {

		driver.findElement(By.id("input-firstname")).sendKeys("Praveen");
		driver.findElement(By.id("input-lastname")).sendKeys("Kumar");
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("9629822005");

		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.id("input-confirm")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@value='1'][@name='newsletter']")).click();
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		driver.findElement(By.xpath("//input[@type='submit']")).click();

		String accountCreatedMessage = "Your Account Has Been Created!";

		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='content']//h1")).getText(), accountCreatedMessage);

	}

	@Test(priority=2)
	public void fillManatoryFields() {

		driver.findElement(By.id("input-firstname")).sendKeys("Praveen");
		driver.findElement(By.id("input-lastname")).sendKeys("Kumar");
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("9629822005");
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.id("input-confirm")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		driver.findElement(By.xpath("//input[@type='submit']")).click();

		String accountCreatedMessage = "Your Account Has Been Created!";

		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='content']//h1")).getText(), accountCreatedMessage);


	}

	@Test(priority=3)
	public void fillExistingUserEmail() {

		driver.findElement(By.id("input-firstname")).sendKeys("Praveen");
		driver.findElement(By.id("input-lastname")).sendKeys("Kumar");
		driver.findElement(By.id("input-email")).sendKeys("praveenvlr3@gmail.com");
		driver.findElement(By.id("input-telephone")).sendKeys("9629822005");
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.id("input-confirm")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		driver.findElement(By.xpath("//input[@type='submit']")).click();

		String emailAlreadyRegisterWarning ="Warning: E-Mail Address is already registered!";

		Assert.assertEquals(driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText(), emailAlreadyRegisterWarning);
	}

	@Test(priority=4)
	public void emptyAllField() {


		driver.findElement(By.xpath("//input[@type='submit']")).click();

		String privacyAgreeWarning ="Warning: You must agree to the Privacy Policy!";
		String firstNameWarning ="First Name must be between 1 and 32 characters!";
		String lastNameWaring ="Last Name must be between 1 and 32 characters!";
		String 	emailWaring ="E-Mail Address does not appear to be valid!";
		String passwordWarning= "Password must be between 4 and 20 characters!";


		Assert.assertEquals(driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText(), privacyAgreeWarning);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-firstname']//following-sibling::div")).getText(), firstNameWarning);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-lastname']//following-sibling::div")).getText(), lastNameWaring);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-email']//following-sibling::div")).getText(), emailWaring);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-password']//following-sibling::div")).getText(), passwordWarning);
	}
}
