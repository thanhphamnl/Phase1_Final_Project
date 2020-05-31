package signInAmazon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import PageObject.PageObject;
import sqlGuider.sqlGuider;

public class yourAddress extends PageObject {

	private static sqlGuider sqlDriver;
	@FindBy(id = "address-ui-widgets-enterAddressPhoneNumber")
	private WebElement phoneNumber;

	@FindBy(xpath = "//input[@class='a-button-input']")
	private WebElement addAddressButton;

	@FindBy(id = "ya-myab-plus-address-icon")
	private WebElement addAddress;

	@FindBy(id = "address-ui-widgets-enterAddressFullName")
	private WebElement fullName;

	@FindBy(id = "address-ui-widgets-enterAddressLine1")
	private WebElement addressLine;

	@FindBy(id = "address-ui-widgets-enterAddressCity")
	private WebElement city;

	@FindBy(id = "address-ui-widgets-enterAddressStateOrRegion")
	private WebElement state;

	@FindBy(id = "address-ui-widgets-enterAddressPostalCode")
	private WebElement zipCode;

	public yourAddress(WebDriver driver) {
		super(driver);
		sqlDriver = new sqlGuider();
	}

	public boolean isInitialized() {
		return addAddress.isDisplayed();
	}

	public void updateAddress() {
		String[] userProfile = sqlDriver.getUserProfile(sGetAmazonUserProfile);
		enterAddress(userProfile[0], userProfile[1], userProfile[2], userProfile[3], userProfile[4], userProfile[5]);
	}

	public void enterAddress(String sFullName, String sAddressLine, String sPhoneNumber, String sCity, String sState,
			String sZipCode) {
		this.fullName.clear();
		this.fullName.sendKeys(sFullName);
		System.out.println("Full Name: " + sFullName);

		sleep(2000);
		this.addressLine.clear();
		this.addressLine.sendKeys(sAddressLine);
		System.out.println("Address: " + sAddressLine);

		sleep(2000);

		this.city.clear();
		this.city.sendKeys(sCity);
		System.out.println("City: " + sCity);

		sleep(2000);

		this.state.clear();
		this.state.sendKeys(sState);
		System.out.println("State: " + sState);

		sleep(2000);

		this.zipCode.clear();
		this.zipCode.sendKeys(sZipCode);
		System.out.println("Zipcode: " + sZipCode);

		sleep(2000);

		this.phoneNumber.clear();
		this.phoneNumber.sendKeys(sPhoneNumber);
		System.out.println("Phone: " + sZipCode);
	}

	public void addAdressSubmit() {
		addAddressButton.click();
		System.out.println("Add Address Button clicked");
	}

	public void clickAddAdress() {
		addAddress.click();
	}

}
