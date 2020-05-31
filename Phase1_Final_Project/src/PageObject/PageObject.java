package PageObject;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObject {

	protected static String sAmazonHome = "select * from baseURL where urlId=1";
	protected static String sGetAmazonUserProfile = "select * from userprofile where userid = 1";
	protected static String sGetEmailAmazon = "select emailAdrress from userprofile where userid = 1";
	protected static String sGetEmailGoogle = "select emailAdrress from userprofile where userid = 2";
	protected static String sGetPasswordAmazon = "select userPassword from userprofile where userid = 1";
	protected static String sGetPasswordGoogle = "select userPassword from userprofile where userid = 2";
	protected static String sGetProductName = "select productName from product where id = 1";
	protected static String sGetFillter1 = "select fillter1 from product where id = 1";
	protected static String sGetFillter2 = "select fillter2 from product where id = 1";
	protected static String sGetFillter3 = "select fillter3 from product where id = 1";
	protected static String sGetFillter4 = "select fillter4 from product where id = 1";
	// private static String sGoogleEmail = "select * from baseURL where urlId=2";
	String sAmazonTitle = "Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more";

	protected WebDriver driver;

	public PageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void waitForLoading(WebDriver driver, WebElement webElement) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(webElement));
	}

	// STATIC SLEEP TO SEE TEST EXECUTION
	protected void sleep(int second) {
		try {
			Thread.sleep(second);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private String setCurrentDateTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now).toString();
	}

	protected void takeScreenshot(String fileName) {

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "//test-output//screenshots//" + setCurrentDateTime() + " "
				+ fileName + ".png";
		try {
			FileUtils.copyFile(scrFile, new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
