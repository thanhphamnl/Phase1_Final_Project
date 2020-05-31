import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import generalBase.BaseDriver;
import signInAmazon.authorizedHomePage;
import signInAmazon.helloSignIn;
import signInAmazon.searchProduct;
import signInAmazon.signInEmailAmazon;
import signInAmazon.signInPasswordAmazon;
import signInAmazon.yourAccount;
import signInAmazon.yourAddress;
import signInAmazon.yourProfile;
import sqlGuider.sqlGuider;

public class TestExecution {
	String url = "";
	Connection con = null;
	Statement stmt = null;
	ResultSet result = null;

	private static WebDriver driver;
	private static BaseDriver baseDriver;
	private static sqlGuider sqlDriver;

	private static String sAmazonHome = "select * from baseURL where urlId=1";
	private static String sGetEmailAmazon = "select emailAdrress from userprofile where userid = 1";
	//private static String sGetEmailGoogle = "select emailAdrress from userprofile where userid = 2";
	private static String sGetPasswordAmazon = "select userPassword from userprofile where userid = 1";
	//private static String sGetPasswordGoogle = "select userPassword from userprofile where userid = 2";
	private static String sGetProductName = "select productName from product where id = 1";
	private static String sGetFillter1 = "select fillter1 from product where id = 1";
	private static String sGetFillter2 = "select fillter2 from product where id = 1";
	private static String sGetFillter3 = "select fillter3 from product where id = 1";
	private static String sGetFillter4 = "select fillter4 from product where id = 1";
	// private static String sGoogleEmail = "select * from baseURL where urlId=2";
	String sAmazonTitle = "Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more";

	@Before
	public void setUp() throws Exception {
		System.out.println("We are calling @Before............");
		sqlDriver = new sqlGuider();
		// Create database
		sqlDriver.createDatabase();

		baseDriver = new BaseDriver();
		// sqlGuider sqlDriver = new sqlGuider();
		driver = baseDriver.setUp("1");// 1 chrome, 2: firefox, 3: ie.
		// Initial Amazone HomePage.
		sqlDriver.retrieveData(sAmazonHome, driver, "Amazon");
	}

	@Test
	public void TestExecute() throws InstantiationException, IllegalAccessException, ClassNotFoundException,
			SQLException, InterruptedException {
		try {
			System.out.println("We are calling @Test............");
			// authenticationRequired auth = new authenticationRequired(driver);

			// SignIn Class---------------------------
			Thread.sleep(5000);
			// Click Navigation to Sign In.
			helloSignIn heSign = new helloSignIn(driver);
			assertTrue(heSign.isInitialized());
			baseDriver.takeScreenshot("HomePage");
//			//home page amazon has launched successfully and sign in link is ready to be clicked.
//			System.out.println("Home page amazon has launched successfully and sign in link is ready to be clicked");	
			signInEmailAmazon signInEmail = heSign.submit();
			System.out.println("Sign in link is clicked");

			Thread.sleep(5000);

			// Enter Email to sign in.
			assertTrue(signInEmail.isInitialized());
			System.out.println("Sign-In Page is loaded successfully");

			// Enter Email to sign in
			signInEmail.enterEmail(sqlDriver.getSingleColumn(sGetEmailAmazon));
			Thread.sleep(3000);
			baseDriver.takeScreenshot("EnteredEmailAddress");
			signInPasswordAmazon signInPassword = signInEmail.submit();
			assertTrue(signInPassword.isInitialized());
			System.out.println("Email is verified, password page is shown");
			Thread.sleep(3000);

			// Enter Password to sign in
			signInPassword.enterPassword(sqlDriver.getSingleColumn(sGetPasswordAmazon));
			Thread.sleep(3000);
			baseDriver.takeScreenshot("EnteredPassword");
			authorizedHomePage signed = signInPassword.submit();
			System.out.println("Password is submitted.");

			// ------------------------------------------------
			//assertTrue(signed.isInitialized());
			//System.out.println("Signed in suceesfully, sign out feature is enable.");

			// SearchProduct
			// Class-------------------------------------------------------------------------------------
			// Initial java class searchProduct to search product, filter product, add to
			// shopping cart, view cart.
			searchProduct search = new searchProduct(driver);

			// Get product from database and send to amazon to search.
			search.enterProduct(sqlDriver.getSingleColumn(sGetProductName));

			Thread.sleep(2000);
			baseDriver.takeScreenshot("EnterProduct");

			// Fillter product from 1 to 4 to get the expecting product.
			String sGetFillter;
			for (int i = 1; i < 5; i++) {
				sGetFillter = "";
				switch (i) {
				case 1:
					sGetFillter = sGetFillter1;
					break;
				case 2:
					sGetFillter = sGetFillter2;
					break;
				case 3:
					sGetFillter = sGetFillter3;
					break;
				case 4:
					sGetFillter = sGetFillter4;
					break;
				}
				// Fillter product
				String fillter = sqlDriver.getSingleColumn(sGetFillter);
				search.fillterLinkText(fillter);
				Thread.sleep(2000);
				baseDriver.takeScreenshot("Fillter" + i + " product");
				System.out.println(fillter);
			}

			// search.viewDetailProduct();
			Thread.sleep(2000);
			baseDriver.takeScreenshot("ViewDetailProduct");

			// add expecting product to cart.
			search.addToShoppingCar();
			Thread.sleep(2000);
			baseDriver.takeScreenshot("AddedToShoppingCart");
			System.out.println("Added product to shopping cart");

			// Review shopping cart.
			search.reviewShoppingCar();
			Thread.sleep(2000);
			baseDriver.takeScreenshot("ReviewShoppingCart");
			System.out.println("Review shopping cart");

			// Ended SearchProduct
			// Class-------------------------------------------------------------------------------------

			// Initial yourAccount Class to update account information
			// -------------------------------------------------------
			yourAccount acc = new yourAccount(driver);

			// Click on your account link.
			acc.yourAccountLink();
			System.out.println("Your Account link is clicked.");

			// Check to see if current your account page is shown correctly
			assertTrue(acc.isInitialized());

			// Taka a screenshot for the current account page.
			baseDriver.takeScreenshot("YourAccountPage");

			// Click on Your Amazon Profile link.
			acc.clickLinkProfile();
			System.out.println("Your Amazon profile link is clicked.");
			// End yourAccount
			// Class-------------------------------------------------------------------------------------

			// Begin authenticationRequired
			// Class------------------------------------------------------------------------
			// Check to see if sing in password is require.
//			if(auth.isPasswordInitialized()) {
//				auth.enterPasswordAmazon();
//				auth.signInPasswordSubmit();				
//			}				
			// End authenticationRequired
			// Class--------------------------------------------------------------------------

			// Initial yourProfile Class to update
			// profile---------------------------------------------------------------
			yourProfile profile = new yourProfile(driver);

			// Check to see if profile page is loaded correctly.
			assertTrue(profile.isInitialized());
			System.out.println("Your profile page is loaded correctly");

			// Upload image.
			profile.uploadImage();
			Thread.sleep(12000);
			baseDriver.takeScreenshot("YourProfilePageAfterUpdate");
			// submit profile by click on your account link.
			profile.submitProfile();
			Thread.sleep(3000);
			System.out.println("Your profile page is updated");
		
			// End yourProfile
			// Class---------------------------------------------------------------------------------

			// Comeback to yourAccount Class update address
			// -------------------------------------------------------

			// Click on your account link.
			acc.yourAccountLink();
			System.out.println("Your Account link is clicked.");

			// Check to see if current your account page is shown correctly
			assertTrue(acc.isInitialized());
			Thread.sleep(2000);
			baseDriver.takeScreenshot("YourAccount");

			// Click on Your Amazon Profile link.
			acc.clickLinkAddress();
			System.out.println("Your Amazon address link is clicked.");
			Thread.sleep(2000);

			// End yourAccount
			// Class-------------------------------------------------------------------------------------

			// Begin authenticationRequired
			// Class------------------------------------------------------------------------
			// Check to see if sign in password is require.
//			if (auth.isPasswordInitialized()) {
//				Thread.sleep(3000);
//				System.out.println("Your Password is required");
//				baseDriver.takeScreenshot("YourPasswordRequired");
//				auth.enterPasswordAmazon();
//				Thread.sleep(3000);
//				System.out.println("Your Password is entered");
//				auth.signInPasswordSubmit();
//			}
			// End authenticationRequired
			// Class--------------------------------------------------------------------------

			// Begin yourAddress
			// Class--------------------------------------------------------------------------		

			// Click on your account link.
			acc.yourAccountLink();
			System.out.println("Your Account link is clicked.");

			// Check to see if current your account page is shown correctly
			assertTrue(acc.isInitialized());

			// Taka a screenshot for the current account page.
			baseDriver.takeScreenshot("YourAccountPage");

			// Click on Your Amazon Profile link.
			acc.clickLinkAddress();
			System.out.println("Your Address link is clicked.");

			yourAddress address = new yourAddress(driver);
			// Check to see if current your account page is shown correctly
			assertTrue(address.isInitialized());
			Thread.sleep(2000);
			baseDriver.takeScreenshot("AddressPage is launched correctly");

			// Click on Your Amazon Profile link.
			address.clickAddAdress();
			System.out.println("Your Amazon address link is clicked.");
			Thread.sleep(2000);

			baseDriver.takeScreenshot("AddNewAddress");
			System.out.println("Add New address Icon is clicked.");

			address.updateAddress();
			Thread.sleep(2000);

			baseDriver.takeScreenshot("EnteredNewAddress");
			System.out.println("Added New address Information.");
			address.addAdressSubmit();

			Thread.sleep(2000);
			baseDriver.takeScreenshot("UpdatedNewAddress");
			System.out.println("Updated New Address.");

			// End yourAddress
			// Class--------------------------------------------------------------------------
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}

	@After
	public void tearDown() throws SQLException {
		System.out.println("We are calling @After............");
		//baseDriver.tearDown();
		con.close();
	}
}