package signInAmazon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import PageObject.PageObject;

public class signInEmailAmazon extends PageObject {

	@FindBy(id = "ap_email")
	private WebElement email;

	@FindBy(id = "continue")
	private WebElement continueButton;

	public signInEmailAmazon(WebDriver driver) {
		super(driver);
		// assertTrue(email.isDisplayed());
	}

	public boolean isInitialized() {
		return email.isDisplayed();
	}

	public void enterEmail(String sEmail) {
		this.email.clear();
		this.email.sendKeys(sEmail);

	}

	public signInPasswordAmazon submit() {
		continueButton.click();
		return new signInPasswordAmazon(driver);
	}
}