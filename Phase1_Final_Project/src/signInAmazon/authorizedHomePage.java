package signInAmazon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import PageObject.PageObject;

public class authorizedHomePage extends PageObject {
	@FindBy(linkText = "Your Account")
	private WebElement yourAccount;

	@FindBy(id = "nav-item-signout")
	private WebElement signOut;

	public authorizedHomePage(WebDriver driver) {
		super(driver);
		// assertTrue(firstName.isDisplayed());
	}

	public boolean isInitialized() {
		return signOut.isDisplayed();
	}

	public yourAccount submit() {
		yourAccount.click();
		return new yourAccount(driver);
	}
}
