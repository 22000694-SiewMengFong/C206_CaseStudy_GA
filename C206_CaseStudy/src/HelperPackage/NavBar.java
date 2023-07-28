package HelperPackage;

import StartPage.Login;
import StartPage.MainPage;
import StartPage.Registration;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class NavBar {

	@SuppressWarnings("exports")
	public static HBox navBarStart(Stage primaryStage) {
		// create a label
		ToolBar toolbar = new ToolBar();

		// creating buttons
		Button btMainPage = new Button("Main Page");
		Button btLogin = new Button("Login");
		Button btRegister = new Button("Register");
		Button btAboutSus = new Button("About Us");

		toolbar.getItems().addAll(btMainPage, btLogin, btRegister, btAboutSus);

		toolbar.setOrientation(Orientation.VERTICAL);
		HBox hbox = new HBox(toolbar);
		hbox.setAlignment(Pos.TOP_LEFT);

		// Add event for main page button
		EventHandler<ActionEvent> handleMainPage = (ActionEvent e) -> {
			(new MainPage()).start(new Stage());
			primaryStage.close();
		};
		btMainPage.setOnAction(handleMainPage);

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

		// Add event for about us button
		EventHandler<ActionEvent> handleAboutUs = (ActionEvent e) -> {
			// Try open about us window else mainpage
			try {
				// (new AboutUs()).start(new Stage());
				primaryStage.close();
			} catch (Exception ex) {
				(new MainPage()).start(new Stage());
				primaryStage.close();
			}
		};
		btAboutSus.setOnAction(handleAboutUs);

		return hbox;
	}

}
