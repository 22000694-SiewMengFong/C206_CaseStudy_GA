package HelperPackage;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.stage.Stage;

public class DBData {

	// NOTE: URL may be different depending on the name of the database
	private static String jdbcURL = "jdbc:mysql://localhost/c206_ga";
	private static String dbUsername = "root";
	private static String dbPassword = "";

	private static String user_access;
	private static String user_id;
	private static String user_name;
	private static String user_picture;
	private static String menu_id;

	/**
	 * Creates Account
	 * 
	 * @param user_email
	 * @param user_password
	 * @param user_access
	 * @param user_name
	 * @param user_picture
	 */
	public DBData(String email, String password, String access, String name, String picture) {

		boolean check = AddAccountDB(email, password, access, name, picture);
		if (check == false) {
			user_id = null;
			user_access = null;
			user_name = null;
			user_picture = null;
			return;
		}
		String[] data = FindAccount(email, password);

		user_id = data[0];
		user_access = data[1];
		user_name = data[2];
		user_picture = data[3];
	}

	/**
	 * Login to account
	 * 
	 * @param user_email
	 * @param user_password
	 */
	public DBData(String email, String password) {
		String[] data = FindAccount(email, password);

		user_id = data[0];
		user_access = data[1];
		user_name = data[2];
		user_picture = data[3];
		if (user_access.equals("vendor")) {
			menu_id = data[4];
		}
	}

	// TODO NOT YET
	public void setUser_picture(String user_picture) {
		this.user_picture = user_picture;
	}

	public String getUser_access() {
		return user_access;
	}

	public String getUser_id() {
		return user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public String getUser_picture() {
		return user_picture;
	}

	public String getMenu_id() {
		return menu_id;
	}

	// ===============================
	// Find and Add Account in DB
	// (DONE - NEED CHECKING)
	// ===============================

	private static String[] FindAccount(String email, String password) {
		DBUtil.init(jdbcURL, dbUsername, dbPassword);

		String[] data = new String[4];

		String select = "SELECT user_access, user_id, user_name, user_picture FROM `user` WHERE `user_email` = SHA1('"
				+ email + "') AND `user_password` = SHA1('" + password + "');";

		ResultSet rs = DBUtil.getTable(select);

		try {
			if (rs != null) {
				while (rs.next()) {
					data[0] = rs.getString("user_id");
					data[1] = rs.getString("user_access");
					data[2] = rs.getString("user_name");
					data[3] = rs.getString("user_picture");

					String updateSQL = "UPDATE user SET LAST_LOGIN = NOW() WHERE user_id='" + data[0] + "'";
					int rowsAffected = DBUtil.execSQL(updateSQL);

					// Set data null if update of Last Login fails
					if (rowsAffected != 1) {
						data = null;
					}

					break;
				}
			} else {
				data = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			data = null;
		}

		DBUtil.close();
		return data;
	}

	private static boolean AddAccountDB(String email, String password, String access, String name, String picture) {
		DBUtil.init(jdbcURL, dbUsername, dbPassword);

		// Return Check
		boolean check = false;

		// Check if email exist in db
		if (CheckEmailDB(email) == false) {

			// Prevent SQL injection
			email = SQLInjection(email);
			name = SQLInjection(name);
			password = SQLInjection(password);

			// Create and format SQL insert Statement
			String insert = "INSERT INTO user(user_name, user_email, user_password, user_picture, user_access, LAST_LOGIN) VALUES ('"
					+ name + "' , SHA1('" + email + "'), SHA1('" + password + "'), " + picture + ", '" + access
					+ "', NOW())";

			int rowsAffectedUser = DBUtil.execSQL(insert);
			// Validate if insert is 1
			if (rowsAffectedUser == 1) {
				check = true;
			}

			FindAccount(email, password);
			// adding to account types

			switch (access) {
			case "normal":
				int phoneNo = Helper.readInt("Enter phone number > ");
				String allegies = Helper.readString("Enter Allegies > ");
				allegies = SQLInjection(allegies);
				String address = Helper.readString("Enter Address > ");
				address = SQLInjection(address);
				insert = "INSERT INTO normal(normal_id, normal_phoneNumber, normal_address, normal_profile, normal_allegies) VALUES ('"
						+ user_id + "' ,'" + phoneNo + "'), '" + address + "'), " + picture + ", '" + allegies + "');";
				break;
			case "vendor":
				phoneNo = Helper.readInt("Enter phone number > ");
				address = Helper.readString("Enter Address > ");
				address = SQLInjection(address);
				String companyName = Helper.readString("Enter Company Name > ");
				companyName = SQLInjection(companyName);
				int menu = Integer.parseInt(menu_id);
				insert = "INSERT INTO vendor(vendor_id, vendor_phoneNumber, vendor_companyName, normal_profile, vendor_address, menu_id) VALUES ('"
						+ user_id + "' ,'" + phoneNo + "'), '" + companyName + "'), " + picture + ", '" + address
						+ "', '" + menu + "');";
				break;
			case "admin":
				insert = "INSERT INTO admin(admin_id, admin_profile) VALUES ('" + user_id + "' , '" + picture + "');";
				break;
			default:
			}
		}

		DBUtil.close();
		return check;
	}

	// ===============================
	// Validating User Inputs
	// (DONE - NEED CHECKING)
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

		String select = "SELECT user_email FROM user WHERE `user_email` = SHA1('" + email + "');";

		ResultSet rs = DBUtil.getTable(select);
		// Getting all the email from the SQL database and comparing it to the input
		// if rs = null - no result
		if (rs != null) {
			try {
				while (rs.next()) {
					check = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		DBUtil.close();
		return check;
	} // End of CheckEmailDB
}
