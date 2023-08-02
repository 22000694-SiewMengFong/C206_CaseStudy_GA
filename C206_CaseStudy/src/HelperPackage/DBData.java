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
	private static String user_email;
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
		user_name = data[1];
		user_email = data[2];
		user_access = data[3];
		user_picture = data[4];
		if (user_access.equals("vendor")) {
			System.out.println("IS this the errro 2?");
			menu_id = data[5];
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

	public String getUser_email() {
		return user_email;
	}

	// ===============================
	// Find and Add Account in DB
	// (DONE - NEED CHECKING)
	// ===============================

	private static String[] FindAccount(String email, String password) {
		DBUtil.init(jdbcURL, dbUsername, dbPassword);
		// connecting to DB and storing info
		String[] data = new String[6];
		String select = "SELECT * FROM user WHERE user_email = SHA1('" + email + "') AND user_password = SHA1('"
				+ password + "');";

		ResultSet rs = DBUtil.getTable(select);
		try {
			while (rs.next()) {
				data[0] = rs.getString("user_id");
				data[1] = rs.getString("user_name");
				data[2] = rs.getString("user_email");
				data[3] = rs.getString("ACCESS_TYPE");

				// Determine access type > access_type data
				switch (data[3]) {
				case "normal":
					String selectNormal = "SELECT normal_profile FROM normal WHERE normal_id = '" + data[0] + "'";
					ResultSet rsNormal = DBUtil.getTable(selectNormal);
					while (rsNormal.next()) {
						data[4] = rsNormal.getString("normal_profile");
					}
					break;
				case "vendor":
					String selectVendor = "SELECT * FROM vendor WHERE vendor_id = '" + data[0] + "'";
					ResultSet rsVendor = DBUtil.getTable(selectVendor);
					while (rsVendor.next()) {
						data[4] = rsVendor.getString("normal_profile");
						data[5] = rsVendor.getString("menu_id");
					}
					break;
				case "admin":
					String selectAdmin = "SELECT * FROM admin WHERE admin_id = '" + data[0] + "'";
					ResultSet rsAdmin = DBUtil.getTable(selectAdmin);
					while (rsAdmin.next()) {
						data[4] = rsAdmin.getString("admin_profile");
						break;
					}
					// update last login
					String updateSQL = "UPDATE user SET LAST_LOGIN = NOW() WHERE user_id='" + data[0] + "'";
					int rowsAffected = DBUtil.execSQL(updateSQL);
					// Set data null if update of Last Login fails
					if (rowsAffected != 1) {
						data = null;
					}
					break;
				}
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
