package pl.edu.agh.ui.fixation.action;

import pl.edu.agh.ui.fixation.action.model.Sensitivity;

public class ChangeSensitivityAction implements Action {
    private Sensitivity sensitivity;
    private int step;

    public ChangeSensitivityAction(Sensitivity sensitivity, int step) {
        this.sensitivity = sensitivity;
        this.step = step;
    }

    @Override
    public void onUserFixation() {
        sensitivity.changeValueBy(step);
    }
}
