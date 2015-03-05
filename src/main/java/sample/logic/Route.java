package sample.logic;

import com.google.common.eventbus.Subscribe;
import sample.controllers.AppScreen;
import sample.controllers.ScreensManager;
import sample.controllers.screens.ClientsListController;
import sample.controllers.screens.LoginController;
import sample.logic.bus.BusManager;

public class Route {

    private final ScreensManager screensManager;

    private Route(ScreensManager screensManager) {
        this.screensManager = screensManager;
    }

    public static Route init(ScreensManager screensManager) {
        for (AppScreen appScreen : AppScreen.values()) {
            screensManager.loadScreen(appScreen);
        }
        screensManager.setScreen(AppScreen.LOGIN);

        final Route route = new Route(screensManager);
        BusManager.register(route);

        return route;
    }

    @Subscribe
    public void onSomeControllerClickButton(ClientsListController.SomeControllerClickButton action) {
        screensManager.setScreen(AppScreen.LOGIN);
    }

    @Subscribe
    public void onLoginControllerLoginCard(LoginController.LoginControllerOnLoginCard action) {
        screensManager.setScreen(AppScreen.CLIENTS_LIST);
    }
}
