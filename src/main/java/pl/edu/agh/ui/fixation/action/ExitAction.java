package pl.edu.agh.ui.fixation.action;

public class ExitAction implements Action {

    @Override
    public void onUserFixation() {
        System.out.println("Exit");
        System.exit(0);
    }
}
