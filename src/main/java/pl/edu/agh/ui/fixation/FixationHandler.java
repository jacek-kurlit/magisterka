package pl.edu.agh.ui.fixation;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;
import pl.edu.agh.config.OptionsConfiguration;
import pl.edu.agh.ui.fixation.action.Action;

public class FixationHandler implements EventHandler<MouseEvent> {

    private OptionsConfiguration configuration;
    private long lastFixationTime;
    private Action action;

    public FixationHandler(Action action, OptionsConfiguration configuration) {
        this.configuration = configuration;
        this.action = action;
    }

    @Override
    public void handle(MouseEvent event) {
        handleEventType(event);

        if(isFixatedOnElement()) {
            lastFixationTime = System.currentTimeMillis();
            action.onUserFixation();
        }
    }

    private void handleEventType(MouseEvent event) {
        if(isEventOfType(event, MouseEvent.MOUSE_ENTERED)) {
            mouseEnter();
        } else if(isEventOfType(event, MouseEvent.MOUSE_CLICKED)) {
            forceEnter();
        } else if(isEventOfType(event, MouseEvent.MOUSE_EXITED)) {
            mouseExit();
        }
    }

    private boolean isEventOfType(MouseEvent event, EventType<MouseEvent> expectedEvent) {
        return event.getEventType().equals(expectedEvent);
    }

    private void mouseEnter() {
        lastFixationTime = System.currentTimeMillis();
    }

    private void forceEnter() {
       lastFixationTime =  -configuration.getElementFixationTime();
    }

    private void mouseExit() {
        lastFixationTime = 0;
    }

    private boolean isFixatedOnElement() {
        if(lastFixationTime == 0) {
            return false;
        }

        long fixationTime = System.currentTimeMillis() - lastFixationTime;

        return  fixationTime >= configuration.getElementFixationTime();
    }
}
