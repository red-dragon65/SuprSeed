package dev.suprseed.Engine.Core.InputHandler.TouchInput;

import java.util.List;

import dev.suprseed.Engine.Core.System.RegisterTypes.IObjectRegister;

public interface InputRegister<T> extends IObjectRegister<T> {

    List<T> getRegisterList();
}
