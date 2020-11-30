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
import pages.login.dashboard.products.Products;
import suites.basesuite.ERPBaseSuite;

public class ProductSuite extends ERPBaseSuite {

	CommonFunctions commonFunctions;

	Dashboard dashboard;

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
	 * Method to add new product
	 */
	@Test(priority = 0, dataProvider = "multipleInput", enabled = false)
	public void addProduct(String name, String category, String description, String feature, String cardAchRequired,
			String tag) {
		products = createObject("Products");
		products.addProduct(name, category, description, feature, cardAchRequired, tag);
		products.verifyProductDetails(name, category, description, feature, cardAchRequired, tag);
	}

	/*
	 * Method to edit the product details
	 */
	@Test(priority = 1, dataProvider = "multipleInput", enabled = false)
	public void editProduct(String name, String category, String description, String feature, String cardAchRequired,
			String tag, String tagToEdit) {
		products = createObject("Products");
		products.editProduct(name, category, description, feature, cardAchRequired, tag, tagToEdit);
	}

	/*
	 * Method to verify product Details
	 */
	@Test(priority = 2, dataProvider = "multipleInput", enabled = false)
	public void verifyProductDetails(String name, String category, String description, String cardAchRequired,
			String tag) {
		products = createObject("Products");
		products.verifyProductDetailsInProductGrid(name, category, description, cardAchRequired, tag);
	}

	/*
	 * Method to publish product
	 */
	@Test(priority = 3, dataProvider = "multipleInput", enabled = true)
	public void publishProduct(String productName, String productTag) {
		products = createObject("Products");
		products.publishProduct(productName, productTag);
	}

	/*
	 * Method to unpublish product
	 */
	@Test(priority = 4, dataProvider = "multipleInput", enabled = true)
	public void unpublishProduct(String productName, String productTag) {
		products = createObject("Products");
		products.unpublishProduct(productName, productTag);
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
