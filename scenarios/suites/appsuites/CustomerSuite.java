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
import suites.basesuite.ERPBaseSuite;

public class CustomerSuite extends ERPBaseSuite {

	CommonFunctions commonFunctions;

	Customers customers;

	Dashboard dashboard;

	@BeforeTest
	public void navigateToDashBoard() {
		dashboard = createObject("Dashboard");
		System.out.println("Before Test is exceucted successfully.");
	}

	@BeforeMethod
	public void navigateToCustomerModule() {
		customers = dashboard.navigateToModule(ERPConstant.CUSTOMER_Mod);
	}

	@Test(priority = 1, dataProvider = "multipleInput", enabled = true)
	public void addCustomer(String name, String emailId, String customerType, String domain, String url,
			String description, String maximumUsers, String tag, String number, String companyName, String address1,
			String address2, String country, String countryCode, String state, String city, String zip) {
		customers = createObject("Customers");
		customers.addCustomer(name, emailId, customerType, domain, url, description, maximumUsers, tag, number,
				companyName, address1, address2, country, countryCode, state, city, zip);
	}

	@Test(priority = 2, dataProvider = "multipleInput", enabled = false)
	public void editCustomer(String NameForSearch, String name, String emailId, String customerType, String domain,
			String webSiteUrl, String description, String maximumUsers, String tag, String number) {
		customers = createObject("Customers");
		customers.editCustomer(NameForSearch, name, emailId, customerType, domain, webSiteUrl, description,
				maximumUsers, tag, number);
		customers.verifyCustomerDetails(name, customerType, tag, description, emailId, number, domain, webSiteUrl,
				maximumUsers);
	}

	@Test(priority = 3, dataProvider = "multipleInput", enabled = false)
	public void verfiyCustomerDetails(String name, String emailId, String customerType, String number) {
		customers = createObject("Customers");
		customers.verifyCustomerDetailsInGrid(name, emailId, customerType, number);
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
