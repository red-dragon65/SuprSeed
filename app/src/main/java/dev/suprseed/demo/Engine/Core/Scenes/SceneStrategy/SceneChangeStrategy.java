package dev.suprseed.demo.Engine.Core.Scenes.SceneStrategy;

import dev.suprseed.demo.Engine.Core.Scenes.SceneHeirarchy.SceneManager;

public interface SceneChangeStrategy<T> {

    void changeScene(SceneManager parentScene, T oldScene, String... sceneId);
}
