package HelperPackage;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class FXHelper {

	@SuppressWarnings("exports")
	public static void loadStage(Stage stage, Scene scene, String title, int Width, int height) {
		stage.setScene(scene);
		stage.setTitle(title);
		stage.setWidth(Width);
		stage.setHeight(height);
		stage.show();
	}

	@SuppressWarnings("exports")
	public static void loadStage(Stage stage, Scene scene, String title) {
		loadStage(stage, scene, title, 600, 500);
	}

	private static String centerStr(int columnWidth, String str) {

		// Prevent null operation
		if (str == null) {
			str = "";
		}

		// Calculate the left and right padding sizes
		int leftPadding = (int) Math.ceil((columnWidth - str.length()) / 2.0);
		int rightPadding = columnWidth - leftPadding;

		if (leftPadding == 0) {
			// If left padding is 0, just add right padding
			return String.format("%" + rightPadding + "s|", str);
		}

		// Add left and right padding
		return String.format("%" + leftPadding + "s%-" + rightPadding + "s|", "", str);
	} // End of centerStr

	/**
	 * Creates a separator row for a table using the given column widths.
	 *
	 * @param columnsWidth the widths of the columns in the table
	 * @return the separator row
	 */
	private static String createSeparator(int[] columnsWidth) {
		String seperator = "";

		// Create a separator for each columns in table
		for (int length : columnsWidth) {

			// Replace all blanks as "-" using length to create the blanks
			seperator += "+" + String.format("%" + length + "s", " ").replaceAll(" ", "-");
		}

		return seperator + "+";
	} // End of createSeperator

	public static String tableFormatter(String[][] data) {

		// Add additional padding for anesthetics
		int cellPadding = 6;

		// Calculate the widths of the columns based on the maximum length of the
		// strings in each column

		int[] columnsWidth = new int[data[0].length];

		// Looks into the 2nd array of data (Columns are)
		for (String[] info : data) {

			// Get the length of each columns by finding the max length value while also
			// assigning padding
			for (int i = 0; i < info.length; i++) {
				// Check for null
				if (info[i] == null) {
					columnsWidth[i] = Math.max(columnsWidth[i], cellPadding);
					continue;
				}

				columnsWidth[i] = Math.max(columnsWidth[i], info[i].length() + cellPadding);
			}
		}

		// Create the separator row
		String separator = createSeparator(columnsWidth);

		// Format the table

		// Initialize table (return), the +3 is for separator
		String[] table = new String[data.length + 3];

		table[0] = separator; // Add separator before header
		table[2] = separator; // Add separator after header
		table[table.length - 1] = separator; // Add separator after data rows

		// Insert data rows into the table
		dataLoop: for (String[] info : data) {

			for (int row = 0; row < table.length; row++) {

				// Find the next empty row in the table
				if (table[row] != null) {
					continue;
				}

				table[row] = "|";
				// Add the centered data for each column to the table row
				for (int column = 0; column < info.length; column++) {
					table[row] += centerStr(columnsWidth[column], info[column]);
				}
				continue dataLoop;
			}
		}

		// Join the rows of the table into a single string, separated by newlines
		return String.join("\n", table);
	} // End of tableFormator
}