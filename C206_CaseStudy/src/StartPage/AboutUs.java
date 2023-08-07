package StartPage;

import java.util.Random;

import HelperPackage.FXHelper;
import HelperPackage.NavBar;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AboutUs extends Application {

	private BorderPane pane = new BorderPane();
	private GridPane grid = new GridPane();

	// Create Box by Entire screen, Main screen, button area
	private VBox vbPaneMain = new VBox();
	private HBox hbPaneBt = new HBox();

	// Create label display to be displayed on top of GUI
	private Label lbAboutUs = new Label("About Sus");

	// Create button for user to click
	private Button btSUS = new Button("SUS");
	private static Label lbRepsonse = new Label("0");

	// Basic setting of the class
	private static final String title = "About Us";
	private static final String stylebt = "-fx-background-color: blue; -fx-text-fill: white;";
	private static final  String stylelb = "-fx-font: 20 arial;";

	@SuppressWarnings("exports")
	public void start(Stage primaryStage) {
		// set the sus grid to center align
		grid.setAlignment(Pos.CENTER);
		
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
		vbPaneMain.getChildren().addAll(lbAboutUs, lbRepsonse, hbPaneBt, grid);

		// Add Nav bar followed by main screen
		pane.setLeft(NavBar.navBarStart(primaryStage));
		pane.setCenter(vbPaneMain);

		Scene mainScene = new Scene(pane);

		// Initialize stage
		FXHelper.loadStage(primaryStage, mainScene, title, 700, 500);

		// Add event for SUS button
		EventHandler<ActionEvent> handleSUS = (ActionEvent e) -> {
			// Sus things
			String[] url = {"sus1.jpg", "sus2.jpeg", "sus3.jpeg", "sus4.png"};
			Random rand = new Random();
			int randomN = rand.nextInt(10);
			int num = Integer.parseInt(lbRepsonse.getText());
			lbRepsonse.setText(String.valueOf(num + 1));
			if (randomN == 2) {
				int randomX = (rand.nextInt(url.length));
				String urlFound = String.format("file:UserProfiles/%s", url[randomX]);
				Image img = new Image(urlFound, 300, 300, false, false);
				ImageView imgView = new ImageView(img);
				grid.add(imgView, 0, 0);
			} else {
				grid.getChildren().clear();
			}
		};
		btSUS.setOnAction(handleSUS);
	} // End of start
} // End of Class
