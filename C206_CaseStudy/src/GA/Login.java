package GA;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Login {

	private static VBox vbPane = new VBox();
	private static HBox hbPane = new HBox();
	
	private static Label lbLogin1 = new Label("Login to Account");
	private static Label lbLogin2 = new Label("Please enter you details to login");
	
	private static Label lbEmail = new Label("Enter email: ");
	private static Label lbPassword = new Label("Enter password: ");
	
	private static TextField tfEmail = new TextField();
	private static TextField tfPassword = new TextField();
	
	private static Button btCreate = new Button("Login");
	
	public static Scene LoginAccount() {
		hbPane.setSpacing(10);
		hbPane.setAlignment(Pos.BASELINE_CENTER);

		
		String style = "-fx-background-color: blue; -fx-text-fill: white;";
		btCreate.setStyle(style);
		lbLogin1.setStyle("-fx-font: 20 arial;");

		
		vbPane.setSpacing(10);
		vbPane.setPadding(new Insets(10,10,10,10));
		vbPane.setAlignment(Pos.BASELINE_CENTER);
		vbPane.getChildren().addAll(
				lbLogin1, lbLogin2,
				lbEmail, tfEmail,
				lbPassword, tfPassword,
				btCreate, hbPane);
		
		int MaxWidthTF = 200;
		tfEmail.setMaxWidth(MaxWidthTF);
		tfPassword.setMaxWidth(MaxWidthTF);
		Scene login = new Scene(vbPane);
		
		
		return login;
	}

}
