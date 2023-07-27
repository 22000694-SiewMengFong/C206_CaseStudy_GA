package StartPage;

import javafx.scene.Scene;
import javafx.stage.Stage;


public class FXHelper {

	@SuppressWarnings("exports")
	public static void loadStage(Stage stage, Scene scene, String title, int Width, int height) {
		stage.setScene(scene);
		stage.setTitle(title);
		stage.setWidth(Width);
		stage.setHeight(height);
		stage.show();
	}

	@SuppressWarnings("exports")
	public static void loadStage(Stage stage, Scene scene, String title) {
		loadStage(stage, scene, title, 600, 500);
	}

}
