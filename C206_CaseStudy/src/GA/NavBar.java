package GA;

import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;

/**
 * 
 */
public class NavBar{

	
	public static ToolBar navBarStart() {
		// create a label
		ToolBar toolbar = new ToolBar();

		// creating buttons
		Button btMainPage = new Button("Main Page");
		Button btLogin = new Button("Login");
		Button btRegister = new Button("Register");
		Button btAboutSus = new Button("About Us");

		toolbar.getItems().addAll(btMainPage, btLogin, btRegister, btAboutSus);

		//toolbar.setOrientation(Orientation.VERTICAL);
		//HBox hbox = new HBox(toolbar);
		//return hbox

		return toolbar;
	}

}
