package sample.controllers.screens;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import sample.controllers.AppScreenController;
import sample.controllers.SceneProvider;
import sample.logic.bus.BusManager;

public class LoginController implements AppScreenController {

    private SceneProvider sceneProvider;
    private EventHandler<? super KeyEvent> previousOnKeyPressed;

    @Override
    public void setSceneProvider(SceneProvider sceneProvider) {
        this.sceneProvider = sceneProvider;
    }

    @Override
    public void onShow() {
        previousOnKeyPressed = sceneProvider.getScene().getOnKeyPressed();

        sceneProvider.getScene().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                BusManager.post(new LoginControllerOnLoginCard());
            }
        });
    }

    @Override
    public void onHide() {
        sceneProvider.getScene().setOnKeyPressed(previousOnKeyPressed);
    }

    public static class LoginControllerOnLoginCard {
    }
}
