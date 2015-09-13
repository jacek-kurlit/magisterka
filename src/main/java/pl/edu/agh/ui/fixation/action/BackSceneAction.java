package pl.edu.agh.ui.fixation.action;

import javafx.scene.Scene;
import pl.edu.agh.ui.controllers.ControllerContext;

public class BackSceneAction implements Action {
    private ControllerContext context;

    public BackSceneAction(ControllerContext context) {
        this.context = context;
    }

    @Override
    public void onUserFixation() {
        Scene previousScene = context.getPreviousScene();
        context.getPrimaryStage().setScene(previousScene);

    }
}
