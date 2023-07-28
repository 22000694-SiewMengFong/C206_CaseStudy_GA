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
    //Create label display to be displayed on top of GUI
    private Label lbLogin1 = new Label("Login to Account");
    private Label lbLogin2 = new Label("Please enter your details to login"); // Corrected "you" to "your"
    private Label lbEmail = new Label("Enter email: ");
    private Label lbPassword = new Label("Enter password: ");
    private Label lbRepsonse = new Label(""); // Corrected "Repsonse" to "Response"
    //Create textfield to store user input data
    private TextField tfEmail = new TextField();
    private TextField tfPassword = new TextField();
    //Create button for user to click to login
    private Button btLogin = new Button("Login");
    //Create title of GUI and the style background
    private String title = "Login Window";
    private String style = "-fx-background-color: blue; -fx-text-fill: white;";
    private String responseError = "-fx-text-fill: red;";
    private int MaxWidthTF = 200;

    public static void main(String[] args) {
        launch(args);
    }
    //Design of screen to be displayed
    @SuppressWarnings("exports")
    public void start(Stage primaryStage) {
    	// Setting up the horizontal box for button area
        hbPane.setSpacing(10);
        hbPane.setAlignment(Pos.BASELINE_CENTER);
        // Styling the "Create Account" button
        btLogin.setStyle(style);
        // Styling the "Register an Account" label
        lbLogin1.setStyle("-fx-font: 20 arial;");
        // Setting up the vertical box for the main content area
        vbPaneMain.setSpacing(10);
        vbPaneMain.setPadding(new Insets(10, 10, 10, 10));
        vbPaneMain.setAlignment(Pos.BASELINE_CENTER);
        vbPaneMain.getChildren().addAll(lbLogin1, lbLogin2, lbEmail, tfEmail, lbPassword, tfPassword, btLogin, hbPane, lbRepsonse);
        tfEmail.setMaxWidth(MaxWidthTF);
        tfPassword.setMaxWidth(MaxWidthTF);
        // Adding the main content area and navigation bar to the entire horizontal box
        vbPaneEntire.getChildren().addAll(NavBar.navBarStart(primaryStage), vbPaneMain);
        Scene login = new Scene(vbPaneEntire);
        //Initialize stage
        FXHelper.loadStage(primaryStage, login, title);
        //Add event for login
        EventHandler<ActionEvent> handleLogin= (ActionEvent e) -> {
            if(checkFields()) {
                String email = tfEmail.getText();
                String password = tfPassword.getText();
                if (Authentication.LoginAccount(email, password)) {
                    //TODO Move user to user's page (DONT DO YET)
                }
            }
        };
        btLogin.setOnAction(handleLogin);
    }

    private boolean checkFields() {
        lbRepsonse.setStyle(responseError);

        // Confirm mandatory fields are filled out
        //To be displayed if email textbox is left empty
        if (tfEmail.getText().isEmpty()) {
            lbRepsonse.setText("The field cannot be left blank. You must enter an email");
            return false;
        }
      //To be displayed if password textbox is left empty
        if (tfPassword.getText().isEmpty()) {
            lbRepsonse.setText("The field cannot be left blank. You must enter a password");
            return false;
        }

        return true;
    }
}
