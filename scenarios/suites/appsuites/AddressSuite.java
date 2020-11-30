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
import pages.login.dashboard.customers.address.Address;
import suites.basesuite.ERPBaseSuite;

public class AddressSuite extends ERPBaseSuite {

	CommonFunctions commonFunctions;

	Customers customers;

	Dashboard dashboard;

	Address address;

	@BeforeTest
	public void navigateToDashBoard() {
		dashboard = createObject("Dashboard");
		System.out.println("Before Test is exceucted successfully.");
	}

	@BeforeMethod
	public void navigateToCustomersModule() {
		customers = dashboard.navigateToModule(ERPConstant.CUSTOMER_Mod);
	}

	@Test(priority = 1, dataProvider = "multipleInput", enabled = true)
	public void addAddressDetails(String customerName, String companyName, String address1, String address2,
			String country, String countryCode, String state, String city, String zip) {
		address = createObject("Address");
		address.addAddressDetails(customerName, companyName, address1, address2, country, countryCode, state, city,
				zip);
		address.verifyAddressDetails(companyName, address1, address2, country, countryCode, state, city, zip);
	}

	@Test(priority = 2, dataProvider = "multipleInput", enabled = true)
	public void editAddressDetails(String customerName, String companyNameForSearch, String companyName,
			String address1, String address2, String country, String countryCode, String state, String city,
			String zip) {
		address = createObject("Address");
		address.editAddressDetails(customerName, companyNameForSearch, companyName, address1, address2, country,
				countryCode, state, city, zip);
		address.verifyAddressDetails(companyName, address1, address2, country, countryCode, state, city, zip);
	}

	@Test(priority = 3, dataProvider = "multipleInput", enabled = false)
	public void deleteAddressDetails(String customerName, String companyName) {
		address = createObject("Address");
		address.deleteAddressCard(customerName, companyName);
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
