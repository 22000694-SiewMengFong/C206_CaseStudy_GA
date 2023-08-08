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

public class AdminUser extends Application {

	// Create Box by Entire screen, Main screen, button area
	private VBox vbPaneMain = new VBox();
	private HBox hbPane = new HBox();
	private BorderPane bpPane = new BorderPane();

	// Create label display to be displayed on top of GUI
	private Label lborderTitle = new Label("System Overview");
	private Label lborderMenu = new Label("Total Menu: ");
	private Label lborderNormal = new Label("Total Parents: ");
	private Label lborderVendor = new Label("Total Vendor: ");
	private Label lborderUser = new Label("Total User: ");

	// Create respective textbox to display system data
	private static TextField totalMenu = new TextField();
	private static TextField totalNormal = new TextField();
	private static TextField totalVendor = new TextField();
	private static TextField totalUser = new TextField();

	// Basic setting of the class
	private String title = "System Overview";
	private String stylebt = "-fx-background-color: blue; -fx-text-fill: white;";
	private String stylelb = "-fx-font: 20 arial;";
	private int MaxWidthTF = 200;

	private static DBData credential;

	/*
	 * 
	 * public static void main(String[] args) { launch(args); } // End of main
	 * 
	 */

	public void startCredential(DBData Credential) {
		credential = Credential;
		start(new Stage());
	}

	@SuppressWarnings("exports")
	public void start(Stage primaryStage) {
		credential = new DBData("admin1@admin1", "admin1");
		if (credential == null) {
			String error = "No Credential Found While Logging In";
			(new MainStart()).startReturn(error);
			return;
		}
		System.out.println("HI");

		// Setting up the vertical box for the main content area
		vbPaneMain.setSpacing(10);
		vbPaneMain.setPadding(new Insets(10, 10, 10, 10));
		vbPaneMain.setAlignment(Pos.CENTER);

		// Styling the "System Overview" label
		lborderTitle.setStyle(stylelb);

		// Adding all the necessary elements to the main content area
		vbPaneMain.getChildren().addAll(lborderTitle, lborderMenu, totalMenu, lborderVendor, totalVendor, lborderNormal,
				totalNormal, lborderUser, totalUser);

		// Setting the maximum width for the text fields and disable editing
		totalMenu.setDisable(true);
		totalNormal.setDisable(true);
		totalVendor.setDisable(true);
		totalUser.setDisable(true);

		totalMenu.setMaxWidth(MaxWidthTF);
		totalNormal.setMaxWidth(MaxWidthTF);
		totalVendor.setMaxWidth(MaxWidthTF);
		totalUser.setMaxWidth(MaxWidthTF);

		// Fill TextField
		totalMenu.setText(credential.getMenuCount());
		totalNormal.setText(credential.getNormalCount());
		totalVendor.setText(credential.getVendorCount());
		totalUser.setText(credential.getUserCount());

		// Adding the main content area and navigation bar to the entire horizontal box
		bpPane.setLeft(NavBar.navBarHomeAdmin(primaryStage));
		bpPane.setCenter(vbPaneMain);

		Scene register = new Scene(bpPane);

		// Initialize stage
		FXHelper.loadStage(primaryStage, register, title, 500, 500);

		// Add event for response
	}

	// Fields for TrackOrder

	@SuppressWarnings("exports")
	public void TrackOrder(Stage primaryStage) {

	}

	@SuppressWarnings("exports")
	public void EditUserAccount(Stage primaryStage) {

	}

	@SuppressWarnings("exports")
	public void AddAccount(Stage primaryStage) {

	}

	@SuppressWarnings("exports")
	public void GenerateSalesReport(Stage primaryStage) {

	}

}