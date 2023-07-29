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

public class AboutUs extends Application {

	// Create Box by Entire screen, Main screen, button area
	private HBox vbPaneEntire = new HBox();
	private VBox vbPaneMain = new VBox();
	private HBox hbPaneBt = new HBox();
	
	// Create label display to be displayed on top of GUI
	private Label lbAboutUs = new Label("About Sus");
		
	// Create button for user to click
	private Button btSUS = new Button("SUS");
	private static Label lbRepsonse = new Label("0");
	
	// Basic setting of the class
	private String title = "About Us";
	private String stylebt = "-fx-background-color: blue; -fx-text-fill: white;";
	private String stylelb = "-fx-font: 20 arial;";
	
	public static void main(String[] args) {
		launch(args);
	} // End of main

	@SuppressWarnings("exports")
	public void start(Stage primaryStage) {

		// Design of button area
		hbPaneBt.setSpacing(10);
		hbPaneBt.setAlignment(Pos.CENTER);
		
		// Set style of buttons
		btSUS.setStyle(stylebt);

		hbPaneBt.getChildren().addAll(btSUS);

		// Design of main screen
		vbPaneMain.setSpacing(10);
		vbPaneMain.setAlignment(Pos.CENTER);

		lbAboutUs.setStyle(stylelb);
		vbPaneMain.getChildren().addAll(lbAboutUs, lbRepsonse, hbPaneBt);

		// Add Nav bar followed by main screen
		vbPaneEntire.getChildren().addAll(NavBar.navBarStart(primaryStage), vbPaneMain);

		Scene mainScene = new Scene(vbPaneEntire);

		// Initialize stage
		FXHelper.loadStage(primaryStage, mainScene, title, 600, 400);
		
		// Add event for SUS button
		EventHandler<ActionEvent> handleSUS = (ActionEvent e) -> {
			
			int num = Integer.parseInt(lbRepsonse.getText());
			lbRepsonse.setText(String.valueOf(num + 1));
		};
		btSUS.setOnAction(handleSUS);
	} // End of start
} // End of Class
