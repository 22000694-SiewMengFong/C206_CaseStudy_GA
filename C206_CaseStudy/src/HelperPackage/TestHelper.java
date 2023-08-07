package HelperPackage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestHelper {

	public static void main(String[] args) {
		

		String jdbcURL = "jdbc:mysql://localhost/c206_ga";
		String dbUsername = "root";
		String dbPassword = "";

		String email = "Test@email";
		String name = "Test";
		String password = "Test1";

		DBUtil.init(jdbcURL, dbUsername, dbPassword);

		// Return login info - user id and access type
		String[] LoginInfo = new String[2];

		// Check if email exist in db

		// Create and format SQL insert Statement
		//String insert = "INSERT INTO user(user_name, user_email, user_password, user_picture, user_access) VALUES ('"
		//		+ name + "' , '" + email + "', SHA1('" + password + "'), NULL, 'normal' );";

		int rowsAffected = 1;//DBUtil.execSQL(insert);

		// Validate if insert is 1
		if (rowsAffected == 1) {
			String select = "SELECT * FROM `user` WHERE `user_email` = '" + email + "' AND `user_password` = SHA1('"
					+ password + "');";

			ResultSet rs = DBUtil.getTable(select);
			// Check if Email exist and that the password match
			try {
				if (rs != null) {
					while (rs.next()) {
						System.out.println(rs.getString("user_id"));
					}
				} 
			} catch (SQLException e) {
				e.printStackTrace();
			}

			
			LoginInfo[1] = "normal";
		}

		DBUtil.close();
	}

}
