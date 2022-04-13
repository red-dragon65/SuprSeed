package com.cruntchy.suprseed.Engine.MainView.Scenes;

public interface SceneChangeStrategy {

    void changeScene(SceneController controller, Scene oldScene, String... sceneId);
}
