package dev.suprseed.Engine.Core.Scenes.SceneHeirarchy;

import dev.suprseed.Engine.Core.Scenes.SceneStrategy.SceneChangeStrategy;
import dev.suprseed.Engine.Core.System.Register.ListRegister;

public interface SceneController<T> {

    void changeScene(SceneChangeStrategy<T> strategy, T oldScene, String... sceneId);

    ListRegister<T> getRegister();
}
