package sample;

import com.google.common.base.Throwables;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.controllers.AppScreen;
import sample.controllers.MainWindowController;
import sample.controllers.ScreensManager;
import sample.log.Logger;
import sample.logic.Route;
import sample.logic.app.CantStartException;
import sample.logic.bus.BusManager;
import sample.settings.Settings;
import sample.settings.SettingsHolder;
import sample.utils.ResourceLoader;

public class Main extends Application {

    public static void main(String[] args) {
        try {
            AppScreen.checkLocations();

            final Settings settings = Settings.fromFile(args);
            SettingsHolder.link(settings);
            Logger.init(settings);

            BusManager.init();

            launch(args);
        } catch (CantStartException e) {
            System.out.println("Cant start application.\n" + Throwables.getStackTraceAsString(e));
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(ResourceLoader.load(AppScreen.SCREENS_MANAGER));
        final Parent root = loader.load();
        final MainWindowController controller = loader.getController();

        primaryStage.setTitle("Антикафе");
        primaryStage.setScene(new Scene(root));

// отключение возможности закрыть приложение
//        Platform.setImplicitExit(false);
//        primaryStage.setOnCloseRequest(Event::consume);
// полноэкранный режим
//        primaryStage.setFullScreen(true);
//        primaryStage.setFullScreenExitHint("");
//        primaryStage.setFullScreenExitKeyCombination(new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN));

        final ScreensManager screensManager = new ScreensManager(controller);
        Route.init(screensManager);

        primaryStage.show();
    }
}
