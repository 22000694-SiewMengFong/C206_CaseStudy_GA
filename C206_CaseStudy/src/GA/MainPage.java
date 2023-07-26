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

	private VBox vbPane = new VBox();
	private HBox hbPane = new HBox();

	private Label lbAppTitleWelcome = new Label("Welcome to the GAy App!");
	private Label lbAppTitleAsk = new Label("Please login/register to start");

	private Button btLogin = new Button("Login");
	private Button btRegister = new Button("Register");
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

		hbPane.setSpacing(10);
		hbPane.setAlignment(Pos.CENTER);

		//hbPane.setStyle("-fx-background-color: blue;");
		// RJ
		String style = "-fx-background-color: blue; -fx-text-fill: white;";
		btLogin.setStyle(style);
		btRegister.setStyle(style);

		hbPane.getChildren().addAll(btLogin, btRegister);

		vbPane.setSpacing(10);
		vbPane.setPadding(new Insets(10, 10, 10, 10));
		vbPane.setAlignment(Pos.BASELINE_CENTER);

		// RJ
		lbAppTitleWelcome.setStyle("-fx-font: 20 arial;");
		vbPane.getChildren().addAll(NavBar.navBarStart(),lbAppTitleWelcome, lbAppTitleAsk, hbPane);

		Scene mainScene = new Scene(vbPane);
		
		globalStage.setScene(mainScene);

		String title = "GA login/registion page";
		
		loadStage(title,600,400);

		EventHandler<ActionEvent> handleLogin = (ActionEvent e) -> (new Login()).start(new Stage());
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
		
		// Load Stage of Registration using the 
		loadStage(title);
	}
}
