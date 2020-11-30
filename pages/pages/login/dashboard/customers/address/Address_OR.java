package pages.login.dashboard.customers.address;

import org.openqa.selenium.By;

import pages.login.dashboard.core.ERPConstant;

public class Address_OR {
	public static By companyName = By.xpath("//input[@name='display']");

	public static By address1 = By.xpath("//input[@name='address1']");

	public static By address2 = By.xpath("//input[@name='address2']");

	public static By stateDropdown = By.xpath("//mat-select[@name='state']");

	public static By cityDropDown = By.xpath("//mat-select[@name='city']");

	public static By zip = By.xpath("//mat-select[@name='zip']");

	// Locators for the 'Address' tab
	public static By addressTab = By.xpath("//div[@aria-label='Address']");

	public static By addressEdit = By.xpath("//mat-card//span[contains(text(),'Edit')]");

	public static By addressPopUp = By.xpath("//mat-dialog-container");

	public static By country = By.xpath("//input[@name='country']");

	public static By countryCode = By.xpath("//input[@name='countryCode']");

	public static By addressCard = By.xpath(
			"//erp-address-list/mat-card/erp-loading//span[contains(text(),'Edit')]//parent::div//parent::div/div[2]");

	public static By addressCardFields = By.xpath(
			"(//erp-address-list/mat-card/erp-loading//span[contains(text(),'Edit')]//parent::div//parent::div/div[2])[%s]/div[%s]");

	public static By addAddressButton = By.xpath("//span[contains(text(),'Add Address')]");

	public static By deleteAddressButton = By.xpath("//erp-confirmation-button/span[contains(text(),'Delete')]");

	public static By addressCardLocator = By.xpath("//mat-card//div[@fxlayout='row wrap']/div");

	public static By companyNameAddressCard = By
			.xpath("//mat-card//div[@fxlayout='row wrap']/div[%s]//div[contains(text(),'%s')]");

	public static By deleteCofirmationDialogContent = By
			.xpath("//erp-confirm-dialog//span[@class='erp-dialog-message']");

}
