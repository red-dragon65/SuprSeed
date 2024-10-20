package dev.suprseed.Engine.Core.System.RegisterTypes;

import java.util.List;

public interface ISceneRegister<T> extends IObjectRegister<T> {

    List<T> getRegisterList();
}
