package Test_HelperPackage;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import HelperPackage.DBUtil;

public class SQLTest {

	private static String jdbcURL = "jdbc:mysql://localhost/c206_ga";
	private static String dbUsername = "root";
	private static String dbPassword = "";

	private List<String> tableNames = Arrays.asList("admin", "child", "has_order", "item", "menu_item", "normal",
			"school", "user", "vendor");

	@Test
	public void test() {

		Connection conn;
		Statement statement;
		ResultSet resultSet;

		try {

			conn = DriverManager.getConnection(jdbcURL, dbUsername, dbPassword);

		} catch (SQLException se) {
			System.out.println("SQL Connection Error: " + se.getMessage());
		}
	}

	@Test
	public void testSelectStatement() {
		DBUtil.init(jdbcURL, dbUsername, dbPassword);

		for (String tableName : tableNames) {
			String sqlStr = "SELECT * FROM " + tableName;
			ResultSet rs = DBUtil.getTable(sqlStr);

			assertNotNull(rs);
			try {
				assertTrue(rs.next());
			} catch (SQLException e) {
				fail("Exception while iterating through resultSet of table " + tableName + ": " + e.getMessage());
			}
		}
	}

	@Test
	public void testColumns() {
		DBUtil.init(jdbcURL, dbUsername, dbPassword);

		for (String tableName : tableNames) {
			String sqlStr = "SELECT * FROM " + tableName;
			ResultSet rs = DBUtil.getTable(sqlStr);

			assertNotNull(rs);

			try {
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();

				if (rs.next()) {
					System.out.println("Table: " + tableName);
					for (int i = 1; i <= columnCount; i++) {
						String columnName = metaData.getColumnName(i);
						System.out.println("Checking column: " + columnName);

						assertNotNull("Column " + columnName + " in table " + tableName, rs.getObject(columnName));
					}
				}
			} catch (SQLException e) {
				fail("Exception while testing columns of table " + tableName + ": " + e.getMessage());
			}
		}
	}

	@Test
	public void testIndivColumns() {
		DBUtil.init(jdbcURL, dbUsername, dbPassword);

		String tableName = "admin";

		String sqlStr = "SELECT * FROM " + tableName;
		ResultSet rs = DBUtil.getTable(sqlStr);

		assertNotNull(rs);

		try {
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			rs.next();

			System.out.println("Table: " + tableName);
			for (int i = 1; i <= columnCount; i++) {
				String columnName = metaData.getColumnName(i);
				System.out.println("Checking column: " + columnName);

				assertNotNull("Column " + columnName + " in table " + tableName, rs.getObject(columnName));
			}
		} catch (SQLException e) {
			fail("Exception while testing columns of table " + tableName + ": " + e.getMessage());
		}
	}

	@Test
	public void testInsertAndUpdateChild() {
		DBUtil.init(jdbcURL, dbUsername, dbPassword);

		String insertSql = "INSERT INTO child (child_id, child_name, child_allegies, normal_id) VALUES (29, 'Ainul', 'Bus', 4)";
		int rowsInserted = DBUtil.execSQL(insertSql);
		assertEquals("Insert operation should affect one row", 1, rowsInserted);
		String updateSql = "UPDATE child SET child_name = 'Opp Ainul	', child_allegies = 'Bus' WHERE child_id = 29";
		int rowsUpdated = DBUtil.execSQL(updateSql);
		assertEquals("Update operation should affect one row", 1, rowsUpdated);
		DBUtil.close();
	}

	@Test
	public void testInsertAndUpdateMenuItem() {
		DBUtil.init(jdbcURL, dbUsername, dbPassword);

		String insertSql = "INSERT INTO menu_item (menu_item_id, item_id, menu_id) VALUES (8, 0, 1)";
		int rowsInserted = DBUtil.execSQL(insertSql);
		assertEquals("Insert operation should affect one row", 1, rowsInserted);

		String updateSql = "UPDATE menu_item SET item_id = 4 WHERE menu_item_id = 8";
		int rowsUpdated = DBUtil.execSQL(updateSql);
		assertEquals("Update operation should affect one row", 1, rowsUpdated);

		DBUtil.close();
	}

	@Test
	public void testUpdateNormal() {
		DBUtil.init(jdbcURL, dbUsername, dbPassword);

		String updateSql = "UPDATE normal SET normal_phoneNumber = 995 WHERE normal_id = 5";
		int rowsUpdated = DBUtil.execSQL(updateSql);
		assertEquals("Update operation should affect one row", 1, rowsUpdated);

		DBUtil.close();
	}

}
