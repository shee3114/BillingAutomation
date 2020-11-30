package pages.login.dashboard.customers.contactPerson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import base.BaseComponent;
import framework.reporter.ScreenshotType;
import pages.CommonFunctions;
import pages.Shared_OR;
import pages.login.dashboard.Dashboard;
import pages.login.dashboard.core.ERPConstant;
import pages.login.dashboard.customers.Customers;

public class ContactPerson extends BaseComponent {

	CommonFunctions commonFunctions;

	Dashboard dashboard;

	Customers customers;

	ContactPerson contactPerson;

	/*
	 * Method to navigate to the contact tab
	 */
	public void navigateToContactPersonTab(String customerName) {

		if (Objects.isNull(customers)) {
			customers = createObject("Customers");
		}

		// Apply filter for the customer and
		customers.applyFilterAndOpenCustomerDetailsPage(customerName);

		// Go to "Contact" tab
		click(ContactPerson_OR.contactTab);
		pause(ERPConstant.MODERATE);
	}

	/*
	 * Method to add contact in customer
	 */
	public void addContactForCustomer(String customerName, String firstName, String middleName, String lastName,
			String emailId, String designation, String contactNumber) {

		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}
		if (Objects.isNull(contactPerson)) {
			contactPerson = createObject("ContactPerson");
		}

		// Open 'Contact Person' Pop-up.
		contactPerson.navigateToContactPersonTab(customerName);

		// Click on "Add Contact" button
		click(ContactPerson_OR.addContactButton);
		pause(ERPConstant.MODERATE);

		// Verify if the "Contact Person" pop-up is getting opened or not.
		boolean flag = isElementExists(ContactPerson_OR.contactPopUp);
		if (flag) {
			RESULT.PASS("Contact person pop-up is opened successfully.", true, ScreenshotType.browser);

			// Verify the title of the pop-up
			commonFunctions.verifyPopUpTitle("Contact Person");

			// Add the details in contact pop-up
			setValue(ContactPerson_OR.contactFirstName, firstName);
			setValue(ContactPerson_OR.contactMiddleName, middleName);
			setValue(ContactPerson_OR.contactLastName, lastName);
			setValue(ContactPerson_OR.contactEmailId, emailId);
			setValue(ContactPerson_OR.contactDesignation, designation);
			setValue(ContactPerson_OR.contactsNumber, contactNumber);

			// click on save button
			commonFunctions.verifyAndClickOnButtonIfEnable(Shared_OR.saveBtn);
			pause(ERPConstant.VERY_LOW);

			// Verify notification ...
			commonFunctions.verifyNotification("Success", "Contact Added Successfully.");

		} else {
			RESULT.FAIL("Failed because 'Contact person' pop-up is not getting opened.", true, ScreenshotType.browser);
		}
	}

	/*
	 * Method to verifyContactDetails
	 */
	public void applyFilterAndVerifyContactsDetails(String firstName, String middleName, String lastName,
			String emailId, String designation, String contactNumber) {

		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}
		if (Objects.isNull(contactPerson)) {
			contactPerson = createObject("ContactPerson");
		}

		// Apply ContactFilter
		contactPerson.applyContactFilter(emailId);

		// Store the data in list which was added while creating New Contact
		ArrayList<String> recordList = contactPerson.getArrayListOfData(firstName, middleName, lastName, emailId,
				designation, contactNumber);
		By columnHeadLocator = (Shared_OR.AllcolumnAHead);

		commonFunctions.verifyRecord(recordList, Arrays.asList("Actions"), columnHeadLocator);

		// clear applied filter
		commonFunctions.clearInput(ContactPerson_OR.contactFilter);

	}

	/*
	 * Method to edit 'Contact Person' Details
	 */
	public void editContactPersonDetails(String customerName, String firstName, String middleName, String lastName,
			String emailId, String designation, String contactNumber) {
		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}
		if (Objects.isNull(contactPerson)) {
			contactPerson = createObject("ContactPerson");
		}

		// Navigate to the 'contants'tab
		contactPerson.navigateToContactPersonTab(customerName);

		// Apply contact filter
		contactPerson.applyContactFilter(emailId);

		// click on edit icon
		click(ContactPerson_OR.contactEditBtn);
		pause(ERPConstant.LOW);

		// Verify if the contactPerson popUp is getting opened or not
		boolean flag = isElementExists(ContactPerson_OR.contactPopUp);
		if (flag) {

			// Verify the PopUp title.
			commonFunctions.verifyPopUpTitle("Contact Person");

			// Verify if the 'Save' button is by default disabled or not.
			boolean saveBtn = isElementEnabled(ContactPerson_OR.saveButton);
			if (!saveBtn) {
				RESULT.PASS("Successfully verified that by default 'Save' button is disabled ", true,
						ScreenshotType.browser);
			} else {
				RESULT.FAIL("Failed... because 'Save' button is not disabled. ", true, ScreenshotType.browser);
			}

			// Verify if the 'Email ID' field is enabled or not.
			Boolean emailIdField = isElementEnabled(ContactPerson_OR.contactEmailId);
			if (!emailIdField) {

				// Get the value of the EmailId field
				String emailDIFieldValue = commonFunctions.getTextOfWebElement(ContactPerson_OR.contactEmailId);

				// Verify the EmailIdField
				if (emailDIFieldValue.equals(emailId)) {
					RESULT.PASS("The value of the 'EmailId' field is matched successfully.", true,
							ScreenshotType.browser);
				} else {
					RESULT.FAIL("Failed to match the value of 'EmailId' field.", true, ScreenshotType.browser);
				}
				RESULT.PASS("Successfully checked that 'EmailId' field is disabled.", true, ScreenshotType.browser);

			} else {
				RESULT.FAIL("Failed!!.... Becuase 'EmailId' field is enabled. ", true, ScreenshotType.browser);
			}

			// Enter the details in 'Contact Person' pop-up
			contactPerson.enterDetailsInEditContactPerson(firstName, middleName, lastName, designation, contactNumber);

			// Click on 'Save' button
			commonFunctions.verifyAndClickOnButtonIfEnable(ContactPerson_OR.saveButton);

			// Verify notification

			// clear the applied filter
			commonFunctions.clearInput(ContactPerson_OR.contactFilter);

		} else {
			RESULT.FAIL("Failed!!.. 'Contact Person' popup is not getting opened. ", true, ScreenshotType.browser);
		}
	}

	/*
	 * Method to enter the details in 'ContactPerson'while editing
	 */
	public void enterDetailsInEditContactPerson(String firstName, String middleName, String lastName,
			String designation, String contactNumber) {
		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}
		commonFunctions.clearInput(ContactPerson_OR.contactFirstName);
		setValue(ContactPerson_OR.contactFirstName, firstName);
		commonFunctions.clearInput(ContactPerson_OR.contactMiddleName);
		setValue(ContactPerson_OR.contactMiddleName, middleName);
		commonFunctions.clearInput(ContactPerson_OR.contactLastName);
		setValue(ContactPerson_OR.contactLastName, lastName);
		commonFunctions.clearInput(ContactPerson_OR.contactDesignation);
		setValue(ContactPerson_OR.contactDesignation, designation);
		commonFunctions.clearInput(ContactPerson_OR.contactsNumber);
		setValue(ContactPerson_OR.contactsNumber, contactNumber);
	}

	/*
	 * Method to store the data in list which was added while creating new Contact.
	 */
	public ArrayList<String> getArrayListOfData(String firstName, String middleName, String lastName, String emailId,
			String designation, String contactNumber) {

		ArrayList<String> dataList = new ArrayList<String>();
		String name = firstName + "  " + middleName + "  " + lastName;
		dataList.add(name.trim());
		dataList.add(designation);
		dataList.add(emailId);
		dataList.add(contactNumber);
		return dataList;
	}

	/*
	 * Method to enable portal for contact person.
	 */
	public void enablePortalForContactPerson(String customerName, String emailId, String firstName, String lastName) {

		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}
		if (Objects.isNull(contactPerson)) {
			contactPerson = createObject("ContactPerson");
		}

		// Navigate to the 'contacts' tab.
		contactPerson.navigateToContactPersonTab(customerName);

		// Apply contact filter
		contactPerson.applyContactFilter(emailId);

		// Verify if the 'Enable Portal' button is exist or not
		boolean EnablePortalBtn = isElementExists(ContactPerson_OR.enablePortalBtn);
		if (EnablePortalBtn) {
			click(ContactPerson_OR.enablePortalBtn);
			pause(ERPConstant.LOW);

			// verify if confirmation pop-up regarding enabling the user is
			// showing or not
			boolean confirmationDialog = isElementExists(Shared_OR.confirmationDialog);

			if (confirmationDialog) {

				// Get the content of the confirmation message.
				String confirmationDialogMessage = getTextWebelement(Shared_OR.confirmationDialogContent);
				System.out.println(confirmationDialogMessage);
				if (confirmationDialogMessage.contains("By enabling the portal for " + firstName + " " + lastName
						+ ", the contact will be able to access the client connect portal.\n"
						+ "Enable portal for the contact?")) {
					RESULT.PASS("Successfully verified the conent of the confirmation dialog.", true,
							ScreenshotType.browser);

					// Click on 'Yes, Do it.' button
					click(Shared_OR.yesDoBtn);
					pause(ERPConstant.LOW);

					// clear the applied filter
					commonFunctions.clearInput(ContactPerson_OR.contactFilter);

					// Verify Notification

				} else {
					RESULT.FAIL("Failed!!..Because the content of the confirmation dialog is invalid.", true,
							ScreenshotType.browser);
				}
			} else {
				RESULT.FAIL("Failed!!.. Because confirmation dialog is not showing", true, ScreenshotType.browser);
			}
		} else {
			RESULT.FAIL("Failed!!..Because 'Enable Protal' button is not exist", true, ScreenshotType.browser);
		}
	}

	/*
	 * Method to disable portal for contact person
	 */
	public void disablePortalForContactPerson(String customerName, String emailId, String firstName, String lastName) {

		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}
		if (Objects.isNull(contactPerson)) {
			contactPerson = createObject("ContactPerson");
		}

		// Navigate to the 'Contacts' tab
		contactPerson.navigateToContactPersonTab(customerName);

		// Apply contact filter
		contactPerson.applyContactFilter(emailId);

		// Verify if the 'Enable Portal' button is exist or not
		boolean EnablePortalBtn = isElementExists(ContactPerson_OR.disablePortalBtn);
		if (EnablePortalBtn) {
			click(ContactPerson_OR.disablePortalBtn);
			pause(ERPConstant.LOW);

			// verify if confirmation pop-up regarding enabling the user is
			// showing or not
			boolean confirmationDialog = isElementExists(Shared_OR.confirmationDialog);

			if (confirmationDialog) {

				// Get the content of the confirmation message.
				String confirmationDialogMessage = getTextWebelement(Shared_OR.confirmationDialogContent);
				if (confirmationDialogMessage.contains("By disabling the portal for " + firstName + " " + lastName
						+ ", the contact will not be able to access the client connect portal. However you can enable the access for contact by providing access again.\n"
						+ "Disable portal for the selected contact?")) {
					RESULT.PASS("Successfully verified the conent of the confirmation dialog.", true,
							ScreenshotType.browser);

					// Click on 'Yes, Do it.' button
					click(Shared_OR.yesDoBtn);
					pause(ERPConstant.LOW);

					// clear the applied filter
					commonFunctions.clearInput(ContactPerson_OR.contactFilter);

					// Verify Notification
				} else {
					RESULT.FAIL("Failed!!.. because the content of the confirmation dialog is invalid.", true,
							ScreenshotType.browser);
				}
			} else {
				RESULT.FAIL("Failed!!.. Because confirmation dialog is not showing.", true, ScreenshotType.browser);
			}
		} else {
			RESULT.FAIL("Failed!!.. Because 'Disable Protal' button is not exist", true, ScreenshotType.browser);
		}
	}

	/*
	 * Method to delete contact person.
	 */
	public void deleteContactPerson(String customerName, String emailId) {

		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}
		if (Objects.isNull(contactPerson)) {
			contactPerson = createObject("ContactPerson");
		}

		// Navigate to the 'Contacts' tab.
		contactPerson.navigateToContactPersonTab(customerName);

		// apply contactFilter
		contactPerson.applyContactFilter(emailId);

		// check if the 'Delete' icon is exist or not.
		boolean deleteBtn = isElementExists(Shared_OR.deleteBtn);
		if (deleteBtn) {
			RESULT.PASS("Successfully checked that delete button is available.", true, ScreenshotType.browser);
			click(Shared_OR.deleteBtn);
			pause(ERPConstant.VERY_LOW);

			// Verify if confirmation dialog is showing or not
			boolean confirmationDialog = isElementExists(Shared_OR.confirmationDialog);

			if (confirmationDialog) {
				// Get the content of confirmation dialo.
				String confirmationDialogMessage = getTextWebelement(Shared_OR.deleteConfirmationContent);

				if (confirmationDialogMessage
						.contains("Delete the selected contact of the customer?\n" + "This action cannot be undone.")) {
					RESULT.PASS("Successfully verified the conent of the confirmation dialog.", true,
							ScreenshotType.browser);

					// Click on 'Yes, Delete it' button
					click(Shared_OR.yesDeleteBtn);
					pause(ERPConstant.VERY_LOW);

					// clear the applied filter
					commonFunctions.clearInput(ContactPerson_OR.contactFilter);

					// Verify Notification

				} else {
					RESULT.FAIL("Failed!!.. because the content of the confirmation dialog is invalid.", true,
							ScreenshotType.browser);
				}
			} else {
				RESULT.FAIL("Failed!!.. Because confirmation dialog is not showing", true, ScreenshotType.browser);
			}
		} else {
			RESULT.FAIL("Failed!!.. Becuase delete icon is not exist.", true, ScreenshotType.browser);
		}
	}

	/*
	 * Method to apply filter for by using contact's emailId.
	 */
	public void applyContactFilter(String emailId) {

		setValue(ContactPerson_OR.contactFilter, emailId);
		pause(ERPConstant.MODERATE);

		// Verify if only one record is showing or not
		int rowCount = getRowCount(ContactPerson_OR.contactTable);
		if (rowCount == 1) {
			RESULT.PASS("Successfully verified that only one record is showing after applying filter.", true,
					ScreenshotType.browser);
		} else {
			RESULT.FAIL("Failed because more than one records are showing after applying filter.", true,
					ScreenshotType.browser);
		}
	}

	/*
	 * Method to verify if the system is allowing to create the contact person with
	 * already existing email id
	 */
	public void addContactWithExitingEmailIdOfSameCustomer(String customerName, String firstName, String lastName,
			String emailId, String contactNumber) {

		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}
		if (Objects.isNull(contactPerson)) {
			contactPerson = createObject("ContactPerson");
		}
		// Navigate to the 'contact' tab.
		contactPerson.navigateToContactPersonTab(customerName);

		click(ContactPerson_OR.addContactButton);

		boolean flag = isElementExists(ContactPerson_OR.contactPopUp);
		if (flag) {
			RESULT.PASS("'Contact Person' pop-up is opened successfully.", true, ScreenshotType.browser);

			// Enter the details in 'Contact Person' pop-up
			setValue(ContactPerson_OR.contactFirstName, firstName);
			setValue(ContactPerson_OR.contactLastName, lastName);
			setValue(ContactPerson_OR.contactsNumber, contactNumber);
			setValue(ContactPerson_OR.contactEmailId, emailId);
			click(ContactPerson_OR.saveButton);

			// Verify if the error message is showing for the email id field
			WebElement element = driver.findElement(Shared_OR.errorMessage);

			// Create an object of JavaScriptExector Class
			JavascriptExecutor executor = (JavascriptExecutor) driver;

			// Get the error message
			String errorMessage = String.valueOf(executor.executeScript("return arguments[0].textContent", element));

			if (errorMessage.equals("Email ID already exists.")) {
				RESULT.PASS(
						"Successfully checked that the system is showing the error message regarding email is already exists.",
						true, ScreenshotType.browser);
			} else {
				RESULT.PASS(
						"Failed!!.. because system is not showing any error message regarding email is already exists.",
						true, ScreenshotType.browser);
			}
			// Close 'contact Person' pop-up
			click(ContactPerson_OR.closeButton);
		} else {
			RESULT.FAIL("Failed!!.. Because 'Contact Person' is not getting opened.", true, ScreenshotType.browser);
		}
	}

	/*
	 * Method to verify if the system is allowed to enable the contact which is
	 * already enabled in another customer.
	 */
	public void verifyIfEnablePortalFunctionForContactWithExitingEmailId(String customerName, String firstName,
			String lastName, String emailId, String contactNumber) {

		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}
		if (Objects.isNull(contactPerson)) {
			contactPerson = createObject("ContactPerson");
		}
		// Navigate to the 'contact' tab.
		contactPerson.navigateToContactPersonTab(customerName);

		click(ContactPerson_OR.addContactButton);

		boolean flag = isElementExists(ContactPerson_OR.contactPopUp);
		if (flag) {
			RESULT.PASS("'Contact Person' is opened successfully.", true, ScreenshotType.browser);

			// Enter the details in 'Contact Person' pop-up
			setValue(ContactPerson_OR.contactFirstName, firstName);
			setValue(ContactPerson_OR.contactLastName, lastName);
			setValue(ContactPerson_OR.contactsNumber, contactNumber);
			setValue(ContactPerson_OR.contactEmailId, emailId);

			commonFunctions.verifyAndClickOnButtonIfEnable(ContactPerson_OR.saveButton);

			contactPerson.applyContactFilter(emailId);
			boolean EnablePortalBtn = isElementExists(ContactPerson_OR.enablePortalBtn);
			if (EnablePortalBtn) {
				click(ContactPerson_OR.enablePortalBtn);
				pause(ERPConstant.LOW);

				// verify if confirmation pop-up regarding enabling the user is
				// showing or not
				boolean confirmationDialog = isElementExists(Shared_OR.confirmationDialog);

				if (confirmationDialog) {

					// Get the content of the confirmation message.
					String confirmationDialogMessage = getTextWebelement(Shared_OR.confirmationDialogContent);
					System.out.println(confirmationDialogMessage);
					if (confirmationDialogMessage.contains("By enabling the portal for " + firstName + " " + lastName
							+ ", the contact will be able to access the client connect portal.\n"
							+ "Enable portal for the contact?")) {
						RESULT.PASS("Successfully verified the conent of the confirmation dialog.", true,
								ScreenshotType.browser);

						// Click on 'Yes, Do it.' button
						click(Shared_OR.yesDoBtn);
						pause(ERPConstant.LOW);

						// clear the applied filter
						commonFunctions.clearInput(ContactPerson_OR.contactFilter);

						// Verify if the error notification is showing or not.
						////// code should be write on 9 July ***********
					} else {
						RESULT.FAIL("Failed!!..Because the content of the confirmation dialog is invalid.", true,
								ScreenshotType.browser);
					}
				} else {
					RESULT.FAIL("Failed!!.. Because confirmation dialog is not showing", true, ScreenshotType.browser);
				}
			}

		} else {
			RESULT.FAIL("Failed!!.. Because 'Contact Person' is not getting opened", true, ScreenshotType.browser);
		}
	}

	public void addContactWithExitingEmailIdOfAnotherCustomer(String customerName, String firstName, String middleName,
			String lastName, String emailId, String designation, String contactNumber) {
		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}
		if (Objects.isNull(contactPerson)) {
			contactPerson = createObject("ContactPerson");
		}
		// Navigate to the 'contact' tab.
		contactPerson.navigateToContactPersonTab(customerName);

		click(ContactPerson_OR.addContactButton);

		boolean flag = isElementExists(ContactPerson_OR.contactPopUp);
		if (flag) {
			RESULT.PASS("'Contact Person' is opened successfully.", true, ScreenshotType.browser);

			// Add the details in contact pop-up
			setValue(ContactPerson_OR.contactFirstName, firstName);
			setValue(ContactPerson_OR.contactMiddleName, middleName);
			setValue(ContactPerson_OR.contactLastName, lastName);
			setValue(ContactPerson_OR.contactEmailId, emailId);
			setValue(ContactPerson_OR.contactDesignation, designation);
			setValue(ContactPerson_OR.contactsNumber, contactNumber);

			// click on save button
			commonFunctions.verifyAndClickOnButtonIfEnable(Shared_OR.saveBtn);
			pause(ERPConstant.VERY_LOW);

			// filter contact list by email id
			contactPerson.applyContactFilter(emailId);

			// Click on enable portal button
			click(ContactPerson_OR.enablePortalBtn);
			pause(ERPConstant.LOW);

			// verify if confirmation pop-up regarding enabling the user is
			// showing or not
			boolean confirmationDialog = isElementExists(Shared_OR.confirmationDialog);

			if (confirmationDialog) {

				// Get the content of the confirmation message.
				String confirmationDialogMessage = getTextWebelement(Shared_OR.confirmationDialogContent);
				System.out.println(confirmationDialogMessage);
				if (confirmationDialogMessage.contains("By enabling the portal for " + firstName + " " + lastName
						+ ", the contact will be able to access the client connect portal.\n"
						+ "Enable portal for the contact?")) {
					RESULT.PASS("Successfully verified the conent of the confirmation dialog.", true,
							ScreenshotType.browser);

					// Click on 'Yes, Do it.' button
					click(Shared_OR.yesDoBtn);
					pause(ERPConstant.LOW);

					// Verify Notification
					boolean errorNotification = commonFunctions.verifyNotification("Failure",
							"Error: The user already exists.");
					if (errorNotification) {
						RESULT.PASS(
								"Successfully checked that the system is not allowed to enable the the user who is already enabled in another customer",
								true, ScreenshotType.browser);
					} else {
						RESULT.FAIL(
								"Failed!!.. Because system is allowed the user to enable the user who is already enabed in another customers.",
								true, ScreenshotType.browser);
					}
				} else {
					RESULT.FAIL("Failed!!..Because the content of the confirmation dialog is invalid.", true,
							ScreenshotType.browser);
				}
			} else {
				RESULT.FAIL("Failed!!.. Because confirmation dialog is not showing", true, ScreenshotType.browser);
			}
		} else {
			RESULT.FAIL("Failed!!.. Because 'Contact Person' is not getting opened.", true, ScreenshotType.browser);
		}
	}

}