package dev.suprseed.Engine.Core.System.RegisterTypes;

import java.util.List;

public interface SceneRegister<T> extends ObjectRegister<T> {

    List<T> getRegisterList();

    void syncLayers();
}
