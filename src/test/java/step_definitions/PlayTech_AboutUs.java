package step_definitions;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utilities.CustomAssert;
import utilities.PageActions;

public class PlayTech_AboutUs extends PageActions {

	CustomAssert customAssert = new CustomAssert();

	By dayDropDown = By.name("day");
	By monthDropDown = By.name("month");
	By yearDropDown = By.name("year");
	By enterSiteButton = By.xpath("//button[text()='Enter Site']");
	By mainMenu = By.id("trigger");
	By aboutUsMenu = By.linkText("ABOUT US");
	By numberOfEmployeesText = By.xpath("//div[text()='Number of Employees']");
	By numberOfEmployeesValue = By.xpath(
			"//div[contains(text(),'Number of Employees')]//preceding-sibling::div//span[@class='odometer-value']");
	By numberOfCountriesValue = By.xpath(
			"//div[contains(text(),'Number of countries')]//preceding-sibling::div//span[@class='odometer-value']");
	By globalLicenseesValue = By
			.xpath("//div[contains(text(),'Global licensees')]//preceding-sibling::div//span[@class='odometer-value']");
	By regulatedJurisdictionsValue = By.xpath(
			"//div[contains(text(),'Regulated Jurisdictions')]//preceding-sibling::div//span[@class='odometer-value']");

	
	@Given("^User enters age details - Day as '(.*?)',  Month as '(.*?)',  Year as '(.*?)' on Home Page$")
	public void enterAgeDetails(String day, String month, String year) {
		navigateToURL("https://www.playtech.com/");
		select(dayDropDown, "Day drop down", day);
		select(monthDropDown, "Month drop down", month);
		select(yearDropDown, "Year drop down", year);
		click(enterSiteButton, "Enter Site Button");
		customAssert.hardAssertEquals(getPageTitle(), "Playtech - the source of success", "Assert Point - Verify Playtech Home page title");
	}

	@When("^User visits About Us page$")
	public void visitAboutUsPage() throws InterruptedException {
		Thread.sleep(2000);
		click(mainMenu, "Main menu");
		click(aboutUsMenu, "About us menu");
		customAssert.hardAssertEquals(getPageTitle(), "Company Overview PlayTech", "Assert Point - Verify Playtech About Us page title");
	}

	@Then("^Following attributes should be displayed - Number of Employees as (.*?), Number of countries Playtech has offices as (.*?), Global licensees as (.*?), Regulated Jurisdictions as (.*?)$")
	public void verifyAttributes(String numberOfEmployees, String numberofCountries, String globalLicensees,
			String regulatedJurisdictions) {

		scrollToElement(numberOfEmployeesText);

		String numberOfEmployeesActual = getMultipleElementsCombinedText(numberOfEmployeesValue, "Number of Employees");
		String numberofCountriesActual = getMultipleElementsCombinedText(numberOfCountriesValue, "Number of Countries");
		String globalLicenseesActual = getMultipleElementsCombinedText(globalLicenseesValue, "Global Licensees");
		String regulatedJurisdictionsActual = getMultipleElementsCombinedText(regulatedJurisdictionsValue,
				"Regulated Jurisdictions");

		customAssert.softAssertEquals(numberOfEmployeesActual, numberOfEmployees, "Assert Point - Verify Number of Employees");
		customAssert.softAssertEquals(numberofCountriesActual, numberofCountries, "Assert Point - Verify Number of Countries");
		customAssert.softAssertEquals(globalLicenseesActual, globalLicensees, "Assert Point - Verify Global Licensees");
		customAssert.softAssertEquals(regulatedJurisdictionsActual, regulatedJurisdictions, "Assert Point - Verify Regulated Jurisdictions");
		
		customAssert.softAsertAll();
	}
}
