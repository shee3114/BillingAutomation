package pages.login.dashboard.organization.customFields;

import org.openqa.selenium.By;

public class CustomFields_OR {

	public static By label = By.xpath("//input[@name='label']");

	public static By placeHolder = By.xpath("//input[@name='Placeholder']");

	public static By defaultValue = By.xpath("//input[@name='defaultValue']");

	public static By description = By.xpath("//textarea[@name='description']");

	public static By type = By.xpath("(//mat-select[@name='type']//span)[2]");

	public static By mandatoryChkBox = By.xpath("//span[contains(text(),'Mandatory')]//ancestor::mat-checkbox//input");

	public static By editableInAdminChckBox = By
			.xpath("//span[contains(text(),'Editable in Admin')]//ancestor::mat-checkbox//input");

	public static By editableInCCheckBox = By
			.xpath("//span[contains(text(),'Editable in Client')]//ancestor::mat-checkbox//input");

	public static By chckboxLoctor = By
			.xpath("//span[contains(text(),'%s')]//ancestor::mat-checkbox//input//parent::div");

	public static By customFieldPopUp = By.xpath("//erp-custom-field-form");

	public static By addCustomFieldBtn = By.xpath("//span[contains(text(),'Add Custom Field')]");

	public static By addType = By.xpath("//span[contains(text(),'Add Type')]");

	public static By licenseTab = By.xpath("//div[@role='tab']");

	public static By labelColumn = By.xpath("//table[contains(@class,'basic-table')]//tr//td[1]//span");

}
