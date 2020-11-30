package pages.login.dashboard.customers;

import org.openqa.selenium.By;

public class Customers_OR {

	public static By CustomerInformationPopup = By.xpath("//erp-customer-form//div[@class='content-body']");

	public static By name = By.xpath("//input[@name='name']");

	public static By displayName = By.xpath("//input[@name='displayName']");

	public static By emailId = By.xpath("//input[@name='emailId']");

	public static By customerTypeDropDown = By.xpath("//mat-select[@name='customerType']");

	public static By domain = By.xpath("//input[@name='domain']");

	public static By webSiteUrl = By.xpath("//input[@name='websiteUrl']");

	public static By description = By.xpath("//textarea[@name='description']");

	public static By maximumUsers = By.xpath("//input[@name='maxAllowedUser']");

	public static By number = By.xpath("//input[@name='number_0']");

	public static By companyName = By.xpath("//input[@name='display']");

	public static By address1 = By.xpath("//input[@name='address1']");

	public static By address2 = By.xpath("//input[@name='address2']");

	public static By stateDropdown = By.xpath("//mat-select[@name='state']");

	public static By cityDropDown = By.xpath("//mat-select[@name='city']");

	public static By zip = By.xpath("//mat-select[@name='zip']");

	public static By advancedFilter = By.xpath("//button[contains(text(),'Advanced Filter')]");

	public static By advancedFilterPopup = By.xpath("//mat-dialog-container");

	public static By customerFilterName = By.xpath("//input[@name='name']");

	public static By threeDotIcon = By.xpath("//mat-icon[contains(text(),'more_vert')]");

	public static By editButton = By.xpath("//button/span[contains(text(),'Edit')]");

	public static By addressSection = By.xpath("(//span[contains(text(),'Address')])[1]");

	public static By customerNameLink = By.xpath("//td//a");

	// Following are the locators for customer details page

	public static By customerDisplayName = By.xpath("(//mat-toolbar-row//span)[1]");

	public static By customerType = By.xpath("(//mat-toolbar-row//span)[3]");

	public static By customerTag = By.xpath("//mat-tab-body//mat-icon[contains(text(),'close')]//parent::div//span");

	public static By customerDescription = By.xpath("//mat-tab-body//p");

	public static By customerURL = By.xpath("//div[@class='c-key-value']//a ");

	public static By customerDetails = By.xpath("//div[@class='c-key-value']//span[2]");
	// public static By crossButton =
	// By.xpath("//button//mat-icon[contains(text(),'close')]");

	// public static By cancelButton =
	// By.xpath("//button//mat-icon[contains(text(),'cancel')]");

}
