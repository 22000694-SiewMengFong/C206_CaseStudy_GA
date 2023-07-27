package StartPage;

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

public class Registration extends Application {

	// Create Box by Entire screen, Main screen, button area
	private HBox vbPaneEntire = new HBox();
	private VBox vbPaneMain = new VBox();
	private HBox hbPane = new HBox();

	private Label lbRegister1 = new Label("Register an Account");
	private Label lbRegister2 = new Label("Please enter you details to start");

	private Label lbName = new Label("Enter name: ");
	private Label lbEmail = new Label("Enter email: ");
	private Label lbPassword1 = new Label("Enter password: ");
	private Label lbPassword2 = new Label("Confirm password: ");

	private Label lbRepsonse = new Label("");

	private TextField tfName = new TextField();
	private TextField tfEmail = new TextField();
	private TextField tfPassword1 = new TextField();
	private TextField tfPassword2 = new TextField();

	private Button btCreate = new Button("Create Account");

	private String title = "Registration Window";
	private String style = "-fx-background-color: blue; -fx-text-fill: white;";
	private String responseError = "-fx-text-fill: red;";
	private String responseGood = "-fx-text-fill: green;";
	private int MaxWidthTF = 200;

	public static void main(String[] args) {
		launch(args);
	}

	@SuppressWarnings("exports")
	public void start(Stage primaryStage) {
		hbPane.setSpacing(10);
		hbPane.setAlignment(Pos.BASELINE_CENTER);

		btCreate.setStyle(style);
		lbRegister1.setStyle("-fx-font: 20 arial;");

		vbPaneMain.setSpacing(10);
		vbPaneMain.setPadding(new Insets(10, 10, 10, 10));
		vbPaneMain.setAlignment(Pos.BASELINE_CENTER);
		vbPaneMain.getChildren().addAll(lbRegister1, lbRegister2, lbName, tfName, lbEmail, tfEmail, lbPassword1,
				tfPassword1, lbPassword2, tfPassword2, btCreate, hbPane, lbRepsonse);

		tfName.setMaxWidth(MaxWidthTF);
		tfEmail.setMaxWidth(MaxWidthTF);
		tfPassword1.setMaxWidth(MaxWidthTF);
		tfPassword2.setMaxWidth(MaxWidthTF);

		vbPaneEntire.getChildren().addAll(NavBar.navBarStart(primaryStage), vbPaneMain);

		Scene register = new Scene(vbPaneEntire);

		FXHelper.loadStage(primaryStage, register, title, 500, 500);

		EventHandler<ActionEvent> handleResponse = (ActionEvent e) -> {
			if(checkFields()) {
				//TODO SQL add in authenticate class
				
				if (Authentication.CreateAccount()) {
					lbRepsonse.setStyle(responseGood);
					lbRepsonse.setText("Account Creation Successful");
				}
			}
		};
		btCreate.setOnAction(handleResponse);

	}

	private boolean checkFields() {
		lbRepsonse.setStyle(responseError);

		// Confirm mandatory fields are filled out
		if (tfName.getText().isEmpty()) {
			lbRepsonse.setText("The field cannot be left blank. You must enter in a name");
			return false;
		}

		if (lbEmail.getText().isEmpty()) {
			lbRepsonse.setText("The field cannot be left blank. You must enter in a email");
			return false;
		}

		if (tfPassword1.getText().isEmpty()) {
			lbRepsonse.setText("The field cannot be left blank. You must enter in a password");
			return false;
		}

		if (tfPassword2.getText().isEmpty()) {
			lbRepsonse.setText("The field cannot be left blank. You must enter in a password");
			return false;
		}

		if (tfPassword1.getText().equals(tfPassword2.getText())) {
			lbRepsonse.setText("Password entered must be the same.");
			return false;
		}

		//TODO Name is all in alpha
		//TODO Email is right format
		//TODO Password is strong (OPTIONAL) - regex
		return true;
	}

}
