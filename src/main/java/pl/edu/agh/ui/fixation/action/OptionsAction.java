package pl.edu.agh.ui.fixation.action;

import javafx.scene.Parent;
import javafx.scene.Scene;
import pl.edu.agh.ui.controllers.ControllerContext;
import pl.edu.agh.ui.loaders.ControllersLoader;

public class OptionsAction implements Action {

    private ControllerContext context;

    public OptionsAction(ControllerContext context) {
        this.context = context;
    }

    @Override
    public void onUserFixation()  {
        Parent root = ControllersLoader.loadAndInit("views/OptionsWindow.fxml", context);
        Scene scene = new Scene(root);

        context.setPreviousScene(context.getPrimaryStage().getScene());
        context.getPrimaryStage().setTitle("Opcje");
        context.getPrimaryStage().setScene(scene);
    }
}
