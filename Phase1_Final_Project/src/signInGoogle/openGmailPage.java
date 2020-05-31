package signInGoogle;

import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import PageObject.PageObject;

public class openGmailPage extends PageObject {

	@FindBy(linkText = "sign in")
	private WebElement signIn;

//	    	@FindBy(id="continue")
//	    	private WebElement continueButton;	

	public openGmailPage(WebDriver driver) {
		super(driver);
	}

	public boolean isInitialized() {
		return signIn.isDisplayed();
	}

	public void createNewTab(String sUrl) {
		// driver.get("https://www.amazon.com");
		((JavascriptExecutor) driver).executeScript("window.open()");

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get(sUrl);
	}

	public void switchToOldTab(int iTabIndex) {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(iTabIndex));
	}

	public signInEmailGoogle submit() {
		signIn.click();
		return new signInEmailGoogle(driver);
	}
}
