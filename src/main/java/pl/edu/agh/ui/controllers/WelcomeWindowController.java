package pl.edu.agh.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import pl.edu.agh.ui.fixation.action.ExitAction;
import pl.edu.agh.ui.fixation.FixationHandler;
import pl.edu.agh.ui.fixation.action.OptionsAction;

public class WelcomeWindowController implements IController {

    @FXML
    private Button communicatorBtn;

    @FXML
    private Button editorBtn;

    @FXML
    private Button optionsBtn;

    @FXML
    private Button exitBtn;

    private  ControllerContext context;

    @Override
    public void initialize(ControllerContext context) {
        this.context = context;

        exitBtn.addEventHandler(MouseEvent.ANY, exitFixation());
        optionsBtn.addEventHandler(MouseEvent.ANY, optionsFixation());
    }

    private FixationHandler exitFixation() {
        return new FixationHandler(new ExitAction(), context.getOptionsConfiguration());
    }

    private FixationHandler optionsFixation() {
        return new FixationHandler(new OptionsAction(context), context.getOptionsConfiguration());
    }
}