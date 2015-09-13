package pl.edu.agh.driver.filters;

import com.theeyetribe.client.data.GazeData;
import com.theeyetribe.client.data.Point2D;
import pl.edu.agh.config.OptionsConfiguration;

import java.awt.*;

public class MovementFilter {
    public static final double BLINKING_PUPIL_SIZE = 0.5;

    private Point2D currentPosition = new Point2D(0, 0);

    private Robot robot;
    private OptionsConfiguration optionsConfiguration;

    public MovementFilter(Robot robot, OptionsConfiguration optionsConfiguration) {
        this.robot = robot;
        this.optionsConfiguration = optionsConfiguration;
    }

    public void moveCursor(GazeData gazeData) {
        if (bothEyesAreOpen(gazeData)) {
            updateCurrentPosition(gazeData.smoothedCoordinates);
            robot.mouseMove((int)currentPosition.x, (int)currentPosition.y);
        }
    }

    private boolean bothEyesAreOpen(GazeData gazeData) {
        return gazeData.rightEye.pupilSize >= BLINKING_PUPIL_SIZE && gazeData.leftEye.pupilSize >= BLINKING_PUPIL_SIZE;
    }

    private void updateCurrentPosition(Point2D targetPosition) {
        double distance = distance(targetPosition);
        double step = distance * optionsConfiguration.getMouseSensitivity();

        double x_orientation = orientation(currentPosition.x, targetPosition.x);
        double y_orientation = orientation(currentPosition.y, targetPosition.y);

        currentPosition.x = currentPosition.x + step * x_orientation;
        currentPosition.y = currentPosition.y + step * y_orientation;
    }

    private double distance(Point2D targetPosition) {
        double x_difference = currentPosition.x - targetPosition.x;
        double y_difference = currentPosition.y - targetPosition.y;

        double d = x_difference*x_difference + y_difference*y_difference;

        if(d == 0.0) {
            return 0.1;
        }

        return Math.sqrt(d);
    }

    private double orientation(double from, double to) {
        double difference = from - to;

        if(difference < 0) {
            return 1.0;
        }

        return -1.0;
    }

}
