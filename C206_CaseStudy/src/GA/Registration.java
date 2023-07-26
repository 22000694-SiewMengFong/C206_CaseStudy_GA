package GA;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 
 */
public class Registration{

	private static VBox vbPane = new VBox();
	private static HBox hbPane = new HBox();
	
	private static Label lbRegister1 = new Label("Register an Account");
	private static Label lbRegister2 = new Label("Please enter you details to start");
	
	private static Label lbName = new Label("Enter name: ");
	private static Label lbEmail = new Label("Enter email: ");
	private static Label lbPassword1 = new Label("Enter password: ");
	private static Label lbPassword2 = new Label("Confirm password: ");
	
	private static TextField tfName = new TextField();
	private static TextField tfEmail = new TextField();
	private static TextField tfPassword1 = new TextField();
	private static TextField tfPassword2 = new TextField();
	
	private static Button btCreate = new Button("Create Account");
	
	public static Scene RegisterAccount() {
		hbPane.setSpacing(10);
		hbPane.setAlignment(Pos.BASELINE_CENTER);

		
		String style = "-fx-background-color: blue; -fx-text-fill: white;";
		btCreate.setStyle(style);
		lbRegister1.setStyle("-fx-font: 20 arial;");

		
		vbPane.setSpacing(10);
		vbPane.setPadding(new Insets(10,10,10,10));
		vbPane.setAlignment(Pos.BASELINE_CENTER);
		vbPane.getChildren().addAll(
				lbRegister1, lbRegister2,
				lbName, tfName,
				lbEmail, tfEmail,
				lbPassword1, tfPassword1,
				lbPassword2, tfPassword2,
				btCreate, hbPane);
		
		int MaxWidthTF = 200;
		tfName.setMaxWidth(MaxWidthTF);
		tfEmail.setMaxWidth(MaxWidthTF);
		tfPassword1.setMaxWidth(MaxWidthTF);
		tfPassword2.setMaxWidth(MaxWidthTF);
		Scene register = new Scene(vbPane);
		
		
		return register;
	}
	
	public static boolean checkEmail(String email) {
		return false;
	}
	
	public static boolean checkPassword(String password1, String password2) {
		return false;
	}
}
