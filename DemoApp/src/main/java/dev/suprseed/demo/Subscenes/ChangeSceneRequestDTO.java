package dev.suprseed.demo.Subscenes;

public class ChangeSceneRequestDTO {

    public CurrentScene currentScene = CurrentScene.MENU;
    public boolean isChangeRequested = false;

    public enum CurrentScene {
        MENU,
        GAMEPLAY
    }
}
