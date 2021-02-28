package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

import com.vimalselvam.cucumber.listener.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;

//this class is created to control webdriver instance
public class DriverInstance {
	protected static WebDriver driver;

	public void launchBrowser(String browserName) {

//WebDriverManager is a library which allows to automate the management of the drivers (e.g. chromedriver, geckodriver, etc.) required by Selenium WebDriver.

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (browserName.equalsIgnoreCase("opera")) {
			WebDriverManager.operadriver().setup();
			driver = new OperaDriver();
		} else {
			System.out.println("Error Message----> " + "browser name not mentioned properly");
			System.exit(0);
		}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
	}

	public void navigateToURL(String url) {
		// navigate to specific url
		driver.get(url);
		Reporter.addStepLog("Visited " + url);
	}

	public void quitDriver() {
		// close browser
		driver.quit();
	}

	public WebDriver getDriver() {
		// get browser/driver instance
		return driver;
	}
}
