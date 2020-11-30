package pages.login.dashboard;

import base.BaseComponent;
import framework.reporter.ScreenshotType;
import pages.CommonFunctions;
import pages.Shared_OR;
import pages.login.dashboard.core.ERPConstant;
import pages.login.dashboard.core.SibConstant;

public class Dashboard extends BaseComponent {

	CommonFunctions commonFunctions = new CommonFunctions();

	/**
	 * @Objective: Method to navigate page
	 * 
	 * @author sheetald
	 * 
	 * @param moduleName-
	 *            moduleName to verify the title of the page
	 */
	public <Type> Type navigateToModule(String moduleName) {

		// Wait for page to load
		commonFunctions.pause(8000);
		switch (moduleName) {
		case ERPConstant.CUSTOMER_Mod:
			commonFunctions.click(Dashboard_OR.customerMenu);
			pause(ERPConstant.MODERATE);
			verifyModule(ERPConstant.CUSTOMER_Mod);
			return createObject(ERPConstant.CUSTOMER_Mod);
		case ERPConstant.PRODUCT_Mod:
			commonFunctions.click(Dashboard_OR.productMenu);
			pause(ERPConstant.MODERATE);
			verifyModule(ERPConstant.PRODUCT_Mod);
			return createObject(ERPConstant.PRODUCT_Mod);
		case ERPConstant.INVOICE_Mod:
			commonFunctions.click(Dashboard_OR.invoiceMenu);
			pause(ERPConstant.MODERATE);
			verifyModule(ERPConstant.INVOICE_Mod);
			return createObject(ERPConstant.INVOICE_Mod);
		case ERPConstant.TRANSACTIONS_Mod:
			commonFunctions.click(Dashboard_OR.transactionMenu);
			pause(ERPConstant.MODERATE);
			verifyModule(ERPConstant.TRANSACTIONS_Mod);
			return createObject(ERPConstant.TRANSACTIONS_Mod);
		case ERPConstant.SUBSCRIPTION_Mod:
			commonFunctions.click(Dashboard_OR.subscriptionMenu);
			pause(ERPConstant.MODERATE);
			verifyModule(ERPConstant.SUBSCRIPTION_Mod);
			return createObject(SibConstant.PLANS);
		case ERPConstant.CUSTOM_FIELD:
			// Click on Setting menu
			commonFunctions.click(Shared_OR.settingMenu);
			pause(ERPConstant.MODERATE);
			commonFunctions.click(Shared_OR.organizationProfileMenu);
			pause(ERPConstant.MODERATE);
			commonFunctions.click(Shared_OR.customFields);
			pause(ERPConstant.MODERATE);
			return createObject(ERPConstant.CUSTOM_FIELD);
		case SibConstant.CONTRACT_Mod:
			// commonFunctions.click(Dashboard_OR.contractsMenu);
			commonFunctions.pause(5000);
			// commonFunctions.click(Dashboard_OR.contractListMenu);
			commonFunctions.pause(5000);
			verifyModule(SibConstant.CONTRACT_Mod);
			return createObject(SibConstant.CONTRACTS);
		case SibConstant.OPERATION_GRID:
			// commonFunctions.click(Dashboard_OR.operationMenu);
			commonFunctions.pause(3000);
			// commonFunctions.click(Dashboard_OR.hoardingsGridMenu);
			commonFunctions.pause(5000);
			// verifyModule(SibConstant.OPERATION_GRID_Mod);
			verifyPageTitle("Operations Grid");
			return createObject(SibConstant.OPERATION_GRID);
		case SibConstant.OPERATION_KANBAN_Mod:
			// commonFunctions.click(Dashboard_OR.operationMenu);
			commonFunctions.pause(3000);
			// commonFunctions.click(Dashboard_OR.hoardingsOperationsMenu);
			commonFunctions.pause(5000);
			// verifyModule(SibConstant.OPERATION_KANBAN_Mod);
			verifyPageTitle("Operations Grid");
			return createObject(SibConstant.OPERATION_KANBAN);
		case SibConstant.CONTRACT_TYPE_Mod:
			// commonFunctions.click(Dashboard_OR.mastersMenu);
			commonFunctions.pause(3000);
			// commonFunctions.click(Dashboard_OR.contractTypeMenu);
			commonFunctions.pause(5000);
			verifyModule(SibConstant.CONTRACT_TYPE_Mod);
			return createObject(SibConstant.CONTRACT_TYPE);
		case SibConstant.DASHBOARD:
			// commonFunctions.click(Dashboard_OR.dashBoard);
			commonFunctions.pause(5000);
			// verifyModule(SibConstant.DASHBOARD);
			verifyPageTitle("ShowItBig");
			return createObject(SibConstant.DASHBOARD);
		}
		return null;
	}

	/**
	 * @Objective: Method to verify page
	 * 
	 * @author sheetald
	 * @param moduleName-
	 *            moduleName to verify the title of the page
	 */
	public boolean verifyModule(String moduleName) {

		// String pageTitle = driver.getTitle();
		// String pageTitle = getTex(Shared_OR.pageTitle);
		// String pageTitle = driver.getTitle();
		// String module = commonFunctions.getModuleName();

		String pageTitle = getTextWebelement(Shared_OR.pageTitle);
		if (pageTitle.toLowerCase().contains(moduleName.toLowerCase())) {
			RESULT.PASS("'" + moduleName + "'" + " Module is opened successfully.", true, ScreenshotType.browser);
		} else {
			RESULT.FAIL("Failed to open the " + "'" + moduleName + "'" + " Module.", true, ScreenshotType.browser);
		}
		return false;
	}
}
