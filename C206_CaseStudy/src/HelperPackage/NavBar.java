package HelperPackage;

import StartPage.AboutUs;
import StartPage.Login;
import StartPage.MainStart;
import StartPage.Registration;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NavBar {

	private static Label app = new Label("GA APP");

	@SuppressWarnings("exports")
	public static HBox navBarStart(Stage primaryStage) {
		// Create Toolbar
		ToolBar toolbar = new ToolBar();

		// Creating buttons
		Button btMainPage = new Button("Main Page");
		Button btLogin = new Button("Login");
		Button btRegister = new Button("Register");
		Button btAboutSus = new Button("About Us");

		// Adding Button, Separator and Spacer to ToolBar
		toolbar.getItems().addAll(app, new Separator(), btMainPage, btLogin, btRegister, btAboutSus);

		// Set ToolBar orientation to vertical
		toolbar.setOrientation(Orientation.VERTICAL);
		
		HBox hbox = new HBox(toolbar);
		hbox.setAlignment(Pos.TOP_LEFT);

		// Add event for main page button
		EventHandler<ActionEvent> handleMainPage = (ActionEvent e) -> {
			(new MainStart()).start(new Stage());
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
				(new MainStart()).start(new Stage());
				primaryStage.close();
			}
		};
		btLogin.setOnAction(handleLogin);

		// Add event for Register button
		EventHandler<ActionEvent> handleRegister = (ActionEvent e) -> {
			// Try open register window else mainpage
			try {
				(new Registration()).RegisterNormal(new Stage());;
				primaryStage.close();
			} catch (Exception ex) {
				(new MainStart()).start(new Stage());
				primaryStage.close();
			}
		};
		btRegister.setOnAction(handleRegister);

		// Add event for about us button
		EventHandler<ActionEvent> handleAboutUs = (ActionEvent e) -> {
			// Try open about us window else mainpage
			try {
				(new AboutUs()).start(new Stage());
				primaryStage.close();
			} catch (Exception ex) {
				(new MainStart()).start(new Stage());
				primaryStage.close();
			}
		};
		btAboutSus.setOnAction(handleAboutUs);

		return hbox;
	}
	
	//TODO add eventhandler
	@SuppressWarnings("exports")
	public static HBox navBarHomeNormal(Stage primaryStage) {
		// Create Toolbar
		ToolBar toolbar = new ToolBar();
        
		// Creates a vertical spacing between Buttons
        final Pane bottomSpacer = new Pane();
        VBox.setVgrow(
        		bottomSpacer,
                Priority.SOMETIMES
        );
        
		// Creating buttons
		Button btDaily = new Button("View Daily Menu");
		Button btOrder = new Button("Place Order");
		Button btTrack = new Button("Track Order Status");
		Button btSetting = new Button("Setting");
		Button btLogOut = LogOut(primaryStage);

		// Adding Button, Separator and Spacer to ToolBar
		toolbar.getItems().addAll(app, new Separator(), btDaily, btOrder, btTrack, btSetting, bottomSpacer, new Separator(),
				btLogOut);

		// Set ToolBar orientation to vertical
		toolbar.setOrientation(Orientation.VERTICAL);
		
		HBox hbox = new HBox(toolbar);
		hbox.setAlignment(Pos.TOP_LEFT);

		return hbox;
	}
	
	//TODO add eventhandler and change button
	@SuppressWarnings("exports")
	public static HBox navBarHomeVendor(Stage primaryStage) {
		// Create Toolbar
		ToolBar toolbar = new ToolBar();
        
		// Creates a vertical spacing between Buttons
        final Pane bottomSpacer = new Pane();
        VBox.setVgrow(
        		bottomSpacer,
                Priority.SOMETIMES
        );
        
		// Creating buttons
		Button btDaily = new Button("View Daily Menu");
		Button btOrder = new Button("Place Order");
		Button btTrack = new Button("Track Order Status");
		Button btSetting = new Button("Setting");
		Button btLogOut = LogOut(primaryStage);

		// Adding Button, Separator and Spacer to ToolBar
		toolbar.getItems().addAll(app, new Separator(), btDaily, btOrder, btTrack, btSetting, bottomSpacer, new Separator(),
				btLogOut);

		// Set ToolBar orientation to vertical
		toolbar.setOrientation(Orientation.VERTICAL);
		
		HBox hbox = new HBox(toolbar);
		hbox.setAlignment(Pos.TOP_LEFT);

		return hbox;
	}
	
	//TODO add eventhandler and change button
	@SuppressWarnings("exports")
	public static HBox navBarHomeAdmin(Stage primaryStage) {
		// Create Toolbar
		ToolBar toolbar = new ToolBar();
        
		// Creates a vertical spacing between Buttons
        final Pane bottomSpacer = new Pane();
        VBox.setVgrow(
        		bottomSpacer,
                Priority.SOMETIMES
        );
        
		// Creating buttons
		Button btDaily = new Button("View Daily Menu");
		Button btOrder = new Button("Place Order");
		Button btTrack = new Button("Track Order Status");
		Button btSetting = new Button("Setting");
		Button btLogOut = LogOut(primaryStage);

		// Adding Button, Separator and Spacer to ToolBar
		toolbar.getItems().addAll(app, new Separator(), btDaily, btOrder, btTrack, btSetting, bottomSpacer, new Separator(),
				btLogOut);

		// Set ToolBar orientation to vertical
		toolbar.setOrientation(Orientation.VERTICAL);
		
		HBox hbox = new HBox(toolbar);
		hbox.setAlignment(Pos.TOP_LEFT);

		return hbox;
	}

	private static Button LogOut(Stage primaryStage) {

		Button btLogOut = new Button("Log Out");
		String logout = "User Logged Out";

		EventHandler<ActionEvent> handleLogOut = (ActionEvent e) -> {

			try {
				(new MainStart()).startReturn(logout);
				primaryStage.close();
			} catch (Exception ex) {
				primaryStage.close();
			}
		};
		btLogOut.setOnAction(handleLogOut);

		return btLogOut;
	}
}
