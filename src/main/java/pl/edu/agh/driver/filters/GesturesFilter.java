package pl.edu.agh.driver.filters;

import com.theeyetribe.client.data.GazeData;
import pl.edu.agh.config.OptionsConfiguration;

import java.awt.*;
import java.awt.event.InputEvent;

public class GesturesFilter {

    private Robot robot;
    private OptionsConfiguration optionsConfiguration;

    private long leftEyeClosedTime = 0;
    private long rightEyeClosedTime = 0;
    private  long bothEyesClosedTime = 0;

    public GesturesFilter(Robot robot, OptionsConfiguration optionsConfiguration)
    {
        this.robot = robot;
        this.optionsConfiguration = optionsConfiguration;
    }

    public void detectGestures(GazeData gazeData) {
        detectLeftEyeGesture(gazeData.leftEye);
        exitProgram(gazeData);
    }

    private void exitProgram(GazeData gazeData) {
/*        if(isEyeClosed(gazeData.rightEye) && isEyeClosed(gazeData.leftEye)) {
            long eyesClosedTime = System.currentTimeMillis() - bothEyesClosedTime;
            if(eyesClosedTime > Consts.EXIT_FIXATION_TIME) {
                System.out.println("exit");
                System.exit(1);
            }
        } else {
            bothEyesClosedTime = System.currentTimeMillis();
        }*/
    }


    private void detectLeftEyeGesture(GazeData.Eye leftEye) {
        if(isEyeClosed(leftEye)) {
            long eyeClosedTime = System.currentTimeMillis() - leftEyeClosedTime;
            if(eyeClosedTime > optionsConfiguration.getLeftClickTime()) {
                pressLeftMouseKey();
                leftEyeClosedTime = System.currentTimeMillis();
            }
        } else {
            leftEyeClosedTime = System.currentTimeMillis();
        }

    }

    private boolean isEyeClosed(GazeData.Eye eye) {
        return eye.pupilSize < MovementFilter.BLINKING_PUPIL_SIZE;
    }

    private void pressLeftMouseKey() {
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    private void pressRightMouseKey() {
        robot.mousePress(InputEvent.BUTTON3_MASK);
        robot.mouseRelease(InputEvent.BUTTON3_MASK);
    }
}
