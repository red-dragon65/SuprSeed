package dev.suprseed.Engine.Core.Scenes.SceneHeirarchy;

import dev.suprseed.Engine.Core.System.RegisterTypes.SceneRegister;

public interface SceneController<T> {

    SceneRegister<T> getRegister();

    void registerScene(BaseScene scene);

    void destroyScene(BaseScene scene);
}
