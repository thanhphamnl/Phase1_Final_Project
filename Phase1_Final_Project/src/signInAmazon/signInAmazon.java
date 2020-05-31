package signInAmazon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import PageObject.PageObject;

public class signInAmazon extends PageObject {

	@FindBy(id = "ap_email")
	private WebElement email;

	@FindBy(id = "ap_password")
	private WebElement password;

	@FindBy(id = "continue")
	private WebElement continueButton;

	public signInAmazon(WebDriver driver) {
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

	public void enterPassword(String sPassword) {
		this.password.clear();
		this.password.sendKeys(sPassword);
	}

	public signInAmazon submit() {
		continueButton.click();
		return new signInAmazon(driver);
	}
}
