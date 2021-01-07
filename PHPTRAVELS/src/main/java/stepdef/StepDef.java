package stepdef;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.codehaus.plexus.util.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.cucumber.listener.Reporter;

import base.TestBase;
import commonUtils.TestUtil;
import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.*;
import pages.BookingFormPage;
import pages.LoginPage;

public class StepDef extends TestBase {

	LoginPage login;
	BookingFormPage booking;
	TestUtil util;

	@Before
	public void initialise() throws Exception {

		initialize();
		Reporter.assignAuthor("Revathy");
		login = new LoginPage();
		booking = new BookingFormPage();
		util = new TestUtil();
	}

	@Given("^Verify Page content$")
	public void verify_Page_content() throws Throwable {
		login = new LoginPage();
		String value = login.LoginPagetext();
		Assert.assertEquals(value, "Hotel Booking Application");

	}

	@Then("^User click Login button$")
	public void user_click_Login_button() throws Throwable {
		login.click();

	}

	@Then("^Verify the Username Validationtext$")
	public void verify_the_Username_Validationtext() throws Throwable {
		String value = login.usernametext();
		Assert.assertEquals(value, "* Please enter userName.");

	}

	@Then("^User enter the Username and click on Login button$")
	public void user_enter_the_Username_and_click_on_Login_button(DataTable username) throws Throwable {
		List<Map<String, String>> data = username.asMaps(String.class, String.class);
		login.LoginData("USERNAME", data.get(0).get("username"));
		login.click();
	}

	@Then("^Verify the Password Validationtext$")
	public void verify_the_Password_Validationtext() throws Throwable {
		String value = login.passwordtext();
		Assert.assertEquals(value, "* Please enter password.");
	}

	@Then("^Useer enter the Password and click on Login button$")
	public void useer_enter_the_Password_and_click_on_Login_button(DataTable password) throws Throwable {
		List<Map<String, String>> data = password.asMaps(String.class, String.class);
		login.LoginData("PASSWORD", data.get(0).get("password"));
		login.click();
	}

	@Then("^Verify the bookingConfirmationpagetitle$")
	public void verify_the_bookingConfirmationpagetitle() throws Throwable {
		String value = login.Pagetitle();
		Assert.assertEquals(value, "Hotel Booking");
	}

	@Then("^User enters the Username and password$")
	public void user_enters_the_Username_and_password(DataTable Credentials) throws Throwable {
		List<Map<String, String>> data = Credentials.asMaps(String.class, String.class);
		login.LoginData("username", data.get(0).get("username"));
		login.LoginData("password", data.get(0).get("password"));

	}

	@Then("^user Enter the booking details and submit button$")
	public void user_Enter_the_booking_details_and_submit_button(DataTable Details) throws Throwable {
		List<Map<String, String>> data = Details.asMaps(String.class, String.class);

		booking.bookingdetails("firstname", data.get(0).get("FIRSTNAME"));
		booking.bookingdetails("lastname", data.get(0).get("LASTNAME"));

		String value = util.EmailValidation(data.get(0).get("EMAIL"));
		if (value.equalsIgnoreCase("Valid")) {
			booking.bookingdetails("email", data.get(0).get("EMAIL"));
		} else {
			booking.bookingdetails("email", data.get(0).get("EMAIL"));
			booking.submit();
			String content = booking.ValidatepopupContent();
			Assert.assertEquals(content, "Please enter valid Email Id.");
			Reporter.addStepLog("Invalid email");
			closeBrowser();

		}
		String value1 = util.Phonenumber(data.get(0).get("MOBILE"));

		if (value1.equalsIgnoreCase("Valid")) {
			booking.bookingdetails("mobile", data.get(0).get("MOBILE"));
		} else {
			booking.bookingdetails("mobile", data.get(0).get("MOBILE"));
			booking.submit();
			String content = booking.ValidatepopupContent();
			Assert.assertEquals(content, "Please enter valid Contact no.");
			Reporter.addStepLog("Invalid Mobile number");
			closeBrowser();

		}

		booking.bookingdetails("address", data.get(0).get("ADDRESS"));
		booking.bookingdetails("city", data.get(0).get("CITY"));
		booking.bookingdetails("State", data.get(0).get("STATE"));
		booking.bookingdetails("Guest", data.get(0).get("GUEST"));
		booking.bookingdetails("CardName", data.get(0).get("CARDNAME"));
		booking.bookingdetails("CardNumber", data.get(0).get("CARDNUMBER"));
		booking.bookingdetails("CVV", data.get(0).get("CVV"));
		booking.bookingdetails("Expirymonth", data.get(0).get("EXPIRYMONTH"));
		booking.bookingdetails("Expiryyear", data.get(0).get("EXPIRYYEAR"));

	}

	@Then("^Click on Confirm Booking button$")
	public void click_on_Confirm_Booking_button() throws Throwable {
		booking.submit();

	}

	@Then("^user Enter the FirstName$")
	public void user_Enter_the_FirstName(DataTable Details) throws Throwable {
		List<Map<String, String>> data = Details.asMaps(String.class, String.class);
		booking.bookingdetails("firstname", data.get(0).get("FIRSTNAME"));
	}

	@Then("^user Enter the LastName$")
	public void user_Enter_the_LastName(DataTable Details) throws Throwable {
		List<Map<String, String>> data = Details.asMaps(String.class, String.class);
		booking.bookingdetails("lastname", data.get(0).get("LASTNAME"));
	}

	@Then("^user Enter the Email$")
	public void user_Enter_the_Email(DataTable Details) throws Throwable {
		List<Map<String, String>> data = Details.asMaps(String.class, String.class);
		booking.bookingdetails("Email", data.get(0).get("EMAIL"));
	}

	@Then("^user Enter the Mobile number$")
	public void user_Enter_the_Mobile_number(DataTable Details) throws Throwable {
		List<Map<String, String>> data = Details.asMaps(String.class, String.class);
		booking.bookingdetails("Mobile", data.get(0).get("MOBILE"));
	}

	@Then("^user Enter the address$")
	public void user_Enter_the_address(DataTable Details) throws Throwable {
		List<Map<String, String>> data = Details.asMaps(String.class, String.class);
		booking.bookingdetails("Address", data.get(0).get("ADDRESS"));
	}

	@Then("^user Enter the City$")
	public void user_Enter_the_City(DataTable Details) throws Throwable {
		List<Map<String, String>> data = Details.asMaps(String.class, String.class);
		booking.bookingdetails("City", data.get(0).get("CITY"));
	}

	@Then("^user Enter the State$")
	public void user_Enter_the_State(DataTable Details) throws Throwable {
		List<Map<String, String>> data = Details.asMaps(String.class, String.class);
		booking.bookingdetails("State", data.get(0).get("STATE"));
	}

	@Then("^user Enter the Guest number$")
	public void user_Enter_the_Guest_number(DataTable Details) throws Throwable {
		List<Map<String, String>> data = Details.asMaps(String.class, String.class);
		booking.bookingdetails("Guest", data.get(0).get("GUEST"));
	}

	@Then("^user Enter the Card Holder Name$")
	public void user_Enter_the_Card_Holder_Name(DataTable Details) throws Throwable {
		List<Map<String, String>> data = Details.asMaps(String.class, String.class);
		booking.bookingdetails("Cardname", data.get(0).get("CARDNAME"));
	}

	@Then("^user Enter the Card Holder Number$")
	public void user_Enter_the_Card_Holder_Number(DataTable Details) throws Throwable {
		List<Map<String, String>> data = Details.asMaps(String.class, String.class);
		booking.bookingdetails("CardNumber", data.get(0).get("CARDNUMBER"));
	}

	@Then("^user Enter the CVV$")
	public void user_Enter_the_CVV(DataTable Details) throws Throwable {
		List<Map<String, String>> data = Details.asMaps(String.class, String.class);
		booking.bookingdetails("CVV", data.get(0).get("CVV"));
	}

	@Then("^user Enter the Expiration month$")
	public void user_Enter_the_Expiration_month(DataTable Details) throws Throwable {
		List<Map<String, String>> data = Details.asMaps(String.class, String.class);
		booking.bookingdetails("EXPIRYMONTH", data.get(0).get("EXPIRYMONTH"));
	}

	@Then("^user Enter the Expiration Year$")
	public void user_Enter_the_Expiration_Year(DataTable Details) throws Throwable {
		List<Map<String, String>> data = Details.asMaps(String.class, String.class);
		booking.bookingdetails("Expiryyear", data.get(0).get("EXPIRYYEAR"));
	}

	@Then("^Verify the Success page$")
	public void verify_the_Success_page() throws Throwable {
		String content = booking.Successtext();
		Assert.assertEquals(content, "Booking Completed!");
	}

	@Then("^Validate the first name alert message and accept$")
	public void validate_the_first_name_alert_message_and_accept() throws Throwable {
		String content = booking.ValidatepopupContent();
		Assert.assertEquals(content, "Please fill the First Name");
	}

	@Then("^Validate the last name alert message and accept$")
	public void validate_the_last_name_alert_message_and_accept() throws Throwable {
		String content = booking.ValidatepopupContent();
		Assert.assertEquals(content, "Please fill the Last Name");
	}

	@Then("^Validate the Email alert message and accept$")
	public void validate_the_Email_alert_message_and_accept() throws Throwable {
		String content = booking.ValidatepopupContent();
		Assert.assertEquals(content, "Please fill the Email");
	}

	@Then("^Validate the Mobile alert message and accept$")
	public void validate_the_Mobile_alert_message_and_accept() throws Throwable {
		String content = booking.ValidatepopupContent();
		Assert.assertEquals(content, "Please fill the Mobile No.");
	}

	@Then("^Validate the City alert message and accept$")
	public void validate_the_City_alert_message_and_accept() throws Throwable {
		String content = booking.ValidatepopupContent();
		Assert.assertEquals(content, "Please select city");
	}

	@Then("^Validate the State alert message and accept$")
	public void validate_the_State_alert_message_and_accept() throws Throwable {
		String content = booking.ValidatepopupContent();
		Assert.assertEquals(content, "Please select state");
	}

	@Then("^Validate the Card name alert message and accept$")
	public void validate_the_Card_name_alert_message_and_accept() throws Throwable {
		String content = booking.ValidatepopupContent();
		Assert.assertEquals(content, "Please fill the Card holder name");
	}

	@Then("^Validate the Card number alert message and accept$")
	public void validate_the_Card_number_alert_message_and_accept() throws Throwable {
		String content = booking.ValidatepopupContent();
		Assert.assertEquals(content, "Please fill the Debit card Number");
	}

	@Then("^Validate the CVV alert message and accept$")
	public void validate_the_CVV_alert_message_and_accept() throws Throwable {
		String content = booking.ValidatepopupContent();
		Assert.assertEquals(content, "Please fill the CVV");
	}

	@Then("^Validate the Expiry month alert message and accept$")
	public void validate_the_Expiry_month_alert_message_and_accept() throws Throwable {
		String content = booking.ValidatepopupContent();
		Assert.assertEquals(content, "Please fill expiration month");
	}

	@Then("^Validate the Expired year alert message and accept$")
	public void validate_the_Expired_year_alert_message_and_accept() throws Throwable {
		String content = booking.ValidatepopupContent();
		Assert.assertEquals(content, "Please fill the expiration year");
	}

	@After(order = 1)

	public void afterScenario(Scenario scenario) {
		if (scenario.isFailed()) {
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			try {

				File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

				File destinationPath = new File(System.getProperty("user.dir") + "/target/cucumber-reports/screenshots/"
						+ screenshotName + ".png");

				FileUtils.copyFile(sourcePath, destinationPath);

				Reporter.addScreenCaptureFromPath(destinationPath.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@After(order = 0)
	public void closeBrowser() {

		login.driverclose();
	}
}
