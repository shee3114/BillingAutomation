package suites.appsuites;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.CommonFunctions;
import pages.login.dashboard.Dashboard;
import pages.login.dashboard.core.ERPConstant;
import pages.login.dashboard.organization.customFields.CustomFields;
import suites.basesuite.ERPBaseSuite;

public class CustomFieldSuite extends ERPBaseSuite {

	CommonFunctions commonFunctions;
	Dashboard dashboard;
	CustomFields customFields;

	@BeforeTest
	public void navigateToDashBoard() {
		dashboard = createObject("Dashboard");
		System.out.println("Before Test is exceucted successfully.");
	}

	@BeforeMethod
	public void navigateToCustomFieldModule() {
		dashboard = createObject("Dashboard");
		customFields = dashboard.navigateToModule(ERPConstant.CUSTOM_FIELD);
	}

	@Test(priority = 1, dataProvider = "multipleInput", enabled = false)
	public void addNewCustomFields(String label, String placeHolder, String defaultValue, String type,
			String description, String mandatoryChkBox, String editableInAdminChkBox, String editableInCCChkBox) {
		customFields = createObject("CustomFields");
		customFields.addNewCustomFields(label, placeHolder, defaultValue, type, description, mandatoryChkBox,
				editableInAdminChkBox, editableInCCChkBox);
	}

	@Test(priority = 2, dataProvider = "multipleInput", enabled = false)
	public void EditCustomFields(String label, String placeHolder, String defaultValue, String type, String description,
			String mandatoryChkBox, String editableInAdminChkBox, String editableInCCChkBox) {
		customFields = createObject("CustomFields");
		customFields.editCustomFields(label, placeHolder, defaultValue, type, description, mandatoryChkBox,
				editableInAdminChkBox, editableInCCChkBox);
	}

	@Test(priority = 3, dataProvider = "multipleInput", enabled = true)
	public void deleteCustomFields(String label) {
		customFields = createObject("CustomFields");
		customFields.deleteCustomFields(label);
	}

	@AfterMethod
	public void navigateToDashboard() {
		// dashboard.navigateToModule("Dashboard");
		System.out.println("After method is exceucted successfully.");
	}

	@AfterTest
	public void navigateDashboard() {
		// dashboard.navigateToModule("Dashboard");
		System.out.println("AfterTest is executed successfully.");
	}
}
