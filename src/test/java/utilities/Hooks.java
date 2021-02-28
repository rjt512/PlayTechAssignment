package utilities;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.vimalselvam.cucumber.listener.Reporter;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks extends DriverInstance {

	@Before
	//launch browser instance before feature starts executing 
	public void launchBrowserInstance() {
		ReadPropertiesFile file=new ReadPropertiesFile();
		launchBrowser(file.returnPropertyValue("browserName"));
	}

	@After(order = 1)
	//First take screenshot after feature is executed for passed and failed tests
	public void takeScreenshot(Scenario scenario) {
		try {
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			TakesScreenshot scrShot = ((TakesScreenshot) getDriver());
			// Call getScreenshotAs method to create image file
			File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
			// Move image file to new destination
			Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
			File destFile = new File("./Screenshots/" + screenshotName + "_" + timeStamp + ".jpg");
			// Copy file at destination
			FileUtils.copyFile(SrcFile, destFile);
			// attach the specified screenshot to the Cucumber Extent html report
			Reporter.addScreenCaptureFromPath(destFile.getAbsolutePath());
		} catch (IOException e) {
		}
	}

	@After(order = 0)
	//After taking screenshot close browser
	public void AfterSteps() {
		quitDriver();
	}
}
