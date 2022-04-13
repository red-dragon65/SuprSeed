package com.cruntchy.suprseed.Engine.Scenes.SceneStrategy;

import com.cruntchy.suprseed.Engine.Scenes.SceneHeirarchy.SceneManager;

public interface SceneChangeStrategy<T> {

    void changeScene(SceneManager parentScene, T oldScene, String... sceneId);
}
