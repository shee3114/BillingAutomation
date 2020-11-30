package pages.login.dashboard.customers.contactPerson;

import org.openqa.selenium.By;

public class ContactPerson_OR {
	// Locators for the 'Contact Person' tab
	public static By contactTab = By.xpath("//div[@aria-label='Contacts']");

	public static By addContactButton = By.xpath("//span[contains(text(),'Add Contact')]");

	public static By contactPopUp = By.xpath("//mat-dialog-container");

	public static By contactFirstName = By.xpath("//input[@name='firstName']");

	public static By contactMiddleName = By.xpath("//input[@name='middleName']");

	public static By contactLastName = By.xpath("//input[@name='lastName']");

	public static By contactEmailId = By.xpath("//input[@name='email']");

	public static By contactDesignation = By.xpath("//input[@name='designation']");

	public static By contactsNumber = By.xpath("//input[@name='number_0']");

	public static By contactFilter = By.xpath("//input[@placeholder='Filter by Email ID']");

	public static By contactTable = By.xpath("//table//thead[@role='rowgroup']");

	public static By enablePortalBtn = By.xpath("//button/span[contains(text(),'Enable Portal')]");

	public static By disablePortalBtn = By.xpath("//button/span[contains(text(),'Disable Portal')]");

	public static By cancelButton = By.xpath("//button/span[contains(text(),'Cancel')]");

	public static By contactEditBtn = By.xpath("//mat-icon[contains(text(),'edit')]");

	public static By saveButton = By.xpath("//span[contains(text(),'Save')]//parent::button");

	public static By closeButton = By.xpath("//mat-dialog-container//button//mat-icon[contains(text(),'close')]");

}
