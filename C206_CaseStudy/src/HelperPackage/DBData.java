package HelperPackage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBData {

	// NOTE: URL may be different depending on the name of the database
	private static String jdbcURL = "jdbc:mysql://localhost/c206_ga";
	private static String dbUsername = "root";
	private static String dbPassword = "";

	private static String user_access;
	private static String user_id;
	private static String user_name;
	private String user_picture;
	private static String user_email;

	/**
	 * Creates Account
	 * 
	 * @param user_email
	 * @param user_password
	 * @param user_access
	 * @param user_name
	 * @param user_picture
	 */
	public DBData(String email, String password, String access, String name, String picture, String[] otherInfo) {

		boolean check = AddAccountDB(email, password, access, name, picture, otherInfo);
		if (check == false) {
			user_id = null;
			user_access = null;
			user_name = null;
			user_picture = null;
			return;
		}
		String[] data = FindAccount(email, password);

		user_id = data[0];
		user_name = data[1];
		user_email = data[2];
		user_access = data[3];
		user_picture = data[4];

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
	}

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
						data[4] = rsVendor.getString("vendor_profile");
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

	private static boolean AddAccountDB(String email, String password, String access, String name, String picture,
			String[] otherInfo) {

		// Return Check
		boolean check = false;

		// Check if email exist in db
		if (CheckEmailDB(email) == true) {
			return check;
		}

		DBUtil.init(jdbcURL, dbUsername, dbPassword);

		// Prevent SQL injection
		email = SQLInjection(email);
		name = SQLInjection(name);
		password = SQLInjection(password);

		// Create and format SQL insert Statement
		String insert = "INSERT INTO user(user_name, user_email, user_password, user_access, LAST_LOGIN) VALUES ('"
				+ name + "' , SHA1('" + email + "'), SHA1('" + password + "'), '" + access + "', NOW())";

		int rowsAffectedUser = DBUtil.execSQL(insert);
		// Validate if insert is 1
		if (rowsAffectedUser != 1) {
			DBUtil.close();
			return check;
		}

		FindAccount(email, password);
		// adding to account types

		String individualTable;
		int rowsAffected;
		String phoneNo;
		String address;
		String allegies;
		String company;
		int menu_id = 0;
		switch (access) {

		case "normal":
			if (otherInfo.length != 3) {
				break;
			}

			phoneNo = otherInfo[0];
			allegies = otherInfo[1];
			address = otherInfo[2];

			phoneNo = SQLInjection(phoneNo);
			allegies = SQLInjection(allegies);
			address = SQLInjection(address);

			individualTable = "INSERT INTO normal (normal_id, normal_phoneNumber, normal_address, normal_profile, normal_allegies) VALUES ('"
					+ user_id + "' ," + phoneNo + ", '" + address + "', " + picture + ", '" + allegies + "');";

			rowsAffected = DBUtil.execSQL(individualTable);

			if (rowsAffected == 1) {
				check = true;
			}

			break;

		case "vendor":
			if (otherInfo.length != 3) {
				break;
			}

			company = otherInfo[0];
			phoneNo = otherInfo[1];
			address = otherInfo[2];

			company = SQLInjection(company);
			phoneNo = SQLInjection(phoneNo);
			address = SQLInjection(address);

			individualTable = "INSERT INTO vendor (vendor_id, vendor_phoneNumber, vendor_companyName, vendor_profile,  vendor_address, menu_id) VALUES ('"
					+ user_id + "' ," + phoneNo + ", '" + company + "', '" + picture + "','" + address + "', " + menu_id
					+ ");";

			rowsAffected = DBUtil.execSQL(individualTable);

			if (rowsAffected == 1) {
				check = true;
			}

			break;
		case "admin":
			if (otherInfo != null) {
				break;
			}

			individualTable = "INSERT INTO admin (admin_id, admin_profile) VALUES ('" + user_id + ",'" + picture
					+ "');";

			rowsAffected = DBUtil.execSQL(individualTable);

			if (rowsAffected == 1) {
				check = true;
			}

			break;
		default:
			// TODO
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
		try {
			while (rs.next()) {
				check = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		DBUtil.close();
		return check;
	} // End of CheckEmailDB

	public static String getMenuCount() {
		int count = 0;
		DBUtil.init(jdbcURL, dbUsername, dbPassword);
		String select = "SELECT DISTINCT menu_id FROM menu_item;";

		ResultSet rs = DBUtil.getTable(select);
		try {
			while (rs.next()) {
				count++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtil.close();
		String no = Integer.toString(count);
		return no;
	}

	public static String getNormalCount() {
		int count = 0;
		DBUtil.init(jdbcURL, dbUsername, dbPassword);
		String select = "SELECT DISTINCT normal_id FROM normal;";

		ResultSet rs = DBUtil.getTable(select);
		try {
			while (rs.next()) {
				count++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtil.close();
		String no = Integer.toString(count);
		return no;
	}

	public static String getUserCount() {
		int count = 0;
		DBUtil.init(jdbcURL, dbUsername, dbPassword);
		String select = "SELECT DISTINCT user_id FROM user;";

		ResultSet rs = DBUtil.getTable(select);
		try {
			while (rs.next()) {
				count++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtil.close();
		String no = Integer.toString(count);
		return no;
	}

	public static String getVendorCount() {
		int count = 0;
		DBUtil.init(jdbcURL, dbUsername, dbPassword);
		String select = "SELECT DISTINCT vendor_id FROM vendor;";

		ResultSet rs = DBUtil.getTable(select);
		try {
			while (rs.next()) {
				count++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtil.close();
		String no = Integer.toString(count);
		return no;
	}

	public static String getItemCount() {
		// todo
		int count = 0;
		DBUtil.init(jdbcURL, dbUsername, dbPassword);
		String select = "SELECT DISTINCT item_id FROM item;";

		ResultSet rs = DBUtil.getTable(select);
		try {
			while (rs.next()) {
				count++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtil.close();
		String no = Integer.toString(count);
		return no;
	}

	public static String getOrderCount() {
		int count = 0;
		DBUtil.init(jdbcURL, dbUsername, dbPassword);
		String select = "SELECT DISTINCT order_id FROM has_order;";

		ResultSet rs = DBUtil.getTable(select);
		try {
			while (rs.next()) {
				count++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtil.close();
		String no = Integer.toString(count);
		return no;
	}

	public static String[][] getAllOrderDetail() {
		DBUtil.init(jdbcURL, dbUsername, dbPassword);

		// SQL statement to retrieve details
		String select = "SELECT DISTINCT * FROM has_order;";
		ResultSet rs = DBUtil.getTable(select);

		// Initialise & assign values to header and data for table
		String[] header = { "UID", "NAME", "ORDER ID", "STATUS", "PREFERENCE", "MENU ID" };
		int orderCount = Integer.parseInt(getOrderCount());
		String[][] data = new String[orderCount + 1][header.length];

		// Set first index of data to header
		data[0] = header;
		int i = 0;
		try {
			while (rs.next()) {
				String[] details = new String[6];

				details[0] = rs.getString("order_id");
				details[1] = rs.getString("order_status");
				details[2] = rs.getString("preference");
				details[3] = rs.getString("normal_id");
				details[4] = rs.getString("menu_id");

				// Retrieve User Name
				String select2 = "SELECT user_name, user_id FROM user WHERE user_id = '" + details[3] + "'";
				ResultSet rs2 = DBUtil.getTable(select2);

				while (rs2.next()) {
					details[5] = rs2.getString("user_name");

					// Assign values based on header info
					if (data[i + 1][0] != details[3] || data[i][0] != details[3]) {
						data[i + 1][0] = details[3];
						data[i + 1][1] = details[5];
						data[i + 1][2] = details[0];
						data[i + 1][3] = details[1];
						data[i + 1][4] = details[2];
						data[i + 1][5] = details[4];
						i++;
					}
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtil.close();
		return data;
	}

	public static String[][] getAllItem() {
		DBUtil.init(jdbcURL, dbUsername, dbPassword);
		String select = "SELECT * FROM item;";

		ResultSet rs = DBUtil.getTable(select);

		int itemCount = Integer.parseInt(getItemCount());
		String[][] AllItem = new String[itemCount][6];

		try {
			int count = 0;
			while (rs.next()) {
				// String[] item = {};
				ArrayList<String> item = new ArrayList<String>();

				item.add(toTitleCase(rs.getString("item_name").strip()));
				item.add(Integer.toString(rs.getInt("item_qty")).strip());
				item.add(toTitleCase(rs.getString("item_description").strip()));
				item.add(toTitleCase((rs.getString("item_dietary").strip())));
				item.add(toTitleCase(rs.getString("item_ingredients").strip()));
				item.add(String.format("%.2f", rs.getDouble("item_price")));

				AllItem[count] = item.toArray(new String[0]);
				count++;
			}
		} catch (SQLException e) {
			AllItem = null;
			e.printStackTrace();
		}

		DBUtil.close();
		return AllItem;
	}

	public static String[] getAllAllegies() {
		DBUtil.init(jdbcURL, dbUsername, dbPassword);
		String select = "SELECT * FROM item;";

		ResultSet rs = DBUtil.getTable(select);
		int ItemCount = Integer.parseInt(getItemCount());

		// Check if there is Item in DB
		if (ItemCount == 0) {
			return null;
		}

		String[] AllegiesList = {};

		try {
			String AllAllegies = "";
			while (rs.next()) {
				AllAllegies += rs.getString("item_dietary").strip() + ",";
			}
			// System.out.println(AllAllegies+" ");
			AllegiesList = AllAllegies.split(",");

			// Remove white spacing
			for (int i = 0; i < AllegiesList.length; i++) {
				AllegiesList[i] = AllegiesList[i].strip().toLowerCase();
			}

			// Remove duplicate
			ArrayList<String> Unique = new ArrayList<String>();

			for (String Allegies : AllegiesList) {
				boolean found = false;

				for (String u : Unique) {
					if (Allegies.equalsIgnoreCase(u) == true) {
						found = true;
						break;
					}
				}

				if (found == false) {
					Unique.add(toTitleCase(Allegies));
				}
			}
			AllegiesList = Unique.toArray(new String[0]);

		} catch (SQLException e) {
			AllegiesList = null;
			e.printStackTrace();
		}

		DBUtil.close();
		return AllegiesList;
	}

	private static String toTitleCase(String givenString) {
		String[] arr = givenString.split(" ");
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < arr.length; i++) {
			sb.append(Character.toUpperCase(arr[i].charAt(0))).append(arr[i].substring(1)).append(" ");
		}
		return sb.toString().trim();
	}
}
