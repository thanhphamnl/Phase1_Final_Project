package signInAmazon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import PageObject.PageObject;

public class signInPasswordAmazon extends PageObject {

	@FindBy(id = "ap_password")
	private WebElement password;

	@FindBy(id = "signInSubmit")
	private WebElement signInSubmitButton;

	public signInPasswordAmazon(WebDriver driver) {
		super(driver);
		// assertTrue(email.isDisplayed());
	}

	public boolean isInitialized() {
		return password.isDisplayed();
	}

	public void enterPassword(String sPassword) {
		this.password.clear();
		this.password.sendKeys(sPassword);

	}
	public authorizedHomePage submit(){
		signInSubmitButton.click();
		return new authorizedHomePage(driver);
	}
}