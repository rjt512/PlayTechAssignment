package utilities;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.vimalselvam.cucumber.listener.Reporter;

public class CustomAssert {

	SoftAssert softAssert = new SoftAssert();

	public void hardAssertEquals(String actualResult, String expectedResult, String assertDescription) {
		try {
			Assert.assertEquals(actualResult, expectedResult, assertDescription);
			Reporter.addStepLog(
					assertDescription + " - Passed - Expected : " + expectedResult + " , Actual : " + actualResult);
		} catch (Exception e) {
			Reporter.addStepLog(
					assertDescription + " - Failed - Expected : " + expectedResult + " , Actual : " + actualResult);
			throw e;
		}
	}
	
	public void hardAssertEquals(boolean actualResult, boolean expectedResult, String assertDescription) {
		try {
			Assert.assertEquals(actualResult, expectedResult, assertDescription);
			Reporter.addStepLog(
					assertDescription + " - Passed - Expected : " + expectedResult + " , Actual : " + actualResult);
		} catch (Exception e) {
			Reporter.addStepLog(
					assertDescription + " - Failed - Expected : " + expectedResult + " , Actual : " + actualResult);
			throw e;
		}
	}

	public void softAssertEquals(String actualResult, String expectedResult, String assertDescription) {
		try {
			softAssert.assertEquals(actualResult, expectedResult, assertDescription);
			Reporter.addStepLog(
					assertDescription + " - Passed - Expected : " + expectedResult + " , Actual : " + actualResult);
		} catch (Exception e) {
			Reporter.addStepLog(
					assertDescription + " - Failed - Expected : " + expectedResult + " , Actual : " + actualResult);
			throw e;
		}
	}
	
	public void softAsertAll() {
		softAssert.assertAll();
	}
}
