package pages.login.dashboard.customers.address;

import java.util.ArrayList;
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
import pages.login.dashboard.customers.Customers;

public class Address extends BaseComponent {
	CommonFunctions commonFunctions;

	Dashboard dashboard;

	Customers customers;

	Address address;

	/*
	 * Method to add the address detail in Address pop-up.
	 */
	public void addAddressDetails(String customerName, String companyName, String address1, String address2,
			String country, String countryCode, String state, String city, String zip) {
		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}
		if (Objects.isNull(customers)) {
			customers = createObject("Customers");
		}
		if (Objects.isNull(address)) {
			address = createObject("Address");
		}
		// Apply filter and go to the customer details page.
		customers.applyFilterAndOpenCustomerDetailsPage(customerName);

		// click on 'address' tab
		click(Address_OR.addressTab);
		pause(ERPConstant.LOW);

		// click on 'Add Address' button
		click(Address_OR.addAddressButton);
		pause(ERPConstant.VERY_LOW);

		// Verify if 'Address' pop is getting opened or not.
		boolean flag = isElementEnabled(Address_OR.addressPopUp);
		if (flag) {

			RESULT.PASS("'Address' Pop-up is opened successfully.", true, ScreenshotType.browser);

			// Verify the title of the pop-up
			commonFunctions.verifyPopUpTitle("Address");

			// Enter the details in 'Address' pop-up.
			address.enterAddressDetails(companyName, address1, address2, country, countryCode, state, city, zip);

			// verify and click on save button
			commonFunctions.verifyAndClickOnButtonIfEnable(Shared_OR.saveBtn);

			// Verify Notification
			// Error notification is not showing after adding address
		} else {
			RESULT.FAIL("Failed to open 'Address' Pop-up.", true, ScreenshotType.browser);
		}
	}

	/*
	 * Method to edit the address details
	 */
	public void editAddressDetails(String customerName, String companyNameForSearch, String companyName,
			String address1, String address2, String country, String countryCode, String state, String city,
			String zip) {

		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}
		if (Objects.isNull(customers)) {
			customers = createObject("Customers");
		}
		// Apply filter and go to the customer details page.
		customers.applyFilterAndOpenCustomerDetailsPage(customerName);

		// click on 'address' tab
		click(Address_OR.addressTab);
		pause(ERPConstant.LOW);

		// Go to the address card which we need to edit.
		List<WebElement> totalAddressCards = getList(Address_OR.addressCardLocator);
		for (int i = 2; i <= totalAddressCards.size(); i++) {
			String number = String.valueOf(i);
			By cardLocator = getLocator(Address_OR.companyNameAddressCard, number, companyNameForSearch);
			boolean card = isElementExists(cardLocator);
			if (card) {
				i = i - 1;
				String editButton = Address_OR.addressEdit.toString().replace("By.xpath: ", "");

				// Generate the locator for the edit button
				By editButtonLocator = By.xpath("(" + editButton + ")[" + i + "]");
				jsScrollToElement(editButtonLocator);
				pause(ERPConstant.MODERATE);
				click(editButtonLocator);
				// click(Address_OR.addressEdit);
				pause(ERPConstant.LOW);

				// Verify if address pop is getting opened or not.
				boolean flag = isElementEnabled(Address_OR.addressPopUp);
				if (flag) {

					// Verify the title of the pop-up
					commonFunctions.verifyPopUpTitle("Address");

					// Add the address details
					commonFunctions.clearInput(Address_OR.companyName);
					setValue(Address_OR.companyName, companyName);

					commonFunctions.clearInput(Address_OR.address1);
					setValue(Address_OR.address1, address1);

					commonFunctions.clearInput(Address_OR.address2);
					setValue(Address_OR.address2, address2);

					commonFunctions.clearInput(Address_OR.country);
					setValue(Address_OR.country, country);

					commonFunctions.clearInput(Address_OR.countryCode);
					setValue(Address_OR.countryCode, countryCode);

					// Select the state
					// Get locator for state drop-down
					By stateDropDown = getLocator(Shared_OR.dropDown, "name", "state");
					click(stateDropDown);
					pause(ERPConstant.LOW);
					commonFunctions.selectOptionFromDropDown(Shared_OR.dropDownOptionLocator, state);

					// Select the city
					// Get locator for the city drop-down
					By cityDropDown = getLocator(Shared_OR.dropDown, "name", "city");
					click(cityDropDown);
					pause(ERPConstant.LOW);
					commonFunctions.selectOptionFromDropDown(Shared_OR.dropDownOptionLocator, city);

					// Select the zip
					// Get the locator for the zip drop-down
					By zipDropDown = getLocator(Shared_OR.dropDown, "name", "zip");
					click(zipDropDown);
					pause(ERPConstant.LOW);
					commonFunctions.selectOptionFromDropDown(Shared_OR.dropDownOptionLocator, zip);

					// Verify and click on Save button
					commonFunctions.verifyAndClickOnButtonIfEnable(Shared_OR.saveBtn);

				} else {
					RESULT.FAIL("Fail to edit the address details because address pop-up is not getting opened", true,
							ScreenshotType.browser);
				}
				break;
			}
		}
	}

	/*
	 * Method to verify the Address Details in address card.
	 */
	public void verifyAddressDetails(String companyName, String address1, String address2, String country,
			String countryCode, String state, String city, String zip) {

		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}
		if (Objects.isNull(customers)) {
			customers = createObject("Customers");
		}
		if (Objects.isNull(address)) {
			address = createObject("Address");
		}

		// Get the Arraylist of Card Details from frontEnd
		ArrayList<String> AddressDetailsData = address.getArrayOfCardData(companyName);

		// Get the Arraylist of the data which is available in sheet.
		ArrayList<String> addressDetails = address.getArrayListOfData(companyName, address1, address2, country,
				countryCode, state, city, zip);

		// ArrayList which store the Address Cards Field's name
		ArrayList<String> addressCardFields = new ArrayList<String>();
		addressCardFields.add("Company Name");
		addressCardFields.add("Address 1");
		addressCardFields.add("Address 2");
		addressCardFields.add("City");
		addressCardFields.add("State");
		addressCardFields.add("Country");
		addressCardFields.add("Country Code");
		addressCardFields.add("Zip");

		int j = 0;
		for (String field : addressCardFields) {
			if (AddressDetailsData.get(j).equals(addressDetails.get(j))) {
				RESULT.PASS(addressCardFields.get(j) + " field is matched successfully.", true, ScreenshotType.browser);

			} else {
				RESULT.FAIL("Failed to match the data of " + addressCardFields.get(j) + " Field", true,
						ScreenshotType.browser);
			}
			j++;
		}
	}

	/*
	 * Method to store the data in list which was added while creating new customer.
	 */
	public ArrayList<String> getArrayListOfData(String companyName, String address1, String address2, String country,
			String countryCode, String state, String city, String zip) {

		ArrayList<String> dataList = new ArrayList<String>();
		dataList.add(companyName);
		dataList.add(address1);
		dataList.add(address2);
		dataList.add(city);
		dataList.add(state);
		dataList.add(country);
		dataList.add("(" + countryCode + ")");
		dataList.add(zip + ".");

		return dataList;
	}

	/*
	 * Method to enter the address details
	 */
	public void enterAddressDetails(String companyName, String address1, String address2, String country,
			String countryCode, String state, String city, String zip) {

		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}

		// Enter the address details
		setValue(Address_OR.address1, address1);
		setValue(Address_OR.address2, address2);
		setValue(Address_OR.companyName, companyName);
		commonFunctions.clearInput(Address_OR.country);
		setValue(Address_OR.country, country);
		commonFunctions.clearInput(Address_OR.countryCode);
		setValue(Address_OR.countryCode, countryCode);

		// Select the state
		// Get locator for state drop-down
		By stateDropDown = getLocator(Shared_OR.dropDown, "name", "state");
		click(stateDropDown);
		pause(ERPConstant.LOW);
		commonFunctions.selectOptionFromDropDown(Shared_OR.dropDownOptionLocator, state);

		// Select the city
		// Get locator for the city drop-down
		By cityDropDown = getLocator(Shared_OR.dropDown, "name", "city");
		click(cityDropDown);
		pause(ERPConstant.LOW);
		commonFunctions.selectOptionFromDropDown(Shared_OR.dropDownOptionLocator, city);

		// Select the zip
		// Get the locator for the zip drop-down
		By zipDropDown = getLocator(Shared_OR.dropDown, "name", "zip");
		click(zipDropDown);
		pause(ERPConstant.LOW);
		commonFunctions.selectOptionFromDropDown(Shared_OR.dropDownOptionLocator, zip);
	}

	/*
	 * Method to delete the address card.
	 */
	public void deleteAddressCard(String customerName, String companyName) {

		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}
		if (Objects.isNull(customers)) {
			customers = createObject("Customers");
		}
		if (Objects.isNull(address)) {
			address = createObject("Address");
		}
		// Apply filter and go to the customer details page.
		customers.applyFilterAndOpenCustomerDetailsPage(customerName);

		// click on 'address' tab
		click(Address_OR.addressTab);
		pause(ERPConstant.LOW);

		// Go to the address card which we need to delete.
		List<WebElement> totalAddressCards = getList(Address_OR.addressCardLocator);
		for (int i = 2; i <= totalAddressCards.size(); i++) {
			String number = String.valueOf(i);
			By cardLocator = getLocator(Address_OR.companyNameAddressCard, number, companyName);
			boolean card = isElementExists(cardLocator);
			if (card) {
				i = i - 1;
				String deleteButton = Address_OR.deleteAddressButton.toString().replace("By.xpath: ", "");
				// Generate the locator for the delete button
				By deleteButtonLocator = By.xpath("(" + deleteButton + ")[" + i + "]");

				jsScrollToElement(deleteButtonLocator);
				click(deleteButtonLocator);

				// Verify if the confirmation pop-up is showing or not.
				boolean confiramtionDialog = isElementExists(Shared_OR.confirmationDialog);
				if (confiramtionDialog) {

					// get the text of the confirmation dialog
					String dialogContent = getTextWebelement(Address_OR.deleteCofirmationDialogContent);

					if (dialogContent.contains(
							"Delete the selected address of the customer?\n" + "This action cannot be undone.")) {
						RESULT.PASS("Successfully verified the conent of the confirmation dialog.", true,
								ScreenshotType.browser);

						// Click on 'Yes, Delete it.' button
						click(Shared_OR.yesDeleteBtn);
						pause(ERPConstant.LOW);
					} else {
						RESULT.FAIL("Failed!!..Because the content of the confirmation dialog is invalid.", true,
								ScreenshotType.browser);
					}
				} else {
					RESULT.FAIL("Failed!!.. Because confirmation dialog is not showing", true, ScreenshotType.browser);
				}
				break;

			} else {
				// RESULT.FAIL("Failed because the required address card is not
				// showing in the list.", true,
				// ScreenshotType.browser);
			}
		}

	}

	/*
	 * Method to get the data showing in address card and return arraylist of that
	 * data
	 */
	public ArrayList<String> getArrayOfCardData(String companyName) {
		String companyNameData;
		String address1Data;
		String address2Data;
		String cityAndStateData;
		String cityData;
		String stateData;
		String countryAndZipData;
		String countryData;
		String countryCodeData;
		String zipData;
		// Array list which store the Front End Address details
		ArrayList<String> AddressDetailsData = new ArrayList<String>();

		List<WebElement> totalAddressCards = getList(Address_OR.addressCardLocator);
		for (int i = 2; i <= totalAddressCards.size(); i++) {
			String number = String.valueOf(i);
			By cardLocator = getLocator(Address_OR.companyNameAddressCard, number, companyName);
			boolean card = isElementExists(cardLocator);
			if (card) {
				i = i - 1;
				String index = String.valueOf(i);
				String addressCard = Address_OR.addressCard.toString().replace("By.xpath: ", "");

				// Generate the locator for the delete button
				By addressCardLocator = By.xpath("(" + addressCard + ")[" + i + "]");
				jsScrollToElement(addressCardLocator);
				pause(ERPConstant.LOW);
				// Get the address details from address card.
				By companyNameLocator = getLocator(Address_OR.addressCardFields, index, "1");
				companyNameData = commonFunctions.getTextOfWebElement(companyNameLocator);

				By address1Locator = getLocator(Address_OR.addressCardFields, index, "2");
				address1Data = commonFunctions.getTextOfWebElement(address1Locator);

				By address2Locator = getLocator(Address_OR.addressCardFields, index, "3");
				address2Data = commonFunctions.getTextOfWebElement(address2Locator);

				By cityAndStateLocator = getLocator(Address_OR.addressCardFields, index, "4");
				cityAndStateData = commonFunctions.getTextOfWebElement(cityAndStateLocator);

				By countryAndZipLocator = getLocator(Address_OR.addressCardFields, index, "5");
				countryAndZipData = commonFunctions.getTextOfWebElement(countryAndZipLocator);

				// Split the data of 'cityAndStateData'
				String[] cityAndStateArray = cityAndStateData.split(",");
				cityData = cityAndStateArray[0];
				stateData = cityAndStateArray[1].trim();

				// Split the data of 'countryAndZipData'
				String[] countryAndZipArray = countryAndZipData.split(" ");
				countryData = countryAndZipArray[0];
				countryCodeData = countryAndZipArray[1];
				zipData = countryAndZipArray[3];

				AddressDetailsData.add(companyNameData);
				AddressDetailsData.add(address1Data);
				AddressDetailsData.add(address2Data);
				AddressDetailsData.add(cityData);
				AddressDetailsData.add(stateData);
				AddressDetailsData.add(countryData);
				AddressDetailsData.add(countryCodeData);
				AddressDetailsData.add(zipData);
				break;
			}
		}
		return AddressDetailsData;
	}
}