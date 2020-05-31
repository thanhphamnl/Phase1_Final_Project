package signInGoogle;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import PageObject.PageObject;

public class signInPasswordGoogle extends PageObject {

	@FindBy(id = "password")
	private WebElement password;
	
	@FindBy(xpath = "//input[@name='password']")
	private WebElement inputPassword;
	

	@FindBy(id = "passwordNext")
	private WebElement passwordNextButton;

	public signInPasswordGoogle(WebDriver driver) {
		super(driver);
	}

	public boolean isInitialized() {
		System.out.println("isInitialized");
		return passwordNextButton.isDisplayed();
	}

	public void enterPassword(String sPassword) {
		waitForLoading(driver, inputPassword);
		this.inputPassword.clear();
		this.inputPassword.sendKeys(sPassword);
	}

	public checkEmail Submit() {
		passwordNextButton.click();
		return new checkEmail(driver);
	}
}
