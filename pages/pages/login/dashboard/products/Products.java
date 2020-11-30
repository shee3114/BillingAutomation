package pages.login.dashboard.products;

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
import pages.login.dashboard.customers.Customers_OR;
import pages.login.dashboard.products.plans.Plans_OR;

public class Products extends BaseComponent {

	CommonFunctions commonFunctions;

	Dashboard dashboard;

	Products products;

	/*
	 * Method to Create/Add new product
	 */
	public void addProduct(String name, String category, String description, String feature, String cardAchRequired,
			String tag) {
		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}
		if (Objects.isNull(dashboard)) {
			dashboard = createObject("Dashboard");
		}
		if (Objects.isNull(products)) {
			products = createObject("Products");
		}
		// Click on 'Add Address' button
		click(Products_OR.addProductButton);
		pause(ERPConstant.LOW);

		// verify page title
		dashboard.verifyModule("Add Product");
		boolean flag = isElementExists(Products_OR.ProductForm);
		if (flag) {
			RESULT.PASS("Successfully verified that 'Add Product' Form is opened successfully.", true,
					ScreenshotType.browser);

			// Enter the details in 'Add Product' form
			products.enterDetailsInAddProductForm(name, category, description, feature, cardAchRequired, tag);

			// Verify and click on 'Save' button
			commonFunctions.verifyAndClickOnButtonIfEnable(Shared_OR.saveBtn);
			pause(ERPConstant.VERY_LOW);

			// Verify Notification
			commonFunctions.verifyNotification("Success", "Product Added Successfully.");
		} else {
			RESULT.FAIL("Failed!!.. Because 'Add Product' Form is not getting opened.'", true, ScreenshotType.browser);
		}
	}

	/*
	 * Method to enter the details in 'Add Product' Form
	 */
	public void enterDetailsInAddProductForm(String name, String category, String description, String feature,
			String cardAchRequired, String tag) {

		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}

		// Enter the details in 'Add Product' Form
		setValue(Products_OR.productName, name);
		setValue(Products_OR.description, description);

		// Select the category
		// Get the locator for category drop-down
		By categoryDropDown = getLocator(Shared_OR.dropDown, "name", "category");
		click(categoryDropDown);
		commonFunctions.selectOptionFromDropDown(Shared_OR.dropDownOptionLocator, category);

		// Enter the data to 'Feature' field and 'Tag' field and hit enter
		// button
		commonFunctions.addFeature(feature);
		commonFunctions.addTag(tag);

		// If the value of the parameter 'cardAchReqired' is No then De-select
		// the 'CARD-ACH Required checkbox.

		if (cardAchRequired.contentEquals("No")) {
			click(Products_OR.cardAchRequiredCheckBox);
		}
	}

	/*
	 * Method to edit the Product details
	 */
	public void editProduct(String name, String category, String description, String feature, String cardAchRequired,
			String tag, String tagToEdit) {

		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}
		if (Objects.isNull(products)) {
			products = createObject("Products");
		}
		// Apply product filter and navigate to the product details page
		products.applyProductFilterByTag(name, tag);

		// Click on the product link on product grid
		products.selectProductFromList(name);

		// Click on edit button
		click(Shared_OR.editButton);

		// Check if the 'Edit Product' form is getting opened or not.
		boolean editProductForm = isElementExists(Products_OR.ProductForm);
		if (editProductForm) {
			RESULT.PASS("Successfully verified that 'Edit Product' form is opened successfully.", true,
					ScreenshotType.browser);

			// Verify if the 'save' button is by default disabled or not
			commonFunctions.verifyIfButtonisDisable(Shared_OR.saveBtn);

			// Enter the details in 'Edit Product' form
			products.enterDetailsInEditProductForm(name, category, description, feature, cardAchRequired, tagToEdit);

			// Click on 'Save' button
			commonFunctions.verifyAndClickOnButtonIfEnable(Shared_OR.saveBtn);

			// Verify Notification
			commonFunctions.verifyNotification("Success", "Product Updated Successfully.");
		} else {
			RESULT.FAIL("Failed!!.. Because 'Edit Product' Form is not getting opened.'", true, ScreenshotType.browser);
		}
	}

	/*
	 * Method to enter the details in 'Edit Product' form
	 */
	public void enterDetailsInEditProductForm(String name, String category, String description, String feature,
			String cardAchRequired, String tagToEdit) {

		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}
		if (Objects.isNull(products)) {
			products = createObject("Products");
		}

		// Verify if the Prduct's name field is disabled or not
		boolean nameField = isElementEnabled(Products_OR.productName);
		if (!nameField) {
			RESULT.PASS("Successfully verfied that the 'Name' field is disabled.", true, ScreenshotType.browser);
		} else {
			RESULT.FAIL("Failed!!.. Because 'Name' field is enabled.", true, ScreenshotType.browser);
		}

		// Verify if the data of 'name' field is showing properly or not
		String nameFieldData = commonFunctions.getTextOfWebElement(Products_OR.productName);
		if (nameFieldData.equals(name)) {
			RESULT.PASS("The Product's name is matched successfully.", true, ScreenshotType.browser);
		} else {
			RESULT.FAIL("Failed to match the product's name.", true, ScreenshotType.browser);
		}

		// Verify 'Category' drop-down field is disabled or not
		// Get the locator for the 'Category' drop-down
		By categoryDropDown = getLocator(Shared_OR.dropDown, "name", "category");
		boolean categoryField = commonFunctions.isDropDownEnabled(categoryDropDown);
		if (!categoryField) {
			RESULT.PASS("Successfully verfied that the 'Category' field is disabled.", true, ScreenshotType.browser);
		} else {
			RESULT.FAIL("Failed!!.. Because 'Category' field is enabled.", true, ScreenshotType.browser);
		}

		// Verify if the data of 'Category' field is showing properly or not
		String categoryFieldData = commonFunctions.getTextOfWebElement(Products_OR.category);
		if (categoryFieldData.equals(category)) {
			RESULT.PASS("The category is matched successfully.", true, ScreenshotType.browser);
		} else {
			RESULT.FAIL("Failed to match the Category.", true, ScreenshotType.browser);
		}

		// Edit the details of the description
		commonFunctions.clearInput(Products_OR.description);
		setValue(Products_OR.description, description);

		// Verify if features are already exist or not and if the exist then
		// remove existing features
		boolean alreadyFeaturesSection = isElementExists(Products_OR.addedFeatureSection);

		if (alreadyFeaturesSection) {
			while (isElementExists(Products_OR.addedFeatureSection)) {
				// Remove all the features
				commonFunctions.removeFeatures(Products_OR.addedFeatureSection);
			}
		}
		// Add the feature Field.
		commonFunctions.addFeature(feature);

		// Verify if the tags are already exist or not and if the tags are exist
		// then remove existing tags.
		boolean alreadyExistingTags = isElementExists(Shared_OR.tagList);
		if (alreadyExistingTags) {
			// Remove all the tags from tag field.
			commonFunctions.removeTags();
		}
		// Add the data to 'Tag' Field.
		commonFunctions.addTag(tagToEdit);

		// If value of parameter 'cardAchRequired' is set to 'No' then de-select
		// the check-box.
		if (cardAchRequired.contentEquals("No")) {
			boolean checkBoxSelected = commonFunctions.isElementSelected(Products_OR.cardAchCheckbox);
			if (checkBoxSelected) {
				// De-select the 'cardAch' Check-box
				click(Products_OR.cardAchRequiredCheckBox);
			}
		} else {
			// If the value of the parameter 'cardAchRequired' is set to 'Yes'
			// then select the check-box.
			boolean checkboxSelected = commonFunctions.isElementSelected(Products_OR.cardAchCheckbox);
			if (!checkboxSelected) {
				// Select the 'cardAch' check-box.
				click(Products_OR.cardAchRequiredCheckBox);
			}
		}
	}

	/*
	 * Method to filter the product and navigate to the product details page.
	 */
	public void applyProductFilterByTag(String name, String tag) {
		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}

		if (Objects.isNull(products)) {
			products = createObject("Products");
		}
		// Click on Drop-down Arrow
		click(Shared_OR.dropDownArrow);
		pause(ERPConstant.VERY_LOW);

		// Click on 'Filter By Tag' option
		click(Products_OR.tagFilterButton);
		pause(ERPConstant.VERY_LOW);

		// Check if 'Select Tags to filter' section is getting opened or not.
		boolean flag = isElementExists(Products_OR.selectTagToFilterSection);
		if (flag) {
			// Select the tag from the tag list
			commonFunctions.selectTagFromTagsSelectorSection(Shared_OR.tagListLocator, tag);

			// Click on Search button
			click(Shared_OR.searchButton);
			pause(ERPConstant.LOW);

			// Select the product from the list
			// products.selectProductFromList(name);
			// pause(ERPConstant.LOW);

		} else {
			RESULT.FAIL("Failed!!.. because 'Select Tag To Filter' Section is not getting opened.", true,
					ScreenshotType.browser);
		}
	}

	/*
	 * The method to select the product from list.
	 */
	public void selectProductFromList(String optionValue) {

		List<WebElement> productNameList = driver.findElements(Products_OR.productNameLink);

		boolean flag = isElementExists(Products_OR.productNameLink);
		if (flag) {
			for (int i = 0; i <= productNameList.size() - 1; i++) {
				String productLabel = "##" + productNameList.get(i).getText() + "##";
				if (productLabel.equals("##" + optionValue + "##")) {
					productNameList.get(i).click();
					RESULT.PASS("Successfully to select the " + optionValue + " option from the dropdown.", true,
							ScreenshotType.browser);
					break;
				}
			}
		} else {
			RESULT.FAIL("Failed because " + optionValue + " is not available in the list", true,
					ScreenshotType.browser);
		}
	}

	/*
	 * Method to Verify the product details
	 */
	public void verifyProductDetails(String name, String category, String description, String feature,
			String cardAchRequired, String tag) {
		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}
		if (Objects.isNull(products)) {
			products = createObject("Products");
		}

		// get arrayList of the product details from 'ProductDetails' page.
		ArrayList<String> productDetailsData = products.getArraylistofProductDetails();

		// Get the arrayList of the data which was added while creating/editing
		// the product
		ArrayList<String> dataList = products.getArrayListOfData(name, category, description, feature, cardAchRequired,
				tag);

		// ArrayList of Products's fields.
		ArrayList<String> fieldList = new ArrayList<String>();
		fieldList.add("Name");
		fieldList.add("Category");
		fieldList.add("Tag");
		fieldList.add("Description");
		fieldList.add("Feature");
		fieldList.add("AchCard Required Field");

		int i = 0;
		for (String field : fieldList) {
			if (productDetailsData.get(i).equals(dataList.get(i))) {
				RESULT.PASS("Product's " + fieldList.get(i) + " is matched successfully.", true,
						ScreenshotType.browser);
			} else {
				RESULT.FAIL("Failed to match the data of Product's " + fieldList.get(i), true, ScreenshotType.browser);
			}
			i++;
		}
		if (cardAchRequired.contentEquals("Yes")) {

			// Check if the text message regarding the card and ACh is mandatory
			// is showing or not.
			boolean flag = isElementExists(Products_OR.cardAchMandatoryText);
			if (flag) {
				RESULT.PASS(
						"Successfully checked that text information message is showing regarding to Card/ACH mandatory",
						true, ScreenshotType.browser);
			} else {
				RESULT.FAIL(
						"Failed!!.. Because text informatino message regarding to the Card/Ach mandatory is not showing.",
						true, ScreenshotType.browser);
			}
		}
	}

	/*
	 * Method to get arrayList of the product details from 'ProductDetails'
	 * page.
	 */
	public ArrayList<String> getArraylistofProductDetails() {

		ArrayList<String> productDetailsData = new ArrayList<String>();
		// Get the details from the 'Product Details' page
		String productNameData = commonFunctions.getTextOfWebElement(Products_OR.productDetailsName);
		String productCategoryData = commonFunctions.getTextOfWebElement(Products_OR.productDetailsCategory);
		String str = commonFunctions.getTextOfWebElement(Products_OR.productDetailsTag);
		String productTagData = str.substring(0, str.length() - 5);
		String productDescriptionData = commonFunctions.getTextOfWebElement(Products_OR.productDetailsDescription);
		String ProductFeatureData = commonFunctions.getTextOfWebElement(Products_OR.productDetailsFeatures);

		boolean flag = isElementExists(Products_OR.cardAchMandatoryText);
		String ProductCardAchRequiredData = null;
		if (flag) {
			ProductCardAchRequiredData = "Yes";
		} else {
			ProductCardAchRequiredData = "No";
		}
		productDetailsData.add(productNameData);
		productDetailsData.add(productCategoryData.trim());
		productDetailsData.add(productTagData.trim());
		productDetailsData.add(productDescriptionData);
		productDetailsData.add(ProductFeatureData.trim());
		productDetailsData.add(ProductCardAchRequiredData);

		return productDetailsData;
	}

	/*
	 * Method to store the data in list which was added while creating/editing
	 * Product.
	 */
	public ArrayList<String> getArrayListOfData(String name, String category, String description, String feature,
			String cardAchRequired, String tag) {

		ArrayList<String> dataList = new ArrayList<String>();
		dataList.add(name);
		dataList.add(category);
		dataList.add(tag);
		dataList.add(description);
		dataList.add(feature);
		dataList.add(cardAchRequired);
		return dataList;

	}

	/*
	 * Method to remove applied filter
	 */
	public void removeProductFilter() {

		// click on cross button to close the customer details page.
		click(Shared_OR.crossButton);
		pause(ERPConstant.MODERATE);
		boolean flag = isElementExists(Shared_OR.cancelButton);
		if (flag) {
			// Click on cancel button in filter section
			click(Shared_OR.cancelButton);
			pause(ERPConstant.MODERATE);
		}
	}

	/*
	 * Method to verify product Details in product grid
	 */
	public void verifyProductDetailsInProductGrid(String name, String category, String description,
			String cardAchRequired, String tag) {

		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}
		if (Objects.isNull(products)) {
			products = createObject("Products");
		}

		// Apply product Filter
		products.applyProductFilterByTag(name, tag);

		// Verify if the 'Published' button is enabled or not
		boolean publishButton = isElementEnabled(Products_OR.productPublishButton);
		if (!publishButton) {
			RESULT.PASS("Successfully checked that 'Publish' button is disabled.", true, ScreenshotType.browser);
		} else {
			RESULT.FAIL("Failed becuase 'Publish' button is enabled.", true, ScreenshotType.browser);
		}
		// Get the arrayList of the data which was added while creating/editing
		// the product
		ArrayList<String> dataList = new ArrayList<String>();
		dataList.add(name);
		dataList.add(category);
		dataList.add(description);
		dataList.add(cardAchRequired);

		// Get the locator of column head
		By columnHeadLocator = (Shared_OR.AllcolumnAHead);

		// Verify Records
		commonFunctions.verifyRecord(dataList, Arrays.asList("Plans", "Actions"), columnHeadLocator);
	}

	/*
	 * Method to go to the productDetails page
	 */
	public void openProductDetailsPage(String productName, String productTag) {

		if (Objects.isNull(products)) {
			products = createObject("Products");
		}
		// Filter the product
		products.applyProductFilterByTag(productName, productTag);

		By productNameLink = getLocator(Products_OR.productsNameLink, productName);

		// Go to 'Product Details' page by clicking on the product name link
		click(productNameLink);
		pause(ERPConstant.LOW);
	}

	/*
	 * Method to publish the product
	 */
	public void publishProduct(String productName, String productTag) {
		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}
		if (Objects.isNull(products)) {
			products = createObject("Products");
		}
		// Apply product filter and navigate to the product details page
		products.openProductDetailsPage(productName, productTag);

		// Verify if the 'product details page is getting opened or not
		boolean productDetailsPage = isElementExists(Products_OR.productDetailsPage);

		if (productDetailsPage) {
			RESULT.PASS("Successfully checked that 'Product Details Page' is opened", true, ScreenshotType.browser);

			// Check if the 'publish' button is exist or not
			boolean publishBtn = isElementExists(Products_OR.productPublishButton);

			if (publishBtn) {
				RESULT.PASS("Successfully checked that 'Publish' button is exist.", true, ScreenshotType.browser);
				click(Products_OR.productPublishButton);
				pause(ERPConstant.VERY_LOW);

				// Verify if the 'confirmation pop-up' about publishing the
				// product is showing or not

				boolean confirmatoinPopUp = isElementExists(Shared_OR.confirmationDialog);

				if (confirmatoinPopUp) {
					RESULT.PASS(
							"Successfully checked that confirmation pop-up is showing regarding publishing the product.",
							true, ScreenshotType.browser);
					// Verify the content of the confirmation dialog and click
					// on "Publish" button
					String dialogContent = getTextWebelement(Shared_OR.confirmationDialogContent);
					if (dialogContent.contains(
							"By publishing the product, users of client connect shall be able to view the product and plans within the product as applicable to that customer."
									+ " You can however unpublish the product at later stage.\n"
									+ "Publish the product " + productName + "?")) {

						RESULT.PASS("The content of the dialog is matched successfully.", true, ScreenshotType.browser);
						// Click on 'Publish' button
						click(Plans_OR.publishButton);
						pause(ERPConstant.VERY_LOW);

						// Verify notification
						commonFunctions.verifyNotification("Success", "Product published successfully.");

					} else {
						RESULT.FAIL("Failed to match the content of confirmation pop-up.", true,
								ScreenshotType.browser);
					}
				} else {
					RESULT.FAIL(
							"Failed because confirmation pop-up regarding publishing the product is not getting opened.",
							true, ScreenshotType.browser);
				}
			} else {
				RESULT.FAIL("Failed because 'Publish' button is not exists.", true, ScreenshotType.browser);
			}
		} else {
			RESULT.FAIL("Failed because 'Product Details Page' was not opened.", true, ScreenshotType.browser);
		}
	}

	/*
	 * Method to Unpublish the product
	 */
	public void unpublishProduct(String productName, String productTag) {
		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}
		if (Objects.isNull(products)) {
			products = createObject("Products");
		}
		// Apply product filter and navigate to the product details page
		products.openProductDetailsPage(productName, productTag);

		// Verify if the 'product details page is getting opened or not
		boolean productDetailsPage = isElementExists(Products_OR.productDetailsPage);

		if (productDetailsPage) {
			RESULT.PASS("Successfully checked that 'Product Details Page' is opened.", true, ScreenshotType.browser);

			// Check if the 'Unpublish' button is exist or not
			boolean unpublishBtn = isElementExists(Products_OR.productUnpublishButton);

			if (unpublishBtn) {
				RESULT.PASS("Successfully checked that 'Unpublish' button is exist", true, ScreenshotType.browser);
				click(Products_OR.productUnpublishButton);
				pause(ERPConstant.VERY_LOW);

				// Verify if the 'confirmation pop-up' about unpublishing the
				// product is showing or not

				boolean confirmatoinPopUp = isElementExists(Shared_OR.confirmationDialog);

				if (confirmatoinPopUp) {
					RESULT.PASS(
							"Successfully checked that confirmation pop-up is showing regarding unpublishing the product",
							true, ScreenshotType.browser);
					// Verify the content of the confirmation dialog and click
					// on "Unpublish" button
					String dialogContent = getTextWebelement(Shared_OR.confirmationDialogContent);
					if (dialogContent.contains(
							"By unpublishing the product, users of client connect shall not be able to view the product itself and all the plans within the product."
									+ " You can however pusblish the product at later stage.\n"
									+ "Unpublish the product " + productName + "?")) {

						RESULT.PASS("The content of the dialog is matched successfully.", true, ScreenshotType.browser);
						// Click on 'Unpublish' button
						click(Plans_OR.unpublishButton);
						pause(ERPConstant.VERY_LOW);

						// Verify notification
						commonFunctions.verifyNotification("Success", "Product unpublished successfully.");

					} else {
						RESULT.FAIL("Failed to match the content of confirmation pop-up.", true,
								ScreenshotType.browser);
					}
				} else {
					RESULT.FAIL(
							"Failed because confirmation pop-up regarding unpublishing the product is not getting opened.",
							true, ScreenshotType.browser);
				}
			} else {
				RESULT.FAIL("Failed because 'Unpublish' button is not exists.", true, ScreenshotType.browser);
			}
		} else {
			RESULT.FAIL("Failed because 'Product Details Page' was not opened.", true, ScreenshotType.browser);
		}
	}
}