package sample.controllers;

public interface AppScreenController {

    default void setSceneProvider(SceneProvider sceneProvider) {
    }

    default void onShow() {
    }

    default void onHide() {
    }
}
