package pl.edu.agh.driver;

import com.theeyetribe.client.GazeManager;
import com.theeyetribe.client.IGazeListener;
import com.theeyetribe.client.data.GazeData;
import pl.edu.agh.config.OptionsConfiguration;
import pl.edu.agh.driver.filters.GesturesFilter;
import pl.edu.agh.driver.filters.MovementFilter;

import java.awt.*;

public class MouseDriver {

    public void start(OptionsConfiguration optionsConfiguration) throws AWTException {
        final GazeManager gm = GazeManager.getInstance();
        gm.activate(GazeManager.ApiVersion.VERSION_1_0, GazeManager.ClientMode.PUSH);
        final GazeListener gazeListener = new GazeListener(new Robot(), optionsConfiguration);
        gm.addGazeListener(gazeListener);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                gm.removeGazeListener(gazeListener);
                gm.deactivate();
            }
        });
    }

    private  static class GazeListener implements IGazeListener {
        private MovementFilter movementFilter;
        private GesturesFilter gesturesFilter;

        public GazeListener(Robot robot, OptionsConfiguration optionsConfiguration) {
            movementFilter = new MovementFilter(robot, optionsConfiguration);
            gesturesFilter = new GesturesFilter(robot, optionsConfiguration);
        }

        @Override
        public void onGazeUpdate(GazeData gazeData) {
            movementFilter.moveCursor(gazeData);
            gesturesFilter.detectGestures(gazeData);
        }

    }
}
