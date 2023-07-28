package StartPage;

import HelperPackage.FXHelper;
import HelperPackage.NavBar;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainPage extends Application {

	// Create Box by Entire screen, Main screen, button area
	private HBox vbPaneEntire = new HBox();
	private VBox vbPaneMain = new VBox();
	private HBox hbPaneBt = new HBox();

	// Create button to put in hbPaneBt
	private Button btLogin = new Button("Login");
	private Button btRegister = new Button("Register");

	// Creates label to put in Main screen
	private Label lbAppTitleWelcome = new Label("Welcome to the GAy App!");
	private Label lbAppTitleAsk = new Label("Please login/register to start");

	// Basic setting of the class
	private String stylebt = "-fx-background-color: blue; -fx-text-fill: white;";
	private String stylelb = "-fx-font: 20 arial;";
	private String title = "GA login/registion page";

	public static void main(String[] args) {
		launch(args);
	} // End of Main

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

		lbAppTitleWelcome.setStyle(stylelb);
		vbPaneMain.getChildren().addAll(lbAppTitleWelcome, lbAppTitleAsk, hbPaneBt);

		// Add Nav bar followed by main screen
		vbPaneEntire.getChildren().addAll(NavBar.navBarStart(primaryStage), vbPaneMain);

		Scene mainScene = new Scene(vbPaneEntire);

		// Initialize stage
		FXHelper.loadStage(primaryStage, mainScene, title, 600, 400);

		// Add event for Login button
		EventHandler<ActionEvent> handleLogin = (ActionEvent e) -> {
			
			// Try open login window else mainpage
			try {
				(new Login()).start(new Stage());
				primaryStage.close();
			} catch (Exception ex) {
				(new MainPage()).start(new Stage());
				primaryStage.close();
			}
		};
		btLogin.setOnAction(handleLogin);

		// Add event for Register button
		EventHandler<ActionEvent> handleRegister = (ActionEvent e) -> {
			
			// Try open register window else mainpage
			try {
				(new Registration()).start(new Stage());
				primaryStage.close();
			} catch (Exception ex) {
				(new MainPage()).start(new Stage());
				primaryStage.close();
			}

		};
		btRegister.setOnAction(handleRegister);

	} // End of Start

} // End of Class
