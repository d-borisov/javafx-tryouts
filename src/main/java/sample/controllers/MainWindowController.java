package sample.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class MainWindowController implements SceneProvider {

    @FXML // mainWindow.fxml -> AnchorPane fx:id="screensContainer"
    private AnchorPane screensContainer;

    @Override
    public Scene getScene() {
        return screensContainer.getScene();
    }

    public AnchorPane getScreensContainer() {
        return screensContainer;
    }

    @FXML
    public void onMenuItemChangeUserClick(ActionEvent actionEvent) {

    }

    @FXML
    public void onMenuItemCloseClick(ActionEvent actionEvent) {
        Platform.exit();
    }
}
