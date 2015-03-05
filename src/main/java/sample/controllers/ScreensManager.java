package sample.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import sample.log.Logger;

import java.util.HashMap;
import java.util.Map;

public class ScreensManager {

    private static final Logger logger = Logger.newLogger(ScreensManager.class);

    private MainWindowController mainWindowController;

    public ScreensManager(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }

    private volatile AppScreen currentScreen;
    private final Map<AppScreen, Parent> screens = new HashMap<>();
    private final Map<AppScreen, AppScreenController> controllers = new HashMap<>();

    public void loadScreen(AppScreen appScreen) {
        try {
            final FXMLLoader myLoader = new FXMLLoader(getClass().getResource(appScreen.getLocation()));
            final Parent node = myLoader.load();
            final Object controller = myLoader.getController();

            if (controller instanceof AppScreenController) {
                final AppScreenController screenController = (AppScreenController) controller;
                screenController.setSceneProvider(mainWindowController);
                controllers.put(appScreen, screenController);
            }
            screens.put(appScreen, node);
        } catch (Exception e) {
            logger.warning(e, "Exception while loading node " + appScreen);
        }
    }

    public void setScreen(AppScreen appScreen) {
        final Node node = screens.get(appScreen);
        if (node == null) {
            logger.debug("Screen hasn't been loaded: " + appScreen);
            return;
        }

        AnchorPane.setBottomAnchor(node, 0.0);
        AnchorPane.setLeftAnchor(node, 0.0);
        AnchorPane.setTopAnchor(node, 0.0);
        AnchorPane.setRightAnchor(node, 0.0);

        if (currentScreen != null) {
            final AppScreenController currentController = controllers.get(currentScreen);
            if (currentController != null) {
                currentController.onHide();
            }
        }

        final AppScreenController screenController = controllers.get(appScreen);
        if (screenController != null) {
            screenController.onShow();
        }

        final ObservableList<Node> nodes = mainWindowController.getScreensContainer().getChildren();
        if (!nodes.isEmpty()) {
            nodes.remove(0);
        }
        nodes.add(node);

        currentScreen = appScreen;
    }

    public void unloadScreen(AppScreen appScreen) {
        final Node removed = screens.remove(appScreen);
        if (removed == null) {
            logger.debug("Screen didn't exist: " + appScreen);
        }
    }

}
