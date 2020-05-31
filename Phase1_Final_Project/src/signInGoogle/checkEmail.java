package signInGoogle;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import PageObject.PageObject;

public class checkEmail extends PageObject {

	@FindBy(linkText = "Inbox")
	private WebElement inbox;
	
	@FindBy(xpath = "//div[@class='iH bzn']//div[@class='T-I J-J5-Ji nX T-I-ax7 T-I-Js-Gs mA']//div[@class='asa']")
	private WebElement deleteCurrentEmail;

	
	@FindBy(id = ":2q")
	private WebElement viewEmailDetail;

	@FindBy(xpath = "/html[1]/body[1]/div[7]/div[3]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/table[1]/tr[1]/td[1]/div[2]/div[2]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[3]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[1]/table[1]/tbody[1]/tr[2]/td[1]/p[2]")
	private WebElement onTimePassword;

	public checkEmail(WebDriver driver) {
		super(driver);
	}

	public boolean isInitialized() {
		waitForLoading(driver, inbox);
		return inbox.isDisplayed();
	}

	public String getOneTimePassword() {
		return onTimePassword.getText();
	}

	public void checkEmailInbox() {
		inbox.click();
	}

	public void deleteCurrentEmail() {
		deleteCurrentEmail.click();
	}
	public void viewEmailDetail() {
		viewEmailDetail.click();
	}
}