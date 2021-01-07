package runnner;

import java.io.File;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.*;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src\\main\\java\\features\\HotelBooking.feature", glue = {
		"stepdef" }, monochrome = true, plugin= {"pretty", 
			"com.cucumber.listener.ExtentCucumberFormatter:target//html//ExtentReport.html"})

public class TagginRunner {
	
	@AfterClass
	public static void writeExtentReport() {
		//Reporter.loadXMLConfig(new File("src//main//resources//extentreport.xml"));
		Reporter.setSystemInfo("Username", System.getProperty("user.name"));
		System.out.println("diwakar");
		Reporter.setSystemInfo("time zone", System.getProperty("user.timesone"));
		Reporter.setSystemInfo("Application Name", "Hotel booking app");
		Reporter.setSystemInfo("OS", System.getProperty("os.name").toString());
		Reporter.setSystemInfo("Environment", "Staging");
		
		
		
	}
	
	

}
