package pages.login.dashboard.products;

import org.openqa.selenium.By;

public class Products_OR {

	public static By addProductButton = By.xpath("//span[contains(text(),'Add Product')]");

	public static By productName = By.xpath("//input[@name='name']");

	public static By description = By.xpath("//textarea[@name='description']");

	public static By tagField = By.xpath("//input[contains(@id,'mat-chip')]");

	public static By ProductForm = By.xpath("//erp-product-form//div[@class='content-body']");

	// This locator to select the check-box
	public static By cardAchRequiredCheckBox = By
			.xpath("//mat-checkbox[@formcontrolname='cardRequired']//input//parent::div");

	// This locator to verify if the check-box is selected or not
	public static By cardAchCheckbox = By.xpath("//mat-checkbox[@formcontrolname='cardRequired']//input");

	public static By tagFilterButton = By.xpath("//button[contains(text(),'Filter By Tags')]");

	public static By selectTagToFilterSection = By.xpath("(//div[@class='cdk-overlay-pane'])[2]");

	public static By category = By.xpath("(//mat-select[@name='category']//div//span)[2]");

	public static By addedFeatureSection = By.xpath("//span[contains(text(),'Added Features:')]");

	public static By productNameLink = By.xpath("(//td//a)[1]");

	public static By productsNameLink = By.xpath("//a[contains(text(),'%s')]");

	// Following are locators for the product details page.
	public static By productDetailsName = By
			.xpath("//mat-toolbar//span[contains(text(),'Edit')]//ancestor::mat-toolbar/div/div[1]/span[1]");

	public static By productDetailsCategory = By
			.xpath("//mat-toolbar//span[contains(text(),'Edit')]//ancestor::mat-toolbar/div/div[1]/span[2]");

	public static By productDetailsTag = By.xpath("(//mat-icon[contains(text(),'close')]//parent::span)[2]");

	public static By productDetailsDescription = By
			.xpath("//span[contains(text(),'Features')]//parent::div/parent::div/div[1]/span");

	public static By productDetailsFeatures = By
			.xpath("//span[contains(text(),'Features')]//parent::div//following-sibling::div/span");

	public static By productDetailsPage = By.xpath("//erp-product-details/div");

	public static By cardAchMandatoryText = By.xpath("//div[contains(text(),'Credit Card/ACH is mandatory')]");

	public static By productPublishButton = By.xpath("//mat-toolbar//span[contains(text(),'Publish')]");

	public static By productUnpublishButton = By.xpath("//mat-toolbar//span[contains(text(),'Unpublish')]");
}
