package StartPage;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.regex.Pattern;
import HelperPackage.Authentication;
import HelperPackage.DBData;
import HelperPackage.FXHelper;
import HelperPackage.NavBar;
import javafx.scene.control.ToggleGroup;

public class Registration extends Application {

	// Create Box by Entire screen, Main screen, button area
	private HBox vbPaneEntire = new HBox();
	private VBox vbPaneMain = new VBox();
	private VBox vbPaneNormal = new VBox();
	private VBox vbPaneAdmin = new VBox();
	private VBox vbPaneVendor = new VBox();
	private VBox vbPaneAccountSelection = new VBox();

	private HBox hbPane = new HBox();

	// Create label display to be displayed on top of GUI
	private Label lbChoice1 = new Label("Choose An Account Type");
	private Label lbRegister1 = new Label("Register an Account");
	private Label lbRegister2 = new Label("Please enter your details to start");

	// Create respective label to ask user for input data
	private Label lbName = new Label("Enter name: ");
	private Label lbEmail = new Label("Enter email: ");
	private Label lbPassword1 = new Label("Enter password: ");
	private Label lbPassword2 = new Label("Confirm password: ");
	private Label lbPhoneNumber = new Label("Enter phone number: ");
	private Label lbAddress = new Label("Enter address: ");
	private Label lbAllegies = new Label("Enter allegies: ");
	private Label lbCompanyName = new Label("Enter company name: ");

	// Create label to be displayed back to user based on certain conditions
	private static Label lbRepsonse = new Label("");

	// Create respective textbox to collect user input data
	private static TextField tfName = new TextField();
	private static TextField tfEmail = new TextField();
	private static TextField tfPassword1 = new TextField();
	private static TextField tfPassword2 = new TextField();
	private static TextField tfPhoneNumber = new TextField();
	private static TextField tfAddress = new TextField();
	private static TextField tfAllegies = new TextField();
	private static TextField tfCompanyName = new TextField();
	private RadioButton rbNormal = new RadioButton("Normal");
	private RadioButton rbAdmin = new RadioButton("Admin");
	private RadioButton rbVendor = new RadioButton("Vendor");
	private Button btSelectAccess = new Button("Select Access Type");
	// Create button to be clicked by user upon filling all textfield
	private Button btCreate = new Button("Create Account");

	// Basic setting of the class
	private String title = "Registration Window";
	private String stylebt = "-fx-background-color: blue; -fx-text-fill: white;";
	private String stylelb = "-fx-font: 20 arial;";
	private static String responseError = "-fx-text-fill: red;";
	private int MaxWidthTF = 200;

	public static void main(String[] args) {
		launch(args);
	} // End of Main

	// new method
	private ToggleGroup accessTypeToggleGroup = new ToggleGroup();

	@SuppressWarnings("exports")
	public void Register(Stage primaryStage) {
		// Add radio buttons to the toggle group
		rbNormal.setToggleGroup(accessTypeToggleGroup);
		rbAdmin.setToggleGroup(accessTypeToggleGroup);
		rbVendor.setToggleGroup(accessTypeToggleGroup);

		vbPaneAccountSelection.setAlignment(Pos.CENTER);
		vbPaneAccountSelection.setSpacing(20);
		vbPaneAccountSelection.getChildren().addAll(lbChoice1, rbNormal, rbAdmin, rbVendor, btSelectAccess);
		vbPaneEntire.getChildren().addAll(NavBar.navBarStart(primaryStage), vbPaneAccountSelection);

		Scene choices = new Scene(vbPaneMain);

		FXHelper.loadStage(primaryStage, choices, title, 500, 500);

		/*
		 * // Set up the main scene for the registration form
		 * vbPaneMain.setAlignment(Pos.CENTER); vbPaneMain.setSpacing(20);
		 * vbPaneMain.getChildren().addAll(lbRegister1, lbRegister2); // Set default
		 * selection to Normal rbNormal.setSelected(true);
		 * 
		 * // Add radio buttons and button to the main content area
		 * vbPaneMain.getChildren().addAll(lbChoice1, rbNormal, rbAdmin, rbVendor,
		 * btSelectAccess);
		 */

		EventHandler<ActionEvent> handleAccessSelection = (ActionEvent e) -> {
			// Check if user has selected an access type
			if (checkAccessType()) {
				// Hide the main pane and display the corresponding access type pane
				vbPaneMain.setVisible(false);
				if (rbNormal.isSelected()) {
					vbPaneNormal.setVisible(true);
				} else if (rbAdmin.isSelected()) {
					vbPaneAdmin.setVisible(true);
				} else if (rbVendor.isSelected()) {
					vbPaneVendor.setVisible(true);
				}
			}
		};
		btSelectAccess.setOnAction(handleAccessSelection);

		Scene accountSelectionScene = new Scene(vbPaneAccountSelection, 500, 500);
		primaryStage.setScene(accountSelectionScene);
		primaryStage.setTitle("Account Type Selection");
		primaryStage.show();
	}

	@SuppressWarnings("exports")
	public void RegisterNormal(Stage primaryStage) {
		// Setting up the horizontal box for button area
		hbPane.setSpacing(10);
		hbPane.setAlignment(Pos.BASELINE_CENTER);

		// Styling the "Create Account" button
		btCreate.setStyle(stylebt);

		// Styling the "Register an Account" label
		lbRegister1.setStyle(stylelb);

		// Setting up the vertical box for the main content area
		vbPaneMain.setSpacing(10);
		vbPaneMain.setPadding(new Insets(10, 10, 10, 10));
		vbPaneMain.setAlignment(Pos.BASELINE_CENTER);

		// Adding all the necessary elements to the main content area
		vbPaneNormal.getChildren().addAll(lbRegister1, lbRegister2, lbName, tfName, lbEmail, tfEmail, lbPhoneNumber,
				tfPhoneNumber, lbAllegies, tfAllegies, lbAddress, tfAddress, lbPassword1, tfPassword1, lbPassword2,
				tfPassword2, lbCompanyName, tfCompanyName, btCreate, hbPane, lbRepsonse);

		// Setting the maximum width for the text fields
		tfName.setMaxWidth(MaxWidthTF);
		tfEmail.setMaxWidth(MaxWidthTF);
		tfPassword1.setMaxWidth(MaxWidthTF);
		tfPassword2.setMaxWidth(MaxWidthTF);

		// Adding the main content area and navigation bar to the entire horizontal box
		vbPaneEntire.getChildren().addAll(NavBar.navBarStart(primaryStage), vbPaneMain);

		Scene register = new Scene(vbPaneEntire);

		// Initialize stage
		FXHelper.loadStage(primaryStage, register, title, 500, 500);

		// Add event for response
		EventHandler<ActionEvent> handleResponse = (ActionEvent e) -> {

			// Check if user input is valid
			if (checkFields() == true) {

				// Inputs are collected and are stored within respective strings
				String name = tfName.getText();
				String email = tfEmail.getText();
				String password = tfPassword1.getText();
				String address = tfAddress.getText();
				String allegies = tfAllegies.getText();
				String phoneNumber = tfPhoneNumber.getText();

				String[] Data = { name, email, password, address, allegies, phoneNumber, };
				// Check if access is create by checking if it is empty
				DBData Credential = Authentication.CreateAccountNormal(Data);

				String access_type = Credential.getUser_access();
				if (Credential != null && access_type != null) {
					primaryStage.close();
					(new HomePage.NormalUser()).startCredential(Credential);
				}
			}

		};
		btCreate.setOnAction(handleResponse);

	}

	@SuppressWarnings("exports")
	public void RegisterVendor(Stage primaryStage) {
		// Setting up the horizontal box for button area
		hbPane.setSpacing(10);
		hbPane.setAlignment(Pos.BASELINE_CENTER);

		// Styling the "Create Account" button
		btCreate.setStyle(stylebt);

		// Styling the "Register an Account" label
		lbRegister1.setStyle(stylelb);

		// Setting up the vertical box for the main content area
		vbPaneMain.setSpacing(10);
		vbPaneMain.setPadding(new Insets(10, 10, 10, 10));
		vbPaneMain.setAlignment(Pos.BASELINE_CENTER);

		// Adding all the necessary elements to the main content area
		vbPaneVendor.getChildren().addAll(lbRegister1, lbRegister2, lbName, tfName, lbEmail, tfEmail, lbPassword1,
				tfPassword1, lbPassword2, tfPassword2, btCreate, hbPane, lbRepsonse);

		// Setting the maximum width for the text fields
		tfName.setMaxWidth(MaxWidthTF);
		tfEmail.setMaxWidth(MaxWidthTF);
		tfPassword1.setMaxWidth(MaxWidthTF);
		tfPassword2.setMaxWidth(MaxWidthTF);

		// Adding the main content area and navigation bar to the entire horizontal box
		vbPaneEntire.getChildren().addAll(NavBar.navBarStart(primaryStage), vbPaneMain);

		Scene register = new Scene(vbPaneEntire);

		// Initialize stage
		FXHelper.loadStage(primaryStage, register, title, 500, 500);

		// Add event for response
		EventHandler<ActionEvent> handleResponse = (ActionEvent e) -> {

			// Check if user input is valid
			if (checkFields() == true) {

				// Inputs are collected and are stored within respective strings
				String name = tfName.getText();
				String email = tfEmail.getText();
				String password = tfPassword1.getText();

				// Check if access is create by checking if it is empty
				DBData Credential = Authentication.CreateAccountVendor(name, email, password);

				String access_type = Credential.getUser_access();
				if (Credential != null && access_type != null) {
					primaryStage.close();
					(new HomePage.NormalUser()).startCredential(Credential);
				}
			}

		};
		btCreate.setOnAction(handleResponse);

	}

	@SuppressWarnings("exports")
	public void RegisterAdmin(Stage primaryStage) {
		// Setting up the horizontal box for button area
		hbPane.setSpacing(10);
		hbPane.setAlignment(Pos.BASELINE_CENTER);

		// Styling the "Create Account" button
		btCreate.setStyle(stylebt);

		// Styling the "Register an Account" label
		lbRegister1.setStyle(stylelb);

		// Setting up the vertical box for the main content area
		vbPaneMain.setSpacing(10);
		vbPaneMain.setPadding(new Insets(10, 10, 10, 10));
		vbPaneMain.setAlignment(Pos.BASELINE_CENTER);

		// Adding all the necessary elements to the main content area
		vbPaneAdmin.getChildren().addAll(lbRegister1, lbRegister2, lbName, tfName, lbEmail, tfEmail, lbPassword1,
				tfPassword1, lbPassword2, tfPassword2, btCreate, hbPane, lbRepsonse);

		// Setting the maximum width for the text fields
		tfName.setMaxWidth(MaxWidthTF);
		tfEmail.setMaxWidth(MaxWidthTF);
		tfPassword1.setMaxWidth(MaxWidthTF);
		tfPassword2.setMaxWidth(MaxWidthTF);

		// Adding the main content area and navigation bar to the entire horizontal box
		vbPaneEntire.getChildren().addAll(NavBar.navBarStart(primaryStage), vbPaneMain);

		Scene register = new Scene(vbPaneEntire);

		// Initialize stage
		FXHelper.loadStage(primaryStage, register, title, 500, 500);

		// Add event for response
		EventHandler<ActionEvent> handleResponse = (ActionEvent e) -> {

			// Check if user input is valid
			if (checkFields() == true) {

				// Inputs are collected and are stored within respective strings
				String name = tfName.getText();
				String email = tfEmail.getText();
				String password = tfPassword1.getText();

				// Check if access is create by checking if it is empty
				DBData Credential = Authentication.CreateAccountAdmin(name, email, password);

				String access_type = Credential.getUser_access();
				if (Credential != null && access_type != null) {
					primaryStage.close();
					(new HomePage.NormalUser()).startCredential(Credential);
				}
			}

		};
		btCreate.setOnAction(handleResponse);

	}

	@SuppressWarnings("exports")
	public void start(Stage primaryStage) {
		(new MainStart()).start(new Stage());
	} // End of Start

	/**
	 * Method return boolean value based on whether user input data is valid
	 * 
	 * @return true if all fields are valid
	 */
	private static boolean checkFields() {

		lbRepsonse.setStyle(responseError);
		boolean check = false;

		// Retrieve input data from textfield
		String name = tfName.getText();
		String email = tfEmail.getText();
		String password1 = tfPassword1.getText();
		String password2 = tfPassword2.getText();

		// Confirm mandatory fields are filled out
		if (name.isEmpty() && email.isEmpty() && password1.isEmpty() && password2.isEmpty()) {
			check = ResponseReturn("All field cannot be left blank.");
		}

		else if (name.isEmpty()) {
			check = ResponseReturn("The field Name cannot be left blank. You must enter in a name");
		}

		else if (isName(name) == false) {
			check = ResponseReturn("Name must contain only alphabetic characters.");
		}

		else if (email.isEmpty()) {
			check = ResponseReturn("The field Email cannot be left blank. You must enter in an email");
		}

		else if (isEmail(email) == false) {
			check = ResponseReturn("Invalid email format. Please enter a valid email address.");
		}

		else if (password1.isEmpty()) {
			check = ResponseReturn("The field Password cannot be left blank. You must enter in a password");
		}

		else if (isPassword(password1) == false) {
			check = ResponseReturn(
					"Password must include at least one capital letter and be at least 8 characters long.");
		}

		else if (password2.isEmpty()) {
			check = ResponseReturn("The field Confirm Password cannot be left blank. You must enter in a password");
		}

		else if (isPassword(password2) == false) {
			check = ResponseReturn(
					"Password must include at least one capital letter and be at least 8 characters long.");
		}

		else if (password1.equals(password2) == false) {
			check = ResponseReturn("Password entered must be the same.");
		}

		else {
			ResponseReturn("");
			check = true;
		}

		return check;
	} // End of CheckFields

	/**
	 * Method set text of lbResponse by response
	 * 
	 * @param response
	 * @return false by default
	 */
	private static boolean ResponseReturn(String response) {
		lbRepsonse.setText(response);
		return false;
	} // End of ResponseReturn

	/**
	 * Method is about validating name by checking if name is all in alphabetical
	 * 
	 * @param name
	 * @return true if name matches
	 */
	private static boolean isName(String name) {

		// Match name using RegEx
		String pattern = "[a-zA-Z]+";

		boolean matched = Pattern.matches(pattern, name);
		return matched;
	} // End of isName

	/**
	 * Method is about validating email by checking if email contains @ and .
	 * 
	 * @param email
	 * @return true if email matches
	 */
	private static boolean isEmail(String email) {

		// Match email using RegEx
		String pattern = "[a-zA-Z0-9]+@[a-zA-Z]+.[a-zA-Z]+";

		boolean matched = Pattern.matches(pattern, email);
		return matched;
	} // End of isEmail

	/**
	 * Method is about validating the password strength using regex.
	 * 
	 * @param password
	 * @return true if password is strong (at least one capital letter and at least
	 *         8 characters long)
	 */
	private static boolean isPassword(String password) {

		// Match password using RegEx
		String pattern = "^(?=.*[A-Z]).{8,}$";

		boolean matched = Pattern.matches(pattern, password);
		return matched;
	} // End of isPassword

	private boolean checkAccessType() {
		if (accessTypeToggleGroup.getSelectedToggle() == null) {
			lbRepsonse.setText("Please select an access type (Normal, Admin, or Vendor).");
			return false;
		}

		// Clear any previous error messages
		lbRepsonse.setText("");
		return true;
	}

}
