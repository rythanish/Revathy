package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBase {
	public static Properties prop;
	public static WebDriver driver;

	public TestBase() {
		try {
			File filepath = new File(System.getProperty("user.dir") + "//src//main//java//config//config.properties");
			FileInputStream fs = new FileInputStream(filepath);
			prop = new Properties();
			prop.load(fs);

		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	public static void initialize() throws Exception {

		String browser = prop.getProperty("Browser");
		if (browser.equalsIgnoreCase("Chrome")) {

			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//Support Drivers//chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

			driver.get(System.getProperty("user.dir")+ prop.getProperty("url"));

		}

	}

	public static void CloseBrowser() {
		driver.quit();
	}
}
