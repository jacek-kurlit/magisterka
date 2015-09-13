package pl.edu.agh.ui.fixation.action;

import javafx.scene.Scene;
import pl.edu.agh.config.OptionsConfiguration;
import pl.edu.agh.ui.controllers.ControllerContext;
import pl.edu.agh.ui.fixation.action.model.Sensitivity;

public class SaveOptionsAction implements Action {

    private ControllerContext context;

    private Sensitivity elementFixationSensitivity;
    private Sensitivity leftClickSensitivity;
    private Sensitivity cursorSensitivity;

    public SaveOptionsAction(ControllerContext context, Sensitivity elementFixationSensitivity, Sensitivity leftClickSensitivity, Sensitivity cursorSensitivity) {
        this.context = context;
        this.elementFixationSensitivity = elementFixationSensitivity;
        this.leftClickSensitivity = leftClickSensitivity;
        this.cursorSensitivity = cursorSensitivity;
    }

    @Override
    public void onUserFixation() {
        updateConfiguration();
        backToWelcomeMenu();
    }

    private void updateConfiguration() {
        OptionsConfiguration optionsConfiguration = context.getOptionsConfiguration();

        optionsConfiguration.setElementFixationTime(elementFixationSensitivity.getCurrentValue());
        optionsConfiguration.setLeftClickTime(leftClickSensitivity.getCurrentValue());
        optionsConfiguration.setScaledMouseSensitivity(cursorSensitivity.getCurrentValue());
    }

    private void backToWelcomeMenu() {
        Scene previousScene = context.getPreviousScene();
        context.getPrimaryStage().setScene(previousScene);
    }
}
