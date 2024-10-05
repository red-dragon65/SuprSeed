package dev.suprseed.Engine.Core.InputHandler.TouchInput;

import java.util.List;

import dev.suprseed.Engine.Core.System.Register.ObjectRegister;

public interface InputRegister<T> extends ObjectRegister<T> {

    List<T> getRegisterList();
}
