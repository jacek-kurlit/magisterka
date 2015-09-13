package pl.edu.agh.ui.fixation.action.model;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class Sensitivity {
    private Label label;
    private ProgressBar progressBar;
    private double currentValue;

    private static final double MIN_VALUE = 100.0;
    private static final double MAX_VALUE = 3000.0;
    private static final double RANGE = MAX_VALUE - MIN_VALUE;

    public Sensitivity(Label label, ProgressBar progressBar, int value) {
        this.label = label;
        this.progressBar = progressBar;
        this.currentValue = value;

        updateView();
    }

    public void changeValueBy(int step) {
        currentValue += step;
        if(currentValue < MIN_VALUE) {
            currentValue = MIN_VALUE;
        }

        if(currentValue > MAX_VALUE) {
            currentValue = MAX_VALUE;
        }

        updateView();
    }

    private void updateView() {
        label.setText(currentValue + "");
        progressBar.setProgress(calculateProgress());
    }

    private double calculateProgress() {
        return (currentValue - MIN_VALUE ) / RANGE;
    }

    public int getCurrentValue() {
        return (int)currentValue;
    }
}
