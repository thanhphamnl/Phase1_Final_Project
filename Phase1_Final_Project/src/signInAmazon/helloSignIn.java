package signInAmazon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import PageObject.PageObject;

public class helloSignIn extends PageObject {

	@FindBy(id = "nav-link-accountList")
	private WebElement linkAccount;

	@FindBy(linkText = "Sign in")
	private WebElement signIn;

	public helloSignIn(WebDriver driver) {
		super(driver);
	}

	public boolean isInitialized() {
		return signIn.isDisplayed();
	}

	public signInEmailAmazon submit() {
		linkAccount.click();
		return new signInEmailAmazon(driver);
	}
}
