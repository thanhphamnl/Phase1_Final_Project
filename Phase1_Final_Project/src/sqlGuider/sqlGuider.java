package sqlGuider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import PageObject.PageObject;
import database.DBConnection;
import generalBase.BaseDriver;

public class sqlGuider{

	// JDBC variables for opening and managing connection
	private static Connection con;
	private static Statement stmt;
	private static ResultSet result;

	private static BaseDriver baseDriver;
	private String sEmail;

	private static String AmazonDB = "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = 'amazon'";
	private static String sCreateDatabase = "CREATE DATABASE IF NOT EXISTS amazon";
	// private static String sDropDatabase = "Drop DATABASE if exists
	// amazonseleniumtest1";

	private static final String sInsertUserprofile = "insert into userprofile (userid, userName, userPassword, firstName, lastName, mailingAddress, emailAdrress, phoneNumber, city , state, zipCode) values (?,?,?,?,?,?,?,?,?,?,?)";
	private static final String sInsertBaseURL = "insert into baseURL (urlId, baseURL) values (?,?)";
	private static final String sInsertProduct = "insert into product (Id, productName, fillter1 , fillter2, fillter3, fillter4) values (?,?,?,?,?,?)";
	private static final String sInsertWebDriver = "insert into webDriver (driverId, driverName) values (?,?)";

	private static String sCreateTableUserprofile = "CREATE TABLE userprofile ("
			+ "  userid INTEGER	PRIMARY KEY NOT NULL," + "  userName varchar(50) NOT NULL,"
			+ "  userPassword varchar(50) NOT NULL," + "  firstName varchar(50) NOT NULL,"
			+ "  lastName varchar(50) NOT NULL," + "  mailingAddress varchar(50) NOT NULL,"
			+ "  emailAdrress varchar(50) NOT NULL," + "  phoneNumber varchar(10) NOT NULL,"
			+ "  city varchar(50) NOT NULL," + "  state varchar(30) NOT NULL," + "  zipCode varchar(5) NOT NULL " + ")";

	private static String sCreateTableBaseURL = "CREATE TABLE baseURL (\r\n"
			+ "  urlId INTEGER	PRIMARY KEY NOT NULL,\r\n" + "  baseURL varchar(500) NOT NULL \r\n" + ")";

	private static String sCreateTableProduct = "CREATE TABLE product (\r\n"
			+ "  id INTEGER	PRIMARY KEY NOT NULL,\r\n" + "  productName varchar(200) NOT NULL,\r\n"
			+ "  fillter1 varchar(100)  NULL,\r\n" + "  fillter2 varchar(100)  NULL,\r\n"
			+ "  fillter3 varchar(100)  NULL,\r\n" + "  fillter4 varchar(100)  NULL\r\n" + ")";

	private static String sCreateTableWebDriver = "CREATE TABLE webDriver (\r\n"
			+ "  driverId INTEGER	PRIMARY KEY NOT NULL,\r\n" + "  driverName varchar(100) NOT NULL \r\n" + ")";

	public sqlGuider() {
	}

	public boolean checkExistingDatabase() {
		boolean amazondb = false;
		try {
			// Create Statement
			con = new DBConnection().getConnectNoDatabase();
			stmt = (Statement) con.createStatement();

			// method which returns the requested information as rows of data
			result = stmt.executeQuery(AmazonDB);
			if (result.next()) {
				System.out.println("database amazon exist");
				amazondb = true;

			} else {
				System.out.println("database amazon does not exist");
				amazondb = false;
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					new DBConnection().closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return amazondb;
	}

	public void createTable() throws ClassNotFoundException {

		if (checkExistingDatabase()) {
			Connection con = null;
			try {
				con = new DBConnection().getConnection();
				// Create Statement
				stmt = (Statement) con.createStatement();
				// set auto commit to false
				con.setAutoCommit(false);
				stmt.executeUpdate(sCreateTableWebDriver);
				stmt.executeUpdate(sCreateTableBaseURL);
				stmt.executeUpdate(sCreateTableUserprofile);
				stmt.executeUpdate(sCreateTableProduct);
				insertUserprofile(con, 1, "Your user name", "yourpassword", "Test", "Selenium", "216 Textile Way",
						"youremail@gmail.com", "123456789", "Dalton", "GA ", "30721");

				insertUserprofile(con, 2, "yourname", "yourpassword", "Test", "Selenium",
						"5050 Alibaba St", "youremail@gmail.com", "333333333", "TestSelenium", "Online",
						"5225");
				insertBaseURL(con, 1, "https://www.amazon.com/");
				insertBaseURL(con, 2, "https://www.mail.google.com/");
				insertWebDriver(con, 1, "chrome");
				insertWebDriver(con, 2, "gecko");
				insertWebDriver(con, 3, "ie");
				insertProduct(con, 1, "pendrive", "SanDisk", "USB 2.0", "1GB & Under",
						"Sandisk B35 SDCZ51-032G-B35 32GB Cruzer USB Flash Drive");
				// now commit transaction
				con.commit();
			} catch (SQLException e) {
				e.printStackTrace();
				try {
					con.rollback();
					System.out.println("JDBC Create Table Transaction rolled back successfully");
				} catch (SQLException e1) {
					System.out.println("SQLException in rollback" + e.getMessage());
				}
			} finally {
				try {
					if (con != null)
						new DBConnection().closeConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public void createDatabase() throws ClassNotFoundException {
		Connection con = null;
		if (!checkExistingDatabase()) {
			try {
				con = new DBConnection().getConnectNoDatabase();
				// Create Statement
				stmt = (Statement) con.createStatement();
				// stmt.executeUpdate(sDropDatabase);
				stmt.executeUpdate(sCreateDatabase);
				createTable();
			} catch (SQLException e) {
				e.printStackTrace();
				try {
					con.rollback();
					System.out.println("JDBC Transaction rolled back successfully");
				} catch (SQLException e1) {
					System.out.println("SQLException in rollback" + e.getMessage());
				}
			} finally {
				try {
					if (con != null)
						new DBConnection().closeConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
	}

	public static void insertUserprofile(Connection con, int userid, String userName, String userPassword,
			String firstName, String lastName, String mailingAddress, String emailAdrress, String phoneNumber,
			String city, String state, String zipCode) throws SQLException {
		PreparedStatement stmt = con.prepareStatement(sInsertUserprofile);
		stmt.setInt(1, userid);
		stmt.setString(2, userName);
		stmt.setString(3, userPassword);
		stmt.setString(4, firstName);
		stmt.setString(5, lastName);
		stmt.setString(6, mailingAddress);
		stmt.setString(7, emailAdrress);
		stmt.setString(8, phoneNumber);
		stmt.setString(9, city);
		stmt.setString(10, state);
		stmt.setString(11, zipCode);

		stmt.executeUpdate();
		stmt.close();
	}

	public static void insertBaseURL(Connection con, int urlId, String baseURL) throws SQLException {
		PreparedStatement stmt = con.prepareStatement(sInsertBaseURL);
		stmt.setInt(1, urlId);
		stmt.setString(2, baseURL);
		stmt.executeUpdate();
		stmt.close();
	}

	public static void insertWebDriver(Connection con, int driverId, String driverName) throws SQLException {
		PreparedStatement stmt = con.prepareStatement(sInsertWebDriver);
		stmt.setInt(1, driverId);
		stmt.setString(2, driverName);
		stmt.executeUpdate();
		stmt.close();
	}

	public static void insertProduct(Connection con, int Id, String productName, String fillter1, String fillter2,
			String fillter3, String fillter4) throws SQLException {
		PreparedStatement stmt = con.prepareStatement(sInsertProduct);
		stmt.setInt(1, Id);
		stmt.setString(2, productName);
		stmt.setString(3, fillter1);
		stmt.setString(4, fillter2);
		stmt.setString(5, fillter3);
		stmt.setString(6, fillter4);
		stmt.executeUpdate();
		stmt.close();
	}

	public void retrieveData(String sql, WebDriver driver, String sHomePage) {
		try {
			con = new DBConnection().getConnection();
			// getting Statement object to execute query
			stmt = con.createStatement();
			result = stmt.executeQuery(sql);
			int webType;
			String webCollector;
			String sendKeysText = "";
			while (result.next()) {
				if (sHomePage.equals("Amazon")) {
					webCollector = result.getString(2);
					webType = 0;
				} else {
					webCollector = result.getString(2);
					webType = result.getInt(3);
					sendKeysText = result.getString(4);
				}
				/*
				 * 1-xpath, 2-id, 3-className, 4-name, 5-linkText, 6-partialLinkText,
				 * 7-cssSelector
				 */
				switch (webType) {
				case 0:// show home page only
					driver.get(webCollector);
					driver.manage().window().maximize();
					//driver.manage().deleteAllCookies();
					driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					//baseDriver.sleep(2000);
					
					break;
				case 1:
					driver.findElement(By.xpath(webCollector)).click();
					break;
				case 2:// Id
					if (sendKeysText != null) {
						driver.findElement(By.id(webCollector)).sendKeys(sendKeysText);
						baseDriver.sleep(2000);
						baseDriver.takeScreenshot(sendKeysText);
					} else {
						driver.findElement(By.id(webCollector)).click();
						baseDriver.sleep(4000);
					}
					break;
				case 3:// className
					driver.findElement(By.className(webCollector)).click();
					baseDriver.sleep(4000);

					break;

				case 4:// name

					break;
				case 5:// linkText

					break;
				case 6:// partialLinkText

					break;
				case 7:// cssSelector

					break;

				}
			}
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// close connection ,stmt and resultset here
			try {

				new DBConnection().closeConnection();

			} catch (SQLException se) {
				/* can't do anything */ }
			try {
				if (!stmt.isClosed()) {
					stmt.close();
				}
			} catch (SQLException se) {
				/* can't do anything */ }
			try {
				result.close();
			} catch (SQLException se) {
				/* can't do anything */ }
		}

	}

	// Get email from database.
	public String getSingleColumn(String sql) {
		try {
			con = new DBConnection().getConnection();
			// getting Statement object to execute query
			stmt = con.createStatement();
			result = stmt.executeQuery(sql);

			while (result.next()) {
				sEmail = result.getString(1);
			}

		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// close connection ,stmt and resultset here
			try {
				new DBConnection().closeConnection();
			} catch (SQLException se) {
				/* can't do anything */ }
			try {
				stmt.close();
			} catch (SQLException se) {
				/* can't do anything */ }
			try {
				result.close();
			} catch (SQLException se) {
				/* can't do anything */ }
		}
		return sEmail;
	}
	
	//Get User profile from database.
	public String[] getUserProfile(String sql) {
		String[] userProfile = new String [6];
		try {
			con = new DBConnection().getConnection();
			// getting Statement object to execute query
			stmt = con.createStatement();
			result = stmt.executeQuery(sql);

			while (result.next()) {
				userProfile[0] = result.getString(2);// sFullName
				userProfile[1] = result.getString(6);// sAddressLine
				userProfile[2] = result.getString(8);// sPhoneNumber
				userProfile[3] = result.getString(9);// sCity
				userProfile[4] = result.getString(10);// sState
				userProfile[5] = result.getString(11);// sZipCode
			}

		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// close connection ,stmt and resultset here
			try {
				new DBConnection().closeConnection();
			} catch (SQLException se) {
				/* can't do anything */ }
			try {
				stmt.close();
			} catch (SQLException se) {
				/* can't do anything */ }
			try {
				result.close();
			} catch (SQLException se) {
				/* can't do anything */ }
		}
		return userProfile;
	}
}
