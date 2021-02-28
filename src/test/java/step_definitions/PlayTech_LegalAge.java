package step_definitions;

import org.openqa.selenium.By;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utilities.CustomAssert;
import utilities.PageActions;

public class PlayTech_LegalAge extends PageActions {

	CustomAssert customAssert = new CustomAssert();

	By dayDropDown = By.name("day");
	By monthDropDown = By.name("month");
	By yearDropDown = By.name("year");
	By enterSiteButton = By.xpath("//button[text()='Enter Site']");
	By legalAgeErrorMessage = By.xpath("//span[text()='Sorry you must be over 18 to enter.']");

	@Given("^User is on PlayTech Legal age verification page$")
	public void openPlaytechWebsite() {
		navigateToURL("https://www.playtech.com/");
		customAssert.hardAssertEquals(getPageTitle(), "Playtech - the source of success",
				"Assert Point - Verify Playtech Home page title");
	}

	@When("^User enters age details - Day as '(.*?)',  Month as '(.*?)',  Year as '(.*?)'$")
	public void enterDetails(String day, String month, String year) {
		select(dayDropDown, "Day drop down", day);
		select(monthDropDown, "Month drop down", month);
		select(yearDropDown, "Year drop down", year);
		click(enterSiteButton, "Enter Site Button");
	}

	@Then("^Following alert message is displayed \"(.*?)\"$")
	public void verifyAlertMessage(String alertMessage) {
		customAssert.hardAssertEquals(isDisplayed(legalAgeErrorMessage, "Invalid age alert message"), true,
				"Assert Point - Verify if under 18 users are displayed an error message");
	}

	@Then("^User is taken to Home Page$")
	public void visitHomePage() {
		customAssert.hardAssertEquals(isDisplayed(legalAgeErrorMessage, "Invalid age alert message"), false,
				"Assert Point - Verify if over 18 users are displayed an error message");
	}
}
