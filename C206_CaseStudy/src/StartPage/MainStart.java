package StartPage;

import HelperPackage.FXHelper;
import HelperPackage.NavBar;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainStart extends Application {

	private BorderPane pane = new BorderPane();
	
	// Create Box by Entire screen, Main screen, button area
	private VBox vbTitle = new VBox();
	private VBox vbPaneMain = new VBox();
	private HBox hbPaneBt = new HBox();
	
	//Create ImageView of Logo
	private ImageView LOGO = new ImageView("file:UserProfiles\\Logo.png");

	// Create button to put in hbPaneBt
	private Button btLogin = new Button("Login");
	private Button btRegister = new Button("Register");

	// Creates label to put in Main screen
	//private Label lbAppTitleWelcome = new Label("Welcome to the GAy App!");
	private Label lbAppTitleAsk = new Label("Please login/register to start!");
	private Label lbReturn = new Label("");
	
	// Basic setting of the class
	private String stylebt = "-fx-background-color: blue; -fx-text-fill: white;";
	private String stylelb = "-fx-font: 20 arial;";
	private String title = "GA login/registion page";
	private static String responseError = "-fx-text-fill: red;";
	
	public static void main(String[] args) {
		launch(args);
	} // End of Main

	public void startReturn(String error) {
		lbReturn.setStyle(responseError);
		lbReturn.setText(error);
		start(new Stage());
	}
	
	@SuppressWarnings("exports")
	public void start(Stage primaryStage) {

		// Design of button area
		hbPaneBt.setSpacing(10);
		hbPaneBt.setAlignment(Pos.CENTER);
		
		// Set style of buttons
		btLogin.setStyle(stylebt);
		btRegister.setStyle(stylebt);

		hbPaneBt.getChildren().addAll(btLogin, btRegister);

		// Design of main screen
		vbPaneMain.setSpacing(10);
		vbPaneMain.setAlignment(Pos.CENTER);

		LOGO.setFitWidth(200);
		LOGO.setFitHeight(200);
		vbTitle.getChildren().addAll(LOGO, lbAppTitleAsk);
		vbTitle.setAlignment(Pos.CENTER);
		vbTitle.setSpacing(5);
		
		vbPaneMain.getChildren().addAll(vbTitle, hbPaneBt, lbReturn);

		pane.setCenter(vbPaneMain);
		pane.setLeft(NavBar.navBarStart(primaryStage));
		
		Scene mainScene = new Scene(pane);

		// Initialize stage
		FXHelper.loadStage(primaryStage, mainScene, title, 600, 400);

		// Add event for Login button
		EventHandler<ActionEvent> handleLogin = (ActionEvent e) -> {
			
			// Try open login window else mainpage
			try {
				(new Login()).start(new Stage());
				primaryStage.close();
			} catch (Exception ex) {
				(new MainStart()).start(new Stage());
				primaryStage.close();
			}
		};
		btLogin.setOnAction(handleLogin);

		// Add event for Register button
		EventHandler<ActionEvent> handleRegister = (ActionEvent e) -> {
			
			// Try open register window else mainpage
			try {
				(new Registration()).RegisterNormal(new Stage());
				primaryStage.close();
			} catch (Exception ex) {
				(new MainStart()).start(new Stage());
				primaryStage.close();
			}

		};
		btRegister.setOnAction(handleRegister);

	} // End of Start

} // End of Class
