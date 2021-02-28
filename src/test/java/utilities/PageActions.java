package utilities;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.vimalselvam.cucumber.listener.Reporter;

//This class is created to customise selenium webdriver methods to handle exceptions as well as element waits
public class PageActions extends DriverInstance {

	// Method to wait for an element to be present on web page
	public void waitForElement(By by) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}

	// Method to find an element on web page
	public WebElement findElement(By by, String elementName) {
		WebElement element = null;
		try {
			waitForElement(by);
			element = driver.findElement(by);
		} catch (Exception e) {
			throw e;
		}
		return element;
	}

	// Method to find list of Web elements
	public List<WebElement> findElements(By by, String elementName) {
		List<WebElement> element = null;
		try {
			waitForElement(by);
			element = driver.findElements(by);
		} catch (Exception e) {
			throw e;
		}
		return element;
	}

	// Method to scroll to a Web element
	public void scrollToElement(By by) {
		try {
			String browserName = ((RemoteWebDriver) driver).getCapabilities().getBrowserName();
			if (browserName.equalsIgnoreCase("chrome")) {
				Actions actions = new Actions(driver);
				actions.moveToElement(findElement(by, ""));
				actions.build().perform();
			} else if (browserName.equalsIgnoreCase("firefox")) {
				WebElement element = driver.findElement(by);
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
			}
		} catch (Exception e) {
			throw e;
		}
	}

	// Method to check if an element is displayed
	public boolean isDisplayed(By by, String elementName) {
		boolean isDisplayed;
		try {
			isDisplayed = driver.findElement(by).isDisplayed();
			Reporter.addStepLog(elementName + " is displayed");
		} catch (NotFoundException e) {
			isDisplayed = false;
			Reporter.addStepLog(elementName + " is not displayed");
		}
		return isDisplayed;
	}

	// Method to to check if an element is enabled
	public boolean isEnabled(By by, String elementName) {
		boolean isEnabled;
		try {
			isEnabled = findElement(by, elementName).isEnabled();
		} catch (NotFoundException e) {
			isEnabled = false;
			throw e;
		}
		return isEnabled;
	}

	// Method to click on an element
	public void click(By by, String elementName) {
		try {
			if (isEnabled(by, elementName)) {
				findElement(by, elementName).click();
				Reporter.addStepLog("Clicked " + elementName);
			}
		} catch (Exception e) {
			throw e;
		}
	}

	// Method to select a value from a drop down
	public void select(By by, String elementName, String textToSelect) {
		try {
			if (isEnabled(by, elementName)) {
				Select select = new Select(findElement(by, elementName));
				select.selectByValue(textToSelect);
				Reporter.addStepLog("Selected " + textToSelect + " in " +elementName);
			}
		} catch (Exception e) {
			throw e;
		}
	}

	// Method to get element text
	public String getElementText(By by, String elementName) {
		String text = "";
		try {
			text = findElement(by, elementName).getText();
		} catch (Exception e) {
			throw e;
		}
		return text;
	}

	// Method to get Multiple Elements Combined text
	public String getMultipleElementsCombinedText(By by, String elementName) {
		String text = "";
		try {

			Iterator<WebElement> itr = findElements(by, elementName).iterator();
			while (itr.hasNext()) {
				text += itr.next().getText();
			}
		} catch (Exception e) {
			throw e;
		}
		return text;
	}

	// Method to get page title
	public String getPageTitle() {
		try {
			return driver.getTitle();
		} catch (Exception e) {
			throw e;
		}
	}
}
