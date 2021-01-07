package pages;

import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import base.TestBase;
import commonUtils.TestUtil;

public class BookingFormPage extends TestBase {

	// PageFactory

	@FindBy(id = "txtFirstName")
	WebElement FName;
	@FindBy(name = "txtLN")
	WebElement LName;
	@FindBy(name = "Email")
	WebElement Email;
	@FindBy(id = "txtPhone")
	WebElement Mobile;
	@FindBy(name = "city")
	WebElement City;
	@FindBy(name = "state")
	WebElement State;
	@FindBy(name = "persons")
	WebElement Guest;
	@FindBy(xpath = "//textarea")
	WebElement Address;
	@FindBy(id = "txtCardholderName")
	WebElement CardHolderName;
	@FindBy(id = "txtDebit")
	WebElement CardNumber;
	@FindBy(id = "txtCvv")
	WebElement CVV;
	@FindBy(id = "txtMonth")
	WebElement ExpiredMonth;
	@FindBy(id = "txtYear")
	WebElement ExpiredYear;
	@FindBy(id = "btnPayment")
	WebElement ConfirmBooking;
	@FindBy(xpath = "//body//h1")
	WebElement successpage;
	TestUtil util;

	public BookingFormPage() {
		PageFactory.initElements(driver, this);
	}

	public void submit() {
		ConfirmBooking.click();
	}

	public String ValidatepopupContent() throws InterruptedException {
        Thread.sleep(3000);
		Alert alert = driver.switchTo().alert();
		String AlertMessage = driver.switchTo().alert().getText();
		alert.accept();
		return AlertMessage;
	}

	public void bookingdetails(String fieldname, String value) {
		util = new TestUtil();
		switch (fieldname.toUpperCase()) {
		case "FIRSTNAME":
			util.sendkeys(driver, FName, 10, value);
			break;
		case "LASTNAME":
			util.sendkeys(driver, LName, 10, value);
			break;
		case "EMAIL":
			util.sendkeys(driver, Email, 10, value);
			break;
		case "MOBILE":
			util.sendkeys(driver, Mobile, 10, value);
			break;
		case "ADDRESS":
			util.sendkeys(driver, Address, 10, value);
			break;

		case "CITY":
			util.selectandsendkeys(driver, City, 10, value);
			break;
		case "STATE":
			util.selectandsendkeys(driver, State, 10, value);
			break;
		case "GUEST":
			util.selectandsendkeys(driver, Guest, 10, value);
			break;
		case "CARDNAME":
			util.sendkeys(driver, CardHolderName, 10, value);
			break;
		case "CARDNUMBER":
			util.sendkeys(driver, CardNumber, 10, value);
			break;
		case "CVV":
			util.sendkeys(driver, CVV, 10, value);
			break;
		case "EXPIRYMONTH":
			util.sendkeys(driver, ExpiredMonth, 10, value);
			break;
		case "EXPIRYYEAR":
			util.sendkeys(driver, ExpiredYear, 10, value);
			break;
		}

	}

	public String Successtext() {
		return util.Pagetext(driver, successpage);

	}
}