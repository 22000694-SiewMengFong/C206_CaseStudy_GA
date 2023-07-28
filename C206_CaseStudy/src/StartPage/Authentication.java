package StartPage;

import java.sql.ResultSet;
import java.sql.SQLException;

import GA.DBUtil;
import GA.Helper;

public class Authentication {

	public static boolean CreateAccount() {
		// TODO Check if account is already in sql by email. Using CheckDB
		// TODO prevent SQL Injection

		// ERROR: SQL insert does not work - Invalid field name for password - 
		//SQL database is in hexadecimal while input is String - Not Match - DM Darrel for screenshot
		// for screenshot
		String email = Helper.readString("Enter your email > ");
		email = SQLInjection(email);
		if (!CheckDB(email)) {
			String name = Helper.readString("Enter your user name > ");
			name = SQLInjection(name);
			String password = Helper.readString("Enter your password > ");
			password = SQLInjection(password);

			String insert = "INSERT INTO user(user_name, user_email, user_password) " + "VALUES ('" + name + "' , '"
					+ email + "', " + password + ")";
			int rowsAffected = DBUtil.execSQL(insert);
			// validate
			if (rowsAffected == 1) {
				System.out.println("Account Created!");
				return true;
			}
		}
		return false;
	}

	public static boolean LoginAccount(String email, String password) {
		// TODO Check if account email and password is correct
		// TODO prevent SQL Injection by email and password
		// check email:

		// NOTE: URL may be different depending on the name of the database
		// ERROR: Password Not able to be matched - SQL database is in hexadecimal while input is String - Not Match
		email = SQLInjection(email);
		password = SQLInjection(password);

		String jdbcURL = "jdbc:mysql://localhost/c206_ga";
		String dbUsername = "root";
		String dbPassword = "";
		DBUtil.init(jdbcURL, dbUsername, dbPassword);

		String sql = "SELECT * FROM user";
		ResultSet rs = DBUtil.getTable(sql);

		try {
			while (rs.next()) {
				String sqlEmail = rs.getString("user_email");
				String sqlPass = rs.getString("user_password");
				if (sqlEmail.equals(email) && sqlPass.equals(password)) {
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	private static String SQLInjection(String x) {
		// TODO Prevent SQL injection of string
		x.replace("'", "''");
		return x;
	}

	private static boolean CheckDB(String email) {
		// TODO Looks at DB and check if email is found

		// NOTE: URL may be different depending on the name of the database
		String jdbcURL = "jdbc:mysql://localhost/c206_ga";
		String dbUsername = "root";
		String dbPassword = "";
		DBUtil.init(jdbcURL, dbUsername, dbPassword);

		String sql = "SELECT * FROM user";
		ResultSet rs = DBUtil.getTable(sql);
		try {
			while (rs.next()) {
				String sqlEmail = rs.getString("user_email");
				if (sqlEmail.equals(email)) {
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
