package signInGoogle;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import PageObject.PageObject;

public class signInEmailGoogle extends PageObject {

	@FindBy(id = "identifierId")
	private WebElement emailOrPhone;

	@FindBy(id = "identifierNext")
	private WebElement nextButton;

	public signInEmailGoogle(WebDriver driver) {
		super(driver);
	}

	public boolean isInitialized() {
		return emailOrPhone.isDisplayed();
	}

	public void enterEmail(String sEmail) {
		this.emailOrPhone.clear();
		this.emailOrPhone.sendKeys(sEmail);
	}

	public signInPasswordGoogle Submit() {
		nextButton.click();
		return new signInPasswordGoogle(driver);
	}

}
