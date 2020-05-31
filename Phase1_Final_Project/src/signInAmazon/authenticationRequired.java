package signInAmazon;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import signInGoogle.checkEmail;
import signInGoogle.openGmailPage;
import signInGoogle.signInEmailGoogle;
import signInGoogle.signInPasswordGoogle;
import sqlGuider.sqlGuider;
import PageObject.PageObject;

public class authenticationRequired extends PageObject {
	private static sqlGuider sqlDriver;

	@FindBy(id = "continue")
	private WebElement continueEmailSubmit;

	@FindBy(id = "ap_email")
	private WebElement email;

	@FindBy(id = "ap_password")
	private WebElement password;

	@FindBy(id = "signInSubmit")
	private WebElement signInPasswordSubmit;

	@FindBy(xpath = "//span[@id='a-autoid-0']//input[@class='a-button-input']")
	private WebElement continueButton2;

	@FindBy(name = "code")
	private WebElement codeNumber;

	public authenticationRequired(WebDriver driver) {
		super(driver);
		sqlDriver = new sqlGuider();
	}

	// -------------------------------------------------------------
	public boolean isEmailInitialized() {
		return email.isDisplayed();
	}

	public boolean isCodeNumberRequired() {
		return codeNumber.isDisplayed();
	}

	public boolean isPasswordInitialized() {
		return password.isDisplayed();
	}

	// -------------------------------------------------------------
	public void enterEmailAmazon(String sEmail) {
		this.email.clear();
		this.email.sendKeys(sEmail);
		continueEmailSubmit.click();
	}

	public void enterEmailAmazon() {
		this.email.clear();
		this.email.sendKeys(sqlDriver.getSingleColumn(sGetEmailAmazon));
		sleep(2000);
		takeScreenshot("EnteredEmailAmazon");
	}

	// -------------------------------------------------------------
	public void enterPassword(String sPassword) {
		this.password.clear();
		this.password.sendKeys(sPassword);

	}

	public void enterPasswordAmazon() {
		this.password.clear();
		this.password.sendKeys(sqlDriver.getSingleColumn(sGetPasswordAmazon));
		sleep(2000);
		takeScreenshot("EnteredPasswordAmazon");
	}

	// -------------------------------------------------------------
	public void enterCodeNumber() {
		this.codeNumber.clear();
		String sCode = sGetAmazonCodeFromGmail();
		if (sCode.isEmpty()) {
			System.out.println("Could not get code number from gmail.");
		} else {
			this.codeNumber.sendKeys(sGetAmazonCodeFromGmail());
		}
		sleep(2000);
		takeScreenshot("CodeNumberRequired");
	}

	public void enterCodeNumber(String sCodeNumber) {
		this.codeNumber.clear();
		this.codeNumber.sendKeys(sCodeNumber);
		signInPasswordSubmit.click();
	}

	// -------------------------------------------------------------
	public String sGetAmazonCodeFromGmail() {
		String sCodeNumber = "";

		// Open gmail to get a verifying code number
		openGmailPage newPage = new openGmailPage(driver);
		newPage.createNewTab("http://mail.google.com");
		System.out.println("New Tab for Gmail is generated and gmail is loading");
		sleep(2000);
		takeScreenshot("NewTabGmail");

//		assertTrue(newPage.isInitialized());
//		System.out.println("1");
//		Thread.sleep(5000);
//		signInEmailGoogle signInEmail1 = newPage.submit();
//		assertTrue(signInEmail1.isInitialized());
//		Thread.sleep(5000);

		// Initial java class signInEmailGoogle
		signInEmailGoogle signInEmail1 = new signInEmailGoogle(driver);

		// Check gmail sign page.
		assertTrue(signInEmail1.isInitialized());
		System.out.println("Gmail is launched.");
		sleep(3000);
		takeScreenshot("LaunchedGmail");

		// Get email from database and send to gmail page to be verified.
		signInEmail1.enterEmail(sqlDriver.getSingleColumn(sGetEmailGoogle));
		System.out.println("Entered email for Gmail.");
		sleep(3000);
		takeScreenshot("EnteredEmailGmail");

		// Submit email to be verified.
		signInPasswordGoogle signInPassword1 = signInEmail1.Submit();
		System.out.println("Entered Email is submitted.");

		// assertTrue(signInPassword1.isInitialized());

		// Get password from database and send to gmail page to be verified.
		signInPassword1.enterPassword(sqlDriver.getSingleColumn(sGetPasswordGoogle));
		System.out.println("Entered password for gmail.");

		sleep(3000);
		takeScreenshot("EnteredPasswordGmail");

		// Submit gmail password.
		checkEmail chEmail = signInPassword1.Submit();
		System.out.println("Gmail password is submitted.");

		// Check if gmail is signed up sucessfully.
		assertTrue(chEmail.isInitialized());
		System.out.println("Gmail is dislayed");

		sleep(3000);
		takeScreenshot("SignedInGmail");

		// Check email in box
		chEmail.checkEmailInbox();
		System.out.println("Checking email in box");

		sleep(3000);
		takeScreenshot("AmazonCodeReceved");

		// View email in detail.
		chEmail.viewEmailDetail();
		System.out.println("Viewing email in detail");
		sleep(3000);
		takeScreenshot("AmazonCodeNumber");

		// Collect code number which sent from amazon.
		sCodeNumber = chEmail.getOneTimePassword();
		System.out.println("Obtained the code number from email :" + sCodeNumber);

		sleep(3000);

		// After get code number then delete email.
		chEmail.deleteCurrentEmail();
		System.out.println("Email is deleted successfully");

		sleep(3000);
		takeScreenshot("AmazonEmailDeleted");

		// Switch to amazon page.
		newPage.switchToOldTab(0);
		System.out.println("Back to amazon page");

		sleep(3000);
		takeScreenshot("BackToAmazon");

		return sCodeNumber;
	}

	// -------------------------------------------------------------
	public void continueEmailSubmit() {
		continueEmailSubmit.click();
	}

	public void signInPasswordSubmit() {
		signInPasswordSubmit.click();
	}

	public void continueSubmit2() {
		continueButton2.click();
	}
}
