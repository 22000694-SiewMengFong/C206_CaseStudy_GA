package GA;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Login extends Application {

	private VBox vbPane = new VBox();
	private HBox hbPane = new HBox();
	
	
	
	private Label lbAppTitleWelcome = new Label("Welcome to the GA App!");
	private Label lbAppTitleAsk = new Label("Please login to start");
	
	
	private Button btLogin = new Button("Login");
	private Button btRegister = new Button("Register");

	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) {
		String jdbcURL = "jdbc:mysql://localhost/demodb";
		String dbUsername = "root";
		String dbPassword = "";
		
		DBUtil.init(jdbcURL, dbUsername, dbPassword);
		
		hbPane.setSpacing(10);
		hbPane.setAlignment(Pos.CENTER);
		
		// RJ
		String style = "-fx-background-color: blue; -fx-text-fill: white;";
		btLogin.setStyle(style);
		btRegister.setStyle(style);
		
		hbPane.getChildren().addAll(btLogin, btRegister);
		
		vbPane.setSpacing(10);
		vbPane.setPadding(new Insets(10,10,10,10));
		vbPane.setAlignment(Pos.CENTER);
		
		// RJ
		lbAppTitleWelcome.setStyle("-fx-font: 20 arial;");
		vbPane.getChildren().addAll(lbAppTitleWelcome, lbAppTitleAsk, hbPane);
		
		Scene mainScene = new Scene(vbPane);
		primaryStage.setScene(mainScene);
		
		primaryStage.setTitle("GA login/registion page");
		primaryStage.setWidth(600);
		primaryStage.setHeight(400);
		
		primaryStage.show();
		

	}

}
