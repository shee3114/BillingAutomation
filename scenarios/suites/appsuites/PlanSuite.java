package suites.appsuites;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.login.dashboard.Dashboard;
import pages.login.dashboard.core.ERPConstant;
import pages.login.dashboard.products.Products;
import pages.login.dashboard.products.plans.Plans;
import suites.basesuite.ERPBaseSuite;

public class PlanSuite extends ERPBaseSuite {

	Dashboard dashboard;
	Plans plans;
	Products products;

	@BeforeTest
	public void navigateToDashBoard() {
		dashboard = createObject("Dashboard");
		System.out.println("Before Test is exceucted successfully.");
	}

	@BeforeMethod
	public void navigateToProductsModule() {
		products = dashboard.navigateToModule(ERPConstant.PRODUCT_Mod);
	}

	/*
	 * Method to add plan
	 */
	@Test(priority = 0, dataProvider = "multipleInput", enabled = false)
	public void addFlatPlanAndVerifyRecords(String productName, String productTag, String planName, String ribbonName,
			String displayName, String description, String planTag, String basePrice, String setUpPrice,
			String upfrontCost, String applicableStatus, String meteredBilling, String licenseType, String pricingModel,
			String unit, String billEvery, String trailDay, String billingCycle, String featureValue, String varID,
			String dcID, String showPlanafterSubscription) {
		plans = createObject("Plans");
		plans.addFlatPlan(productName, productTag, planName, ribbonName, displayName, description, planTag, basePrice,
				setUpPrice, upfrontCost, applicableStatus, meteredBilling, licenseType, pricingModel, unit, billEvery,
				trailDay, billingCycle, featureValue);
		plans.verifyPlanDetailsInRow(productName, productTag, planName, basePrice, setUpPrice, pricingModel, unit,
				varID, dcID);
		plans.verifyPlanDetailsAfterExpansion(productName, productTag, planName, displayName, ribbonName, setUpPrice,
				upfrontCost, meteredBilling, trailDay, showPlanafterSubscription, basePrice, unit, pricingModel,
				featureValue);
	}

	/*
	 * Method to edit plan details
	 */
	@Test(priority = 1, dataProvider = "multipleInput", enabled = true)
	public void editAndVerifyPlanRecord(String productName, String productTag, String planName, String ribbonName,
			String displayName, String description, String planTag, String basePrice, String setUpPrice,
			String upfrontCost, String applicableStatus, String meteredBilling, String licenseType, String pricingModel,
			String unit, String billEvery, String trailDay, String billingCycle, String selectVAR, String selectDC,
			String varID, String dcID) {
		plans = createObject("Plans");
		// Edit the details in form also select the direct customer and VAR customer for
		// the plan.
		plans.editPlan(productName, productTag, planName, ribbonName, displayName, description, planTag, basePrice,
				setUpPrice, upfrontCost, applicableStatus, meteredBilling, licenseType, pricingModel, unit, billEvery,
				trailDay, billingCycle, selectVAR, selectDC, varID, dcID);
		plans.verifyPlanDetailsInRow(productName, productTag, planName, basePrice, setUpPrice, pricingModel, unit,
				varID, dcID);
	}

	/*
	 * Method to publish the plan
	 */
	@Test(priority = 2, dataProvider = "multipleInput", enabled = true)
	public void publishPlans(String productName, String productTag, String planName) {
		plans = createObject("Plans");
		plans.publishPlans(productName, productTag, planName);
	}

	/*
	 * Method to publishing the plan
	 */
	@Test(priority = 3, dataProvider = "multipleInput", enabled = true)
	public void unpublishPlans(String productName, String productTag, String planName) {
		plans = createObject("Plans");
		plans.unpublishPlans(productName, productTag, planName);
	}

	/*
	 * Method to create the plan for the selected Customers.
	 */
	/*
	 * @Test(priority = 4, dataProvider = "multipleInput", enabled = false) public
	 * void createPlanWithSelectedCustomers(String productName, String productTag,
	 * String planName, String selectVAR, String selectDC, String varID, String
	 * dcID) { plans = createObject("Plans");
	 * plans.createPlanWithSelectedCustomers(productName, productTag, planName,
	 * selectVAR, selectDC, varID, dcID); }
	 */
	/*
	 * Method to create plan with unit pricing model
	 */
	@Test(priority = 7, dataProvider = "multipleInput", enabled = true)
	public void createAndVerifyUnitBasedPlan(String productName, String productTag, String planName, String ribbonName,
			String displayName, String description, String planTag, String basePrice, String setUpPrice,
			String upfrontCost, String applicableStatus, String meteredBilling, String licenseType, String pricingModel,
			String unit, String pricePerUnit, String freeQuantity, String billEvery, String trailDay,
			String billingCycle, String featureValue, String varID, String dcID, String showPlanafterSubscription,
			String endQuantity) {
		plans = createObject("Plans");
		plans.createPlanWithUnitPricingModel(productName, productTag, planName, ribbonName, displayName, description,
				planTag, basePrice, setUpPrice, upfrontCost, applicableStatus, meteredBilling, licenseType,
				pricingModel, unit, pricePerUnit, freeQuantity, billEvery, trailDay, billingCycle, featureValue);
		plans.verifyPlanDetailsInRow(productName, productTag, planName, basePrice, setUpPrice, pricingModel, unit,
				varID, dcID);
		plans.verifyPlanVolmeORTierORUnitDetailsAfterExpansion(productName, productTag, planName, displayName,
				ribbonName, setUpPrice, upfrontCost, meteredBilling, trailDay, showPlanafterSubscription, basePrice,
				unit, pricingModel, pricePerUnit, endQuantity, freeQuantity, featureValue);
	}

	/*
	 * Method to create and verify plan with volume Based pricing model
	 */
	@Test(priority = 8, dataProvider = "multipleInput", enabled = true)
	public void createAndVerifyVolumeBasedPlan(String productName, String productTag, String planName,
			String ribbonName, String displayName, String description, String planTag, String basePrice,
			String setUpPrice, String upfrontCost, String applicableStatus, String meteredBilling, String licenseType,
			String pricingModel, String unit, String pricePerUnit, String endQuantity, String billEvery,
			String trailDay, String billingCycle, String featureValue, String varID, String dcID,
			String showPlanafterSubscription, String freeQuanaity) {
		plans = createObject("Plans");
		plans.createPlanWithVolumePricingModel(productName, productTag, planName, ribbonName, displayName, description,
				planTag, basePrice, setUpPrice, upfrontCost, applicableStatus, meteredBilling, licenseType,
				pricingModel, unit, pricePerUnit, endQuantity, billEvery, trailDay, billingCycle, featureValue);
		plans.verifyPlanDetailsInRow(productName, productTag, planName, basePrice, setUpPrice, pricingModel, unit,
				varID, dcID);
		plans.verifyPlanVolmeORTierORUnitDetailsAfterExpansion(productName, productTag, planName, displayName,
				ribbonName, setUpPrice, upfrontCost, meteredBilling, trailDay, showPlanafterSubscription, basePrice,
				unit, pricingModel, pricePerUnit, endQuantity, freeQuanaity, featureValue);
	}

	/*
	 * Method to create and verify plan with volume Based pricing model
	 */
	@Test(priority = 9, dataProvider = "multipleInput", enabled = true)
	public void createAndVerifyTierBasedPlan(String productName, String productTag, String planName, String ribbonName,
			String displayName, String description, String planTag, String basePrice, String setUpPrice,
			String upfrontCost, String applicableStatus, String meteredBilling, String licenseType, String pricingModel,
			String unit, String pricePerUnit, String endQuantity, String billEvery, String trailDay,
			String billingCycle, String featureValue, String varID, String dcID, String showPlanafterSubscription,
			String freeQuanaity) {
		plans = createObject("Plans");
		plans.createPlanWithTierPricingModel(productName, productTag, planName, ribbonName, displayName, description,
				planTag, basePrice, setUpPrice, upfrontCost, applicableStatus, meteredBilling, licenseType,
				pricingModel, unit, pricePerUnit, endQuantity, billEvery, trailDay, billingCycle, featureValue);
		plans.verifyPlanDetailsInRow(productName, productTag, planName, basePrice, setUpPrice, pricingModel, unit,
				varID, dcID);
		plans.verifyPlanVolmeORTierORUnitDetailsAfterExpansion(productName, productTag, planName, displayName,
				ribbonName, setUpPrice, upfrontCost, meteredBilling, trailDay, showPlanafterSubscription, basePrice,
				unit, pricingModel, pricePerUnit, endQuantity, freeQuanaity, featureValue);
	}

	/*
	 * Method to delete the plan
	 */
	@Test(priority = 8, dataProvider = "multipleInput", enabled = false)
	public void deletePlan(String productName, String productTag, String planName) {
		plans = createObject("Plans");
		plans.deletePlan(productName, productTag, planName);
	}

	@AfterMethod
	public void navigateToDashboard() {
		products = createObject("Products");
		products.removeProductFilter();
	}

	@AfterTest
	public void navigateDashboard() {
		// dashboard.navigateToModule("Dashboard");
		System.out.println("AfterTest is executed successfully.");
	}
}
