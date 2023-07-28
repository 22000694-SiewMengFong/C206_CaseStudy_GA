package StartPage;

import HelperPackage.FXHelper;
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

public class Login extends Application {

	// Create Box by Entire screen, Main screen, button area
	private HBox vbPaneEntire = new HBox();
	private VBox vbPaneMain = new VBox();
	private HBox hbPane = new HBox();

	// Create label display to be displayed on top of GUI
	private Label lbLogin1 = new Label("Login to Account");
	private Label lbLogin2 = new Label("Please enter your details to login");
	private Label lbEmail = new Label("Enter email: ");
	private Label lbPassword = new Label("Enter password: ");
	private static Label lbRepsonse = new Label("");

	// Create textfield to store user input data
	private static TextField tfEmail = new TextField();
	private static TextField tfPassword = new TextField();

	// Create button for user to click to login
	private Button btLogin = new Button("Login");

	// Basic setting of the class
	private String title = "Login Window";
	private String stylebt = "-fx-background-color: blue; -fx-text-fill: white;";
	private String stylelb = "-fx-font: 20 arial;";
	private static String responseError = "-fx-text-fill: red;";
	private int MaxWidthTF = 200;

	public static void main(String[] args) {
		launch(args);
	} // End of main

	@SuppressWarnings("exports")
	public void start(Stage primaryStage) {
		// Setting up the horizontal box for button area
		hbPane.setSpacing(10);
		hbPane.setAlignment(Pos.BASELINE_CENTER);

		// Styling the "Create Account" button
		btLogin.setStyle(stylebt);

		// Styling the "Register an Account" label
		lbLogin1.setStyle(stylelb);

		// Setting up the vertical box for the main content area
		vbPaneMain.setSpacing(10);
		vbPaneMain.setPadding(new Insets(10, 10, 10, 10));
		vbPaneMain.setAlignment(Pos.BASELINE_CENTER);
		vbPaneMain.getChildren().addAll(lbLogin1, lbLogin2, lbEmail, tfEmail, lbPassword, tfPassword, btLogin, hbPane,
				lbRepsonse);
		tfEmail.setMaxWidth(MaxWidthTF);
		tfPassword.setMaxWidth(MaxWidthTF);

		// Adding the main content area and navigation bar to the entire horizontal box
		vbPaneEntire.getChildren().addAll(NavBar.navBarStart(primaryStage), vbPaneMain);
		Scene login = new Scene(vbPaneEntire);

		// Initialize stage
		FXHelper.loadStage(primaryStage, login, title);

		// Add event for login
		EventHandler<ActionEvent> handleLogin = (ActionEvent e) -> {

			if (checkFields() == true) {
				String email = tfEmail.getText();
				String password = tfPassword.getText();
				if (Authentication.LoginAccount(email, password) == true) {
					// TODO Move user to user's page (DONT DO YET)
					ResponseReturn("Sccue");
				} else {
					ResponseReturn("Email or Password is invalid. Try Again.");
				}
			}
		};
		btLogin.setOnAction(handleLogin);
	} // End of start

	/**
	 * Method checkFields check if all given fields is valid
	 * 
	 * @return
	 */
	private static boolean checkFields() {
		lbRepsonse.setStyle(responseError);

		String email = tfEmail.getText();
		String password = tfPassword.getText();

		boolean check = false;

		// Confirm mandatory fields are filled out
		if (email.isEmpty() && password.isEmpty()) {
			check = ResponseReturn("All field cannot be left blank.");
		}

		else if (email.isEmpty()) {
			check = ResponseReturn("The field Email cannot be left blank. You must enter an email");
		}

		else if (password.isEmpty()) {
			check = ResponseReturn("The field Password cannot be left blank. You must enter a password");
		}

		else {
			ResponseReturn("");
			check = true;
		}

		return check;
	} // End of checkFields

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
} // End of class
