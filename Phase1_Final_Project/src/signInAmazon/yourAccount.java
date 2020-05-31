package signInAmazon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import PageObject.PageObject;

public class yourAccount extends PageObject {

	@FindBy(linkText = "Your Amazon profile")
	private WebElement yourAmazonProfile;

	@FindBy(linkText = "Your Account")
	private WebElement yourAccountLink;

	@FindBy(linkText = "Your addresses")
	private WebElement yourAddress;

	public yourAccount(WebDriver driver) {
		super(driver);
	}

	public void yourAccountLink() {
		yourAccountLink.click();
	}

	public boolean isInitialized() {
		return yourAmazonProfile.isDisplayed();
	}

	public yourProfile clickLinkProfile() {
		yourAmazonProfile.click();
		return new yourProfile(driver);
	}

	public yourAddress clickLinkAddress() {
		yourAddress.click();
		return new yourAddress(driver);
	}
}
