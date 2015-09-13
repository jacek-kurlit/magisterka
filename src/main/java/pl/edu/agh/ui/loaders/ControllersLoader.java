package pl.edu.agh.ui.loaders;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import pl.edu.agh.Main;
import pl.edu.agh.ui.controllers.ControllerContext;
import pl.edu.agh.ui.controllers.IController;

import java.io.IOException;

public class ControllersLoader {
    public static Parent loadAndInit(String fxml, ControllerContext context) {
        try {
            FXMLLoader fxmlloader = new FXMLLoader(Main.class.getClassLoader().getResource(fxml));
            Parent root = fxmlloader.load();
            ((IController)fxmlloader.getController()).initialize(context);

            return root;
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.err.println("Could not load " + fxml);
        System.exit(-1);
        return null;
    }
}
