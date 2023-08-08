package HelperPackage;

import HomePage.AdminUser;
import HomePage.NormalUser;
import HomePage.VendorUser;
import StartPage.AboutUs;
import StartPage.Login;
import StartPage.MainStart;
import StartPage.Registration;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NavBar {

	private static final ImageView LOGO = new ImageView("file:UserProfiles/Logo.png");
	private static final int MAXWIDTH = 150;

	@SuppressWarnings("exports")
	public static HBox navBarStart(Stage primaryStage) {
		// Create Toolbar
		ToolBar toolbar = new ToolBar();

		// Creating buttons
		Button btMainPage = new Button("Main Page");
		Button btLogin = new Button("Login");
		Button btRegister = new Button("Register");
		Button btAboutSus = new Button("About Us");

		// Set Logo Size
		LOGO.setFitWidth(75);
		LOGO.setFitHeight(75);

		// Set max width of button
		btMainPage.setMaxWidth(MAXWIDTH);
		btLogin.setMaxWidth(MAXWIDTH);
		btRegister.setMaxWidth(MAXWIDTH);
		btAboutSus.setMaxWidth(MAXWIDTH);

		// Adding Button, Separator and Spacer to ToolBar
		toolbar.getItems().addAll(LOGO, new Separator(), btMainPage, btLogin, btRegister, btAboutSus);

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
				(new Registration()).RegisterNormal(new Stage());
				;
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

	// TODO add eventhandler
	@SuppressWarnings("exports")
	public static HBox navBarHomeNormal(Stage primaryStage) {
		// Create Toolbar
		ToolBar toolbar = new ToolBar();

		// Set Logo Size
		LOGO.setFitWidth(75);
		LOGO.setFitHeight(75);

		// Creates a vertical spacing between Buttons
		final Pane bottomSpacer = new Pane();
		VBox.setVgrow(bottomSpacer, Priority.SOMETIMES);

		// Creating buttons
		Button btDaily = new Button("View Daily Menu");
		Button btOrder = new Button("Place Order");
		Button btTrack = new Button("Track Order Status");
		Button btSetting = new Button("Setting");
		Button btLogOut = LogOut(primaryStage);

		// Set max width of button
		btDaily.setMaxWidth(MAXWIDTH);
		btOrder.setMaxWidth(MAXWIDTH);
		btTrack.setMaxWidth(MAXWIDTH);
		btSetting.setMaxWidth(MAXWIDTH);
		btLogOut.setMaxWidth(MAXWIDTH);

		// Adding Button, Separator and Spacer to ToolBar
		toolbar.getItems().addAll(LOGO, new Separator(), btDaily, btOrder, btTrack, btSetting, bottomSpacer,
				new Separator(), btLogOut);

		// Set ToolBar orientation to vertical
		toolbar.setOrientation(Orientation.VERTICAL);

		HBox hbox = new HBox(toolbar);
		hbox.setAlignment(Pos.TOP_LEFT);

		//
		EventHandler<ActionEvent> handleDaily = (ActionEvent e) -> {
			//
			(new NormalUser()).start(new Stage());
			primaryStage.close();

		};
		btDaily.setOnAction(handleDaily);

		//
		EventHandler<ActionEvent> handleOrder = (ActionEvent e) -> {
			//
			try {
				(new NormalUser()).PlaceOrder(new Stage());
				primaryStage.close();
			} catch (Exception ex) {
				(new NormalUser()).start(new Stage());
				primaryStage.close();
			}
		};
		btOrder.setOnAction(handleOrder);

		//
		EventHandler<ActionEvent> handleTrack = (ActionEvent e) -> {
			//
			try {
				(new NormalUser()).TrackOrder(new Stage());
				primaryStage.close();
			} catch (Exception ex) {
				(new NormalUser()).start(new Stage());
				primaryStage.close();
			}
		};
		btTrack.setOnAction(handleTrack);

		//
		EventHandler<ActionEvent> handleSetting = (ActionEvent e) -> {
			//
			try {
				(new NormalUser()).Setting(new Stage());
				primaryStage.close();
			} catch (Exception ex) {
				(new NormalUser()).start(new Stage());
				primaryStage.close();
			}
		};
		btSetting.setOnAction(handleSetting);

		return hbox;
	}

	// TODO add eventhandler and change button (VENDOR)
	@SuppressWarnings("exports")
	public static HBox navBarHomeVendor(Stage primaryStage) {
		// Create Toolbar
		ToolBar toolbar = new ToolBar();

		// Set Logo Size
		LOGO.setFitWidth(75);
		LOGO.setFitHeight(75);

		// Creates a vertical spacing between Buttons
		final Pane bottomSpacer = new Pane();
		VBox.setVgrow(bottomSpacer, Priority.SOMETIMES);

		// Creating buttons
		Button btOverview = new Button("Vender Overview");
		Button btUpdate = new Button("Update Credentials");
		Button btMenu = new Button("Create a new Menu");
		Button btAdd = new Button("Add Items");
		Button btEdit = new Button("Edit Items");
		Button btDelete = new Button("Delete Item");
		Button btLogOut = LogOut(primaryStage);

		// Set max width of button
		btOverview.setMaxWidth(MAXWIDTH);
		btUpdate.setMaxWidth(MAXWIDTH);
		btMenu.setMaxWidth(MAXWIDTH);
		btAdd.setMaxWidth(MAXWIDTH);
		btEdit.setMaxWidth(MAXWIDTH);
		btDelete.setMaxWidth(MAXWIDTH);
		btLogOut.setMaxWidth(MAXWIDTH);

		// Adding Button, Separator and Spacer to ToolBar
		toolbar.getItems().addAll(LOGO, new Separator(), btOverview, btUpdate, btMenu, btAdd, btEdit, btDelete,
				bottomSpacer, new Separator(), btLogOut);

		// Set ToolBar orientation to vertical
		toolbar.setOrientation(Orientation.VERTICAL);

		HBox hbox = new HBox(toolbar);
		hbox.setAlignment(Pos.TOP_LEFT);

		//
		EventHandler<ActionEvent> handleOverview = (ActionEvent e) -> {
			//
			(new VendorUser()).start(new Stage());
			primaryStage.close();
		};
		btOverview.setOnAction(handleOverview);

		//
		EventHandler<ActionEvent> handleUpdate = (ActionEvent e) -> {
			//
			try {
				(new VendorUser()).UpdateCredential(new Stage());
				primaryStage.close();
			} catch (Exception ex) {
				(new VendorUser()).start(new Stage());
				primaryStage.close();
			}
		};
		btUpdate.setOnAction(handleUpdate);

		//
		EventHandler<ActionEvent> handleMenu = (ActionEvent e) -> {
			//
			try {
				(new VendorUser()).CreateMenu(new Stage());
				primaryStage.close();
			} catch (Exception ex) {
				(new VendorUser()).start(new Stage());
				primaryStage.close();
			}
		};
		btMenu.setOnAction(handleMenu);

		//
		EventHandler<ActionEvent> handleAdd = (ActionEvent e) -> {
			//
			try {
				(new VendorUser()).AddItem(new Stage());
				primaryStage.close();
			} catch (Exception ex) {
				(new VendorUser()).start(new Stage());
				primaryStage.close();
			}
		};
		btAdd.setOnAction(handleAdd);

		//
		EventHandler<ActionEvent> handleEdit = (ActionEvent e) -> {
			//
			try {
				(new VendorUser()).EditItem(new Stage());
				primaryStage.close();
			} catch (Exception ex) {
				(new VendorUser()).start(new Stage());
				primaryStage.close();
			}
		};
		btEdit.setOnAction(handleEdit);

		//
		EventHandler<ActionEvent> handleDelete = (ActionEvent e) -> {
			//
			try {
				(new VendorUser()).DeleteItem(new Stage());
				primaryStage.close();
			} catch (Exception ex) {
				(new VendorUser()).start(new Stage());
				primaryStage.close();
			}
		};
		btDelete.setOnAction(handleDelete);

		return hbox;
	}

	// TODO add eventhandler and change button (ADMIN)
	@SuppressWarnings("exports")
	public static HBox navBarHomeAdmin(Stage primaryStage) {
		// Create Toolbar
		ToolBar toolbar = new ToolBar();

		// Set Logo Size
		LOGO.setFitWidth(75);
		LOGO.setFitHeight(75);

		// Creates a vertical spacing between Buttons
		final Pane bottomSpacer = new Pane();
		VBox.setVgrow(bottomSpacer, Priority.SOMETIMES);

		// Creating buttons
		Button btOverview = new Button("System Overview");
		Button btViewOrder = new Button("View Order Status");
		Button btEdit = new Button("Edit User Account");
		Button btAdd = new Button("Add an Account");
		Button btReport = new Button("Generate Sales Report");
		Button btLogOut = LogOut(primaryStage);

		// Set max width of button
		btOverview.setMaxWidth(MAXWIDTH);
		btViewOrder.setMaxWidth(MAXWIDTH);
		btEdit.setMaxWidth(MAXWIDTH);
		btAdd.setMaxWidth(MAXWIDTH);
		btEdit.setMaxWidth(MAXWIDTH);
		btReport.setMaxWidth(MAXWIDTH);
		btLogOut.setMaxWidth(MAXWIDTH);

		// Adding Button, Separator and Spacer to ToolBar
		toolbar.getItems().addAll(LOGO, new Separator(), btOverview, btViewOrder, btEdit, btAdd, btReport, bottomSpacer,
				new Separator(), btLogOut);

		// Set ToolBar orientation to vertical
		toolbar.setOrientation(Orientation.VERTICAL);

		HBox hbox = new HBox(toolbar);
		hbox.setAlignment(Pos.TOP_LEFT);

		// Add event for main page button
		EventHandler<ActionEvent> handleOverview = (ActionEvent e) -> {
			(new AdminUser()).start(new Stage());
			primaryStage.close();
		};
		btOverview.setOnAction(handleOverview);

		// Add event for Login button
		EventHandler<ActionEvent> handleViewOrder = (ActionEvent e) -> {
			// Try open login window else mainpage
			try {
				(new AdminUser()).TrackOrder(new Stage());
				primaryStage.close();
			} catch (Exception ex) {
				(new AdminUser()).start(new Stage());
				primaryStage.close();
			}
		};
		btViewOrder.setOnAction(handleViewOrder);

		// Add event for Login button
		EventHandler<ActionEvent> handleEditUser = (ActionEvent e) -> {
			// Try open login window else mainpage
			try {
				(new AdminUser()).EditUserAccount(new Stage());
				primaryStage.close();
			} catch (Exception ex) {
				(new AdminUser()).start(new Stage());
				primaryStage.close();
			}
		};
		btEdit.setOnAction(handleEditUser);

		// Add event for Register button
		EventHandler<ActionEvent> handleAddAccount = (ActionEvent e) -> {
			// Try open register window else mainpage
			try {
				(new AdminUser()).AddAccount(new Stage());
				;
				primaryStage.close();
			} catch (Exception ex) {
				(new AdminUser()).start(new Stage());
				primaryStage.close();
			}
		};
		btAdd.setOnAction(handleAddAccount);

		// Add event for about us button
		EventHandler<ActionEvent> handleReport = (ActionEvent e) -> {
			// Try open about us window else mainpage
			try {
				(new AdminUser()).GenerateSalesReport(new Stage());
				primaryStage.close();
			} catch (Exception ex) {
				(new AdminUser()).start(new Stage());
				primaryStage.close();
			}
		};
		btReport.setOnAction(handleReport);

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
