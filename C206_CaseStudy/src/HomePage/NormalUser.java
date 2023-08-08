package HomePage;

import HelperPackage.DBData;
import HelperPackage.FXHelper;
import HelperPackage.NavBar;
import StartPage.MainStart;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class NormalUser extends Application {

	private static DBData credential;

	// Create Box by Entire screen, Main screen, button area
	private BorderPane bpPane = new BorderPane();
	private VBox vbPaneMain = new VBox();
	private HBox hbPane = new HBox();

	// Create label display to be displayed on top of GUI
	private Label lbRegister1 = new Label("Register an Account");
	private Label lbRegister2 = new Label("Please enter your details to start");

	// Create respective label to ask user for input data
	private Label lbName = new Label("Enter name: ");
	private Label lbEmail = new Label("Enter email: ");
	private Label lbPassword1 = new Label("Enter password: ");
	private Label lbPassword2 = new Label("Confirm password: ");

	// Create label to be displayed back to user based on certain conditions
	private static Label lbRepsonse = new Label("");

	// Create respective textbox to collect user input data
	private static TextField tfName = new TextField();
	private static TextField tfEmail = new TextField();
	private static TextField tfPassword1 = new TextField();
	private static TextField tfPassword2 = new TextField();

	// Create button to be clicked by user upon filling all textfield
	private Button btCreate = new Button("Create Account");

	// Basic setting of the class
	private String title = "Registration Window";
	private String stylebt = "-fx-background-color: blue; -fx-text-fill: white;";
	private String stylelb = "-fx-font: 20 arial;";
	private int MaxWidthTF = 200;
	private static final int MAXWIDTH = 150;
	
	public static void main(String[] args) {
		launch(args);
	} // End of main

	public void startCredential(DBData Credential) {
		credential = Credential;
		start(new Stage());
	}

	@SuppressWarnings("exports")
	public void start(Stage primaryStage) {
		credential = new DBData("normal1@normal1", "normal1");
		if (credential == null) {
			String error = "No Credential Found While Logging In";
			(new MainStart()).startReturn(error);
			return;
		}

		// Creating Filter Bar
		HBox hbFilter = new HBox();
		
		String[] AllegiesArray = DBData.getAllAllegies();
		int AllegiesNumber = AllegiesArray.length;
		ToolBar toolbarFilter = new ToolBar();
		
		for (int i = 0; i < AllegiesNumber; i++) {
			Button btFilter = new Button();
			
			btFilter.setText(AllegiesArray[i]);
			btFilter.setMaxWidth(100);
			toolbarFilter.getItems().add(btFilter);
		}

		// Set ToolBar orientation to vertical
		toolbarFilter.setOrientation(Orientation.HORIZONTAL);

		hbFilter = new HBox(toolbarFilter);
		
		vbPaneMain.getChildren().addAll(hbFilter);
		
		// Adding the main content area and navigation bar to the entire horizontal box
		bpPane.setLeft(NavBar.navBarHomeNormal(primaryStage));
		bpPane.setCenter(vbPaneMain);

		Scene register = new Scene(bpPane);

		// Initialize stage
		FXHelper.loadStage(primaryStage, register, title, 500, 500);

		// Add event for response
	}

	@SuppressWarnings("exports")
	public void PlaceOrder(Stage primaryStage) {

	}

	@SuppressWarnings("exports")
	public void TrackOrder(Stage primaryStage) {

	}

	@SuppressWarnings("exports")
	public void Setting(Stage primaryStage) { 
		   
		  // Declare necessary elements needed for this stage 
		  ImageView profPic = new ImageView(String.format("file:UserProfiles/%s", credential.getUser_picture())); 
		  Button changeProfPic = new Button("Change Profile Picture"); 
		   
		  lbName.setText("Name: "); 
		  tfName.setText(credential.getUser_name()); 
		  lbEmail.setText("Email: "); 
		  tfEmail.setText(credential.getUser_email()); 
		  Label lbContact = new Label("Contact: "); 
		  TextField tfContact = new TextField(); // Retrieve Contacts thru credential.get 
		  // TO DECLARE ALLERGIES WITH CHECKBOXES HERE LATER 
		  Label lbAddress = new Label("Address: "); 
		  TextField tfAddress = new TextField(); // Retrieve Address thru credential.get 
		   
		  Button updateParticulars = new Button("Update"); 
		   
		  // Setting MAXWIDTH for all TextFields 
		  tfName.setMaxWidth(MAXWIDTH); 
		  tfEmail.setMaxWidth(MAXWIDTH); 
		  tfContact.setMaxWidth(MAXWIDTH); 
		  tfAddress.setMaxWidth(MAXWIDTH); 
		  tfPassword1.setMaxWidth(MAXWIDTH); 
		  tfPassword2.setMaxWidth(MAXWIDTH); 
		   
		  // Inserting elements into vbPaneMain 
		  vbPaneMain.getChildren().addAll(profPic, changeProfPic, lbName, tfName, lbEmail, tfEmail, lbContact, tfContact, 
		    lbAddress, tfAddress, lbPassword1, 
		    tfPassword1, lbPassword2, tfPassword2, updateParticulars, lbRepsonse); 
		  vbPaneMain.setAlignment(Pos.CENTER); 
		  vbPaneMain.setSpacing(6); 
		  vbPaneMain.setPadding(new Insets(10,10,10,10)); 
		   
		  // Adding the main content area and navigation bar to the entire horizontal box 
		  bpPane.setLeft(NavBar.navBarHomeNormal(primaryStage)); 
		  bpPane.setCenter(vbPaneMain); 
		 
		  Scene settings = new Scene(bpPane); 
		 
		  // Initialize stage 
		  FXHelper.loadStage(primaryStage, settings, title, 500, 600); 
		 
		  // Add event for response 
		 
		 }

}