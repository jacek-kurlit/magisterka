package pl.edu.agh.config;

import javax.inject.Singleton;

@Singleton
public class OptionsConfiguration {
    private int elementFixationTime = 1000;
    private int leftClickTime = 500;
    private double mouseSensitivity = 1.0;

    public int getElementFixationTime() {
        return elementFixationTime;
    }

    public void setElementFixationTime(int elementFixationTime) {
        this.elementFixationTime = elementFixationTime;
    }

    public int getLeftClickTime() {
        return leftClickTime;
    }

    public void setLeftClickTime(int leftClickTime) {
        this.leftClickTime = leftClickTime;
    }

    public double getMouseSensitivity() {
        return mouseSensitivity;
    }

    public int getScaledMouseSensitivity() {
        return (int)mouseSensitivity * 1000;
    }

    public void setScaledMouseSensitivity(int mouseSensitivity) {

        this.mouseSensitivity = (double)mouseSensitivity / 1000.0;
    }
}
