package pages.login.dashboard.customers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import base.BaseComponent;
import framework.reporter.ScreenshotType;
import pages.CommonFunctions;
import pages.Shared_OR;
import pages.login.dashboard.Dashboard;
import pages.login.dashboard.core.ERPConstant;
import pages.login.dashboard.customers.address.Address;

public class Customers extends BaseComponent {

	CommonFunctions commonFunctions;

	Dashboard dashboard;

	Customers customers;

	Address address;

	/*
	 * Method to open the 'Add Customer' Form
	 */
	public void openAddCustomerForm() {

		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}
		if (Objects.isNull(dashboard)) {
			dashboard = createObject("Dashboard");
		}

		// Click on 'New Customer' button
		commonFunctions.click(Shared_OR.newCustomerBtn);
		pause(ERPConstant.MODERATE);

		// Verify the page title
		dashboard.verifyModule("Add Customer");
	}

	/*
	 * Method to add the customer
	 */
	public void addCustomer(String name, String emailId, String customerType, String domain, String url,
			String description, String maximumUsers, String tag, String number, String companyName, String address1,
			String address2, String country, String countryCode, String state, String city, String zip) {

		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}
		if (Objects.isNull(customers)) {
			customers = createObject("Customers");
		}

		// open "New Customer" form
		customers.openAddCustomerForm();
		boolean flag = isElementExists(Customers_OR.CustomerInformationPopup);
		if (flag) {
			customers.enterDetailsInAddCustomerForm(name, emailId, customerType, domain, url, description, maximumUsers,
					tag, number, companyName, address1, address2, country, countryCode, state, city, zip);

			// Verify if the save button is enabled or not and click on it
			commonFunctions.verifyAndClickOnButtonIfEnable(Shared_OR.saveBtn);
			pause(ERPConstant.VERY_LOW);

			// Verify the notification
			commonFunctions.verifyNotification("Success", "Customer created successfully.");

		} else {
			RESULT.FAIL("Failed to add new customer because 'Add Customer' pop-up is not getting opened.", true,
					ScreenshotType.browser);
		}
	}

	/*
	 * Method to edit the customer details
	 */
	public void editCustomer(String nameForSearch, String name, String emailId, String customerType, String domain,
			String webSiteUrl, String description, String maximumUsers, String tag, String number) {

		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}
		if (Objects.isNull(customers)) {
			customers = createObject("Customers");
		}
		if (Objects.isNull(dashboard)) {
			dashboard = createObject("Dashboard");
		}

		// Apply customer filter and open customer Details page.
		customers.applyFilterAndOpenCustomerDetailsPage(nameForSearch);

		// click on Edit button
		click(Customers_OR.editButton);
		pause(ERPConstant.LOW);

		boolean flag = isElementExists(Customers_OR.CustomerInformationPopup);
		if (flag) {
			RESULT.PASS("'Edit Customer' form is opened successfully.", true, ScreenshotType.browser);

			// Verify if the 'Edit Customer' pop-up is getting or not
			dashboard.verifyModule("Edit Customer");

			// Verify if the 'save' button is by default disabled or not
			commonFunctions.verifyIfButtonisDisable(Shared_OR.saveBtn);

			// Enter the details in 'Edit Customer' form
			customers.enterDetailsInEditCustomerFrom(nameForSearch, name, emailId, customerType, domain, webSiteUrl,
					description, maximumUsers, tag, number);

			// click on 'Save' button
			commonFunctions.verifyAndClickOnButtonIfEnable(Shared_OR.saveBtn);
			pause(ERPConstant.VERY_LOW);

			// verify the notification
			commonFunctions.verifyNotification("Success", "Customer updated successfully.");
		} else {
			RESULT.FAIL("Failed to opened 'Edit Cutomer' form", true, ScreenshotType.browser);
		}
	}

	/*
	 * Method to apply filter for the customer by name and navigate to the customer
	 * details page.
	 */
	public void applyFilterAndOpenCustomerDetailsPage(String name) {
		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}
		if (Objects.isNull(customers)) {
			customers = createObject("Customers");
		}
		// Click on drop-down arrow
		click(Shared_OR.dropDownArrow);
		pause(ERPConstant.LOW);

		// Click on 'Advanced Filter' option
		click(Customers_OR.advancedFilter);
		pause(ERPConstant.MODERATE);

		// Verify if the advanced filter pop-up is getting opened or not
		boolean flag = isElementExists(Customers_OR.advancedFilterPopup);

		if (flag) {
			// Apply filter for customer name field
			setValue(Customers_OR.customerFilterName, name);

			// Click on search button
			click(Shared_OR.searchButton);
			pause(ERPConstant.MODERATE);

			// click on name link on the customer
			customers.selectCustomerFromList(Customers_OR.customerNameLink, name);
			pause(ERPConstant.LOW);

		} else {
			RESULT.FAIL("Failed to set the customer filter because 'Advanced Filter' pop-up is not get opened. .", true,
					ScreenshotType.browser);
		}
	}

	/*
	 * The method to select the customer from list.
	 */
	public void selectCustomerFromList(By columnLocator, String optionValue) {

		List<WebElement> customerNameList = driver.findElements(Customers_OR.customerNameLink);

		for (int i = 0; i <= customerNameList.size() - 1; i++) {
			String cutomerLabel = "##" + customerNameList.get(i).getText() + "##";
			if (cutomerLabel.equals("##" + optionValue + "##")) {
				customerNameList.get(i).click();
				RESULT.PASS("Successfully to select the " + optionValue + " option from the list.", true,
						ScreenshotType.browser);
				break;
			} else {
				RESULT.FAIL("Failed because " + optionValue + " is not available in the list", true,
						ScreenshotType.browser);
			}
		}
	}

	/*
	 * Method to remove applied customer filter
	 */
	public void removeCustomerFilter() {

		// click on cross button to close the customer details page.
		click(Shared_OR.crossButton);
		pause(ERPConstant.MODERATE);

		// Click on cancel button in filter section
		click(Shared_OR.cancelButton);
		pause(ERPConstant.MODERATE);
	}

	/*
	 * Method to enter the details in 'Add Customer' Form
	 */
	public void enterDetailsInAddCustomerForm(String name, String emailId, String customerType, String domain,
			String url, String description, String maximumUsers, String tag, String number, String companyName,
			String address1, String address2, String country, String countryCode, String state, String city,
			String zip) {

		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}
		if (Objects.isNull(customers)) {
			customers = createObject("Customers");
		}
		if (Objects.isNull(address)) {
			address = createObject("Address");
		}

		setValue(Customers_OR.name, name);
		setValue(Customers_OR.emailId, emailId);

		// Get the customer drop-down locator
		By CustomerTypeDropDown = getLocator(Shared_OR.dropDown, "name", "customerType");
		click(CustomerTypeDropDown);
		pause(ERPConstant.LOW);
		commonFunctions.selectOptionFromDropDown(Shared_OR.dropDownOptionLocator, customerType);

		setValue(Customers_OR.webSiteUrl, url);
		setValue(Customers_OR.domain, domain);
		setValue(Customers_OR.description, description);
		setValue(Customers_OR.maximumUsers, maximumUsers);
		setValue(Customers_OR.number, number);

		// Add the value of 'tag'
		commonFunctions.addTag(tag);

		// Enter the details in "Address Section"
		address.enterAddressDetails(companyName, address1, address2, country, countryCode, state, city, zip);
		System.out.println("Customer details are added successfully.");
	}

	/*
	 * Method to enter the details in "Edit Customer" form
	 */
	public void enterDetailsInEditCustomerFrom(String nameToSearch, String name, String emailId, String customerType,
			String domain, String webSiteUrl, String description, String maximumUsers, String tag, String number) {

		// Verify if the name field is disabled or not
		boolean nameField = isElementEnabled(Customers_OR.name);
		if (!nameField) {
			RESULT.PASS("Successfully verified that 'Name' field is disabled.", true, ScreenshotType.browser);

		} else {
			RESULT.FAIL("Failed because 'Name' field is enabled.", true, ScreenshotType.browser);
		}
		// Get the text of the 'Name' field
		String customerName = commonFunctions.getTextOfWebElement(Customers_OR.name);

		if (customerName.equals(nameToSearch)) {
			RESULT.PASS("Successfully verified that the customer name is showing properly.", true,
					ScreenshotType.browser);
		} else {
			RESULT.FAIL("Failed to match the Customer's name.", true, ScreenshotType.browser);
		}

		commonFunctions.clearInput(Customers_OR.displayName);
		setValue(Customers_OR.displayName, name);
		commonFunctions.clearInput(Customers_OR.emailId);
		setValue(Customers_OR.emailId, emailId);
		commonFunctions.clearInput(Customers_OR.webSiteUrl);
		setValue(Customers_OR.webSiteUrl, webSiteUrl);
		commonFunctions.clearInput(Customers_OR.domain);
		setValue(Customers_OR.domain, domain);
		commonFunctions.clearInput(Customers_OR.description);
		setValue(Customers_OR.description, description);
		commonFunctions.clearInput(Customers_OR.maximumUsers);
		setValue(Customers_OR.maximumUsers, maximumUsers);
		commonFunctions.clearInput(Customers_OR.number);
		setValue(Customers_OR.number, number);

		// Check if tag is exit or not
		boolean flag = isElementExists(Shared_OR.tagList);
		if (flag) {
			commonFunctions.removeTags();
		}

		// Add tag
		commonFunctions.addTag(tag);

		// Get the text of the 'Customer Type' field and verify if it proper or
		// not.
		By CustomerTypeDropDown = getLocator(Shared_OR.dropDown, "name", "customerType");
		String customerTypeData = commonFunctions.getTextOfWebElement(CustomerTypeDropDown);
		if (customerTypeData.equals(customerType)) {
			RESULT.PASS("The data of the 'CustomerType' field is match successfully.", true, ScreenshotType.browser);
		} else {
			RESULT.FAIL("Failed to match the data of 'CustomerType' field.", true, ScreenshotType.browser);
		}
		// Verify if the 'Customer Type' field is enabled or not in 'Edit
		// Customer' form
		Boolean customerTypeDropDown = commonFunctions.isDropDownEnabled(Customers_OR.customerTypeDropDown);
		if (!customerTypeDropDown) {
			RESULT.PASS("Successfully verified that 'Customer Type' field is disabled in 'Edit Customer' form", true,
					ScreenshotType.browser);
		} else {
			RESULT.FAIL("Failed because 'Customer Type' field is enabled in 'Edit Customer' form", true,
					ScreenshotType.browser);
		}

		// Verify if the 'Address' section is showing or not in 'Edit
		// Customer' form
		boolean addressSection = isElementExists(Customers_OR.addressSection);
		if (!addressSection) {
			RESULT.PASS("Successfully checked that 'Address' section is not showing in 'Edit Customer' form.", true,
					ScreenshotType.browser);
		} else {
			RESULT.FAIL("Failed because 'Address' section is showing in the 'Edit Customer' form.", true,
					ScreenshotType.browser);
		}
	}

	/*
	 * Method to verify the Customer details in "Over View" tab.
	 */
	public void verifyCustomerDetails(String name, String customerType, String tag, String description, String emailId,
			String contactNumber, String domain, String webSiteUrl, String maximumUsers) {

		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}
		if (Objects.isNull(customers)) {
			customers = createObject("Customers");
		}

		// Get the arrayList of the data which was added while creating/editing
		// the customer

		ArrayList<String> CustomerDetailsData = customers.getArrayListOfData(name, customerType, description, tag,
				emailId, contactNumber, domain, webSiteUrl, maximumUsers);

		// Get the arrayList of the customer details from 'OverView' tab
		ArrayList<String> customerDetailsArrayList = customers.getArrayListOfCustomerDetails();

		// Get the Array list of the customer fields
		ArrayList<String> customerFields = new ArrayList<String>();

		customerFields.add("Customer Name");
		customerFields.add("Customer Type");
		customerFields.add("Description");
		customerFields.add("Tag");
		customerFields.add("WebSite URL");
		customerFields.add("EmailId");
		customerFields.add("Contact Number");
		customerFields.add("Domain");
		customerFields.add("Maximum User count");
		customerFields.add("Responsible For Payment");

		int j = 0;
		for (String field : customerFields) {
			if (customerDetailsArrayList.get(j).equals(CustomerDetailsData.get(j))) {
				RESULT.PASS(customerFields.get(j) + " field is matched successfully.", true, ScreenshotType.browser);

			} else {
				RESULT.FAIL("Failed to match the data of " + customerFields.get(j) + " Field", true,
						ScreenshotType.browser);
			}
			j++;
		}
	}

	/*
	 * Get the arrayList of the data which was added while creating/editing the
	 * customer
	 */
	public ArrayList<String> getArrayListOfData(String name, String customerType, String description, String tag,
			String emailId, String contactNumber, String domain, String webSite, String maximumUsers) {

		ArrayList<String> dataList = new ArrayList<String>();
		dataList.add(name);
		dataList.add(customerType);
		dataList.add(description);
		dataList.add(tag);
		dataList.add(webSite);
		dataList.add(emailId);
		dataList.add(contactNumber);
		dataList.add(domain);
		dataList.add(maximumUsers);

		if (customerType.equals("Direct Customer") || (name.equals("VAR"))) {
			dataList.add("Yes");
		} else {
			dataList.add("No");
		}
		return dataList;
	}

	/*
	 * Get the array List of the data from the "Customer Overview" tab.
	 */
	public ArrayList<String> getArrayListOfCustomerDetails() {
		List<WebElement> customerDetailsList = getList(Customers_OR.customerDetails);
		ArrayList<String> customerDetailsArrayList = new ArrayList<String>();

		// Get the name of the customer and customer type

		String customerName = commonFunctions.getTextOfWebElement(Customers_OR.customerDisplayName);
		String customerType = commonFunctions.getTextOfWebElement(Customers_OR.customerType);
		String customerTag = commonFunctions.getTextOfWebElement(Customers_OR.customerTag);
		// String customerTag = str.substring(0, str.length() - 5);
		String customerWebSite = commonFunctions.getTextOfWebElement(Customers_OR.customerURL);
		String customerDescription = commonFunctions.getTextOfWebElement(Customers_OR.customerDescription);

		for (int i = 0; i < customerDetailsList.size(); i++) {
			customerDetailsArrayList.add(customerDetailsList.get(i).getText());
		}
		customerDetailsArrayList.add(0, customerName.trim());
		customerDetailsArrayList.add(1, customerType.trim());
		customerDetailsArrayList.add(2, customerDescription);
		customerDetailsArrayList.add(3, customerTag);
		customerDetailsArrayList.add(4, customerWebSite);

		return customerDetailsArrayList;
	}

	/*
	 * Method to verify customer details in customer grid
	 */
	public void verifyCustomerDetailsInGrid(String name, String emailId, String customerType, String number) {
		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}
		if (Objects.isNull(customers)) {
			customers = createObject("Customers");
		}

		// Apply Filter
		customers.applyFilterAndOpenCustomerDetailsPage(name);

		// Get the arrayList of the data which was added while creating/editing
		// the customer
		ArrayList<String> dataList = new ArrayList<String>();
		dataList.add(name);
		dataList.add(emailId);
		dataList.add(customerType);
		dataList.add(number);

		// Get the locator of column head
		By columnHeadLocator = (Shared_OR.AllcolumnAHead);

		// Verify Records
		commonFunctions.verifyRecord(dataList, Arrays.asList("Reference"), columnHeadLocator);
	}
}
