package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.fasterxml.jackson.databind.deser.ValueInstantiator.Gettable;

import base.TestBase;
import commonUtils.TestUtil;
import cucumber.api.java.bm.Tetapi;

public class LoginPage extends TestBase {

	// Page Factory
	@FindBy(xpath = "//input[@name='userName']")
	WebElement LoginUserName;

	@FindBy(name = "userPwd")
	WebElement password;

	@FindBy(xpath = "//input[@type =\"button\"]")
	WebElement Login;

	@FindBy(xpath = "//div//h1")
	WebElement Loginpage;

	@FindBy(xpath = "//*[@id ='userErrMsg']")
	WebElement usernamevalidation;

	@FindBy(xpath = "//*[@id ='pwdErrMsg']")
	WebElement passwordvalidation;

	TestUtil util;

	public LoginPage() {

		PageFactory.initElements(driver, this);

	}

	public String LoginPagetext() {
		util = new TestUtil();

		return util.Pagetext(driver, Loginpage);

	}

	public String usernametext() {
		return util.Pagetext(driver, usernamevalidation);
	}

	public void LoginData(String fieldname, String value) {

		if (fieldname.equalsIgnoreCase("username")) {
			util.sendkeys(driver, LoginUserName, 10, value);
		}
		if (fieldname.equalsIgnoreCase("password")) {
			util.sendkeys(driver, password, 10, value);
		}

	}

	public void click() {
		util.click(driver, Login);
	}

	public String ValidatepopupContent() throws Throwable {

		return util.ValidatepopupContent(driver);

	}

	public String passwordtext() {
		return util.Pagetext(driver, passwordvalidation);

	}
	
	public String Pagetitle() throws InterruptedException {
		Thread.sleep(3000);
		return driver.getTitle();
	}

	public void driverclose() {
		driver.close();
	}
}
