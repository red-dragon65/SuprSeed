package com.cruntchy.suprseed.Engine.Core.Scenes.SceneStrategy;

import com.cruntchy.suprseed.Engine.Core.Scenes.SceneHeirarchy.SceneManager;

public interface SceneChangeStrategy<T> {

    void changeScene(SceneManager parentScene, T oldScene, String... sceneId);
}
