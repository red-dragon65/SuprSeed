package dev.suprseed.demo.Engine.Core.InputHandler.TouchInput;

import dev.suprseed.demo.Engine.Core.System.Register.ObjectRegister;

import java.util.List;

public interface InputRegister<T> extends ObjectRegister<T> {

    List<T> getRegisterList();
}
