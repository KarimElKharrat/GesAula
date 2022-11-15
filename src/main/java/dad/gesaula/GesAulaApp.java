package dad.gesaula;

import dad.gesaula.controllers.MainController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GesAulaApp extends Application {

	private MainController controller = new MainController();
	public static Stage primaryStage;
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		GesAulaApp.primaryStage = primaryStage;
		
		primaryStage.setTitle("GesAulaApp");
		primaryStage.setScene(new Scene(controller.getView()));
		primaryStage.getIcons().add(new Image(getClass().getResource("/images/app-icon-64x64.png").toString()));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
