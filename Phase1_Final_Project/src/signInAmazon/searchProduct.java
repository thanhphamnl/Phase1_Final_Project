package signInAmazon;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import PageObject.PageObject;

public class searchProduct extends PageObject {

	public searchProduct(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "nav-cart")
	private WebElement shoppingCartButton;

	@FindBy(xpath = "//button[@id='siNoCoverage-announce']")
	private WebElement noThanksProtectionPlan;

	@FindBy(id = "add-to-cart-button")
	private WebElement addToCart;

	@FindBy(id = "twotabsearchtextbox")
	private WebElement searchProductField;

	public boolean isInitialized() {
		return searchProductField.isDisplayed();
	}

	public void enterProduct(String sProduct) {
		this.searchProductField.clear();
		this.searchProductField.sendKeys(sProduct);
		this.searchProductField.sendKeys(Keys.ENTER);
	}

	public void fillterLinkText(String sFillterProduct) {
		driver.findElement(By.linkText(sFillterProduct)).click();
	}

	public void addToShoppingCar() {
		// waitForLoading(driver, noThanksProtectionPlan);

		addToCart.click();
	}

	public void reviewShoppingCar() {
		if (noThanksProtectionPlan.isDisplayed()) {
			noThanksProtectionPlan.click();
		}
		shoppingCartButton.click();
	}
}
