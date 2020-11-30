package pages.login.dashboard.organization.customFields;

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
import pages.login.dashboard.core.ERPConstant;
import pages.login.dashboard.customers.Customers_OR;

public class CustomFields extends BaseComponent {

	CommonFunctions commonFunctions;
	CustomFields customFields;

	/*
	 * Method to add the new Custom Field
	 */
	public void addNewCustomFields(String label, String placeHolder, String defaultValue, String type,
			String description, String mandatoryChkBox, String editableInAdminChkBox, String editableInCCChkBox) {

		if (Objects.isNull(customFields)) {
			customFields = createObject("CustomFields");
		}
		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}

		// Open 'Add Custom Field' Pop-up
		customFields.openCustomFields();

		boolean flag = isElementExists(CustomFields_OR.customFieldPopUp);
		if (flag) {
			customFields.enterDetailsInAddCustomFields(label, placeHolder, defaultValue, type, description,
					mandatoryChkBox, editableInAdminChkBox, editableInCCChkBox);

			// click on save button
			click(Shared_OR.saveBtn);
			pause(ERPConstant.LOW);

			// verify notification
			commonFunctions.verifyNotification("Success", "Custom field added successfully.");

		} else {
			RESULT.FAIL("Failed to open the 'Add Custom Field' pop-up", true, ScreenshotType.browser);
		}
	}

	/*
	 * Method to enter the details in 'Add Custom Feild'
	 */
	private void enterDetailsInAddCustomFields(String label, String placeHolder, String defaultValue, String type,
			String description, String mandatoryChkBox, String editableInAdminChkBox, String editableInCCChkBox) {
		if (Objects.isNull(customFields)) {
			customFields = createObject("CustomFields");
		}
		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}

		setValue(CustomFields_OR.label, label);
		setValue(CustomFields_OR.placeHolder, placeHolder);
		setValue(CustomFields_OR.description, description);

		// Select the value from 'Type' Drop-down
		By typeDropDown = getLocator(Shared_OR.dropDown, "name", "type");

		click(typeDropDown);
		pause(ERPConstant.LOW);

		commonFunctions.selectOptionFromDropDown(Shared_OR.dropDownOptionLocator, type);

		// Check if the 'Mandatory' check-box is selected or not.
		boolean mandatoryCheckBox = commonFunctions.isElementSelected(CustomFields_OR.mandatoryChkBox);

		if (mandatoryCheckBox) {
			RESULT.FAIL("Failed because 'Mandatory' checkbox is by default selected.", true, ScreenshotType.browser);
		}

		// Check if the 'Editable in Admin' check-box is selected or not.
		boolean editableInAdmin = commonFunctions.isElementSelected(CustomFields_OR.editableInAdminChckBox);
		if (!editableInAdmin) {
			RESULT.FAIL("Failed because 'Editable in Admin' chechbox is not selected by default.", true,
					ScreenshotType.browser);
		}

		// Check if the 'Editable in Client Connect' check-box is selected or
		// not
		boolean editableInClientConnect = commonFunctions.isElementSelected(CustomFields_OR.editableInCCheckBox);
		if (!editableInClientConnect) {
			RESULT.FAIL("Failed because 'Editable in Client Connet' checkbox is not selected by default.", true,
					ScreenshotType.browser);
		}

		if (mandatoryChkBox.equals("Yes")) {
			By checkBoxLocator = getLocator(CustomFields_OR.chckboxLoctor, "Mandatory");
			// Select mandatory check-box
			click(checkBoxLocator);
		}

		if (editableInAdminChkBox.equals("No")) {
			By checkBoxLocator = getLocator(CustomFields_OR.chckboxLoctor, "Editable in Admin");
			// Select the check-box for 'Editable in the admin Check-box'
			click(checkBoxLocator);
			pause(ERPConstant.LOW);
		}

		if (editableInCCChkBox.equals("No")) {
			By checkBoxLocator = getLocator(CustomFields_OR.chckboxLoctor, "Editable in Client");
			// Select the check-box for 'Editable in Client connect' Check-box
			click(checkBoxLocator);
		}
	}

	/*
	 * method to open 'Add Custom Field' pop-up
	 */
	public void openCustomFields() {

		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}
		// Click on "Add Custom Field"
		click(CustomFields_OR.addCustomFieldBtn);
		pause(ERPConstant.MODERATE);

		// verify the title of the 'Add Custom field"
		commonFunctions.verifyPopUpTitle("Add Custom Field");
	}

	/*
	 * Method to edit the custom field details
	 */
	public void editCustomFields(String label, String placeHolder, String defaultValue, String type, String description,
			String mandatoryChkBox, String editableInAdminChkBox, String editableInCCChkBox) {
		if (Objects.isNull(customFields)) {
			customFields = createObject("CustomFields");
		}
		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}

		// click on edit button
		customFields.clickOnEditButton(label);

		// Verify if the 'Edit Custom Field' pop-up is getting opened or not
		boolean flag = isElementExists(CustomFields_OR.customFieldPopUp);
		if (flag) {
			// Verify the title of the pop-up
			commonFunctions.verifyPopUpTitle("Edit Custom Field");

			// Verify if the 'Label' field is disabled or not
			boolean labelField = isElementEnabled(CustomFields_OR.label);
			if (!labelField) {
				RESULT.PASS("Successfully verified that 'Label' field is by default disabled", true,
						ScreenshotType.browser);
			} else {
				RESULT.FAIL("Failed because 'Label' field is enabled.", true, ScreenshotType.browser);
			}

			// Verify the value of the label is showing properly or not
			String labelValue = commonFunctions.getTextOfWebElement(CustomFields_OR.label);
			if (labelValue.equals(label)) {
				RESULT.PASS("The value of 'Label' fied is matched successfully.", true, ScreenshotType.browser);
			} else {
				RESULT.FAIL("Failed to match the vlaue of the 'Label' field.", true, ScreenshotType.browser);
			}

			// Verify if the 'Type' drop-down field is enabled or not
			By typeDropDownLocator = getLocator(Shared_OR.dropDown, "name", "type");
			boolean typeDropDown = commonFunctions.isDropDownEnabled(typeDropDownLocator);
			if (!typeDropDown) {
				RESULT.PASS("Successfully verified that 'Type' dropdown field is by default disabled.", true,
						ScreenshotType.browser);
			} else {
				RESULT.FAIL("Failed because 'Type' dropdown field is by default enabled.", true,
						ScreenshotType.browser);
			}

			// Verify the data showing for 'Type' drop-down is proper or not
			String typeData = commonFunctions.getTextOfWebElement(CustomFields_OR.type);
			if (typeData.equals(type)) {
				RESULT.PASS("The value of the 'Type' dropdown field is matched successfully", true,
						ScreenshotType.browser);
			} else {
				RESULT.FAIL("Failed to match the value of the 'Type' dropdown field.", true, ScreenshotType.browser);
			}
			customFields.enterDetailsInEditCustomFieldForm(placeHolder, defaultValue, description, mandatoryChkBox,
					editableInAdminChkBox, editableInCCChkBox);

			// Click on 'Save' button and verify notification
			click(Shared_OR.saveBtn);
			pause(ERPConstant.VERY_LOW);

			commonFunctions.verifyNotification("Success", "Custom field updated successfully.");
		}

		RESULT.FAIL("Failed because 'Edit Custom Field' pop-up was not opened.", true, ScreenshotType.browser);

	}

	/*
	 * Method to click on edit button for the given label name
	 */
	public void clickOnEditButton(String label) {

		List<WebElement> labelColumnData = commonFunctions.getList(CustomFields_OR.labelColumn);
		String value_new = "##";
		for (int i = 0; i <= labelColumnData.size(); i++) {
			String labelValue = value_new + labelColumnData.get(i).getText() + "##";
			if (labelValue.equals("##" + label + "##")) {
				i = i + 1;
				By editButton = By.xpath("(//mat-icon[contains(text(),'edit')])[" + i + "]");
				click(editButton);
				break;
			}
		}
	}

	/*
	 * Method to enter the details in "Edit Custom Field"
	 */
	public void enterDetailsInEditCustomFieldForm(String placeHolder, String defaultValue, String description,
			String mandatoryChkBox, String editableInAdminChkBox, String editableInCCChkBox) {
		if (Objects.isNull(commonFunctions)) {

			commonFunctions = createObject("CommonFunctions");
		}
		// Clear the data of the all the editable fields in "Edit Custom Field"
		// form

		commonFunctions.clearInput(CustomFields_OR.placeHolder);
		commonFunctions.clearInput(CustomFields_OR.defaultValue);
		commonFunctions.clearInput(CustomFields_OR.description);

		// Enter the data to the all the editable field in "Edit Custom Field"
		setValue(CustomFields_OR.placeHolder, placeHolder);
		setValue(CustomFields_OR.defaultValue, defaultValue);
		setValue(CustomFields_OR.description, description);

		// Select or De-select 'Mandatory' check-box according to the expected
		// result.
		boolean mandatoryCheckBox = commonFunctions.isElementSelected(CustomFields_OR.mandatoryChkBox);
		By mandatoryCheckBoxLocator = getLocator(CustomFields_OR.chckboxLoctor, "Mandatory");

		if (mandatoryCheckBox && mandatoryChkBox.equals("No")) {
			// De-select mandatory check-box
			click(mandatoryCheckBoxLocator);
		}
		if (!mandatoryCheckBox && mandatoryChkBox.equals("Yes")) {
			// Select mandatory check-box
			click(mandatoryCheckBoxLocator);
		}

		// Select or De-select 'Editable In Admin' check-box according to the
		// expected result.

		boolean editableInAdminCheckBox = commonFunctions.isElementSelected(CustomFields_OR.editableInAdminChckBox);
		By editableInAdminChkBoxLocator = getLocator(CustomFields_OR.chckboxLoctor, "Editable in Admin");

		if (editableInAdminCheckBox && editableInAdminChkBox.equals("No")) {
			// De-select the 'Editable In Admin Check-box
			click(editableInAdminChkBoxLocator);
		}
		if (!editableInAdminCheckBox && editableInAdminChkBox.equals("Yes")) {
			// select the 'Editable In Admin Check-box
			click(editableInAdminChkBoxLocator);
		}

		// Select or De-select 'Editable In Client Connect' check-box according
		// to the
		// expected result.

		boolean editableInCCCheckBox = commonFunctions.isElementSelected(CustomFields_OR.editableInAdminChckBox);
		By editableInCCChkBoxLocator = getLocator(CustomFields_OR.chckboxLoctor, "Editable in Client");

		if (editableInCCCheckBox && editableInCCChkBox.equals("No")) {
			// De-select the 'Editable In Admin Check-box
			click(editableInCCChkBoxLocator);
		}
		if (!editableInCCCheckBox && editableInCCChkBox.equals("Yes")) {
			// select the 'Editable In Admin Check-box
			click(editableInCCChkBoxLocator);
		}
	}

	/*
	 * Method to verify the entered 'Custom Fields'
	 */
	public void verifyCustomFieldDetails(String label, String placeHolder, String defaultValue, String type,
			String description, String mandatoryChkBox, String editableInAdminChkBox, String editableInCCChkBox) {
		if (Objects.isNull(customFields)) {
			customFields = createObject("CustomFields");
		}
		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}

		// Store the data in list which was added while creating new custom
		// Fields.

		ArrayList<String> recordList = customFields.getArrayListOfData(label, placeHolder, type, defaultValue,
				description, mandatoryChkBox, editableInAdminChkBox, editableInCCChkBox);

		By columnHeadLocator = (Shared_OR.AllcolumnAHead);

		List<WebElement> labelColumnData = commonFunctions.getList(CustomFields_OR.labelColumn);
		String value_new = "##";
		for (int i = 0; i <= labelColumnData.size(); i++) {
			String labelValue = value_new + labelColumnData.get(i).getText() + "##";
			if (labelValue.equals("##" + label + "##")) {
				i = i + 1;

				break;
			}
		}

		commonFunctions.verifyRecord(recordList, Arrays.asList("Actions"), columnHeadLocator);

	}

	/*
	 * Method to store the data in list which was added while creating new
	 * Custom Field.
	 */
	public ArrayList<String> getArrayListOfData(String label, String placeHolder, String type, String defaultValue,
			String description, String mandatoryChkBox, String editableInAdminChkBox, String editableInCCChkBox) {

		ArrayList<String> dataList = new ArrayList<String>();
		dataList.add(label);
		dataList.add(placeHolder);
		dataList.add(type);
		dataList.add(defaultValue);
		dataList.add(description);
		dataList.add(mandatoryChkBox);
		dataList.add(editableInAdminChkBox);
		dataList.add(editableInCCChkBox);
		return dataList;
	}

	/*
	 * Method to delete the custom fields
	 */

	public void deleteCustomFields(String label) {
		if (Objects.isNull(customFields)) {
			customFields = createObject("CustomFields");
		}
		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}

		List<WebElement> labelColumnData = commonFunctions.getList(CustomFields_OR.labelColumn);
		String value_new = "##";
		for (int i = 0; i <= labelColumnData.size(); i++) {
			String labelValue = value_new + labelColumnData.get(i).getText() + "##";
			if (labelValue.equals("##" + label + "##")) {
				i = i + 1;
				By deleteButton = By.xpath("(//mat-icon[contains(text(),'delete')])[" + i + "]");

				click(deleteButton);
		

			// Check if the 'Confirmation' pop-up is showing or not.
			boolean confirmationDialog = isElementExists(Shared_OR.confirmationDialog);

			if (confirmationDialog) {

				// Get the text of the confirmation dialogue
				String confirmationDialogMessage = getTextWebelement(Shared_OR.deleteConfirmationContent);
				if (confirmationDialogMessage.contains("Do you want to delete " + label + " field?")) {
					RESULT.PASS("Successfully verified the conent of the confirmation dialog.", true,
							ScreenshotType.browser);

					// Click on 'Yes, Delete it' button
					click(Shared_OR.deleteButton);
					pause(ERPConstant.VERY_LOW);

					// verify notification
					commonFunctions.verifyNotification("Success", "Custom field deleted successfully.");
				} else {
					RESULT.FAIL("Failed!!.. because the content of the confirmation dialog is invalid.", true,
							ScreenshotType.browser);
				}
			} else {
				RESULT.FAIL("Failed!!.. Because confirmation dialog is not showing", true, ScreenshotType.browser);
			}
			break;
			}
		}
	}
}
