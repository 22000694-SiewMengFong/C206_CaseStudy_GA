package HelperPackage;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Authentication {

	// NOTE: URL may be different depending on the name of the database
	private static String jdbcURL = "jdbc:mysql://localhost/c206_ga";
	private static String dbUsername = "root";
	private static String dbPassword = "";

	// ===============================
	// Login and Register account and returns login info - user_id and access type
	// ===============================

	/**
	 * Method CreateAccount
	 * 
	 * @param name
	 * @param email
	 * @param password
	 * @param access
	 * @return
	 */
	private static String[] CreateAccount(String name, String email, String password, String access) {
		DBUtil.init(jdbcURL, dbUsername, dbPassword);

		// Return login info - by user id and access type
		String[] LoginInfo = new String[2];

		// Check if email exist in db
		if (CheckEmailDB(email) == false) {

			// Prevent SQL injection
			email = SQLInjection(email);
			name = SQLInjection(name);
			password = SQLInjection(password);

			// Create and format SQL insert Statement
			String insert = "INSERT INTO user(user_name, user_email, user_password, user_picture, user_access) VALUES ('"
					+ name + "' , '" + email + "', SHA1('" + password + "'), NULL, '" + access + "')";

			int rowsAffected = DBUtil.execSQL(insert);

			// Validate if insert is 1
			if (rowsAffected == 1) {
				LoginInfo = LoginInfo(email, password);
			} else {
				LoginInfo = null;
			}
		}

		DBUtil.close();
		return LoginInfo;
	} // End of CreateAccount

	/**
	 * Method CreateAccountNormal
	 * 
	 * @param name
	 * @param email
	 * @param password
	 * @return
	 */
	public static String[] CreateAccountNormal(String name, String email, String password) {
		String[] LoginInfo = new String[2];

		String access = "normal";

		LoginInfo = CreateAccount(name, email, password, access);
		return LoginInfo;

	} // End of CreateAccountNormal

	/**
	 * Method CreateAccountVendor
	 * 
	 * @param name
	 * @param email
	 * @param password
	 * @return
	 */
	public static String[] CreateAccountVendor(String name, String email, String password) {
		String[] LoginInfo = new String[2];

		String access = "vendor";

		LoginInfo = CreateAccount(name, email, password, access);
		return LoginInfo;

	} // End of CreateAccountVendor

	/**
	 * Method CreateAccountAdmin
	 * 
	 * @param name
	 * @param email
	 * @param password
	 * @return
	 */
	public static String[] CreateAccountAdmin(String name, String email, String password) {
		String[] LoginInfo = new String[2];

		String access = "admin";

		LoginInfo = CreateAccount(name, email, password, access);
		return LoginInfo;

	} // End of CreateAccountAdmin

	/**
	 * Method LoginAccount
	 * 
	 * @param email
	 * @param password
	 * @return
	 */
	public static String[] LoginAccount(String email, String password) {
		DBUtil.init(jdbcURL, dbUsername, dbPassword);

		boolean check = false;
		String[] LoginInfo = new String[2];

		// Prevent SQL Injection
		email = SQLInjection(email);
		password = SQLInjection(password);

		// Create and format SQL select Statement
		String select = "SELECT user_id, user_access FROM `user` WHERE `user_email` = '" + email
				+ "' AND `user_password` = SHA1('" + password + "');";

		ResultSet rs = DBUtil.getTable(select);
		// Check if Email exist and that the password match
		try {
			if (rs != null) {
				while (rs.next()) {
					check = true;
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		DBUtil.close();

		if (check == true) {
			LoginInfo = LoginInfo(email, password);
		} else {
			LoginInfo = null;
		}

		return LoginInfo;
	} // End of LoginAccount

	// ===============================
	// Validating User Inputs
	// ===============================

	/**
	 * Prevent SQL injection of string by double quoting single quotes
	 * 
	 * @param str
	 * @return formated SQL string
	 */
	private static String SQLInjection(String str) {
		str.replace("'", "''");
		return str;
	} // End of SQLInjection

	/**
	 * Method CheckEmailDB check if email is used inside db
	 * 
	 * @param email
	 * @return true if email is found
	 */
	private static boolean CheckEmailDB(String email) {
		DBUtil.init(jdbcURL, dbUsername, dbPassword);

		email = SQLInjection(email);

		boolean check = false;

		// Create and format SQL select Statement

		String select = "SELECT user_email FROM user";

		ResultSet rs = DBUtil.getTable(select);
		// Getting all the email from the SQL database and comparing it to the input
		try {
			while (rs.next()) {
				String sqlEmail = rs.getString("user_email");
				if (sqlEmail.equals(email)) {
					check = true;
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		DBUtil.close();
		return check;
	} // End of CheckEmailDB

	// ===============================
	// Login Section
	// ===============================
	// TODO make both getAccess and getUserID method to be only usable by LoginInfo
	// IN THIS CLASS (Authentication)

	/**
	 * Method LoginInfo returns a String List containing user id and access type
	 * 
	 * @param email
	 * @param password
	 * @return
	 */
	private static String[] LoginInfo(String email, String password) {
		String[] LoginInfo = new String[2];
		LoginInfo[0] = getUserID(email, password);
		
		String user_id = LoginInfo[0];
		LoginInfo[1] = getAccess(user_id);

		return LoginInfo;

	} // End of LoginInfo

	/**
	 * Method getAccess
	 * 
	 * @param email
	 * @param password
	 * @return
	 */
	private static String getAccess(String user_id) {
		String UserAccess = null;

		String select = "SELECT user_access FROM `user` WHERE `user_id` = '" + user_id + "');";

		ResultSet rs = DBUtil.getTable(select);

		try {
			if (rs != null) {
				while (rs.next()) {
					UserAccess = rs.getString("user_access");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return UserAccess;
	} // End of getUserAccess

	/**
	 * Method getInfo
	 * 
	 * @param email
	 * @param password
	 * @return
	 */
	private static String getUserID(String email, String password) {
		String UserID = null;

		String select = "SELECT user_id FROM `user` WHERE `user_email` = '" + email + "' AND `user_password` = SHA1('"
				+ password + "');";

		ResultSet rs = DBUtil.getTable(select);

		try {
			if (rs != null) {
				while (rs.next()) {
					UserID = rs.getString("user_id");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return UserID;
	} // End of getUserID
} // End of Class
