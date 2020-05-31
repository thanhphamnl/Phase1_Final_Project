package generalBase;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class BaseDriver {
	private WebDriver driver;

	public WebDriver setUp(String sBrowser) {

		BrowserDriverFactory factory = new BrowserDriverFactory(sBrowser);
		driver = factory.createDriver();	
		return driver;		
	}	

	public void tearDown() {
		// Closing driver
		System.out.println("[Closing driver]");
		driver.close();
	}

	// STATIC SLEEP TO SEE TEST EXECUTION
	public void sleep(int iWait) {
		try {
			Thread.sleep(iWait);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private String setCurrentDateTime() {
		   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
		   LocalDateTime now = LocalDateTime.now();
		   return dtf.format(now).toString();
	}

	public void takeScreenshot(String fileName) {
		
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "//test-output//screenshots//" + setCurrentDateTime()+" " +fileName + ".png";
		try {
			FileUtils.copyFile(scrFile, new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
