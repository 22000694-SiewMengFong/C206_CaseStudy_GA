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
import java.util.regex.Pattern;
import HelperPackage.FXHelper;

public class Registration extends Application {

    // Create Box by Entire screen, Main screen, button area
    private HBox vbPaneEntire = new HBox();
    private VBox vbPaneMain = new VBox();
    private HBox hbPane = new HBox();

    private Label lbRegister1 = new Label("Register an Account");
    private Label lbRegister2 = new Label("Please enter your details to start"); // Corrected "you" to "your"

    private Label lbName = new Label("Enter name: ");
    private Label lbEmail = new Label("Enter email: ");
    private Label lbPassword1 = new Label("Enter password: ");
    private Label lbPassword2 = new Label("Confirm password: ");

    private Label lbRepsonse = new Label(""); // Corrected "Repsonse" to "Response"

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

            if (checkFields() == true) {
                // David
                // TODO SQL add in authenticate class
                // Inputs are collected and are stored within respective strings
                String name = tfName.getText();
                String email = tfEmail.getText();
                String password = tfPassword1.getText();

                // CreateAccount method within the Authentication class is called and the strings
                // created earlier are used as arguments
                if (Authentication.CreateAccount(name, email, password)) {
                    lbRepsonse.setStyle(responseGood);
                    lbRepsonse.setText("Account Creation Successful");
                }
            }

        };
        btCreate.setOnAction(handleResponse);

    }

    private boolean checkFields() {
        lbRepsonse.setStyle(responseError);

        String name = tfName.getText();
        String email = tfEmail.getText();
        String password1 = tfPassword1.getText();
        String password2 = tfPassword2.getText();

        // Confirm mandatory fields are filled out
        if (name.isEmpty()) {
            lbRepsonse.setText("The field cannot be left blank. You must enter in a name");
            return false;
        }

        if (email.isEmpty()) {
            lbRepsonse.setText("The field cannot be left blank. You must enter in an email");
            return false;
        }

        if (password1.isEmpty()) {
            lbRepsonse.setText("The field cannot be left blank. You must enter in a password");
            return false;
        }

        if (password2.isEmpty()) {
            lbRepsonse.setText("The field cannot be left blank. You must enter in a password");
            return false;
        }

        if (!password1.equals(password2)) {
            lbRepsonse.setText("Password entered must be the same.");
            return false;
        }
        // David
        // TODO Name is all in alpha
        // Method isAllAlpha is called below
        if (!isName(name)) {
            lbRepsonse.setText("Name must contain only alphabetic characters.");
            return false;
        }

        // TODO Email is right format
        // Method isValidEmail is called below
        if (!isEmail(email)) {
            lbRepsonse.setText("Invalid email format. Please enter a valid email address.");
            return false;
        }

        // ######## NEED CHECKING
        // TODO Password is strong (OPTIONAL) - regex
        if (!isPassword(password1)) {
            lbRepsonse.setText("Password must include at least one capital letter and be at least 8 characters long.");
            return false;
        }
        return true;
    }

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
    }

    /**
     * Method is about validating email by checking if email contains @ and .
     * 
     * @param email
     * @return true if email matches
     */
    private static boolean isEmail(String email) {

        String pattern = "[a-zA-Z0-9]+@[a-zA-Z]+.[a-zA-Z]+";

        boolean matched = Pattern.matches(pattern, email);
        return matched;
    }

    /**
     * Method is about validating the password strength using regex.
     * 
     * @param password
     * @return true if password is strong (at least one capital letter and at least 8 characters long)
     */
    private static boolean isPassword(String password) {

        String pattern = "^(?=.*[A-Z]).{8,}$";

        boolean matched = Pattern.matches(pattern, password);
        return matched;
    }

}
