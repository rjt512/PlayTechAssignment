package test_runner;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.ZoneId;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.vimalselvam.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
//include features, step definitions and cucumber hooks directories in cucumber options. 
//Plug in for ExtentCucumber html report is used to create extent report in ExecutionReports directory
@CucumberOptions(features = "Features", glue = { "step_definitions", "utilities" }, plugin = {
		"com.vimalselvam.cucumber.listener.ExtentCucumberFormatter:ExecutionReports/LatestExecutionReport.html" }, monochrome = true)

public class Runner {

	@BeforeClass
	// this method takes a backup of last generated html extent report by renaming
	// it, if there is any, so that it is not lost
	public static void backupReport() {
		try {
			File oldReport = new File("./ExecutionReports/LatestExecutionReport.html");
			if (oldReport.exists()) {
				// Use BasicFileAttributes to get creation date of last generated html extent
				// report to use it in backup name so that its unique
				BasicFileAttributes attr = Files.readAttributes(oldReport.toPath(), BasicFileAttributes.class);
				File newFile = new File("./ExecutionReports/Report_"
						+ attr.creationTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime() + ".html");
				// rename the last generated html extent report if any
				oldReport.renameTo(newFile);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@AfterClass
	public static void writeExtentReport() {
		// create ExtentCucumber html report at the end of execution using
		// extent-config.xml
		Reporter.loadXMLConfig("src/test/java/utilities/extent-config.xml");
	}
}