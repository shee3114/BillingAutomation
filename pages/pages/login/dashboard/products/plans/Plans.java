package pages.login.dashboard.products.plans;

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
import pages.login.dashboard.products.Products;

public class Plans extends BaseComponent {

	CommonFunctions commonFunctions;
	Dashboard dashboard;
	Products products;
	Plans plans;

	/*
	 * Method to add new Flat based plan in product
	 */
	public void addFlatPlan(String productName, String productTag, String planName, String ribbonName,
			String displayName, String description, String planTag, String basePrice, String setUpPrice,
			String upfrontCost, String applicableStatus, String meteredBilling, String licenseType, String pricingModel,
			String unit, String billEvery, String trailDay, String billingCycle, String featureValue) {

		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}
		if (Objects.isNull(plans)) {
			plans = createObject("Plans");
		}
		if (Objects.isNull(products)) {
			products = createObject("Products");
		}
		// Filter the product
		products.applyProductFilterByTag(productName, productTag);

		// click on 'Add Plan' button
		click(Plans_OR.addPlanFormGrid);
		pause(ERPConstant.LOW);

		// Verify if the 'Add Plan' pop-up is getting opened or not
		boolean planForm = isElementExists(Plans_OR.planForm);
		if (planForm) {

			RESULT.PASS("'Add Plan' page is open successfully.", true, ScreenshotType.browser);
			// Check the title
			String pageTitle = commonFunctions.getTextOfWebElement(Plans_OR.planPopUpTitle);
			String expectedTitle = "Add Plan  in " + productName;
			if (pageTitle.equals(expectedTitle)) {
				RESULT.PASS("Title of the 'Add Plan' is matched successfully", true, ScreenshotType.browser);

				// enter the details in 'Add Plan' form.
				plans.enterDetailsForFlatBasedPlan(planName, ribbonName, displayName, description, planTag, basePrice,
						setUpPrice, upfrontCost, applicableStatus, meteredBilling, licenseType, pricingModel, unit,
						billEvery, trailDay, billingCycle, featureValue);

				// click on 'Save' button
				click(Shared_OR.saveBtn);

				// Verify Notification
				commonFunctions.verifyNotification("Success", "Plan Added Successfully.");
			} else {
				RESULT.FAIL("Failed!!!.. Because title of the 'Add Plan' is not matched.", true,
						ScreenshotType.browser);
			}
		} else {
			RESULT.FAIL("Failed!! becuase 'Add Plan' page is not opened.", true, ScreenshotType.browser);
		}
	}

	/*
	 * Method to enter the detail in Add plan form with Flat pricing model
	 */
	public void enterDetailsForFlatBasedPlan(String planName, String ribbonName, String displayName, String description,
			String planTag, String basePrice, String setUpPrice, String upfrontCost, String applicableStatus,
			String meteredBilling, String licenseType, String pricingModel, String unit, String billEvery,
			String trailDay, String billingCycle, String featureValue) {

		plans.enterDetailsInAddPlanForm(planName, ribbonName, displayName, description, planTag, basePrice, setUpPrice,
				upfrontCost, applicableStatus, meteredBilling, licenseType, billEvery, trailDay, billingCycle,
				featureValue);

		// Go to 'General' tab
		click(Plans_OR.generalTab);
		pause(ERPConstant.LOW);

		// Set Pricing model and Unit
		click(Plans_OR.pricingModel);
		commonFunctions.selectOptionFromDropDown(Shared_OR.dropDownOptionLocator, pricingModel);

		// Set Unit
		click(Plans_OR.pricingUnit);
		commonFunctions.selectOptionFromDropDown(Shared_OR.dropDownOptionLocator, unit);

	}

	/*
	 * Method to add UnitBased Plan
	 */
	public void createPlanWithUnitPricingModel(String productName, String productTag, String planName,
			String ribbonName, String displayName, String description, String planTag, String basePrice,
			String setUpPrice, String upfrontCost, String applicableStatus, String meteredBilling, String licenseType,
			String pricingModel, String unit, String pricePerUnit, String freeQuantity, String billEvery,
			String trailDay, String billingCycle, String featureValue) {
		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}
		if (Objects.isNull(plans)) {
			plans = createObject("Plans");
		}
		if (Objects.isNull(products)) {
			products = createObject("Products");
		}

		// Filter the product
		products.applyProductFilterByTag(productName, productTag);

		// click on 'Add Plan' button
		click(Plans_OR.addPlanFormGrid);
		pause(ERPConstant.LOW);

		// Verify if the 'Add Plan' pop-up is getting opened or not
		boolean planForm = isElementExists(Plans_OR.planForm);
		if (planForm) {

			RESULT.PASS("'Add Plan' page is open successfully.", true, ScreenshotType.browser);
			// Check the title
			String pageTitle = commonFunctions.getTextOfWebElement(Plans_OR.planPopUpTitle);
			String expectedTitle = "Add Plan  in " + productName;
			if (pageTitle.equals(expectedTitle)) {
				RESULT.PASS("Title of the 'Add Plan' is matched successfully", true, ScreenshotType.browser);

				// enter the details in 'Add Plan' form
				plans.enterDetailsForUnitBasedPlan(planName, ribbonName, displayName, description, planTag, basePrice,
						setUpPrice, upfrontCost, applicableStatus, meteredBilling, licenseType, pricingModel, unit,
						pricePerUnit, freeQuantity, billEvery, trailDay, billingCycle, featureValue);

				// click on 'Save' button
				click(Shared_OR.saveBtn);

				// Verify Notification
				commonFunctions.verifyNotification("Success", "Plan Added Successfully.");
			} else {
				RESULT.FAIL("Failed!!!.. Because title of the 'Add Plan' is not matched.", true,
						ScreenshotType.browser);
			}
		} else {
			RESULT.FAIL("Failed!! becuase 'Add Plan' page is not opened.", true, ScreenshotType.browser);
		}
	}

	/*
	 * Method to enter details for adding plan with unit based plan
	 */
	private void enterDetailsForUnitBasedPlan(String planName, String ribbonName, String displayName,
			String description, String planTag, String basePrice, String setUpPrice, String upfrontCost,
			String applicableStatus, String meteredBilling, String licenseType, String pricingModel, String unit,
			String pricePerUnit, String freeQuantity, String billEvery, String trailDay, String billingCycle,
			String featureValue) {

		// Enter the details to "Add Plan" form
		plans.enterDetailsInAddPlanForm(planName, ribbonName, displayName, description, planTag, basePrice, setUpPrice,
				upfrontCost, applicableStatus, meteredBilling, licenseType, billEvery, trailDay, billingCycle,
				featureValue);

		// Go to 'General' tab
		click(Plans_OR.generalTab);
		pause(ERPConstant.LOW);

		// Set Pricing model and Unit
		click(Plans_OR.pricingModel);
		commonFunctions.selectOptionFromDropDown(Shared_OR.dropDownOptionLocator, pricingModel);

		// Set Unit
		click(Plans_OR.pricingUnit);
		commonFunctions.selectOptionFromDropDown(Shared_OR.dropDownOptionLocator, unit);

		// Verify if the 'Price Per Unit' field is showing or not and enter the
		// data into it.
		boolean pricePerUnitField = isElementExists(Plans_OR.pricePerUnitFieldForUnitBased);
		if (pricePerUnitField) {
			RESULT.PASS("Successfully checked that 'Price Per Unit' field is available", true, ScreenshotType.browser);
			setValue(Plans_OR.pricePerUnitFieldForUnitBased, pricePerUnit);
		} else {
			RESULT.FAIL("Failed!!.. because 'Price Per Unit' field is not available.", true, ScreenshotType.browser);
		}

		// Verify if the 'Free Quantity' field is showing or not and enter the
		// data into it.
		boolean freeQtyField = isElementExists(Plans_OR.freeQtyField);
		if (freeQtyField) {
			setValue(Plans_OR.freeQtyField, freeQuantity);
			RESULT.PASS("Successfully checked that 'Free Quantity' field is available.", true, ScreenshotType.browser);
		} else {
			RESULT.FAIL("Failed!!... Because 'Free Quantity' field is not available.", true, ScreenshotType.browser);
		}

	}

	/*
	 * Method to add volume based Plan
	 */
	public void createPlanWithVolumePricingModel(String productName, String productTag, String planName,
			String ribbonName, String displayName, String description, String planTag, String basePrice,
			String setUpPrice, String upfrontCost, String applicableStatus, String meteredBilling, String licenseType,
			String pricingModel, String unit, String pricePerUnit, String endQuantity, String billEvery,
			String trailDay, String billingCycle, String featureValue) {
		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}
		if (Objects.isNull(plans)) {
			plans = createObject("Plans");
		}
		if (Objects.isNull(products)) {
			products = createObject("Products");
		}

		// Filter the product
		products.applyProductFilterByTag(productName, productTag);

		// click on 'Add Plan' button
		click(Plans_OR.addPlanFormGrid);
		pause(ERPConstant.LOW);

		// Verify if the 'Add Plan' pop-up is getting opened or not
		boolean planForm = isElementExists(Plans_OR.planForm);
		if (planForm) {

			RESULT.PASS("'Add Plan' page is open successfully.", true, ScreenshotType.browser);
			// Check the title
			String pageTitle = commonFunctions.getTextOfWebElement(Plans_OR.planPopUpTitle);
			String expectedTitle = "Add Plan  in " + productName;
			if (pageTitle.equals(expectedTitle)) {
				RESULT.PASS("Title of the 'Add Plan' is matched successfully", true, ScreenshotType.browser);

				// enter the details in 'Add Plan' form
				plans.enterDetailsForVolumeBasedPlan(planName, ribbonName, displayName, description, planTag, basePrice,
						setUpPrice, upfrontCost, applicableStatus, meteredBilling, licenseType, pricingModel, unit,
						pricePerUnit, endQuantity, billEvery, trailDay, billingCycle, featureValue);

				// click on 'Save' button
				click(Shared_OR.saveBtn);

				// Verify Notification
				commonFunctions.verifyNotification("Success", "Plan Added Successfully.");
			} else {
				RESULT.FAIL("Failed!!!.. Because title of the 'Add Plan' is not matched.", true,
						ScreenshotType.browser);
			}
		} else {
			RESULT.FAIL("Failed!! becuase 'Add Plan' page is not opened.", true, ScreenshotType.browser);
		}

	}

	/*
	 * Method to enter details for adding plan with Volume Based
	 */
	private void enterDetailsForVolumeBasedPlan(String planName, String ribbonName, String displayName,
			String description, String planTag, String basePrice, String setUpPrice, String upfrontCost,
			String applicableStatus, String meteredBilling, String licenseType, String pricingModel, String unit,
			String pricePerUnit, String endQuantity, String billEvery, String trailDay, String billingCycle,
			String featureValue) {

		plans.enterDetailsInAddPlanForm(planName, ribbonName, displayName, description, planTag, basePrice, setUpPrice,
				upfrontCost, applicableStatus, meteredBilling, licenseType, billEvery, trailDay, billingCycle,
				featureValue);

		// Go to 'General' tab
		click(Plans_OR.generalTab);
		pause(ERPConstant.LOW);

		// Set Pricing model and Unit
		click(Plans_OR.pricingModel);
		commonFunctions.selectOptionFromDropDown(Shared_OR.dropDownOptionLocator, pricingModel);

		// Set Unit
		click(Plans_OR.pricingUnit);
		commonFunctions.selectOptionFromDropDown(Shared_OR.dropDownOptionLocator, unit);

		// Verify the functionality of pricing bracket section
		plans.verifyPricingBracketSection();

		List<String> endQuantityList = Arrays.asList(endQuantity.split(","));
		List<String> pricerPerUnitList = Arrays.asList(pricePerUnit.split(","));

		// Verify if the 'End Quantity' field is showing or not
		boolean pricingBacket = isElementExists(Plans_OR.pricingBracketSection);
		if (pricingBacket) {
			RESULT.PASS("Successfuly checked that 'Pricing Brackets' section is available.", true,
					ScreenshotType.browser);
			for (int i = 0; i < endQuantityList.size(); i++) {
				String endQuantityData = endQuantityList.get(i);
				i = i + 1;
				String index = String.valueOf(i);
				By endQuantityLocator = getLocator(Plans_OR.endQtyField, index);

				// Set the data in 'End Quantity' section of pricing bracket.
				setValue(endQuantityLocator, endQuantityData);

				// Click on Plus button
				click(Plans_OR.addRecordBtn);
				pause(ERPConstant.LOW);
				i = i - 1;
			}

			System.out.println("Size is: " + pricerPerUnitList.size());
			for (int j = 0; j < pricerPerUnitList.size(); j++) {
				String pricePerUnitData = pricerPerUnitList.get(j);
				j = j + 1;
				String index = String.valueOf(j);

				By pricePerUnitLocator = getLocator(Plans_OR.pricePerUnitField, index);
				setValue(pricePerUnitLocator, pricePerUnitData);
				j = j - 1;
			}

		} else {
			RESULT.FAIL("Failed!!.. Because 'Pricing Brackets' section is not available.", true,
					ScreenshotType.browser);
		}
	}

	/*
	 * Method to add tier based plan
	 */
	public void createPlanWithTierPricingModel(String productName, String productTag, String planName,
			String ribbonName, String displayName, String description, String planTag, String basePrice,
			String setUpPrice, String upfrontCost, String applicableStatus, String meteredBilling, String licenseType,
			String pricingModel, String unit, String pricePerUnit, String endQuantity, String billEvery,
			String trailDay, String billingCycle, String featureValue) {
		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}
		if (Objects.isNull(plans)) {
			plans = createObject("Plans");
		}
		if (Objects.isNull(products)) {
			products = createObject("Products");
		}

		// Filter the product
		products.applyProductFilterByTag(productName, productTag);

		// click on 'Add Plan' button
		click(Plans_OR.addPlanFormGrid);
		pause(ERPConstant.LOW);

		// Verify if the 'Add Plan' pop-up is getting opened or not
		boolean planForm = isElementExists(Plans_OR.planForm);
		if (planForm) {

			RESULT.PASS("'Add Plan' page is open successfully.", true, ScreenshotType.browser);
			// Check the title
			String pageTitle = commonFunctions.getTextOfWebElement(Plans_OR.planPopUpTitle);
			String expectedTitle = "Add Plan  in " + productName;
			if (pageTitle.equals(expectedTitle)) {
				RESULT.PASS("Title of the 'Add Plan' is matched successfully", true, ScreenshotType.browser);

				// enter the details in 'Add Plan' form
				plans.enterDetailsForTierBasedPlan(planName, ribbonName, displayName, description, planTag, basePrice,
						setUpPrice, upfrontCost, applicableStatus, meteredBilling, licenseType, pricingModel, unit,
						pricePerUnit, endQuantity, billEvery, trailDay, billingCycle, featureValue);

				// click on 'Save' button
				click(Shared_OR.saveBtn);

				// Verify Notification
				commonFunctions.verifyNotification("Success", "Plan Added Successfully.");
			} else {
				RESULT.FAIL("Failed!!!.. Because title of the 'Add Plan' is not matched.", true,
						ScreenshotType.browser);
			}
		} else {
			RESULT.FAIL("Failed!! becuase 'Add Plan' page is not opened.", true, ScreenshotType.browser);
		}
	}

	/*
	 * Method to enter details for adding plan with Tier Based
	 */
	public void enterDetailsForTierBasedPlan(String planName, String ribbonName, String displayName, String description,
			String planTag, String basePrice, String setUpPrice, String upfrontCost, String applicableStatus,
			String meteredBilling, String licenseType, String pricingModel, String unit, String pricePerUnit,
			String endQuantity, String billEvery, String trailDay, String billingCycle, String featureValue) {

		// Enter the details in add plan form
		plans.enterDetailsInAddPlanForm(planName, ribbonName, displayName, description, planTag, basePrice, setUpPrice,
				upfrontCost, applicableStatus, meteredBilling, licenseType, billEvery, trailDay, billingCycle,
				featureValue);

		// Go to 'General' tab
		click(Plans_OR.generalTab);
		pause(ERPConstant.LOW);

		// Set Pricing model and Unit
		click(Plans_OR.pricingModel);
		commonFunctions.selectOptionFromDropDown(Shared_OR.dropDownOptionLocator, pricingModel);

		// Set Unit
		click(Plans_OR.pricingUnit);
		commonFunctions.selectOptionFromDropDown(Shared_OR.dropDownOptionLocator, unit);

		// Verify the functionality of pricing bracket section
		plans.verifyPricingBracketSection();

		List<String> endQuantityList = Arrays.asList(endQuantity.split(","));
		List<String> pricerPerUnitList = Arrays.asList(pricePerUnit.split(","));

		// Verify if the 'End Quantity' field is showing or not
		boolean pricingBacket = isElementExists(Plans_OR.pricingBracketSection);
		if (pricingBacket) {
			for (int i = 0; i < endQuantityList.size(); i++) {
				String endQuantityData = endQuantityList.get(i);
				i = i + 1;
				String index = String.valueOf(i);
				By endQuantityLocator = getLocator(Plans_OR.endQtyField, index);

				// Set the data in 'End Quantity' section of pricing bracket.
				setValue(endQuantityLocator, endQuantityData);

				// Click on Plus button
				click(Plans_OR.addRecordBtn);
				pause(ERPConstant.LOW);
				i = i - 1;
			}

			System.out.println("Size is: " + pricerPerUnitList.size());
			for (int j = 0; j < pricerPerUnitList.size(); j++) {
				String pricePerUnitData = pricerPerUnitList.get(j);
				j = j + 1;
				String index = String.valueOf(j);

				By pricePerUnitLocator = getLocator(Plans_OR.pricePerUnitField, index);
				setValue(pricePerUnitLocator, pricePerUnitData);
				j = j - 1;
			}
			RESULT.PASS("Successfuly checked that 'Pricing Brackets' section is available.", true,
					ScreenshotType.browser);
		} else {
			RESULT.FAIL("Failed!!.. Because 'Pricing Brackets' section is not available.", true,
					ScreenshotType.browser);
		}

	}

	/*
	 * Method to add Hybrid Based Plan
	 */
	public void createPlanWithHybridPricingModel(String productName, String productTag, String planName,
			String ribbonName, String displayName, String description, String planTag, String setUpPrice,
			String upfrontCost, String applicableStatus, String meteredBilling, String licenseType, String unit,
			String billEvery, String trailDay, String billingCycle, String featureValue, String groupEndQuantity,
			String flatBasePrice, String flatPricingModel, String unitBasePrice, String unitPricingModel,
			String unitPricePerUnit, String unitFreeQuantity, String volumeBasePrice, String volumePricingModel,
			String volumeEndQuntity, String volumePricePerUnit, String tierBasePrice, String triePricingModel,
			String tierEndQuanity, String tier, String tierPricePerUnit) {

		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}
		if (Objects.isNull(plans)) {
			plans = createObject("Plans");
		}
		if (Objects.isNull(products)) {
			products = createObject("Products");
		}

		// Filter the product
		products.applyProductFilterByTag(productName, productTag);

		// click on 'Add Plan' button
		click(Plans_OR.addPlanFormGrid);
		pause(ERPConstant.LOW);

		// Verify if the 'Add Plan' pop-up is getting opened or not
		boolean planForm = isElementExists(Plans_OR.planForm);
		if (planForm) {

			RESULT.PASS("'Add Plan' page is open successfully.", true, ScreenshotType.browser);
			// Check the title
			String pageTitle = commonFunctions.getTextOfWebElement(Plans_OR.planPopUpTitle);
			String expectedTitle = "Add Plan  in " + productName;
			if (pageTitle.equals(expectedTitle)) {
				RESULT.PASS("Title of the 'Add Plan' is matched successfully", true, ScreenshotType.browser);

				// enter the details in 'Add Plan' form
				plans.enterDetailsForHybridBasedPlan(planName, ribbonName, displayName, description, planTag,
						setUpPrice, upfrontCost, applicableStatus, meteredBilling, licenseType, unit, billEvery,
						trailDay, billingCycle, featureValue);
				// click on 'Save' button
				click(Shared_OR.saveBtn);

				// Verify Notification
				commonFunctions.verifyNotification("Success", "Plan Added Successfully.");
			} else {
				RESULT.FAIL("Failed!!!.. Because title of the 'Add Plan' is not matched.", true,
						ScreenshotType.browser);
			}
		} else {
			RESULT.FAIL("Failed!! becuase 'Add Plan' page is not opened.", true, ScreenshotType.browser);
		}
	}

	/*
	 * Method to enter details for adding plan with Tier Based
	 */
	public void enterDetailsForHybridBasedPlan(String planName, String ribbonName, String displayName,
			String description, String planTag, String basePrice, String setUpPrice, String upfrontCost,
			String applicableStatus, String meteredBilling, String licenseType, String unit, String billEvery,
			String trailDay, String billingCycle, String featureValue) {

		// Enter the details in add plan form
		plans.enterDetailsInAddPlanForm(planName, ribbonName, displayName, description, planTag, basePrice, setUpPrice,
				upfrontCost, applicableStatus, meteredBilling, licenseType, billEvery, trailDay, billingCycle,
				featureValue);

		// Go to 'General' tab
		click(Plans_OR.generalTab);
		pause(ERPConstant.LOW);

		// Set Pricing model and Unit
		click(Plans_OR.pricingModel);
		commonFunctions.selectOptionFromDropDown(Shared_OR.dropDownOptionLocator, pricingModel);

		// Set Unit
		click(Plans_OR.pricingUnit);
		commonFunctions.selectOptionFromDropDown(Shared_OR.dropDownOptionLocator, unit);

	}

	/*
	 * /* Method to enter the details in 'Add Plan' Form
	 */
	private void enterDetailsInAddPlanForm(String planName, String ribbonName, String displayName, String description,
			String planTag, String basePrice, String setUpPrice, String upfrontCost, String applicableStatus,
			String meteredBilling, String licenseType, String billEvery, String trailDay, String billingCycle,
			String featureValue) {
		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}

		setValue(Plans_OR.name, planName);
		setValue(Plans_OR.ribbonName, ribbonName);
		setValue(Plans_OR.displayName, displayName);
		setValue(Plans_OR.description, description);

		// Enter the value in the tags
		commonFunctions.addTag(planTag);

		// Verify if the 'All Direct Customers' check-box is by default selected
		// or not
		boolean allDCChkBox = commonFunctions.isElementSelected(Plans_OR.allDCChkBox);
		if (allDCChkBox) {
			RESULT.FAIL("Failed because..by default 'All Direct Customers' checkbox is selected.", true,
					ScreenshotType.browser);
		}

		// Verify if the 'Selected Direct Customers' Check-box is by default
		// selected or not
		boolean selectedDCChkBox = commonFunctions.isElementSelected(Plans_OR.selectdDCChkBox);
		if (selectedDCChkBox) {
			RESULT.FAIL("Failed because..by default 'Selected Direct Customers' checkbox is selected.", true,
					ScreenshotType.browser);
		}

		// Verify if the 'all VARs' Check-box is by default
		// selected or not
		boolean allVARChkBox = commonFunctions.isElementSelected(Plans_OR.allVarChkBox);
		if (allVARChkBox) {
			RESULT.FAIL("Failed because..by default 'All VARs' checkbox is selected.", true, ScreenshotType.browser);
		}

		// Verify if the 'Selected VARs' Check-box is by default
		// selected or not
		boolean selectedVARChkBox = commonFunctions.isElementSelected(Plans_OR.selectedVarChkBox);
		if (selectedVARChkBox) {
			RESULT.FAIL("Failed because..by default 'Selected VARs' checkbox is selected.", true,
					ScreenshotType.browser);
		}

		// Verify if the 'Metered Billing' check-box is by default selected or
		// not

		boolean meteredBillingChckBox = commonFunctions.isElementSelected(Plans_OR.meteredBillingChkBox);
		if (meteredBillingChckBox) {
			RESULT.FAIL("Failed because..by default 'Metered Billing' checkbox is selected.", true,
					ScreenshotType.browser);
		}
		if (meteredBilling.equals("Yes")) {
			// Select the check-box for the 'Metered Billing'
			click(Plans_OR.meteredBillingChkBoxLocator);
		} else {
			// Select the license from the drop-down.
			By licenseTypeLocator = getLocator(Shared_OR.dropDown, "formcontrolname", "licenseType");
			click(licenseTypeLocator);
			pause(ERPConstant.VERY_LOW);
			commonFunctions.selectOptionFromDropDown(Shared_OR.dropDownOptionLocator, licenseType);
		}

		// Select check-box for 'All Direct customers
		plans.selectAllDirectCustomers();

		// Select check-box for 'All VAR Customers
		plans.selectAllDirectCustomers();

		// Set base price and setup Price
		setValue(Plans_OR.basePrice, basePrice);
		setValue(Plans_OR.setUpPrice, setUpPrice);

		// Verify if the by default 'Applicable status' is disabled or not.
		By applicableStatusDropDown = getLocator(Shared_OR.dropDown, "formcontrolname", "applicableStatus");

		boolean applicableStatusFieldDefault = commonFunctions.isDropDownEnabled(applicableStatusDropDown);

		if (applicableStatusFieldDefault) {
			RESULT.FAIL("Failed because by default 'Applicable Status' is enabled.", true, ScreenshotType.browser);
		}
		// Set the value to 'Upfront Payment' section
		setValue(Plans_OR.upfrontCost, upfrontCost);

		boolean applicableStatusField = commonFunctions.isDropDownEnabled(applicableStatusDropDown);
		if (applicableStatusField) {
			// up-front status
			click(applicableStatusDropDown);
			pause(ERPConstant.VERY_LOW);
			commonFunctions.selectOptionFromDropDown(Shared_OR.dropDownOptionLocator, applicableStatus);
		} else {
			RESULT.FAIL(
					"Failed because 'Applicable Status' field is not getting enabled after entering the upfront cost..",
					true, ScreenshotType.browser);
		}

		// Set the bill Every
		jsScrollToElement(Plans_OR.billEvery); // Just check it
		commonFunctions.clearInput(Plans_OR.billEvery);
		setValue(Plans_OR.billEvery, billEvery);

		commonFunctions.clearInput(Plans_OR.trailDays);
		setValue(Plans_OR.trailDays, trailDay);

		// Set the values in 'Billing Cycle'
		setValue(Plans_OR.billingCycle, billingCycle);

		// Add Features to the plan
		plans.addFeaturesToPlan(featureValue);

	}

	/*
	 * 
	 * /* Method to edit plan details
	 */
	public void editPlan(String productName, String productTag, String planName, String ribbonName, String displayName,
			String description, String planTag, String basePrice, String setUpPrice, String upfrontCost,
			String upfrontStatus, String meteredBilling, String licenseType, String pricingModel, String unit,
			String billEvery, String trailDay, String billingCycle, String selectVAR, String selectDC, String varID,
			String dcID) {

		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}
		if (Objects.isNull(plans)) {
			plans = createObject("Plans");
		}
		if (Objects.isNull(products)) {
			products = createObject("Products");
		}
		// Apply Filter by product tag and go to the product details page
		products.openProductDetailsPage(productName, productTag);

		// Search the Plan name from the list which need to edit
		plans.clickOnEditButton(planName);

		// Verify if the 'Edit Plan' pop-up is getting opened or not.
		boolean planForm = isElementExists(Plans_OR.planForm);
		if (planForm) {
			RESULT.PASS("'Edit Plan' Form is opened successfully.", true, ScreenshotType.browser);

			// Verify the title of the pop-up
			String pageTitle = commonFunctions.getTextOfWebElement(Plans_OR.planPopUpTitle);
			String expectedTitle = "Edit Plan " + planName + " in " + productName;
			if (pageTitle.equals(expectedTitle)) {
				RESULT.PASS("The title of the 'Edit Plan' is matched successfully.", true, ScreenshotType.browser);

				// Select multiple direct customers for the plan
				plans.selectDirectCusotomerForPlan(planName, selectDC, dcID);

				// Select multiple VAR customers for the plan.
				plans.selectVARCustomerForPlan(planName, selectVAR, varID);

				// Scroll to element
				jsScrollToElement(Plans_OR.name);
				pause(ERPConstant.VERY_LOW);

				// Enter the details in 'Edit Plan' form
				plans.enterDetailsInEditPlanForm(productName, productTag, planName, ribbonName, displayName,
						description, planTag, basePrice, setUpPrice, upfrontCost, upfrontStatus, meteredBilling,
						licenseType, pricingModel, unit, billEvery, trailDay, billingCycle);
				// click on 'Save' button
				click(Shared_OR.saveBtn);

				// Verify Notification
				commonFunctions.verifyNotification("Success", "Plan Updated Successfully.");

			} else {
				RESULT.FAIL("The title of the 'Edit Plan' is not matched successfully.", true, ScreenshotType.browser);
			}
		} else {
			RESULT.FAIL("Failed to open the 'Edit Plan' form ", true, ScreenshotType.browser);
		}
	}

	/*
	 * Method to enter the details in 'Edit Plan'
	 */
	private void enterDetailsInEditPlanForm(String productName, String productTag, String planName, String ribbonName,
			String displayName, String description, String planTag, String basePrice, String setUpPrice,
			String upfrontCost, String upfrontStatus, String meteredBilling, String licenseType, String pricingModel,
			String unit, String billEvery, String trailDay, String billingCycle) {

		// Verify if the 'Name' field is enabled or not
		boolean nameField = isElementEnabled(Plans_OR.name);

		if (nameField) {
			RESULT.FAIL("Failed because the 'Name' field is not by default disabled.", true, ScreenshotType.browser);
		}

		// Verify if the data of the 'Name' field is proper or not.
		String nameFieldData = commonFunctions.getTextOfWebElement(Plans_OR.name);
		if (!nameFieldData.equals(planName)) {
			RESULT.FAIL("Failed to match the data of the 'Name' field.", true, ScreenshotType.browser);
		}

		// Cleared the data of all the editable fields of the 'Edit Plan' form.
		commonFunctions.clearInput(Plans_OR.ribbonName);
		commonFunctions.clearInput(Plans_OR.displayName);
		commonFunctions.clearInput(Plans_OR.description);
		commonFunctions.clearInput(Plans_OR.basePrice);
		commonFunctions.clearInput(Plans_OR.setUpPrice);
		jsScrollToElement(Plans_OR.billEvery); // Just check it
		commonFunctions.clearInput(Plans_OR.billEvery);
		commonFunctions.clearInput(Plans_OR.trailDays);

		setValue(Plans_OR.ribbonName, ribbonName);
		setValue(Plans_OR.displayName, displayName);
		setValue(Plans_OR.description, description);
		setValue(Plans_OR.basePrice, basePrice);
		setValue(Plans_OR.setUpPrice, setUpPrice);
		setValue(Plans_OR.billEvery, billEvery);
		setValue(Plans_OR.trailDays, trailDay);

		// Set the value to 'Upfront Payment' section
		setValue(Plans_OR.upfrontCost, upfrontCost);

		// up-front status
		By upfrontDropDownLocator = getLocator(Shared_OR.dropDown, "formcontrolname", "applicableStatus");
		click(upfrontDropDownLocator);
		pause(ERPConstant.VERY_LOW);
		commonFunctions.selectOptionFromDropDown(Shared_OR.dropDownOptionLocator, upfrontStatus);
	}

	/*
	 * Method to search for the required plan from the plan list and then click on
	 * edit button
	 */
	public void clickOnEditButton(String planName) {
		List<WebElement> planNameColumnData = commonFunctions.getList(Plans_OR.planName);
		String value_new = "##";
		for (int i = 0; i <= planNameColumnData.size(); i++) {
			String labelValue = value_new + planNameColumnData.get(i).getText() + "##";
			if (labelValue.equals("##" + planName + "##")) {
				i = i + 1;
				By editButton = By.xpath("(//button//mat-icon[contains(text(),'edit')])[" + i + "]");
				click(editButton);
				i = i - 1;
				break;
			}
		}
	}

	/*
	 * Method to publish the plan
	 */
	public void publishPlans(String productName, String productTag, String planName) {
		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}
		if (Objects.isNull(plans)) {
			plans = createObject("Plans");
		}
		if (Objects.isNull(products)) {
			products = createObject("Products");
		}
		// Apply Filter by product tag and go to the product details page
		products.openProductDetailsPage(productName, productTag);

		// Get the row number of the plan.
		int number = plans.getPlanRowNumber(planName);
		By publishButton = By
				.xpath("//tr[contains(@class,'clickable')][" + number + "]//button//span[contains(text(),'Publish')]");

		// Verify if the 'Publish' button is exist or not
		boolean publishBtn = isElementExists(publishButton);
		if (publishBtn) {

			RESULT.PASS("Successfully checked that 'Publish' button is available for the plan.", true,
					ScreenshotType.browser);

			// click on 'Publish' button
			click(publishButton);
			pause(ERPConstant.VERY_LOW);

			// Verify if the 'Confirmation' pop-up is showing or not.
			boolean confirmationDialog = isElementExists(Shared_OR.confirmationDialog);
			if (confirmationDialog) {

				// Verify the content of the confirmation dialog
				String confirmationContent = commonFunctions.getTextWebelement(Plans_OR.confirmationDialogContent);

				if (confirmationContent.contains(
						"By publishing the plan, users of client connect shall be able to view the respective plan within the product. "
								+ "You can however unpublish the plan at later stage.\n" + "Publish the selected plan "
								+ planName + "?")) {
					RESULT.PASS("Successfully verified the content of 'Confirmation' dialogue", true,
							ScreenshotType.browser);

					// Click on publish button
					click(Plans_OR.publishButton);

					// Verify Notification
					commonFunctions.verifyNotification("Success", "Plan published successfully.");

				} else {
					RESULT.FAIL("Failed!!.. because the content of the confirmation dialog is invalid.", true,
							ScreenshotType.browser);
				}
			} else {
				RESULT.FAIL("Failed because 'Cofirmation' dialog is not showing.", true, ScreenshotType.browser);
			}
		} else {
			RESULT.FAIL("Failed because 'Publish' button is not available", true, ScreenshotType.browser);
		}
		// break;
		// }
		// }
	}

	/*
	 * Method to 'Un-publish Product'
	 */
	public void unpublishPlans(String productName, String productTag, String planName) {
		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}
		if (Objects.isNull(plans)) {
			plans = createObject("Plans");
		}
		if (Objects.isNull(products)) {
			products = createObject("Products");
		}
		// Apply Filter by product tag and go to the product details page
		products.openProductDetailsPage(productName, productTag);

		// Get the row number of the plan from plan list.
		int number = plans.getPlanRowNumber(planName);
		By unpublishButton = By.xpath(
				"//tr[contains(@class,'clickable')][" + number + "]//button//span[contains(text(),'Unpublish')]");

		// Verify if the 'Unpublish' button is exists or not

		boolean unpublishBtn = isElementExists(unpublishButton);
		if (unpublishBtn) {

			RESULT.PASS("Successfully checked that 'Publish' button is available for the plan.", true,
					ScreenshotType.browser);

			// click on 'Unpublish' button
			click(unpublishButton);
			pause(ERPConstant.VERY_LOW);

			// Verify if the 'Confirmation' dialog is showing or not.
			boolean confirmationDialog = isElementExists(Shared_OR.confirmationDialog);
			if (confirmationDialog) {

				// Verify the content of the confirmation dialog.
				String confirmationContent = commonFunctions.getTextWebelement(Plans_OR.confirmationDialogContent);
				System.out.println(confirmationContent);
				if (confirmationContent.contains(
						"By unpublishing the plan, users of client connect shall not be able to view the respective plan within the product."
								+ " You can however publish the plan at later stage.\n" + "Unpublish the selected plan "
								+ planName + "?")) {
					RESULT.PASS("Successfully verified the content of 'Confirmation' dialogue", true,
							ScreenshotType.browser);

					// Click on publish button
					click(Plans_OR.unpublishButton);

					// Verify notification.
					commonFunctions.verifyNotification("Success", "Plan unpublished successfully.");

				} else {
					RESULT.FAIL("Failed!!.. because the content of the confirmation dialog is invalid.", true,
							ScreenshotType.browser);
				}
			} else {
				RESULT.FAIL("Failed!!.. Because confirmation dialog is not showing.", true, ScreenshotType.browser);
			}
		} else {
			RESULT.FAIL("Failed!!.. because 'Unpublish' button is not available", true, ScreenshotType.browser);
		}
		// break;
		// }
		// }
	}

	/*
	 * Method to Delete the plan
	 */
	public void deletePlan(String productName, String productTag, String planName) {
		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}
		if (Objects.isNull(plans)) {
			plans = createObject("Plans");
		}
		if (Objects.isNull(products)) {
			products = createObject("Products");
		}
		// Apply Filter by product tag and go to the product details page
		products.openProductDetailsPage(productName, productTag);

		// Get the row number of the plan
		int rowNumber = plans.getPlanRowNumber(planName);

		By deleteButton = By.xpath("(//button//mat-icon[contains(text(),'delete')])[" + rowNumber + "]");

		// Verify if the 'Delete' button exist or not
		boolean flag = isElementExists(deleteButton);
		if (flag) {
			RESULT.PASS("Successfully checked that 'Delete' button is available for the plan", true,
					ScreenshotType.browser);

			// Click on 'Delete' button
			click(deleteButton);
			pause(ERPConstant.VERY_LOW);

			// Verify if the confirmation dialogue is showing or not.
			boolean confirmationDialog = isElementExists(Shared_OR.confirmationDialog);
			if (confirmationDialog) {

				// Verify the content of the confirmation dialog
				String confirmationContent = commonFunctions.getTextWebelement(Plans_OR.confirmationDialogContent);
				System.out.println(confirmationContent);
				if (confirmationContent.contains(
						"By deleting the selected plan, it will deleted forever from the product but all the data/history related to the plan will be abailable.\n"
								+ "Delete the selected plan " + planName + " from product?")) {
					RESULT.PASS("Successfully verified the content of 'Confirmation' dialogue", true,
							ScreenshotType.browser);

					// Click on delete button
					click(Shared_OR.deleteButton);

					// Verify Notification.
					commonFunctions.verifyNotification("Success", "Plan Removed Successfully.");
				} else {
					RESULT.FAIL("Failed!!.. Because the content of the confirmation dialog is invalid.", true,
							ScreenshotType.browser);
				}
			} else {
				RESULT.FAIL("Failed!!.. Because confirmation dialog was not showing.", true, ScreenshotType.browser);
			}
		} else {
			RESULT.FAIL("Failed!!.. Because 'Delete' button is not available", true, ScreenshotType.browser);
		}
		// break;
		// }
		// }
	}

	/*
	 * Method to create the plan for specific VAR or specific Direct Customers.
	 */
	public void createPlanWithSelectedCustomers(String productName, String productTag, String planName,
			String selectVAR, String selectDC, String varID, String dcID) {
		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}
		if (Objects.isNull(plans)) {
			plans = createObject("Plans");
		}

		if (Objects.isNull(products)) {
			products = createObject("Products");
		}
		// Apply Filter by product tag and go to the product details page
		// products.openProductDetailsPage(productName, productTag);

		// Search the Plan name from the list which need to edit
		plans.clickOnEditButton(planName);

		// Check if the 'Edit Plan' pop-up is not getting opened or not

		boolean flag = isElementExists(Plans_OR.planForm);
		if (flag) {

			RESULT.PASS("Successfully checked that 'Edit Plan' form is getting opened.", true, ScreenshotType.browser);

			// Select multiple direct customers for the plan
			plans.selectDirectCusotomerForPlan(planName, selectDC, dcID);

			// Select multiple VAR customers for the plan.
			plans.selectVARCustomerForPlan(planName, selectVAR, varID);

			// click on 'Save' button.
			click(Shared_OR.saveBtn);

			// Verify Notification.
			commonFunctions.verifyNotification("Success", "Plan Updated Successfully.");

		} else {
			RESULT.FAIL("Failed because 'Edit Plan' form is not geting opened", true, ScreenshotType.browser);
		}
	}

	/*
	 * Method to select direct customers for the plan
	 */
	public void selectSingleDirectCusotomerForPlan(String planName, String allDC, String selectDC, String dcID) {
		String dcId = "(" + dcID + ")";

		// Check if the 'Select Direct Customers'
		Boolean selectDirectCustomerChkBox = commonFunctions.isElementSelected(Plans_OR.selectdDCChkBox);
		By selectDirectCustomerChkBoxLocator = getLocator(Plans_OR.checkBoxLocator, "forDC");

		if (!selectDirectCustomerChkBox && selectDC.contains("Yes")) {
			click(selectDirectCustomerChkBoxLocator);
			pause(ERPConstant.VERY_LOW);

			// Verify if the 'Select Customers' button is showing or not.
			boolean flag = isElementExists(Plans_OR.selectCustomers);
			if (flag) {
				RESULT.PASS("Successfully checked that 'Select Customers' button is showing.", true,
						ScreenshotType.browser);
				click(Plans_OR.selectCustomers);
				pause(ERPConstant.VERY_LOW);

				// Verify if the 'Assign Customers to Plan" pop-up is showing or
				// not.
				boolean assignCustomersPopUp = isElementExists(Plans_OR.assignCustomersToPlan);
				if (assignCustomersPopUp) {

					// Verify the title of the pop-up
					String popUpTitle = getTextWebelement(Shared_OR.popUpTitle);
					if (popUpTitle.contains("Assign customers to " + planName)) {
						RESULT.PASS("Successfully verified that the name of the pop-up title.", true,
								ScreenshotType.browser);

					} else {
						RESULT.FAIL("Failed to match the name of the pop-up title.", true, ScreenshotType.browser);
					}

					// Check if the 'Save' button is enabled or not

					boolean saveBtn = isElementEnabled(Plans_OR.assignCustomerSaveBtn);

					if (saveBtn) {
						RESULT.FAIL("Failed because 'Save' button is enabled by default.", true,
								ScreenshotType.browser);
					}
					// Get the list of the customer's name list
					List<WebElement> dCCustomerList = commonFunctions.getList(Plans_OR.selectCustomerSectionList);
					for (int i = 0; i < dCCustomerList.size(); i++) {
						String customerID = dCCustomerList.get(i).getText().trim();
						if (customerID.equals(dcId)) {
							i = i + 1;
							By selectBtn = By.xpath(
									"((//div[contains(@class,'cdk-drop-list')])[1]//div[contains(@class,'example-box')]//button)["
											+ i + "]");
							// click on 'Select' button.
							click(selectBtn);
							i = i - 1;

							// Verify if the selected custom is showing in the
							// 'Selected
							// Customers' section or not
							String customerId = commonFunctions.getTextWebelement(Plans_OR.selectedCustomersCustomerID);
							String directCustomerID = customerId.replaceAll("\\s", "");
							if (directCustomerID.equals(dcId)) {
								// Click on 'Save' button
								commonFunctions.verifyAndClickOnButtonIfEnable(Plans_OR.assignCustomerSaveBtn);
								pause(ERPConstant.VERY_LOW);
								RESULT.PASS("Reuqired Customer is selected properly", true, ScreenshotType.browser);
							} else {
								RESULT.FAIL("Customers is not selected.", true, ScreenshotType.browser);
							}
							break;
						}
					}
				} else {
					RESULT.FAIL("Failed because 'Assign Customers' pop-up was not opened.", true,
							ScreenshotType.browser);
				}
			} else {
				RESULT.FAIL("Failed because 'Select Customers' button is not exist.", true, ScreenshotType.browser);
			}
		}
	}

	/*
	 * Method to select VAR for the plan.
	 */
	public void selectSingleVARCustomerForPlan(String planName, String allVAR, String selectVAR, String varID) {

		String varId = "(" + varID + ")";

		Boolean selectVARCustomerChkBox = commonFunctions.isElementSelected(Plans_OR.selectedVarChkBox);
		By selectVARCustomerChkBoxLocator = getLocator(Plans_OR.checkBoxLocator, "forVars");

		// If the value of 'Select VAR' is 'Yes' or 'Select VAR' check-box is
		// not selected.
		if (!selectVARCustomerChkBox && selectVAR.contains("Yes")) {
			click(selectVARCustomerChkBoxLocator);
			// Verify if the 'Select VARs' button is showing or not.
			boolean flag = isElementExists(Plans_OR.selectVARs);
			if (flag) {
				RESULT.PASS("Successfully checked that 'Select VARs' button is showing.", true, ScreenshotType.browser);
				click(Plans_OR.selectVARs);
				pause(ERPConstant.VERY_LOW);

				// Verify if the 'Assign Customers to Plan" pop-up is showing or
				// not.
				boolean assignCustomersPopUp = isElementExists(Plans_OR.assignCustomersToPlan);
				if (assignCustomersPopUp) {

					// Verify the title of the pop-up
					String popUpTitle = getTextWebelement(Shared_OR.popUpTitle);
					if (popUpTitle.contains("Assign customers to " + planName)) {
						RESULT.PASS("Successfully verified that the name of the pop-up title.", true,
								ScreenshotType.browser);
					} else {
						RESULT.FAIL("Failed to match the name of the pop-up title.", true, ScreenshotType.browser);
					}

					// Get the list of the customer's name list
					List<WebElement> dCCustomerList = commonFunctions.getList(Plans_OR.selectCustomerSectionList);
					for (int i = 0; i < dCCustomerList.size(); i++) {
						String customerID = dCCustomerList.get(i).getText().trim();
						if (customerID.equals(varId)) {
							i = i + 1;
							By selectBtn = By.xpath(
									"((//div[contains(@class,'cdk-drop-list')])[1]//div[contains(@class,'example-box')]//button)["
											+ i + "]");
							// click on 'Select' button.
							click(selectBtn);
							i = i - 1;

							// Verify if the selected custom is showing in the
							// 'Selected
							// Customers' section or not
							String customerId = commonFunctions.getTextWebelement(Plans_OR.selectedCustomersCustomerID);
							String varCustomerID = customerId.replaceAll("\\s", "");
							if (varCustomerID.equals(varId)) {
								RESULT.PASS("Reuqired Customer is selected properly", true, ScreenshotType.browser);

								// Click on 'Save' button
								commonFunctions.verifyAndClickOnButtonIfEnable(Plans_OR.assignCustomerSaveBtn);
								pause(ERPConstant.VERY_LOW);
							} else {
								RESULT.FAIL("Customers is not selected.", true, ScreenshotType.browser);
							}
							break;
						}
					}
				} else {
					RESULT.FAIL("Failed because 'Assign Customers' pop-up was not opened.", true,
							ScreenshotType.browser);
				}
			} else {
				RESULT.FAIL("Failed because 'Select VARs' button is not exist.", true, ScreenshotType.browser);
			}
		}

	}

	/*
	 * Method to select all Direct customers
	 */
	public void selectAllDirectCustomers() {

		// Check if the 'All Direct Customers' check-box is selected or not.
		boolean allDirectChkBox = commonFunctions.isElementSelected(Plans_OR.allDCChkBox);
		By allDirectCheckBoxLocator = getLocator(Plans_OR.checkBoxLocator, "forAllDCs");

		if (!allDirectChkBox) {
			click(allDirectCheckBoxLocator);
			pause(ERPConstant.VERY_LOW);
		} else {
			RESULT.INFO("Checkbox is already selected for 'All Direct Customers'.", true, ScreenshotType.browser);
		}
	}

	/*
	 * Method to select all VARs
	 */
	public void selectAllVARs() {

		// Check if the 'All VARs' check-box is selected or not.
		boolean allVARChkBox = commonFunctions.isElementSelected(Plans_OR.allVarChkBox);
		By allVARChkBoxLocator = getLocator(Plans_OR.checkBoxLocator, "forAllVars");

		if (!allVARChkBox) {
			// Select the 'All VARs Check-box'
			click(allVARChkBoxLocator);
			pause(ERPConstant.VERY_LOW);
		} else {
			RESULT.INFO("Checkbox is already selected for 'All VARs Customers'.", true, ScreenshotType.browser);
		}
	}

	/*
	 * Method to select multiple direct customers for plan
	 */
	public void selectDirectCusotomerForPlan(String planName, String selectDC, String dcID) {

		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}
		if (Objects.isNull(plans)) {
			plans = createObject("Plans");
		}

		List<String> ids = Arrays.asList(dcID.split(","));

		// Check if the 'Select Direct Customers'
		Boolean selectDirectCustomerChkBox = commonFunctions.isElementSelected(Plans_OR.selectdDCChkBox);
		By selectDirectCustomerChkBoxLocator = getLocator(Plans_OR.checkBoxLocator, "forDC");

		if (!selectDirectCustomerChkBox && selectDC.contains("Yes")) {
			click(selectDirectCustomerChkBoxLocator);
			pause(ERPConstant.VERY_LOW);

			// Verify if the 'Select Customers' button is showing or not.
			boolean flag = isElementExists(Plans_OR.selectCustomers);
			if (flag) {
				RESULT.PASS("Successfully checked that 'Select Customers' button is showing.", true,
						ScreenshotType.browser);
				click(Plans_OR.selectCustomers);
				pause(ERPConstant.VERY_LOW);

				// Verify if the 'Assign Customers to Plan" pop-up is
				// showing or
				// not.
				boolean assignCustomersPopUp = isElementExists(Plans_OR.assignCustomersToPlan);
				if (assignCustomersPopUp) {

					// Verify the title of the pop-up
					String popUpTitle = getTextWebelement(Shared_OR.popUpTitle);
					if (popUpTitle.contains("Assign customers to " + planName)) {
						RESULT.PASS("Successfully verified that the name of the pop-up title.", true,
								ScreenshotType.browser);
					} else {
						RESULT.FAIL("Failed to match the name of the pop-up title.", true, ScreenshotType.browser);
					}
					// Check if the 'Save' button is enabled or not
					boolean saveBtn = isElementEnabled(Plans_OR.assignCustomerSaveBtn);

					if (saveBtn) {
						RESULT.FAIL("Failed because 'Save' button is enabled by default.", true,
								ScreenshotType.browser);
					}
					for (int j = 0; j < ids.size(); j++) {
						// Get the list of the customer's name list
						List<WebElement> dCCustomerList = commonFunctions.getList(Plans_OR.selectCustomerSectionList);
						for (int i = 0; i < dCCustomerList.size(); i++) {
							String customerID = dCCustomerList.get(i).getText().trim();
							if (customerID.equals(ids.get(j))) {
								i = i + 1;
								By selectBtn = By.xpath(
										"((//div[contains(@class,'cdk-drop-list')])[1]//div[contains(@class,'example-box')]//button)["
												+ i + "]");

								// click on 'Select' button.
								click(selectBtn);
								i = i - 1;
								break;
							}
						}
					}
					// Verify the customer list showing in "Selected VARs"
					// section.
					List<WebElement> selectedVARList = commonFunctions.getList(Plans_OR.selectedCustomersSectionList);
					for (int m = 0; m < selectedVARList.size(); m++) {
						String varCustomerId = selectedVARList.get(m).getText().trim();
						for (int n = 0; n < ids.size(); n++) {
							if (varCustomerId.equals(ids.get(n))) {
								System.out.println("Reuqired VAR Customer is selected properly.");
								break;
							}
						}
					}
				} else {
					RESULT.FAIL("Failed because 'Assign Customers' pop-up was not opened.", true,
							ScreenshotType.browser);
				}
			}
			// Click on 'Save' button
			commonFunctions.verifyAndClickOnButtonIfEnable(Plans_OR.assignCustomerSaveBtn);
			pause(ERPConstant.VERY_LOW);
		} else {
			RESULT.FAIL("Failed because 'Select Customers' button is not exist.", true, ScreenshotType.browser);
		}
	}

	/*
	 * Method to select multiple VAR customer for plan.
	 */
	public void selectVARCustomerForPlan(String planName, String selectVAR, String varID) {
		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}
		if (Objects.isNull(plans)) {
			plans = createObject("Plans");
		}

		List<String> ids = Arrays.asList(varID.split(","));

		Boolean selectVARCustomerChkBox = commonFunctions.isElementSelected(Plans_OR.selectedVarChkBox);
		By selectVARCustomerChkBoxLocator = getLocator(Plans_OR.checkBoxLocator, "forVars");

		// If the value of 'Select VAR' is 'Yes' or 'Select VAR' check-box is
		// not selected.
		if (!selectVARCustomerChkBox && selectVAR.contains("Yes")) {
			click(selectVARCustomerChkBoxLocator);
			pause(ERPConstant.VERY_LOW);

			// Verify if the 'Select VARs' button is showing or not.
			boolean flag = isElementExists(Plans_OR.selectVARs);
			if (flag) {
				RESULT.PASS("Successfully checked that 'Select Customers' button is showing.", true,
						ScreenshotType.browser);
				click(Plans_OR.selectVARs);
				pause(ERPConstant.VERY_LOW);

				// Verify if the 'Assign Customers to Plan" pop-up is
				// showing or not.
				boolean assignCustomersPopUp = isElementExists(Plans_OR.assignCustomersToPlan);
				if (assignCustomersPopUp) {

					// Verify the title of the pop-up
					String popUpTitle = getTextWebelement(Shared_OR.popUpTitle);
					if (popUpTitle.contains("Assign customers to " + planName)) {
						RESULT.PASS("Successfully verified that the name of the pop-up title.", true,
								ScreenshotType.browser);
					} else {
						RESULT.FAIL("Failed to match the name of the pop-up title.", true, ScreenshotType.browser);
					}

					// Check if the 'Save' button is enabled or not
					boolean saveBtn = isElementEnabled(Plans_OR.assignCustomerSaveBtn);

					if (saveBtn) {
						RESULT.FAIL("Failed because 'Save' button is enabled by default.", true,
								ScreenshotType.browser);
					}
					for (int j = 0; j < ids.size(); j++) {

						// Get the list of the customer's name list
						List<WebElement> varCustomerList = commonFunctions.getList(Plans_OR.selectCustomerSectionList);
						for (int i = 0; i < varCustomerList.size(); i++) {
							String customerID = varCustomerList.get(i).getText().trim();
							if (customerID.equals(ids.get(j))) {
								i = i + 1;
								By selectBtn = By.xpath(
										"((//div[contains(@class,'cdk-drop-list')])[1]//div[contains(@class,'example-box')]//button)["
												+ i + "]");

								// click on 'Select' button.
								click(selectBtn);
								i = i - 1;
								break;
							}
						}
					}

					// Verify the customer list showing in "Selected VARs"
					// section.
					List<WebElement> selectedVARList = commonFunctions.getList(Plans_OR.selectedCustomersSectionList);
					for (int m = 0; m < selectedVARList.size(); m++) {
						String varCustomerId = selectedVARList.get(m).getText().trim();
						for (int n = 0; n < ids.size(); n++) {
							if (varCustomerId.equals(ids.get(n))) {
								System.out.println("Reuqired VAR Customer is selected properly.");
								break;
							}
						}
					}
				} else {
					RESULT.FAIL("Failed because 'Assign Customers' pop-up was not opened.", true,
							ScreenshotType.browser);
				}
			}
			// Click on 'Save' button
			commonFunctions.verifyAndClickOnButtonIfEnable(Plans_OR.assignCustomerSaveBtn);
			pause(ERPConstant.VERY_LOW);
		} else {
			RESULT.FAIL("Failed because 'Select Customers' button is not exist.", true, ScreenshotType.browser);
		}
	}

	/*
	 * Method to verify the plan details after expanding row.
	 */
	public void verifyPlanDetailsAfterExpansion(String productName, String productTag, String planName,
			String displayName, String ribbonName, String setUpPrice, String upfrontCost, String meteredBilling,
			String trailDays, String showPlanafterSubscription, String basePrice, String unit, String pricingModel,
			String feature) {

		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}
		if (Objects.isNull(plans)) {
			plans = createObject("Plans");
		}
		if (Objects.isNull(products)) {
			products = createObject("Products");
		}

		// Split the pricing model by colon
		String[] pricingArray = pricingModel.split(":");
		String pricing = pricingArray[0];

		// Get the Array List of the plan details list
		ArrayList<String> planDetailList = plans.getPlanDetailsAfterExpansion(planName);

		// Get the Array List of the data which is added while creating plan
		ArrayList<String> recordList = plans.getArrayListOfData(planName, displayName, ribbonName, setUpPrice,
				upfrontCost, meteredBilling, trailDays, showPlanafterSubscription, basePrice, unit, pricing, feature);

		// Get the Array List of the plan details field name
		ArrayList<String> planfieldList = plans.getPlanDetailsFieldList(pricing);
		int j = 0;
		for (String field : planfieldList) {
			if (planDetailList.get(j).equals(recordList.get(j))) {
				RESULT.PASS("The data of '" + planfieldList.get(j) + "' field is matched successfully.", true,
						ScreenshotType.browser);

			} else {
				RESULT.FAIL("Failed to match the data of '" + planfieldList.get(j) + "' Field.", true,
						ScreenshotType.browser);
			}
			j++;
		}
	}

	/*
	 * Method to store the data in list which was added while creating new customer.
	 */
	public ArrayList<String> getArrayListOfData(String planName, String displayName, String ribbonName,
			String setUpPrice, String upfrontCost, String meteredBilling, String trailDays,
			String showPlanafterSubscription, String basePrice, String unit, String pricing, String featureValue) {

		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}
		if (Objects.isNull(plans)) {
			plans = createObject("Plans");
		}
		ArrayList<String> dataList = new ArrayList<String>();

		boolean flag = plans.isPlanPublished(planName);
		String isPlanPublished;
		if (flag) {
			isPlanPublished = "Not Published";
		} else {
			isPlanPublished = "Published";
		}
		String meterBillingValue;
		if (meteredBilling.contains("Yes")) {
			meterBillingValue = "On";
		} else {
			meterBillingValue = "Off";
		}
		dataList.add(displayName);
		dataList.add(ribbonName);
		dataList.add(isPlanPublished);
		dataList.add("$" + setUpPrice + ".00");
		dataList.add("$" + upfrontCost + ".00");
		dataList.add(meterBillingValue);
		dataList.add(trailDays);
		dataList.add(showPlanafterSubscription);
		dataList.add("$" + basePrice + ".00");
		dataList.add(unit);
		dataList.add(pricing);
		dataList.add(featureValue);

		return dataList;
	}

	/*
	 * Method to verify the plan details in row.
	 */
	public void verifyPlanDetailsInRow(String productName, String productTag, String planName, String basePrice,
			String setUpPrice, String pricingModel, String unit, String varID, String dcID) {

		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}
		if (Objects.isNull(plans)) {
			plans = createObject("Plans");
		}
		if (Objects.isNull(products)) {
			products = createObject("Products");
		}

		if (!varID.contains("NA") && !dcID.contains("NA")) {

			// Verify assigned direct customers in plan
			plans.verifyAssignedDirectCustomers(planName, dcID);

			// close the overLay Pop-up by again clicking on it
			click(Plans_OR.customerOverLay);
			pause(ERPConstant.VERY_LOW);

			// Verify assigned VAR customer in plan.
			plans.verifyAssignedVARCustoemers(planName, varID);

			// Close the overLay pop-up by again clicking on it
			click(Plans_OR.customerOverLay);
			pause(ERPConstant.VERY_LOW);
		}
		// Create ArrayList for storing plan detail list.
		ArrayList<String> planDetailList = new ArrayList<String>();

		int rowNumber = plans.getPlanRowNumber(planName);
		String number = String.valueOf(rowNumber);
		By planDetails = getLocator(Plans_OR.planRowDetails, number);
		List<WebElement> planDetail = getList(planDetails);

		// Get the row number of the plan
		for (int j = 0; j < planDetail.size(); j++) {
			planDetailList.add(planDetail.get(j).getText());
		}

		String[] PricingArray = pricingModel.split(":");
		String Pricing = PricingArray[0];

		String basePriceWithDollor = "$" + basePrice + ".00";
		String setUpPriceWithDollor = "$" + setUpPrice + ".00";

		// ArrayList of the data from excel sheet.
		ArrayList<String> dataList = new ArrayList<String>();
		dataList.add(planName);
		dataList.add(basePriceWithDollor);
		dataList.add(setUpPriceWithDollor);
		dataList.add(Pricing);
		dataList.add(unit);

		By columnHeadLocator = (Shared_OR.AllcolumnAHead);

		// ArrayList which store the column head
		ArrayList<String> columnHeader = commonFunctions.getColumnHead(Arrays.asList("Actions", "For"),
				columnHeadLocator);

		int j = 0;
		for (String field : columnHeader) {
			if (planDetailList.get(j).equals(dataList.get(j))) {
				RESULT.PASS("The data of '" + columnHeader.get(j) + "' field is matched successfully.", true,
						ScreenshotType.browser);
			} else {
				RESULT.FAIL("Failed to match the data of '" + columnHeader.get(j) + "' Field.", true,
						ScreenshotType.browser);
			}
			j++;
		}
	}

	/*
	 * Method to verify the assigned VAR customers
	 */
	public void verifyAssignedVARCustoemers(String planName, String varId) {
		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}
		if (Objects.isNull(plans)) {
			plans = createObject("Plans");
		}

		List<String> varIds = Arrays.asList(varId.split(","));

		// Go to the plan record and get the data and store into arrayList
		// List<WebElement> planNameColumnData =
		// commonFunctions.getList(Plans_OR.planName);
		// String value_new = "##";
		// for (int i = 0; i <= planNameColumnData.size(); i++) {
		// String labelValue = value_new + planNameColumnData.get(i).getText() +
		// "##";
		// if (labelValue.equals("##" + planName + "##")) {
		// i = i + 1;

		// Get the plan row number
		int rowNumber = plans.getPlanRowNumber(planName);
		String number = String.valueOf(rowNumber);

		// Verify if the 'View Assinged VAR' icon is showing or not
		By viewAssignedVARIcon = getLocator(Plans_OR.viewAssignedVAR, number);
		boolean viewAssignVAR = isElementExists(viewAssignedVARIcon);
		if (viewAssignVAR) {

			// Click on 'View Assigned VAR'
			click(viewAssignedVARIcon);
			pause(ERPConstant.VERY_LOW);

			// Verify if the overlay for VAR is showing or not
			boolean varOverLay = isElementExists(Plans_OR.customerOverLay);
			if (varOverLay) {
				RESULT.PASS("Successfully checked that 'Customer Overlay' pop-up is getting opened.", true,
						ScreenshotType.browser);

				// Check the title of the overlay
				String varOverLayTitle = getTextWebelement(Plans_OR.overlayTitle);

				if (varOverLayTitle.equals(planName)) {
					RESULT.PASS("The title of the 'Customer Overlay' matched successfully.", true,
							ScreenshotType.browser);
					// Get the list of the Customers name showing in
					// 'Overlay'

					for (int m = 0; m < varIds.size(); m++) {
						List<WebElement> overLayVAR = getList(Plans_OR.overlayCustomerList);
						String varCustomerId = overLayVAR.get(m).getText().trim();
						for (int n = 0; n < varIds.size(); n++) {
							if (varCustomerId.contains(varIds.get(n))) {
								RESULT.PASS("Customer ID is matched successfully", true, ScreenshotType.browser);
								break;
							}
						}
					}
					// ************************************PLEASE*************
					// please else statement for the customer id is not
					// matched
					// successfully.******************************************************PLEASE
				} else {
					RESULT.FAIL("Failed to match the title of the 'Customer Overlay'.", true, ScreenshotType.browser);
				}
			} else {
				RESULT.FAIL("Failed becuse ' VAR Customer Overlay' pop-up was not getting opened.", true,
						ScreenshotType.browser);
			}
		} else {
			RESULT.INFO("'View Assinged VARs' icon is not exist in plan record.", true, ScreenshotType.browser);
		}
		// i = i - 1;
		// break;
		// }
		// }
	}

	/*
	 * Method to verify the assigned Direct Customers
	 */
	public void verifyAssignedDirectCustomers(String planName, String dcID) {
		List<String> dcIds = Arrays.asList(dcID.split(","));

		// Go to the plan record and get the data and store into arrayList
		// List<WebElement> planNameColumnData =
		// commonFunctions.getList(Plans_OR.planName);
		// String value_new = "##";
		// for (int i = 0; i <= planNameColumnData.size(); i++) {
		// String labelValue = value_new + planNameColumnData.get(i).getText() +
		// "##";
		// if (labelValue.equals("##" + planName + "##")) {
		// i = i + 1;
		//

		// Get the plan Row Number
		int rowNumber = plans.getPlanRowNumber(planName);
		String number = String.valueOf(rowNumber);

		// Verify if the 'View Assigned customer' icon is showing or
		// not.
		By viewAssignCutomerIcon = getLocator(Plans_OR.viewAssingedDC, number);

		boolean viewAssignCusotmers = isElementExists(viewAssignCutomerIcon);
		if (viewAssignCusotmers) {
			click(viewAssignCutomerIcon);
			pause(ERPConstant.VERY_LOW);

			// Verify if the 'Overlay' for the direct customer is
			// getting opened or not
			boolean dcOverlay = isElementExists(Plans_OR.customerOverLay);
			if (dcOverlay) {
				RESULT.PASS("Successfully checked that 'Direct Customer Overlay' pop-up is getting opened.", true,
						ScreenshotType.browser);
				// Check the title of the overlay
				String dcOverLayTitle = getTextWebelement(Plans_OR.overlayTitle);

				if (dcOverLayTitle.equals(planName)) {
					RESULT.PASS("The title of the 'Customer Overlay' matched successfully.", true,
							ScreenshotType.browser);
					// Get the list of the Customers name showing in
					// 'Overlay'

					for (int m = 0; m < dcIds.size(); m++) {
						List<WebElement> overLayDC = getList(Plans_OR.overlayCustomerList);
						String varCustomerId = overLayDC.get(m).getText().trim();
						for (int n = 0; n < overLayDC.size(); n++) {
							if (varCustomerId.contains(dcIds.get(n))) {
								RESULT.PASS("Customer ID is matched successfully", true, ScreenshotType.browser);
								break;
							}
						}
					}
				} else {
					RESULT.FAIL("Failed becuse ' Direct Customer Overlay' pop-up was not getting opened.", true,
							ScreenshotType.browser);
				}
			} else {

				RESULT.INFO("'View Assinged Customers' icon is not exist in plan record.", true,
						ScreenshotType.browser);
			}
		}
		// i = i - 1;
		// break;
		// }
		// }
	}

	/*
	 * Method to add features
	 */
	public void addFeaturesToPlan(String featureValue) {
		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}
		if (Objects.isNull(plans)) {
			plans = createObject("Plans");
		}

		// Go to 'Other Information' tab
		click(Plans_OR.otherInformationTab);

		// Add feature
		commonFunctions.addFeature(featureValue);

	}

	/*
	 * Method to check if the plan is published or not
	 */
	public boolean isPlanPublished(String planName) {
		boolean flag = false;
		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}

		// Get the plan row number
		int rowNumber = plans.getPlanRowNumber(planName);
		By publishButton = By.xpath(
				"//tr[contains(@class,'clickable')][" + rowNumber + "]//button//span[contains(text(),'Publish')]");

		// Verify if the 'Publish' button is exist or not
		boolean publishBtn = isElementExists(publishButton);
		if (publishBtn) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	/*
	 * Method to return plan row number
	 */
	public int getPlanRowNumber(String planName) {
		List<WebElement> planNameColumnData = commonFunctions.getList(Plans_OR.planName);
		String value_new = "##";
		int number = 0;
		for (int i = 0; i <= planNameColumnData.size(); i++) {
			String labelValue = value_new + planNameColumnData.get(i).getText() + "##";
			if (labelValue.equals("##" + planName + "##")) {
				i = i + 1;
				number = i;
				break;
			}
		}
		return number;
	}

	/*
	 * Method to verify if the functionality of pricing bracket
	 */
	public void verifyPricingBracketSection() {
		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}
		if (Objects.isNull(plans)) {
			plans = createObject("Plans");
		}

		// Enter the data in first field of the 'End Quantity' Field.
		int number = 1;

		String indexFirst = String.valueOf(number);
		By endQtyField = getLocator(Plans_OR.endQtyField, indexFirst);
		setValue(endQtyField, "5");

		// Verify if 'Validation message' regarding last pricing bracket is
		// showing or not
		boolean validationMsg = isElementExists(Plans_OR.lastPricingBracketValidationMessage);
		if (validationMsg) {
			RESULT.PASS("Successfully checked that the validation message is showing.", true, ScreenshotType.browser);
			String validationMessageContent = commonFunctions
					.getTextWebelement(Plans_OR.lastPricingBracketValidationMessage);
			if (validationMessageContent.equals(
					"*Do not add End Quantity in last entry. It will be removed to extend range upto infinite.")) {
				RESULT.PASS("Successfully matched the contend of the validation message. ", true,
						ScreenshotType.browser);
			} else {
				RESULT.FAIL("Failed to match the contentof validation message.", true, ScreenshotType.browser);
			}

		} else {
			RESULT.FAIL("Failed!!...because the validation message is not showing.", true, ScreenshotType.browser);
		}
		// Increase the value of the number by one
		number = number + 1;
		String secondIndex = String.valueOf(number);

		By newPricingBracket = getLocator(Plans_OR.pricingBracketItems, secondIndex);

		// Click on plus button
		click(Plans_OR.addRecordBtn);
		pause(ERPConstant.VERY_LOW);

		// Verify if the 'new pricing bracket line is showing or not.
		boolean newPricingBracketLineItem = isElementExists(newPricingBracket);
		if (newPricingBracketLineItem) {
			RESULT.PASS(
					"Successfully checked that after click on 'plus' button new line item is showing pricing bracket section",
					true, ScreenshotType.browser);
		} else {
			RESULT.FAIL("Failed because new line item is not showing after clicking on 'plus' button.", true,
					ScreenshotType.browser);
		}

		// Verify if the validation message regarding last pricing bracket is
		// showing or not.
		boolean validationMsgAfteClickOnPlusButton = isElementExists(Plans_OR.lastPricingBracketValidationMessage);
		if (!validationMsgAfteClickOnPlusButton) {
			RESULT.PASS("Successfully verified that afer click on 'Plus' button, validaton message is not showing.",
					true, ScreenshotType.browser);
		} else {
			RESULT.FAIL("Failed because validation message is still showing after click on 'Plus' button ", true,
					ScreenshotType.browser);
		}

		// Click on minus button check if the second line item for the pricing
		// bracket is showing or not
		click(Plans_OR.removeRecordBtn);
		pause(ERPConstant.VERY_LOW);

		boolean secondPricingBracketLineItem = isElementExists(newPricingBracket);
		if (!secondPricingBracketLineItem) {
			RESULT.PASS(
					"Successfully checked that after click on 'minus' button second line item is not showing in pricing bracket section",
					true, ScreenshotType.browser);
		} else {
			RESULT.FAIL("Failed because second line item is still showing after clicking on 'minus' button.", true,
					ScreenshotType.browser);
		}

	}

	/*
	 * Method to verify the plan details of volume and tier pricing after expanding
	 * row.
	 */
	public void verifyPlanVolmeORTierORUnitDetailsAfterExpansion(String productName, String productTag, String planName,
			String displayName, String ribbonName, String setUpPrice, String upfrontCost, String meteredBilling,
			String trailDays, String showPlanafterSubscription, String basePrice, String unit, String pricingModel,
			String pricePerUnit, String endQuantity, String freeQuanaity, String featureValue) {

		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}
		if (Objects.isNull(plans)) {
			plans = createObject("Plans");
		}
		if (Objects.isNull(products)) {
			products = createObject("Products");
		}

		// Split the pricing model by colon
		String[] pricingArray = pricingModel.split(":");
		String pricing = pricingArray[0];

		// get the ArrayList of planDetailsList
		ArrayList<String> planDetailList = plans.getPlanDetailsAfterExpansion(planName);
		ArrayList<String> recordList;

		// If the pricing model is 'volume' or 'Tier'
		if (pricing.contains("Volume") || pricing.contains("Tier")) {

			// Get the value of the pricing brackets from given data
			String pricingBrackets = plans.getPricingBracket(pricePerUnit, endQuantity);

			// Get the ArrayList of the data which is added while creating plan
			recordList = plans.getArrayListOfData(planName, displayName, ribbonName, setUpPrice, upfrontCost,
					meteredBilling, trailDays, showPlanafterSubscription, basePrice, unit, pricing, featureValue);

			int totalRecords = recordList.size();

			// Add the pricing bracket to the RecordList.
			recordList.add(totalRecords - 1, pricingBrackets.trim());
		} else {
			recordList = plans.getArrayListOfData(planName, displayName, ribbonName, setUpPrice, upfrontCost,
					meteredBilling, trailDays, showPlanafterSubscription, basePrice, unit, pricing, featureValue);
			int totalRecords = recordList.size();

			// Add the value of 'Free Quantity' and 'Price per unit' in the arraylist.
			String pricePerUnitInDolor = "$" + pricePerUnit + ".00";
			recordList.add(recordList.size() - 1, pricePerUnitInDolor);
			recordList.add(recordList.size() - 2, freeQuanaity);

		}

		// Get the Arraylist of the plan details field name
		ArrayList<String> planfieldList = plans.getPlanDetailsFieldList(pricing);
		int j = 0;
		for (String field : planfieldList) {
			if (planDetailList.get(j).equals(recordList.get(j))) {
				RESULT.PASS("The data of '" + planfieldList.get(j) + "' field is matched successfully.", true,
						ScreenshotType.browser);

			} else {
				RESULT.FAIL("Failed to match the data of '" + planfieldList.get(j) + "' Field.", true,
						ScreenshotType.browser);
			}
			j++;
		}
	}

	/*
	 * Method to get the arrayList of plan details
	 */
	public ArrayList<String> getPlanDetailsAfterExpansion(String planName) {

		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}
		if (Objects.isNull(plans)) {
			plans = createObject("Plans");
		}
		// Initialize the arrayList for storing plan details
		ArrayList<String> planDetailList = new ArrayList<String>();

		By productNameLink = getLocator(Plans_OR.planNameLlink, planName);
		click(productNameLink);

		int rowNumber = plans.getPlanRowNumber(planName);
		String number = String.valueOf(rowNumber);
		By planDetails = getLocator(Plans_OR.planDetails, number);
		List<WebElement> planDetail = getList(planDetails);

		for (int j = 0; j < planDetail.size(); j++) {
			planDetailList.add(planDetail.get(j).getText());
		}
		return planDetailList;

	}

	/*
	 * Method to get the pricing bracket
	 */
	public String getPricingBracket(String pricePerUnit, String endQuantity) {
		if (Objects.isNull(commonFunctions)) {
			commonFunctions = createObject("CommonFunctions");
		}
		if (Objects.isNull(plans)) {
			plans = createObject("Plans");
		}
		List<String> endQuantityList = Arrays.asList(endQuantity.split(","));
		List<String> pricePerUnitList = Arrays.asList(pricePerUnit.split(","));

		ArrayList<String> pricingBracketList = new ArrayList<String>();
		String qtyStartFrom = null;
		String qtyEndTo = null;
		String pricingBracket = "";
		String newPricingBracket = "";

		for (int i = 0; i < pricePerUnitList.size(); i++) {
			if (i == 0) {
				// Set the 'From Quantity' to 1
				qtyStartFrom = "1";

				// Set the 'End Quantity"
				qtyEndTo = " to " + endQuantityList.get(i);
			}
			if (i != 0) {
				// Get the 'qtyFrom' Value of current pricing bracket by using previous 'End
				// quantity' of previous pricing bracket
				i = i - 1;
				String previousQtyValue = endQuantityList.get(i);
				int previousQtyToValueInt = Integer.parseInt(previousQtyValue);
				int currentQtyFromValueInt = previousQtyToValueInt + 1;
				qtyStartFrom = String.valueOf(currentQtyFromValueInt);
				i = i + 1;
				if (i == endQuantityList.size()) {

					qtyEndTo = " & above";
				} else {
					qtyEndTo = " to " + endQuantityList.get(i);
				}
			}
			String pricePerUnitValue = "$" + pricePerUnitList.get(i) + ".00";
			pricingBracket = qtyStartFrom + qtyEndTo + " ➔ " + pricePerUnitValue;
			pricingBracketList.add(i, pricingBracket);
			newPricingBracket = newPricingBracket + pricingBracket + "\n";
			System.out.println(newPricingBracket);
		}

		return newPricingBracket;

	}

	/*
	 * Method to get the arrayList of the planDetails Field
	 */
	public ArrayList<String> getPlanDetailsFieldList(String pricing) {
		// Get the arraylist of the plan details field name
		ArrayList<String> planfieldList = new ArrayList<String>();

		if (pricing.contains("Flat")) {
			planfieldList.add("Display Name");
			planfieldList.add("Ribbon");
			planfieldList.add("Is Plan Published");
			planfieldList.add("Setup Cost");
			planfieldList.add("Upfront Cost");
			planfieldList.add("Metered Billing");
			planfieldList.add("Trail Days");
			planfieldList.add("Show Plan After Subscription");
			planfieldList.add("Base Price");
			planfieldList.add("Unit");
			planfieldList.add("Pricing");
			planfieldList.add("Features");
		}
		if (pricing.contains("Unit")) {
			planfieldList.add("Display Name");
			planfieldList.add("Ribbon");
			planfieldList.add("Is Plan Published");
			planfieldList.add("Setup Cost");
			planfieldList.add("Upfront Cost");
			planfieldList.add("Metered Billing");
			planfieldList.add("Trail Days");
			planfieldList.add("Show Plan After Subscription");
			planfieldList.add("Base Price");
			planfieldList.add("Unit");
			planfieldList.add("Pricing");
			planfieldList.add("Free Quantity");
			planfieldList.add("price Per Unit");
			planfieldList.add("Features");
		}
		if (pricing.contains("Volume") || pricing.contains("Tier")) {
			planfieldList.add("Display Name");
			planfieldList.add("Ribbon");
			planfieldList.add("Is Plan Published");
			planfieldList.add("Setup Cost");
			planfieldList.add("Upfront Cost");
			planfieldList.add("Metered Billing");
			planfieldList.add("Trail Days");
			planfieldList.add("Show Plan After Subscription");
			planfieldList.add("Base Price");
			planfieldList.add("Unit");
			planfieldList.add("Pricing");
			planfieldList.add("price Brackets");
			planfieldList.add("Features");
		}

		return planfieldList;
	}
}
