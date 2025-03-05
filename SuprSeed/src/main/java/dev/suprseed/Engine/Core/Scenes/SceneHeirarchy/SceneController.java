package dev.suprseed.Engine.Core.Scenes.SceneHeirarchy;

import dev.suprseed.Engine.Core.System.RegisterTypes.SceneRegister;

public interface SceneController<T> {

    SceneRegister<T> getRegister();
}
