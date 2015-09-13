package pl.edu.agh;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.edu.agh.config.OptionsConfiguration;
import pl.edu.agh.driver.MouseDriver;
import pl.edu.agh.ui.controllers.ControllerContext;
import pl.edu.agh.ui.loaders.ControllersLoader;

import java.awt.*;


public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	private static boolean DEBUG = true;
	@Override
	public void start(Stage primaryStage) {
		ControllerContext context = createContext(primaryStage);
		if(!DEBUG) {
			startMouseDriver(context);
		}
		showWelcomeWindow(primaryStage, context);
	}

	private ControllerContext createContext(Stage primaryStage) {
		return new ControllerContext(primaryStage, new OptionsConfiguration());
	}

	private void startMouseDriver(ControllerContext context) {
		try {
			MouseDriver mouseDriver = new MouseDriver();
			mouseDriver.start(context.getOptionsConfiguration());
		} catch (AWTException e) {
			System.err.println("Could not start mouse driver!");
			System.exit(0);
		}
	}

	private void showWelcomeWindow(Stage primaryStage, ControllerContext context) {
		Parent root = ControllersLoader.loadAndInit("views/WelcomeWindow.fxml", context);

		Scene scene = new Scene(root);

		primaryStage.setTitle("Komunikator");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
