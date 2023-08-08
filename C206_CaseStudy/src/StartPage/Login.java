package StartPage;

import HelperPackage.Authentication;
import HelperPackage.DBData;
import HelperPackage.FXHelper;
import HelperPackage.NavBar;
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
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

//TODO Link user to HomePage base on their user_access type (NOT YET)

public class Login extends Application {

	// Create Box by Entire screen, Main screen, button area
	private BorderPane bpPane = new BorderPane();
	// private HBox vbPaneEntire = new HBox();
	private VBox vbPaneMain = new VBox();
	private HBox hbPane = new HBox();

	// Create label display to be displayed on top of GUI
	private Label lbLogin1 = new Label("Login to Account");
	private Label lbLogin2 = new Label("Please enter your details to login");
	private Label lbEmail = new Label("Enter email: ");
	private Label lbPassword = new Label("Enter password: ");
	private static Label lbRepsonse = new Label("");

	// Create textfield to store user input data
	private static TextField tfEmail = new TextField("normal1@normal1");
	private static TextField tfPassword = new TextField("normal1");

	// Create button for user to click to login
	private Button btLogin = new Button("Login");

	// Basic setting of the class
	private String title = "Login Window";
	private String stylebt = "-fx-background-color: blue; -fx-text-fill: white;";
	private String stylelb = "-fx-font: 20 arial;";
	private static String responseError = "-fx-text-fill: red;";
	private int MaxWidthTF = 200;

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
		vbPaneMain.setAlignment(Pos.CENTER);
		vbPaneMain.getChildren().addAll(lbLogin1, lbLogin2, lbEmail, tfEmail, lbPassword, tfPassword, btLogin, hbPane,
				lbRepsonse);
		tfEmail.setMaxWidth(MaxWidthTF);
		tfPassword.setMaxWidth(MaxWidthTF);

		// Adding the main content area and navigation bar to the entire horizontal box
		bpPane.setLeft(NavBar.navBarStart(primaryStage));
		bpPane.setCenter(vbPaneMain);

		Scene login = new Scene(bpPane);

		// Initialize stage
		FXHelper.loadStage(primaryStage, login, title);

		// Add event for login
		EventHandler<ActionEvent> handleLogin = (ActionEvent e) -> {

			if (checkFields() == true) {
				String email = tfEmail.getText();
				String password = tfPassword.getText();

				DBData Credential = Authentication.LoginAccount(email, password);
				if (Credential != null && Credential.getUser_id() != null) {

					String access = Credential.getUser_access();

					switch (access) {
					case "normal":
						primaryStage.close();
						(new HomePage.NormalUser()).startCredential(Credential);
						break;
					case "vendor":
						primaryStage.close();
						(new HomePage.VendorUser()).startCredential(Credential);
						break;
					case "admin":
						primaryStage.close();
						(new HomePage.AdminUser()).startCredential(Credential);
						break;
					default:
						ResponseReturn("Something went wrong. Authentication failed.");

					}
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
