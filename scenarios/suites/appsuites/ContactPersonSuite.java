package suites.appsuites;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.CommonFunctions;
import pages.login.dashboard.Dashboard;
import pages.login.dashboard.core.ERPConstant;
import pages.login.dashboard.customers.Customers;
import pages.login.dashboard.customers.contactPerson.ContactPerson;
import suites.basesuite.ERPBaseSuite;

public class ContactPersonSuite extends ERPBaseSuite {

	CommonFunctions commonFunctions;

	Customers customers;

	Dashboard dashboard;

	ContactPerson contactPerson;

	@BeforeTest
	public void navigateToDashBoard() {
		dashboard = createObject("Dashboard");
		System.out.println("Before Test is exceucted successfully.");
	}

	@BeforeMethod
	public void navigateToCustomersModule() {
		customers = dashboard.navigateToModule(ERPConstant.CUSTOMER_Mod);
	}

	@Test(priority = 0, dataProvider = "multipleInput", enabled = false)
	public void addContactPerson(String customerName, String firstName, String middleName, String lastName,
			String emailId, String designation, String contactNumber) {
		contactPerson = createObject("ContactPerson");
		contactPerson.addContactForCustomer(customerName, firstName, middleName, lastName, emailId, designation,
				contactNumber);
		contactPerson.applyFilterAndVerifyContactsDetails(firstName, middleName, lastName, emailId, designation,
				contactNumber);
	}

	@Test(priority = 1, dataProvider = "multipleInput", enabled = false)
	public void enablePortalForContactPerson(String customerName, String emailId, String firstName, String lastName) {
		contactPerson = createObject("ContactPerson");
		contactPerson.enablePortalForContactPerson(customerName, emailId, firstName, lastName);
	}

	@Test(priority = 2, dataProvider = "multipleInput", enabled = false)
	public void disalbePortalForContactPerson(String customerName, String emailId, String firstName, String lastName) {
		contactPerson = createObject("ContactPerson");
		contactPerson.disablePortalForContactPerson(customerName, emailId, firstName, lastName);
	}

	@Test(priority = 3, dataProvider = "multipleInput", enabled = false)
	public void editContactPerson(String customerName, String firstName, String middleName, String lastName,
			String emailId, String designation, String contactNumber) {
		contactPerson = createObject("ContactPerson");
		contactPerson.editContactPersonDetails(customerName, firstName, middleName, lastName, emailId, designation,
				contactNumber);
		contactPerson.applyFilterAndVerifyContactsDetails(firstName, middleName, lastName, emailId, designation,
				contactNumber);
	}

	@Test(priority = 4, dataProvider = "multipleInput", enabled = false)
	public void deleteContactPerson(String customerName, String emailId) {
		contactPerson = createObject("ContactPerson");
		contactPerson.deleteContactPerson(customerName, emailId);
	}

	@Test(priority = 5, dataProvider = "multipleInput", enabled = false)
	public void addContactWithExitingEmailId(String customerName, String firstName, String lastName, String emailId,
			String contactNumber) {
		contactPerson = createObject("ContactPerson");
		contactPerson.addContactWithExitingEmailIdOfSameCustomer(customerName, firstName, lastName, emailId,
				contactNumber);
	}

	@Test(priority = 6, dataProvider = "multipleInput", enabled = true)
	public void verifyABC(String customerName, String firstName, String middleName, String lastName, String emailId,
			String designation, String contactNumber) {
		contactPerson = createObject("ContactPerson");
		contactPerson.addContactWithExitingEmailIdOfAnotherCustomer(customerName, firstName, middleName, lastName,
				emailId, designation, contactNumber);
	}

	@AfterMethod
	public void navigateToDashboard() {
		customers = createObject("Customers");
		customers.removeCustomerFilter();
	}

	@AfterTest
	public void navigateDashboard() {
		// dashboard.navigateToModule("Dashboard");
		System.out.println("AfterTest is executed successfully.");
	}

}
