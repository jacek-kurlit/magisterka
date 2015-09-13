package pl.edu.agh.ui.controllers;

import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.edu.agh.config.OptionsConfiguration;


public class ControllerContext {
    private Stage primaryStage;
    private OptionsConfiguration optionsConfiguration;
    private Scene previousScene;

    public ControllerContext(Stage primaryStage, OptionsConfiguration optionsConfiguration) {
        this.primaryStage = primaryStage;
        this.optionsConfiguration = optionsConfiguration;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public OptionsConfiguration getOptionsConfiguration() {
        return optionsConfiguration;
    }

    public Scene getPreviousScene() {
        return previousScene;
    }

    public void setPreviousScene(Scene previousScene) {
        this.previousScene = previousScene;
    }
}
