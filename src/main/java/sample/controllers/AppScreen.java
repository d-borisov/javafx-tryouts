package sample.controllers;

import sample.logic.app.CantStartException;
import sample.utils.ResourceLoader;

public enum AppScreen {
    SCREENS_MANAGER("/views/mainWindow.fxml"),
    LOGIN("/views/screens/login.fxml"),
    CLIENTS_LIST("/views/screens/clientsList.fxml");

    private final String location;

    private AppScreen(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public static void checkLocations() throws CantStartException {
        for (AppScreen each : AppScreen.values()) {
            if (ResourceLoader.url(each.location) == null) {
                throw new CantStartException("Bad location %s of %s", each.location, each);
            }
        }

    }
}
