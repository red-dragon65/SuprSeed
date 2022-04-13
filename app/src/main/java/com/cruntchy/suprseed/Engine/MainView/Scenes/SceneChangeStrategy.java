package com.cruntchy.suprseed.Engine.MainView.Scenes;

import com.cruntchy.suprseed.Engine.MainView.GameProcessor.BetterScene.SceneManager;

public interface SceneChangeStrategy<T> {

    void changeScene(SceneManager parentScene, T oldScene, String... sceneId);
}
