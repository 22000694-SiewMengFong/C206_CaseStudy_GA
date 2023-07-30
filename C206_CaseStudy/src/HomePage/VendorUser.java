package HomePage;

import HelperPackage.DBData;
import javafx.application.Application;
import javafx.stage.Stage;

public class VendorUser extends Application {

	private DBData credential;
	
	public static void main(String[] args) {
		launch(args);
	} // End of main

	@SuppressWarnings("exports")
	public void start(Stage primaryStage) {
		
	}
	
	@SuppressWarnings("exports")
	public void startCredential(Stage primaryStage, DBData credential) {
		this.credential = credential;
		start(primaryStage);
	}

}
