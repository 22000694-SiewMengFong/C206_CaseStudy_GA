package StartPage;

import java.sql.ResultSet;
import java.sql.SQLException;

import HelperPackage.DBUtil;

//TODO fix select statement error
//TODO change boolean of LoginAccount to string where it returns the user_access type

public class Authentication {

	// NOTE: URL may be different depending on the name of the database
	private static String jdbcURL = "jdbc:mysql://localhost/c206_ga";
	private static String dbUsername = "root";
	private static String dbPassword = "";

	/**
	 * Method CreateAccount
	 * 
	 * @param name
	 * @param email
	 * @param password
	 * @return
	 */
	protected static String CreateAccount(String name, String email, String password) {
		DBUtil.init(jdbcURL, dbUsername, dbPassword);

		// ERROR: SQL execute not running in java
		// FIXED: password can be retrieve by hashing user password to compare

		String check = null;

		// Check if email exist in db
		if (CheckEmailDB(email) == false) {

			// Prevent SQL injection
			email = SQLInjection(email);
			name = SQLInjection(name);
			password = SQLInjection(password);

			// Create and format SQL insert Statement
			String insert = "INSERT INTO user(user_name, user_email, user_password, user_picture, user_access) VALUES ('"
					+ name + "' , '" + email + "', SHA1('" + password + "'), NULL, 'normal' );";

			int rowsAffected = DBUtil.execSQL(insert);

			// Validate if insert is 1
			if (rowsAffected == 1) {
				check = "normal";
			}
		}

		DBUtil.close();
		return check;

	} // End of CreateAccount

	/**
	 * Method LoginAccount
	 * 
	 * @param email
	 * @param password
	 * @return
	 */
	protected static boolean LoginAccount(String email, String password) {
		DBUtil.init(jdbcURL, dbUsername, dbPassword);

		boolean check = false;

		// Prevent SQL Injection
		email = SQLInjection(email);
		password = SQLInjection(password);

		// Create and format SQL select Statement
		String select = "SELECT * FROM `user` WHERE `user_email` = '" + email + "' AND `user_password` = SHA1('"
				+ password + "');";

		int rowsAffected = DBUtil.execSQL(select);

		// Validate if select is 1
		if (rowsAffected == 1) {
			check = true;
		}

		DBUtil.close();
		return check;
	} // End of LoginAccount

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

		String select = "SELECT * FROM user";

		ResultSet rs = DBUtil.getTable(select);
		// Getting all the email from the SQL database and comparing it to the input
		try {
			while (rs.next()) {
				String sqlEmail = rs.getString("user_email");
				if (sqlEmail.equals(email)) {
					check = true;
					return check;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DBUtil.close();
		return check;
	} // End of CheckEmailDB

} // End of Class
