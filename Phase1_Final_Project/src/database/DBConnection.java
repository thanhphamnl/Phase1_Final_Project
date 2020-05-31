package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	public final static String DB_DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
	//public final static String DB_URL_DATABASE = "jdbc:mysql://localhost:3306/amazonseleniumtest?serverTimezone=UTC";
	public final static String DB_URL_DATABASE = "jdbc:mysql://localhost:3306/amazon?serverTimezone=UTC";
	public final static String DB_URL_NO_DATABASE = "jdbc:mysql://localhost:3306/?serverTimezone=UTC";
	public final static String DB_USERNAME = "root";
	public final static String DB_PASSWORD = "YOUR PASSWORD HERE";
	public static Connection con;

	public Connection getConnection() throws ClassNotFoundException, SQLException {
		con = null;
		// load the Driver Class
		Class.forName(DB_DRIVER_CLASS);
		//System.out.println(con.isClosed());	
		con = DriverManager.getConnection(DB_URL_DATABASE, DB_USERNAME, DB_PASSWORD);	

		System.out.println("DB Connection created successfully");
		return con;
	}

	public Connection getConnectNoDatabase() throws ClassNotFoundException, SQLException {
		con = null;
		// load the Driver Class
		Class.forName(DB_DRIVER_CLASS);
		//System.out.println(con.isClosed());	
		con = DriverManager.getConnection(DB_URL_NO_DATABASE, DB_USERNAME, DB_PASSWORD);	

		System.out.println("DB Connection created successfully");
		return con;
	}
	
	public void closeConnection() throws SQLException {
		if (DBConnection.con != null) {
			DBConnection.con.close();
			System.out.println("DB Connection is closed successfully\n");
		}
	}

}
