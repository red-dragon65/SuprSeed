package dev.suprseed.Engine.Lib.Input.Registers;

import java.util.List;

import dev.suprseed.Engine.Core.System.RegisterTypes.ObjectRegister;

public interface InputRegister<T> extends ObjectRegister<T> {

    List<T> getRegisterList();
}
