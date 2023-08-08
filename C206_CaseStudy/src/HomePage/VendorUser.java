package HomePage;

import HelperPackage.DBData;
import HelperPackage.FXHelper;
import HelperPackage.NavBar;
import StartPage.MainStart;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VendorUser extends Application {

	// Create Box by Entire screen, Main screen, button area
	private VBox vbPaneMain = new VBox();
	private HBox hbPane = new HBox();
	private BorderPane bpPane = new BorderPane();

	// Create label display to be displayed on top of GUI
	private Label lborderTitle = new Label("Vendor Overview");
	private Label lborderMenu = new Label("Total Menu: ");
	private Label lborderItem = new Label("Total Items: ");
	private Label lborderOrder = new Label("Total Orders: ");

	// Create respective textbox to display system data
	private static TextField totalMenu = new TextField();
	private static TextField totalItem = new TextField();
	private static TextField totalOrder = new TextField();

	// Basic setting of the class
	private String title = "Vendor Overview";
	private String stylebt = "-fx-background-color: blue; -fx-text-fill: white;";
	private String stylelb = "-fx-font: 20 arial;";
	private int MaxWidthTF = 200;

	private DBData credential;

	public static void main(String[] args) {
		launch(args);
	} // End of main

	public void startCredential(DBData Credential) {
		credential = Credential;
		start(new Stage());
	}

	@SuppressWarnings("exports")
	public void start(Stage primaryStage) {
		credential = new DBData("vendor1@vendor1", "vendor1");

		// Setting up the vertical box for the main content area
		vbPaneMain.setSpacing(10);
		vbPaneMain.setPadding(new Insets(10, 10, 10, 10));
		vbPaneMain.setAlignment(Pos.BASELINE_CENTER);

		// Adding all the necessary elements to the main content area
		vbPaneMain.getChildren().addAll(lborderTitle, lborderMenu, totalMenu, lborderItem, totalItem, lborderOrder,
				totalOrder);
		
		// Setting the maximum width for the text fields and disable editing
		totalMenu.setDisable(true);
		totalItem.setDisable(true);
		totalOrder.setDisable(true);

		totalMenu.setMaxWidth(MaxWidthTF);
		totalItem.setMaxWidth(MaxWidthTF);
		totalOrder.setMaxWidth(MaxWidthTF);

		// Fill TextField
		totalMenu.setText(credential.getMenuCount());
		totalItem.setText(credential.getItemCount());
		totalOrder.setText(credential.getOrderCount());

		// Adding the main content area and navigation bar to the entire horizontal box
		bpPane.setLeft(NavBar.navBarHomeVendor(primaryStage));
		bpPane.setCenter(vbPaneMain);

		Scene register = new Scene(bpPane);

		// Initialize stage
		FXHelper.loadStage(primaryStage, register, title, 500, 500);
	}
	
	@SuppressWarnings("exports")
	public void UpdateCredential(Stage primayStage) {
		
	}

	@SuppressWarnings("exports")
	public void CreateMenu(Stage primayStage) {
		
	}

	@SuppressWarnings("exports")
	public void AddItem(Stage primayStage) {
		
	}

	@SuppressWarnings("exports")
	public void EditItem(Stage primayStage) {
		
	}

	@SuppressWarnings("exports")
	public void DeleteItem(Stage primayStage) {
		
	}
}
