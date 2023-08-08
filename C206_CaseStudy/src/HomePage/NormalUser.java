package HomePage;

import HelperPackage.DBData;
import HelperPackage.FXHelper;
import HelperPackage.NavBar;
import StartPage.MainStart;
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

public class NormalUser extends Application {

	private static DBData credential;

	// Create Box by Entire screen, Main screen, button area
	private BorderPane bpPane = new BorderPane();
	private VBox vbPaneMain = new VBox();
	private HBox hbPane = new HBox();

	// Create label display to be displayed on top of GUI
	private Label lbRegister1 = new Label("Register an Account");
	private Label lbRegister2 = new Label("Please enter your details to start");

	// Create respective label to ask user for input data
	private Label lbName = new Label("Enter name: ");
	private Label lbEmail = new Label("Enter email: ");
	private Label lbPassword1 = new Label("Enter password: ");
	private Label lbPassword2 = new Label("Confirm password: ");

	// Create label to be displayed back to user based on certain conditions
	private static Label lbRepsonse = new Label("");

	// Create respective textbox to collect user input data
	private static TextField tfName = new TextField();
	private static TextField tfEmail = new TextField();
	private static TextField tfPassword1 = new TextField();
	private static TextField tfPassword2 = new TextField();

	// Create button to be clicked by user upon filling all textfield
	private Button btCreate = new Button("Create Account");

	// Basic setting of the class
	private String title = "Parent Window";
	private String stylebt = "-fx-background-color: blue; -fx-text-fill: white;";
	private String stylelb = "-fx-font: 20 arial;";
	private int MaxWidthTF = 200;

	/*
	 * 
	 * public static void main(String[] args) { launch(args); } // End of main
	 * 
	 */

	public void startCredential(DBData Credential) {
		credential = Credential;
		start(new Stage());
	}

	@SuppressWarnings("exports")
	public void start(Stage primaryStage) {
		credential = new DBData("normal1@normal1", "normal1");
		if (credential == null) {
			String error = "No Credential Found While Logging In";
			(new MainStart()).startReturn(error);
			return;
		}

		// Setting up the horizontal box for button area
		hbPane.setSpacing(10);
		hbPane.setAlignment(Pos.CENTER);

		// Styling the "Create Account" button
		btCreate.setStyle(stylebt);

		// Styling the "Register an Account" label
		lbRegister1.setStyle(stylelb);

		// Setting up the vertical box for the main content area
		vbPaneMain.setSpacing(10);
		vbPaneMain.setPadding(new Insets(10, 10, 10, 10));
		vbPaneMain.setAlignment(Pos.CENTER);

		// Adding all the necessary elements to the main content area
		vbPaneMain.getChildren().addAll(lbRegister1, lbRegister2, lbName, tfName, lbEmail, tfEmail, lbPassword1,
				tfPassword1, lbPassword2, tfPassword2, btCreate, hbPane, lbRepsonse);

		// Setting the maximum width for the text fields
		tfName.setMaxWidth(MaxWidthTF);
		tfEmail.setMaxWidth(MaxWidthTF);
		tfPassword1.setMaxWidth(MaxWidthTF);
		tfPassword2.setMaxWidth(MaxWidthTF);

		// Adding the main content area and navigation bar to the entire horizontal box
		bpPane.setLeft(NavBar.navBarHomeNormal(primaryStage));
		bpPane.setCenter(vbPaneMain);

		Scene register = new Scene(bpPane);

		// Initialize stage
		FXHelper.loadStage(primaryStage, register, title, 500, 500);

		// Add event for response
	}

	@SuppressWarnings("exports")
	public void PlaceOrder(Stage primaryStage) {

	}

	@SuppressWarnings("exports")
	public void TrackOrder(Stage primaryStage) {

	}

	@SuppressWarnings("exports")
	public void Setting(Stage primaryStage) {

	}

}