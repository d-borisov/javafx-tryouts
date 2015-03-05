package sample.controllers.screens;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sample.controllers.AppScreenController;
import sample.logic.bus.BusManager;

public class ClientsListController implements AppScreenController {

    @FXML
    public void onAction(ActionEvent actionEvent) {
        BusManager.post(new SomeControllerClickButton());
    }

    public static final class SomeControllerClickButton {
    }
}
