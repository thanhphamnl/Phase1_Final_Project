package generalBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserDriverFactory {

	private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	private String browser;

	public BrowserDriverFactory(String browser) {
		this.browser = browser.toLowerCase();
	}

	public WebDriver createDriver() {
		System.out.println("[Setting up driver: " + browser + "]");

		// Creating driver
		switch (Integer.parseInt(browser)) {
		case 1:
			System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
			driver.set(new ChromeDriver());
			
//			String chromeProfilePath = "C:\\Users\\thanh\\AppData\\Local\\Google\\Chrome\\User Data\\";
//			ChromeOptions chromeProfile = new ChromeOptions();
//			chromeProfile.addArguments("user-data-dir=" + chromeProfilePath);			
//			chromeProfile.addArguments("profile-directory=default");	
//			driver.set(new ChromeDriver(chromeProfile));				
			break;

		case 2:
			System.setProperty("webdriver.gecko.driver", "resources/geckodriver.exe");
			driver.set(new FirefoxDriver());
			break;

		case 3:
			System.setProperty("webdriver.ie.driver", "resources/IEDriverServer.exe");
			driver.set(new InternetExplorerDriver());
			break;

		default:
			System.out.println("[Couldn't set: " + browser + ". Its unknown. Starting chrome instead]");
			System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
			driver.set(new ChromeDriver());
			break;

		}

		return driver.get();
	}
	
}