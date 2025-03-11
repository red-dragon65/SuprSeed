package dev.suprseed.Engine.Core.System.RegisterTypes;

import java.util.List;
import java.util.Optional;

import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;

public interface SceneRegister<T> extends ObjectRegister<T> {

    List<T> getRegisterList();

    void syncLayers();

    void removeObject(String sceneId);

    Optional<BaseScene> getScene(String sceneId);
}
