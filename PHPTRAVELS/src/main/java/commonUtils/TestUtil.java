package commonUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestUtil {

	public static void sendkeys(WebDriver driver, WebElement element, int timeout, String Value) {

		new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(Value);

	}

	public String ValidatepopupContent(WebDriver driver) throws InterruptedException {

		Alert alert = driver.switchTo().alert();
		String AlertMessage = driver.switchTo().alert().getText();
		alert.accept();
		return AlertMessage;

	}

	public static void click(WebDriver driver, WebElement element) {
		element.click();
	}

	public static String Pagetext(WebDriver driver, WebElement Element) {

		return Element.getText();

	}

	public static void selectandsendkeys(WebDriver driver, WebElement element, int timeout, String Value) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
		Select sel = new Select(element);
		sel.selectByValue(Value);

	}
	
	public static String EmailValidation(String email)
	{
		String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
	 
	Pattern pattern = Pattern.compile(regex);
	if (pattern.matcher(email).matches()){
		return "Valid"  ;
	}
	else {
		return "Invalid"  ;
	}}
	
	public static String Phonenumber(String s) 
    { 
        
        Pattern p = Pattern.compile("(0/91)?[7-9][0-9]{9}");  
        if(p.matcher(s).matches()) {
        	return "Valid" ;
        }
        	else {
        		return "Invalid"  ;
        	}
        }
    } 



