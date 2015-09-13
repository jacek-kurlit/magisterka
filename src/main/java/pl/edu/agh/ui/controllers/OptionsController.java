package pl.edu.agh.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import pl.edu.agh.config.OptionsConfiguration;
import pl.edu.agh.ui.fixation.FixationHandler;
import pl.edu.agh.ui.fixation.action.BackSceneAction;
import pl.edu.agh.ui.fixation.action.ChangeSensitivityAction;
import pl.edu.agh.ui.fixation.action.SaveOptionsAction;
import pl.edu.agh.ui.fixation.action.model.Sensitivity;

public class OptionsController implements IController {

    public static final int STEP = 100;
    @FXML
    private Button efmBtn;

    @FXML
    private Label elementFixLabel;

    @FXML
    private ProgressBar efProgressBar;

    @FXML
    private Button efpBtn;

    @FXML
    private Button lcmBtn;

    @FXML
    private Label leftClickLabel;

    @FXML
    private ProgressBar lcProgressBar;

    @FXML
    private Button lcpBtn;

    @FXML
    private Button rcmBtn;

    @FXML
    private Label sensitivityLabel;

    @FXML
    private ProgressBar sensProgressBar;

    @FXML
    private Button rcpBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private Button cancelBtn;

    private ControllerContext context;


    private Sensitivity elementFixationSensitivity;
    private Sensitivity leftClickSensitivity;
    private Sensitivity rightClickSensitivity;

    @Override
    public void initialize(ControllerContext context) {
        this.context = context;

        OptionsConfiguration configuration = context.getOptionsConfiguration();

        elementFixationSensitivity = new Sensitivity(elementFixLabel, efProgressBar, configuration.getElementFixationTime());
        leftClickSensitivity = new Sensitivity(leftClickLabel, lcProgressBar, configuration.getLeftClickTime());
        rightClickSensitivity = new Sensitivity(sensitivityLabel, sensProgressBar, configuration.getScaledMouseSensitivity());

        efpBtn.addEventHandler(MouseEvent.ANY, elementFixationIncrease());
        efmBtn.addEventHandler(MouseEvent.ANY, elementFixationDecrease());

        rcpBtn.addEventHandler(MouseEvent.ANY, rightClickTimeIncrease());
        rcmBtn.addEventHandler(MouseEvent.ANY, rightClickTimeDecrease());

        lcpBtn.addEventHandler(MouseEvent.ANY, leftClickTimeIncrease());
        lcmBtn.addEventHandler(MouseEvent.ANY, leftClickTimeDecrease());

        cancelBtn.addEventHandler(MouseEvent.ANY, cancelFixation());
        saveBtn.addEventHandler(MouseEvent.ANY, saveFixation());
    }

    private FixationHandler cancelFixation() {
        return new FixationHandler(new BackSceneAction(context), context.getOptionsConfiguration());
    }

    private FixationHandler saveFixation() {
        return new FixationHandler(new SaveOptionsAction(context, elementFixationSensitivity, leftClickSensitivity, rightClickSensitivity), context.getOptionsConfiguration());
    }

    private FixationHandler elementFixationIncrease() {
        return new FixationHandler(new ChangeSensitivityAction(elementFixationSensitivity, STEP), context.getOptionsConfiguration());
    }

    private FixationHandler elementFixationDecrease() {
        return new FixationHandler(new ChangeSensitivityAction(elementFixationSensitivity, -STEP), context.getOptionsConfiguration());
    }

    private FixationHandler rightClickTimeIncrease() {
        return new FixationHandler(new ChangeSensitivityAction(rightClickSensitivity, STEP), context.getOptionsConfiguration());
    }

    private FixationHandler rightClickTimeDecrease() {
        return new FixationHandler(new ChangeSensitivityAction(rightClickSensitivity, -STEP), context.getOptionsConfiguration());
    }

    private FixationHandler leftClickTimeIncrease() {
        return new FixationHandler(new ChangeSensitivityAction(leftClickSensitivity, STEP), context.getOptionsConfiguration());
    }

    private FixationHandler leftClickTimeDecrease() {
        return new FixationHandler(new ChangeSensitivityAction(leftClickSensitivity, -STEP), context.getOptionsConfiguration());
    }
}
