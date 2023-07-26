package GA;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 
 */
public class MainPage extends Application {

	private Stage globalStage = new Stage();

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception {
		StartPage();

	}

	private void loadStage(String title, int Width, int height) {
		globalStage.setTitle(title);
		globalStage.setWidth(Width);
		globalStage.setHeight(height);
		globalStage.show();
	}

	private void loadStage(String title) {
		loadStage(title, 600, 500);
	}

	private void StartPage() {
		VBox vbPane = new VBox();
		HBox hbPane = new HBox();

		Label lbAppTitleWelcome = new Label("Welcome to the GAy App!");
		Label lbAppTitleAsk = new Label("Please login/register to start");

		Button btLogin = new Button("Login");
		Button btRegister = new Button("Register");

		String style = "-fx-background-color: blue; -fx-text-fill: white;";
		String title = "GA login/registion page";
		
		hbPane.setSpacing(10);
		hbPane.setAlignment(Pos.CENTER);

		btLogin.setStyle(style);
		btRegister.setStyle(style);

		hbPane.getChildren().addAll(btLogin, btRegister);

		vbPane.setSpacing(10);
		vbPane.setAlignment(Pos.BASELINE_CENTER);

		lbAppTitleWelcome.setStyle("-fx-font: 20 arial;");
		vbPane.getChildren().addAll(NavBar.navBarStart(), lbAppTitleWelcome, lbAppTitleAsk, hbPane);

		Scene mainScene = new Scene(vbPane);

		globalStage.setScene(mainScene);
		loadStage(title, 600, 400);

		EventHandler<ActionEvent> handleLogin = (ActionEvent e) -> {
			MainLogin();
		};
		btLogin.setOnAction(handleLogin);

		EventHandler<ActionEvent> handleRegister = (ActionEvent e) -> {
			MainRegister();
		};
		btRegister.setOnAction(handleRegister);
	}

	private void MainRegister() {

		// Calls Registration class to get scene in RegisterAccout
		Scene newScene = Registration.RegisterAccount();

		// Set scene in newScene
		globalStage.setScene(newScene);

		String title = "GA Registion Page";

		// Load Stage of Registration using the globalStage
		loadStage(title);
	}

	private void MainLogin() {
		
		// Calls Login class to get scene in LoginAccount
		Scene newScene = Login.LoginAccount();
		
		// Set scene in newScene
		globalStage.setScene(newScene);
		
		String title = "GA Login Page";
		
		// Load Stage of login using the globalStage
		loadStage(title);
	}
}
