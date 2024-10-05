package dev.suprseed.demo.Engine.Core.Scenes.SceneHeirarchy;

import dev.suprseed.demo.Engine.Core.Scenes.SceneStrategy.SceneChangeStrategy;
import dev.suprseed.demo.Engine.Core.System.Register.ListRegister;

public interface SceneController<T> {

    void changeScene(SceneChangeStrategy<T> strategy, T oldScene, String... sceneId);

    ListRegister<T> getRegister();
}
