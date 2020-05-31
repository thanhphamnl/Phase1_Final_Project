package signInAmazon;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import PageObject.PageObject;

public class yourProfile extends PageObject {

	public yourProfile(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//div[@class='a-row image-edit-popover-trigger-holder']//img")

	private WebElement avatarImage;

	// id:a-popover-content-3
	@FindBy(xpath = "//div[@id='a-popover-content-3']//span[@class='a-size-small a-color-base upload-photo'][contains(text(),'Upload')]")
	private WebElement uploadButton;

	@FindBy(id = "avatar-image")
	private WebElement yourAmazonProfile;

	@FindBy(linkText = "Your Account")
	private WebElement yourAccount;

	public boolean isInitialized() {
		return avatarImage.isDisplayed();
	}

	public void uploadImage() throws IOException, InterruptedException {
		avatarImage.click();
		System.out.println("avatarImage clicked");
		Thread.sleep(2000);
		// Taka a screenshot for the current profile page.
		takeScreenshot("YourProfilePageBeforeUpdate");

		uploadButton.click();
		System.out.println("uploadButton clicked");
		sleep(3000);

		Runtime.getRuntime().exec("scripts/Uploadfile.exe");
		System.out.println("Exec uploadfile.exe");
		sleep(10000);
		// Taka a screenshot for the current profile page.
		takeScreenshot("YourProfilePageAfterUpdate");
	}

	public yourAccount submitProfile() {
		yourAccount.click();
		return new yourAccount(driver);
	}

}